package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AllAboutVillagersController extends Main{
    String PostName,VillageName,HeadName,HouseNo;
    String clickedButton;
    public String getVillage(){
        return PostName+"_"+VillageName;
    }
    @FXML
    private BorderPane allAboutVillagersBorderPane;
    @FXML
    private Button b1,b2,b3,b4,b5;
    @FXML
    private TextField postOffice,village,headName,hno;
    @FXML
    private FlowPane topFlow,centerFlow;
    @FXML
    private TableView<Featch_AllAboutVillagersDetails> allAboutVillageTable;
    @FXML
    private TableColumn<Featch_AllAboutVillagersDetails,String> c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14;
    @FXML
    ObservableList<Featch_AllAboutVillagersDetails> oblist= FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        postOffice.setStyle("-fx-font-family: Inconsolata; -fx-font-size: 13");
        village.setStyle("-fx-font-family: Inconsolata; -fx-font-size: 13");
        headName.setStyle("-fx-font-family: Inconsolata; -fx-font-size: 13");
        hno.setStyle("-fx-font-family: Inconsolata; -fx-font-size: 13");
        allAboutVillageTable.setPadding(new Insets(10));
        allAboutVillageTable.getColumns().forEach(column ->column.setMinWidth(120));
        allAboutVillageTable.getColumns().forEach(column ->column.setMaxWidth(300));
    }
    public void handleAllVillageRecords(){
        clickButton();
        CommonProcedureForAll common=new CommonProcedureForAll();
        if (PostName.isEmpty()||VillageName.isEmpty()){
            common.alertMessage("warning", "Not Found", "Please Enter Postoffice and Village Name To See The Records..!");
        }else {
            if (common.isLower(PostName)&&common.isLower(VillageName)){
                try {
                    int villageFound=0;
                    String villageToFind=PostName+"_"+VillageName;
                    ResultSet rsCheckVillage=st.executeQuery(" select Post,Village from allvillages;");
                    while (rsCheckVillage.next()){
                        if (villageToFind.equals(rsCheckVillage.getString(1)+"_"+rsCheckVillage.getString(2))){
                            villageFound=1;
                            break;
                        }
                    }
//                All Are Set.......
                    if (villageFound==1){
                        if (clickedButton.equals(b2.getText())){
                            ResultSet rsGetVillage=st.executeQuery("select * from "+villageToFind+";");
                            bindColumns(rsGetVillage);
                        }
                        else if (clickedButton.equals(b3.getText())){
                            ResultSet rsGetHeads=st.executeQuery("select * from "+villageToFind+"_head_of_family;");
                            bindColumns(rsGetHeads);
                        }
                    }else {
                        common.alertMessage("warning", "Not Match", "This Village("+villageToFind+") Is Not Exist Into Your Record File..!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                    common.alertMessage("warning", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
                }

            }
            else {
                common.setCheckCase(0);
                common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
            }

        }
    }
    @FXML
    public void getAllVillagers(){
        clickedButton =b2.getText();
        handleAllVillageRecords();
        clickedButton =null;
    }
    @FXML
    public void getHead(){
        clickedButton =b3.getText();
        handleAllVillageRecords();
        clickedButton =null;
    }

    @FXML
    public void handleAllHeadOfFamiliesRecords(){
        clickButton();
        CommonProcedureForAll common=new CommonProcedureForAll();
        if (PostName.isEmpty()||VillageName.isEmpty()||HeadName.isEmpty()||HouseNo.isEmpty()){
            common.alertMessage("warning", "Not Found", "Please Fill All The Details Before Moving To Next..!");
        }else {
            if (common.isLower(PostName)&&common.isLower(VillageName)&&common.isLower(HeadName)){
                try {
                    int villageFound=0;
                    int houseFound=0;
                    String villageToFind=PostName+"_"+VillageName;
                    ResultSet rsCheckVillage=st.executeQuery(" select Post,Village from allvillages;");
                    while (rsCheckVillage.next()){
                        if (villageToFind.equals(rsCheckVillage.getString(1)+"_"+rsCheckVillage.getString(2))){
                            villageFound=1;
                            break;
                        }
                    }
                    String headToFind=HeadName+"_"+HouseNo;
                    ResultSet rsCheckHead=st.executeQuery("select name,house_no from "+villageToFind+"_head_of_family;");
                    while (rsCheckHead.next()){
                        if (headToFind.equals(rsCheckHead.getString(1)+"_"+rsCheckHead.getString(2))){
                            houseFound=1;
                            break;
                        }
                    }
//                All Are Set.......
                    if (villageFound==1&&houseFound==1){
                       ResultSet rsFinal=st.executeQuery("select * from "+villageToFind+"_"+headToFind+"_family;");
                       bindColumns(rsFinal);

                    }else {
                        common.alertMessage("warning", "Not Match", "This Member Is Not Registered On This Portal As The Head Of Their Family..!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                    common.alertMessage("warning", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
                }

            }
            else {
                common.setCheckCase(0);
                common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
            }

        }
    }

    @FXML
    public void clickButton(){
        oblist.clear();
        PostName= postOffice.getText();
        VillageName=village.getText();
        HeadName=headName.getText();
        HouseNo=hno.getText();

    }
    public void bindColumns(ResultSet rs){
        try {
            if(rs.next()==true){
                do {
                    oblist.add(new Featch_AllAboutVillagersDetails(rs.getString(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                            rs.getString(12),rs.getString(13),rs.getString(14)));
                }while (rs.next());
            }else {
                CommonProcedureForAll common=new CommonProcedureForAll();
                common.alertMessage("warning", "Empty", "Data Not Found..!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("mother_name"));
        c4.setCellValueFactory(new PropertyValueFactory<>("age"));
        c5.setCellValueFactory(new PropertyValueFactory<>("dob"));
        c6.setCellValueFactory(new PropertyValueFactory<>("aadhar_no"));
        c7.setCellValueFactory(new PropertyValueFactory<>("category"));
        c8.setCellValueFactory(new PropertyValueFactory<>("gender"));
        c9.setCellValueFactory(new PropertyValueFactory<>("religion"));
        c10.setCellValueFactory(new PropertyValueFactory<>("ph_no"));
        c11.setCellValueFactory(new PropertyValueFactory<>("house_no"));
        c12.setCellValueFactory(new PropertyValueFactory<>("work_type"));
        c13.setCellValueFactory(new PropertyValueFactory<>("date"));
        c14.setCellValueFactory(new PropertyValueFactory<>("time"));

        allAboutVillageTable.setItems(oblist);
        allAboutVillageTable.setVisible(true);
    }
    @FXML
    public void handleShowAdminList(){
        System.out.println("In Handle Show Admin List");
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(allAboutVillagersBorderPane.getScene().getWindow());
        dialog.setTitle("Admin's List");
        dialog.setHeaderText("Details Of All The Admin's:");
        dialog.getDialogPane().setMinSize(1000,500);
        CommonProcedureForAll common=new CommonProcedureForAll();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("AdminListsFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        }
        catch (IOException e){
            common.alertMessage("warning", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }
    @FXML
    public void handleShowResponsiblePersons(){
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(allAboutVillagersBorderPane.getScene().getWindow());
        dialog.setTitle("Responsible Person's");
        dialog.getDialogPane().setMinSize(1050,500);
        dialog.setResizable(true);
        CommonProcedureForAll common=new CommonProcedureForAll();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("showResponsiblePersonsFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        }
        catch (IOException e){
            common.alertMessage("warning", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }
}

