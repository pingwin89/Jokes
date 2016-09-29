package pl.pawc.jokes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collections;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.database.Transaction;
import pl.pawc.jokes.database.Util;
import pl.pawc.jokes.sort.OrderByLikes;

public class CommandHandler{
	
	private String path;
	public Scanner sc = null;
	public HashMap<Integer, Joke> jokes = null;
	
	public CommandHandler(String path, Scanner sc, HashMap<Integer, Joke> jokes){
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
				recent();
				break;
			}
			case "oldest" : {
				oldest();
				break;
			}
			case "top" : {
				top();
				break;
			}
			case "worst" : {
				worst();
				break;
			}
			case "like" : {
				break;
			}
			case "save" : {
				save();
				break;
			}
			case "comment" : {
				break;
			}
			case "help" : {
				help();
				break;
			}
			case "quit" : {
				quit();
				break;
			}
		}
	}

	private void save(){
		Transaction.saveJokesToFile(path, jokes);
		log("jokes saved");
	}

	private void help(){
		String help =
		"print \n" +
		"add joke \n" +
		"del joke \n" +
		"recent \n" +
		"oldest \n" +
		"top \n" +
		"worst \n" +
		"like \n" +
		"comment \n" +
		"save \n" +
		"quit \n" +
		"help";
		log(help);
	}

	private void top(){
		ArrayList<Joke> list = Util.getListFromMap(jokes);
		Collections.sort(list, new OrderByLikes());
		print(list);
	}

	private void worst(){
		ArrayList<Joke> list = Util.getListFromMap(jokes);
		Collections.sort(list, Collections.reverseOrder(new OrderByLikes()));
		print(list);	
	}

	private void oldest(){
		ArrayList<Joke> list = Util.getListFromMap(jokes);
		Collections.sort(list, Collections.reverseOrder());
		print(list);
	}

	private void recent(){
		ArrayList<Joke> list = Util.getListFromMap(jokes);
		Collections.sort(list);
		print(list);		
	}

	private void quit(){
		sc.close();	
		log("quitting, bye..");
		System.exit(0);
	}
	
	private void addJoke(){
		log("your joke: ");
		String content = read();
		log("your name: ");
		String author = read();
		Joke joke = new Joke(author, content);
		jokes.put(Util.nextKeyNumber(jokes.keySet()), joke);
		log("your joke has been submitted");
	}
	
	private void deleteJoke(){
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

	private void print(ArrayList<Joke> jokes){
		int i = 1;
		for(Joke joke : jokes){
			log(i+". "+joke.getAuthor()+": "+joke.getContent());
			log("posted on "+joke.getDate().toString());
			log("likes: "+joke.getLikes());
				int j = 1;
				for(Comment comment : joke.getComments()){
					log("    "+j+". "+comment.getAuthor()+": "+comment.getText()+", "+comment.getDate().toString());
					j++;
				}
			i++;				
			log("");
		}
	}
	
	private void print(){
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
	
	private String read(){
		String line = sc.nextLine();
		if(line==null){
			log("No input. Exiting program");
			System.exit(0);
		}
		return line;		
	}
	
	private int readNumber(){
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
	
	private void log(String line){
		System.out.println(line);
	}
	
}
