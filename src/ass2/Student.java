package ass2;

import java.io.Serializable;
import java.util.Arrays;

class Student extends User implements Serializable {
    
    private String degree;
    
    //constructor
    public Student(String username, char[] password, String degree, Library lib) {
        super(username, password, lib);
        this.degree = degree;
    }
    
    public String getDegree() {
        return degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public int getUserType() {
        return 1;
    }
    
    protected String getDetails() {
        return "\nUsername: " + username + "\nPassword: " + new String(password) + "\nDegree: " + degree + "\n";
    }
    

}
