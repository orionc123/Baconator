import java.util.HashMap;

//*******************************************************************
//	Graph
//
//	Stores collections of actors and movies using HashMaps.
//*******************************************************************

public class Graph {
	public HashMap<String, Actor> actors;
	public HashMap<String, Movie> movies;
	
	public Graph() {
		actors = new HashMap<>();
		movies = new HashMap<>();
	}
}
