// GsonDemo (c) 2018 Baltasar MIT License <baltasarq@gmail.com>

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Ppal {
    public static String ETQ_AUTHOR = "author";
    public static String ETQ_TITLE = "title";
    
    /** Reads all books from a json file.
      * @param fn The name of the json file.
      */
    public static void read(String fn)
    {
        Collection<Book> books = new ArrayList<>();
        
        try {
            JsonReader jreader = new JsonReader( new FileReader( fn ) );
            BooksPersistence.readBooks( jreader, books );
            
            for(Book book: books) {
                System.out.println( book );
            }
        } catch(FileNotFoundException exc) {
            System.err.println( "ERROR: file not found: " + fn );
        } catch(IOException exc) {
            System.err.println( "ERROR reading: " + fn
                                   + ": " + exc.getMessage() );
        }
    
        return;
    }
    
    /** Writes all given books from a json file.
      * @param fn The name of the json file.
      */
    public static void write(String fn)
    {   
        Collection<Book> books = new ArrayList<>();
        
        // Create books to save
        books.add( new Book( "Alejandro Dumas", "El conde de Montecristo" ) );
        books.add( new Book( "Anónimo", "Cantar de Mío Cid" ) );
        books.add( new Book( "Miguel de Cervantes Saavedra", "Don Quijote de la Mancha" ) );
        
        // Save books
        try (JsonWriter jwriter = new JsonWriter( new FileWriter( fn ) )) {
            BooksPersistence.writeBooks( jwriter, books );
        } catch(FileNotFoundException exc) {
            System.err.println( "ERROR: file not found: " + fn );
        } catch(IOException exc) {
            System.err.println( "ERROR writing: " + fn
                                   + ": " + exc.getMessage() );
        }
    
        return;
    }
  
    public static void main(String[] args)
    {
        if ( args.length > 0 ) {
            write( args[ 0 ] );
            read( args[ 0 ] );
        } else {
            System.out.println( "No json file given" );
        }
        
        return;
    }
}
