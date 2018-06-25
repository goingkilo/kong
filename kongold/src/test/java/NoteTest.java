//
//import junit.framework.TestCase;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.Query;
//
//import  com.goingkilo.web.entity.Note;
//
//import java.util.List;
//
///**
// * Unit test for simple App.
// */
//public class NoteTest extends TestCase {
//
//
//	public void testNote() {
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		Note note = new Note("slavoj zizek is entertaining");
//		session.save(note);
//
//
//		session.getTransaction().commit();
//		session.close();
//	}
//
//	public void testQuery() {
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Note note = new Note("slavoj zizek is entertaining");
//		session.save(note);
//		Note note1 = new Note("slavoj zizek is a godless communist");
//		session.save(note1);
//		Note note2 = new Note("this is no fun");
//		session.save(note2);
//		session.getTransaction().commit();
//
//		Query query = session.createQuery("from Note ");
//		List list = query.list();
//		System.out.println( list.size() );
//		for( Object o : list ) {
//			System.out.println( ((Note)o).getMessage() );
//		}
//
//		session.close();
//	}
//}
