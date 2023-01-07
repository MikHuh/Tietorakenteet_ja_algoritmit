package com.anttijuustila.tira;

/**
 * 
 * <p>
 * Implement the <code>createBook()</code> method to return your instance of the
 * Book interface.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public final class BookFactory {
    private BookFactory() {
    }

    public static Book createBook() {
        Book theBook = new BookImplementation();
        return theBook;
    }
}
