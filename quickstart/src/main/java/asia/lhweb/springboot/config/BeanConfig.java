package asia.lhweb.springboot.config;

import asia.lhweb.springboot.bean.Cat;
import asia.lhweb.springboot.bean.Dog;
import asia.lhweb.springboot.bean.Furn;
import asia.lhweb.springboot.bean.Monster;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * 1.@Configuration标识这是一个配置类，等价于配置文件
 * 韩顺平教育
 * 2．程序员可以通过@Bean注解注入bean对象到容器
 * 3．当一个类被@Configuration标识，该类-Bean也会注入容器
 *
 * @author 罗汉
 * @date 2023/11/10
 * <p>
 * 1.proxyBeanMethods: 代理bean的方法
 * (1)Ful(proxyBeanMethods =true)、保证每个@Bean方法被调用多少次返回的组件都是单实例的,是代理方式】*
 * (2) Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的是非代理方式】
 * (3)特别说明;: proxyBeanMethods是在调用@Bean方法才生效，因此，需要先获取BeanConfig组件，
 * 再调用方法*而不是直接通过SpringBoot主程序得到的容器来获取bean,注意观察直接通过ioc.getBean()获取Bean,
 * proxyBeanMethods值并没有生效
 * (4)如何选择:组件依赖必须使用Full模式默认。如果不需要组件依赖使用Lite模*
 * (5) Lite榄也称为轻帚级榄式，因为不检测依劫关系，运行谏度快
 * 注意：！！！！！！！如果@Configuration(proxyBeanMethods = true)然后再Bean设置了@Scope("prototype")
 * 那么依然不是单例的
 */

/**
 * bean配置
 * Import 可以传入一个数组，可以一次注入多个组件
 * public @interface Import {
 * Class<?>[] value();
 * }
 * 注意@Import 方式注入的组件, 默认组件的名字就是全类名
 *

 */
@Configuration(proxyBeanMethods = true)//@Configuration 标识这是一个配置类: 等价 配置文件 默认ture是单例的
@Import(value = {Dog.class, Cat.class})
//@EnableConfigurationProperties (Furn. class)解读
//1、开启Furn配置绑定功能
//2、把Furn组件自动注册到容器中
//3 有这个注解可以吧Furn上的Component注解取消
@EnableConfigurationProperties({Furn.class})
public class BeanConfig {
    /**
     * 1. @Bean : 给容器中添加组件
     * 2. monster01() : 默认方法名作为组件的 id
     * 3. Monster: 返回类型就是组件类型, 返回的值就是 new Monster(100, "牛魔王", 500, "芭蕉扇")
     * 4. @Bean("monster_nmw"): 重新指定组件的 id = “monster_nmw”
     * 5. 配置类里面使用@Bean 标注在方法上给容器注册组件，默认是单实例的
     */
    @Bean
    // @Scope("prototype")// 设置不是单例的
    // @Bean(name = "monster_02")//不设置就是默认是方法名为ID
    public Monster monster01() {
        return new Monster(200, "牛魔王", 500, "封魔拳");
    }

    /**
     * * 1. 表示只有容器中注入了 name = monster_nmw 的组件，下面的组件(dog01)才会被
     * 注入
     * * 2. @ConditionalOnBean(name = "monster_nmw") 也可以放在类名处，
     * * 则表示对该配置类中所有要注入
     * * 的组件都进行条件约束
     * * 3. 还有很多其它条件约束注解，用到时在讲解.
     * @return
     */
    /**
     * test01
     * 用来测试@ConditionalOnBean(name = "monster_nmw")的bean
     * 1 这里的bean是按顺序注入的 所以这个方法要放在前面
     * @return {@link Monster}
     */
    @Bean(name = "monster_nmw")
    public Monster test01() {
        return new Monster(200, "牛魔王", 500, "封魔拳");
    }
    @Bean
    // @Bean(name = "monster_nmw")
    /**
     * @ConditionalOnBean(name = "monster_nmw")表示：
     * 1 当容器中有一个bean,名字是monster_new 就注入dog01这个dog bean
     * 如果没有就不注入dog 01这个bean
     * 2 可以放在方法上、类上
     * 3 放在类上表示该类的所有组件都进行条件约束
     */
    @ConditionalOnBean(name = "monster_nmw")
    public Dog dog01(){
        return new Dog();
    }


}
