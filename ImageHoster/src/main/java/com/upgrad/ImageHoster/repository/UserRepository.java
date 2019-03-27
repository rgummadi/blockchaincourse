package com.upgrad.ImageHoster.repository;

import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

//Write the annotation which is a special type of @Component annotation and describes that the class defines a data repository
@Repository
public class UserRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the User object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public void registerUser(User newUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(newUser);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }
    
    public User checkUser(String username, String password) {

        try {
     	EntityManager em = emf.createEntityManager();
         TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
         typedQuery.setParameter("username", username);
         typedQuery.setParameter("password", password);

         return typedQuery.getSingleResult();
        } catch(NoResultException nre) {
     	   return null;
        }
   }
}