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
import java.util.Map;

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
    private TextArea outputTxtArea;
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
        outputTxtArea.setText("");
        String outputText;
        if(selectedDirectory == null) {
            outputText = "Proszę najpierw wybrać folder";
            outputTxtArea.setText(outputText);
            return;
        }
        if(stringToBeReplaced.getText() == "" || stringToReplaceWith.getText() == ""){
            outputText = "Proszę najpierw wpisać ciągi znaków, jakie mają być zamienione";
            outputTxtArea.setText(outputText);
            return;
        }
        ReplacedRef replacedInfo = new StringReplacer().replaceStrings(selectedDirectory, fileExtensionTxtField.getText().stripTrailing(), stringToBeReplaced.getText(), stringToReplaceWith.getText());
        outputText = "Dokonano podmian w następujących (" + replacedInfo.replacedFilesWithCount.keySet().size() + ") plikach w następujących liczbach:\n";
        for(Map.Entry<String, Integer> fileWithCount : replacedInfo.replacedFilesWithCount.entrySet()){
            outputText += fileWithCount.getKey() + " : ";
            outputText += fileWithCount.getValue().intValue() + "\n";
        }
        outputText += "\n Wystąpiły błędy w następujących plikach (" + replacedInfo.filesWithErrors.size() + ") :\n";
        for(String errorFileName : replacedInfo.filesWithErrors){
            outputText += "\n" + errorFileName;
        }
        outputTxtArea.setText(outputText);
    }

    private void displayErrorMsg(String msg){

    }


}