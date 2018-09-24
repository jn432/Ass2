package ass2;

import java.io.Serializable;
import java.util.ArrayList;

class Librarian extends User implements Serializable {
    
    public Librarian(String username, char[] password) {
        super(username, password);
    }
    
    
    public int getUserType() {
        return 2;
    }
    
    public String getDetails() {
        return "\nAccount ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\n";
    }
    
    public static void checkOutBook(Student s, int ISBN) {
        Book b = Ass2.LIB.findBook(ISBN);
        b.borrowBook(s);
    }
    
    public static boolean createBook(int ISBN, String title, String author, ArrayList<String> keyword, String location) {
        Book b = Ass2.LIB.findBook(ISBN);
        if (b == null) {
            b = new Book(ISBN, title, author, keyword, location);
            Ass2.LIB.getBookList().add(b);
            Ass2.LIB.saveFile(Ass2.LIB.getBookList());
            System.err.println("Book created");
            return true;
        }
        System.err.println("Book not created");
        return false;
    }
    
}
