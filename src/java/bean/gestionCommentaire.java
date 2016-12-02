/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import classe.Commentaire;
import classe.Membre;
import classe.MembreUtil;
import classe.Restaurant;
import classe.Typecuisine;
import classe.Typemembre;
import classe.commentaireUtil;
import classe.restaurantUtil;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class gestionCommentaire {

     private commentaireUtil comUtil;
     private Membre membre;
     private Restaurant restaurant;
     private String contenu;
     private Date datecreation;
     private int note;
     private String message;
    /**
     * Creates a new instance of ajouterClient
     */
    public gestionCommentaire() {
        comUtil = new commentaireUtil();
    }
    
    public String getMessage() {
        return message;
    }
    
    public void ajouterCommentaire()
    {
       
        comUtil.ajouterCommentaire(contenu, datecreation, membre, note, restaurant);
        
        message = "Le commentaire a bien été ajouté!";
    }
    
    public void supprimerResto(int id)
    {
        message = comUtil.supCommentaire(id);
    }
      public Commentaire GetCommentaire(int id)
    {
        return  comUtil.getCommentaire(id);
    }
   
    public void setNote(int note) {
        this.note= note;
    }
    public void setContenu(String Contenu) {
        this.contenu= Contenu;
    }
     public void setdateCreation(Date dateCreation) {
        this.datecreation = dateCreation;
    }
    public void setResto(Restaurant resto) {
        this.restaurant= resto;
    }
     public void setMembre(Membre mem) {
        this.membre = mem;
    }
      public int getNote() {
        return this.note ;
    }
    public String getContenu() {
       return this.contenu;
    }
     public Date getdatecreation() {
       return this.datecreation;
    }
  
     public Membre  getMembre() {
        return this.membre;
    }
    public Restaurant  getRestaurants() {
        return this.restaurant;
    }

}
