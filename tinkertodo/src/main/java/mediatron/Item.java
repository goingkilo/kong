package mediatron;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ceylon_tea")
public class Item {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long item_id;
	
	@OneToOne(optional=false,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id") 
	private User owner;
	
	private String text;
	private Status status;

	private String tags;

	@Column
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Column
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Column
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	
	

}
