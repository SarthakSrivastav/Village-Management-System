package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchPersonsController extends Main{
    @FXML
    private Button search;
    @FXML
    private TableView<Featch_AllAboutVillagersDetails> showRearchResult;
    @FXML
    private TableColumn<Featch_AllAboutVillagersDetails,String> c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14;
    @FXML
    ObservableList<Featch_AllAboutVillagersDetails> oblist= FXCollections.observableArrayList();

    String namePrompt,fnamePrompt,mnamePrompt,aadharPrompt,categoryPrompt,religionPrompt,pnoPrompt,hnoPrompt,professionPrompt,agePrompt,genderPrompt;
    @FXML
    private TextField post,village,name,fname,mname,aadhar,category,religion,pno,hno,profession,age,gender,head,house;
    @FXML
    private RadioButton r1,r2,r3,rage1,rage2,rage3;
    @FXML
    public void initialize(){
        showRearchResult.getColumns().forEach(column ->column.setMinWidth(120));
        showRearchResult.getColumns().forEach(column ->column.setMaxWidth(300));
    }
    @FXML
    public void nameClicked(){
        namePrompt=name.getPromptText();
        disableRemainingTextFields(namePrompt);
//        namePrompt=null;
    }
    @FXML
    public void fnameClicked(){
        fnamePrompt=fname.getPromptText();
        disableRemainingTextFields(fnamePrompt);
//        fnamePrompt=null;
    }
    @FXML
    public void mnameClicked(){
        mnamePrompt=mname.getPromptText();
        disableRemainingTextFields(mnamePrompt);
//        mnamePrompt=null;
    }
    @FXML
    public void aadharClicked(){
      aadharPrompt=aadhar.getPromptText();
        disableRemainingTextFields(aadharPrompt);
//        aadharPrompt=null;
    }
    @FXML
    public void categoryClicked(){
        categoryPrompt=category.getPromptText();
        disableRemainingTextFields(categoryPrompt);
//        categoryPrompt=null;
    }
    @FXML
    public void religionClicked(){
        religionPrompt=religion.getPromptText();
        disableRemainingTextFields(religionPrompt);
//        religionPrompt=null;
    }
    @FXML
    public void pnoClicked(){
        pnoPrompt=pno.getPromptText();
        disableRemainingTextFields(pnoPrompt);
//        pnoPrompt=null;
    }
    @FXML
    public void hnoClicked(){
        hnoPrompt=hno.getPromptText();
        disableRemainingTextFields(hnoPrompt);
//        hnoPrompt=null;
    }
    @FXML
    public void professionClicked(){
        professionPrompt=profession.getPromptText();
        disableRemainingTextFields(professionPrompt);
//        professionPrompt=null;
    }
    @FXML
    public void ageClicked(){
        agePrompt=age.getPromptText();
        disableRemainingTextFields(agePrompt);
//        agePrompt=null;
    }
    @FXML
    public void genderClicked(){
        genderPrompt=gender.getPromptText();
        disableRemainingTextFields(genderPrompt);
//        genderPrompt=null;
    }
    public void disableRemainingTextFields(String notDisable){
        if (notDisable.equals(name.getPromptText())){
            fname.setDisable(true);
            mname.setDisable(true);
            aadhar.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(fname.getPromptText())){
            name.setDisable(true);
            mname.setDisable(true);
            aadhar.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(mname.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            aadhar.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(aadhar.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(category.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            aadhar.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(religion.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            aadhar.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(pno.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            aadhar.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(hno.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            aadhar.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(profession.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            aadhar.setDisable(true);
            age.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(age.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            aadhar.setDisable(true);
            gender.setDisable(true);
        }
        else if (notDisable.equals(gender.getPromptText())){
            name.setDisable(true);
            fname.setDisable(true);
            mname.setDisable(true);
            category.setDisable(true);
            religion.setDisable(true);
            pno.setDisable(true);
            hno.setDisable(true);
            profession.setDisable(true);
            age.setDisable(true);
            aadhar.setDisable(true);
        }
    }

    @FXML
    public void searchData(){
        String PostName=post.getText();
        String VillageName=village.getText();
        CommonProcedureForAll common=new CommonProcedureForAll();
        if (PostName.isEmpty()||VillageName.isEmpty()){
            common.alertMessage("warning", "Incomplete", "Please Enter Post And Village Name Before Moving To Next..!");
        }
        else {
            if (common.isLower(PostName)&&common.isLower(VillageName)){
                try {
                    int villageFound=0;
                    String enteredVillage=PostName+"_"+VillageName;
                    ResultSet checkVillage=st.executeQuery("select Post,Village from allvillages;");
                    while (checkVillage.next()){
                        if ((checkVillage.getString(1)+"_"+checkVillage.getString(2)).equals(enteredVillage)){
                            villageFound=1;
                            break;
                        }
                    }if (villageFound==1){
                        if (r1.isSelected()||r2.isSelected()){
                            if (!(name.getText()).isEmpty()){
                                System.out.println("In Name");
                                if(common.isLower(name.getText())){
                                    if (r1.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where name='"+name.getText()+"';");
                                        bindColumns(rs1);
                                        System.out.println("data is comming from "+enteredVillage);
                                    }
                                    else{
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where name='"+name.getText()+"';");
                                        bindColumns(rs1);
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else if (!(fname.getText()).isEmpty()){
                                System.out.println("In Father Name");
                                if(common.isLower(fname.getText())){
                                    if (r1.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where father_name='"+fname.getText()+"';");
                                        bindColumns(rs1);
                                        System.out.println("data is comming from "+enteredVillage);
                                    }
                                    else {
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where fathar_name='"+fname.getText()+"';");
                                        bindColumns(rs1);
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else if (!(mname.getText()).isEmpty()){
                                System.out.println("In Mother Name");
                                if(common.isLower(mname.getText())){
                                    if (r1.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where mother_name ='"+mname.getText()+"';");
                                        bindColumns(rs1);
                                        System.out.println("data is comming from "+enteredVillage);
                                    }
                                    else {
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where mother_name ='"+mname.getText()+"';");
                                        bindColumns(rs1);
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else if (!(age.getText()).isEmpty()){
                                System.out.println("In Age");
                                if (r1.isSelected()){
                                    System.out.println("data is comming from "+enteredVillage);
                                    if (rage1.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where age < "+age.getText()+";");
                                        bindColumns(rs1);
                                    }
                                    else if (rage2.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where age > "+age.getText()+";");
                                        bindColumns(rs1);
                                    }
                                    else if (rage3.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where age = "+age.getText()+";");
                                        bindColumns(rs1);
                                    }
                                    else common.alertMessage("error", "Error", "Type Of Age Is Not Selected..!");
                                }
                                else {
                                    System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                    if (rage1.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where age < "+age.getText()+";");
                                        bindColumns(rs1);
                                    }
                                    else if (rage2.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where age > "+age.getText()+";");
                                        bindColumns(rs1);
                                    }
                                    else if (rage3.isSelected()){
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where age = "+age.getText()+";");
                                        bindColumns(rs1);
                                    }
                                    else common.alertMessage("error", "Error", "Type Of Age Is Not Selected..!");
                                }
                            }
                            else if (!(aadhar.getText()).isEmpty()){
                                System.out.println("In Aadhar");
                                if (common.checkLength(aadhar.getText(),12)){
                                    if (r1.isSelected()){
                                        System.out.println("data is comming from "+enteredVillage);
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where aadhar_no  = '"+aadhar.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                    else {
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where aadhar_no  = '"+aadhar.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                }
                                else {
                                    aadhar.clear();
                                    common.alertMessage("warning", "Invalid", "Incorrect Aadhar No.. Please Re-enter It Again..!");
                                }
                            }
                            else if (!(category.getText()).isEmpty()){
                                System.out.println("In Category");
                                if(common.isLower(category.getText())){
                                    if (r1.isSelected()){
                                        System.out.println("data is comming from "+enteredVillage);
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where category ='"+category.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                    else {
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where category ='"+category.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else if (!(religion.getText()).isEmpty()){
                                System.out.println("In Religion");
                                if(common.isLower(religion.getText())){
                                    if (r1.isSelected()){
                                        System.out.println("data is comming from "+enteredVillage);
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where religion ='"+religion.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                    else {
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where religion ='"+religion.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else if (!(gender.getText()).isEmpty()){
                                System.out.println("In Gender");
                                if(common.isLower(gender.getText())){
                                    if (r1.isSelected()){
                                        System.out.println("data is comming from "+enteredVillage);
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where gender  ='"+gender.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                    else {
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where gender  ='"+gender.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else if (!(pno.getText()).isEmpty()){
                                System.out.println("In Phone Number");
                                if (common.checkLength(pno.getText(),10)){
                                    if (r1.isSelected()){
                                        System.out.println("data is comming from "+enteredVillage);
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where ph_no   ='"+pno.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                    else {
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where ph_no   ='"+pno.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Invalid", "Wrong Mobile Number Please Check It Again..!");

                                }
                            }
                            else if (!(hno.getText()).isEmpty()){
                                System.out.println("In House Number");
                                if (r1.isSelected()){
                                    System.out.println("data is comming from "+enteredVillage);
                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where house_no='"+hno.getText()+"';");
                                    bindColumns(rs1);
                                }
                                else {
                                    System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where house_no='"+hno.getText()+"';");
                                    bindColumns(rs1);
                                }
                            }
                            else if (!(profession.getText()).isEmpty()){
                                System.out.println("In Profession");
                                if (common.isLower(profession.getText())){
                                    if (r1.isSelected()){
                                        System.out.println("data is comming from "+enteredVillage);
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+" where work_type='"+profession.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                    else {
                                        System.out.println("data is comming from "+enteredVillage+"_head_of_family");
                                        ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_head_of_family where work_type='"+profession.getText()+"';");
                                        bindColumns(rs1);
                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                }
                            }
                            else common.alertMessage("error", "Error", "Please Enter Condition On Which You Want To Search Record..!");
//Working in  r1 and r2
                        }
                        else if(r3.isSelected()){
                            String headName=head.getText();
                            String houseNumber=house.getText();
                            String headHouse=headName+"_"+houseNumber;
                            if (headName.isEmpty()||houseNumber.isEmpty()){
                                common.alertMessage("warning", "Incomplete", "Please Enter Head Of Family Name And The House Number Before Moving To Next..!");
                            }
                            else {
//                                TODO
                                if (common.isLower(headName)){
                                    ResultSet checkHeadAndHouse=st.executeQuery("select name,house_no from "+enteredVillage+"_head_of_family;");
                                    int headFound=0;
                                    while (checkHeadAndHouse.next()){
                                        if ((checkHeadAndHouse.getString(1)+"_"+checkHeadAndHouse.getString(2)).equals(headHouse)){
                                            headFound=1;
                                            break;
                                        }
                                    }
                                    if (headFound==1){
                                            if (!(name.getText()).isEmpty()){
                                                System.out.println("In Name");
                                                if(common.isLower(name.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_name='"+name.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else if (!(fname.getText()).isEmpty()){
                                                System.out.println("In Father Name");
                                                if(common.isLower(fname.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_fname ='"+fname.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else if (!(mname.getText()).isEmpty()){
                                                System.out.println("In Mother Name");
                                                if(common.isLower(mname.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_mname ='"+mname.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else if (!(age.getText()).isEmpty()){
                                                System.out.println("In Age");
                                                if (rage1.isSelected()){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_age < "+age.getText()+";");
                                                    bindColumns(rs1);
                                                }
                                                else if (rage2.isSelected()){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_age > "+age.getText()+";");
                                                    bindColumns(rs1);
                                                }
                                                else if (rage3.isSelected()){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_age = "+age.getText()+";");
                                                    bindColumns(rs1);
                                                }
                                                else common.alertMessage("error", "Error", "Type Of Age Is Not Selected..!");

                                            }
                                            else if (!(aadhar.getText()).isEmpty()){
                                                System.out.println("In Aadhar");
                                                if (common.checkLength(aadhar.getText(),12)){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_aadhar_no  = '"+aadhar.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    aadhar.clear();
                                                    common.alertMessage("warning", "Invalid", "Incorrect Aadhar No.. Please Re-enter It Again..!");
                                                }
                                            }
                                            else if (!(category.getText()).isEmpty()){
                                                System.out.println("In Category");
                                                if(common.isLower(category.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_category ='"+category.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else if (!(religion.getText()).isEmpty()){
                                                System.out.println("In Religion");
                                                if (common.isLower(religion.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_religion ='"+religion.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else if (!(gender.getText()).isEmpty()){
                                                System.out.println("In Gender");
                                                if(common.isLower(gender.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_gender ='"+gender.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else if (!(pno.getText()).isEmpty()){
                                                System.out.println("In Phone Number");
                                                if (common.checkLength(pno.getText(),10)){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_ph_no ='"+pno.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Invalid", "Wrong Mobile Number Please Check It Again..!");

                                                }
                                            }
                                            else if (!(hno.getText()).isEmpty()){
                                                System.out.println("In House Number");
                                                ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_house_no='"+hno.getText()+"';");
                                                bindColumns(rs1);
                                            }
                                            else if (!(profession.getText()).isEmpty()){
                                                System.out.println("In Profession");
                                                if (common.isLower(profession.getText())){
                                                    ResultSet rs1=st.executeQuery("select * from "+enteredVillage+"_"+headHouse+"_family where m_work_type='"+profession.getText()+"';");
                                                    bindColumns(rs1);
                                                }
                                                else {
                                                    common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
                                                }
                                            }
                                            else common.alertMessage("error", "Error", "Please Enter Condition On Which You Want To Search Record..!");
// Working r3
                                        System.out.println("Data is comming from "+enteredVillage+"_"+headHouse+"_family Table..!");
                                    }
                                    else {
                                        common.alertMessage("warning", "Not Found", "It Seems Like This Person '"+headName+"' Is Not Registered As The Head Of His Family..!");

                                    }
                                }
                                else {
                                    common.alertMessage("warning", "Error", "Please Assure That Head Of Family Name Is Filled In Lowercase And Without Space..!");

                                }

                            }
                        }
//                        TODO
                        else common.alertMessage("warning", "Not Selected", "Please Choose Where You Want To Search Data.. At The Bottom Left..!");

                    }
                    else {
                        common.alertMessage("warning", "Not Found", "This Village Is Not Registered On This Portal..!");

                    }
                }
                catch (SQLException e){
                    common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stacktrace..!");
                    e.printStackTrace();
                }
            }
            else {
                common.alertMessage("warning", "Error", "Please Assure That All Details Are Filled In Lowercase And Without Space..!");
            }
        }
        name.setDisable(false);
        fname.setDisable(false);
        mname.setDisable(false);
        category.setDisable(false);
        religion.setDisable(false);
        pno.setDisable(false);
        hno.setDisable(false);
        profession.setDisable(false);
        aadhar.setDisable(false);
        gender.setDisable(false);
        age.setDisable(false);
        name.clear();
        fname.clear();
        mname.clear();
        category.clear();
        religion.clear();
        pno.clear();
        hno.clear();
        profession.clear();
        aadhar.clear();
        gender.clear();
        age.clear();
    }
    @FXML
    public void bindColumns(ResultSet rs){
        oblist.clear();
        try {
            if(rs.next()==true){
                System.out.println("Yes Data Is Found..!");
                do {
                    oblist.add(new Featch_AllAboutVillagersDetails(rs.getString(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                            rs.getString(12),rs.getString(13),rs.getString(14)));
                }while (rs.next());
            }else {
                CommonProcedureForAll common=new CommonProcedureForAll();
                common.alertMessage("warning", "Empty", "No Data Found..!");
                return;
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

        showRearchResult.setItems(oblist);
        showRearchResult.setVisible(true);
    }

    @FXML
    public void enterSubmit(){
        search.setStyle("-fx-background-color:#dbbfbd");
    }
    @FXML
    public void exitsubmit(){
        search.setStyle("-fx-background-color:#955251");
    }
}
