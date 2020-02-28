package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        Config config = (Config) ctx.getBean("config");
        config.getItems().entrySet().stream().forEach(
                entry -> System.out.println(entry.getKey() +  " = " + entry.getValue().getColor()));

    }
}
