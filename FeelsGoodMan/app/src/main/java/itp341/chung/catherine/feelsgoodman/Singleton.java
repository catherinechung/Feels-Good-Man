package itp341.chung.catherine.feelsgoodman;

import java.util.ArrayList;
import java.util.List;

import itp341.chung.catherine.feelsgoodman.model.Memory;

/**
 * Created by catherinechung on 5/7/17.
 */

public class Singleton {

    private static Singleton instance;
    private ArrayList<Memory> memoryList = new ArrayList<Memory>();

    private Singleton() {
    }

    public static Singleton Instance() {
        // Create new instance if no instance is initialized
        // Else return stored instance
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public int getNumberOfMemories() {
        return memoryList.size();
    }

    public Memory getMemory(int index) {
        return memoryList.get(index);
    }

    public void addMemory(Memory newMemory) {
        memoryList.add(newMemory);
    }

    public List<Memory> getMemoryList(){
        return memoryList;
    }
}
