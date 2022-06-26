package com.example.qbs_zadanie;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;

import java.io.File;

public class MainUIController {

    @FXML
    private Button folderChooseBtn;
    @FXML
    private Label chosenFilePathLabel;
    @FXML
    private TextField fileExtensionTxtField;
    @FXML
    private TextArea stringToBeReplaced;
    @FXML
    private TextArea stringToReplaceWith;
    @FXML
    private TextArea outputTxt;
    @FXML
    private Button executeBtn;

    private File selectedDirectory;

    @FXML
    public void initialize(){
        folderChooseBtn.setOnMouseClicked(mouseEvent -> this.onFolderChooseBtnClicked());
        executeBtn.setOnMouseClicked(mouseEvent -> this.onExecuteBtnClicked());
    }

    private void onFolderChooseBtnClicked(){
        selectedDirectory = new DirectoryChooser().showDialog(folderChooseBtn.getScene().getWindow());
        chosenFilePathLabel.setText("Wybrana ścieżka: " + selectedDirectory.getAbsolutePath());
    }

    private void onExecuteBtnClicked(){
        outputTxt.setText("");
        if(fileExtensionTxtField.getText().contains(" "))
            displayErrorMsg("Proszę wpisać tylko jedno rozszerzenie pliku, bez spacji");
        new StringReplacer().replaceStrings(selectedDirectory, fileExtensionTxtField.getText(), stringToBeReplaced.getText(), stringToReplaceWith.getText());
    }

    private void displayErrorMsg(String msg){

    }


}