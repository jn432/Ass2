package ass2;

import java.io.Serializable;
import java.util.ArrayList;

class Student extends User implements Serializable {
    
    private String degree;
    private ArrayList<Record> reserveList;
    
    //constructor
    public Student(String username, char[] password, String degree, Library lib) {
        super(username, password, lib);
        this.degree = degree;
        reserveList = new ArrayList<>();
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
    
    public ArrayList<Record> getRecords() {
        return reserveList;
    }
    
    protected String getDetails() {
        return "\nUsername: " + username + "\nPassword: " + new String(password) + "\nDegree: " + degree + "\n";
    }
    
    public boolean reserveBook(Book b) {
        for (Record r : reserveList) {
            if (r.previouslyReserved(this, b)) {
                System.err.println("Person has previously borrowed this book");
                return false;
            }
        }
        if (b.getReservedStatus().equals("Available")) {
            b.setReservedStatus("Reserved");
        }
        Record record = new Record(this, b);
        reserveList.add(record);
        b.getReserveList().add(record);
        LIBRARY.saveLibrary();
        return true;
    }

}
