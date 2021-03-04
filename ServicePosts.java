/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;
import com.esprit.models.Posts;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


/**
 *
 * @author Rihab
 */
public class ServicePosts {
    Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(Posts p) {
        try {
            PreparedStatement pre=cnx.prepareStatement("INSERT INTO `alpha`.`posts` ( `contenuP`, `nbcmt`, `views`, `likes`, `dateP`) VALUES ( ?, ?, ?, ?,CURRENT_TIMESTAMP);");
              pre.setString(1, p.getContenuP());
              pre.setInt(2, p.getNbcmt());
              pre.setInt(3, p.getViews());
              pre.setInt(4, p.getLikes());
              

    pre.executeUpdate();
         //   System.out.println("Post ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 public void supprimer(Posts t) {
        try {
            String requete ="delete from posts where contenuP = '" + t.getContenuP() + "' ;";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
          //  System.out.println("Post supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(Posts p,String a) {
        try {
            int idp=afficheridP(p);
            String requete = "UPDATE posts SET contenuP= '" + a + "'  WHERE idP= '" + idp + "'  ;";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Posts modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    public List<Posts> afficher() {
        List<Posts> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM posts";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int idP=rs.getInt("idP");
               String contenuP=rs.getString("contenuP");
               
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               
             
               Date dateP=rs.getDate("dateP");
               
               
               Posts p=new Posts(idP, contenuP, nbcmt, views, likes,dateP);
                list.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public int afficheridP(Posts p) {

        try {
            String requete = "SELECT idp FROM posts where contenuP=  '" + p.getContenuP() + "' ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int idP=rs.getInt("idP");
            return idP;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return 0;
    }
    public int GetNbViews(Posts p){
        try {
            String requete = "SELECT views FROM posts where contenuP=  '" + p.getContenuP() + "' ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int views=rs.getInt("views");
            return views;
            }}
catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
return 0;
}

public int GetNbLikes(Posts p){
try{
String requete = "SELECT likes FROM posts where contenuP=  '" + p.getContenuP() + "' ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int likes=rs.getInt("likes");
              return likes;
            }}
catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
return 0;
}



public void  TauxDePart(Posts p){
double x=GetNbLikes(p);
double y= GetNbViews(p);
//System.out.println(x);
//System.out.println(y);
double taux=(x/y)*100;
System.out.println("Taux de Participation du Post est " + taux +" %");
}



 public boolean viewsUp(Posts p)  {
        try { int x =afficheridP(p);
        PreparedStatement pst=cnx.prepareStatement("UPDATE posts SET views= views + 1   WHERE idP='" + x + "'  ;");
            pst.executeUpdate();

            }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
        return true;
    }

 public boolean likesUp(Posts p)  {
    try{ int x =afficheridP(p);
        PreparedStatement pre=cnx.prepareStatement("UPDATE posts SET likes= likes + 1   WHERE idP='" + x + "'  ;");
            pre.executeUpdate();

        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
        return true;
    }

 public List<Posts> Trending() {
        List<Posts> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM posts where dateP > DATE_SUB(NOW(), INTERVAL 24 HOUR) and views >100 order by views desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int idP=rs.getInt("idP");
               String contenuP=rs.getString("contenuP");
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               Date dateP=rs.getDate("dateP");
               Posts p=new Posts(idP, contenuP, nbcmt, views, likes,dateP);
               list.add(p);
               System.out.println("Posts on Trending");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }


}
