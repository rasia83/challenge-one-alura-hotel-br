package rasia.hotelalura.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import rasia.hotelalura.dao.GuestDAO;
import rasia.hotelalura.dao.LoginDAO;
import rasia.hotelalura.dao.ReservationDAO;
import rasia.hotelalura.entity.Guest;
import rasia.hotelalura.entity.Login;
import rasia.hotelalura.entity.Reservation;
import rasia.hotelalura.util.JPAUtil;

public class Connection {

	public static void main(String[] args) {
 
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		calendar.add(Calendar.DATE, 3);
		Date currentDatePlus3Days = calendar.getTime();

		//Login login = new Login("admin", "admin");
		Reservation reservation = new Reservation(currentDate, currentDatePlus3Days, new BigDecimal(1699), "Dinheiro");
		Guest guest = new Guest("Tiago", "Edgar Rasia", currentDate, "brasileiro", "+55 (19) 9........", reservation);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		//LoginDAO loginDAO = new LoginDAO(em);
		ReservationDAO reservationDAO = new ReservationDAO(em);
		GuestDAO guestDAO = new GuestDAO(em);

		em.getTransaction().begin();


		List<Reservation> reservations = reservationDAO.findAll();
		for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
			Reservation iReservation = (Reservation) iterator.next();
			System.out.println(iReservation);
		}	

		List<Guest> guests = guestDAO.findAll();
		for (Iterator<Guest> iterator = guests.iterator(); iterator.hasNext();) {
			Guest iGuest = (Guest) iterator.next();
			System.out.println(iGuest);
		}		
		
		
		//loginDAO.save(login);
		reservationDAO.save(reservation);
		guestDAO.save(guest);
		
		//reservationDAO.delete(reservation);
		
		
		em.getTransaction().commit();
		em.close();
		System.out.println("** OK **");
	}

}
