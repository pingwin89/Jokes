package pl.pawc.jokes;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.Date;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;

public class Database{

	private String path;
	private ArrayList<Joke> jokes;
	BufferedReader bfr;
	FileReader fr;
	FileWriter fw;

	public Database(String path){
		this.path = path;
		try{
			fr = new FileReader(path);
			bfr = new BufferedReader(fr);
			fillTheListFrom(bfr);
			fr.close();
			bfr.close();
		}
		catch(FileNotFoundException e){
			log("Database file does not exist");
			jokes = new ArrayList<Joke>();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	private void fillTheListFrom(BufferedReader bfr) throws IOException{
		jokes = new ArrayList<Joke>();
		String line;
		while((line = bfr.readLine())!=null){
			addJoke(line);
		}
	}

	private void addJoke(String line){
		jokes.add(parse(line));
	}

	public void addJoke(Joke joke){
		jokes.add(joke);
	}

	private Joke parse(String line){
		String[] data = line.split(";");
		Joke joke = new Joke();
		joke.setContent(data[0]);
		joke.setDate(data[1]);
		joke.setLikes(data[2]);
		try{
			joke.setComments(data[3]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			//log("a joke without comments");
		}	
		return joke;
	}

	public void print(){
		log("Printing jokes \n");
		int i = 1;
		for(Joke joke : jokes){
			log(i+": "+joke.getContent());
			log("Likes: "+joke.getLikes());
			log("Date: "+(new Date(joke.getDate())).toString());
			i++;
			log("");
		}
	}

	public void save(){
		String data = "";
		for(Joke joke : jokes){
			data += parseJoke(joke);
			data += "\n";
		}
		write(data);
	}

	private String parseJoke(Joke joke){
		String content = joke.getContent();
		int likes = joke.getLikes();
		Long time = joke.getDate();
		ArrayList<Comment> comments = joke.getComments();
		String parsedComments = parseComments(comments);
		String line = content+";"+time+";"+likes+";"+parsedComments;
		return line;
	}

	private String parseComments(ArrayList<Comment> comments){
		if(comments==null||comments.isEmpty()) return "";
		String result = "";
		for(Comment comment : comments){
			result += comment.getAuthor();
			result += "$";
			result += comment.getDate();
			result += "$";
			result += comment.getText();
			result += "#";
		}
		return result;
	}

	private void write(String line){
		try{
			fw = new FileWriter(path);
			fw.write(line);
			fw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void log(String info){
		System.out.println(info);
	}

}
