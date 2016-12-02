/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import classe.Membre;
import classe.MembreUtil;
import classe.Restaurant;
import classe.Typecuisine;
import classe.Typemembre;
import classe.restaurantUtil;
import static java.util.Collections.list;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class gestionRestos {

     private restaurantUtil restoUti;
     private Typecuisine typecuisine;
     private String nom;
     private String description;
     private String siteweb;
     private int idMembre;
     private long prixmoyen;
     private String image;
     private String message;
    /**
     * Creates a new instance of ajouterClient
     */
    public gestionRestos() {
        restoUti = new restaurantUtil();
    }
    
    public String getMessage() {
        return message;
    }
    
    public void ajouterResto()
    {
       
        restoUti.ajouterResto(description, nom, siteweb, idMembre, image, typecuisine);
        message = "Le client a bien été ajouté!";
    }
    public List<Restaurant> ListResto(int id)
    {
         List<Restaurant> lstResto;
       
        lstResto =restoUti.listeRestaurant();
        
        return lstResto;
       
    }
    public void supprimerResto(int id)
    {
        message = restoUti.supResto(id);
    }

   
    public void setNom(String nom) {
        this.nom= nom;
    }
    public void setDescription(String Description) {
        this.description = Description;
    }
     public void setSiteWeb(String siteweb) {
        this.siteweb = siteweb;
    }
    public void setPrixMoyen(long prixmoyen) {
        this.prixmoyen = prixmoyen;
    }
     public void setImage(String image) {
        this.image = image;
    }
      public String getNom() {
        return this.nom ;
    }
    public String getDescription() {
       return this.description;
    }
     public String getImage() {
       return this.image;
    }
  
     public String  getSiteWeb() {
        return this.siteweb;
    }

}
