package BinarySearchTree;

public class Comparator {
  public Integer compare(Object obj1, Object obj2) {
    if (obj1 instanceof Integer && obj2 instanceof Integer) {
      Integer intObj1 = (Integer) obj1;
      Integer intObj2 = (Integer) obj2;

      return intObj1 - intObj2;
    } else {
      return null;
    }
  }
}
