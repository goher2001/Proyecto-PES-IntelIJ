package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Event extends Model {
    public String titulo;
    public String descripcion;
    public String HoraIN;
    public String HoraOUT;
    public String Fecha;
    @ManyToOne
    public User User;

    public Event(String titulo, String descripcion, String HoraIN, String HoraOUT, String Fecha){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.HoraIN = HoraIN;
        this.HoraOUT= HoraOUT;
        this.Fecha = Fecha;
    }
}
