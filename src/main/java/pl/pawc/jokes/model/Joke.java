package pl.pawc.jokes.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Joke{
	
	private String content;
	private String author;
	private Date date;
	private int likes;
	private ArrayList<Comment> comments;

	public Joke(){
	}

	public Joke(String content, String author){
		this.content = content;
		this.author = author;
		date = new Date();
		likes = 0;
		comments = new ArrayList<Comment>();
	}

	public void setContent(String content){
		this.content = content;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public void setDate(Long date){
		this.date = new Date(date);
	}
	
	public void setDate(String date){
		this.date = new Date(parseDate(date));
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
	
	public void setLikes(int likes){
		this.likes = likes;
	}

	public void setLikes(String likes){
		this.likes = Integer.parseInt(likes);
	}

	public void setComments(ArrayList<Comment> comments){
		this.comments = comments;
	}
	
	public String getContent(){
		return content;
	}

	public String getAuthor(){
		return author;
	}
	
	public int getLikes(){
		return likes;
	}

	public Date getDate(){
		return date;
	}

	public ArrayList<Comment> getComments(){
		return comments;
	}

}