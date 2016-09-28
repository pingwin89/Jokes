package pl.pawc.jokes.ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.database.Transaction;
import pl.pawc.jokes.database.Util;

public class CommandHandler{
	
	public static HashMap<Integer, Joke> jokes = null;
	public static Scanner sc = null;
	
	public static void handle(String command){
		switch(command){
			case "print" : {
				CommandHandler.print();
				break;
			}
			case "add joke" : {
				CommandHandler.addJoke();
				break;
			}
			case "del joke" : {
				CommandHandler.deleteJoke();
				break;
			}
			case "recent" : {
				break;
			}
			case "oldest" : {
				break;
			}
			case "top" : {
				break;
			}
			case "worst" : {
				break;
			}
			case "like" : {
				break;
			}
			case "save" : {
				break;
			}
			case "help" : {
				break;
			}
			case "quit" : {
				break;
			}
		}
	}
	
	
	public static void addJoke(){
		log("your joke: ");
		String content = CommandHandler.read();
		log("your name: ");
		String author = CommandHandler.read();
		Joke joke = new Joke(author, content);
		jokes.put(Util.nextKeyNumber(jokes.keySet()), joke);
		log("your joke has been submitted");
	}
	
	public static void deleteJoke(){
		log("which number?");
		int i = readNumber();
		if(jokes.get(i)==null){
			log("No such joke");
		}
		else{
			jokes.remove(i);
			log("the joke has been removed");
		}
	}
	
	public static void print(){
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
	
	public static String read(){
		String line = sc.nextLine();
		if(line==null){
			log("No input. Exiting program");
			System.exit(0);
		}
		return line;		
	}
	
	public static int readNumber(){
		int i = 0;
		try{
			i = sc.nextInt();
		}
		catch(InputMismatchException e){
			log(e.toString());
			return 0;
		}
		catch(IllegalStateException e){
			e.printStackTrace();
			log("Stream closed");
			System.exit(0);
		}
		return i;
	}
	
	public static void log(String line){
		System.out.println(line);
	}
	
}