package Alex;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Alex Sharma (x23436930)
 * Section: BMI Tracker Screen
 *
 * This window lets the user enter height and weight, calculates the BMI,
 * shows it on screen and also keeps a small history of BMI records in
 * memory using BMIRecordManager.
 */
public class BMIPage extends JFrame {

    // Manager that stores all BMIRecord objects for this screen.
    private static final BMIRecordManager manager = new BMIRecordManager();

    // GUI fields so I can read/write the values inside the button listeners.
    private JTextField heightField;
    private JTextField weightField;
    private JLabel resultLabel;

    public BMIPage() {
        setTitle("BMI Calculator");
        setSize(450, 500);
        setLocationRelativeTo(null);                 // centre on screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // 9 rows because I have the image, inputs, result and extra buttons.
        card.setLayout(new GridLayout(9, 1, 10, 10));

        // ---- IMAGE ROW ----
        try {
            // I load the BMI chart image from the Alex package and scale it down a bit.
            ImageIcon original = new ImageIcon(getClass().getResource("/Alex/BMIchart.jpg"));
            Image scaled = original.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
            card.add(imgLabel);
        } catch (Exception ex) {
            // If the image is missing I just show a text label instead so the app still works.
            card.add(new JLabel("BMI Chart", SwingConstants.CENTER));
        }

        // ---- TITLE ----
        JLabel title = new JLabel("BMI Calculator", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        card.add(title);

        // ---- HEIGHT ROW ----
        JPanel heightRow = new JPanel(new BorderLayout(10, 0));
        heightRow.setBackground(Color.WHITE);
        JLabel heightLabel = new JLabel("Height (cm): ");
        heightField = new JTextField();
        heightRow.add(heightLabel, BorderLayout.WEST);
        heightRow.add(heightField, BorderLayout.CENTER);
        card.add(heightRow);

        // ---- WEIGHT ROW ----
        JPanel weightRow = new JPanel(new BorderLayout(10, 0));
        weightRow.setBackground(Color.WHITE);
        JLabel weightLabel = new JLabel("Weight (kg): ");
        weightField = new JTextField();
        weightRow.add(weightLabel, BorderLayout.WEST);
        weightRow.add(weightField, BorderLayout.CENTER);
        card.add(weightRow);

        // ---- CALCULATE BUTTON ----
        JButton calcButton = new JButton("Calculate BMI");
        calcButton.addActionListener(e -> calculateAndSaveBmi());
        card.add(calcButton);

        // ---- RESULT LABEL ----
        resultLabel = new JLabel("Enter your details and click Calculate.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        card.add(resultLabel);

        // ---- EXTRA BUTTONS FOR HISTORY / SEARCH / DELETE ----

        JButton viewHistoryBtn = new JButton("View BMI History");
        viewHistoryBtn.addActionListener(e -> showHistory());
        card.add(viewHistoryBtn);

        JButton searchBtn = new JButton("Search BMI");
        searchBtn.addActionListener(e -> searchRecord());
        card.add(searchBtn);

        JButton deleteBtn = new JButton("Delete Record");
        deleteBtn.addActionListener(e -> deleteRecord());
        card.add(deleteBtn);

        add(card, BorderLayout.CENTER);
    }

    // ================== Logic Methods ==================

    /**
     * Reads height and weight from the text fields, calculates the BMI,
     * shows it on the label and stores a BMIRecord using the manager.
     */
    private void calculateAndSaveBmi() {
        try {
            double hCm = Double.parseDouble(heightField.getText().trim());
            double wKg = Double.parseDouble(weightField.getText().trim());

            if (hCm <= 0 || wKg <= 0) {
                resultLabel.setText("Height and weight must be greater than 0.");
                return;
            }

            double hMeters = hCm / 100.0;
            double bmi = wKg / (hMeters * hMeters);

            // Show the result with two decimal places.
            resultLabel.setText(String.format("Your BMI: %.2f", bmi));

            // Save record in the ArrayList via the manager.
            BMIRecord record = new BMIRecord(hCm, wKg, bmi);
            manager.addRecord(record);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers for height and weight.");
        }
    }

    /**
     * Builds a simple text list of all saved BMI records and shows it
     * in a dialog window.
     */
    private void showHistory() {
        if (manager.getAllRecords().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No BMI records saved yet.",
                    "History",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Saved BMI Records:\n\n");
        List<BMIRecord> all = manager.getAllRecords();
        for (int i = 0; i < all.size(); i++) {
            sb.append(i)
              .append(": ")
              .append(all.get(i).toString())
              .append("\n");
        }

        JOptionPane.showMessageDialog(this,
                sb.toString(),
                "History",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Lets the user type a BMI value and looks for an exact match in the list.
     */
    private void searchRecord() {
        if (manager.getAllRecords().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No records available to search.",
                    "Search BMI",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String input = JOptionPane.showInputDialog(this,
                "Enter BMI value to search (e.g. 22.5):",
                "Search BMI",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null) {
            return; // user cancelled
        }

        try {
            double bmiValue = Double.parseDouble(input.trim());
            BMIRecord found = manager.findByExactBmi(bmiValue);

            if (found != null) {
                JOptionPane.showMessageDialog(this,
                        "Record found:\n" + found,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No record with that exact BMI.",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number, for example 23.4",
                    "Search BMI",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Deletes a record by its index in the list. I ask the user for an index,
     * then call the manager to remove it.
     */
    private void deleteRecord() {
        if (manager.getAllRecords().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No records to delete.",
                    "Delete Record",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String input = JOptionPane.showInputDialog(this,
                "Enter index to delete (0 to " + (manager.getAllRecords().size() - 1) + "):",
                "Delete Record",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null) {
            return; // user cancelled
        }

        try {
            int idx = Integer.parseInt(input.trim());

            // Let the manager handle bounds checking.
            manager.deleteRecord(idx);

            JOptionPane.showMessageDialog(this,
                    "If the index was valid, the record is now deleted.",
                    "Delete Record",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a whole number.",
                    "Delete Record",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
