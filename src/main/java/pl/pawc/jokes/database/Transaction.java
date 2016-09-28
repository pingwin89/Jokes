package pl.pawc.jokes.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.database.Util;

public class Transaction{
	
	public static HashMap<Integer, Joke> getJokesFromFile(String path){
		BufferedReader bfr = null;
		FileReader fr = null;
		HashMap<Integer, Joke> jokes = new HashMap<Integer, Joke>();
		try{
			fr = new FileReader(path);
			bfr = new BufferedReader(fr);
			String line;
			int i = 1;
			while((line = bfr.readLine()) != null){
				Joke joke = Util.getJokeFrom(line);
				jokes.put(i, joke);
				i++;
			}
			
		}
		catch(FileNotFoundException e){
			System.out.println(e.toString()+", returning empty jokes list");
			return new HashMap<Integer, Joke>();
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Returning empty jokes list");
			return new HashMap<Integer, Joke>();
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
