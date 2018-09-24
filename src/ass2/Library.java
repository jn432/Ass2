package ass2;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

class Library {
    
    private String bookFile;
    private String userFile;
    private String recordFile;
    
    private ArrayList<Book> bookList;
    private ArrayList<User> userList;
    private ArrayList<Record> recordList;
    
    
    public ArrayList<Book> getBookList() {
        return bookList;
    }
    
    public ArrayList<User> getUserList() {
        return userList;
    }
    
    
    public User findUser(String username) {
        //search method here
        for (User u : userList) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    public Book findBook(int ISBN) {
        //search method here
        for (Book b : bookList) {
            if (b.getISBN() == ISBN) {
                return b;
            }
        }
        return null;
    }
    
    //load or create files on initializatoin
    public Library(String books, String users, String records) {
        //set the file path
        bookFile = books;
        userFile = users;
        recordFile = records;
        
        //loading or creating the book file
        try {
            ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(books)));
            try {
                bookList = (ArrayList<Book>) input.readObject();
            }
            catch (EOFException eof) {
                System.err.println("No more books");
            }
            input.close();
            System.err.println("book file loaded");
        }
        catch (NoSuchFileException e) {
                bookList = new ArrayList<Book>();
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR at book file loading: " + e);
        }
        
        //loading or creating the user file
        try {
            ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(users)));
            try {
                userList = (ArrayList<User>) input.readObject();
            }
            catch (EOFException eof) {
                System.err.println("No more users");
            }
            input.close();
            System.err.println("user file loaded");
        }
        catch (NoSuchFileException e) {
            //no previous user file, create an empty arraylist of users
            userList = new ArrayList<User>();
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR at user file loading: " + e);
        }
        
        //loading or creating the record file
        try {
            ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(records)));
            try {
                recordList = (ArrayList<Record>) input.readObject();
            }
            catch (EOFException eof) {
                System.err.println("No more records");
            }
            input.close();
            System.err.println("record file loaded");
        }
        catch (NoSuchFileException e) {
            //no previous user file, create an empty arraylist of users
            recordList = new ArrayList<Record>();
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR at record file loading: " + e);
        }
    }
    
    //save the book list after update
    public void saveFile(Object list) {
        String path;
        if (list == bookList) {
            path = bookFile;
        }
        else if (list == userList) {
            path = userFile;
        }
        else {
            path = recordFile;
        }
        try {
            ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(path)));
            output.writeObject(list);
            output.close();
            System.err.println("File saved");
        }
        catch (IOException e) {
            System.err.println("ERROR at writing " + path + ": " + e);
        }
    }
    
}
