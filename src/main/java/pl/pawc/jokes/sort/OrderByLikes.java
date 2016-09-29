package pl.pawc.jokes.sort;

import java.util.Comparator;

import pl.pawc.jokes.model.Joke;

public class OrderByLikes implements Comparator<Joke>{

	@Override
	public int compare(Joke j1, Joke j2){
		return j1.getLikes() > j2.getLikes() ? 1 : (j1.getLikes() < j2.getLikes() ? -1 : 0 );
	}

}
