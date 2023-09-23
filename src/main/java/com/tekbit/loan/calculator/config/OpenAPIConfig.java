package com.tekbit.loan.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {


    @Value("${tekbit.loan.calculator.openapi.dev-url}")
    private String devUrl;

    @Value("${tekbit.loan.calculator.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("abdulvahap.gencdal@gmail.com");
        contact.setName("A.Vahap Gencdal");
        contact.setUrl("https://github.com/vahapgencdal/loan-calculator");

        License mitLicense = new License().name( "Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("The Loan Calculator API")
                .version("1.0")
                .contact(contact)
                .description("The loan calculator API bases on payback time, desired amount and interest rate.").termsOfService("https://github.com/vahapgencdal/loan-calculator/blob/main/README.md")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
