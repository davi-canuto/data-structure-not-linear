package AVL;


public class TreeTest {
  public static void main(String[] args) {
    Tree tree = new Tree();

    tree.insert(10);
    tree.insert(5);
    tree.insert(15);
    tree.insert(22);
    tree.insert(8);
    tree.insert(2);

    tree.show();

    System.out.printf("%s%n", "-".repeat(30));
  }
}