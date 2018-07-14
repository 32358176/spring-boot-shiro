package com.gov.xmxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.gov.xmxx.dao")
@SpringBootApplication()
public class XmxxApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmxxApplication.class, args);
	}
}
