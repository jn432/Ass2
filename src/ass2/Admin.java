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
        return "\nAccount ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\n";
    }
    
    //deletes the user from the list, and also saves the changed list
    public static void deleteUser(User u) {
        Ass2.LIB.getUserList().remove(u);
        System.err.println("User deleted");
        Ass2.LIB.saveFile(Ass2.LIB.getUserList());
    }
    
    //changes attributes for a user, and saves the updates
    public static boolean editUser(User u, String username, char[] password, String degree) {
        User checkDuplicate = Ass2.LIB.findUser(username);
        //if username is already used by an account and is not the same user
        if (checkDuplicate != null && u != checkDuplicate) {
            System.err.println("User not edited");
            return false;
        }
        u.username = username;
        u.password = password;
        //if user is a student
        if (u.getUserType() == 1) {
            Student s = (Student) u;
            s.setDegree(degree);
        }
        Ass2.LIB.saveFile(Ass2.LIB.getUserList());
        System.err.println("User edited");
        return true;
    }
    
    //create a new student and add it to the list
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
    
    
    //create a librarian and add it to the list
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
