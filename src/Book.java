// GsonDemo (c) 2018 Baltasar MIT License <baltasarq@gmail.com>

/** Represents a book. */
public class Book {
    /** Creates a new book.
      * @param author the author of the book.
      * @param title the title of the book.
      */
    public Book(String author, String title)
    {
        this.title = title;
        this.author = author;
    }
  
    /** @return the title of the book. */
    public String getTitle()
    {
        return this.title;
    }
  
    /** @return the author of the book. */
    public String getAuthor()
    {
        return this.author;
    }
    
    public int hashCode()
    {
        return ( 11 * this.getAuthor().hashCode() )
             + ( 13 * this.title.hashCode() );
    }
    
    public boolean equals(Object obj)
    {
        boolean toret = false;
        
        if ( obj instanceof Book ) {
            Book other = (Book) obj;
            
            toret = ( other.getAuthor().equals( this.getAuthor() )
                   && other.getTitle().equals( this.getTitle() ) );
        }
        
        return toret;
    }
    
    public String toString()
    {
        return this.getAuthor() + ": " + this.getTitle();
    }
  
    private String title;
    private String author;
}
