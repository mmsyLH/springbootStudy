package asia.lhweb.springboot.config;

import asia.lhweb.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 豆config2
 *
 * @author 罗汉
 * @date 2023/11/10
 */
@Configuration()//@Configuration 标识这是一个配置类: 等价 配置文件
public class BeanConfig2 {

    @Bean
    public Monster monster02() {
        return new Monster(300, "蚂蚁精", 800, "吃小动物");
    }
}
