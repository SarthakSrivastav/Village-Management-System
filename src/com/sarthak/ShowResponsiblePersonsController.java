package com.sarthak;
import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowResponsiblePersonsController extends Main{
    @FXML
    private TableView<Featch_ResponsiblePersonsDetails> responsiblePeople;
    @FXML
    private TableColumn<Featch_ResponsiblePersonsDetails,String> c1,c2,c3,c4;
    @FXML
    ObservableList<Featch_ResponsiblePersonsDetails> oblist= FXCollections.observableArrayList();
    @FXML
    private Button submit;
    @FXML
    private TextField post,villa;
    @FXML
    public void initialize(){
        responsiblePeople.getColumns().forEach(column ->column.setMinWidth(125));
        responsiblePeople.getColumns().forEach(column ->column.setMaxWidth(300));
    }
    @FXML
    public void handleShowResponsiblePersons(){
        oblist.clear();
        String PostName=post.getText();
        String VillageName=villa.getText();
        CommonProcedureForAll common=new CommonProcedureForAll();
        if (PostName.isEmpty()||VillageName.isEmpty()){
            common.alertMessage("warning", "Incomplete Details", "Please Enter Post And Village Name To See The List Of Responsible's Peoples..!");
        }
        else {
            if (common.isLower(PostName)&&common.isLower(VillageName)){
                try {
                    ResultSet rsCheckVillage=st.executeQuery("select Post,Village from allvillages;");
                    int found=0;
                    while (rsCheckVillage.next()){
                        if ((rsCheckVillage.getString(1)+"_"+rsCheckVillage.getString(2)).equals(PostName+"_"+VillageName)){
                            found=1;
                            break;
                        }
                    }
                    if (found==1){
                        ResultSet respo=st.executeQuery("select * from "+PostName+"_"+VillageName+"_responsible_peoples;;");
                        if (respo.next()==true){
                            do {
                                oblist.add(new Featch_ResponsiblePersonsDetails(respo.getString(1),respo.getString(4),
                                        respo.getString(3),respo.getString(2)));
                            }while(respo.next());
                        }
                        else {
                            common.alertMessage("warning", "Empty", "No Data Found..!");
                            return;
                        }
                        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
                        c2.setCellValueFactory(new PropertyValueFactory<>("profession"));
                        c3.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                        c4.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
                        responsiblePeople.setItems(oblist);
                        responsiblePeople.setVisible(true);
                    }
                    else {
                        common.alertMessage("information", "Error", "It Seems Like This Village Is Not Registered..!");
                    }
                }
                catch (SQLException e){
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
    public void enterSubmit(){
        submit.setStyle("-fx-background-color:#cdb498");
    }
    @FXML
    public void exitsubmit(){
        submit.setStyle("-fx-background-color:#564129");
    }
}