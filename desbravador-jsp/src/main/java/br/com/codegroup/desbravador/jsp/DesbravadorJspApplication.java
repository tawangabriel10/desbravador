package br.com.codegroup.desbravador.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "br.com.codegroup.desbravador.jsp")
public class DesbravadorJspApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DesbravadorJspApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DesbravadorJspApplication.class);
    }
}