# Baconator
This program attempts to demonstrate the 6 Degrees of Kevin Bacon. It reads 2 files containing every actor and actress in the IMDb, as well as all the movies and TV shows they've starred in. It stores this data in an undirected graph in adjacency list form, and performs a BFS on the graph giving each actor and actress a "Bacon Number". After this calculation, the user will be prompted to enter the name of an actor or actress to receive his or her Bacon Number (and a few tasteless puns).


## Using this program
This project is compiled & run through the Driver class. It functions on the original data files, and relies on the filler text at the beginning of the list files.


## Note: Known Bug
I'm currently unable to find a solution to to my java.lang.OutOfMemoryError when reading the entire database. I'm not sure how to significantly cut down on the runtime & memory footprint without deviating from the HashMap implementation in order to find adjacent vertices (input data as 1 actor + collection of movies vs. 1 movie & collection of actors). I've used only the first 500k entries in each file to make the program functional. I'd appreciate some input on how to resolve this issue.
