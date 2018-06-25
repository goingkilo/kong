package lego.todo;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemDAO extends AbstractDAO<Item> {

	public ItemDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Item findById(Long id) {
		return get(id);
	}

	public long create(Item item) {
		return persist(item).getId();
	}

	public long update(Item item) {return persist(item).getId();}

	public void delete(Item item) {
		currentSession().delete(item);
	}

	public List<Item> findAll() {
		return list(namedQuery(Item.QUERY_NAME));
	}

}
