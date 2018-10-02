package ass2;

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class Book implements Serializable {
    
    private int isbn;
    private String title;
    private String author;
    private String location;
    private String status;
    private ArrayList<Record> reserveList;
    
    
    public Book(int isbn, String title, String author, String location) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.location = location;
        this.status = "Available";
        this.reserveList = new ArrayList<Record>();
    }
    
    public int getISBN() {
        return isbn;
    }
    
    public String getReservedStatus() {
        return status;
    }
    
    //print out all information of the book
    public void printDetails(JTextArea output) {
        output.append("ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nLocation: " + location + "\nStatus: " + status);
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
        Collections.sort(reserveList);
    }
    
}
