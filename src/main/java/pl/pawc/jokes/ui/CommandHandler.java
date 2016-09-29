package pl.pawc.jokes.ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.database.Transaction;
import pl.pawc.jokes.database.Util;

public class CommandHandler{
	
	public Scanner sc = null;
	public HashMap<Integer, Joke> jokes = null;
	
	public CommandHandler(Scanner sc, HashMap<Integer, Joke> jokes){
		this.sc = sc;
		this.jokes = jokes;
	}

	public void handle(String command){
		switch(command){
			case "print" : {
				print();
				break;
			}
			case "add joke" : {
				addJoke();
				break;
			}
			case "del joke" : {
				deleteJoke();
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
	
	
	public void addJoke(){
		log("your joke: ");
		String content = read();
		log("your name: ");
		String author = read();
		Joke joke = new Joke(author, content);
		jokes.put(Util.nextKeyNumber(jokes.keySet()), joke);
		log("your joke has been submitted");
	}
	
	public void deleteJoke(){
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
	
	public void print(){
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
	
	public String read(){
		String line = sc.nextLine();
		if(line==null){
			log("No input. Exiting program");
			System.exit(0);
		}
		return line;		
	}
	
	public int readNumber(){
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
	
	public void log(String line){
		System.out.println(line);
	}
	
}
