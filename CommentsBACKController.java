/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.models.Comments;
import com.esprit.models.Posts;
import com.esprit.services.ServiceComments;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class CommentsBACKController implements Initializable {

    @FXML
    private ComboBox<?> Btn_Sortby;
    @FXML
    private Button Btn_Posts;
    @FXML
    private Button Btn_Stats;
    @FXML
    private TextField searchbar;
    @FXML
    private TableView<Comments> cmtTable;
    @FXML
    private TableColumn<?, ?> col_ContenuC;
    @FXML
    private TableColumn<?, ?> col_Likes;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?,? > col_reportc;
    @FXML
    private TableColumn<?,?> col_user;
    private Object Sortby;
   ServiceComments sc = new ServiceComments();
    @FXML
    private Button Btn_Delete;
    ObservableList<Comments> oblist = FXCollections.observableArrayList();
      
    private Button Btn_Stats1;
    @FXML
    private Button Home_btn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        CommentsTable();
       
     // Sortby.setItems(FXCollections.observableArrayList("Idp","Default"));
    // Sortby.setValue("Default");
        // TODO
    }  
   
     
    
     
     
     
     
     
     
     

    
    @FXML
    private void SupprimerC(ActionEvent event) throws SQLException {
        Object p1=cmtTable.getSelectionModel().getSelectedItem();
        if (!p1.equals(null)){ 
        sc.supprimer((Comments) p1);
       CommentsTable();}
    }
    
    

    

    @FXML
    private void gotoStats(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("stats.fxml"));
         Parent root= loader.load();
         Btn_Stats.getScene().setRoot(root);
         
        
    }

   @FXML
    private void search(ActionEvent event) {
          String x= searchbar.getText();
          oblist = (ObservableList<Comments>) sc.RechercheC(x);
          col_ContenuC.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
          col_reportc.setCellValueFactory(new PropertyValueFactory<>("reportC"));
          col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
          cmtTable.setItems(oblist);
    }

 @FXML
    private void CommentsTable() {
    try {
            oblist= (ObservableList<Comments>) sc.afficher();
            col_ContenuC.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
            col_Likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
            col_reportc.setCellValueFactory(new PropertyValueFactory<>("reportC"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
            cmtTable.setItems(oblist);
            
            
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());        }
	}
    
    
    @FXML
     private void Trie() {
        
        oblist = (ObservableList<Comments>) sc.Trieln();
         col_ContenuC.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
         col_Likes.setCellValueFactory(new PropertyValueFactory<>("Likes"));
         col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
         col_reportc.setCellValueFactory(new PropertyValueFactory<>("reportC"));
         col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
         cmtTable.setItems(oblist);
	}

     
      @FXML
    private void GoToPosts (ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("PostsBACK.fxml"));
         Parent root= loader.load();
         Btn_Posts.getScene().setRoot(root);
         

          
    
    }
     @FXML
    private void GoToHome (ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("HOMEfront.fxml"));
         Parent root= loader.load();
         Home_btn.getScene().setRoot(root);
    }
    

   
     
}
