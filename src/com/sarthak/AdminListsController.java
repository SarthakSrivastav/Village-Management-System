package com.sarthak;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminListsController extends Main{
    @FXML
    private TableView<Featch_AdminInformation> adminTable;
    @FXML
    private TableColumn<Featch_AdminInformation,String> c1,c2,c3,c4,c5,c6,c7,c8,c9;
    ObservableList<Featch_AdminInformation> oblist= FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        adminTable.getColumns().forEach(column ->column.setMinWidth(150));
        adminTable.getColumns().forEach(column ->column.setMaxWidth(300));
        try {
            ResultSet rs=st.executeQuery("select * from admininfo;");
            while (rs.next()){
                oblist.add(new Featch_AdminInformation(rs.getString(3),rs.getString(1),rs.getString(2),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(9),rs.getString(10)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("fname"));
        c3.setCellValueFactory(new PropertyValueFactory<>("lname"));
        c4.setCellValueFactory(new PropertyValueFactory<>("age"));
        c5.setCellValueFactory(new PropertyValueFactory<>("phno"));
        c6.setCellValueFactory(new PropertyValueFactory<>("post"));
        c7.setCellValueFactory(new PropertyValueFactory<>("village"));
        c8.setCellValueFactory(new PropertyValueFactory<>("date"));
        c9.setCellValueFactory(new PropertyValueFactory<>("time"));

        adminTable.setItems(oblist);
        adminTable.setVisible(true);

    }
}
