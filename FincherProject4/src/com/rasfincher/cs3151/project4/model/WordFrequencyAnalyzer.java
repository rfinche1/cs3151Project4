package com.rasfincher.cs3151.project4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordFrequencyAnalyzer {
  private HashMap<String, Integer> words;
  
  public WordFrequencyAnalyzer(List<String> rawData) {
    this.populateWords(rawData);
  }
  
  private void populateWords(List<String> rawData) {
    this.words = new HashMap<String, Integer>();
    
    for (String currentWord : rawData) {
      Integer wordCount = this.words.get(currentWord);
      wordCount = (wordCount == null) ? 1 : ++wordCount;
      this.words.put(currentWord, wordCount);
    }
  }
  
  public HashMap<String, Integer> getWords() {
    return this.words;
  }

  /**
   * Returns a String listing every word and it's count
   * 
   * @return A String listing every word and it's count
   */
  public String getWordFrequencyData() {
    StringBuilder sb = new StringBuilder();
    Iterator<Entry<String, Integer>> it = this.getWords().entrySet().iterator();
    
    while (it.hasNext()) {
        Entry<String, Integer> pair = it.next();
        sb.append(pair.getKey() + " -> " + pair.getValue() + System.lineSeparator());
        it.remove();
    }
    return sb.toString();
  }
  
  
}
  