package rasia.hotelalura.dao;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import rasia.hotelalura.util.EncryptPassword;

public class LoginDAO {
	
	private EntityManager em;

	public LoginDAO(EntityManager em) {
		this.em = em;
	}
	
	public boolean passwordTest(String username, String password) {

		String jpql = "SELECT l.password FROM Login l WHERE l.username = :username";

		try {
			String dbPass = em.createQuery(jpql, String.class)
							.setParameter("username", username)
							.getSingleResult();
			
			password = EncryptPassword.encrypt(password);
			
			if(password.equalsIgnoreCase(dbPass))
				return true;
			
		} catch (NoResultException e) {
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return false;
	}

}
