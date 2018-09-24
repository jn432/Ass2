package ass2;

import java.io.Serializable;

class Student extends User implements Serializable {
    
    private String degree;
    
    //constructor
    public Student(String username, char[] password, String degree) {
        super(username, password);
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
    
    public String getDetails() {
        return "\nAccount ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\nDegree: " + degree + "\n";
    }
    

}
