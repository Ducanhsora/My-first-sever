/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tien;

/**
 *
 * @author Tien
 */
public class Habit {
    private String name;
    private String description;
    private boolean completedToday;
    
    public Habit(String name, String description, boolean completedToday) {
        this.name = name;
        this.description = description;
        this.completedToday = completedToday;
        
    
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompletedToday() {
        return completedToday;
    }

    @Override
    public String toString() {
        return "Name: " + name +
               "\nDescription: " + description +
               "\nCompleted today: " + (completedToday ? "Yes" : "No") +
               "\n-----------------------------\n";
    }

}
    
