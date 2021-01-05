package com.sarthak;

import com.mysql.cj.protocol.Resultset;
import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class familyMembersController extends Main{
    static String finamepostName;
    static String finalvillageName;
    @FXML
    private Button submit;
    @FXML
    public void getFamilyMemberPostAndVillage(String post,String village){
        finamepostName=post;
        finalvillageName=village;
    }
    @FXML
    private TextField postOffice,village,headName,hno,name,fname,mname,aadhar,phno;
    @FXML
    private Spinner age;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox gender,wt;
    @FXML
    private CheckBox responsible;

    @FXML
    public void initialize() {
        postOffice.clear();
        village.clear();
        postOffice.setEditable(true);
        village.setEditable(true);
        postOffice.setPromptText("post_in_lower_case");
        village.setPromptText("village_in_lower_case");
        headName.setPromptText("head_name_in_lower_case");
        hno.setPromptText("house_no");
        name.setPromptText("member_name_in_lower_case");
        fname.setPromptText("father_name_in_lower_case");
        mname.setPromptText("mother_name_in_lower_case");
        age.setPromptText("age");
        aadhar.setPromptText("12_digits");
        phno.setPromptText("10_digits");
        wt.setPromptText("profession");
        dob.setPromptText("Date Of Birth");
        if (finamepostName!=null&&finalvillageName!=null){
            postOffice.setText(finamepostName);
            village.setText(finalvillageName);
            postOffice.setEditable(false);
            village.setEditable(false);
        }
    }
    @FXML
    public void handleFamilyMembers(){
        String postName=postOffice.getText();
        String villageName=village.getText();
        String familyHead=headName.getText();
        String houseNo=hno.getText();
        String memberName=name.getText();
        String fatherName=fname.getText();
        String motherName=mname.getText();
        int memberAge= (int) age.getValue();
        String dateOfBirth=dob.getEditor().getText();
        String Gender= (String) gender.getValue();
        String aadharNo=aadhar.getText();
        String phoneNo=phno.getText();
        String profession=(String) wt.getValue();

        CommonProcedureForAll common=new CommonProcedureForAll();
        if (postName.isEmpty()||villageName.isEmpty()||familyHead.isEmpty()||houseNo.isEmpty()||memberName.isEmpty()||fatherName.isEmpty()||
                motherName.isEmpty()||memberAge==0||dateOfBirth.isEmpty()||aadharNo.isEmpty()||phoneNo.isEmpty()||profession.isEmpty()){
            common.alertMessage("warning", "Empty Details", "Please Fill All The Details Before Moving To Next...!");
        }
        else {
            if (common.isLower(postName)&&common.isLower(villageName)&&common.isLower(familyHead)&&common.isLower(memberName)&&common.isLower(fatherName)&&
                    common.isLower(motherName)&&common.isLower(profession)&&common.checkLength(aadharNo,12)&&common.checkLength(phoneNo,10)){

                String Village_Name=postName+"_"+villageName;
                try {
                    ResultSet rsVillages = st.executeQuery("select Post,Village from allvillages;");
                    ArrayList<String> varifyVillage = new ArrayList<>();
                    while (rsVillages.next()) {
                        varifyVillage.add(rsVillages.getString(1) + "_" + rsVillages.getString(2));
                    }
                    if (varifyVillage.contains(Village_Name)) {
//                        ResultSet rsAadhar=st.executeQuery("select aadhar_no from "+Village_Name+";");
//                        int duplicateAadhar=0;
//                        while (rsAadhar.next()){
//                            if ((rsAadhar.getString(1)).equals(aadharNo)){
//                                duplicateAadhar=1;
//                                break;
//                            }
//                        }
//                        if (duplicateAadhar==0){
//
//                        }
                        String HeadOfFamilyAndHouseNo=familyHead+"_"+houseNo;
                        ResultSet rsHeads=st.executeQuery("select name,house_no from "+Village_Name+"_head_of_family;");
                        ArrayList varifyHeadAndHouse = new ArrayList();
                        while (rsHeads.next()){
                            varifyHeadAndHouse.add(rsHeads.getString(1)+"_"+rsHeads.getString(2));
                        }
                        if (varifyHeadAndHouse.contains(HeadOfFamilyAndHouseNo)){
                            ResultSet rsAadhar=st.executeQuery("select aadhar_no from "+Village_Name+";");
                            int  duplicate=0;
                            while(rsAadhar.next()){
                                if ((rsAadhar.getString(1)).equals(aadharNo)){
                                    duplicate=1;
                                    break;
                                }
                            }
                            int successful=0;
                            if (duplicate==0){
                                ResultSet rsFinal=st.executeQuery("select category,religion from "+Village_Name+"_head_of_family where house_no="+houseNo+";");
                                rsFinal.next();
                                String query1="insert into "+Village_Name+" values('"+memberName+"','"+fatherName+"','"+motherName+"',"+memberAge+",'"+dateOfBirth+"'," +
                                        "'"+aadharNo+"','"+rsFinal.getString(1)+"','"+Gender+"','"+rsFinal.getString(2)+"','"+phoneNo+"'," +
                                        "'"+houseNo+"','"+profession+"',curdate(),curtime());";
                                String query2="insert into "+Village_Name+"_"+HeadOfFamilyAndHouseNo+"_family values('"+memberName+"','"+fatherName+"','"+motherName+"',"+memberAge+",'"+dateOfBirth+"'," +
                                        "'"+aadharNo+"','"+rsFinal.getString(1)+"','"+Gender+"','"+rsFinal.getString(2)+"','"+phoneNo+"'," +
                                        "'"+houseNo+"','"+profession+"',curdate(),curtime());";
                                String query3="insert into "+Village_Name+"_responsible_peoples values('"+memberName+"','"+phoneNo+"','"+houseNo+"','"+profession+"');";

                                st.addBatch(query1);
                                st.addBatch(query2);
                                Optional<ButtonType> result = common.alertMessage("confirmation", "Give Confirmation", "Are You Sure '" +memberName+ "' Is The Family Member Of "+familyHead+"");
                                if (result.get() == ButtonType.OK) {
                                    if (responsible.isSelected()){
                                        st.addBatch(query3);
                                    }
                                    st.executeBatch();
                                    successful=1;
                                    common.alertMessage("information", "New Member Added", "New Family Member Added Successfully..!");
                                }
                                st.clearBatch();
                                if (successful==1){
                                    headName.clear();
                                    hno.clear();
                                    name.clear();
                                    fname.clear();
                                    mname.clear();
                                    aadhar.clear();
                                    phno.clear();
                                    dob.setValue(null);
                                    responsible.setSelected(false);
                                }
                            }
                            else {
                                common.alertMessage("warning", "Duplicate Entry", "Person From This ID Already Exist..!");
                                aadhar.clear();
                            }
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Not Exist");
                            alert.setHeaderText("This Family Is Not Registered..!");
                            alert.setContentText("Goto (Add-> Head Of Family) And Register This Family First..!");
                            alert.showAndWait();
                            headName.clear();
                            hno.clear();
                        }
                        varifyHeadAndHouse.clear();
                    }else {
                        common.alertMessage("warning", "Not Found", "This Village Is Not Registered... Please Register It First..!");
                        village.clear();
                        postOffice.clear();
                    }
                    varifyVillage.clear();
                }catch (SQLException e){
                    common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
                    e.printStackTrace();
                }
            }
            else {
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
