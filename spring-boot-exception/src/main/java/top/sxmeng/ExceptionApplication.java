package top.sxmeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.sxmeng.mapper")
public class ExceptionApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ExceptionApplication.class, args);
    }
}
