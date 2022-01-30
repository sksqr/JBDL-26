package com.gfg;

import com.gfg.analyzer.KeywordAnalyserImplementation;
import com.gfg.analyzer.KeywordAnalyzerInterface;

public class Demo {
    public static void main(String[] args) {

        KeywordAnalyzerInterface keywordAnalyzerInterface = new KeywordAnalyserImplementation();
        keywordAnalyzerInterface.recordKeyword("Java jobs");
        keywordAnalyzerInterface.recordKeyword("Java Spring jobs");
        keywordAnalyzerInterface.recordKeyword("C++ jobs");
        keywordAnalyzerInterface.recordKeyword("C++ jobs");
        System.out.println(keywordAnalyzerInterface.getAllKeyword());


    }
}
