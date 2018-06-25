package lego.todo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "notes")
@NamedQueries({@NamedQuery(name = "get_all_items",query = "select i from Item i order by id")})
public class Item {


	public static final String QUERY_NAME = "get_all_items";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String detail;

    public Item() {
    }

    public String getDetail() {

        return detail;
    }

    public void setDetail(String detail) {

        this.detail = detail;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }
}
