package com.rasfincher.cs3151.project4.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.rasfincher.cs3151.project4.model.BinarySearchTree;
import com.rasfincher.cs3151.project4.model.RedBlackTree;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TreeController {
    private static final String TEXT_FILE = ".txt";
    private static final char DELIMITER = ',';
    private File directory;
    private List<File> files;
    private BinarySearchTree<String> bsTree;
    private RedBlackTree<String> rbTree;
    private StringBuilder outputString;

    @FXML
    private MenuItem openMenuItem;
	
    @FXML
    private MenuItem saveMenuItem;
    
    @FXML
    private Button exportButton;
    
    @FXML
    private MenuItem clearMenuItem;

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
      File chosenDirectory = this.displayOpenChooser();
    	
    	if (chosenDirectory != null) {
    	  this.setDirectory(chosenDirectory);
    	} 
    	
    	this.addInputFiles(this.getDirectory());
    	try {
        this.scanInputFiles();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    	this.outputTextBox.setText("Filename,BSTreeSize,BSTreeHeight,BSTreeLeafCount,RBTreeSize,RBTreeHeight,RBTreeLeafCount"
    	    + System.lineSeparator());
    	this.outputTextBox.appendText(this.outputString.toString());
    	
    	this.enableExportButtons();
    }

    @FXML
    void exportFile(ActionEvent event) {
      FileChooser chooser = displayExportChooser();
      File file = chooser.showSaveDialog(new Stage());
      
      this.saveFile(file);
    }
    
    @FXML
    void clear(ActionEvent event) {
      this.files.clear();
      this.outputString = new StringBuilder();
      this.outputTextBox.setText("No data loaded. Loading data may take some time, please be patient.");
      this.disableExportButtons();
    }
    
    @FXML
    void onQuitMenuItem(ActionEvent event) {
      Platform.exit();
    }
    
    /**
     * Default constructor for a TreeController
     * 
     */
    
    public TreeController() {
      this.files = new ArrayList<File>();
      this.outputString = new StringBuilder();
    }
    
    private void enableExportButtons() {
      this.exportButton.setDisable(false);
      this.saveMenuItem.setDisable(false);
    }
    
    private void disableExportButtons() {
      this.exportButton.setDisable(true);
      this.saveMenuItem.setDisable(true);
    }
    
    private void addInputFiles(File file) {
      for (File currentFile : file.listFiles()) {
        if (currentFile.getName().endsWith(TEXT_FILE)) {
            this.getFiles().add(currentFile);
        }
        if (currentFile.isDirectory()) {
          this.addInputFiles(currentFile);
        }
      }
    }

    private File displayOpenChooser() {
      DirectoryChooser chooser = new DirectoryChooser();
    	chooser.setTitle("Choose a folder of text files to analyze.");
    	String userDirectoryString = System.getProperty("user.home");
    	File userDirectory = new File(userDirectoryString);
    	chooser.setInitialDirectory(userDirectory);
    	
    	File chosenFile = chooser.showDialog(new Stage());
      return chosenFile;
    }
    
    private FileChooser displayExportChooser() {
      FileChooser.ExtensionFilter txtFilter = 
          new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
      FileChooser.ExtensionFilter csvFilter = 
          new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
      
      FileChooser chooser = new FileChooser();
      chooser.getExtensionFilters().add(txtFilter);
      chooser.getExtensionFilters().add(csvFilter);
      chooser.setInitialFileName("treeComparison.csv");
      return chooser;
    }

    private void saveFile(File file) {
      if (file != null) {
        try {
          FileWriter writer = new FileWriter(file);
          writer.write(this.outputTextBox.getText());
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    
    private void generateTreeOutput(BinarySearchTree<String> bsTree, RedBlackTree<String> rbTree, File file) {
      this.outputString.append(file.getName() + DELIMITER + bsTree.size() + DELIMITER + bsTree.height() + 
                DELIMITER + bsTree.leafCount() + DELIMITER + rbTree.size() + DELIMITER + rbTree.height() + 
                DELIMITER + rbTree.leafCount() + System.lineSeparator());
    }
    
    private void scanInputFiles() throws IOException{
    	Scanner scanner;
    	
      for (File currentFile : this.getFiles()) {
    	  scanner = new Scanner(currentFile);
    	  this.bsTree = new BinarySearchTree<String>();
    	  this.rbTree = new RedBlackTree<String>();
    	  
    	  while (scanner.hasNext()) {
    	    String token = scanner.next();
    	    this.bsTree.add(token);
    	    this.rbTree.add(token);
    	  }
    	  this.generateTreeOutput(bsTree, rbTree, currentFile);
    	  scanner.close();
    	}
    }
    
    public List<File> getFiles() {
      return this.files;
    }

    /**
     * Gets the input file
     * 
     * @return the input file
     */
    public File getDirectory() {
      return directory;
    }

   /**
    * Sets the input file
    * 
    * @precondition inputFile != null
    * @postcondition this.inputFile = inputFile
    * 
    * @param directory
    */
    public void setDirectory(File directory) {
      this.directory = Objects.requireNonNull(directory);
    }

}
