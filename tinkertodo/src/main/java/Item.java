import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    private long id;
    private String shortdesc;
    private String detail;
    private String date;

    private String project;
    private String owner;

    private String status;
    // due

    public Item() {}

    public Item(String shortdesc, String itemText, String date, String project, String owner, String status) {
        this.shortdesc = shortdesc;
        this.detail = itemText;
        this.date = date;
        this.project = project;
        this.owner = owner;
        this.status = status;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    @Column(name="shortdesc")
    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    @Column(name="item")
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name="happened_on")
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Column(name="status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="owner")
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name="project")
    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ",shortdesc='" + shortdesc +'\'' +
                ", detail='" + detail + '\'' +
                ", date='" + date + '\'' +
                ", project='" + project + '\'' +
                ", owner='" + owner + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (shortdesc != null ? !shortdesc.equals(item.shortdesc) : item.shortdesc != null) return false;
        if (detail != null ? !detail.equals(item.detail) : item.detail != null) return false;
        if (date != null ? !date.equals(item.date) : item.date != null) return false;
        if (project != null ? !project.equals(item.project) : item.project != null) return false;
        if (owner != null ? !owner.equals(item.owner) : item.owner != null) return false;
        return !(status != null ? !status.equals(item.status) : item.status != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));

        result = 31 * result + (shortdesc != null ? shortdesc.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Item copy() {
        return new  Item(this.shortdesc, this.detail, this.date, this.project, this.owner, this.status) ;
    }
}
