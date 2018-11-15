/*
 *Username: (Karanjot Pabla)
 *
 * Node class that creates the Node for the stack
 */

public class Node<E> {
 E item;
 Node<E> next;
 public Node(E item, Node<E> next) {
  this.item=item;
  this.next=next;
 }
}
