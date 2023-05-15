package rasia.hotelalura.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DailyRateDAO {

	private EntityManager em;

	public DailyRateDAO(EntityManager em) {
		this.em = em;
	}

	public BigDecimal getDailyRate() {
		String jpql = "SELECT dr.price FROM DailyRate dr ORDER BY dr.id DESC";
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
	    query.setMaxResults(1);
	    return (BigDecimal)query.getSingleResult();
		// return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

}
