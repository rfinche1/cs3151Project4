package com.rasfincher.cs3151.project4.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import com.rasfincher.cs3151.project4.model.BinarySearchTree;
import com.rasfincher.cs3151.project4.model.WordFrequencyAnalyzer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TreeController {
  private File inputFile;
  private BinarySearchTree<String> searchTree;

	@FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem preferencesMenuItem;

    @FXML
    private MenuItem quitMenuItem;

    @FXML
    private TextArea outputTextBox;

    @FXML
    private TextField treeHeightTextArea;

    @FXML
    private TextField treeSizeTextArea;

    @FXML
    private TextField leafCountTextArea;
    
    @FXML
    void chooseInputFile(ActionEvent event) {
    	File chosenFile = this.displayFileChooser();
    	
    	if (chosenFile == null) {
    		return;
    	} else {
    		this.setInputFile(chosenFile);
        	this.scanInputFile();
        	this.displayOutput();
    	}
    }

    private File displayFileChooser() {
      FileChooser chooser = new FileChooser();
    	chooser.setTitle("Choose a text file to analyze.");
    	chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"));
    	String userDirectoryString = System.getProperty("user.home");
    	File userDirectory = new File(userDirectoryString);
    	chooser.setInitialDirectory(userDirectory);
    	
    	File chosenFile = chooser.showOpenDialog(new Stage());
      return chosenFile;
    }
    
    private void scanInputFile() {
    	Scanner scanner;
    	this.setSearchTree(new BinarySearchTree<String>());
    	try {
			scanner = new Scanner(this.getInputFile());
			  while(scanner.hasNext()) {
			    this.getSearchTree().add(scanner.next());
			  }
			scanner.close();
			}
    	
    	catch (FileNotFoundException e) {
			e = new FileNotFoundException("File was not found.");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("File Not Found");
			alert.setContentText(e.getMessage());
		  }
    }
    
    private void displayOutput() {
    	this.treeHeightTextArea.setText(Integer.toString(this.getSearchTree().height()));
    	this.treeSizeTextArea.setText(Integer.toString(this.getSearchTree().size()));
    	this.leafCountTextArea.setText(Integer.toString(this.getSearchTree().leafCount()));
    	this.outputTextBox.setText("");
    	ArrayList<String> words = new ArrayList<String>();
    	for (String currentString : this.getSearchTree()) {
        words.add(currentString.toLowerCase());
      }
    	Collections.sort(words);
    	WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer(words);
    	this.outputTextBox.setText("Words and the number of times they appear" + System.lineSeparator());
    	this.outputTextBox.appendText(analyzer.getWordFrequencyData());
    	
    }

	/**
	 * Gets the input file
	 * 
	 * @return the input file
	 */
  public File getInputFile() {
		return inputFile;
	}

  /**
   * Sets the input file
   * 
   * @precondition inputFile != null
   * @postcondition this.inputFile = inputFile
   * 
   * @param inputFile
   */
	public void setInputFile(File inputFile) {
		this.inputFile = Objects.requireNonNull(inputFile);
	}
	
	/**
	 * Gets the search tree
	 * 
	 * @return the search tree
	 */
	public BinarySearchTree<String> getSearchTree() {
		return this.searchTree;
	}

	/**
	 * Sets the search tree
	 * 
	 * @precondition searchTree != null
	 * @postcondition this.searchTree = searchTree;
	 * 
	 * @param searchTree
	 */
	private void setSearchTree(BinarySearchTree<String> searchTree) {
		this.searchTree = Objects.requireNonNull(searchTree);
	}

}
