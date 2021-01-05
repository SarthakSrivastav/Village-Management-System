package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
public class showResourcesController extends Main{
    @FXML
    private Button submit;
    @FXML
    private TableView<Featch_Resources> resourceTable;
    @FXML
    private TableColumn<Featch_Resources,String> c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13;
    @FXML
    ObservableList<Featch_Resources> oblist= FXCollections.observableArrayList();
    @FXML
    private TextField post,villa;
    @FXML
    public void initialize(){
        resourceTable.getColumns().forEach(column ->column.setMinWidth(150));
        resourceTable.getColumns().forEach(column ->column.setMaxWidth(300));
    }
    @FXML
    public void showResources(){
        String Post=post.getText();
        String Village=villa.getText();
        CommonProcedureForAll common=new CommonProcedureForAll();
        if (Post.isEmpty()||Village.isEmpty()){
            common.alertMessage("information","Empty Fields","Please Enter Both Post And Village Name To See The Resources..!");
        }
        else {
            if (common.isLower(Post)&&common.isLower(Village)){
                try {
                    ResultSet rsCheckVillage=st.executeQuery("select Post,Village from allvillages;");
                    int villageFound=0;
                    while (rsCheckVillage.next()){
                        if ((rsCheckVillage.getString(1)+"_"+rsCheckVillage.getString(2)).equals(Post+"_"+Village)){
                            villageFound=1;
                            break;
                        }
                    }
                    if (villageFound==1){
                        ResultSet resultSet=st.executeQuery("select * from "+ Post +"_"+Village+"_resources;");
                        bindColumns(resultSet);
                    }
                    else {
                        common.alertMessage("information","Not Found","It Seems Like This Village Is Not Registered On This Portal...!");
                        villa.clear();
                        post.clear();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                    common.alertMessage("error", "Error","Something Went Wrong Please Check Your Stack Trace..!");

                }
            }
            else {
                common.alertMessage("warning","Use Lower Case","please fill all the detalis in lower case without space use '_' for spaces..!");
                common.setCheckCase(0);
            }
        }
    }
    @FXML
    public void bindColumns(ResultSet rs){
        oblist.clear();
        try {
            if(rs.next()==true){
                do {
                    oblist.add(new Featch_Resources(rs.getString(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                            rs.getString(12),rs.getString(13)));
                }while (rs.next());
            }else {
                CommonProcedureForAll common=new CommonProcedureForAll();
                common.alertMessage("warning", "Empty", "Data Not Found..!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("police_station_name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("police_contact_no"));
        c3.setCellValueFactory(new PropertyValueFactory<>("women_helpline_no"));
        c4.setCellValueFactory(new PropertyValueFactory<>("play_school"));
        c5.setCellValueFactory(new PropertyValueFactory<>("primary_school"));
        c6.setCellValueFactory(new PropertyValueFactory<>("high_school"));
        c7.setCellValueFactory(new PropertyValueFactory<>("inter_college"));
        c8.setCellValueFactory(new PropertyValueFactory<>("hospital"));
        c9.setCellValueFactory(new PropertyValueFactory<>("anganwadi"));
        c10.setCellValueFactory(new PropertyValueFactory<>("water_supply"));
        c11.setCellValueFactory(new PropertyValueFactory<>("food_supply"));
        c12.setCellValueFactory(new PropertyValueFactory<>("safe_for_womens_or_not"));
        c13.setCellValueFactory(new PropertyValueFactory<>("date_of_submission"));
        resourceTable.setItems(oblist);
        resourceTable.setVisible(true);
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
