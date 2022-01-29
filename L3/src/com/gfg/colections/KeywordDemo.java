package com.gfg.colections;
//Dev-1
public class KeywordDemo {
    public static void main(String[] args) {

        KeywordAnalyzerInterface keywordAnalyzerInterface = new KeywordAnalyserImplementation();
        keywordAnalyzerInterface.recordKeyword("Laptop");
        keywordAnalyzerInterface.recordKeyword("Mobile");
        keywordAnalyzerInterface.recordKeyword("Ear Phone");
        keywordAnalyzerInterface.recordKeyword("Ear Phone");
        keywordAnalyzerInterface.recordKeyword("Ear Phone");
        keywordAnalyzerInterface.recordKeyword("Mobile");
        keywordAnalyzerInterface.recordKeyword("Pen");
        keywordAnalyzerInterface.recordKeyword("Macbook");
        keywordAnalyzerInterface.recordKeyword("Pen");

        for(String keyword: keywordAnalyzerInterface.getAllKeyword()){
            System.out.println(keyword);
        }

        for(KeywordAndFrequency keywordAndFrequency: keywordAnalyzerInterface.getAllKeywordWithFreq()){
            System.out.println(keywordAndFrequency);
        }
    }
}
