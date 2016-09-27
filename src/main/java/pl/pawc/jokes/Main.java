package pl.pawc.jokes;

import pl.pawc.jokes.model.Joke;
import pl.pawc.jokes.model.Comment;
import pl.pawc.jokes.Database;

class Main{
	
	public static void main(String args[]){
		Database db = new Database(args[0]);
		db.print();
	}

}
