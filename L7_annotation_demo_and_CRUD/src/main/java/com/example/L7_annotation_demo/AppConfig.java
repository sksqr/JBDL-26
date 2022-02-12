package com.example.L7_annotation_demo;

import com.gfg.analyzer.KeywordAnalyserImplementation;
import com.gfg.analyzer.KeywordAnalyzerInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    @Primary
	public KeywordAnalyzerInterface getKeywordAnalyzer(){
		return new KeywordAnalyserImplementation();
	}

}
