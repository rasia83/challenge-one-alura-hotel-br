package rasia.hotelalura.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "guests")
public class Guest implements Serializable {

	private static final long serialVersionUID = -3607955042993130334L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active = true;
    
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	private String nationality;
	
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation; 
	


	public Guest() {
	}

	public Guest(String name, String lastName, Date birthday, String nationality, String phone,
			Reservation reservation) {
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
		this.nationality = nationality;
		this.phone = phone;
		this.reservation = reservation;
	}

	public Guest(Long id, String name, String lastName, Date birthday, String nationality, String phone,
			Reservation reservation) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
		this.nationality = nationality;
		this.phone = phone;
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", lastName=" + lastName + "]";
	}
	
	
	
}
