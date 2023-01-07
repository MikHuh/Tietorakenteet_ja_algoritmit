package oy.tol.tra;

/**
 * This class instantiates a queue implementing the {@code QueueInterface}.
 * 
 * @author Antti Juustila
 */
public class QueueFactory {

   private QueueFactory() {
   }

   /**
    * Creates an instance of QueueInterface for Integer type.
    * @param size Number of elements the queue can hold.
    * @return The queue object.
    */
   public static QueueInterface<Integer> createIntegerQueue(int size) {
      // TODO: Implement this when you have finished your QueueImplementation.
      // - Instantiates your queue implementation using Integer as template parameter, 
      // - initialize it to hold number of objects in the size parameter.
      // - and return the object to the caller.
      QueueImplementation<Integer> Queue = new QueueImplementation<>();
      Queue.init(Integer.class, size);
      
      return Queue;
   }

}
