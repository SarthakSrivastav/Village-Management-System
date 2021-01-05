package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllRegisteredVillagesController extends Main{
    @FXML
    private TableView<Featch_AllRegisteredVillages> allRegisteredVillages;
    @FXML
    private TableColumn<Featch_AllRegisteredVillages,String>  c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    @FXML
    ObservableList<Featch_AllRegisteredVillages> oblist= FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        CommonProcedureForAll common=new CommonProcedureForAll();
        try{
            ResultSet rsvillages=st.executeQuery("select * from allvillages;");
            while (rsvillages.next()){
                oblist.add(new Featch_AllRegisteredVillages(rsvillages.getString(1), rsvillages.getString(2),
                        rsvillages.getString(3),rsvillages.getString(4),rsvillages.getString(5),
                        rsvillages.getString(6),rsvillages.getString(7),rsvillages.getString(8),
                        rsvillages.getString(9),rsvillages.getString(10)));
            }
        }catch (SQLException e){
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("Village"));
        c2.setCellValueFactory(new PropertyValueFactory<>("Post"));
        c3.setCellValueFactory(new PropertyValueFactory<>("Block"));
        c4.setCellValueFactory(new PropertyValueFactory<>("Distric"));
        c5.setCellValueFactory(new PropertyValueFactory<>("State"));
        c6.setCellValueFactory(new PropertyValueFactory<>("Country"));
        c7.setCellValueFactory(new PropertyValueFactory<>("Gram_Pradhan"));
        c8.setCellValueFactory(new PropertyValueFactory<>("Pincode"));
        c9.setCellValueFactory(new PropertyValueFactory<>("date"));
        c10.setCellValueFactory(new PropertyValueFactory<>("time"));
        allRegisteredVillages.setItems(oblist);
        allRegisteredVillages.setVisible(true);
    }

}