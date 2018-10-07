package ass2;

import java.io.Serializable;

class Librarian extends User implements Serializable {
    
    public Librarian(String username, char[] password, Library lib) {
        super(username, password, lib);
    }
    
    
    public int getUserType() {
        return 2;
    }
    
    protected String getDetails() {
        return "\nUsername: " + username + "\nPassword: " + new String(password) + "\n";
    }
    
    public Book createBook(int ISBN, String title, String author, String location) {
        //look for a book with that ISBN
        Book b = LIBRARY.findBook(ISBN);
        
        //if there is no book with that ISBN
        if (b == null) {
            //create the book and put it in the map
            b = new Book(LIBRARY, ISBN, title, author, location);
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
    
        public boolean editBook(Book b, int ISBN, String title, String author, String location) {
        //find a book with the same ISBN
        Book checkDuplicate = LIBRARY.findBook(ISBN);
        //if ISBN is already used by a book and is not the same book
        if (checkDuplicate != null && b != checkDuplicate) {
            System.err.println("Book not edited");
            return false;
        }
        
        //remove the book from the map, to remove the connection with old key
        LIBRARY.getBooks().remove(b.getISBN());
        
        //edit details of user
        b.setISBN(ISBN);
        b.setTitle(title);
        b.setAuthor(author);
        b.setLocation(location);
        
        //readd account back in map with new key
        LIBRARY.getBooks().put(ISBN, b);
        System.err.println("Book edited");
        //save the file
        LIBRARY.saveLibrary();
        return true;
    }
    
    //deletes the book 
    public void deleteBook(Book b) {
        //delete all records
        for (Record r : b.getReserveList()) {
            //remove all reserve from students
            Student s = r.getStudent();
            s.getRecords().remove(r);
        }
        LIBRARY.getBooks().remove(b.getISBN());
        System.err.println("Book deleted");
        LIBRARY.saveLibrary();
    }
    
    //librarian checks out a book for a student
    public boolean borrowBook(Student s, Book b) {
        String status = b.getReservedStatus();
        if (status.equals("Available")) {
            b.setReservedStatus("Borrowed");
            LIBRARY.saveLibrary();
            return true;
        }
        else if (status.equals("Reserved") && b.getReserveList().get(0).getStudent() == s) {
            b.setReservedStatus("Borrowed");
            b.getReserveList().remove(0);
            s.getRecords().remove(0);
            LIBRARY.saveLibrary();
            return true;
        }
        return false;
    }
    
    //a book is returned
    public boolean returnBook(Book b) {
        if (b.getReserveList().size() > 0) {
            b.setReservedStatus("Reserved");
            LIBRARY.saveLibrary();
            return false;
        }
        b.setReservedStatus("Available");
        LIBRARY.saveLibrary();
        return true;
    }
}
