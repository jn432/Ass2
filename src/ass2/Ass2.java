package ass2;

public class Ass2 {
    
    private static final String LIBRARYFILE = "library.ser";
    
    public static void main(String[] args) {
        
        //load the library to be used. will create a new library if it doesn't exist
        Library lib = Library.loadLibrary(LIBRARYFILE);
        
        //dummy data
        
        //grab the admin account
        Admin admin = (Admin) lib.findUser("admin");
        
        //if there is no admin account, create it for the library and save
        if (admin == null) {
            admin = new Admin("admin","pass".toCharArray(), lib);
            lib.getUsers().put(admin.getUsername(), admin);
            lib.saveLibrary();
        }
        
        //try to create student accounts
        Student s1 = (Student) lib.findUser("student");
        if (s1 == null) {
            s1 = admin.createStudent("student","pass1".toCharArray(),"Computer Science");
        }
        Student s2 = (Student) lib.findUser("student2");
        if (s2 == null) {
            s2 = admin.createStudent("student2","pass2".toCharArray(),"Mathematics");
        }
        Student s3 = (Student) lib.findUser("student3");
        if (s3 == null) {
            s3 = admin.createStudent("student3","pass3".toCharArray(),"Chemistry");
        }
        
        //try to create librarian accounts
        Librarian l = (Librarian) lib.findUser("librarian");
        if (l == null) {
            l = admin.createLibrarian("librarian","pass".toCharArray());
        }
        
        //books
        Book b1 = lib.findBook(1);
        if (b1 == null) {
            b1 = l.createBook(1, "Programming for idiots", "Mark Sifer", "Level 1");
            
        }
        Book b2 = lib.findBook(2);
        if (b2 == null) {
            b2 = l.createBook(2, "Snek programming", "John Nguyen", "Level 1");
        }
        Book b3 = lib.findBook(3);
        if (b3 == null) {
            b3 = l.createBook(3, "Discrete Mathematics", "Joseph Tonien", "Level 2");
        }

        //records
        s1.reserveBook(b1);
        s1.reserveBook(b3);
        s2.reserveBook(b1);
        s3.reserveBook(b1);
        
        //end of dummy data

        new WindowLogin(lib).setVisible(true);
    }
}
