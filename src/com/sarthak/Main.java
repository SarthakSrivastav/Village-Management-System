package com.sarthak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ResourceBundle;

public class Main extends Application {
    static Statement st;
    static PreparedStatement pst;
    static Connection con;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainController.fxml"));
        primaryStage.setTitle("Village Management System ");
        primaryStage.setScene(new Scene(root, 1280, 655));
        primaryStage.setMinHeight(655.0);
        primaryStage.setMinWidth(1250.0);
        primaryStage.show();
    }


    public static void main(String[] args){
        try{
            ResourceBundle rb=ResourceBundle.getBundle("DatabaseInfo");
            Class.forName(rb.getString("driver"));
            Connection con= DriverManager.getConnection(rb.getString("url"),rb.getString("uname"),rb.getString("pass"));
            st=con.createStatement();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        launch(args);
    }
}
