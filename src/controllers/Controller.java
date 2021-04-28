package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.PasswordGenerator;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ChoiceBox length;
    public ChoiceBox count;
    public CheckBox rus;
    public CheckBox rusUpper;
    public CheckBox eng;
    public CheckBox engUpper;
    public CheckBox nums;
    public CheckBox symbols;
    public ListView list;
    public TextField passwordToCode;
    public TextField codedPassword;
    public TextField codeToPassword;
    public TextField casualPassword;
    public Random random = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        length.getItems().addAll(15, 20, 25, 30, 35, 40);
        count.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        length.setValue(20);
        count.setValue(1);
    }

    public void generate(ActionEvent actionEvent) {
        if (rus.isSelected() || rusUpper.isSelected() || eng.isSelected() || engUpper.isSelected() || nums.isSelected() || symbols.isSelected()) {
            list.getItems().clear();
            int i = 1;
            while (i <= (int) count.getValue()) {
                PasswordGenerator passwordGenerator = new PasswordGenerator((int) length.getValue(), random);
                if (rus.isSelected())
                    passwordGenerator.filterAddRus();
                if (rusUpper.isSelected())
                    passwordGenerator.filterAddRusUpper();
                if (eng.isSelected())
                    passwordGenerator.filterAddEng();
                if (engUpper.isSelected())
                    passwordGenerator.filterAddEngUpper();
                if (nums.isSelected())
                    passwordGenerator.filterAddNums();
                if (symbols.isSelected())
                    passwordGenerator.filterAddSymbols();
                list.getItems().add(passwordGenerator.toString() + "");
                i++;
            }
        }
    }

    public void code(ActionEvent actionEvent) {
        if (passwordToCode.getText().length() > 0) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(passwordToCode.getText());
            passwordGenerator.filterCode();
            codedPassword.setText(passwordGenerator.toString());
        }
    }

    public void decode(ActionEvent actionEvent) {
        if (codeToPassword.getText().length() > 0) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(codeToPassword.getText());
            passwordGenerator.filterDecode();
            casualPassword.setText(passwordGenerator.toString());
        }
    }
}
