package lego.feeds;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ArticleDAO extends AbstractDAO<Article> {

	public ArticleDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Article> getArticles() {
		Criteria cr = currentSession().createCriteria(Article.class);
		return cr.list();
	}

	public List<Article> getArticlesByAuthor(String author) {
		Criteria cr = currentSession().createCriteria(Article.class);
		cr.add(Restrictions.like("author", "%"+author+"%"));
		return cr.list();
	}

	public List<Article> getArticlesByLink(String link) {
		Criteria cr = currentSession().createCriteria(Article.class);
		cr.add(Restrictions.like("link", "%"+link+"%"));
		return cr.list();
	}

	public void save(List<Article> articles) {
		for( Article article : articles ) {
			persist(article);
		}
	}

	public void save(Article article) {
		persist(article);
	}
}
