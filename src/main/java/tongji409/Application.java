package tongji409;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * @author lijiechu
 * @create on 16/12/3
 * @description SpringBoot启动应用
 */
@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args)throws Exception {
        SpringApplication.run(Application.class, args);
    }

    //实现EmbeddedServletContainerCustomizer接口以修改启动端口
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8100);
    }
}
