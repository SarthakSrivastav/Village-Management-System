package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
public class NewAdminController extends Main {
    @FXML
    private TextField oid, fname, lname, pno, aadhar, post, village;
    @FXML
    private Spinner<Integer> age;
    @FXML
    private PasswordField opass, pass, confirmpass;
    @FXML
    private Button submit;

    @FXML
    public void initialize() {
        oid.setPromptText("owner's_id");
        opass.setPromptText("owner's_password");
        fname.setPromptText("first_name");
        lname.setPromptText("last_name");
        pno.setPromptText("10-digits");
        aadhar.setPromptText("12-digits");
        post.setPromptText("post_office_name");
        village.setPromptText("village_name");
        pass.setPromptText("new_password");
        confirmpass.setPromptText("confirm_password");
    }

    @FXML
    public void handleNewAdminRecord() {
        try {
            String ownerid = oid.getText();
            String ownerPassword = opass.getText();
            String afname = fname.getText();
            String alname = lname.getText();
            String aid = aadhar.getText();
            int aage = age.getValue();
            String aphno = pno.getText();
            String postName = post.getText();
            String villageName = village.getText();
            String password = pass.getText();
            String confirmPassword = confirmpass.getText();
            //Checking All TextFields Fill Or Not.....
            CommonProcedureForAll common = new CommonProcedureForAll();
            if (ownerid.isEmpty() || ownerPassword.isEmpty() || afname.isEmpty() || alname.isEmpty() || aage == 0 || aphno.isEmpty() || postName.isEmpty()
                    || villageName.isEmpty() || aid.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                common.alertMessage("warning", "Empty Details", "Please Fill All The Details Before Moving To Next..!");
            } else {
//                If All TextFields Are Filled Then Check For Owner's Details.....
                if (common.isLower(afname) && common.isLower(alname) && common.isLower(postName) && common.checkLength(aphno, 10) &&
                        common.isLower(villageName) && common.checkLength(aid, 12)) {
                    ResultSet rsForVillageRegisteredOrNot = st.executeQuery("select Post,Village from allvillages;");
                    int villageRegistered = 0;
                    while (rsForVillageRegisteredOrNot.next()) {
                        if ((rsForVillageRegisteredOrNot.getString(1) + "_" + rsForVillageRegisteredOrNot.getString(2)).equals(postName + "_" + villageName)) {
                            villageRegistered = 1;
                            break;
                        }
                    }
                    if (villageRegistered == 1) {
                        ResultSet rs = st.executeQuery("select id,password from owner;");
                        rs.next();
                        if (ownerid.equals(rs.getString(1)) && ownerPassword.equals(rs.getString(2))) {
                            ResultSet rsCheckAdminInfo = st.executeQuery("select aadhar_no,post_name,village_name from admininfo;");
                            if (rsCheckAdminInfo.next() == true) {
                                ArrayList adminID_List = new ArrayList();
                                ArrayList adminPostVillage_list = new ArrayList();
                                do {
                                    adminID_List.add(rsCheckAdminInfo.getString(1));
                                    adminPostVillage_list.add(rsCheckAdminInfo.getString(2) + "_" + rsCheckAdminInfo.getString(3));
                                } while (rsCheckAdminInfo.next());
                                if (!adminID_List.contains(aid)) {
                                    if (!adminPostVillage_list.contains((postName + "_" + villageName))) {
                                        if (password.equals(confirmPassword)) {
                                            Optional<ButtonType> result = common.alertMessage("confirmation", "Confirm", "Are You Sure To Register '" + afname + "' As The Admin Of '" + postName + " " + villageName + "' Village..?");
                                            if (result.get() == ButtonType.OK) {
                                                st.execute("insert into admininfo values('" + afname + "','" + alname + "','" + aid + "'," + aage + ",'" + aphno + "','" + postName + "','" + villageName + "'," +
                                                        "'" + password + "',curdate(),curtime());");
                                                common.alertMessage("information", "Registration Successful", "Now '" + afname + "' " + alname + " Is The Admin Of '" + villageName + "'..!");
                                                allClear();
                                            }
                                        } else {
                                            common.alertMessage("error", "Miss Match Error", "Password Didn't Match... Please Confirm Your Password");
                                            pass.clear();
                                            confirmpass.clear();
                                        }
                                    } else {
                                        common.alertMessage("information", "Rejected", "There Is Already One Registered Admin From This Village..!");
                                        post.clear();
                                        village.clear();
                                    }
                                } else {
                                    common.alertMessage("warning", "Already Registered", "Admin From This Aadhar Number Already Registerd..!");
                                    aadhar.clear();
                                }
                                adminID_List.clear();
                                adminPostVillage_list.clear();
                            } else {
                                if (password.equals(confirmPassword)) {
                                    Optional<ButtonType> result = common.alertMessage("confirmation", "Confirm", "Are You Sure To Register '" + afname + "' As The Admin Of '" + postName + " " + villageName + "' Village..?");
                                    if (result.get() == ButtonType.OK) {
                                        st.execute("insert into admininfo values('" + afname + "','" + alname + "','" + aid + "'," + aage + ",'" + aphno + "','" + postName + "','" + villageName + "'," +
                                                "'" + password + "',curdate(),curtime());");
                                        common.alertMessage("information", "Registration Successful", "Now '" + afname + "' " + alname + " Is The Admin Of '" + villageName + "'..!");
                                       allClear();
                                    }
                                } else {
                                    common.alertMessage("error", "Miss Match Error", "Password Didn't Match... Please Confirm Your Password");
                                    pass.clear();
                                    confirmpass.clear();
                                }
                            }
                        } else {
                            common.alertMessage("error", "Wrong Owner's Detail", "It Seems Like You Are Not The Owner...!!!");
                            oid.clear();
                            opass.clear();
                        }
                    } else {
                        common.alertMessage("information", "Village Not Found", "It Seems Like This Village Is Not Registered Yet... Go To (Register-> New Village) And Register It First..!");
                    }
                } else {
                    if (common.getCheckLength() == 1) {
                        common.alertMessage("error", "Invalid", "Please Check Your Aadhar Number and Phone Number Once Again..!");
                        aadhar.clear();
                        pno.clear();
                        common.setCheckLength(0);
                    }
                    if (common.getCheckCase() == 1) {
                        common.alertMessage("error", "Invalid", "Please Fill All Details In Lower Case & Without Space And Use Unserscore('_') For Spaces..!");
                        common.setCheckCase(0);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void allClear(){
        oid.clear();
        opass.clear();
        fname.clear();
        lname.clear();
        aadhar.clear();
        pno.clear();
        post.clear();
        village.clear();
        pass.clear();
        confirmpass.clear();
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
