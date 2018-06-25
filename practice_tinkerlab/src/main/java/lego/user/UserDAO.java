package lego.user;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by kraghunathan on 5/29/17.
 */
public class UserDAO  extends AbstractDAO<User> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getByEmail(String email) {
        Criteria  c = criteria();
        c.add(Restrictions.eq("email", email));
        User user = uniqueResult(c);
        return user;
    }
}
