package com.gfg.analyzer;

import com.google.gson.Gson;

import java.util.List;

//Dev-1
public class KeywordDemo {
    public static void main(String[] args) {

//        KeywordAndFrequency keywordAndFrequency = new KeywordAndFrequency("Mobile",3);
//        System.out.println(keywordAndFrequency);
        Gson gson = new Gson();
//        System.out.println(gson.toJson(keywordAndFrequency));

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
        List<KeywordAndFrequency> list = keywordAnalyzerInterface.getAllKeywordWithFreq();
        String response = gson.toJson(list);
        System.out.println(response);

//        for(KeywordAndFrequency keywordAndFrequency: keywordAnalyzerInterface.getAllKeywordWithFreq()){
//            System.out.println(keywordAndFrequency);
//        }

    }
}
