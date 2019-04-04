package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    //Call the getAllImages() method in the Repository and obtain a List of all the images in the database
    public List<Image> getAllImages() {
        //Complete the method
    	return imageRepository.getAllImages();
    }


    //The method calls the createImage() method in the Repository and passes the image to be persisted in the database
    public void uploadImage(Image image) {
        imageRepository.uploadImage(image);
    }


    //The method calls the getImageByTitle() method in the Repository and passes the title of the image to be fetched
    public Image getImageByTitle(String title) {
        //Complete the method
    	return imageRepository.getImageByTitle(title);
    }
    
  //The method calls the getImage() method in the Repository and passes the id of the image to be fetched
    public Image getImage(Integer imageId) {
    	return imageRepository.getImage(imageId);
    }

    //The method calls the updateImage() method in the Repository and passes the Image to be updated in the database
    public void updateImage(Image updatedImage) {
        //Complete the method
    	imageRepository.updateImage(updatedImage);
    }
    
  //The method calls the deleteImage() method in the Repository and passes the Image id of the image to be deleted in the database
    public void deleteImage(Integer imageId) {
        //Complete the method
    	imageRepository.deleteImage(imageId);
    }

    public boolean isImageOwner(User user,User image_owner) {
    	
    	if (user == null || image_owner == null)
    		return false;
    	
    	if (user.getId() == image_owner.getId())
    		return true;
    	else
    		return false;
    }
    
    
}
