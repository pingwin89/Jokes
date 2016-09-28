package pl.pawc.jokes.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.database.Util;

public class Transaction{
	
	public static ArrayList<Joke> getJokesFromFile(String path){
		BufferedReader bfr = null;
		FileReader fr = null;
		ArrayList<Joke> jokes = new ArrayList<Joke>();
		try{
			fr = new FileReader(path);
			bfr = new BufferedReader(fr);
			String line;
			
			while((line = bfr.readLine()) != null){
				Joke joke = Util.getJokeFrom(line);
				jokes.add(joke);
			}
			
		}
		catch(FileNotFoundException e){
			System.out.println(e.toString()+", returning empty jokes list");
			return new ArrayList<Joke>();
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Returning empty jokes list");
			return new ArrayList<Joke>();
		}
		finally{
			Transaction.closeStreams(bfr);
			return jokes;
		}
	}
	
	public static void closeStreams(BufferedReader bfr){
		try{
			bfr.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}