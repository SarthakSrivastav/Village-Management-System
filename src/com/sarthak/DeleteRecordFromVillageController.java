package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
public class DeleteRecordFromVillageController extends Main{
    static String finalPost;
    static String finalVillage;
    int datafoundInVillage =0;
    String houseNumber;
    String villagerName;
    @FXML
    private TableView<Featch_AllAboutVillagersDetails> showRearchResult,showFamily;
    @FXML
    private TextField post,village,aadhar,newHead;
    @FXML
    private Button remove;
    @FXML
    private TableColumn<Featch_AllAboutVillagersDetails,String> c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,
            c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28;
    @FXML
    ObservableList<Featch_AllAboutVillagersDetails> oblist= FXCollections.observableArrayList();
    @FXML
    ObservableList<Featch_AllAboutVillagersDetails> oblist2= FXCollections.observableArrayList();
    @FXML
    public void getPrePostAndVillage(String post,String village){
        finalPost=post;
        finalVillage=village;
    }

    @FXML
    public void initialize(){
        post.clear();
        village.clear();
        post.setEditable(true);
        village.setEditable(true);
        post.setPromptText("in_lowercase");
        village.setPromptText("in_lowercase");
        aadhar.setPromptText("12-digits");
        newHead.setPromptText("new_family_head_name");
        showRearchResult.getColumns().forEach(column ->column.setMinWidth(120));
        showRearchResult.getColumns().forEach(column ->column.setMaxWidth(300));
        showFamily.getColumns().forEach(column ->column.setMinWidth(120));
        showFamily.getColumns().forEach(column ->column.setMaxWidth(300));
        if (finalVillage!=null&&finalPost!=null){
            post.setText(finalPost);
            village.setText(finalVillage);
            post.setEditable(false);
            village.setEditable(false);
        }
    }
    @FXML
    public void handleSearch(){
        String postName=post.getText();
        String villageName=village.getText();
        String aadharNo=aadhar.getText();
        CommonProcedureForAll common=new CommonProcedureForAll();
        try {
            if (postName.isEmpty()||villageName.isEmpty()||aadharNo.isEmpty()){
                common.alertMessage("error","Incomplete Details","Minimum Required Details->(Post, Village, Aadhar No.) Before Moving To Next..!");
            }
            else {
                if (common.checkLength(aadharNo,12)&&common.isLower(postName)&&common.isLower(villageName)){
                    ResultSet checkVillage=st.executeQuery("select Post,Village from allvillages;");
                    int villageFound=0;
                    while (checkVillage.next()){
                        if ((checkVillage.getString(1)+"_"+checkVillage.getString(2)).equals(postName+"_"+villageName)){
                            villageFound=1;
                            break;
                        }
                    }
                    if (villageFound==1){
                        ResultSet rsInVillage=st.executeQuery("select * from "+postName+"_"+villageName+" where aadhar_no = '"+aadharNo+"';");//123412341234
                        bindColumns(rsInVillage);
                        if (datafoundInVillage ==1){
                            ResultSet rsInHead=st.executeQuery("select name  from "+postName+"_"+villageName+"_head_of_family where house_no ='"+houseNumber+"';");
                            rsInHead.next();
                            String head=rsInHead.getString(1);
                            if (head.equals(villagerName)){
                                Optional<ButtonType> result=common.alertMessage("confirmation","Give Confirmation","Member From This Id Is The Head Of His Family... Please Choose New Head For This Family From The Table Given Below..!");
                                if (result.get()==ButtonType.OK){
                                    String NewHeadName=newHead.getText();
                                    ResultSet rsCheckFamilyMember=st.executeQuery("select count(m_aadhar_no) from "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family");
                                    rsCheckFamilyMember.next();
                                    int countFamilyMembers=rsCheckFamilyMember.getInt(1);
                                    System.out.println(countFamilyMembers);
                                    if (countFamilyMembers==1){
                                        System.out.println("countFamilyMembers==1");
                                        Optional<ButtonType> resultOnlyOneFamily=common.alertMessage("confirmation","Please Confirm","Oops... Person From This ID Is The Only Member From His Family... Do You Really Want To Remove Him..?");
                                        if (resultOnlyOneFamily.get()==ButtonType.OK){
                                            String query1="delete from "+postName+"_"+villageName+" where aadhar_no = '"+aadharNo+"';";
                                            String query2="delete from "+postName+"_"+villageName+"_head_of_family where aadhar_no = '"+aadharNo+"';";
                                            String query3="drop table "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family;";
                                            st.addBatch(query1);
                                            st.addBatch(query2);
                                            st.addBatch(query3);
                                            st.executeBatch();
                                            st.clearBatch();
                                            common.alertMessage("information","Operation Successful","Data Removed Successfully From Your Record File..!");
                                            oblist.clear();
                                            oblist2.clear();
                                            aadhar.clear();
                                            newHead.clear();
                                        }
                                    }
                                    else {
                                        System.out.println("countFamilyMembers != 1 ");
//                                        st.execute("delete from  "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family where m_aadhar_no = '"+aadharNo+"';");
                                        ResultSet rsCheckFamily=st.executeQuery("select * from "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family");
                                        bindColumnsTableTwo(rsCheckFamily);
                                        if (NewHeadName.isEmpty()){
                                            common.alertMessage("warning","Empty Field","Please Enter Name Of New Head Of The Family Before Removing The Record..!");
                                        }
                                        else {
                                            if (common.isLower(NewHeadName)){
                                                ResultSet checkMember=st.executeQuery("select m_name  from "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family;");
                                                int memberFound=0;
                                                while (checkMember.next()){
                                                    if (!head.equals(checkMember.getString(1))){
                                                        if (NewHeadName.equals(checkMember.getString(1))){
                                                            memberFound=1;
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (memberFound==1){
//                                                    st.execute("delete from  "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family where m_aadhar_no = '"+aadharNo+"';");
                                                    String query1="delete from "+postName+"_"+villageName+" where aadhar_no = '"+aadharNo+"';";
                                                    String query2="delete from "+postName+"_"+villageName+"_head_of_family where aadhar_no = '"+aadharNo+"';";
                                                    String query3="delete from  "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family where m_aadhar_no = '"+aadharNo+"';";
                                                    String query4="alter table "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family rename to "+postName+"_"+villageName+"_"+NewHeadName+"_"+houseNumber+"_family;";
                                                    st.addBatch(query1);
                                                    st.addBatch(query2);
                                                    st.addBatch(query3);
                                                    st.addBatch(query4);
                                                    st.executeBatch();
                                                    st.clearBatch();
                                                    ResultSet rs=st.executeQuery("select * from "+postName+"_"+villageName+"_"+NewHeadName+"_"+houseNumber+"_family where m_name = '"+NewHeadName+"';");
                                                    rs.next();
                                                    st.execute("insert into "+postName+"_"+villageName+"_head_of_family values('"+rs.getString(1)+"','"+rs.getString(2)+"'" +
                                                            ",'"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+rs.getString(6)+"'" +
                                                            ",'"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"'" +
                                                            ",'"+rs.getString(11)+"','"+rs.getString(12)+"',curdate(),curtime());");
                                                    common.alertMessage("information","Operation Successful","Data Removed Successfully From Your Record File..!");
                                                    oblist.clear();
                                                    oblist2.clear();
                                                    aadhar.clear();
                                                    newHead.clear();
                                                }else {
                                                    String title;
                                                    if (NewHeadName.equals(head)){
                                                        title="Please Enter Name Of New Head Of The Family... Not The Name Of Existing Head..!";
                                                    }
                                                    else {
                                                        title=NewHeadName+" Is Not The Family Member Of '"+head+"'... Please Enter Name Of Existing Member..!";
                                                    }
                                                    common.alertMessage("warning","Wrong Member",title);
                                                }
                                            }
                                            else {
                                                common.alertMessage("warning","Use Lower Case","Please Enter New Head Name In Lower Case Without Space...!");
                                                common.setCheckCase(0);
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                String query1="delete from "+postName+"_"+villageName+" where aadhar_no = '"+aadharNo+"';";
                                String query2="delete from "+postName+"_"+villageName+"_"+head+"_"+houseNumber+"_family where m_aadhar_no ='"+aadharNo+"';";
                                st.addBatch(query1);
                                st.addBatch(query2);
                                Optional<ButtonType> result=common.alertMessage("confirmation","Give Confirmation1111111","Do You Really Want To Remove This Person's Details From Your Record File..?");
                                if (result.get()==ButtonType.OK){
                                    st.executeBatch();
                                    st.clearBatch();
                                    common.alertMessage("information","Operation Successful","Data Removed Successfully From Your DB File..!");
                                    oblist.clear();
                                    oblist2.clear();
                                    aadhar.clear();
                                    newHead.clear();
                                }
                            }
                            datafoundInVillage =0;
                        }
                    }
                    else {
                        common.alertMessage("information","Village Not Found","This Village Is Not Registered Yet..!");
                    }
                }
                else {
                    if (common.getCheckCase()==1){
                        common.alertMessage("information","Use Lower Case","Please Fill All The Details In Lower Order Without Space..!");
                        common.setCheckCase(0);
                    }
                    if (common.getCheckLength()==1){
                        common.alertMessage("error","Wrong ID","Wrong Aadhar Number Please Check It Again...!");
                        aadhar.clear();
                    }
                }
            }
        }catch (SQLException e){
            common.alertMessage("error","Error","Something Went Wrong Please Check Your Stack Trace");
            e.printStackTrace();
        }
    }
    @FXML
    public void bindColumns(ResultSet rs){
        oblist.clear();
        try {
            if(rs.next()==true){
                System.out.println("Yes Data Is Found..!");
                datafoundInVillage =1;
                villagerName=rs.getString(1);
                houseNumber=rs.getString(11);
                do {
                    oblist.add(new Featch_AllAboutVillagersDetails(rs.getString(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                            rs.getString(12),rs.getString(13),rs.getString(14)));
                }while (rs.next());
            }else {
                CommonProcedureForAll common=new CommonProcedureForAll();
                common.alertMessage("information", "Empty", "No Data Found..!");
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
    public void bindColumnsTableTwo(ResultSet rs) {
        oblist2.clear();
        try {
            if (rs.next() == true) {
                System.out.println("Yes Data Is Found..!");
                do {
                    oblist2.add(new Featch_AllAboutVillagersDetails(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                            rs.getString(12), rs.getString(13), rs.getString(14)));
                } while (rs.next());
            } else {
                CommonProcedureForAll common = new CommonProcedureForAll();
                common.alertMessage("information", "Empty", "No Data Found..!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c15.setCellValueFactory(new PropertyValueFactory<>("name"));
        c16.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        c17.setCellValueFactory(new PropertyValueFactory<>("mother_name"));
        c18.setCellValueFactory(new PropertyValueFactory<>("age"));
        c19.setCellValueFactory(new PropertyValueFactory<>("dob"));
        c20.setCellValueFactory(new PropertyValueFactory<>("aadhar_no"));
        c21.setCellValueFactory(new PropertyValueFactory<>("category"));
        c22.setCellValueFactory(new PropertyValueFactory<>("gender"));
        c23.setCellValueFactory(new PropertyValueFactory<>("religion"));
        c24.setCellValueFactory(new PropertyValueFactory<>("ph_no"));
        c25.setCellValueFactory(new PropertyValueFactory<>("house_no"));
        c26.setCellValueFactory(new PropertyValueFactory<>("work_type"));
        c27.setCellValueFactory(new PropertyValueFactory<>("date"));
        c28.setCellValueFactory(new PropertyValueFactory<>("time"));
        showFamily.setItems(oblist2);
        showFamily.setVisible(true);
    }
    @FXML
    public void enterSubmit(){
        remove.setStyle("-fx-background-color:#ff1500");
    }
    @FXML
    public void exitsubmit(){
        remove.setStyle("-fx-background-color:#ff8566");
    }
}
