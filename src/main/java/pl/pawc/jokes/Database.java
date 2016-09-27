package pl.pawc.jokes;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.ArrayList;

import pl.pawc.jokes.model.Joke;

public class Database{

	private String path;
	private ArrayList<Joke> jokes;

	public Database(String path){
		try{
			FileReader fileReader = new FileReader(path);
			BufferedReader bfr = new BufferedReader(fileReader);
			fillTheListFrom(bfr);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			jokes = new ArrayList<Joke>();
		}
	}

	private void fillTheListFrom(BufferedReader bfr) throws IOException{
		String line;
		while((line = bfr.readLine())!=null){
			addJoke(line);
		}
	}

	private void addJoke(String line){
		jokes.add(parse(line));
	}

	private Joke parse(String line){
		String[] data = line.split(";");
		Joke joke = new Joke();
		joke.setContent(data[0]);
		joke.setDate(data[1]);
		joke.setLikes(data[2]);
		joke.setComments(data[3]);
		return joke;
	}

	public void print(){
		int i = 1;
		for(Joke joke : jokes){
			log(i+": "+joke.getContent());
			log("Likes: "+joke.getLikes());
			i++;
		}
	}

	public void log(String info){
		System.out.println(info);
	}

}
