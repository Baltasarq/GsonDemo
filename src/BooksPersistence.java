// GsonDemo (c) 2018 Baltasar MIT License <baltasarq@gmail.com>

import java.io.IOException;
import java.util.Collection;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/** Util class for saving and retrieving books. */
public class BooksPersistence {
    public static String ETQ_AUTHOR = "author";
    public static String ETQ_TITLE = "title";
    
    /** @return A book read from JSON. */
    public static Book readBook(JsonReader reader) throws IOException
    {
        String author = "";
        String title = "";
        
        reader.beginObject();
        
        while( reader.hasNext() ) {
            String label = reader.nextName();
            
            if ( label.equals( ETQ_AUTHOR ) ) {
                author = reader.nextString();
            }
            else
            if ( label.equals( ETQ_TITLE ) ) {
                title = reader.nextString();
            } else {
                // Unknown name found
                reader.skipValue();
            }
        }
        
        reader.endObject();
        
        if ( author.isEmpty() ) {
            throw new IOException( "missing book's author" );
        }
        
        if ( title.isEmpty() ) {
            throw new IOException( "missing book's title" );
        }
        
        return new Book( author, title );
    }
  
    /** Reads a list of books from JSON.
      * @param reader the reader to get the list of books from.
      * @param books the collection to fill with the read books.
      */
    public static void readBooks(JsonReader reader, Collection<Book> books) throws IOException
    {
        reader.beginArray();
    
        while ( reader.hasNext() ) {
            books.add( readBook( reader ) );
        }
    
        reader.endArray();
    }
    
    /** Writes a list of books to JSON.
      * @param writer the writer to output the list of books to.
      * @param books the collection containing all the books.
      */
    public static void writeBooks(JsonWriter writer, Collection<Book> books) throws IOException
    {
        writer.beginArray();
    
        for(Book book: books) {
            writer.beginObject();
            writer.name( ETQ_AUTHOR ).value( book.getAuthor() );
            writer.name( ETQ_TITLE ).value( book.getTitle() );
            writer.endObject();
        }
    
        writer.endArray();
    }
}
