import play.test.*;
import play.jobs.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job{
    public void doJob(){
         if(User.count() == 0) {
            User u1 = new User("Usuari1", "pwd").save();
            Event e1 = new Event("Aniversari", "descripcio", "10:00", "12:00", "12/05/22").save();
            Calendari c1 = new Calendari("Calendari1").save();
            Calendari c2 = new Calendari("Calendari2").save();
            u1.calendaris.add(c1);
            u1.calendaris.add(c2);
            u1.events.add(e1);

            c1.User = u1;
            c1.save();
            c2.User = u1;
            c2.save();
            e1.User = u1;
            e1.save();

            u1.save();
        }
    }
}
