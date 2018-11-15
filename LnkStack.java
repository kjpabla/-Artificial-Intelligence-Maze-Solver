/*
 *Username: (Karanjot Pabla)
 *
 * Linked Stack class that implements stack class to store path solution
 */
public class LnkStack<E> implements Stack<E> {
 Node<E> stack;
 
 public LnkStack() {
  stack=null;
 }
 
 public void push(E value) {
  stack=new Node<E>(value,stack);
 }
 
 public E pop() {
  if (isEmpty())
   throw new EmptyException();
  E value=stack.item;
  stack=stack.next;
  return value;
 }
 
 public E top() {
  if (stack==null)
   throw new EmptyException();
  return stack.item;
 }
 
 public boolean isEmpty() {
  return stack==null;
 }
}
