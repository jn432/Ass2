package ass2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

class Librarian extends User implements Serializable {
    
    public Librarian(String username, char[] password, Library lib) {
        super(username, password, lib);
    }
    
    
    public int getUserType() {
        return 2;
    }
    
    protected String getDetails() {
        return "\nUsername: " + username + "\nPassword: " + Arrays.toString(password) + "\n";
    }
    
    public void checkOutBook(Student s, int ISBN) {
        Book b = LIBRARY.findBook(ISBN);
        b.borrowBook(s);
    }
    
    public Book createBook(int ISBN, String title, String author, String location) {
        //look for a book with that ISBN
        Book b = LIBRARY.findBook(ISBN);
        
        //if there is no book with that ISBN
        if (b == null) {
            //create the book and put it in the map
            b = new Book(ISBN, title, author, location);
            LIBRARY.getBooks().put(ISBN, b);
            
            //save the library file
            LIBRARY.saveLibrary();
            System.err.println("Book created");
            return b;
        }
        
        //book was not created
        System.err.println("Book not created");
        return null;
    }
    
}
