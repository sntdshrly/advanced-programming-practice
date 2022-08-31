package com.example.file_io_prak.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainController {
    @FXML
    private TextArea txtArea;
    private Path txtLoc;

    @FXML
    protected void onActionOpen(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        Path path = Paths.get(file.toURI());
        txtLoc = path;
        try{
            String strCombine = "";
            List<String> lines = Files.readAllLines(path);
            for(String l: lines){
                strCombine += l + "\n";
            }
            txtArea.setText(strCombine);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onActionSaveAs() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(txtArea.getScene().getWindow());
        Path path = Paths.get(file.toURI());

        try{
            Files.write(path,txtArea.getText().getBytes());
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onActionSave(ActionEvent actionEvent) throws IOException {
        if (txtLoc!=null){
            Files.write(txtLoc,txtArea.getText().getBytes());
        }
        else{
            onActionSaveAs();
        }
    }
}