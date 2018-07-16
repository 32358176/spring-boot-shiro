package com.gov.xmxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.gov.xmxx.dao")
@SpringBootApplication()
@EnableTransactionManagement
public class XmxxApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmxxApplication.class, args);
	}
}
