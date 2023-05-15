package rasia.hotelalura.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import rasia.hotelalura.entity.Guest;
import rasia.hotelalura.entity.Reservation;


public class ReservationDAO {
	
	private EntityManager em;

	public ReservationDAO(EntityManager em) {
		this.em = em;
	}
	
	public void save(Reservation reservation) {
		if(reservation.getId() == null)
			this.em.persist(reservation);
		else
			update(reservation);
	}
	
	public void update(Reservation reservation) {
		this.em.merge(reservation);
	}
	
	public void delete(Reservation reservation) {
		reservation.setActive(false);
		this.em.merge(reservation);
		
		// setar .setActive(false) para o hóspede relacionado
		GuestDAO guestDAO = new GuestDAO(em);
		List<Guest> guests = guestDAO.findByReservation(reservation);
		for (Iterator<Guest> iterator = guests.iterator(); iterator.hasNext();) {
			Guest guest = (Guest) iterator.next();
			guest.setActive(false);
			this.em.merge(guest);
		}
	}
	
	public void deleteById(Long id) {
		delete(em.find(Reservation.class, id));
	}
	
	public Reservation findById(Long id) {
		return em.find(Reservation.class, id);
	}
	
	public List<Reservation> findAll() {
		String jpql = "SELECT r FROM Reservation r WHERE r.active = true";
		return em.createQuery(jpql, Reservation.class).getResultList();
	}
	
	public List<Reservation> findAny(String search) {
		
		if(search.length() < 1) {
			String jpql = "SELECT r FROM Reservation r WHERE r.active = true ";
			return em.createQuery(jpql, Reservation.class).getResultList();
		} else {
			String jpql = "SELECT r FROM Reservation r WHERE r.active = true AND "
					+ "(r.id = :id OR r.price = :price OR "
					+ "LOWER(r.paymentOption) LIKE CONCAT('%', LOWER(:paymentOption), '%') OR "
					+ "r.checkInDate = :checkInDate  OR r.checkOutDate = :checkOutDate )";
			TypedQuery<Reservation> query = em.createQuery(jpql, Reservation.class);

			try {
				query.setParameter("id", Long.parseLong(search));
			} catch (NumberFormatException e) {
				query.setParameter("id", 0L);
			}
			
			try {
				query.setParameter("price", new BigDecimal(search));
			} catch (NumberFormatException e) {
				query.setParameter("price", new BigDecimal(-1));
			}
			
		    query.setParameter("paymentOption", search);

	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
		    try {
		        query.setParameter("checkInDate", sdf.parse(search), TemporalType.DATE);
		    } catch (Exception e) {
		        query.setParameter("checkInDate", null);
		    }
	        
		    try {
		        query.setParameter("checkOutDate", sdf.parse(search), TemporalType.DATE);
		    } catch (Exception e) {
		        query.setParameter("checkOutDate", null);
		    }

			return query.getResultList();	
		}
	}
}
