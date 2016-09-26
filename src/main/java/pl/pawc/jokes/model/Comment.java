package pl.pawc.jokes.model;

import java.util.Date;

public class Comment{
	
	private String author;
	private Date date;
	private String text;

	public Comment(String author, String text){
		this.author = author;
		date = new Date();
		this.text = text;
	}

	private String getAuthor(){
		return author;
	}

	private Date getDate(){
		return date;
	}

	private String getText(){
		return text;
	}

}