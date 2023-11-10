package asia.lhweb.springboot.config;

import asia.lhweb.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 豆config3
 *
 * @author 罗汉
 * @date 2023/11/10
 */
@Configuration()//@Configuration 标识这是一个配置类: 等价 配置文件
//导入xml
@ImportResource(locations = {"classpath:beans.xml","classpath:beans02.xml"})
public class BeanConfig3 {

}
