package ass2;

import java.io.Serializable;

class Admin extends User implements Serializable {
    
    public Admin(String username, char[] password) {
        super(username, password);
        System.err.println("Admin created");
    }
    
    public int getUserType() {
        return 3;
    }
    
    public String getDetails() {
        return "Username: " + username + "\nPassword: " + password + "\n";
    }
    
    public static boolean createStudent(String username, char[] password, String degree) {
        User u = Ass2.LIB.findUser(username);
        if (u == null) {
            Student s = new Student(username, password, degree);
            System.err.println("Student created");
            Ass2.LIB.getUserList().add(s);
            Ass2.LIB.saveFile(Ass2.LIB.getUserList());
            return true;
        }
        System.err.println("Student not created");
        return false;
    }
    
    public static boolean createLibrarian(String username, char[] password) {
        User u = Ass2.LIB.findUser(username);
        if (u == null) {
            Librarian l = new Librarian(username, password);
            System.err.println("Librarian created");
            Ass2.LIB.getUserList().add(l);
            Ass2.LIB.saveFile(Ass2.LIB.getUserList());
            return true;
        }
        System.err.println("Librarian not created");
        return false;
    }
}
