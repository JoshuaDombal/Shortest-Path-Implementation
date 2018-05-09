// Vertex class

public class Vertex{

  String name;
  String previous;
  String distance;
  String time;
  String price;


  public Vertex(String initDistance , String initPrevious, String initName, String initTime, String initPrice ) {
    name = initName;
    previous = initPrevious;
    distance = initDistance;
    time = initTime;
    price = initPrice;
  }

  public Vertex() {
    name = "vert";
    previous = "prev";
    distance = "0";
    price = "0";
    time = "0";
  }

  //public int editDistance()


}
