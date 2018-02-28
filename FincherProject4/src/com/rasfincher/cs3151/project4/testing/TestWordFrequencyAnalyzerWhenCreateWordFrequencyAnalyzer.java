package com.rasfincher.cs3151.project4.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rasfincher.cs3151.project4.model.WordFrequencyAnalyzer;

class TestWordFrequencyAnalyzerWhenCreateWordFrequencyAnalyzer {
  private List<String> words;
  
  @BeforeEach
  void setUp() throws Exception {
    this.words = new ArrayList<String>(Arrays.asList("One", "Two", "Three", "Four", "One", "Four"));
  }

  @Test
  void testCreateWordFrequencyAnalyzerWithValidListOfWords() {
    WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer(this.words);
    
    assertEquals(4, analyzer.getWords().size());
    assertEquals((Integer) 2, analyzer.getWords().get("One")); 
    assertEquals((Integer) 1, analyzer.getWords().get("Two")); 
    assertEquals((Integer) 1, analyzer.getWords().get("Three")); 
    assertEquals((Integer) 2, analyzer.getWords().get("Four")); 
  }

}
