package lego.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.Principal;
import java.util.Date;

@Entity
@Table(name = "user_account")
public class User implements Principal {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private Date last_login;
	@Column
	private String login_status; // yes no reset disabled
	@Column
	private String role;


	@Override
	public String getName() {
		return email;
	}


	public User() {
	}

	public User(String email, Date last_login, String login_status, String password, String role) {

		this.email = email;
		this.last_login = last_login;
		this.login_status = login_status;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
