package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.JPA;
import play.data.validation.*;
import java.util.*;

import models.*;

import javax.persistence.Query;

public class Application extends Controller {


   public static void index(int i) {

       render();
    }
    public static void login(String fullname, String password){
        User us = User.find("byFullnameAndPassword", fullname, password).first();
        if(us != null){
            renderText("login " + fullname);
        }
        else
            renderText("Abans registrar-se");
    }

    public static void Register (String fullname, String password){

            User m = User.find("byFullnameAndPassword",fullname,password).first();
            if (m==null){
                new User(fullname,password).save();
                renderText("Usuari donat d'alta");
            }else{
                renderText("Aquest usuari ja ha estat donat d'alta");
            }
        }

        public static void accioLogin(){
       renderTemplate("Application/index.html");
        }

    public static void accioRegister(){
        renderTemplate("Application/register.html");
    }


    public static void DonarDeBaixaCalendari(String titulo){
        Calendari c = Calendari.find("byTitulo", titulo).first();
        if(c!=null){
            if(c.User!=null){
                c.User.calendaris.remove(c);
                c.User.save();
            }
            c.delete();
            renderText(titulo + " donat de baixa");
        }
        renderText("Calendari no existeix");
    }
    public static void DonarDeBaixaEvent(String titulo){
        Event e = Event.find("byTitulo", titulo).first();
        if(e!=null){
            if(e.User!=null){
                e.User.events.remove(e);
                e.User.save();
            }
            e.delete();
            renderText(titulo + " donat de baixa");
        }
        renderText("Event no existeix");
    }
    public void DonarDeBaixaUsuari(String fullname){
        User u = User.find("byFullname", fullname).first();
        if (u!=null){
            Calendari c;
            for(int i=0; i<u.calendaris.size(); i++){
                c = u.calendaris.get(i);
                c.User = null;
                c.save();
            }
            Event e;
            for(int j=0; j<u.events.size(); j++){
                e = u.events.get(j);
                e.User = null;
                e.save();
            }
            u.delete();
            renderText(fullname + " donat de baixa");
        }
        renderText("Usuari no existeix");
    }

}