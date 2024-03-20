package org.example.projwithborder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HelloController {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnLock;

    @FXML
    private Button btnOpen;

    FileChooser dialogBox = new FileChooser();

    @FXML
    private Button btnSave;

    @FXML
    private Label lblDisplay;

    @FXML
    private MenuItem mnClose;

    @FXML
    private TextArea txtEditor;

    @FXML
    void getCharacters(KeyEvent event) {
        // DISPLAY CHARACTERS TYPED
        lblDisplay.setText("Characters Typed: " + txtEditor.getText().length());
    }

    @FXML
    void onEdit(ActionEvent event) {
        //
        txtEditor.setEditable(true);
    }

    @FXML
    void onLock(ActionEvent event) {
        // PREVENT THE USER FROM TYPING
        txtEditor.setEditable(false);
    }

    @FXML
    void onOpen(ActionEvent event) throws FileNotFoundException {
        // ADD FILTERS TO SHOW ONLY TEXT FILES
//        dialogBox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

        File selectedFile = dialogBox.showOpenDialog(new Stage());
        // CHECK HAS USER SELECTED A FILE?
        if (selectedFile == null) {
            // DO NOTHING
        } else {
            // CLEAR CONTENT BEFORE OPENING
            txtEditor.setText("");
            Scanner input = new Scanner(selectedFile);
            while(input.hasNextLine()) {
                txtEditor.appendText(input.nextLine() + '\n');
//                txtEditor.setText(input.nextLine() + '\n');
            }
            input.close();
            lblDisplay.setText("Characters Typed: " + txtEditor.getText().length());
        }
    }

    @FXML
    void onSave(ActionEvent event) throws FileNotFoundException {
        // ADD FILTERS TO SHOW ONLY TEXT FILES
//        dialogBox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

        File selectedFile = dialogBox.showSaveDialog(new Stage());
        if(selectedFile==null) {
            // DO NOTHING
        } else{
            PrintWriter output = new PrintWriter(selectedFile);
            output.println(txtEditor.getText());
            output.close();

            //  CLEAR CONTENT AFTER SAVING
            txtEditor.setText("");
            lblDisplay.setText("Characters Typed: " + txtEditor.getText().length());
        }
    }

    @FXML
    void onExit(ActionEvent event) {
        // CLOSE APPLICATION
        Platform.exit();
    }

}
