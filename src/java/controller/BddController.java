/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import DAO.*;
import service.User;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.*;
import javax.servlet.*;




/**
 *
 * @author faycal
 */
public class BddController extends MultiActionController {    
    private String login;
    private String pwd;
    private User utilisateur;
    private HttpSession session;
    public BddController() {}
   
    public ModelAndView  menu(HttpServletRequest request,
			HttpServletResponse response){
        
        login=request.getUserPrincipal().getName();
               
        //pwd=request.getParameter("password");
       
        utilisateur =new User(login);
        
        session=request.getSession();
        session.setAttribute("user", utilisateur);
        System.out.println ("nbuser="+User.getCompteur());
        return new ModelAndView("menu").addObject("user",utilisateur); 
        
    }
    public ModelAndView  logout(HttpServletRequest request,
			HttpServletResponse response){
        
        
        request.getSession().invalidate();
        
        
        return new ModelAndView("deconnexion"); 
        
    }
    
   public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
             
       return new ModelAndView("resultat").addObject("liste",new MagasinHelper().getAchats());
      
   }
 public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
     
       ModelAndView mv = new ModelAndView("form_inscription");
       
       mv.addObject("user",session.getAttribute("user"));
      mv.addObject("discount",new MagasinHelper().getDiscountCode());
      mv.addObject("code",new MagasinHelper().getZipCode());
      return mv;
   }
 public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
     ModelAndView mv = new ModelAndView("detail");
     mv.addObject("user",session.getAttribute("user"));
     mv.addObject("code",new MagasinHelper().getDiscountCode());
     mv.addObject("cpostaux",new MagasinHelper().getZipCode());
     mv.addObject("client",new MagasinHelper().getClient(request.getParameter("num")));
     return mv;
   }
 public ModelAndView find(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
     
       ModelAndView mv ;
       MagasinHelper requeteur = new MagasinHelper(); 
       if (requeteur.getClients(request.getParameter("nom")).isEmpty() || request.getParameter("nom").equals("%") ){
           mv= new ModelAndView("error");         
           mv.addObject("erreur", "0 enregistrement"); 
                  
                }
                else{
            mv= new ModelAndView("resultat");
                mv.addObject("liste",requeteur.getClients(request.getParameter("nom")));
               
                }
       
     mv.addObject("user",session.getAttribute("user"));
      return mv;
   }
 public ModelAndView formfind(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
      return new ModelAndView("recherche");
   }
  public ModelAndView achats(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
     
       ModelAndView mv = new ModelAndView("achats");
       
     mv.addObject("user",session.getAttribute("user"));
     System.out.println("num "+Integer.parseInt(request.getParameter("numero")));
      
      mv.addObject("achats",new MagasinHelper().getAchats(Integer.parseInt(request.getParameter("numero"))));
      return mv;
   }
   
    public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       String param1 = request.getParameter("nom");
        String param2 = request.getParameter("adresse");
        String param3 = request.getParameter("telephone");
        String param4 = request.getParameter("email");
        String param5 = request.getParameter("code_remise");
        String param6 = request.getParameter("CP");
        new MagasinHelper().insertCustomer(new MagasinHelper().getMaxId()+1, param5.charAt(0), param6);
       return new ModelAndView("confirm").addObject("confirm","enregistrement effectué");
   
   }
   public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
        new MagasinHelper().deleteCustomer(Integer.parseInt(request.getParameter("numero")));
       return new ModelAndView("confirm").addObject("confirm","suppression effectuée");
   
   }
    public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String param1 = request.getParameter("numero");
        String param2 = request.getParameter("code_remise");
        String param3 = request.getParameter("nom");
        String param4 = request.getParameter("adresse");
        String param5 = request.getParameter("telephone");
        String param6 = request.getParameter("email");
        String param7 = request.getParameter("CP");
        System.out.println(request.getParameter("CP"));
       
        new MagasinHelper().updateCustomer(Integer.valueOf(param1),param2.charAt(0),param3,param4,param5,param6,param7);
       return new ModelAndView("confirm").addObject("confirm","mise à jour effectuée");
   
   }
}
	
    

