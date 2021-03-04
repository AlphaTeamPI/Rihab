/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.services;
import com.esprit.models.Comments;
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
public class ServiceComments {
   Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(Comments p) {
try {
        PreparedStatement pst=cnx.prepareStatement("INSERT INTO comments ( `idP`, `contenuC`, `dateC`, `reportC`, `iduser`) VALUES ( ?, ?, CURRENT_TIMESTAMP, ?, ?);");
        pst.setInt(1, p.getIdP());
        pst.setString(2, p.getContenuC());
        pst.setInt(3, p.getReportC());
        pst.setInt(4, p.getIduser());
        pst.executeUpdate();
    
   }
 
         catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

    public void supprimer(Comments x) {
        try {
            String requete =("delete from comments where contenuC = '" + x.getContenuC() + "' ;");
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
          //  System.out.println("Commentaire supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

   

  public void modifier(Comments c,String C) {
        try {
            int idc=AfficheridC(c);
            String requete = "UPDATE comments SET contenuC= '" + C + "'  WHERE idC= '" + idc + "'  ;";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commentaire modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


public List<Comments> afficher() {
        List<Comments> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM comments";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int idC=rs.getInt(1);
               int idP=rs.getInt("idP");
               String contenuC=rs.getString("contenuC");   
               
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               Date dateC=rs.getDate("dateC");
               
               
               Comments  c=new Comments(idC,idP,contenuC,reportC,iduser,dateC);
                list.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }



    



      public int AfficheridC(Comments c) {

        try {
            String requete = "SELECT idC FROM comments where contenuC=  '" + c.getContenuC() + "' ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
               int idC=rs.getInt("idC");
            return idC;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return 0;
    

}



}
