import java.util.*;
public class MyComparator implements Comparator<Vertex> {

  public int compare(Vertex a, Vertex b) {
    return a.distance.compareTo(b.distance);
  }

}
