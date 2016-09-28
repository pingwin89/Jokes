package pl.pawc.jokes;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.database.Util;
import pl.pawc.jokes.database.Transaction;

import java.util.ArrayList;

class Main{
	
	public static void main(String args[]){
		ArrayList<Joke> jokes = Transaction.getJokesFromFile("baza");
		print(jokes);
	}
	
	public static void print(ArrayList<Joke> jokes){
		int i = 1;
		for(Joke joke : jokes){
			log(i+". "+joke.getAuthor()+": "+joke.getContent());
			log("posted on "+joke.getDate().toString());
			log("likes: "+joke.getLikes());
			log("Comments: ");
				int j = 1;
				for(Comment comment : joke.getComments()){
					log("    "+j+". "+comment.getAuthor()+": "+comment.getText()+", "+comment.getDate().toString());
					j++;
				}
			i++;
			log("");
		}
	}

	public static void log(String line){
		System.out.println(line);
	}
}