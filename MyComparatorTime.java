import java.util.*;
public class MyComparatorTime implements Comparator<Vertex> {

  public int compare(Vertex a, Vertex b) {
    return a.time.compareTo(b.time);
  }

}
