package com.example.L7_annotation_demo;

import com.gfg.analyzer.KeywordAnalyserImplementation;
import com.gfg.analyzer.KeywordAnalyzerInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class L7AnnotationDemoApplication {


	public L7AnnotationDemoApplication() {
		System.out.println("Creating object of L7AnnotationDemoApplication");
	}

	public static void main(String[] args) {
//		ProductService productService = new ProductService();
		SpringApplication.run(L7AnnotationDemoApplication.class, args);
	}

}

