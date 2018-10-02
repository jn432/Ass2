package ass2;

import java.io.Serializable;

class Admin extends User implements Serializable {
    
    public Admin(String username, char[] password, Library library) {
        super(username, password, library);
        System.err.println("Admin created");
    }
    
    public int getUserType() {
        return 3;
    }
    
    protected String getDetails() {
        return "\nUsername: " + username + "\nPassword: " + new String(password) + "\n";
    }
    
    //deletes the user from the list, and also saves the changed list
    public void deleteUser(User u) {
        LIBRARY.getUsers().remove(u.username);
        System.err.println("User deleted");
        LIBRARY.saveLibrary();
    }
    
    //changes attributes for a user, and saves the updates
    public boolean editUser(User u, String username, char[] password, String degree) {
        //find a user with the username
        User checkDuplicate = LIBRARY.findUser(username);
        //if username is already used by an account and is not the same user
        if (checkDuplicate != null && u != checkDuplicate) {
            System.err.println("User not edited");
            return false;
        }
        
        //remove the user from the map, to remove the connection with old key
        LIBRARY.getUsers().remove(u.getUsername());
        
        //edit details of user
        u.username = username;
        u.password = password;
        //if user is a student, then also change degree
        if (u.getUserType() == 1) {
            Student s = (Student) u;
            s.setDegree(degree);
        }
        
        //readd account back in map with new key
        LIBRARY.getUsers().put(username, u);
        
        System.err.println("User edited");
        //save the file
        LIBRARY.saveLibrary();
        return true;
    }
    
    //create a new student and add it to the list
    public Student createStudent(String username, char[] password, String degree) {
        //find a user with the same username
        User u = LIBRARY.findUser(username);
        
        //if username is not used
        if (u == null) {
            //create a student with the input details
            Student s = new Student(username, password, degree, LIBRARY);
            System.err.println("Student created");
            
            //and save the file
            LIBRARY.getUsers().put(username, s);
            LIBRARY.saveLibrary();
            return s;
        }
        
        //a user with the same username was found
        System.err.println("Student not created");
        return null;
    }
    
    
    //create a librarian and add it to the list
    public Librarian createLibrarian(String username, char[] password) {
        //find a user with the same username
        User u = LIBRARY.findUser(username);
        
        //if username is not used
        if (u == null) {
            //create a librarian with the input details
            Librarian l = new Librarian(username, password, LIBRARY);
            System.err.println("Librarian created");
            
            //and save the file
            LIBRARY.getUsers().put(username, l);
            LIBRARY.saveLibrary();
            return l;
        }
        
        //a user with the same username was found
        System.err.println("Librarian not created");
        return null;
    }
}
