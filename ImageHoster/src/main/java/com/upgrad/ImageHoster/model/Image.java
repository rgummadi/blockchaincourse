package com.upgrad.ImageHoster.model;
import java.util.Date;

public class Image {

    public Image(int id, String title, String imagefile, String description, Date date2) {
		// TODO Auto-generated constructor stub
    	
    	this.id = id;
    	this.title = title;
    	this.imageFile = imagefile;
    	this.date = date2;
    	this.description = description;
	}

    public Image(int id, String title, String imagefile, Date date2) {
		// TODO Auto-generated constructor stub
    	
    	this.id = id;
    	this.title = title;
    	this.imageFile = imagefile;
    	this.date = date2;
    	
	}
    
    public Image() {
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	//id of the image
    private Integer id;

    //title of the image
    private String title;

    //The image in Base64 format
    private String imageFile;

    //Description of the image
    private String description;

    //Date on which the image is posted
    private Date date;

    //Write the constructor for id, title, imageFile, and date

    //Write getter and setter for all the attributes
}
