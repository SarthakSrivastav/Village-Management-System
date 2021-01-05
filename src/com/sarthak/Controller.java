package com.sarthak;

import com.sarthak.dataModel.CommonProcedureForAll;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller extends Main {
    @FXML
    private RadioButton r1, r2, r3;
    @FXML
    private TextField id, pass, post, village;
    @FXML
    private Button login;
    @FXML
    public void initialize() {
    }

    @FXML
    public  void selectButtonOne() {
        id.setDisable(false);
        pass.setDisable(false);
        post.setDisable(true);
        village.setDisable(true);
    }

    @FXML
    public void selectButtonTwo() {
        id.setDisable(false);
        pass.setDisable(false);
        post.setDisable(false);
        village.setDisable(false);
    }

    @FXML
    public void selectButtonThree() {
        post.setDisable(true);
        village.setDisable(true);
        id.setDisable(true);
        pass.setDisable(true);
    }

    @FXML
    public void handleLogin() {
        CommonProcedureForAll common = new CommonProcedureForAll();
        HomeClassController loginType=new HomeClassController();
        try {
            if (r1.isSelected()) {
                String ownerID = id.getText();
                String ownerPassword = pass.getText();
                if (ownerID.isEmpty() || ownerPassword.isEmpty()) {
                    common.alertMessage("warning", "Empty", "Please Enter ID And Password Both Before Login..!");
                } else {
                    if (common.checkLength(ownerID, 12)) {
                        ResultSet rsOwner = st.executeQuery("select id,password,name from owner;");
                        rsOwner.next();
                        if ((rsOwner.getString(1)).equals(ownerID) && (rsOwner.getString(2)).equals(ownerPassword)) {
                            Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                            Stage hoemStage = new Stage();
                            hoemStage.setTitle("Owner Login");
                            hoemStage.setScene(new Scene(root, 1280, 655));
                            hoemStage.setMinHeight(655.0);
                            hoemStage.setMinWidth(1250.0);
                            hoemStage.show();
                            allSetPrevious();
                            loginType.setLoginType(1,0,0);
                        } else {
                            common.alertMessage("error", "Wrong Details", "It Seems Like You Are Not The Owner..!");
                            id.clear();
                            pass.clear();
                        }
                    } else {
                        common.setCheckLength(0);
                        common.alertMessage("warning", "Wrong", "Invalid ID Please Check It Again..!");
                        id.clear();
                    }
                }
            } else if (r2.isSelected()) {
                String adminID = id.getText();
                String adminPassword = pass.getText();
                String postName = post.getText();
                String villageName = village.getText();
                if (adminID.isEmpty() || adminPassword.isEmpty() || postName.isEmpty() || villageName.isEmpty()) {
                    common.alertMessage("warning", "Empty", "Please Provide All The Details Given Below Before Login..!");
                } else {
                    if (common.checkLength(adminID, 12) && common.isLower(postName) && common.isLower(villageName)) {
                        ResultSet rsAdmin = st.executeQuery("select post_name,village_name from admininfo;");
                        if (rsAdmin.next() == true) {
                            ArrayList villageList = new ArrayList();
                            do {
                                villageList.add(rsAdmin.getString(1) + "_" + rsAdmin.getString(2));
                            } while (rsAdmin.next());
                            if (villageList.contains(postName + "_" + villageName)) {
                                ResultSet rsCheckIDPASS = st.executeQuery("select aadhar_no,password from admininfo where post_name='" + postName + "'and village_name='" + villageName + "';");
                                rsCheckIDPASS.next();
                                if ((rsCheckIDPASS.getString(1) + "_" + rsCheckIDPASS.getString(2)).equals(adminID + "_" + adminPassword)) {
                                    Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                                    Stage hoemStage = new Stage();
                                    ResultSet adminName=st.executeQuery("select first_name,last_name from admininfo where aadhar_no = '"+adminID+"';");
                                    adminName.next();
                                    hoemStage.setTitle("Admin '" +adminName.getString(1)+" "+adminName.getString(2)+"' Login");
                                    hoemStage.setScene(new Scene(root, 1280, 655));
                                    hoemStage.setMinHeight(655.0);
                                    hoemStage.setMinWidth(1250.0);
                                    hoemStage.show();
                                    allSetPrevious();
                                    loginType.setLoginType(0,1,0);

                                    HeadOfFamilyController head=new HeadOfFamilyController();
                                    familyMembersController family=new familyMembersController();
                                    DeleteRecordFromVillageController delete=new DeleteRecordFromVillageController();
                                    AddResourcesController resource=new AddResourcesController();

                                    head.getPrePostAndVillage(postName,villageName);
                                    family.getFamilyMemberPostAndVillage(postName,villageName);
                                    delete.getPrePostAndVillage(postName,villageName);
                                    resource.getPrePostAndVillage(postName,villageName);

                                    hoemStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                        public void handle(WindowEvent we) {
                                            head.getPrePostAndVillage(null,null);
                                            family.getFamilyMemberPostAndVillage(null,null);
                                            delete.getPrePostAndVillage(null,null);
                                            resource.getPrePostAndVillage(null,null);
                                        }
                                    });
                                } else {
                                    common.alertMessage("information", "Wrong Details", "Incorrect Admin Details... Please Check Your ID And Password..!");
                                    id.clear();
                                    pass.clear();
                                }
                            } else {
                                common.alertMessage("information", "Not Registered", "It Seems Like This Village Is Not Registered Yet..!");
                                post.clear();
                                village.clear();
                            }
                            villageList.clear();
                        } else {
                            common.alertMessage("information", "Empty Application", "Yet.. There Is Not Any Registered Village On This Portal..!");
                            allSetPrevious();
                        }
                    } else {
                        if (common.getCheckLength() == 1) {
                            common.alertMessage("warning", "Wrong", "Invalid ID Please Check It Again..!");
                            common.setCheckLength(0);
                            id.clear();
                        }
                        if (common.getCheckCase() == 1) {
                            common.alertMessage("warning", "Use Lower Case...", "Please Fill All Details In Lower Case Without Space (Use '_' For Spaces)..!");
                            common.setCheckCase(0);
                        }
                    }
                }
            } else if (r3.isSelected()) {
                Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                Stage hoemStage = new Stage();
                hoemStage.setTitle("General Login");
                hoemStage.setScene(new Scene(root, 1280, 655));
                hoemStage.setMinHeight(655.0);
                hoemStage.setMinWidth(1250.0);
                hoemStage.show();
                allSetPrevious();
                loginType.setLoginType(0,0,1);
            } else {
                common.alertMessage("warning", "Select Login Type", "Please Choose Your Login Type..!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            common.alertMessage("error", "Error", "Something Went Wrong Please Check Your Stack Trace...!");
        }
    }

    public void allSetPrevious() {
        id.clear();
        pass.clear();
        post.clear();
        village.clear();
        id.setDisable(false);
        pass.setDisable(false);
        post.setDisable(false);
        village.setDisable(false);
        r1.setSelected(false);
        r2.setSelected(false);
        r3.setSelected(false);
    }
    @FXML
    public void enterLogin(){
        login.setStyle("-fx-background-color:#2d436c");
    }
    @FXML
    public void exitLogin(){
        login.setStyle("-fx-background-color:#6f8cc3");
    }
}
