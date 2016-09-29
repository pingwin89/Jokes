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
		HashMap<Integer, Joke> jokes = Transaction.loadJokesFromFile(file);
		Scanner sc = new Scanner(System.in);

		CommandHandler ch = new CommandHandler(sc, jokes);	

		String line; 
		
		while(true){
			System.out.printf(">");
			line = ch.sc.nextLine();
			if(line == null) System.exit(0);
			ch.handle(line);
		}		
		
	}
	
}
