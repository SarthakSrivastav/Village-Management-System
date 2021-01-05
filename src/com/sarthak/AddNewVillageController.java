package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddNewVillageController extends Main {
    @FXML
    private TextField country, state, district, block, post, village, pradhan, pincode, oid, opass;
    @FXML
    private Button submit;
    @FXML
    public void initialize() {
        oid.setPromptText("owner's id");
        opass.setPromptText("owner's_password");
        country.setPromptText("country_in lower_case");
        state.setPromptText("state_in_lower_case");
        district.setPromptText("district_in_lower_case");
        block.setPromptText("block_in_lower_case");
        post.setPromptText("post_in_lower_case");
        village.setPromptText("village_in_lower_case");
        pradhan.setPromptText("gram_pradhan_in_lower_case");
        pincode.setPromptText("pin code_in_lower_case");
    }

    @FXML
    public void handleAddNewVillage() {
        String countryName = country.getText();
        String stateName = state.getText();
        String districtName = district.getText();
        String blockName = block.getText();
        String postName = post.getText();
        String villageName = village.getText();
        String pradhanName = pradhan.getText();
        String pinName = pincode.getText();

        if (countryName.isEmpty() || stateName.isEmpty() || districtName.isEmpty() || blockName.isEmpty() || postName.isEmpty()
                || villageName.isEmpty() || pradhanName.isEmpty() || pinName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Details");
            alert.setHeaderText("Please Fill All The Details Before Submission...!");
            alert.showAndWait();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            if (common.isLower(countryName) && common.isLower(stateName) && common.isLower(districtName) && common.isLower(blockName) && common.isLower(postName)
                    && common.isLower(villageName) && common.isLower(pradhanName) && common.checkLength(pinName, 6)) {
                String addNewVillage = postName + "_" + villageName;

                try {
                    ResultSet rsOwner = st.executeQuery("select id,password from owner;");
                    rsOwner.next();
                    String ownerid = oid.getText();
                    String ownerPassword = opass.getText();
                    if (ownerid.equals(rsOwner.getString(1)) && ownerPassword.equals(rsOwner.getString(2))) {
                        int confermation = 0;
                        ResultSet rs = st.executeQuery("select Post,Village from allVillages;");
                        String query1 = "insert into AllVillages values('" + countryName + "','" + stateName + "','" + districtName + "','" + blockName + "'," +
                                "'" + postName + "','" + villageName + "','" + pradhanName + "','" + pinName + "',curdate(),curtime());";
                        String query2 = "create table " + addNewVillage + "_head_of_family(name varchar(50),fathar_name varchar(50), mother_name varchar(50),age int(3),dob varchar(50),aadhar_no varchar(12)," +
                                "category varchar(50),gender varchar(6),religion varchar(50),ph_no varchar(10),house_no varchar(20) unique,work_type varchar(50),date date,time time," +
                                "primary key(aadhar_no));";
                        String query3 = "create table " + addNewVillage + "(name varchar(50),father_name varchar(50),mother_name varchar(50),age int(3),dob varchar(50),aadhar_no varchar(12)," +
                                "category varchar(50),gender varchar(20),religion varchar(50),ph_no varchar(10),house_no varchar(20),work_type varchar(50)," +
                                "date date,time time,primary key(aadhar_no));";
                        String query4 = "create table " + addNewVillage + "_responsible_peoples(name varchar(50),phone_no varchar(10),house_no varchar(20),profession varchar(50));";
                        String query5 = "create table " + addNewVillage + "_resources(police_station_name varchar(50),police_contact_no varchar(20),women_helpline_no varchar(20),play_school varchar(50)," +
                                "primary_school varchar(50),high_school varchar(50),inter_college varchar(50),hospital varchar(50),anganwadi varchar(10),water_supply varchar(10)," +
                                "food_supply varchar(10),safe_for_womens_or_not varchar(10),date_of_submission date);";
                        st.addBatch(query1);
                        st.addBatch(query2);
                        st.addBatch(query3);
                        st.addBatch(query4);
                        st.addBatch(query5);

                        if (rs.next() == true) {
                            int exist = 0;
                            do {
                                String dbVillagers = rs.getString(1) + "_" + rs.getString(2);
                                if (addNewVillage.equals(dbVillagers)) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Already Exist");
                                    alert.setHeaderText("This Village Already Exist In Your Database.");
                                    alert.setContentText("Enter Village Name Which Does't Exist...!");
                                    alert.showAndWait();
                                    post.clear();
                                    village.clear();
                                    exist = 1;
                                    break;
                                }
                            } while (rs.next());
                            if (exist == 0) {
                                st.executeBatch();
                                confermation = 1;
                            }
                        } else {
                            st.executeBatch();
                            confermation = 1;
                        }
                        if (confermation == 1) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Operation Successful");
                            alert.setHeaderText("New Village " + villageName + " Added Successfully...!");
                            alert.showAndWait();
                            country.clear();
                            state.clear();
                            district.clear();
                            block.clear();
                            post.clear();
                            village.clear();
                            pradhan.clear();
                            pincode.clear();
                            oid.clear();
                            opass.clear();
                        }
                        st.clearBatch();
                    } else {
                        common.alertMessage("error", "Wrong Owner's Detail", "It Seems Like You Are Not The Owner...!!!");
                        oid.clear();
                        opass.clear();
                    }
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Check Again");
                    alert.setHeaderText("Something Went Wrong Please Check Your Stack Trace..!");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            } else {
                if (common.getCheckCase() == 1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Use Lower Case...");
                    alert.setHeaderText("please fill all details in lower case without space..!");
                    alert.setContentText("use underscore( _ ) for spaces...!");
                    common.setCheckCase(0);
                    alert.showAndWait();
                }
                if (common.getCheckLength() == 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Pin Code");
                    alert.setHeaderText("Pin Code Is Not Correct Please Enter Correct One..!");
                    common.setCheckLength(0);
                    alert.showAndWait();
                    pincode.clear();
                }
            }
        }
    }
    @FXML
    public void enterSubmit(){
        submit.setStyle("-fx-background-color:#d9d9d9");
    }
    @FXML
    public void exitsubmit(){
        submit.setStyle("-fx-background-color:#4b7cb4");
    }
}
