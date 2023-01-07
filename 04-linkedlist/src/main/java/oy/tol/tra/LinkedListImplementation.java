package oy.tol.tra;

import javax.lang.model.element.Element;

public class LinkedListImplementation<Element> implements LinkedListInterface<Element> {

   private class Node<E> {
      Node(E data) {
         element = data;
         next = null;
      }

      E element;
      Node<E> next;
   }

   private Node<Element> head = null;
   private int size = 0;

   @Override
   public void add(Element element) throws NullPointerException, LinkedListAllocationException {
      if (element == null) {
         throw new NullPointerException("null");
      }
      Node<Element> Node = new Node<Element>(element);
      if (head == null) {
         head = Node;
      } else {
         Node<Element> n = head;
         while (n.next != null) {
            n = n.next;
         }
         n.next = Node;
      }
      size++;
      if (head == null) {
         throw new LinkedListAllocationException("Not implemented yet!");
      }
   }

   @Override
   public void add(int index, Element element)
         throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      Node<Element> n = head;
      if (index <= -1) {
         throw new IndexOutOfBoundsException("List out of bounds");
      }
      if (index > size) {
         throw new IndexOutOfBoundsException("List out of bounds");
      }
      if (element == null) {
         throw new NullPointerException("null");
      }
      Node<Element> Node = new Node<Element>(element);

      if (head == null || index == 0) {
         {
            Node<Element> p = head;
            head = Node;
            head.next = p;
         }

      } else {
         for (int i = 0; i < index - 1; i++) {
            n = n.next;
         }
         Node<Element> k = n.next;
         n.next = Node;
         Node.next = k;
      }
      size++;

      if (head == null) {
         throw new LinkedListAllocationException("Not implemented yet!");
      }
   }

   @Override
   public boolean remove(Element element) throws NullPointerException {
      if (element == null) {
         throw new NullPointerException("null");
      }
      Node<Element> n = head;
      if (n == null) {
         return false;
      }
      while (n.element != element) {
         if (n.next == null) {
            return false;
         }
         n = n.next;
      }
      n.next = n.next.next;
      return true;
   }

   @Override
   public Element remove(int index) throws IndexOutOfBoundsException {
      if (index < 0) {
         throw new IndexOutOfBoundsException("Index out of bounds");
      }
      if (index > size - 1) {
         throw new IndexOutOfBoundsException("Index out of bounds");
      }
      Node<Element> n = head;
      if (n == null) {
         return null;
      }
      if (index == 0) {
         head = n.next;
         size--;
         return n.element;
      }
      for (int i = 0; i > index; i++) {
         n = n.next;
      }
      Node<Element> b = n.next;
      n.next = n.next.next;
      size--;
      return b.element;
   }

   @Override
   public Element get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index > size - 1) {
         throw new IndexOutOfBoundsException("Index out of bounds");
      }
      Node<Element> n = head;
      if (index == 0) {
         return n.element;
      }
      for (int i = 0; i < index - 1; i++) {
         n = n.next;
      }
      return n.next.element;
   }

   @Override
   public int indexOf(Element element) {
      if (element == null) {
         throw new NullPointerException("null");
      }
      Node<Element> n = head;
      if (n == null) {
         return -1;
      }

      int i = 0;
      for (i = 0; n.element != element; i++) {
         if (n.next == null) {
            return -1;
         }
         n = n.next;
      }
      return i;
   }

   @Override
   public int size() {
      Node<Element> n = head;
      int i = 0;
      if (n != null) {
         i++;
         while (n.next != null) {
            i++;
            n = n.next;
         }
         return i;
      } else {
         return 0;
      }
   }

   @Override
   public void reset() {
      head = null;
      size = 0;
   }

   @Override
   public void reverse() {
      Node<Element> point = head;
      Node<Element> current = null;
      Node<Element> prev = null;
      while (point != null) {
         current = point;
         point = point.next;
         current.next = prev;
         prev = current;

      }
      head = current;

      // TODO: implement this only when doing the task explained the TASK-2.md.
      // This method is not needed in doing the task in the README.md.
   }

}
