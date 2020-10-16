package assignment2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//*******************************************************************
//	Driver
//
//	This class contains the majority of the work to perform the 6
//	Degrees of Kevin Bacon. It reads 2 files containing every actor
//	and actress in the IMDb, as well as all the movies and TV shows
//	they've starred in. It stores this data in an undirected graph
//	in adjacency list form, and performs a BFS on the graph giving
//	each actor and actress a "Bacon Number". After this calculation,
//	the user will be prompted to enter the name of an actor or actress
//	to receive his or her Bacon Number (and a few tasteless puns).
//
//	@author Orion Carroll
//*******************************************************************

public class Driver {
	private Graph g;

	public Driver() {
		g = new Graph();
	}

	/*
	 * This method reads the two input files, and creates one graph containing the
	 * names of the actors and actresses as vertices, and the names of the movies
	 * they've appeared in together as edges.
	 */
	public void readIn() throws IOException {
		// Create input stream for actors.list, and scan through filler text
		FileInputStream inputStream1 = new FileInputStream(new File("actors.list"));
		Scanner sc = new Scanner(inputStream1, "UTF-8");
		String line = sc.nextLine();
		while (!line.equals("THE ACTORS LIST")) {
			line = sc.nextLine();
		}
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		// Parse through actors & movies in actors.list
		Actor a = null;
		int count = 0;
		// while (sc.hasNextLine()) {

		// Only uses the first 500k actor entries due to memory issues
		while (count < 500000) {

			line = sc.nextLine();
			if (!line.equals("")) {
				if (line.charAt(0) != 9) { // actor + tabs + movie
					String[] split = line.split("	");
					a = new Actor(split[0]);
					g.actors.put(split[0], a);
					split = split[split.length - 1].split(" \\[");
					Movie m = g.movies.get(split[0]);
					if (m == null) {
						m = new Movie(split[0]);
						g.movies.put(split[0], m);
					}
					a.addToMovies(m);
					m.addToCast(a);

					count++;

				} else { // movie only
					String[] split = line.split(" \\[");
					Movie m = g.movies.get(split[0].trim());
					if (m == null) {
						m = new Movie(split[0]);
						g.movies.put(split[0], m);
					}
					a.addToMovies(m);
					m.addToCast(a);
				}
			}
		}
		if (sc.ioException() != null) {
			sc.close();
			throw sc.ioException();
		}
		if (inputStream1 != null) {
			inputStream1.close();
		}
		if (sc != null) {
			sc.close();
		}

		// Repeat process for actresses.list
		FileInputStream inputStream2 = new FileInputStream(new File("actresses.list"));
		Scanner sc2 = new Scanner(inputStream2, "UTF-8");
		line = sc2.nextLine();
		while (!line.equals("THE ACTRESSES LIST")) {
			line = sc2.nextLine();
		}
		sc2.nextLine();
		sc2.nextLine();
		sc2.nextLine();
		a = null;
		count = 0;
		// while (sc2.hasNextLine()) {
		
		// Only uses the first 500k actor entries due to memory issues
		while (count < 500000) {
			
			line = sc2.nextLine();
			if (!line.equals("")) {
				if (line.charAt(0) != 9) {
					String[] split = line.split("	");
					a = new Actor(split[0]);
					g.actors.put(split[0], a);
					split = split[split.length - 1].split(" \\[");
					Movie m = g.movies.get(split[0]);
					if (m == null) {
						m = new Movie(split[0]);
						g.movies.put(split[0], m);
					}
					a.addToMovies(m);
					m.addToCast(a);

					count++;

				} else {
					String[] split = line.split(" \\[");
					Movie m = g.movies.get(split[0].trim());
					if (m == null) {
						m = new Movie(split[0]);
						g.movies.put(split[0], m);
					}
					a.addToMovies(m);
					m.addToCast(a);
				}
			}
		}
		if (sc2.ioException() != null) {
			sc2.close();
			throw sc2.ioException();
		}
		if (inputStream2 != null) {
			inputStream2.close();
		}
		if (sc2 != null) {
			sc2.close();
		}
	}

	/*
	 * This method performs a breadth-first search on all actors/actresses starting
	 * at Kevin Bacon. After this method runs, every actor/actress will have their
	 * Bacon Number set.
	 */
	public void bfs() {
		// Initialize start vertex
		Actor bacon = g.actors.get("Bacon, Kevin (I)");
		bacon.setSeen(true);
		bacon.setNum((short) 0);
		Queue<Actor> q = new LinkedList<>();
		q.add(bacon);
		// Crawl through adjacencies BFS style
		while (!q.isEmpty()) {
			Actor u = q.remove();
			ArrayList<Movie> uStarsIn = u.getMovies();
			for (Movie m : uStarsIn) {
				ArrayList<Actor> costars = m.getCast();
				for (Actor v : costars) {
					if (!v.isSeen()) {
						v.setSeen(true);
						v.setNum((short) (u.getNum() + 1));
						v.setParent(u);
						v.setMovieOnPath(m);
						q.add(v);
					}
				}
			}
		}
	}

	/*
	 * Prints to standard output the Bacon Number & path from the specified
	 * actor/actress to Kevin Bacon.
	 * 
	 * @param name Actor/actress specified by the user
	 */
	public void printPath(String name) {
		Actor current = g.actors.get(name);
		if (current == null) {
			System.out.println("No entry found for " + name + ".");
			return;
		} else if (current.getNum() == Short.MAX_VALUE) {
			System.out.println(current.getName() + "has a Bacon number of infinity.");
			return;
		}
		System.out.println(name + " has a Bacon number of " + current.getNum() + ":");
		while (current.getNum() > 0) {
			System.out.println(current.getName() + " was in");
			System.out.println("	" + current.getMovieOnPath().getName() + " with");
			current = current.getParent();
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Orion Carroll");
		System.out.println("The University of Wisconsin - La Crosse");
		System.out.println("CS 353 - Fall 2018\n");
		System.out.println(
				"This program demonstrates the Six Degrees of Kevin Bacon!\nThe theory is that there are six or fewer acquaintance links between any two people on Earth.\nIn this case, there are six or fewer links between any actor/actress and Kevin Bacon. \nAfter a few minutes of calculation you can enter the name of any actor/actress, and see how they're linked to Kevin Bacon.\nPlease wait while we prime the pork.\n");
		Driver d = new Driver();
		d.readIn();
		System.out.println("Sizzling...\n");
		d.bfs();
		System.out.println("Done!");
		boolean done = false;
		while (!done) {
			System.out.println("Enter the name of an actor or actress: [Last, First]");
			Scanner scan = new Scanner(System.in);
			String userInput = scan.nextLine();
			d.printPath(userInput);
			System.out.println("Keep Baconating? [Yes/No]");
			if (scan.nextLine().equalsIgnoreCase("No")) {
				done = true;
				scan.close();
			}
		}
		System.out.println("There you go bacon my heart again.");
	}
}
