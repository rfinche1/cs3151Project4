package com.rasfincher.cs3151.project4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
    this.sortMapForOutput(sb);
    
    return sb.toString();
  }

  private void sortMapForOutput(StringBuilder sb) {
    Set<Entry<String, Integer>> entries = this.getWords().entrySet();
    Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>() {
      
      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
          Integer v1 = e1.getValue();
          Integer v2 = e2.getValue();
          return v2.compareTo(v1);
      }
    };
    
    List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
    Collections.sort(listOfEntries, valueComparator);  
    LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());
    
    
    for(Entry<String, Integer> entry : listOfEntries){
      sortedByValue.put(entry.getKey(), entry.getValue());
    }
    
    Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
    
    for (Entry<String, Integer> mapping : entrySetSortedByValue) {
      sb.append(mapping.getKey() + " -> " + mapping.getValue() + System.lineSeparator());
    }
  }
  
  
}
  