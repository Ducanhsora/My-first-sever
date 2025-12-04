
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tien;
/**
 *
 * @author Tien
 */
import java.util.ArrayList;
public class HabitTips {
    private ArrayList<String> tips;
    
        public HabitTips(){
            tips = new ArrayList<>();
            
            tips.add("Drink at least 1.5-2 liters of water each day.");
            tips.add("Sleep 7-9 hours to maintain energy and focus.");
            tips.add("Walking 10 minutes after meals to improve digestion.");
            tips.add("Limit screen time before bedtime to improve sleep quality.");
            tips.add("Take 5 minutes breaks during a 30 minutes study sessions.");            
        }
    public void addTip(String tip){
        tips.add(tip);
        System.out.println("Added new tip:" +tip);
    }
    public ArrayList<String> getAllTips(){
        return tips;
}   
    public int countTips(){
        return tips.size();
    }
}
