package com.dev.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.dev.spring.beans.User;
@Repository
public class JpaImpl implements UserDAO {
	private final static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("MySQLUnit");

	@Override
	public Boolean createUser(User user) {
		Boolean state=false;
		try {
			
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			state = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;

	}

	@Override
	public User searchUser(Integer userId) {
      EntityManager em=emf.createEntityManager();
   User user=   em.find(User.class, userId);
   em.close();
		return user;
	}

	

	@Override
	public User deleteUser(Integer userId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, userId);
		em.remove(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public User login(Integer userId, String password) {
		EntityManager em = emf.createEntityManager();
	       Query query = em.createQuery("from User u where u.userId= :userid and u.password= :password ");
	       query.setParameter("userid", userId );
	       query.setParameter("password", password);
	       em.getTransaction().begin();
	       List user=query.getResultList();
	       em.getTransaction().commit();
	      User user1= (User) user.get(0);
	      System.out.println(user1);
		return user1;
	}

	@Override
	public Boolean updateUser(Integer userId, String password) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			User user = em.find(User.class, userId);
			user.setPassword(password);
			em.getTransaction().commit();
			em.close();
			state = true;
		} catch (Exception e) {
			//Custom Exception
			e.printStackTrace();
		}
		return state;
	}

	

}
