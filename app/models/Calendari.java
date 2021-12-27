package models;
import javax.persistence.*;

import play.db.jpa.*;
@Entity
public class Calendari extends Model{
    public String titulo;
    @ManyToOne
    public User User;

    public Calendari(String titulo){
        this.titulo = titulo;

    }

}
