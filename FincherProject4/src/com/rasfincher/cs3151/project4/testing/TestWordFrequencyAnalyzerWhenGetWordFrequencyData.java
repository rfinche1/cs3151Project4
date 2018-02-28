package com.rasfincher.cs3151.project4.testing;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rasfincher.cs3151.project4.model.WordFrequencyAnalyzer;

class TestWordFrequencyAnalyzerWhenGetWordFrequencyData {
  private List<String> words;
  
  @BeforeEach
  void setUp() throws Exception {
    this.words = new ArrayList<String>(Arrays.asList("One", "Two", "Three", "Four", "One", "Four"));
    
  }

  @Test
  void testGetWordFrequencyDataWithValidList() {
    WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer(this.words);
    
    String result = analyzer.getWordFrequencyData();
    
    assertEquals("One -> 2" + System.lineSeparator() +
                 "Four -> 2" + System.lineSeparator() +
                 "Two -> 1" + System.lineSeparator() +
                 "Three -> 1" + System.lineSeparator(), result);
  }

}
