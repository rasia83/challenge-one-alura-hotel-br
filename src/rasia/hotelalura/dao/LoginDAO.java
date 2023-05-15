package rasia.hotelalura.dao;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import rasia.hotelalura.util.EncryptPassword;

public class LoginDAO {
	
	private EntityManager em;

	public LoginDAO(EntityManager em) {
		this.em = em;
	}
	
	public boolean passwordTest(String username, String password) {

		String jpql = "SELECT l.password FROM Login l WHERE l.username = :username";
		String dbPass = em.createQuery(jpql, String.class)
						.setParameter("username", username)
						.getSingleResult();

		try {
			password = EncryptPassword.encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		if(password.equalsIgnoreCase(dbPass))
			return true;
		return false;
	}

}
