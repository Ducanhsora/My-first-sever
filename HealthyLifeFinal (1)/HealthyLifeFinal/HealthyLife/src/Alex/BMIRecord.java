package Alex;

/**
 * Author: Alex Sharma (x23436930)
 * Section: BMI Tracker - OOP Project
 *
 * This class is a simple data holder for one BMI entry.
 * It keeps the user's height, weight and the BMI value that was calculated.
 */
public class BMIRecord {

    // I’m storing height in centimetres so it’s easier to display on the UI.
    private double heightCm;

    // Weight is stored in kilograms to match the BMI formula we use.
    private double weightKg;

    // This is the actual BMI number that gets calculated from height and weight.
    private double bmiValue;

    /**
     * Constructor to create a new BMIRecord.
     * I pass in the height, weight and already-calculated BMI value.
     */
    public BMIRecord(double heightCm, double weightKg, double bmiValue) {
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.bmiValue = bmiValue;
    }

    // Getter methods so the other classes (like the manager and UI) can read the values.

    public double getHeightCm() {
        return heightCm;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public double getBmiValue() {
        return bmiValue;
    }

    /**
     * I use toString() mainly for debugging and displaying the record
     * in a simple text format when needed.
     */
    @Override
    public String toString() {
        return String.format("Height: %.1f cm, Weight: %.1f kg, BMI: %.2f",
                heightCm, weightKg, bmiValue);
    }
}

