/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.models;
import java.sql.Date;

/**
 *
 * @author Rihab
 */
public class Comments {
    private int idC,reportC,iduser;
    Posts c;
    int idP;
    private String contenuC;
    Date dateC;



public Comments(int idC,Posts p, String contenuC, Date dateC, int reportC, int iduser) {
        this.idC = idC;
        this.c=p;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
}
public Comments(int idC,int idP, String contenuC,int reportC, int iduser ) {
        this.idC = idC;
        this.idP=idP;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
}



public Comments(int idC,int idP, String contenuC,int reportC, int iduser,Date dateC) {
        this.idC = idC;
        this.idP=idP;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
}


public Comments(Posts c, String contenuC, Date dateC, int reportC, int iduser) {
        this.c = c;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
}


public Comments(int idP, String contenuC,int reportC, int iduser ) {
        this.idP=idP;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
}


/**********  setters   ************/

    public void setIdC(int idC) {
        this.idC = idC;
    }

     public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setIdP(Posts c) {
        this.c = c;
    }

    public void setReportC(int reportC) {
        this.reportC = reportC;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setContenuC(String contenuC) {
        this.contenuC = contenuC;
    }

    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }


/********** getters ************/


    public int getIdC() {
        return idC;
    }

    public int getIdP() {
        return idP;
    }
/*
    public int getIdP() {
        return c.getIdP();
    }
*/

    public int getReportC() {
        return reportC;
    }

    public int getIduser() {
        return iduser;
    }

    public String getContenuC() {
        return contenuC;
    }    

    public Date getDateC() {
        return dateC;
    }




public String toString() {
        return "Comments{" + "idC=" + idC + ", idP=" + idP + ", reportC=" + reportC + ", iduser=" + iduser + ", contenuC=" + contenuC + ", Date=" + dateC +'}';
    }










}
