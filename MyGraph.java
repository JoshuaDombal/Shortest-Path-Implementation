import java.util.*;
import java.io.*;
import java.util.Scanner;


/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 *  Josh Dombal
 *  2016
 */
public class MyGraph {

    public static HashMap<String, LinkedList<String[]>> adj = new HashMap<String, LinkedList<String[]>>();


      // Loads and returns the collection of vertices of this graph
   	//Postcondition
      // - A Collection of vertices is returned
   	public static Collection<String> loadVertices(String vertexfile) {
       Collection <String> vertices = new ArrayList<String>();

       // Iterates through the given text file and adds each line to the collection
       try{
         Scanner input = new Scanner(new File(vertexfile));
         while (input.hasNextLine()) {
           String vert = input.nextLine();
           vertices.add(vert);
         }
       }
       catch (FileNotFoundException e) {
         System.out.println("File does not exist");
         System.exit(0);
       }

          return vertices;

   	}

   	// Loads and returns the collection of edges of this graph
   	//Postcondition
      // - A Collection of edges is returned
   	public static Collection<String> loadEdges(String edgefile) {

          Collection <String> edge = new ArrayList<String>();

          try {
            Scanner input = new Scanner(new File(edgefile));
            while(input.hasNextLine()) {
              String edgeValue = input.nextLine() + " " + input.nextLine() + " " + input.nextLine() + " " + input.nextLine() + " " + input.nextLine();
              edge.add(edgeValue);
            }
          }
          catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.exit(1);
          }

          return edge;

   	}

      // Displays the collection of vertices of this graph
      // Precondition
      //  - vertex is a collection of vertices
   	public static void displayVertices(Collection<String> vertex) {
   	    System.out.println(Arrays.toString(vertex.toArray()));
         return;
   	}


      // Displays the collection of edges of this graph
      // Precondition
      //  - edge is a collection of edges
   	public static void displayEdges(Collection<String> edge) {

   	   System.out.println(Arrays.toString(edge.toArray()));

         return;

   	}

      // Display graph
      // Precondition
      //  - vertex is a collection of vertices
      //  - edge is a collection of edges
      // Postcondition
      // Display an adjacency list or adjacency matrix of the graph
   	public static void displayGraph(Collection<String> vertex, Collection<String> edge) {

      String[] p = edge.toArray(new String[edge.size()]);

      Iterator itr = vertex.iterator();

      // Iterates through the verteces
      while(itr.hasNext()) {
        String verteces = (String)itr.next();
        LinkedList<String[]> edgeA = new LinkedList<String[]>();
        System.out.print(verteces);

        // Iterates through all the edges
        for (int i = 0; i < edge.size(); i ++) {
          String[] eValues = new String[4];
          String[] values = p[i].split(" ");

          // if the vertex is equal to the starting point, add an adjacent edge
          if (verteces.equals(values[0])) {
            eValues[0] = values[1];
            eValues[1] = values[2];
            eValues[2] = values[3];
            eValues[3] = values[4];

            // Saves the edge in a linked list of edges
            edgeA.add(eValues);

            // Prints out the edge
            System.out.print(" ---> " +Arrays.toString(eValues));

          }


        }
        System.out.println();

        // Adds each vertece with their adjacent edges into a hashmap
        MyGraph.adj.put(verteces, edgeA);
      }

         return;

   	}



   	// Identifies advjacent vertices for a given vertex
   	// Precondition
      //  - input vertex, v is one of the vertices in the graph
   	// Postcondition
      //  - returns a collection of vertices adjacent to v in the graph

   	public static Collection<String> findAdjacentVertices(String vertex) {

          Collection <String> adjVertices = new ArrayList<String>();

          LinkedList<String[]> values = MyGraph.adj.get(vertex);
          Iterator itr = values.iterator();

          // Iterates through each edge in the values corresponding to the given vertex
          while (itr.hasNext()) {
            String[] oneValue = (String[])itr.next();
            adjVertices.add(oneValue[0]);
          }


          return adjVertices;

   	}


      // Checks whether vertex end_point is adjacent to vertex start_point (i.e. start_point -> end_point) in a directed graph.
      //Precondition
      // - Vertex a is the start_point
      // - Vertex b is the end_point
      //Postcondition
      // - Returns an array which will contain distance, time_needed, and ticket_price of edge if there is a directed edge from start_point to end_point
      // - Returns -1 otherwise.
      // - (Also, returns -1 if one of the two vertices does not exist.)

   	public static int[] checkIsAdjacent(String a, String b) {

      // Array that contains distance, time needed and ticket price
      int[] value = new int[3];

   	    LinkedList<String[]> values = adj.get(a);

        Iterator itr = values.iterator();

        // Iterates through each value adjacent to the start point
        while(itr.hasNext()) {
          String[] oneValue = (String[])itr.next();

          // Checks if the the first element of the value in the hashMap is equal to the end point
          // If it is, the distance, time and price are added to the value array
          if (oneValue[0].equals(b)) {
            value[0] = Integer.valueOf(oneValue[1]);
            value[1] = Integer.valueOf(oneValue[2]);
            value[2] = Integer.valueOf(oneValue[3]);
            return value;
          }
        }

          value[0] = -1;
          return value;

   	}

   	// Identifies the shortest route from start_point to end_point.
   	// Precondition
      //  - start_point is the starting vertex
      //  - end_point is the destination vertex
   	//  - route is a list in which the route will be stored, in order, beginning with the start_point and ending with the end_point
      //  - the list will be empty if no such route exists.
      //Postcondition
   	//  - returns the length of the shortest route from start_point to end_point
      //  -1 if no such path exists.
      //  - if multiple such route exists, return only 1 route satisfying the criteria

   	public static int findShortestRoute(String start_point, String end_point, List<String> route, Collection<String> vertex) {

        Comparator<Vertex> comp = new MyComparator();

        // Holds the vertices, with the min value on top
   	    //PriorityQueue<Vertex> verts = new PriorityQueue<Vertex>(vertex.size(), comp);
        LinkedList<Vertex> verts = new LinkedList<Vertex>();

        // An empty hashmap for the verteces once they have been pulled off the queue
        HashMap<String, Vertex> settled = new HashMap<String, Vertex>();


        Iterator itr = vertex.iterator();

        // Initializes all of the verteces
        while (itr.hasNext()) {
          String vertece = (String)itr.next();

          // If the vertece is not equal to the start point, the distance is set to infinity
          if (!vertece.equals(start_point)) {
              Vertex v = new Vertex("999999999", "null", vertece, "999999999", "999999999");
              verts.add(v);

          // The start vertex distance is initialized at 0
          } else {
            Vertex v = new Vertex( "0", "null", vertece, " ", " ");
            verts.add(v);

          }

       }

       while (!verts.isEmpty()) {
         Vertex minVert = verts.poll();

         System.out.println("Distance " + minVert.name);
         settled.put(minVert.name, minVert);

         LinkedList<String[]> edges = new LinkedList<String[]>();

         // Only updates vertices if the vertex we are looking at has adjacent edges
         if (!(adj.get(minVert.name) == null)) {
             edges = adj.get(minVert.name);


             Iterator it = edges.iterator();

             Iterator vertIt = verts.iterator();

             // Loops through the edges of the minVertex of the priority queue
             while (it.hasNext()) {
               String[] ed = (String[])it.next();
               Edge currEdge = new Edge(" ", ed[0], ed[1], ed[2], ed[3]);

               int newDistance = Integer.parseInt(currEdge.distance) + Integer.parseInt(minVert.distance);
               Vertex currVert = new Vertex();

               // Looks through all the vertices
               for (Vertex v: verts) {
                 Vertex tempVert = v;

                 // If the endPoint of the current edge is equal to the current vertex name, then currVert is set to tempVert
                 if (currEdge.endPoint.equals(tempVert.name)) {
                   currVert = tempVert;

                   // If the new distance is smaller than the previous distance, the distance is updated
                   if (newDistance < Integer.parseInt(currVert.distance)) {
                     String dis = Integer.toString(newDistance);
                     Vertex updatedVertex = new Vertex(dis, minVert.name, currVert.name, "999999999", "999999999");

                     System.out.println("Just updated " + updatedVertex.name + " to " + updatedVertex.distance);
                     verts.remove(currVert);
                     verts.add(updatedVertex);
                   }
                   break;

                 }

               }

           }

        }


       }

       String curr = end_point;

       route.add(end_point);

       // Adds the route one vertex at a time into the route collection
       while (!(settled.get(curr).previous.equals("null"))) {
         String previous = settled.get(curr).previous;
         route.add(previous);
         curr = previous;

       }

       // Reverses the path so that it starts from the start point
       Collections.reverse(route);

       int finalDist = Integer.parseInt(settled.get(end_point).distance);
       return finalDist;


   	}


    // Identifies the fastest route from start_point to end_point.
  // Precondition
    //  - start_point is the starting vertex
    //  - end_point is the destination vertex
  //  - route is a list in which the route will be stored, in order, beginning with the start_point and ending with the end_point
    //  - the list will be empty if no such route exists.
    //Postcondition
  //  - returns the total_time of the fastest route from start_point to end_point
    //  -1 if no such path exists.
    //  - if multiple such route exists, return only 1 route satisfying the criteria

  public static int findCheapestRoute(String start_point, String end_point, List<String> route, Collection<String> vertex) {

    Comparator<Vertex> comp = new MyComparator();
    PriorityQueue<Vertex> verts = new PriorityQueue<Vertex>(vertex.size(), comp);
    HashMap<String, Vertex> settled = new HashMap<String, Vertex>();

    Iterator itr = vertex.iterator();


    // Initializes all of the verteces
    while (itr.hasNext()) {
      String vertece = (String)itr.next();

      if (!vertece.equals(start_point)) {
          Vertex v = new Vertex("999999999", "null", vertece, "999999999", "999999999");
          verts.add(v);

      } else {
        Vertex v = new Vertex( "0", "null", vertece, "0", "0");
        verts.add(v);

      }

   }

   // Continuously loops until the queue of vertices is empty
   while (!verts.isEmpty()) {
     Vertex minVert = verts.poll();

     System.out.println("Price" + minVert.name);
     settled.put(minVert.name, minVert);

     LinkedList<String[]> edges = new LinkedList<String[]>();
     if (!(adj.get(minVert.name) == null)) {
         edges = adj.get(minVert.name);


       Iterator it = edges.iterator();

       Iterator vertIt = verts.iterator();

       // Loops through the edges of the minVertex of the priority queue

       while (it.hasNext()) {

         String[] ed = (String[])it.next();
         Edge currEdge = new Edge(" ", ed[0], ed[1], ed[2], ed[3]);
         //String destination = currEdge[0];
         int newPrice = Integer.parseInt(currEdge.price) + Integer.parseInt(minVert.price);
         Vertex currVert = new Vertex();

         // Loops through all the vertices
         for (Vertex v: verts) {
           Vertex tempVert = v;
           if (currEdge.endPoint.equals(tempVert.name)) {
             currVert = tempVert;

             // If the new price is less than the previous price, the price is updated
             if (newPrice < Integer.parseInt(currVert.price)) {
               String nPrice = Integer.toString(newPrice);
               Vertex updatedVertex = new Vertex("999999999", minVert.name, currVert.name, "999999999", nPrice);

               verts.remove(currVert);
               verts.add(updatedVertex);
             }
             break;

           }

         }

       }

    }


   }

   String curr = end_point;

   route.add(end_point);

   // Adds the route one vertex at a time into the route collection
   while (!(settled.get(curr).previous.equals("null"))) {
     String previous = settled.get(curr).previous;
     route.add(previous);
     curr = previous;

   }

   // Reverses the route so that it displays starting at the start node
   Collections.reverse(route);

   int finalPrice = Integer.parseInt(settled.get(end_point).price);
   return finalPrice;


  }



     // Identifies the shortest route from start_point to end_point.
   	// Precondition
      //  - start_point is the starting vertex
      //  - end_point is the destination vertex
   	//  - route is a list in which the route will be stored, in order, beginning with the start_point and ending with the end_point
      //  - the list will be empty if no such route exists.
      //Postcondition
   	//  - returns the length of the shortest route from start_point to end_point
      //  -1 if no such path exists.
      //  - if multiple such route exists, return only 1 route satisfying the criteria


   	public static int findFastestRoute(String start_point, String end_point, List<String> route, Collection<String> vertex) {

      Comparator<Vertex> comp = new MyComparator();
      PriorityQueue<Vertex> verts = new PriorityQueue<Vertex>(vertex.size(), comp);
      HashMap<String, Vertex> settled = new HashMap<String, Vertex>();

      Iterator itr = vertex.iterator();


      // Initializes all of the verteces
      while (itr.hasNext()) {
        String vertece = (String)itr.next();

        if (!vertece.equals(start_point)) {
            Vertex v = new Vertex("999999999", "null", vertece, "999999999", "999999999");
            verts.add(v);

        } else {
          Vertex v = new Vertex( "0", "null", vertece, "0", "0");
          verts.add(v);

        }

     }
     // Continuously loops until the queue of vertices is empty
     while (!verts.isEmpty()) {
       Vertex minVert = verts.poll();

       System.out.println("Time " + minVert.name);
       settled.put(minVert.name, minVert);

       LinkedList<String[]> edges = new LinkedList<String[]>();

       // Only updates vertices if the vertex we are looking at has adjacent edges
       if (!(adj.get(minVert.name) == null)) {
           edges = adj.get(minVert.name);


         Iterator it = edges.iterator();


         Iterator vertIt = verts.iterator();

         // Loops through the edges of the minVertex of the priority queue
         while (it.hasNext()) {

           String[] ed = (String[])it.next();
           Edge currEdge = new Edge(" ", ed[0], ed[1], ed[2], ed[3]);

           int newTime = Integer.parseInt(currEdge.time) + Integer.parseInt(minVert.time);
           Vertex currVert = new Vertex();

            // Looks through all the vertices
           for (Vertex v: verts) {
             Vertex tempVert = v;
             if (currEdge.endPoint.equals(tempVert.name)) {
               currVert = tempVert;

               // If the new time is smaller than the previous time, the time is updated
               if (newTime < Integer.parseInt(currVert.time)) {
                 String nTime = Integer.toString(newTime);
                 Vertex updatedVertex = new Vertex("999999999", minVert.name, currVert.name, nTime, "999999999");

                 verts.remove(currVert);
                 verts.add(updatedVertex);
               }
               break;

             }

           }

         }

      }


     }

     String curr = end_point;

     route.add(end_point);

     // Adds the route one vertex at a time into the route collection
     while (!(settled.get(curr).previous.equals("null"))) {
       String previous = settled.get(curr).previous;
       route.add(previous);
       curr = previous;

     }

     Collections.reverse(route);

     int finalTime = Integer.parseInt(settled.get(end_point).time);
     return finalTime;

   	}



}
