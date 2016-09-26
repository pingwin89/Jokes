package pl.pawc.jokes.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Joke{
	
	private String content;
	private Date date;
	private int likes;
	private List<Comment> comments;

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
		like++;
	}

}