package ass2;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Record implements Serializable, Comparable<Record> {
    private User reserver;
    private LocalDateTime date;
    
    //constructor for a record
    public Record(User reserver) {
        this.reserver = reserver;
        date = LocalDateTime.now();
    }
    
    public String printRecord() {
        //format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = date.format(formatter);
        //return the string
        return "User: " + reserver.getUsername() + "\nDate reserved: " + formatDateTime + "\n";
    }
    
    public int compareTo(Record r) {
        return date.compareTo(r.date);
    }
}
