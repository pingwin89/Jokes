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

	public Comment(String author, String date, String text){
		this.author = author;
		this.date = new Date(parseDate(date));
		this.text = text;
	}
	
	private Long parseDate(String date){
		Long result = 0L;
		try{
			result = Long.parseLong(date);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
		finally{
			return result;
		}
	}

	public String getAuthor(){
		return author;
	}

	public Date getDate(){
		return date;
	}

	public String getText(){
		return text;
	}

}