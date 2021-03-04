/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Posts;
import com.esprit.services.ServicePosts;
import com.esprit.models.Comments;
import com.esprit.services.ServiceComments;

/**
 *
 * @author Rihab
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     ServicePosts sp = new ServicePosts();
     Posts p= new Posts("post 1", 11,688,14);
     Posts p1= new Posts("post 2", 22,107,122);
     Posts p2= new Posts("post 3", 33,13,23);
     Posts p3= new Posts("post 4", 44,42,44);

      sp.ajouter(p);
        sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter(p3);
        sp.afficher().forEach(System.out::println);
        System.out.println("*****************************");
        
        
       /*System.out.println("**************SUPPRESSION***************");
        System.out.println("Suppression");
        sp.supprimer(p3);
        sp.afficher().forEach(System.out::println);*/

     /*   System.out.println("**************MODIFY***************");
        
        sp.modifier(p2,"Testinnnnng");
 sp.afficher().forEach(System.out::println);*/


/*System.out.println("**************TAUXPARTC***************");
       sp.TauxDePart(p1);*/

    /*System.out.println("**************LIKES ND VIEWS***************");

     sp.likesUp(p1);

        sp.viewsUp(p1);
 sp.afficher().forEach(System.out::println);

  sp.TauxDePart(p1); */

       System.out.println("**********TRENDING**********"); 

     

        sp.Trending().forEach(System.out::println);
  System.out.println("**********TRENDING END**********"); 

    
    
/*
     ServiceComments sc = new ServiceComments();
     Comments c0= new Comments(sp.afficheridP(p1),"Comment 1", 11,12);
     Comments c1= new Comments(sp.afficheridP(p2),"Comment 2", 22,2);
     Comments c2= new Comments(sp.afficheridP(p3),"Comment 3", 33,13);
     Comments c3= new Comments(sp.afficheridP(p1),"Comment 4", 44,42);
*/
/*
       sc.ajouter(c0);
        sc.ajouter(c1);
        sc.ajouter(c2);
        sc.ajouter(c3);*/
  /*       System.out.println("***********AJOUT************");
        sc.afficher().forEach(System.out::println);
        System.out.println("***************************");
*/

        /*
         System.out.println("***********SUPPRESSION************");
        sc.supprimer(c3);
        
        sc.afficher().forEach(System.out::println);*/

/*
        System.out.println("***************MODIFY**************");
        
        sc.modifier(c2,"LAA");
        sc.afficher().forEach(System.out::println);*/
}

}