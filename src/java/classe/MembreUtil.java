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


public class MembreUtil {
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
            com.setInteger("membre", membre.getIdmembre());
            com.setInteger("restaurant", restaurant.getIdresto());
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
            
            membre.setCommentaires(unCommentaire);
	    
            session.saveOrUpdate(membre);
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
     public void supCommentaire(int id, int index)
    {
        Membre leMembre = this.getClientId(id);
        Transaction tx = null;
        if(leMembre != null)
        {
     
 
            leMembre.supCommentaire(index);
            try{    
            tx = session.beginTransaction();
          
	    
            session.saveOrUpdate(leMembre);
            tx.commit();
            
         this.session.close();
        
      
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        }
    }
       public Commentaire getCommentaire(int id, int index)
    {
        Membre leMembre = this.getClientId(id);
        Transaction tx = null;
        if(leMembre != null)
        {
     
            return leMembre.getCommentaires(index);
      
        }
        else
        {
            return null;
        }
        
    }
    
    

    public void ajouterClient(String nomUtil, String mdpConf, String email,String Mdp, Typemembre Type)
    {
        if(mdpConf.equals(Mdp) )
        {
            Transaction tx = null;
             try{  
           
            
            this.session = HibernateUtil.getSessionFactory().openSession();
        
       
            Membre SMembre = new Membre();
            SMembre.setNomutil(nomUtil);
            SMembre.setEmail(email);
            SMembre.setMpd(Mdp);
          
            SMembre.setRestoPref(1);
           SMembre.setTypemembre(Type);
           SMembre.setTypecuisinePref(1);
                  
            tx = session.beginTransaction();
			
	
            
            session.saveOrUpdate(SMembre);
            tx.commit();
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        
        this.session.close();
        } else
        {
            String test="Test";
        }
        
    }
     public Membre getClientConnexion(String nomUtil,String Mdp)
    {
        Membre unMembre = null;
        List<Membre> listeClients = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query mem = session.createQuery("from Membre where Nomutil = :nomUtil AND Mpd = :Mdp");
            mem.setString(nomUtil,Mdp);
            listeClients = mem.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        if (listeClients.size()!=0)
        {
            return listeClients.get(0);
        }
        else
        {
          return null;
        }
    
    }
      public Membre getClientId(int id)
    {
        Membre unMembre = null;
        List<Membre> listeClients = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query mem = session.createQuery("from Membre where id=:id");
            mem.setInteger(id,id);
            listeClients = mem.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        if (listeClients.size()!=0)
        {
            return listeClients.get(0);
        }
        else
        {
          return null;
        }
    
    }
    
    
   
    
}
