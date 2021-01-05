package com.sarthak;
import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class HeadOfFamilyController extends Main {
    static String finalPost;
    static String finalVillage;
    @FXML
    private TextField postOffice, village, headName, fname, mname, aadhar, phno, hno;
    @FXML
    private Spinner age;
    @FXML
    private ComboBox category, gender, religion,wt;
    @FXML
    private DatePicker dob;
    @FXML
    private CheckBox responsible;
    @FXML
    private Button submit;
    @FXML
    public void getPrePostAndVillage(String post,String village){
        finalPost=post;
        finalVillage=village;
    }
    @FXML
    public void initialize() {
        postOffice.clear();
        village.clear();
        postOffice.setEditable(true);
        village.setEditable(true);
        postOffice.setPromptText("post_in_lower_case");
        village.setPromptText("village_in_lower_case");
        headName.setPromptText("head_name_in_lower_case");
        fname.setPromptText("father_name_in_lower_case");
        mname.setPromptText("mother_name_in_lower_case");
        age.setPromptText("age");
        aadhar.setPromptText("12_digits");
        phno.setPromptText("10_digits");
        hno.setPromptText("house_no");
        wt.setPromptText("profession");
        dob.setPromptText("Date Of Birth");
        if (finalVillage!=null&&finalPost!=null){
            postOffice.setText(finalPost);
            village.setText(finalVillage);
            postOffice.setEditable(false);
            village.setEditable(false);
        }
    }

    @FXML
    public void handleHeadOfTheFamily() {
        CommonProcedureForAll common = new CommonProcedureForAll();
        String postName = postOffice.getText();
        String villageName = village.getText();
        String headOfFamily = headName.getText();
        String fatherName = fname.getText();
        String motherName = mname.getText();
        int hAge = (int) age.getValue();
        String aadharNo = aadhar.getText();
        String houseNo = hno.getText();
        String worktype = (String) wt.getValue();
        String datePickerDate=dob.getEditor().getText();
        String categoryName = (String) category.getValue();
        String genderName = (String) gender.getValue();
        String religionName = (String) religion.getValue();
        String phoneNo = phno.getText();

        if (postName.isEmpty() || villageName.isEmpty() || headOfFamily.isEmpty() || fatherName.isEmpty() || motherName.isEmpty()
                || aadharNo.isEmpty() || houseNo.isEmpty() || worktype.isEmpty() || categoryName.isEmpty() || genderName.isEmpty()
                || religionName.isEmpty() || phoneNo.isEmpty()) {
            common.alertMessage("warning", "Empty Details", "Please Fill All The Details Before Moving To Next...!");
        } else {
            if (common.isLower(postName) && common.isLower(villageName) && common.isLower(headOfFamily) && common.isLower(motherName)
                    && common.checkLength(aadharNo, 12) && common.isLower(worktype) && common.isLower(categoryName) && common.isLower(religionName)
                    && common.checkLength(phoneNo, 10)) {
                try {
                    ResultSet rs = st.executeQuery("select Post,Village from allvillages;");
                    ArrayList<String> listOfExistingVillages = new ArrayList<>();
                    while (rs.next()) {
                        listOfExistingVillages.add(rs.getString(1) + "_" + rs.getString(2));
                    }
                    String villageFound = postName + "_" + villageName;
                    if (listOfExistingVillages.contains(villageFound)) {
                        String query1 = "insert into " + villageFound + " values('" + headOfFamily + "','" + fatherName + "','" + motherName + "'," + hAge + ",'"+datePickerDate+"','" + aadharNo + "'" +
                                ",'" + categoryName + "','" + genderName + "','" + religionName + "','" + phoneNo + "','" + houseNo + "','" + worktype + "',curdate(),curtime());";
                        String query2 = "insert into " + villageFound + "_head_of_family values('" + headOfFamily + "','" + fatherName + "','" + motherName + "'," + hAge + ",'"+datePickerDate+"','" + aadharNo + "'" +
                                ",'" + categoryName + "','" + genderName + "','" + religionName + "','" + phoneNo + "','" + houseNo + "','" + worktype + "',curdate(),curtime());";
                        String query3=" create table "+villageFound+"_"+headOfFamily+"_"+houseNo+"_family(m_name varchar(50),m_fname varchar(50),m_mname varchar(50),m_age int(3),dob varchar(20),m_aadhar_no" +
                                " varchar(12),m_category varchar(50),m_gender varchar(6),m_religion varchar(50),m_ph_no varchar(10),m_house_no varchar(20),m_work_type" +
                                " varchar(50),date date, time time, primary key(m_aadhar_no));";
                        String query4 = "insert into "+villageFound+"_"+headOfFamily+"_"+houseNo+"_family values('" + headOfFamily + "','" + fatherName + "','" + motherName + "'," + hAge + ",'"+datePickerDate+"','" + aadharNo + "'" +
                                ",'" + categoryName + "','" + genderName + "','" + religionName + "','" + phoneNo + "','" + houseNo + "','" + worktype + "',curdate(),curtime());";

                        String query5="insert into "+villageFound+"_responsible_peoples values('"+headOfFamily+"','"+phoneNo+"','"+houseNo+"','"+worktype+"');";

                        st.addBatch(query1);
                        st.addBatch(query2);
                        st.addBatch(query3);
                        st.addBatch(query4);
                        ResultSet rsHeadAadhar = st.executeQuery("select aadhar_no,house_no from " + villageFound +"_head_of_family;");
                        ArrayList listOfAadharNo = new ArrayList<>();
                        ArrayList listOfHouseNo = new ArrayList<>();
                        int successful=0;
                        if (rsHeadAadhar.next() == true) {
                            do {
                                listOfAadharNo.add(rsHeadAadhar.getString(1));
                                listOfHouseNo.add(rsHeadAadhar.getString(2));
                            } while (rsHeadAadhar.next());

                            if (!listOfAadharNo.contains(aadharNo) && !listOfHouseNo.contains(houseNo)) {
                                Optional<ButtonType> result = common.alertMessage("confirmation", "Give Confirmation", "Are You Sure '" + headOfFamily + "' Is The Head Of His Family..?");
                                if (result.get() == ButtonType.OK) {
                                    if (responsible.isSelected()){
                                        st.addBatch(query5);
                                    }
                                    st.executeBatch();
                                    successful=1;
                                    common.alertMessage("information", "Operation Successful", "Data Saved Into Your Record Successfully..!");
                                }
                            } else {
                                common.alertMessage("error", "Duplicate Entry", "Member Of This Aadhar Id And House No Already Registered!");
                                aadhar.clear();
                                hno.clear();
                            }
                            listOfAadharNo.clear();
                        } else {
                            Optional<ButtonType> result = common.alertMessage("confirmation", "Give Confirmation", "Are You Sure '" + headOfFamily + "' Is The Head Of His Family..?");
                            if (result.get() == ButtonType.OK) {
                                if (responsible.isSelected()){
                                    st.addBatch(query5);
                                }
                                st.executeBatch();
                                successful=1;
                                common.alertMessage("information", "Operation Successful", "Data Saved Into Your Record File Successfully..!");
                            }
                        }
                        if (successful==1){
                            headName.clear();
                            fname.clear();
                            mname.clear();
                            aadhar.clear();
                            phno.clear();
                            hno.clear();
                            dob.setValue(null);
                            responsible.setSelected(false);
                        }
                        st.clearBatch();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Not Found");
                        alert.setHeaderText("It Seems Like This Village Is Not Registered..!");
                        alert.setContentText("Go To (Register-> New Village) To Register This Village..!");
                        alert.showAndWait();
                    }
                    listOfExistingVillages.clear();
                } catch (SQLException e) {
                    common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
                    e.printStackTrace();
                }
            } else {
                if (common.getCheckCase() == 1) {
                    common.alertMessage("warning", "Use Lower Case...", "please fill all details in lower case without space..!");
                    common.setCheckCase(0);
                }
                if (common.getCheckLength() == 1) {
                    common.alertMessage("error", "Invalid", "Please Check Your Aadhar No and Phone No Once Again..!");
                    aadhar.clear();
                    phno.clear();
                    common.setCheckLength(0);
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
