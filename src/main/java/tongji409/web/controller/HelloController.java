package tongji409.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lijiechu
 * @create on 16/12/3
 * @description
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String sayHello(){
        return "Hello, Spring-Boot World!";
    }
}
