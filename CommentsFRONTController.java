/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import static com.esprit.GUI.PostsFRONTController.selectedpost;
import com.esprit.models.Comments;
import com.esprit.models.Posts;
import com.esprit.services.ServiceComments;
import com.esprit.utils.DataSource;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class CommentsFRONTController implements Initializable {

    @FXML
    private Button Btn_Ajout;
    @FXML
    private TextField tfContenuC;
    @FXML
    private Button BtnMod;
    @FXML
    private Button Btn_Delete;
     ServiceComments sc = new ServiceComments();
    @FXML
    private TableView<Comments> cmtview;
    ObservableList<Comments> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_cont;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TextField tfContenuC1;
    @FXML
    private ComboBox<?> Btn_Sort;
    @FXML
    private TextField searchbar;
    @FXML
    private TableColumn<?, ?> col_likes;
    @FXML
    private TableColumn<?, ?> col_user;
    @FXML
    private TableColumn<?, ?> Idp;
    private JFXListView<Label> CommentsLists;
    @FXML
    private Button Btn_Posts;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InitTable();
        } catch (SQLException ex) {
            Logger.getLogger(CommentsFRONTController.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection cnx = DataSource.getInstance().getCnx();
          Posts p=PostsFRONTController.selectedpost;
          tfContenuC1.setText(p.getContenuP());
          
          
    }    
    
     private void InitTable() throws SQLException {
//         Posts c = pstview.getSelectionModel().getSelectedItem();
//       idp = c.getIdP().;

     
                  
                        List<Comments> listc=sc.Related(PostsFRONTController.selectedpost.getIdP());
                        ObservableList<Comments> data=FXCollections.observableArrayList(listc);
                        System.out.println(selectedpost.getIdP());
//                        oblist= (ObservableList<Comments>) sc.Related(selectedpost.getIdP());
                        col_cont.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
                        col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
                        col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
                        
                        cmtview.setItems(data);
                   
                }
            
         
            
            
     

   
        
        
        

    @FXML
    private void ModifierC(ActionEvent event) throws SQLException {
               Comments c = cmtview.getSelectionModel().getSelectedItem();
         sc.modifier(c,tfContenuC.getText());
      //  JOptionPane.showMessageDialog(null,"Commentaire M");
         InitTable();
    }

    @FXML
    private void SupprimerC(ActionEvent event) throws SQLException {
        Comments c = cmtview.getSelectionModel().getSelectedItem();
        sc.supprimer(c);

        
         InitTable();
    }
    
    private void Trie() throws SQLException {
        
        oblist= (ObservableList<Comments>) sc.Trieln();
        col_cont.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
        cmtview.setItems(oblist);
	}
    
    
    
    @FXML
    private void Trier(ActionEvent event) throws SQLException {
           if(Btn_Sort.getValue()=="Idp")
     {
        
         
         Trie();
     }
         
         else if(Btn_Sort.getValue()=="new")
     {
         
         
         InitTable();
     }
    }

    
    @FXML
    private void search(ActionEvent event) throws SQLException {
          String x= searchbar.getText();
          oblist= (ObservableList<Comments>) sc.RechercheC(x);
          col_cont.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
          col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
            
          col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
            
          cmtview.setItems(oblist);
    }
    
      public void ListComments(int idp) throws SQLException
    {
           ObservableList<Comments> data =FXCollections.observableArrayList();
        data= (ObservableList<Comments>) sc.Related(idp);
        
         for(int i=0 ; i<data.size() ; i++)
        {
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label("         "+data.get(i).getContenuC());
            CommentsLists.getItems().add(lbl);
            
        }
        CommentsLists.setExpanded(true);
        CommentsLists.depthProperty().set(1);
        CommentsLists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

  
      
       
    }
      
      
       @FXML
    private void AjouterC(ActionEvent event) throws SQLException {
//       Comments c = cmtview.getSelectionModel().getSelectedItem();
        sc.ajouter(new Comments(PostsFRONTController.selectedpost.getIdP(),tfContenuC.getText(), 0, 0));
//       JOptionPane.showMessageDialog(null,"Commentaire ajouté");
         InitTable();

    }
    
   /* @FXML
    private void ajoutercmt(ActionEvent event) throws SQLException {
          String contenuC = tfContenuC.getText();
          
          
   
          
            int index= PostsLists.getSelectionModel().getSelectedIndex();
           int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                        
                     id=l.get(index);
                     Posts p=new Posts(id);
                     Comments c = new Comments(contenuC,p,4);
                      sc.ajouter(c);
                      CommentsLists.getItems().clear();
                      ListComments(id);
          tfContenuC.setText("");
                     
                 
                 
                    }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
            
     
    */

    @FXML
    private void GoToPosts(ActionEvent event) throws IOException {
       
           FXMLLoader loader= new FXMLLoader(getClass().getResource("PostsFRONT.fxml"));
         Parent root= loader.load();
         Btn_Posts.getScene().setRoot(root);
    }
    
        
    }

