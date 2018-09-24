package ass2;

import java.io.Serializable;
import java.util.Arrays;

abstract class User implements Serializable {
    protected String username;
    protected char[] password;
    
    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public char[] getPassword() {
        return password;
    }
    
    abstract public int getUserType();
    
    abstract public String getDetails();
    
    public static boolean verifyUser(User u, char[] enteredPassword) {
        if (Arrays.equals(u.password, enteredPassword)) {
            return true;
        }
        return false;
    }
}


