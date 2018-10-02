package ass2;

import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

class Library implements Serializable {
    
    private final String LIBRARYFILE;
    
    private final HashMap<Integer, Book> BOOKMAP;
    private final HashMap<String, User> USERMAP;
    
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
    
    //load or create library on initializatoin
    public Library(String libraryFile) {
        //set the file path
        LIBRARYFILE = libraryFile;
        //create the hashmaps for users and books
        BOOKMAP = new HashMap<>();
        USERMAP = new HashMap<>();
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
