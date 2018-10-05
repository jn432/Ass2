package ass2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Library implements Serializable {
    
    private final String LIBRARYFILE;
    
    private final HashMap<Integer, Book> BOOKMAP;
    private final HashMap<String, User> USERMAP;
    
    //create library on initializatoin
    public Library(String libraryFile) {
        //set the file path
        LIBRARYFILE = libraryFile;
        //create the hashmaps for users and books
        BOOKMAP = new HashMap<>();
        USERMAP = new HashMap<>();
    }
    
    //get methods
    public HashMap<Integer, Book> getBooks() {
        return BOOKMAP;
    }
    
    public HashMap<String, User> getUsers() {
        return USERMAP;
    }
    //end of get methods
    
    //find a user in the library
    public User findUser(String username) {
        return USERMAP.get(username);
    }
    
    //find a book in the library
    public Book findBook(int ISBN) {
        return BOOKMAP.get(ISBN);
    }
    
    //search for books by Title
    public ArrayList<Book> getBooksByTitle(String search) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book b : BOOKMAP.values()) {
            if (b.getTitle().toLowerCase().equals(search.toLowerCase())) {
                books.add(b);
            }
        }
        return books;
    }
    
    //search for books by Author
    public ArrayList<Book> getBooksByAuthor(String search) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book b : BOOKMAP.values()) {
            if (b.getAuthor().toLowerCase().equals(search.toLowerCase())) {
                books.add(b);
            }
        }
        return books;
    }
    
    //search for books by keyword
    public ArrayList<Book> getBooksByKeyword(String search) {
        HashSet<String> words = new HashSet(Arrays.asList(search.split(search.toLowerCase())));
        ArrayList<Book> books = new ArrayList<>();
        for (Book b : BOOKMAP.values()) {
            for (String word : words) {
                if (b.getAuthor().toLowerCase().contains(word)) {
                    books.add(b);
                    break;
                }
            }
        }
        return books;
    }
    
    //save the library after an update
    public void saveLibrary() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(LIBRARYFILE)));
            output.writeObject(this);
            output.close();
            System.err.println("Library saved");
        }
        catch (IOException e) {
            System.err.println("ERROR at writing " + this + ": " + e);
        }
    }
    
    //try to load a library given a string, if it doesn't exist, then a new library is created
    public static Library loadLibrary(String libraryFile) {
        
        try {
            ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(libraryFile)));
            return (Library) input.readObject();
        }
        catch (NoSuchFileException e) {
                System.err.println("No library file, creating a new library file");
                return new Library(libraryFile);
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR at library loading: " + e);
        }
        return null;
    }
}
