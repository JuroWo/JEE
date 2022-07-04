/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;
import java.sql.*;

/**
 *
 * @author faycal
 */
public class MagasinHelper {
    Session session = null;

    public List<String> getColonnes() {
        return colonnes;
    }
    ArrayList <String> colonnes=new ArrayList();

    public MagasinHelper() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List getClients(){
        List <Customer> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("select a.customerId, a.name, a.addressline1,a.addressline2,a.zip,b.rate from Customer a, DiscountCode b where a.discountCode=b.discountCode");
            //Query q=session.createQuery("from Customer");
            resultat=q.list();
                    
            
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}
    public List getClients(String name){
        List resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           Query q=session.createQuery("select a.customerId, a.name, a.addressline1,a.addressline2,a.zip,b.rate from Customer a, DiscountCode b where a.discountCode=b.discountCode and a.name like :_name");
            //Query q=session.createQuery("from Customer");
            q.setString("_name",name);
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}

public List getDiscountCode(){
List resultat=null;
        Transaction tx=null;
        try{
           if(!session.isOpen())
                session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("select a.discountCode from DiscountCode a");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
           if (session.isOpen())session.close();
          
        }
      
    return resultat;


}
public List getMicroMarket(){
List <MicroMarket> resultat=null;
        Transaction tx=null;
        try{
            //if(!session.isOpen())
                session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("from MicroMarket");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
           if (session.isOpen())session.close(); 
        
        }
        return resultat;
}
public List getZipCode(){
List resultat=null;
        Transaction tx=null;
        try{
            //if(!session.isOpen())
                session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("select a.zipCode from MicroMarket a");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
           if (session.isOpen())session.close(); 
        
        }
        return resultat;
}
public void insertCustomer  (int _customerId, char _discountCode, String _zip) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Customer a =new Customer(_customerId,_discountCode,_zip);
             session.save(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

public void updateCustomer  (int _customerId,char _discountCode, String _name, String _adress, String _phone,String _email, String _zip){
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             
             Customer a =new Customer(_customerId,_discountCode,_name, _adress, _phone,_email, _zip);
            
             session.update(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}
public Customer getClient(int id){
   
   
   Customer client=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery(" from Customer a  where a.customerId =:_id");
           q.setInteger("_id", id);
           client=(Customer)q.list().iterator().next();
      }
       catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return client;
}
public Customer getClient(String name){
   
   
   Customer client=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery(" from Customer a  where a.customerId =:_name");
           q.setString("_name", name);
           client=(Customer)q.list().iterator().next();
      }
       catch (Exception e) {
        System.out.println("erreur"+e);
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return client;
}
public void deleteCustomer  (int _id) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Query q=session.createQuery(" from Customer a  where a.customerId =:_id");
             q.setInteger("_id",_id);
             session.delete((Customer)q.list().iterator().next());
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}
 public List<PurchaseOrder> getAchats(int id){
        List resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           Query q=session.createQuery(" from PurchaseOrder  a where a.customerId=:_id");
           q.setInteger("_id", id);
            resultat=(List<PurchaseOrder>)q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}

public int getMaxId(){
     Transaction tx=null;
     int resultat=-1;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           Query q=session.createQuery("select MAX(a.customerId) from Customer a");
           
            resultat=(int)q.list().iterator().next();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
    
}
}
