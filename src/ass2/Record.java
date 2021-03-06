package ass2;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

class Record implements Serializable, Comparable<Record> {
    private Student reserver;
    private Book book;
    private LocalDateTime date;
    
    //constructor for a record
    public Record(Student reserver, Book book) {
        this.reserver = reserver;
        this.book = book;
        date = LocalDateTime.now();
    }
    
    public Student getStudent() {
        return reserver;
    }
    
    public Book getBook() {
        return book;
    }
    
    public boolean previouslyReserved(Student s, Book b) {
        if (reserver == s && book == b) {
            return true;
        }
        return false;
    }
    
    public String printRecord() {
        //format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = date.format(formatter);
        //return the string
        return "User: " + reserver.getUsername() + " Book: " + book.getISBN() + " Date reserved: " + formatDateTime + "\n";
    }
    
    public int compareTo(Record r) {
        return date.compareTo(r.date);
    }
    
    public void generateRecord(Formatter file) {
        //format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = date.format(formatter);
        file.format("User: " + reserver.getUsername() + " Book ISBN: " + book.getISBN() + " Date reserved: " + formatDateTime + "%n");
    }
}
