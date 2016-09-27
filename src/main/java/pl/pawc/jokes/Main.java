package pl.pawc.jokes;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.Database;

class Main{
	
	public static void main(String args[]){
		Database db = new Database(args[0]);
		Joke joke1 = new Joke("test joke 1");
		Joke joke2 = new Joke("test joke 2");
		db.addJoke(joke1);
		db.addJoke(joke2);
		db.print();
		db.save();
	}

}
