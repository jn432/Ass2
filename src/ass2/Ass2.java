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
        Student s = admin.createStudent("student","pass".toCharArray(),"Computer Science");
        
        //try to create librarian accounts
        Librarian l = (Librarian) lib.findUser("librarian");
        
        if (l == null) {
            l = admin.createLibrarian("librarian","pass".toCharArray());
        }
        
        //books
        l.createBook(1, "Programming for idiots", "Sifer, Mark", "Level 1");
        //end of dummy data

        new WindowLogin(lib).setVisible(true);
    }
}
