package pl.pawc.jokes;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.database.Util;
import pl.pawc.jokes.database.Transaction;
import pl.pawc.jokes.ui.CommandHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main{
	
	public static void main(String args[]){
		
		String file = args[0];
		CommandHandler.jokes = Transaction.loadJokesFromFile(file);
		CommandHandler.sc  = new Scanner(System.in);
		
		String line; 
		
		while(true){
			System.out.printf(">");
			line = CommandHandler.sc.nextLine();
			if(line == null) System.exit(0);
			CommandHandler.handle(line);
		}		
		
	}
	
}