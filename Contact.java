package sample;

import javafx.beans.property.*;

public class Contact {

    private SimpleStringProperty num;
    private SimpleStringProperty name;

    Contact() {

    }

    Contact(String num, String name) {
        this.num = new SimpleStringProperty(num);
        this.name = new SimpleStringProperty(name);
    }

    public String getNum() {
        return num.get();
    }

    public String getName() {
        return name.get();
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
