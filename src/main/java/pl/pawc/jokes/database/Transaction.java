package pl.pawc.jokes.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.database.Util;

public class Transaction{
	
	public static void saveJokesToFile(String path, HashMap<Integer, Joke> jokes){
		FileWriter fw = null;
		try{
			fw = new FileWriter("baza2");
			String jokesString = parseJokesToString(jokes);
			fw.write(jokesString);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			closeOutputStreams(fw);
		}
	}
	
	public static String parseJokesToString(HashMap<Integer, Joke> jokes){
		String result = "";
		for(int i : jokes.keySet()){
			Joke joke = jokes.get(i);
			result += Util.turnJokeIntoLine(joke);
			result += "\n";
		}
		return result;
	}
	
	public static HashMap<Integer, Joke> loadJokesFromFile(String path){
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
			Transaction.closeInputStreams(bfr);
			return jokes;
		}
	}
	
	public static void closeOutputStreams(FileWriter fw){
		try{
			if(fw != null) fw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void closeInputStreams(BufferedReader bfr){
		try{
			if(bfr != null) bfr.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
