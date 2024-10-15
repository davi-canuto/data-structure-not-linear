
package BinarySearchTree;

public class BSTreeTest {
  public static void main(String[] args) {
    BSTree tree = new BSTree();

    tree.insert(10);
    tree.insert(5);
    tree.insert(8);
    tree.insert(15);
    tree.insert(22);
    tree.insert(30);

    tree.show();

    tree.remove(10);

    tree.show();
  }
}
