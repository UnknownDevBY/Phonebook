package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML private TableColumn<Contact, String> num;
    @FXML private TableColumn<Contact, String> name;
    @FXML private TableView<Contact> table;
    @FXML private TextField textField;
    private ObservableList<Contact> personData = FXCollections.observableArrayList();
    private ObservableList<Contact> tempList;
    private ResultSet resultSet;
    private SqlConnectionToDb sqlCon;

    public void initialize() throws SQLException {
        sqlCon = SqlConnectionToDb.getInstance();
        table.setEditable(true);
        setTextFieldActions();
        getContacts();
        this.num.setCellValueFactory(new PropertyValueFactory<>("num"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(personData);
    }

    public void addContactPressed(ActionEvent actionEvent) {
        new SceneUpdater().updateScene("addContact.fxml", "Добавить контакт", actionEvent);
    }

    private void setTextFieldActions() {
        textField.setOnAction(e -> {
            tempList = FXCollections.observableArrayList();
            for(int i = 0, j = personData.size(); i < j; ++i)
                if(personData.get(i).getName().toLowerCase().contains(textField.getText().toLowerCase()) || personData.get(i).getNum().toLowerCase().contains(textField.getText().toLowerCase()))
                    tempList.add(personData.get(i));
            table.setItems(tempList);
        });
        textField.setOnKeyTyped(e -> {
            tempList = FXCollections.observableArrayList();
            for(int i = 0, j = personData.size(); i < j; ++i)
                if(personData.get(i).getName().toLowerCase().contains(textField.getText().toLowerCase()) || personData.get(i).getNum().toLowerCase().contains(textField.getText().toLowerCase()))
                    tempList.add(personData.get(i));
            table.setItems(tempList);
        });
    }

    private void getContacts() throws SQLException {
        resultSet = sqlCon.selectFromDatabase("SELECT * FROM " + SqlConnectionToDb.TABLE_NAME + ";");
        String num;
        String info;
        while (resultSet.next()) {
            num = resultSet.getString("name");
            info = resultSet.getString("info");
            personData.add(new Contact(num, info));
        }
    }
}
