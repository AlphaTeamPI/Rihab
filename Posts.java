/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.models;
import java.sql.*;
/**
 *
 * @author Rihab
 */
public class Posts {
private int idP,nbcmt,views,likes,report,iduser;
    private String contenuP;
    Date dateP;


 
    /************ constructors **********/
    
    
    
    public Posts() {
    }  

    public Posts(int idP) {
        this.idP = idP;
    }

    
    public Posts(String contenuP, int nbcmt, int views, int likes) {
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
            
        this.contenuP = contenuP;
        this.dateP = dateP;
    }
public Posts(int idP,String contenuP, int nbcmt, int views, int likes, Date dateP) {
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
        this.idP= idP;    
        this.contenuP = contenuP;
        this.dateP = dateP;
    }

   
    
/**********  setters   ************/
    public void setIdP(int idP) {
        this.idP = idP;
    }
    
    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public void setNbcmt(int nbcmt) {
        this.nbcmt = nbcmt;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setReport(int report) {
        this.report = report;
    }

  
    public void setContenuP(String contenuP) {
        this.contenuP = contenuP;
    }
    
    
    /********** getters ************/

    public int getIdP() {
        return idP;
    }
    
    public Date getDateP() {
        return dateP;
    }

    public int getNbcmt() {
        return nbcmt;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public int getReport() {
        return report;
    }

    
    public String getContenuP() {
        return contenuP;
    }
    /************** To String *****************/
    
     @Override
    public String toString() {
        return "Posts{" + "idP=" + idP + ", nbcmt=" + nbcmt + ", views=" + views + ", likes=" + likes + ", report=" + report +  ", contenuP=" + contenuP + ", Date=" + dateP +'}';
    }
    



}
