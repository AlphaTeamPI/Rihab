/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.models.Comments;
import com.esprit.models.Posts;
import com.esprit.services.ServicePosts;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static javafx.scene.input.KeyCode.C;
import javax.swing.table.DefaultTableModel;


/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class PostsBACKController implements Initializable {

    @FXML
    private ComboBox<?> Btn_Sortby;
    @FXML
    private TextField searchbar;
    @FXML
    private Button Btn_Comments;
    @FXML
    private Button Btn_Stats;
//    @FXML
//    private TableColumn<Posts,String> contenuP;
//    @FXML
//    private TableColumn<Posts, Integer> views;
//    @FXML
//    private TableColumn<Posts, Integer> likes;
//    @FXML
//    private TableColumn<Posts, Date> dateP;
    @FXML
    private Button Btn_Delete;
ObservableList<Posts> List = FXCollections.observableArrayList();
    ServicePosts sp = new ServicePosts();
    @FXML
    private TableView<Posts> postview;
    /**
     * Initializes the controller class.
     */
        ObservableList<Posts> oblist = FXCollections.observableArrayList();
    @FXML
    private Button Btn_PostsFRONT;
    @FXML
    private Button Home_btn;
    @FXML
    private TableView<Posts> pstview;
  
    @FXML
    private TableColumn<Posts,String> col_post;
    @FXML
    private TableColumn<Posts,Date> col_date;
    @FXML
    private TableColumn<Posts,Integer> col_likes;
    @FXML
    private TableColumn<Posts,Integer> col_views;
    @FXML
    private TableColumn<Posts,Integer> nbcmt;
      @FXML
    private TableColumn<Posts, Integer> col_report;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            PostTABLE();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(PostsBACKController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
//    public void PostTable(){
//         try {
//            oblist= (ObservableList<Posts>) sp.afficher();
//           //idP.setCellValueFactory(new PropertyValueFactory<>("idp"));
//            contenuP.setCellValueFactory(new PropertyValueFactory<>("contenuP"));
//            views.setCellValueFactory(new PropertyValueFactory<>("views"));
//            likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
//          // report.setCellValueFactory(new PropertyValueFactory<>("report"));
//            dateP.setCellValueFactory(new PropertyValueFactory<>("dateP"));
//
//            postview.setItems(oblist);
//            
//            
//        } catch (SQLException ex) {
//                  System.out.println(ex.getMessage());        }
//    }
     @FXML
     public void PostTABLE() throws SQLException  {
         List<Posts> lp=sp.afficher();
    ObservableList<Posts> LIST = FXCollections.observableArrayList(lp);   
           col_post.setCellValueFactory(new PropertyValueFactory<>("contenuP"));
             col_date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
             col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
             nbcmt.setCellValueFactory(new PropertyValueFactory<>("nbcmt"));
             col_views.setCellValueFactory(new PropertyValueFactory<>("views"));
             col_report.setCellValueFactory(new PropertyValueFactory<>("report"));

          
          pstview.setItems(LIST);
          
         }
    
    @FXML
    private void Trie() {
     
    }
     @FXML
    private void search(ActionEvent event) throws SQLException {
          
    }

    @FXML
    private void GoToComments(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("CommentsBACK.fxml"));
         Parent root= loader.load();
         Btn_Comments.getScene().setRoot(root);
    }

   @FXML
    private void gotoStats(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("stats.fxml"));
         Parent root= loader.load();
         Btn_Stats.getScene().setRoot(root);
         
        
    }
    
    
     @FXML
    private void GoToPostsFront (ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("PostsFRONT.fxml"));
         Parent root= loader.load();
         Btn_PostsFRONT.getScene().setRoot(root);
    }
    

    @FXML
    private void GoToHome (ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("HOMEfront.fxml"));
         Parent root= loader.load();
         Home_btn.getScene().setRoot(root);
    }
    
    @FXML
    private void SupprimerP(ActionEvent event) {
        
    }
   
         
  


   
    private void trier(ActionEvent event)  {
          
    } 

    

  

    
}
   
     
     
     
 
