package AVL;


public class TreeTest {
  public static void main(String[] args) {
    Tree tree = new Tree();

    tree.insert(10);
    tree.insert(5);
    tree.insert(4);
    tree.show();
    System.out.printf("%s%n", "-".repeat(30));
  }
}