import java.util.*;
import java.io.*;
import java.util.Scanner;

/**
 * Driver program that reads in a graph and prompts user for source and destination for the desired travel route.
 * Do not change this file
 * Add your name here:
 */

public class Routes {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("USAGE: java Routes <vertex_file> <edge_file>");
			System.exit(1);
		}

      Scanner s = null;

      String vertexfile        = args[0];
      String edgefile           = args[1];

      // Open and Read the vertex file
		Collection<String> vertex = new ArrayList<String>();
		vertex = MyGraph.loadVertices(vertexfile);

      // Display the vertices
      System.out.println("Vertices in the graph are: \n");
      MyGraph.displayVertices(vertex);

      // Open and Read the edge file
      Collection<String> edge = new ArrayList<String>();
		edge = MyGraph.loadEdges(edgefile);

      // Display the edges with their weights
      System.out.println("Edges in the graph with their weights (distance, time, price) are: \n");
      MyGraph.displayEdges(edge);

      // Display graph
			System.out.println("GRAPH");
      MyGraph.displayGraph(vertex, edge);


			//Collection<String> cool = MyGraph.findAdjacentVertices("A");
			//Iterator itr = cool.iterator();

			// Tests checkIsAdjacent
			/*
			System.out.println("YOOOOOOOOOOOOOOOO");
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
			*/

      // Construct the Preferred Route
      Scanner console = new Scanner(System.in);

		for(;;) {
			System.out.print("What is your Start vertex? ");
			String start_point = console.nextLine();
			if(!vertex.contains(start_point)) {
				System.out.println("No such vertex in the graph.\n");
				System.exit(0);
			}

			System.out.print("What is your Destination vertex? ");
			String end_point = console.nextLine();
			if(!vertex.contains(end_point)) {
				System.out.println("No such vertex in the graph\n");
				System.exit(1);
			}

            System.out.print("What is your Optimization parameter? (1 = shortest route, 2 = fastest route, 3 = cheapest route, 4 = display all options) ");
			Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
			if(choice < 1 || choice > 4) {
				System.out.println("Invalid option");
				System.exit(2);
			}

			if (choice == 4) {
   			System.out.println("Shortest route from "+start_point+" to "+end_point+" is:\n");
   			List<String> shortestRoute = new ArrayList<String>();
   			int length = MyGraph.findShortestRoute(start_point, end_point, shortestRoute, vertex);
   			for(String hop : shortestRoute)
   				System.out.print(hop+"---> ");
   			System.out.println(length);

            System.out.println("Fastest route from "+start_point+" to "+end_point+" is:\n");
   			List<String> fastestRoute = new ArrayList<String>();
   			int total_time = MyGraph.findFastestRoute(start_point, end_point, fastestRoute, vertex);
   			for(String hop : fastestRoute)
   				System.out.print(hop+"---> ");
   			System.out.println(total_time);


            System.out.println("Cheapest route from "+start_point+" to "+end_point+" is:\n");
   			List<String> cheapestRoute = new ArrayList<String>();
   			int price = MyGraph.findCheapestRoute(start_point, end_point, cheapestRoute, vertex);
   			for(String hop : cheapestRoute)
   				System.out.print(hop+"---> ");
   			System.out.println(price);


         }
         else if (choice == 1){
            System.out.println("Shortest route from "+start_point+" to "+end_point+" is:\n");
   			List<String> route = new ArrayList<String>();

   			int length = MyGraph.findShortestRoute(start_point, end_point, route, vertex);
   			for(String hop : route)
   				System.out.print(hop+"---> ");
   			System.out.println(length);

         }
         else if (choice == 2){
           System.out.println("Fastest route from "+start_point+" to "+end_point+" is:\n");
   			List<String> route = new ArrayList<String>();
   			int total_time = MyGraph.findFastestRoute(start_point, end_point, route, vertex);
				System.out.println("PATH");
   			for(String hop : route)
   				System.out.print(hop+" ");
   			System.out.println(total_time);

         }
         else {
            System.out.println("Cheapest route from "+start_point+" to "+end_point+" is:\n");
   			List<String> route = new ArrayList<String>();
   			int price = MyGraph.findCheapestRoute(start_point, end_point, route, vertex);
   			for(String hop : route)
   				System.out.print(hop+" ");
   			System.out.println(price);

         }

		}

	}


}
