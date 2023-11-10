package asia.lhweb.springboot;

import asia.lhweb.springboot.bean.Cat;
import asia.lhweb.springboot.bean.Dog;
import asia.lhweb.springboot.bean.Monster;
import asia.lhweb.springboot.config.BeanConfig;
import asia.lhweb.springboot.config.BeanConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :罗汉
 * @date : 2023/9/27
 */
@SpringBootApplication(scanBasePackages = {"asia.lhweb"})//表示是springboot项目
public class MainApp {
    private static ConfigurableApplicationContext ioc;

    public static void main(String[] args) {
        //启动
        ioc = SpringApplication.run(MainApp.class, args);
        // String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        // for (String beanDefinitionName : beanDefinitionNames) {
        //     System.out.println("beanDefinitionName:"+beanDefinitionName);
        // }


        //测试在sb中，依然可以用spring方式注入----------开始
        // method1();

        //测试用@Configuration方式注入
        // method2();

        //=======获取配置类========
        // method3();

        //=======演示@Configuration(proxyBeanMethods = true)=============
        // method4();

        //测试可以有多个配置类
        // method5();

        //=======演示@Import=============
        // method6();

        //=======演示@Conditional=============
        // method7();

        //=======@ImportResource(locations = "classpath:beans.xml")=============
        method8();

    }

    private static void method8() {
        Monster monster03 = ioc.getBean("monster03", Monster.class);
        Monster monster04 = ioc.getBean("monster04", Monster.class);
        System.out.println("Monster03: " + monster03);
        System.out.println("Monster04: " + monster04);

        System.out.println("Monster03 bean是否存在"+ioc.containsBean("monster03"));
        System.out.println("Monster04 bean是否存在"+ioc.containsBean("monster04"));
    }

    /**
     * 测试在sb中，依然可以用spring方式注入
     */
    private static void method1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster03 = ac.getBean("monster03", Monster.class);
        System.out.println("monster03:"+monster03);
    }
    private static void method2() {
        Monster monster01 = ioc.getBean("monster01", Monster.class);
        Monster monster02 = ioc.getBean("monster01", Monster.class);
        System.out.println(monster01+":"+monster01.hashCode());
        System.out.println(monster02+":"+monster02.hashCode());
        System.out.println(monster02==monster01);
    }
    private static void method3() {
        BeanConfig bean = ioc.getBean(BeanConfig.class);
        System.out.println(bean);
    }
    private static void method4() {
        BeanConfig beanConfig = ioc.getBean(BeanConfig.class);
        Monster monster = beanConfig.monster01();
        Monster monster2 = beanConfig.monster01();
        System.out.println(monster.hashCode()+":"+monster);
        System.out.println(monster2.hashCode()+":"+monster2);
    }
    private static void method5() {
        BeanConfig beanConfig = ioc.getBean(BeanConfig.class);
        Monster monster1 = beanConfig.monster01();
        BeanConfig2 beanConfig2 = ioc.getBean(BeanConfig2.class);
        Monster monster2 = beanConfig2.monster02();

        System.out.println(monster2);
        System.out.println(monster1);
    }
    private static void method6() {
        Dog bean = ioc.getBean(Dog.class);
        Cat bean2 = ioc.getBean(Cat.class);
        System.out.println(bean);
        System.out.println(bean2);
    }

    /**
     * 报错 ：  No bean named 'dog01' available
     */
    private static void method7() {
        Dog bean = ioc.getBean("dog01",Dog.class);
        System.out.println(bean);
    }

}
