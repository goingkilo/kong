import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by kraghunathan on 5/23/17.
 */
public class ItemDAO extends AbstractDAO<Item> {

    public ItemDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Item findById(Long id) {
        return get(id);
    }

    public List<Item> getAllItems() {
        org.hibernate.Query q = namedQuery("from Item");
        return list( q);
    }

    public void create(Item item) {
        currentSession().persist(item);
    }

    public void update(Item item) {
        currentSession().update(item);
    }

    public void delete(Long id) {
        Item item = findById(id);
        currentSession().delete(item);

    }

}
