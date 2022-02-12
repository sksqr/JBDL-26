package com.example.L7_annotation_demo;

import com.gfg.analyzer.KeywordAnalyzerInterface;
import com.gfg.analyzer.KeywordAndFrequency;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("MyKeywordAnalyzer")
public class MyKeywordAnalyzer implements KeywordAnalyzerInterface {

    @Override
    public void recordKeyword(String s) {

    }

    @Override
    public List<String> getAllKeyword() {
        return null;
    }

    @Override
    public List<KeywordAndFrequency> getAllKeywordWithFreq() {
        return null;
    }
}
