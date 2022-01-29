package com.gfg.colections;

import java.util.List;
//Contract
public interface KeywordAnalyzerInterface {

    void recordKeyword(String keyword);

    List<String> getAllKeyword();

    List<KeywordAndFrequency> getAllKeywordWithFreq();
}
