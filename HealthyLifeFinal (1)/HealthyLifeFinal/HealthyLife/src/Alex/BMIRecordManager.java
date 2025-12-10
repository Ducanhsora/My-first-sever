package Alex;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Alex Sharma (x23436930)
 * Section: BMI Tracker - OOP Project
 *
 * This class basically manages a list of BMIRecord objects. I’m using an ArrayList
 * because records can be added and removed easily while the app is running.
 */
public class BMIRecordManager {

    // This ArrayList is where all BMI entries are stored while the app is open.
    private final ArrayList<BMIRecord> records;

    public BMIRecordManager() {
        // I make a new empty list every time the app starts.
        records = new ArrayList<>();
    }

    /**
     * When user adds a new BMI entry from the UI, this method stores it.
     */
    public void addRecord(BMIRecord record) {
        records.add(record);
    }

    /**
     * This method returns all the saved BMI records. The UI uses this to refresh
     * the table whenever something changes.
     */
    public List<BMIRecord> getAllRecords() {
        return records;
    }

    /**
     * I search for a record by the exact BMI value. Using Math.abs so
     * small decimal differences won’t break the match.
     */
    public BMIRecord findByExactBmi(double bmi) {
        for (BMIRecord r : records) {
            if (Math.abs(r.getBmiValue() - bmi) < 0.01) {
                return r;
            }
        }
        // If I don’t find anything, I return null so the UI can show a message.
        return null;
    }

    /**
     * Deletes a record by index (usually from the list/table). I check the index
     * so the app won’t crash if user tries something invalid.
     */
    public void deleteRecord(int index) {
        if (index >= 0 && index < records.size()) {
            records.remove(index);
        }
    }
}
