package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUpdater {

    public void updateScene(String fxmlFile, String title, ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setTitle(title);
            window.setScene(scene);
            window.show();
        }
        catch (IOException ex) {
            System.out.println("IOException Handled");
        }
    }
}
