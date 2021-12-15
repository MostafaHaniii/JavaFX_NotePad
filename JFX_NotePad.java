/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package d7l1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Mostafa
 */
public class D7L1 extends Application {

    MenuBar bar = new MenuBar();
    TextArea TA = new TextArea();
    MenuItem Open = new MenuItem("Open..");
    MenuItem Save = new MenuItem("Save");

    @Override
    public void init() {
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");

        MenuItem New = new MenuItem("New");
        New.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));

        Save.setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
        MenuItem Exit = new MenuItem("Exit");

        MenuItem Undo = new MenuItem("Undo");
        MenuItem Cut = new MenuItem("Cut");
        MenuItem Copy = new MenuItem("Copy");
        MenuItem Paste = new MenuItem("Paste");
        MenuItem Delete = new MenuItem("Delete");
        MenuItem SelectAll = new MenuItem("Select All");

        MenuItem About = new MenuItem("About Notepad");
        MenuItem JCompile = new MenuItem("Java Compile");

        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        SeparatorMenuItem sep3 = new SeparatorMenuItem();

        file.getItems().addAll(New, Open, Save, Exit);
        file.getItems().add(3, sep1);
        edit.getItems().addAll(Undo, Cut, Copy, Paste, Delete, SelectAll);
        edit.getItems().add(1, sep2);
        edit.getItems().add(6, sep3);
        help.getItems().addAll(About, JCompile);

        bar.getMenus().addAll(file, edit, help);
        TA.setPrefSize(500, 500);
        TA.setPrefColumnCount(50);

        Exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Exit");
                alert.setHeaderText("Confirmation");
                alert.setContentText("Do you want to exit JFX_NotePad");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                    System.out.println("Exit");
                } else {
                    //Nothing
                }
            }
        });
        SelectAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("1");
                TA.selectAll();
            }
        });

        Delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Delete");
                TA.deleteText(TA.getSelection());
            }
        });
        Copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("1");
                TA.copy();
            }
        });

        Paste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("1");
                TA.paste();
            }
        });

        Cut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("1");
                TA.cut();
            }
        });

        Undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("1");
                TA.undo();
            }
        });

        About.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("About Dialog");
                //alert.setHeaderText("Information Dialog");
                alert.setContentText("Mostafa Hany Imam ES Intake 42 ITI");
                alert.showAndWait();
            }
        });

        New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("About Dialog");
                //alert.setHeaderText("Information Dialog");
                alert.setContentText("Do you want to Make A New File");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    TA.clear();
                } else {

                }
            }
        });

        JCompile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Java Compile Dialog");
                //alert.setHeaderText("Information Dialog");
                alert.setContentText("Do you want to Compile the Following File");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    TA.clear();
                } else {

                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(TA);
        Scene scene = new Scene(pane, 500, 500);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        final Button openButton = new Button("Open File");
        final Button openMultipleButton = new Button("Open File");
        Open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Opening a dialog box
                //fileChooser.showOpenDialog(primaryStage);
                File f = fileChooser.showOpenDialog(primaryStage);

                if (f != null) {
                    FileReader fr = null;
                    try (BufferedReader br = new BufferedReader(fr)) {
                        String line = null;
                        do {
                            try {
                                line = br.readLine();
                            } catch (IOException ex) {
                                Logger.getLogger(D7L1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (line != null) {
                                TA.appendText(line);
                                TA.appendText("\n");
                            }
                        } while (line != null);
                    } catch (IOException ex) {
                        Logger.getLogger(D7L1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(D7L1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                   System.out.println("File Not Found!");
                }
            }
        });

        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Save File");
        fileChooser2.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
        ////fileChooser2.getExtensionFilters().add(new ExtensionFilter("Text",".txt"));        
        Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileWriter writer = null;
                File f = fileChooser.showSaveDialog(primaryStage);
                if (f != null) {
                    try {
                        writer = new FileWriter(f);
                        try (BufferedWriter buffer = new BufferedWriter(writer)) {
                            buffer.write(TA.getText());
                        } catch (IOException ex) {
                            Logger.getLogger(D7L1.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                writer.close();
                            } catch (IOException ex) {
                                Logger.getLogger(D7L1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(D7L1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("No File found");
                }
            }
        });

        primaryStage.setTitle("Fx NotePad");

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
