package asia.lhweb;

import asia.lhweb.springboot.bean.Furn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author :罗汉
 * @date : 2023/9/27
 */
@Controller
public class HiController {
    //需求 website从配置文件注入application.properties
    @Value("${my.website}")
    private String website;
    @Resource//把容器里的furn01bean 配置到HiController的注解
    private Furn furn;
    @RequestMapping("/hi")
    @ResponseBody
    public String hello(){
        System.out.println("hello");
        System.out.println(website);
        return "Hi:"+website;
    }
    @RequestMapping("/furn")
    @ResponseBody
    public Furn furn(){
        return furn;
    }

}
