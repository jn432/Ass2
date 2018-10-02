package ass2;

import java.io.Serializable;
import java.util.Arrays;

abstract class User implements Serializable {
    
    
    protected String username;
    protected char[] password;
    protected final Library LIBRARY;
    
    //constructor
    public User(String username, char[] password, Library library) {
        this.username = username;
        this.password = password;
        this.LIBRARY = library;
    }
    
    
    //get methods
    public String getUsername() {
        return username;
    }
    
    public char[] getPassword() {
        return password;
    }
    //end of get methods
    
    //set methods
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(char[] password) {
        this.password = password;
    }
    //end of set methods
    
    //abstract methods that child classes must have
    abstract public int getUserType();
    
    abstract protected String getDetails();
    //end of abstract methods
    
    //returns true if password matches the account's password, otherwise returns false
    public boolean verifyUser(char[] enteredPassword) {
        if (Arrays.equals(this.password, enteredPassword)) {
            return true;
        }
        return false;
    }
}


