// Creation of an edge class

public class Edge {

  String startPoint;
  String endPoint;
  String distance;
  String time;
  String price;


  // returns the start point of the edge_file
  public Edge(String initStartPoint, String intitEndPoint, String initDistance, String initTime, String initPrice) {
    startPoint = initStartPoint;
    endPoint = intitEndPoint;
    distance = initDistance;
    time = initTime;
    price = initPrice;
  }
/*
  public Edge(Object e) {
    startPoint = e.startPoint;
    endPoint = e.endPoint;
    distance = e.distance;
    time = e.time;
    price = e.price;
  }*/

  public String toString() {
    return  startPoint + " " + endPoint + " " + distance + " " + time + " " + price;
  }
  public String[] toArray() {
    String[] a = {startPoint, endPoint, distance, time, price};
    return a;
  }



}
