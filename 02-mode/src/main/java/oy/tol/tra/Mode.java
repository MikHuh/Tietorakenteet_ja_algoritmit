package oy.tol.tra;

import java.util.Arrays;

/**
 * For finding the mode of a number from an array of integers.
 * 
 * Usage: Mode.Result foundMode = Mode.findMode(array);
 * 
 * Result contains the number most often found in the array,
 * as well as the count of the said number.
 */
public class Mode {

   public static class Result {
      public int number = 0;
      public int count = 0;
   }

   private Mode() {
      // Empty
   }

   /**
    * Finds the mode of the specified array, -1 if one could not be found. Mode
    * means the number most often found in the array.
    * 
    * @return The Result, containing the mode number and the count how many time it
    *         was in the array.
    */
   public static Result findMode(int[] array) {
      // TOD: implement this to find the mode of the integer array.
      Result result = new Result();
      result.number = -1;
      result.count = -1;
      int count = 0;
      int mode = -1;
      int maxCount = 0;
      // put numbers in order
      Arrays.sort(array);

      for (int i = 1; i < array.length - 1; i++) {
         if (array[i] == array[i - 1]) {
            count++;
         } else {
            if (count > maxCount) {
               mode = array[i - 1];
               maxCount = count;
            }
            count = 0;
         }
      }

      result.count = maxCount;
      result.number = mode;
      return result;
   }
}