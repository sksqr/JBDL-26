package com.gfg.colections;

import java.util.List;

public class DummyImplementation implements KeywordAnalyzerInterface{

    @Override
    public void recordKeyword(String keyword) {

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
