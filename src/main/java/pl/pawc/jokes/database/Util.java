package pl.pawc.jokes.database;

import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.model.Joke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Util{
	
	public static String turnJokeIntoLine(Joke joke){
		String result = "";
		result += joke.getAuthor();
		result += ";";
		result += joke.getContent();
		result += ";";
		result += String.valueOf(joke.getDate().getTime());
		result += ";";
		result += joke.getLikes();
		result += ";";
		
		for(Comment comment : joke.getComments()){
			result += comment.getAuthor();
			result += ";";
			result += comment.getDate().getTime();
			result += ";";
			result += comment.getText();
			result += ";";
		}		
		return result.substring(0, result.length()-1);
		
	}

	public static Joke getJokeFrom(String line){
		
		String[] data = line.split(";");
		int size = data.length;
		
		String author = data[0];
		String content = data[1];
		String date = data[2];
		String likes = data[3];
		
		String commentsString = getCommentsStringFrom(data);
		ArrayList<Comment> comments = null;
		
		if("".equals(commentsString) || commentsString == null){
			comments = new ArrayList<Comment>();
		}
		else{
			comments = getCommentsListFrom(commentsString);
		}
		Joke joke = new Joke();
		
		joke.setAuthor(author);
		joke.setContent(content);
		joke.setDate(date);
		joke.setLikes(likes);
		joke.setComments(comments);
		
		return joke;
		
	}
	
	public static String getCommentsStringFrom(String[] data){
		String result = "";
		for(int i=4; i< data.length; i++){
			result += data[i];
			result += ";";
		}
		return result.substring(0, result.length()-1);
	}
	
	public static ArrayList<Comment> getCommentsListFrom(String line){
	
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		String[] data = line.split(";");
		int size = data.length;
		
		for(int i=0; i<size; i+=3){
			String author = data[i];
			String date = data[i+1];
			String text = data[i+2];
			Comment comment = new Comment(author, date, text);
			comments.add(comment);
		}
		return comments;
	}
	
	public static int nextKeyNumber(Set<Integer> set){
		int max = 0;
		for(int i : set){
			if(i>max) max = i;
		}
		return max+1;
	}

	public static ArrayList<Joke> getListFromMap(HashMap<Integer, Joke> jokes){
		ArrayList<Joke> result = new ArrayList<Joke>();
		for(int i : jokes.keySet()){
			result.add(jokes.get(i));
		}
		return result;
	}

}
