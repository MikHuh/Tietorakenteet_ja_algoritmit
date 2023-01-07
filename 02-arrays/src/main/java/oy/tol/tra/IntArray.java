package oy.tol.tra;

/**
 * A simple array of integers to be used in testing
 * misbehaving algorithm for reversing the array.
 */
public class IntArray {
   private Integer[] elements = null;

   private IntArray() {
   }

   /**
    * A constructor for building IntArrays.
    * 
    * @param elements the plain Java integer array with numbers to add.
    */
   public IntArray(Integer[] elements) {
      this.elements = new Integer[elements.length];
      for (int counter = 0; counter < elements.length; counter++) {
         this.elements[counter] = elements[counter];
      }
   }

   /**
    * The method to reverse the internal Java int array.
    */
   public void reverse() {
      /*
       * TODO:
       * 1. Edit the test data files to see if the reverse() really works or not.
       * 2. Execute the IntArrayTests to see that some of them fail.
       * 3. Study the code below and try to find what is the issue.
       * 4. Use the debugger to see the execution and variable values if necessary.
       * 5. Fix the issue.
       */
      int i = 0;
      while (i < elements.length / 2) {
         int temp = elements[i];
         elements[i] = elements[elements.length - i - 1];
         elements[elements.length - i - 1] = temp;
         i = i + 1;
      }
   }

   /**
    * Sorts the array to ascending order.
    */
   public void sort() {
      /*
       * TODO:
       * 1. Edit the test data files to see if the reverse() really works or not.
       * 2. Execute the IntArrayTests to see that some of them fail.
       * 3. Study the code below and try to find what is the issue.
       * 4. Use the debugger to see the execution and variable values if necessary.
       * 5. Fix the issue.
       */
      int i = elements.length - 1;
      while (i > 0) {
         if (elements[i] < elements[i - 1]) {
            int tmp = elements[i];
            elements[i] = elements[i - 1];
            elements[i - 1] = tmp;
            i = elements.length;
         }
         i--;
      }
   }

   /**
    * Returns the plain Java int [] array for investigation.
    * 
    * @return The int array.
    */
   public Integer[] getArray() {
      return elements;
   }
}
