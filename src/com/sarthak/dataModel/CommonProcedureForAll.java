package com.sarthak.dataModel;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CommonProcedureForAll {
    public int checkCase=0;
    public int checkLength =0;

    public int getCheckCase() {
        return checkCase;
    }

    public void setCheckCase(int checkCase) {
        this.checkCase = checkCase;
    }

    public int getCheckLength() {
        return checkLength;
    }

    public void setCheckLength(int checkLength) {
        this.checkLength = checkLength;
    }

    public Optional<ButtonType> alertMessage(String type, String title, String headerText) {
        Optional<ButtonType> result;
        switch (type) {
            case "confirmation": {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(title);
                alert.setHeaderText(headerText);
                result=alert.showAndWait();
                break;
            }
            case "warning": {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(title);
                alert.setHeaderText(headerText);
                result=alert.showAndWait();
                break;
            }
            case "error": {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setHeaderText(headerText);
                result=alert.showAndWait();
                break;
            }
            case "information": {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(headerText);
                result=alert.showAndWait();
                break;
            }
            case "none": {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle(title);
                alert.setHeaderText(headerText);
                result=alert.showAndWait();
                break;
            }
            default:
                result=null;
        }
        return result;
    }
    public boolean isLower(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i]!='_'){
                if (!Character.isLowerCase(charArray[i])) {
                    setCheckCase(1);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkLength(String pin,int requiredSize) {
        int len = pin.length();
        if (len == requiredSize)
            return true;
        else {
            setCheckLength(1);
            return false;
        }
    }
}