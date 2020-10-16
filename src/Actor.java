import java.util.ArrayList;

//*******************************************************************
//	Actor
//
//	An Actor object serves as a vertex in the graph. Color[u], 
//	d[u], and pi[u] are initialized here, saving a step in BFS.
//*******************************************************************

public class Actor {
	private String name;
	private short baconNumber;
	private Actor parent;
	private boolean seen;
	private Movie movieOnPath;
	private ArrayList<Movie> movies;
	
	public Actor(String name) {
		this.name = name;
		baconNumber = Short.MAX_VALUE;
		parent = null;
		setSeen(false);
		movies = new ArrayList<>();
	}
	
	public void setNum(short num) {
		this.baconNumber = num;
	}
	
	public int getNum() {
		return baconNumber;
	}

	public String getName() {
		return name;
	}

	public Actor getParent() {
		return parent;
	}

	public void setParent(Actor parent) {
		this.parent = parent;
	}
	
	public void addToMovies(Movie m) {
		movies.add(m);
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public void setMovieOnPath(Movie m) {
		this.movieOnPath = m;
	}
	
	public Movie getMovieOnPath() {
		return movieOnPath;
	}
}
