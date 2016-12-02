/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

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

    public void ajouterClient(String nomUtil, String email,String Mdp, Typemembre Type)
    {
        
            Membre SMembre = new Membre();
            SMembre.setNomutil(nomUtil);
            SMembre.setEmail(email);
            SMembre.setMpd(Mdp);
          
            SMembre.setRestoPref(1);
           SMembre.setTypemembre(Type);
           SMembre.setTypecuisinePref(1);
                  
            Transaction tx = null;
            this.session = HibernateUtil.getSessionFactory().openSession();
        
        try{    
            tx = session.beginTransaction();
			
	    // l'ajout ne se fait pas car il reste des champs null qui ne doivent pas l'être
            
            session.saveOrUpdate(SMembre);
            tx.commit();
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        
        this.session.close();
        
    }
     public Membre getClient(String nomUtil,String Mdp)
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
    
   
    
}
