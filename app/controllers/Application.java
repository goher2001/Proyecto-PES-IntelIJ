package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.JPA;
import play.data.validation.*;
import java.util.*;

import models.*;

import javax.persistence.Query;

public class Application extends Controller {

    public static String usuariConnectat;
    @Before
    static void connectedUser() { usuariConnectat = session.get("user"); }
   public static void index(int i) {

       render();
    }
    public static void january(){
        renderTemplate("Application/January.html");
    }
    public static void february(){
        renderTemplate("Application/February.html");
    }
    public static void march(){
        renderTemplate("Application/March.html");
    }
    public static void april(){
        renderTemplate("Application/April.html");
    }
    public static void may(){
        renderTemplate("Application/May.html");
    }
    public static void june(){
        renderTemplate("Application/June.html");
    }
    public static void july(){
        renderTemplate("Application/July.html");
    }
    public static void august(){
        renderTemplate("Application/August.html");
    }
    public static void september(){
        renderTemplate("Application/September.html");
    }
    public static void october(){
        renderTemplate("Application/October.html");
    }
    public static void november(){
        renderTemplate("Application/November.html");
    }
    public static void december(){
        renderTemplate("Application/December.html");
    }
    public static void deleteuser() {renderTemplate("Application/DeleteUser.html");}

    public static void login(String fullname, String password){
        User us = User.find("byFullnameAndPassword", fullname, password).first();
        if(us != null){
            usuariConnectat = fullname;
            session.put("user", fullname);
            renderTemplate("Application/principal.html");
        }
        else
            renderText("Abans registrar-se");
    }

    public static void mycalendars(){
        User us = User.find("byFullname", usuariConnectat).first();
       String msg = "";
        if (us!=null) {
            Calendari c;
            for (int i = 0; i < us.calendaris.size(); i++) {
                c = us.calendaris.get(i);
                msg = msg + c.titulo + ", ";
            }
        }
       renderText("Your calendars are: " + msg);
    }
    public static void myevents(){
        User us = User.find("byFullname", usuariConnectat).first();
        String msg = "";
        if (us!=null) {
            Event e;
            for (int j = 0; j < us.events.size(); j++) {
                e = us.events.get(j);
                msg = msg + "\n Title: " + e.titulo + "\n Description: " + e.descripcion + "\n Start hour: " + e.HoraIN + "\n Finish hour:" + e.HoraOUT + "\n Date: " + e.Fecha + "\n" + "\n";
            }
        }
        renderText("Your events are: " + msg);
    }

    public static void Register (String fullname, String password){

            User m = User.find("byFullnameAndPassword",fullname,password).first();
            if (m==null){
                new User(fullname,password).save();
                renderTemplate("Application/principal.html");
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
            renderTemplate("Application/index.html");
        }
        renderText("Usuari no existeix");
    }
    public static void tancarSessio(){
        session.clear();
        renderTemplate("Application/index.html");
    }

}