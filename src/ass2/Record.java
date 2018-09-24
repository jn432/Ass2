package ass2;

import java.io.Serializable;
import java.time.LocalDateTime;

class Record implements Serializable, Comparable<Record> {
    private User reserver;
    private LocalDateTime date;
    
    //constructor for a record
    public Record(User reserver) {
        this.reserver = reserver;
        date = LocalDateTime.now();
    }
    
    public String printRecord() {
        return "User: " + reserver + "\nDate reserved: " + date.getDayOfWeek() + "/" + date.getDayOfMonth() + "/" + date.getDayOfYear() + "\n";
    }
    
    public int compareTo(Record r) {
        return date.compareTo(r.date);
    }
}
