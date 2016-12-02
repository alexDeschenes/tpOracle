/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alexandre
 */


public class commentaireUtil {
    Session session = null;

    public String ajouterCommentaire(String contenu, Date datecreation,Membre membre, int note,Restaurant restaurant)
    {
        String msgRetour ="";
        Commentaire comPourUserResto = null;
        List<Commentaire> listeCommentaires = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query com = session.createQuery("from Commentaire where membre = :membre AND restaurant = :restaurant");
            com.setInteger(membre.getIdmembre(),restaurant.getIdresto());
            listeCommentaires = com.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        if (listeCommentaires.size()!=0)
        {
            msgRetour= "L'utilisateur a déjà écris un commentaire pour ce restaurant";
        }
        else
        {
            Commentaire unCommentaire = new Commentaire();
            unCommentaire.setContenu(contenu);
            unCommentaire.setDatecreation(datecreation);
            unCommentaire.setMembre(membre);
            unCommentaire.setNote(note);
            unCommentaire.setRestaurant(restaurant);
   
            this.session = HibernateUtil.getSessionFactory().openSession();
        
        try{    
            tx = session.beginTransaction();
			
	    // l'ajout ne se fait pas car il reste des champs null qui ne doivent pas l'être
            
            session.saveOrUpdate(unCommentaire);
            tx.commit();
            
         this.session.close();
        
          msgRetour= "commentaire ajouté";
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        
       }
        return msgRetour;
        
    }
     public Commentaire getCommentaire(int id)
    {
        Commentaire unCommentaire = null;
        List<Commentaire> listeCommentaire = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query Com = session.createQuery("from Commentaire where Idcommentaire = :Idcommentaire");
            Com.setInteger(id, id);
            listeCommentaire = Com.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        if (listeCommentaire.size()!=0)
        {
            return listeCommentaire.get(0);
        }
        else
        {
          return null;
        }
    
    }
  
       
     public String  supCommentaire(int id)
    {
        String message;
        Transaction tx = null;
        List<Commentaire> listeCommentaire = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query Com = session.createQuery("from commentaires where id = :id");
            Com.setInteger(id, id);
            listeCommentaire = Com.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listeCommentaire.size()!=0)
        {
            session.delete(listeCommentaire.get(0));
            this.session.close();
            return "le commentaire a bien été supprimer";
            
        }
        else
        {
          this.session.close();
          return "Commentaire introuvable";
        }
        
        
      
    
    }
   
    
}
