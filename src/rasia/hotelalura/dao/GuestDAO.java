package rasia.hotelalura.dao;


import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import rasia.hotelalura.entity.Guest;
import rasia.hotelalura.entity.Reservation;

public class GuestDAO {
	
	private EntityManager em;

	public GuestDAO(EntityManager em) {
		this.em = em;
	}
	
	public void save(Guest guest) {
		this.em.persist(guest);
	}
	
	public void update(Guest guest) {
		this.em.merge(guest);
	}
	
	public void delete(Guest guest) {
		guest.setActive(false);
		this.em.merge(guest);
	}
	
	public void deleteById(Long id) {
		delete(em.find(Guest.class, id));
	}

	public Guest findById(Long id) {
		return em.find(Guest.class, id);
	}

	public List<Guest> findByReservation(Reservation reservation) {
		String jpql = "SELECT g FROM Guest g WHERE g.reservation = :reservation";
		return em.createQuery(jpql, Guest.class)
				.setParameter("reservation", reservation)
				.getResultList();
	}

	public List<Guest> findAll() {
		String jpql = "SELECT g FROM Guest g WHERE g.active = true";
		return em.createQuery(jpql, Guest.class).getResultList();
	}

	public List<Guest> findAny(String search) {

		if(search.length() < 1) {
			String jpql = "SELECT g FROM Guest g WHERE g.active = true";
			return em.createQuery(jpql, Guest.class).getResultList();
		} else {
			String jpql = "SELECT g FROM Guest g WHERE g.active = true AND "
					+ "(g.id = :id OR "
					+ "LOWER(g.name) LIKE CONCAT('%', LOWER(:name), '%') OR "
					+ "LOWER(g.lastName) LIKE CONCAT('%', LOWER(:lastName), '%') OR "
					+ "g.birthday = :birthday  OR "
					+ "LOWER(g.nationality) LIKE CONCAT('%', LOWER(:nationality), '%') OR "
					+ "LOWER(g.phone) LIKE CONCAT('%', LOWER(:phone), '%') OR "
					+ "g.reservation = :reservation )";

			TypedQuery<Guest> query = em.createQuery(jpql, Guest.class);

			try {
				query.setParameter("id", Long.parseLong(search));
			} catch (NumberFormatException e) {
				query.setParameter("id", 0L);
			}
			
		    query.setParameter("name", search);
		    
		    query.setParameter("lastName", search);
			
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
		    try {
		        query.setParameter("birthday", sdf.parse(search), TemporalType.DATE);
		    } catch (Exception e) {
		        query.setParameter("birthday", null);
		    }
		    
		    query.setParameter("nationality", search);
		    
		    query.setParameter("phone", search);
		    
			try {
			    query.setParameter("reservation", em.getReference(Reservation.class, Long.parseLong(search)));
			} catch (NumberFormatException e) {
				query.setParameter("reservation", null);
			}

			return query.getResultList();	
		}
	}
}
