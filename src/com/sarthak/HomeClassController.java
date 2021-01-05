package com.sarthak;
import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeClassController extends Main {
    static int loginAsOwner;
    static int loginAsAdmin;
    static int loginAsGeneral;
    String PostName;
    String VillageName;
    @FXML
    private BorderPane homeBorderPane;
    @FXML
    private TextField post, village,search;
    @FXML
    private Label l1, l2;
    @FXML
    private Button click;

    @FXML
    public void initialize() {
        l1.setText("''Welcome To My Project''");

    }
    public void setLoginType(int owner, int admin, int general) {
        loginAsOwner = owner;
        loginAsAdmin = admin;
        loginAsGeneral = general;
    }

    @FXML
    public void checkLoginTypeForNewAdmin() {
        if (loginAsOwner == 1) {
            addNewAdminDialog();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Register New Admin On This Portal... Only Owner Have That Permission..!");
        }
    }

    @FXML
    public void addNewAdminDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());

        dialog.setTitle("Register New Admin");
//        dialog.setHeaderText("Fill The Form To ''Register New Admin''.");
        dialog.setResizable(true);
        dialog.getDialogPane().setMinSize(500.0, 200.0);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("newAdminFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            System.out.println("Couldn't Load The Dialog");
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
    }

    @FXML
    public void checkLoginTypeForNewVillage() {
        if (loginAsOwner == 1) {
            addNewVillageDialog();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Register New Village On This Portal... Only Owner Have That Permission..!");
        }
    }

    @FXML
    public void addNewVillageDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());
        dialog.setTitle("Register New Village");
//        dialog.setHeaderText("Fill The Form To ''Register New Village''.");
        dialog.setResizable(true);
        dialog.getDialogPane().setMinSize(500.0, 200.0);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddNewVillageFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    @FXML

//    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2
    public void checkLoginTypeForRemoveAdmin() {
        if (loginAsOwner == 1) {
            removeAdminDialog();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Remove Admin... Only Owner Have The Permission To Remove Admin..!");
        }
    }

    @FXML
    public void removeAdminDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());

        dialog.setTitle("Remove Admin");
        dialog.setHeaderText("Please Fill The Required Details Mention Below.");
        dialog.setResizable(true);
        dialog.getDialogPane().setMinSize(500.0, 200.0);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("removeAdminFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            System.out.println("Couldn't Load The Dialog");
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
    }

    @FXML
    public void handleAllRedisteredVillages() {
        System.out.println("In Handle Show Villagers List");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());
        dialog.setTitle("Registered Villages On This Portal");
        dialog.getDialogPane().setMinSize(1000, 500);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("allRegsteredVillagesFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    @FXML
    public void checkLoginTypeForHeadOfFamily() {
        if (loginAsOwner == 1 || loginAsAdmin == 1) {
            handleHeadOfTheFamily();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Open That Section..!");
        }
    }

    @FXML
    public void handleHeadOfTheFamily() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());
        dialog.setTitle("New Family Head");
        dialog.setHeaderText("Please Fill All The Required Details Given Below Without Space (use '_' for spaces) To Register New Head Of The Family...!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("headOfFamilyFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.show();
    }

    @FXML
    public void checkLoginTypeForFamilyMember() {
        if (loginAsOwner == 1 || loginAsAdmin == 1) {
            handleFamilyMembers();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Open That Section..!");
        }
    }

    @FXML
    public void handleFamilyMembers() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());
        dialog.setTitle("Add Family Members");
        dialog.setHeaderText("Please Fill All The Required Details Given Below Without Space (use '_' for spaces) To Add Family Members..!");
//        dialog.setResizable(true);
//        dialog.getDialogPane().setMinSize(500.0,200.0);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("familyMembersFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();

    }

    @FXML
    public void showAllDetails() {
        try {
            Parent homeRoot = FXMLLoader.load(getClass().getResource("allAboutVillagers.fxml"));
            Stage homeStage = new Stage();
            homeStage.setTitle("Welcome To List View Page");
//            homeStage.setScene(new Scene(homeRoot));
            homeStage.setScene(new Scene(homeRoot, 1280, 655));
            homeStage.setMinHeight(655.0);
            homeStage.setMinWidth(1250.0);
//                         homeStage.setScene(new Scene(homeRoot, 1000, 500));
//                         homeStage.setMinHeight(655.0);
//                         homeStage.setMinWidth(1250.0);
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
    }

    @FXML
    public void handleSearchPersons() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchPersonsFXML.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Search");
            stage.setScene(new Scene(root, 1280, 655));
            stage.setMinHeight(655.0);
            stage.setMinWidth(1250.0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
    }

    @FXML
    public void checkLoginTypeForRemoveVillager() {
        if (loginAsOwner == 1 || loginAsAdmin == 1) {
            handleRemoveVillageMembers();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Manipulate Villager's Details..!");
        }
    }

    @FXML
//    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    public void handleRemoveVillageMembers() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("deleteRecordFromVillageFXML.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Delete Record");
            stage.setScene(new Scene(root, 1280, 655));
            stage.setMinHeight(655.0);
            stage.setMinWidth(1250.0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
    }

    @FXML
    public void checkLoginTypeForAddResources() {
        if (loginAsOwner == 1 || loginAsAdmin == 1) {
            handleAddResources();
        } else {
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("warning", "Lock", "You Are Not The Authorized User To Add Resources To The Village..!");
        }
    }

    @FXML
    public void handleAddResources() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());
        dialog.setTitle("Add Resources");
        dialog.setHeaderText("Please Fill Carefully All The Details Given Below.");
//        dialog.setResizable(true);
        dialog.getDialogPane().setMinSize(900.0, 400.0);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddResourcesFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    @FXML
    public void handleShowResources() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeBorderPane.getScene().getWindow());
        dialog.setTitle("List Of Resources");
        dialog.setResizable(true);
        dialog.getDialogPane().setMinSize(1050.0, 400.0);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("showResourcesFXML.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    public boolean commonMethodForAll() {
        PostName = post.getText();
        VillageName = village.getText();
        CommonProcedureForAll common = new CommonProcedureForAll();
        if (PostName.isEmpty() || VillageName.isEmpty()) {
            common.alertMessage("warning", "Empty Details", "Please Enter Post And Village Name To See The Result..!");
            return false;
        } else {
            if (common.isLower(PostName) && common.isLower(VillageName)) {
                return true;
            } else {
                common.alertMessage("warning", "Use Lower Case...", "please fill all details in lower case without space..!");
                common.setCheckCase(0);
                return false;
            }
        }
    }

    public boolean checkVillage() {
        int villageFound = 0;
        CommonProcedureForAll common = new CommonProcedureForAll();
        try {
            ResultSet checkVillage = st.executeQuery("select Post,Village from allvillages;");
            if (checkVillage.next() == true) {
                do {
                    if ((checkVillage.getString(1) + "_" + checkVillage.getString(2)).equals(PostName + "_" + VillageName)) {
                        villageFound = 1;
                        break;
                    }
                } while (checkVillage.next());
            } else {
                common.alertMessage("information", "Empty Portal", "Yet There Is Not Any Village Registered On This Portal..!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
        if (villageFound == 1)
            return true;
        else {
            common.alertMessage("information", "Not Found", "It Seems Like This Village Is Not Registered On This Portal..!");
            l1.setText(null);
            return false;
        }
    }

    @FXML
    public void male() {
        setLeftButton_4(1);
    }

    @FXML
    public void female() {
        setLeftButton_4(2);
    }
    @FXML
    public void hindu(){
        setLeftButton_4(3);
    }
    @FXML
    public void muslim(){
        setLeftButton_4(4);
    }
    @FXML
    public void responsible(){
        setLeftButton_4(5);
    }
    @FXML
    public void womensSafeOrNot(){
        setLeftButton_4(6);
    }
    @FXML
    void populationBelowEighteenYears(){
        setLeftButton_4(7);
    }
    @FXML
    void populationAboveEighteenYears(){
        setLeftButton_4(8);
    }
    @FXML
    void working(){
        setLeftButton_4(9);
    }
    @FXML
    void nonWorking(){
        setLeftButton_4(10);
    }
    @FXML
    void totalPopulation(){
        setLeftButton_4(11);
    }
    @FXML
    void admin(){
        onlyForAdminAndTotalVillages(12);
    }
    @FXML
    void villages(){
        onlyForAdminAndTotalVillages(13);
    }
    @FXML
    void totalHouses(){
        setLeftButton_4(14);
    }

    @FXML
    public void setLeftButton_4(int mf) {
        try {
            if (commonMethodForAll()) {
                if (checkVillage()) {
                    if (mf == 1) {
                        ResultSet male = st.executeQuery("select count(gender) from " + PostName + "_" + VillageName + " where gender='male';");
                        male.next();
                        int Male = male.getInt(1);
                        l1.setText("Total Male Population = '" + Male + "'");
                    } else if (mf==2){
                        ResultSet female = st.executeQuery("select count(gender) from " + PostName + "_" + VillageName + " where gender='female';");
                        female.next();
                        int Female = female.getInt(1);
                        l1.setText("Total Female Population = '" + Female + "'");
                    }else if (mf==3){
                        ResultSet hindu = st.executeQuery("select count(religion ) from " + PostName + "_" + VillageName + " where religion = 'hindu';");
                        hindu.next();
                        int Hindu = hindu.getInt(1);
                        l1.setText("Total Hindu Population = '" + Hindu + "'");
                    }
                    else if (mf==4){
                        ResultSet muslim = st.executeQuery("select count(religion ) from " + PostName + "_" + VillageName + " where religion = 'muslim';");
                        muslim.next();
                        int Muslim = muslim.getInt(1);
                        l1.setText("Total Muslim Population = '" + Muslim + "'");
                    }
                    else if (mf==5){
                        ResultSet responsible = st.executeQuery("select count(name) from " + PostName + "_" + VillageName + "_responsible_peoples;");
                        responsible.next();
                        int Responsible = responsible.getInt(1);
                        l1.setText("Total Responsible People From This Village = '" + Responsible + "'");
                    }
                    else if (mf==6){
                        ResultSet safeWomen = st.executeQuery("select safe_for_womens_or_not from " + PostName + "_" + VillageName + "_resources;");
                        if (safeWomen.next()==true){
                            String Safe = safeWomen.getString(1);
                            if (Safe.equals("yes")){
                                l1.setText("'Yes' "+VillageName+" Is Safe For Women's.");
                            }
                            else l1.setText("'No' "+VillageName+" Is Not Safe For Women's.");
                            l2.setText(null);
                        }
                        else {
                            CommonProcedureForAll common=new CommonProcedureForAll();
                            common.alertMessage("information","Empty Result","No Record Found..!");
                            l1.setText(" ");
                            l2.setText(" ");
                        }
                        return;
                    }
                    else if (mf==7){
                        ResultSet belowEighteen = st.executeQuery("select count(age) from " + PostName + "_" + VillageName + " where age<=18;");
                        belowEighteen.next();
                        int Below = belowEighteen.getInt(1);
                        l1.setText("Population Below or Equal To 18 yr's = '" + Below + "'");
                    }
                    else if (mf==8){
                        ResultSet aboveEighteen = st.executeQuery("select count(age) from " + PostName + "_" + VillageName + " where age>18;");
                        aboveEighteen.next();
                        int Above = aboveEighteen.getInt(1);
                        l1.setText("Population Above 18 yr's = '" + Above + "'");
                    }
                    else if (mf==9){
                        ResultSet working = st.executeQuery("select count(work_type) from "+PostName+"_"+VillageName+" where work_type != 'unemployee';");
                        working.next();
                        int Working = working.getInt(1);
                        l1.setText("Total Working Population = '" + Working + "'");
                    }
                    else if (mf==10){
                        ResultSet nonWorking = st.executeQuery("select count(work_type) from "+PostName+"_"+VillageName+" where work_type = 'unemployee';");
                        nonWorking.next();
                        int NonWorking = nonWorking.getInt(1);
                        l1.setText("Total Non-Working Population = '" + NonWorking + "'");
                    }
                    else if (mf==11){
                        ResultSet rs1 = st.executeQuery("select count(aadhar_no) from " + PostName + "_" + VillageName + ";");
                        if (rs1.next() == true) {
                            int totalPopulation = rs1.getInt(1);
                            l1.setText("Total Population Of " + VillageName + " = '" + totalPopulation + "'.");
                        } else {
                            l1.setText("Yet There Are Not Any Person Registered From '" + VillageName + "' On This Portal. ");
                        }
                        l2.setText(null);
                        return;
                    }
                    else if (mf==14){
                        ResultSet tHouses = st.executeQuery("select count(aadhar_no) from " + PostName + "_" + VillageName + "_head_of_family;");
                        tHouses.next();
                        int T_House = tHouses.getInt(1);
                        l1.setText("Total Registered Houses From '" + VillageName + "' Is = '" + T_House + "'.");
                        l2.setText(null);
                        return;
                    }


                    ResultSet totalPopulation = st.executeQuery(" select count(aadhar_no) from " + PostName + "_" + VillageName + ";");
                    totalPopulation.next();
                    int total = totalPopulation.getInt(1);
                    l2.setText("Total Population = '" + total + "'");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
    }
    @FXML
    public void onlyForAdminAndTotalVillages(int mf){
        try {
            if (mf==12){
                ResultSet rs1 = st.executeQuery("select count(aadhar_no) from admininfo;");
                if (rs1.next() == true) {
                    int totalAdmins = rs1.getInt(1);
                    l1.setText("Total Registered Admins On This Portal = '" + totalAdmins + "'.");
                } else {
                    l1.setText("Yet There Are Not Any Admin Registered On This Portal. ");
                }
                l2.setText(null);
                return;
            }
            else if (mf==13){
                ResultSet rs1 = st.executeQuery("select count(Village) from allvillages;");
                if (rs1.next() == true) {
                    int totalVillage = rs1.getInt(1);
                    l1.setText("Total Registered Villages On This Portal = '" + totalVillage + "'.");
                } else {
                    l1.setText("Yet There Are Not Any Village Registered On This Portal. ");
                }
                l2.setText(null);
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
            CommonProcedureForAll common = new CommonProcedureForAll();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace..!");
        }
    }
    @FXML
    public void clearWindow() {
        post.clear();
        village.clear();
        l1.setText(null);
        l2.setText(null);
    }
    @FXML
    public void myPortfolio(){
        openWebpage("file:///D:/VSCode/My%20New%20Portfolio/about_village.html");
    }
    @FXML
    public void myQualifications(){
        openWebpage("file:///D:/VSCode/MyCV.html");
    }
    @FXML
    public void openGoogle(){
        String toSearch=search.getText();
        if (!toSearch.isEmpty()){
            String open="https://"+toSearch;
            openWebpage(open);
        }
        else {
            CommonProcedureForAll common=new CommonProcedureForAll();
            common.alertMessage("warning","Empty url","You Didn't Provide What you Want To Search..!");
        }
    }
    @FXML
    public static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void enterSubmit(){
        click.setStyle("-fx-background-color:#d9d9d9");
    }
    @FXML
    public void exitsubmit(){
        click.setStyle("-fx-background-color:#005580");
    }
}