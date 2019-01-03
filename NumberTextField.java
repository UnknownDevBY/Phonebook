package sample;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    public NumberTextField() {
        this.setPromptText("Номер телефона");
    }

    @Override
    public void replaceText(int i, int i1, String str) {
        if(str.matches("[0-9]") || str.isEmpty())
            super.replaceText(i, i1, str);
    }
}
