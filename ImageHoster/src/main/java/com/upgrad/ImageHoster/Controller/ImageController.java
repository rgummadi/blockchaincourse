package com.upgrad.ImageHoster.Controller;

import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.model.Tag;
import com.upgrad.ImageHoster.service.ImageService;
import com.upgrad.ImageHoster.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private TagService tagService;

    //This method displays all the images in the user home page after successful login
    @RequestMapping("images")
    public String getUserImages(Model model) {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "images";
    }

    //This method is called when the details of the specific image with corresponding title are to be displayed
    //The logic is to get the image from the databse with corresponding title. After getting the image from the database the details are shown
    //First receive the dynamic parameter in the incoming request URL in a string variable 'title' and also the Model type object
    //Call the getImageByTitle() method in the business logic to fetch all the details of that image
    //Add the image in the Model type object with 'image' as the key
    //Return 'images/image.html' file
    @RequestMapping("/images/{imageId}/{title}")
    public String showImage(@PathVariable("title") String title,@PathVariable("imageId") Integer imageId, Model model) {
        Image image = imageService.getImage(imageId);
        model.addAttribute("image", image);
        model.addAttribute("tags",image.getTags());
        model.addAttribute("title",title);
        model.addAttribute("id", imageId);
        model.addAttribute("comments",image.getComments());
        return "/images/image";
    }

    //This controller method is called when the request pattern is of type 'images/upload'
    //The method returns 'images/upload.html' file
    @RequestMapping("/images/upload")
    public String newImage() {
        return "images/upload";
    }

    //After storing the image, this method directs to the logged in user homepage displaying all the images
    @RequestMapping(value = "/images/upload", method = RequestMethod.POST)
    public String createImage(@RequestParam("file") MultipartFile file,@RequestParam("tags") String tags, Image newImage, HttpSession session) throws IOException {

        User user = (User) session.getAttribute("loggeduser");
        newImage.setUser(user);
        String uploadedImageData = convertUploadedFileToBase64(file);
        newImage.setImageFile(uploadedImageData);
        
        List<Tag> imageTags = findOrCreateTags(tags);
        newImage.setTags(imageTags);
        newImage.setDate(new Date());
        imageService.uploadImage(newImage);
        return "redirect:/images";
    }

    @RequestMapping(value = "/editImage")
    public String editImage(@RequestParam("imageId") Integer imageId, HttpSession session,Model model) {
    	
    	
        Image image = imageService.getImage(imageId);
        // if the logged in user is the creator of the image
        if (imageService.isImageOwner((User)session.getAttribute("loggeduser"),image.getUser())) {
        	String tags = convertTagsToString(image.getTags());
            model.addAttribute("image", image);
            model.addAttribute("tags",tags);
            return "images/edit";
        }else {
        	
        	model.addAttribute("image", image);
            model.addAttribute("tags",image.getTags());
            model.addAttribute("title",image.getTitle());
            model.addAttribute("id", imageId);
            String error = "Only the owner of the image can edit the image";
            model.addAttribute("editError", error);
            return "images/image";
        }
        
        
        
    }

    @RequestMapping(value = "/editImage", method = RequestMethod.PUT)
    public String editImageSubmit(@RequestParam("file") MultipartFile file, @RequestParam("imageId") Integer imageId,@RequestParam("tags") String tags, Image updatedImage, HttpSession session) throws IOException {
    	
    	Image image = imageService.getImage(imageId);
        String updatedImageData = convertUploadedFileToBase64(file);
        List<Tag> imageTags = findOrCreateTags(tags);
        
        if (updatedImageData.isEmpty())
            updatedImage.setImageFile(image.getImageFile());
        else {
            updatedImage.setImageFile(updatedImageData);
        }
    	
    	 updatedImage.setId(imageId);
    	 User user = (User) session.getAttribute("loggeduser");
    	 updatedImage.setUser(user);
    	 updatedImage.setTags(imageTags);
    	 updatedImage.setDate(new Date());
    	 imageService.updateImage(updatedImage);
    	 return "redirect:/images/" + imageId + "/" + updatedImage.getTitle();
    }
    
  //This controller method is called when the request pattern is of type 'deleteImage' and also the incoming request is of DELETE type
    //The method calls the deleteImage() method in the business logic passing the id of the image to be deleted
    //Looks for a controller method with request mapping of type '/images'
    @RequestMapping(value = "/deleteImage", method = RequestMethod.DELETE)
    public String deleteImageSubmit(@RequestParam(name = "imageId") Integer imageId, HttpSession session,Model model) {
    	Image image = imageService.getImage(imageId);
        // if the logged in user is the creator of the image
        if (imageService.isImageOwner((User)session.getAttribute("loggeduser"),image.getUser())) {
        	imageService.deleteImage(imageId);
        	return "redirect:/images";
        }else {
        	
        	model.addAttribute("image", image);
            model.addAttribute("tags",image.getTags());
            model.addAttribute("title",image.getTitle());
            model.addAttribute("id", imageId);
           String error = "Only the owner of the image can edit the image";
            model.addAttribute("deleteError", error);
            return "images/image";
        }
        //Complete the method
    	
    }
    //This method converts the image to Base64 format
    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
    
  //findOrCreateTags() method has been implemented, which returns the list of tags after converting the ‘tags’ string to a list of all the tags and also stores the tags in the database if they do not exist in the database. Observe the method and complete the code where required for this method.
    //Try to get the tag from the database using getTagByName() method. If tag is returned, you just need to add that tag in a list of all the tags, and if null is returned, you need to first store that tag in the database and then the tag is added to a list
    //createTag() method is used to store the tag in the database
    //After adding all tags to a list, the list is returned

    private List<Tag> findOrCreateTags(String tagNames) {
        StringTokenizer st = new StringTokenizer(tagNames, ",");
        List<Tag> tags = new ArrayList<Tag>();

        while (st.hasMoreTokens()) {
            String tagName = st.nextToken().trim();

            //You need to implement the business logic and the repository to interact with the database for getTagByName() method
            //You pass the tag name to this method and this method returns the corresponding tag from the database if exists. The method returns null if the tag does not exist in the database.
            //This method receives the tag name and returns the Tag type object from the database if the tag with the same name exists in the database
            //If the tag with the corresponding name does not exist in the database, it returns null
            Tag tag = tagService.getTagByName(tagName);

            if (tag == null) {
                Tag newTag = new Tag(tagName);
                tag = tagService.createTag(newTag);
            }
            tags.add(tag);
        }
        return tags;
    }

    private String convertTagsToString(List<Tag> tags) {
    	
    	if (tags.isEmpty()){
    		return "";
    	}
        StringBuilder tagString = new StringBuilder();

        for (int i = 0; i <= tags.size() - 2; i++) {
            tagString.append(tags.get(i).getName()).append(",");
        }

        Tag lastTag = tags.get(tags.size() - 1);
        tagString.append(lastTag.getName());

        return tagString.toString();
    }
}
