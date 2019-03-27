package com.upgrad.TechnicalBlog.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.upgrad.TechnicalBlog.model.Post;

@Repository
public class PostRepository {

	@PersistenceUnit(unitName="techblog")
	private EntityManagerFactory emf;
	
	public List<Post> getAllPosts() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Post> query =  em.createQuery("SELECT p from Post p", Post.class);
		List<Post> resultList = query.getResultList();
		
		return resultList;
		
	}
	
	public Post getLatestPost() {
		
		EntityManager em = emf.createEntityManager();
		return em.find(Post.class,3);
		
	}
	
	public Post createPost(Post newPost) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction =  em.getTransaction();
		try {
			transaction.begin();
			em.persist(newPost);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
		}
		
		return newPost;
		
	}
	
	public Post getPost(Integer postId) {
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class, postId);
    }
	
	public void updatePost(Post updatedPost) {
		   EntityManager em = emf.createEntityManager();
		   EntityTransaction transaction = em.getTransaction();

		   try {
		       transaction.begin();
		       em.merge(updatedPost);
		       transaction.commit();
		   }catch(Exception e) {
		       transaction.rollback();
		   }
		}
	public void deletePost(Integer postId) {
		   EntityManager em = emf.createEntityManager();
		   EntityTransaction transaction = em.getTransaction();

		   try {
		       transaction.begin();
		       Post post = em.find(Post.class, postId);
		       em.remove(post);
		       transaction.commit();
		   }catch(Exception e) {
		       transaction.rollback();
		   }
		}
	
}
