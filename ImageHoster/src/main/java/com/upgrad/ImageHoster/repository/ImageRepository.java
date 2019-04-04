package com.upgrad.ImageHoster.repository;

import com.upgrad.ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class ImageRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;


    public Image uploadImage(Image newImage) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newImage);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newImage;
    }

    public List<Image> getAllImages() {
    	EntityManager em = emf.createEntityManager();
		TypedQuery<Image> query =  em.createQuery("SELECT i from Image i", Image.class);
		List<Image> resultList = query.getResultList();
		
		return resultList;
    }

    public Image getImageByTitle(String title) {
    	try {
    	 EntityManager em = emf.createEntityManager();
         TypedQuery<Image> typedQuery = em.createQuery("SELECT i FROM Image i WHERE i.title = :title", Image.class);
         typedQuery.setParameter("title", title);

         return typedQuery.getSingleResult();
    	} catch(NoResultException nre) {
    		return null;
    	}
    }
    	
    	//The method creates an instance of EntityManager
        //Executes JPQL query to fetch the image from the database with corresponding id
        //Returns the image fetched from the database
        public Image getImage(Integer imageId) {
        	EntityManager em = emf.createEntityManager();
        	return em.find(Image.class, imageId);
        }

        //The method receives the Image object to be updated in the database
        //Creates an instance of EntityManager
        //Starts a transaction
        //The transaction is committed if it is successful
        //The transaction is rolled back in case of unsuccessful transaction
        public void updateImage(Image updatedImage) {
        	EntityManager em = emf.createEntityManager();
 		   EntityTransaction transaction = em.getTransaction();

 		   try {
 		       transaction.begin();
 		       em.merge(updatedImage);
 		       transaction.commit();
 		   }catch(Exception e) {
 		       transaction.rollback();
 		   }
        	
        }
        
      //The method receives the Image id of the image to be deleted in the database
        //Creates an instance of EntityManager
        //Starts a transaction
        //Get the image with corresponding image id from the database
        //This changes the state of the image model from detached state to persistent state, which is very essential to use the remove() method
        //If you use remove() method on the object which is not in persistent state, an exception is thrown
        //The transaction is committed if it is successful
        //The transaction is rolled back in case of unsuccessful transaction
        public void deleteImage(Integer imageId) {
            //Complete the method
        	EntityManager em = emf.createEntityManager();
 		   EntityTransaction transaction = em.getTransaction();

 		   try {
 		       transaction.begin();
 		       Image image = em.find(Image.class, imageId);
 		       em.remove(image);
 		       transaction.commit();
 		   }catch(Exception e) {
 		       transaction.rollback();
 		   }
        }

    }


