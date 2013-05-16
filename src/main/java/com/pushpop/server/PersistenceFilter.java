package com.pushpop.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.pushpop.server.dao.PersistenceManagerFactory;
import com.pushpop.server.dao.ThreadLocalPersistenceManager;

public class PersistenceFilter implements Filter {
	protected static final Logger logger = Logger
			.getLogger(PersistenceFilter.class.getName());

	private static EntityManagerFactory entityManagerFactory = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		entityManagerFactory = PersistenceManagerFactory
				.getEntityManagerFactory();
	}

	@Override
	public void destroy() {
		ThreadLocalPersistenceManager.removeThreadLocalEntityManager();
		entityManagerFactory.close();
		ThreadLocalPersistenceManager.removeThreadLocalEntityManager();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		EntityManager em = null;

		try {
			em = entityManagerFactory.createEntityManager();
			ThreadLocalPersistenceManager.setThreadLocakEntityManager(em);
			chain.doFilter(req, res);

			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
		} finally {
			ThreadLocalPersistenceManager.removeThreadLocalEntityManager();
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}
}
