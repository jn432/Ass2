package ass2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Ass2 {
    
    public static Library LIB;
    
    public static void main(String[] args) {
        
        //create the library to be used, and also set the 3 output file paths
        LIB = new Library("books.ser", "users.ser", "records.ser");
        
        //dummy data
        
        //user accounts
        Admin.createStudent("student","pass".toCharArray(),"Computer Science");
        Admin.createLibrarian("librarian","pass".toCharArray());
        
        //if there is no admin account, create it and save
        if (LIB.findUser("admin") == null) {
            Admin admin = new Admin("admin","pass".toCharArray());
            LIB.getUserList().add(admin);
            LIB.saveFile(LIB.getUserList());
        }
        
        //books
        ArrayList<String> keyword = new ArrayList<String>();
        keyword.add("Computing");
        keyword.add("C#");
        keyword.add("Apple");
        keyword.add("Windows");
        Librarian.createBook(1, "Programming for idiots", "Sifer, Mark", keyword, "Level 1");
        //end of dummy data

        new WindowLogin().setVisible(true);
    }
}
