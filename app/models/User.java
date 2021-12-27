package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model {
    public String password;
    public String fullname;


    @OneToMany
    public List<Calendari> calendaris = new ArrayList();

    @OneToMany
    public List<Event> events = new ArrayList();
    public User( String fullname, String password) {

        this.password = password;
        this.fullname = fullname;
    }
}

