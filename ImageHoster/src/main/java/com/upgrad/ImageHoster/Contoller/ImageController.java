package com.upgrad.ImageHoster.Contoller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.upgrad.ImageHoster.HardCodedImage;
import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.service.ImageService;



@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private HardCodedImage hardCodedImage;
    
    //This method displays all the images in the user home page after successful login
    @RequestMapping("images")
    public String getUserImages(Model model) {
        //Complete the method
        //Get all the hard-coded images in the application using getAllImages() method in ImageService class and add them to the model with 'images' as the key
    	List<Image> images = imageService.getAllImages();
    	model.addAttribute("images",images);
    	return "images";
    }
    
    @RequestMapping("/images/{title}")
    public String showImage(@PathVariable("title") String title, Model model) {
        Date date = new Date();
        Image image = null;
        if (title.equals("Dr. Strange")) {
            image = new Image(1, "Dr. Strange",hardCodedImage.getDrStrange(),"Dr. Strange has a time stone", date);
        } else if (title.equals("SpiderMan")) {
            image = new Image(2, "SpiderMan", hardCodedImage.getSpiderMan(), "Spider man dies in Infinity War",date);
        }

        //Add the image in the model with the key as 'image'
        //Return 'images/image.html' file
        
        model.addAttribute("image", image);
        
        return "images/image.html";
    }
    
  //This controller method is called when the request pattern is of type 'images/upload'
    //The method returns 'images/upload.html' file
    @RequestMapping("/images/upload")
    public String newImage() {
        return "images/upload";
    }

    //This controller method is called when the request pattern is of type 'images/upload' and also the incoming request is of POST type
    //The method receives all the details of the image to be stored in the database, but currently we are not using database so the business logic simply retuns null and does not store anything in the database
    //After you get the imageFile, convert it to Base64 format and store it as a string
    //After storing the image, this method directs to the logged in user homepage displaying all the images
    @RequestMapping(value = "/images/upload", method = RequestMethod.POST)
    public String createImage(@RequestParam("file") MultipartFile file, Image newImage) throws IOException {

        //Complete the method
        //Encode the imageFile to Base64 format and set it as the imageFile attribute of the newImage
    	newImage.setImageFile(this.convertUploadedFileToBase64(file));
        //Set the date attributuploadImagee of newImage
        //Call the business logic to upload an image which currently does not store the image in the database
    	imageService.uploadImage(newImage);
        //After uploading the image direct to the logged in user homepage displaying all the images
    	return "redirect:/images";
    }

    //This method converts the image to Base64 format
    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
}

