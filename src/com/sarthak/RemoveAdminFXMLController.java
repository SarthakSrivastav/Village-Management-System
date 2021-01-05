package com.sarthak;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class RemoveAdminFXMLController extends Main{
    @FXML
    private TextField oidToRemove,idToRemove;
    @FXML
    private PasswordField opassToRemove,passToRemove;
    @FXML
    private Button remove;
    @FXML
    public void initialize(){
        oidToRemove.setPromptText("Owner's ID");
        opassToRemove.setPromptText("Owner's Password");
        idToRemove.setPromptText("Admin's ID");
        passToRemove.setPromptText("Admin's Password");
    }
    @FXML
    public void handleRemoveAdmin(){
        String ownerId=oidToRemove.getText();
        String ownerPass=opassToRemove.getText();
        String adminId=idToRemove.getText();
        String adminPass=passToRemove.getText();
        try{
            if (ownerId.isEmpty()||ownerPass.isEmpty()||adminId.isEmpty()||adminPass.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Details");
                alert.setHeaderText("Please Fill All The Details Before Removal..!");
                alert.showAndWait();
            }else {
                ResultSet rsOwner=st.executeQuery("select id,password from owner;");
                rsOwner.next();
                if (rsOwner.getString(1).equals(ownerId) && rsOwner.getString(2).equals(ownerPass)){
                    ResultSet rsAdmin=st.executeQuery("select aadhar_no from admininfo;");
                    ArrayList<String> adminInfo=new ArrayList<>();
                    while (rsAdmin.next()){
                        adminInfo.add(rsAdmin.getString(1));
                    }
                    if (adminInfo.contains(adminId)){
                        ResultSet getPassword=st.executeQuery("select password from admininfo where aadhar_no='"+adminId+"';");
                        getPassword.next();
                        if (getPassword.getString(1).equals(adminPass)){
                            st.execute("delete from admininfo where aadhar_no = '"+adminId+"';");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Admin Removed");
                            alert.setHeaderText("Admin Whose ID is "+adminId+" Removed Successfully From Owner's Permission..!");
                            alert.showAndWait();
                            oidToRemove.clear();
                            opassToRemove.clear();
                            idToRemove.clear();
                            passToRemove.clear();
                        }
                        else {
                            Alert alert=new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Wrong Password");
                            alert.setHeaderText("Incorrect Password Please Try Again..!");
                            alert.showAndWait();
                            passToRemove.clear();
                        }
                    }
                    else {
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Not Exist..!");
                        alert.setHeaderText("Id "+adminId+" Not Exist..!");
                        alert.showAndWait();
                        idToRemove.clear();
                    }
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong Owner Details");
                    alert.setHeaderText("It Seems Like You Are Not The Owner...!!!");
                    alert.showAndWait();
                    oidToRemove.clear();
                    opassToRemove.clear();
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    @FXML
    public void enterSubmit(){
        remove.setStyle("-fx-background-color:#ff3333");
    }
    @FXML
    public void exitsubmit(){
        remove.setStyle("-fx-background-color:#ff6666");
    }
}
