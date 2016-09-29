package pl.pawc.jokes.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Joke implements Comparable<Joke>{
	
	private String author;
	private String content;
	private Date date;
	private int likes;
	private ArrayList<Comment> comments;

	public Joke(){
	}

	public Joke(String author, String content){
		this.author = author;
		this.content = content;
		date = new Date();
		likes = 0;
		comments = new ArrayList<Comment>();
	}
	
	public void setAuthor(String author){
		this.author = author;
	}

	public void setContent(String content){
		this.content = content;
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

	public void addLike(){
		likes++;
	}

	public void addComment(Comment comment){
		comments.add(comment);
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

	public String getAuthor(){
		return author;
	}
	
	public String getContent(){
		return content;
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

	@Override
	public int compareTo(Joke joke){
		return date.getTime() < joke.getDate().getTime() ? 1 : (date.getTime() > joke.getDate().getTime() ? -1 : 0);
	}

}
