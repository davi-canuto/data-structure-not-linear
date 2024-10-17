package BinarySearchTree;

public class Comparator {
  public Integer compare(Object obj1, Object obj2) {
    if (obj1 instanceof Number && obj2 instanceof Number) {
      Double numObj1 = ((Number) obj1).doubleValue();
      Double numObj2 = ((Number) obj2).doubleValue();

      return Double.compare(numObj1, numObj2);
    } else {
      return null;
    }
  }
}