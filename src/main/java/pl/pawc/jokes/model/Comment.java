package pl.pawc.jokes.model;

import java.util.Date;

public class Comment{
	
	private String author;
	private Date date;
	private String text;

	public Comment(String[] data){
		this(data[0], data[1], data[2]);
	}

	public Comment(String author, String date, String text){
		this.author = author;
		this.date = new Date(Long.parseLong(date));
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
