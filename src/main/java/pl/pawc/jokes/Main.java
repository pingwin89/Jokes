package pl.pawc.jokes;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.database.Util;
import pl.pawc.jokes.database.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

class Main{
	
	public static void main(String args[]){
		HashMap<Integer, Joke> jokes = Transaction.loadJokesFromFile("baza");
		//print(jokes);
		
		Comment comment = new Comment("Jim", "I like that");
		Comment comment2 = new Comment("Adam", "LMAO");
		ArrayList<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);
		comments.add(comment2);
		
		Joke joke = new Joke("Tom", "Another sample joke");
		joke.setComments(comments);
		
		jokes.put(99, joke);
		
		Transaction.saveJokesToFile("baza2", jokes);
		print(jokes);
		
	}
	
	public static void print(HashMap<Integer, Joke> jokes){
		for(int i : jokes.keySet()){
			Joke joke = jokes.get(i);
			log(i+". "+joke.getAuthor()+": "+joke.getContent());
			log("posted on "+joke.getDate().toString());
			log("likes: "+joke.getLikes());
				int j = 1;
				for(Comment comment : joke.getComments()){
					log("    "+j+". "+comment.getAuthor()+": "+comment.getText()+", "+comment.getDate().toString());
					j++;
				}
			log("");
		}
	}

	public static void log(String line){
		System.out.println(line);
	}
}