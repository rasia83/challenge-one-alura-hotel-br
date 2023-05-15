package rasia.hotelalura.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {

	private static final long serialVersionUID = -2120817188094412950L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active = true;

	@Column(name = "check_in_date")
	@Temporal(TemporalType.DATE)
	private Date checkInDate;

	@Column(name = "check_out_date")
	@Temporal(TemporalType.DATE)
	private Date checkOutDate;

	@Column(name = "price", precision = 10, scale = 2)
	private BigDecimal price;

	@Column(name = "payment_option")
	private String paymentOption;

	public Reservation() {
	}

	public Reservation(Date checkInDate, Date checkOutDate, BigDecimal price, String paymentOption) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.price = price;
		this.paymentOption = paymentOption;
	}

	public Reservation(Long id, Date checkInDate, Date checkOutDate, BigDecimal price, String paymentOption) {
		this.id = id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.price = price;
		this.paymentOption = paymentOption;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", price="
				+ price + ", paymentOption=" + paymentOption + "]";
	}

}
