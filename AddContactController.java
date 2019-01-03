package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class AddContactController {
    @FXML private TextField telNumField;
    @FXML private TextField nameField;
    private SqlConnectionToDb sqlCon;

    public void contactAddPressed(ActionEvent actionEvent) throws SQLException {
        sqlCon = SqlConnectionToDb.getInstance();
        if(telNumField.getLength() != 0 && nameField.getLength() != 0)
            sqlCon.insertIntoDatabase(telNumField.getText(), nameField.getText());
        new SceneUpdater().updateScene("sample.fxml", "Журнал контактов", actionEvent);
    }
}
