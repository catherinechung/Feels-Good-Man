package itp341.chung.catherine.feelsgoodman.model;

/**
 * Created by catherinechung on 5/7/17.
 */

public class Memory {

    // instance variables
    private String memory;
    private String timestamp;

    // Constructor
    public Memory() {
        super();
        memory = "";
        timestamp = "";
    }

    // Getters
    public String getComment() {
        return memory;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
