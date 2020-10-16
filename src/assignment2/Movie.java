package assignment2;

import java.util.ArrayList;

//*******************************************************************
//	Movie
//
//	A Movie object stores all the actors that have starred in the
//	same movie. This serves as an edge in the graph, or a way to
//	link Actors in the adjacency list.
//*******************************************************************

public class Movie {
	private String name;
	private ArrayList<Actor> cast;
	
	public Movie(String name) {
		this.name = name;
		cast = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	
	public void addToCast(Actor a) {
		cast.add(a);
	}
	
	public ArrayList<Actor> getCast() {
		return cast;
	}
}
