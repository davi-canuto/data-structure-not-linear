package AVL;


public class TreeTest {
  public static void main(String[] args) {
    Tree tree = new Tree();

    tree.insert(10);
    tree.insert(15);
    tree.insert(20);
    tree.insert(25);
    tree.insert(30);
    tree.insert(40);
    tree.insert(35);
    tree.show();
    System.out.printf("%s%n", "-".repeat(30));
  }
}