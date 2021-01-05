package com.sarthak;
import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
public class AddResourcesController extends Main{
    static String finalPost;
    static String finalVillage;
    @FXML
    private CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;
    @FXML

    private Button save;
    @FXML
    public void getPrePostAndVillage(String post,String village){
        finalPost=post;
        finalVillage=village;
    }
    @FXML
    private TextField post,village,ps,playschool,primaryschool,highschool,interscollege,hospital,psno,womens;
    @FXML
    public void initialize(){
//        save.setPadding(new Insets(10));
        post.clear();
        village.clear();
        post.setEditable(true);
        village.setEditable(true);
        playschool.setDisable(true);
        primaryschool.setDisable(true);
        highschool.setDisable(true);
        interscollege.setDisable(true);
        hospital.setDisable(true);
        if (finalVillage!=null&&finalPost!=null){
            post.setText(finalPost);
            village.setText(finalVillage);
            post.setEditable(false);
            village.setEditable(false);
        }
    }
    @FXML
    public void onChickAbleDisable(){
        if (c1.isSelected())
            playschool.setDisable(false);
        else
            playschool.setDisable(true);

        if (c2.isSelected())
            primaryschool.setDisable(false);
        else
            primaryschool.setDisable(true);

        if (c3.isSelected())
            highschool.setDisable(false);
        else
            highschool.setDisable(true);

        if (c4.isSelected())
            interscollege.setDisable(false);
        else
            interscollege.setDisable(true);

        if (c5.isSelected())
            hospital.setDisable(false);
        else
            hospital.setDisable(true);
    }
    public void handleAddResources(){
        String postName=post.getText();
        String villageName=village.getText();
        String psName=ps.getText();
        String psContact=psno.getText();
        String womenHelpLineNo=womens.getText();
        String playSchool;
        String primarySchool;
        String highSchool;
        String interCollege;
        String hospitalName;
        String Anganwadi;
        String water;
        String food;
        String safeForWomenOrNot;
        CommonProcedureForAll common=new CommonProcedureForAll();
        if (postName.isEmpty()||villageName.isEmpty()||psName.isEmpty()||psContact.isEmpty()||womenHelpLineNo.isEmpty()){
            common.alertMessage("information","Empty Details","Please Enter Above Five Details Before Moving To Next...!");
        }
        else {
            if (common.isLower(postName)&&common.isLower(villageName)&&common.isLower(psName)){
                try {
                    ResultSet rsCheckVillage=st.executeQuery("select Post,Village from allvillages;");
                    if (rsCheckVillage.next()==true){
                        int villageFound=0;
                        do {
                            if ((rsCheckVillage.getString(1)+"_"+rsCheckVillage.getString(2)).equals(postName+"_"+villageName)){
                                villageFound=1;
                                break;
                            }
                        }while (rsCheckVillage.next());
                        if (villageFound==1){
                            if (c1.isSelected())
                                playSchool=playschool.getText();
                            else playSchool="not_exist";

                            if (c2.isSelected())
                                primarySchool=primaryschool.getText();
                            else primarySchool="not_exist";

                            if (c3.isSelected())
                                highSchool=highschool.getText();
                            else highSchool="not_exist";

                            if (c4.isSelected())
                                interCollege=interscollege.getText();
                            else interCollege="not_exist";

                            if (c5.isSelected())
                                hospitalName=hospital.getText();
                            else hospitalName="not_exist";

                            if (c6.isSelected())
                                Anganwadi="exist";
                            else
                                Anganwadi="not_exist";

                            if (c7.isSelected())
                                water="exist";
                            else
                                water="not_exist";

                            if (c8.isSelected())
                                food="exist";
                            else
                                food="not_exist";

                            if (c9.isSelected())
                                safeForWomenOrNot="yes";
                            else
                                safeForWomenOrNot="no";

                            String query="insert into "+postName+"_"+villageName+"_resources values('"+psName+"','"+psContact+"','"+womenHelpLineNo+"','"+playSchool+"'," +
                                    "'"+primarySchool+"','"+highSchool+"','"+interCollege+"','"+hospitalName+"','"+Anganwadi+"','"+water+"','"+food+"','"+safeForWomenOrNot+"'," +
                                    "curdate());";

                            ResultSet rsAvailable=st.executeQuery("select police_station_name from "+postName+"_"+villageName+"_resources;");
                            if (rsAvailable.next()==true){
                                Optional<ButtonType> result=common.alertMessage("confirmation","Already Have Record","It Seems Like Resources For This Village Already Saved..! If You Want To Update Them, Then Please Press 'OK' ..!");
                                if (result.get()==ButtonType.OK){
                                    st.addBatch("delete from "+postName+"_"+villageName+"_resources;");
                                    st.addBatch(query);
                                    st.executeBatch();
                                    st.clearBatch();
                                    common.alertMessage("information","Resources Save","All The Resources Are Saved Successfully..!");
                                    allClear();
                                }
                            }
                            else {
                                st.execute(query);
                                common.alertMessage("information","Resources Save","All The Resources Are Saved Successfully..!");
                                allClear();
                            }
                        }
                        else {
                            common.alertMessage("information","Village Not Found","It Seems Like This Village Is Not Registered On This Portal..!");
                            post.clear();
                            village.clear();

                        }
                    }
                    else {
                        common.alertMessage("warning","Empty Portal","Yet There Is Not Any Village Registered On This Portal... So Please Register Village First Then Add Resources..!");
                        allClear();
                    }
                }
                catch (SQLException e){
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
    public void allClear(){
        ps.clear();
        playschool.clear();
        primaryschool.clear();
        highschool.clear();
        interscollege.clear();
        hospital.clear();
        psno.clear();
        womens.clear();
        c1.setSelected(false);
        c2.setSelected(false);
        c3.setSelected(false);
        c4.setSelected(false);
        c5.setSelected(false);
        c6.setSelected(false);
        c7.setSelected(false);
        c8.setSelected(false);
        c9.setSelected(false);
        playschool.setDisable(true);
        primaryschool.setDisable(true);
        highschool.setDisable(true);
        interscollege.setDisable(true);
        hospital.setDisable(true);
    }

    @FXML
    public void enterSubmit(){
        save.setStyle("-fx-background-color:#d9d9d9");
    }
    @FXML
    public void exitsubmit(){
        save.setStyle("-fx-background-color:#4b7cb4");
    }
}
