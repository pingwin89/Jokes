package pl.pawc.jokes.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Joke{
	
	private String content;
	private Date date;
	private int likes;
	private ArrayList<Comment> comments;

	public Joke(){
	}

	public Joke(String content){
		this.content = content;
		date = new Date();
		likes = 0;
		comments = new ArrayList<Comment>();
	}

	public void addComment(Comment comment){
		comments.add(comment);
	}

	public void addLike(){
		likes++;
	}

	public void setContent(String content){
		this.content = content;
	}
	
	public void setDate(String date){
		this.date = new Date(Long.parseLong(date));
	}

	public void setDate(Long date){
		this.date = new Date(date);
	}

	public void setLikes(String likes){
		this.likes = Integer.parseInt(likes);
	}

	public void setLikes(int likes){
		this.likes = likes;
	}

	// # rozdziela komenatrze 
    // $ rozdziela dane wewnątrz komentarza
	public void setComments(String line){
		comments = new ArrayList<Comment>();
		String[] data = line.split("#");
		for(String record : data){
			comments.add(new Comment(record.split("$")));
		}	
	}

	public String getContent(){
		return content;
	}

	public int getLikes(){
		return likes;
	}

	public Long getDate(){
		return date.getTime();
	}

	public ArrayList<Comment> getComments(){
		return comments;
	}

}
