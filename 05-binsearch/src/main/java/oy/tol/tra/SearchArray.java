package oy.tol.tra;

/**
 * For searching a number from an array of integers.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public class SearchArray {

   private SearchArray() {
      // Empty
   }

   /**
    * Finds a number from the specified array using linear search, -1 if one could
    * not be found.
    * 
    * @param aValue    The value to find.
    * @param fromArray The array to search.
    * @param fromIndex The index to start searching from.
    * @param toIndex   The index to search to in the array.
    * @return The index of the number in the array, -1 if not found.
    */
   public static int linearSearch(int aValue, int[] fromArray, int fromIndex, int toIndex) {
      for (int index = fromIndex; index < toIndex; index++) {
         if (fromArray[index] == aValue) {
            return index;
         }
      }
      return -1;
   }

   /**
    * Finds a number from the specified array using binary search, -1 if one could
    * not be found.
    * Note that binary search works only with sorted arrays.
    * <p>
    * TODO: implement the binary search algorithm.
    * 
    * @param aValue    The value to find.
    * @param fromArray The array to search.
    * @param fromIndex The index to start searching from.
    * @param toIndex   The index to search to in the array.
    * @return The index of the number in the array, -1 if not found.
    */
   public static int binarySearch(int aValue, int[] fromArray, int fromIndex, int toIndex) {

      if (fromIndex < toIndex) {
         int mid = fromIndex + ((toIndex - fromIndex) / 2); // Cut array in half for speed

         if (fromArray[mid] == aValue) { // if value is found return value
            return mid;
         }
         // if middle is bigger than value call recursively to cut array in (bigger side
         // of the array) half again
         if (fromArray[mid] > aValue) {
            return binarySearch(aValue, fromArray, fromIndex, mid - 1);
         } else { // if middle is smaller than value call recursively to cut array in (smaller
                  // side of the array) half again
            return binarySearch(aValue, fromArray, mid + 1, toIndex);
         }
      }
      // if value could not be found return -1
      return -1;
   }
}
