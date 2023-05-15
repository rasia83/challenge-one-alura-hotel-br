package rasia.hotelalura.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login  implements Serializable  {
	// test >>  implements Serializable  to try in find by id

	private static final long serialVersionUID = -4480408421130831073L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active = true;
    
	private String username;
	private String password;

	public Login() {
	}

	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Login(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + "]";
	}

}
