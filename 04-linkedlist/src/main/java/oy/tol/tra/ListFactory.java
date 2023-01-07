package oy.tol.tra;

import java.sql.Struct;
import javax.crypto.spec.IvParameterSpec;
import javax.lang.model.element.Element;
import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * This class creates different types of lists implementing the {@code LinkedListInterface} interface.
 * 
 * @author Antti Juustila
 */
public class ListFactory {
   /**
    * Creates an instance of ListImplementation for String type.
    * @return The list object.
    */
    

   public static LinkedListInterface<String> createStringLinkedList(){
      
      // TODO:
      // - Instantiate your list implementation, 
      // - initialize it
      // - and return the list object to the caller. 
      
      LinkedListImplementation<String> ok = new LinkedListImplementation<>();


      return ok;
   }
   
}
