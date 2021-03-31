/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.models.Comments;
import com.esprit.models.Posts;
import com.esprit.services.ServicePosts;
import com.esprit.services.ServiceComments;
import com.esprit.utils.DataSource;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

import static com.restfb.Version.VERSION_3_2;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class PostsFRONTController implements Initializable {
//int idp;
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
    private Button Btn_Ajout;
    @FXML
    private Button BtnMod;
    @FXML
    private Button Btn_Delete;
    @FXML
    private ComboBox<String> Btn_Sort;
    @FXML
    private TextField searchbar;
    @FXML
    private TextField tfContenuP;
    ObservableList<Posts> list = FXCollections.observableArrayList();
    ServicePosts sp = new ServicePosts(); 
  ServiceComments sc = new ServiceComments();
   static Posts selectedpost; 
   static Stage stage;
//  Posts c = pstview.getSelectionModel().getSelectedItem();
       
   @FXML
    private TableColumn<Posts, Integer> col_report;
    
   
    ObservableList<Comments> oblist = FXCollections.observableArrayList();
   
 private TextField tfContenuC;
    
    
    @FXML
    private Button ViewP;
    @FXML
    private Button Btn_Ajout1;
    @FXML
    private TextArea addposttxt;
    @FXML
    private JFXListView<Label> PostsLists;
    @FXML
    private Button btn_likeP;
    @FXML
    private Button Btn_Posts;
//    @FXML
//    private TextField tfContenuC;
    @FXML
    private TableView<Comments> cmtview;
    @FXML
    private TableColumn<Comments,String> col_cont;
    @FXML
    private TableColumn<Comments,Date> col_date1;
    @FXML
    private TableColumn<Comments,Integer> col_likes1;
    
//    @FXML
//    private TableColumn<C, ?> col_user;
//    @FXML
//    private TableColumn<?, ?> Idp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addposttxt.setPromptText("Have Something in mind? Please Share it with us"); 
        addposttxt.getParent().requestFocus();
    /* int idp;
        Posts c = pstview.getSelectionModel().getSelectedItem();*/
    //   idp = c.getIdP();
    ServicePosts sp = new ServicePosts(); 
     Connection cnx = DataSource.getInstance().getCnx();
        try {
            PostTABLE();
        } catch (SQLException ex) {
            Logger.getLogger(PostsFRONTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    try {
        InitTable();
    } catch (SQLException ex) {
        Logger.getLogger(PostsFRONTController.class.getName()).log(Level.SEVERE, null, ex);
    }
     OpenAddComment();
     Btn_Sort.setItems(FXCollections.observableArrayList("Trending","New"));
     Btn_Sort.setValue("New");
        try {
            ListPosts();
        } catch (SQLException ex) {
            
        }
        
    //  ListComments(int idp);
      
    }   
    
    
    
    
    
    
    
    /**********************Posts***********************/
    
    
       public void ListPosts() throws SQLException
    {
        List<Posts> list=sp.afficher();
        ObservableList<Posts> data =FXCollections.observableArrayList(list);
//        data= (ObservableList<Posts>) sp.afficher();
       
        
         for(int i=0 ; i<data.size() ; i++)
        {
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
          // User x=sp.listuser(data.get(i).getIduser());
       Label lbl = new Label(""+data.get(i).getContenuP()+"\n\n"+ "       | views : "+data.get(i).getViews()+"       | likes : "+data.get(i).getLikes()+"     | Date : "+data.get(i).getDateP());
       PostsLists.getItems().add(lbl);    
       ObservableList ListView = PostsLists.getItems();
        
   // ListView<String> PostsLists = new ListView<String>();
       
            
            
        }
          
        PostsLists.setExpanded(true);
        PostsLists.depthProperty().set(1);
        PostsLists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
       
    }


    
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
    private void AjouterP(ActionEvent event) throws SQLException {
        ServicePosts sp = new ServicePosts(); 
         sp.ajouter(new Posts(tfContenuP.getText()));
         
         
         //postonFacebook(tfContenuP.getText());
       JOptionPane.showMessageDialog(null,"Post ajouté");
         PostTABLE();
    }
    @FXML
    private void GoToPosts (ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("PostsBACK.fxml"));
         Parent root= loader.load();
         Btn_Posts.getScene().setRoot(root);
    }
    
    
    
    
     @FXML
    private void AjouterF(ActionEvent event) throws SQLException {
       
         postonFacebook(tfContenuP.getText());
       JOptionPane.showMessageDialog(null,"Post ajouté sur Facebook");
         PostTABLE();
    }

    
    private void OpenAddComment(){
        stage=new Stage();
        pstview.setOnMouseClicked(event->{
            if(event.getClickCount()>=2 ){
                if(pstview.getSelectionModel().getSelectedItem()!=null)
                {
                    try {
                        selectedpost=pstview.getSelectionModel().getSelectedItem();
                        Parent root;
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("CommentsFRONT.fxml"));
                        root=loader.load();
                        Scene scene=new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PostsFRONTController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
    }
    
    
    
    @FXML
    private void ModifierP(ActionEvent event) throws SQLException {
        ServicePosts sp = new ServicePosts(); 
        Posts p = pstview.getSelectionModel().getSelectedItem();
         sp.modifier(p,tfContenuP.getText());
         PostTABLE();
    }

  
  @FXML
     private void ViewP(ActionEvent event) throws SQLException {
          Posts c = pstview.getSelectionModel().getSelectedItem();
        int idp=  sp.afficheridP(c);
            pstview.getItems().clear();
           
             
            
              PostTABLE();
             
        
    }
    @FXML
    private void SupprimerP(ActionEvent event) throws SQLException {
         Posts c = pstview.getSelectionModel().getSelectedItem();
        
        sp.supprimer(c);
        
       PostTABLE(); 
        
        
    }

    @FXML
    private void Trier() throws SQLException {
         list= (ObservableList<Posts>) sp.Trending();
       col_post.setCellValueFactory(new PropertyValueFactory<>("contenuP"));
             col_date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
             col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
             nbcmt.setCellValueFactory(new PropertyValueFactory<>("nbcmt"));
             col_views.setCellValueFactory(new PropertyValueFactory<>("views"));
             col_report.setCellValueFactory(new PropertyValueFactory<>("report"));
              pstview.setItems(list);
    }

    private void Trie(ActionEvent event) throws SQLException {
           if(Btn_Sort.getValue()=="Trending")
     {
        
         
         Trier();
     }
         
         else if(Btn_Sort.getValue()=="New")
     {
         
         
         PostTABLE();
     }
    }
    @FXML
    private void search(ActionEvent event) throws SQLException {
          String x= searchbar.getText();
          list= (ObservableList<Posts>) sp.Recherche(x);
         col_post.setCellValueFactory(new PropertyValueFactory<>("contenuP"));
             col_date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
             col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
             nbcmt.setCellValueFactory(new PropertyValueFactory<>("nbcmt"));
             col_views.setCellValueFactory(new PropertyValueFactory<>("views"));
            
             col_report.setCellValueFactory(new PropertyValueFactory<>("report"));
              pstview.setItems(list);
    
    
    }
     /************************* Comments **************************************/
    
     private void InitTable() throws SQLException {
//         Posts c = pstview.getSelectionModel().getSelectedItem();
//       idp = c.getIdP().;

          pstview.setOnMouseClicked(event->{
                if(pstview.getSelectionModel().getSelectedItem()!=null){
                    try {
                        selectedpost=pstview.getSelectionModel().getSelectedItem();
                        List<Comments> listc=sc.Related(selectedpost.getIdP());
                        ObservableList<Comments> data=FXCollections.observableArrayList(listc);
                        System.out.println(selectedpost.getIdP());
//                        oblist= (ObservableList<Comments>) sc.Related(selectedpost.getIdP());
                        col_cont.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
                        col_date1.setCellValueFactory(new PropertyValueFactory<>("dateC"));
                        col_likes1.setCellValueFactory(new PropertyValueFactory<>("likes"));
                        
                        cmtview.setItems(data);
                    } catch (SQLException ex) {
                        Logger.getLogger(PostsFRONTController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
          });
         
            
            
     }
    
    private void AjouterC(ActionEvent event) {
          int index = PostsLists.getSelectionModel().getSelectedIndex();
        Posts c = null;
      int idp=  sp.afficheridP(c);
      
         
          sc.ajouter(new Comments(tfContenuC.getText()));
         
        JOptionPane.showMessageDialog(null,"Commentaire ajouté");
       // ListComments(int idp);
    }

    private void ModifierC(ActionEvent event) throws SQLException {
         Comments c = cmtview.getSelectionModel().getSelectedItem();
         sc.modifier(c,tfContenuC.getText());
      //  JOptionPane.showMessageDialog(null,"Commentaire M");
         InitTable();
    }

    private void SupprimerC(ActionEvent event) throws SQLException {
         Comments c = cmtview.getSelectionModel().getSelectedItem();
         
        sc.supprimer(c);

        
        InitTable();
    }
    
//  private void isSelected(){
//
//pstview.setOnMouseClicked((MouseEvent event) -> {
//        if(event.getButton().equals(MouseButton.PRIMARY)){
//              Posts c = pstview.getSelectionModel().getSelectedItem();
//            System.out.println(c);
//                    idp=c.getIdP();
//            cmtview.getItems().clear();
//            System.out.println(idp);
//            try {
//                InitTable();
//            } catch (SQLException ex) {
//                Logger.getLogger(PostsFRONTController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//            
//        }
//    });
//    }

 
    
    
    
    
    
    
    
    
    
    
    
    
    /***********************************Facebook******************************/
    
    
    public void postonFacebook(String p){
        String accessToken="EAA4XQXhZA2fIBAGGk9FImuDbJCpytRYXvtyIPou4wFKw4UMyD11NtbKjHuU7twZAlSZBZCi3W7IpkLIaFONCJ1j3DSBm0PvKhoy6Ioxpq4PTv3idmn4M0GZAlxXLQ8ZCG4T3cW0AZAIg6UCMmcodEri3uvAVZCHeZAKlQoGa3usAnH8tjXCTn4WDoPjpJZChTLXpcZD";
         DefaultFacebookClient fbClient=new DefaultFacebookClient(accessToken, VERSION_3_2);
        
        FacebookType response = fbClient.publish("107494964499210/feed",FacebookType.class, Parameter.with("message",p));
        
        System.out.println("fb.com/"+response.getId());


           
    }
  /*  private void postonFb(ActionEvent event) {
          String contenuP = addposttxt.getText();
       String accessToken="EAA4XQXhZA2fIBAL5MpHpkNYnFeoR2YfGwmZCMdYqkiZAZCYn1hElbwq2K2OVTv7sOrXuu76mKOLfcoYR4wmZBRmgRohDT4hTQ9ZCVZBtF4CdZCNLxhAN50gzgPvWi8yqv72xv4p8wIyEsCQo4kuAVZAfcQ4ESBCcJJAx42KcenCWj3V2YGgGxT9RbJ9fstr2me0vssTB0kOkIbgZDZD";
        DefaultFacebookClient fbClient=new DefaultFacebookClient(accessToken);
        
        FacebookType response = fbClient.publish("107494964499210/feed",FacebookType.class, Parameter.with("message",contenuP));
        
        System.out.println("fb.com/"+response.getId());

    }*/

    
    
  
     
     
    /*public void ListComments(int idp) throws SQLException
    {
           ObservableList<Comments> data =FXCollections.observableArrayList();
        data= (ObservableList<Comments>) sc.Related(idp);
        
         for(int i=0 ; i<data.size() ; i++)
        {
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label("         "+data.get(i).getContenuC());
            CommentsLists.getItems().add(lbl);
             ObservableList ListView = CommentsLists.getItems();
            
        }
        CommentsLists.setExpanded(true);
        CommentsLists.depthProperty().set(1);
        CommentsLists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

  
      
       
    }

  
 
 
    }*/
    

    
    
    
    



    @FXML
    private void LikePost(ActionEvent event) {
    }

  
    

  
    }