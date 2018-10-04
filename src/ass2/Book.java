package ass2;

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

class Book implements Serializable {
    
    private Library LIBRARY;
    
    private int isbn;
    private String title;
    private String author;
    private String location;
    private String status;
    private ArrayList<Record> reserveList;
    
    //constructor
    public Book(Library library, int isbn, String title, String author, String location) {
        LIBRARY = library;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.location = location;
        this.status = "Available";
        this.reserveList = new ArrayList<>();
    }
    
    //get methods
    public int getISBN() {
        return isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getReservedStatus() {
        return status;
    }
    
    public ArrayList<Record> getReserveList() {
        return reserveList;
    }
    //end of get methods
    
    //set methods
    public void setISBN(int isbn) {
        this.isbn = isbn;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    //end of set methods
    
    //print out all information of the book
    public void printDetails(JTextArea output) {
        output.append("ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nLocation: " + location + "\nStatus: " + status + "\n");
        for (Record record : reserveList) {
            output.append(record.printRecord());
        }
    }
    
    public void borrowBook(Student s) {
        status = "Borrowed";
    }
    
    public void reserveBook(Student student) {
        if (status.equals("Available")) {
            status = "Reserved";
        }
        Record r = new Record(student);
        reserveList.add(r);
        LIBRARY.saveLibrary();
    }
    
    public void generateRecord(Formatter file) {
        file.format("ISBN: " + isbn + "%nTitle: " + title + "%nAuthor: " + author + "%nLocation: " + location + "%nStatus: " + status + "%n");
        for (Record record : reserveList) {
            record.generateRecord(file);
        }
    }
    
}
