
import java.awt.*;
import javax.swing.*;

public class MetricThreadData {

    private static final Insets COMPONENT_INSETS = new Insets(5, 5, 5, 5);
    private static final double WEIGHT_X = 0.25;
    private static final double THREAD_DEPTH_FACTOR = 0.613;

    public static void open(Point location) {
        JFrame frame = new JFrame("Metric Thread Data");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 800);
        frame.setLocation(location);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        frame.add(mainPanel);
        placeComponents(mainPanel, frame);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = COMPONENT_INSETS;
        gbc.weightx = WEIGHT_X;
        gbc.weighty = 0;

        addBackButton(panel, frame, gbc);
        addThreadSizeSelection(panel, gbc);
        addDecimalPlacesSelection(panel, gbc);
        addShowDataButton(panel, gbc, frame);
        addDataArea(panel, gbc);
    }

    private static void addBackButton(JPanel panel, JFrame frame, GridBagConstraints gbc) {
        JButton backButton = new JButton("Back to Thread Data Screen");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        backButton.addActionListener(e -> {
            frame.dispose();
            ThreadData.open(frame.getLocation());
        });
    }

    private static JComboBox<String> threadSizeDropdown;

    private static void addThreadSizeSelection(JPanel panel, GridBagConstraints gbc) {
        JLabel threadSizeLabel = new JLabel("Select Thread Size:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(threadSizeLabel, gbc);

        String[] threadSizes = {
            "M1 x 0.2", "M1 x 0.25", "M1.2 x 0.2", "M1.2 x 0.25", "M1.4 x 0.2", "M1.4 x 0.3", "M1.6 x 0.2", "M1.6 x 0.35", "M1.8 x 0.2", "M1.8 x 0.35",
            "M2 x 0.25", "M2 x 0.4", "M2.2 x 0.25", "M2.2 x 0.45", "M2.5 x 0.35", "M2.5 x 0.45", "M3 x 0.35", "M3 x 0.5", "M3.5 x 0.35", "M3.5 x 0.6",
            "M4 x 0.5", "M4 x 0.7", "M5 x 0.5", "M5 x 0.8", "M6 x 0.75", "M6 x 1.0", "M7 x 0.75", "M7 x 1.0", "M8 x 1.0", "M8 x 1.25", "M10 x 1.25", "M10 x 1.5",
            "M12 x 1.5", "M12 x 1.75", "M14 x 1.5", "M14 x 2.0", "M16 x 1.5", "M16 x 2.0", "M18 x 1.5", "M18 x 2.5", "M20 x 1.5", "M20 x 2.5", "M22 x 1.5", "M22 x 2.5",
            "M24 x 2.0", "M24 x 3.0", "M27 x 2.0", "M27 x 3.0", "M30 x 2.0", "M30 x 3.5", "M33 x 2.0", "M33 x 3.5", "M36 x 2.0", "M36 x 4.0", "M39 x 2.0", "M39 x 4.0",
            "M42 x 2.0", "M42 x 4.5", "M45 x 2.0", "M45 x 4.5", "M48 x 2.0", "M48 x 5.0", "M52 x 2.0", "M52 x 5.0", "M56 x 2.0", "M56 x 5.5", "M60 x 2.0", "M60 x 5.5",
            "M64 x 2.0", "M64 x 6.0", "M68 x 2.0", "M68 x 6.0", "M72 x 2.0", "M72 x 6.0", "M76 x 2.0", "M76 x 6.0", "M80 x 2.0", "M80 x 6.0", "M85 x 2.0", "M85 x 6.0",
            "M90 x 2.0", "M90 x 6.0", "M95 x 2.0", "M95 x 6.0", "M100 x 2.0", "M100 x 6.0"
        };
        threadSizeDropdown = new JComboBox<>(threadSizes);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(threadSizeDropdown, gbc);
    }

    private static JCheckBox decimalPlacesCheckbox3;
    private static JCheckBox decimalPlacesCheckbox4;
    private static JCheckBox decimalPlacesCheckbox5;

    private static void addDecimalPlacesSelection(JPanel panel, GridBagConstraints gbc) {
        JLabel decimalPlacesLabel = new JLabel("Decimal Places:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(decimalPlacesLabel, gbc);

        decimalPlacesCheckbox3 = new JCheckBox("3");
        decimalPlacesCheckbox4 = new JCheckBox("4");
        decimalPlacesCheckbox5 = new JCheckBox("5");

        ButtonGroup decimalPlacesGroup = new ButtonGroup();
        decimalPlacesGroup.add(decimalPlacesCheckbox3);
        decimalPlacesGroup.add(decimalPlacesCheckbox4);
        decimalPlacesGroup.add(decimalPlacesCheckbox5);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(decimalPlacesCheckbox3);
        checkBoxPanel.add(decimalPlacesCheckbox4);
        checkBoxPanel.add(decimalPlacesCheckbox5);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(checkBoxPanel, gbc);
    }

    private static JEditorPane dataArea;

    private static void addShowDataButton(JPanel panel, GridBagConstraints gbc, JFrame frame) {
        JButton showDataButton = new JButton("Show Data");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(showDataButton, gbc);

        showDataButton.addActionListener(e -> {
            String threadSize = (String) threadSizeDropdown.getSelectedItem();
            int decimalPlaces = getSelectedDecimalPlaces();

            if (threadSize != null && decimalPlaces != -1) {
                Double majorDiameter = ThreadDataStorage.getMajorDiameter(threadSize);
                Double pitch = ThreadDataStorage.getPitch(threadSize);
                if (majorDiameter != null && pitch != null) {
                    String externalData = calculateThreadData("External", majorDiameter, pitch, decimalPlaces);
                    String internalData = calculateThreadData("Internal", majorDiameter, pitch, decimalPlaces);
                    String leadAndTapDrillData = calculateLeadAndTapDrillSize(majorDiameter, pitch, decimalPlaces);
                    dataArea.setText("<html>" + externalData + "<br><br>" + internalData + "<br><br>" + leadAndTapDrillData + "</html>");
                } else {
                    dataArea.setText("Data not available for the selected thread size.");
                }
            } else {
                dataArea.setText("Please select a valid thread size and decimal places.");
            }
        });
    }

    private static void addDataArea(JPanel panel, GridBagConstraints gbc) {
        dataArea = new JEditorPane();
        dataArea.setContentType("text/html");
        dataArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dataArea);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);
    }

    private static int getSelectedDecimalPlaces() {
        if (decimalPlacesCheckbox3.isSelected()) {
            return 3;
        }
        if (decimalPlacesCheckbox4.isSelected()) {
            return 4;
        }
        if (decimalPlacesCheckbox5.isSelected()) {
            return 5;
        }
        return -1;
    }

    private static String calculateThreadData(String type, double majorDiameter, double pitch, int decimalPlaces) {
        double threadDepth = THREAD_DEPTH_FACTOR * pitch;
        double minorDiameter = majorDiameter - (2 * threadDepth);
        String format = "%." + decimalPlaces + "f";
        String minorDiameterLabel = type.equals("External") ? "Minor Diameter (d1)" : "Minor Diameter (D1)";

        return String.format(
                "<b>%s Thread Data:</b><br>"
                + "Major Diameter: <b>" + format + "</b> mm<br>"
                + "Pitch: <b>" + format + "</b> mm<br>"
                + "Thread Depth: <b>" + format + "</b> mm<br>"
                + "%s: <b>" + format + "</b> mm",
                type, majorDiameter, pitch, threadDepth, minorDiameterLabel, minorDiameter
        );
    }

    private static String calculateLeadAndTapDrillSize(double majorDiameter, double pitch, int decimalPlaces) {
        double lead = pitch;
        double tapDrillSize = majorDiameter - pitch;
        String format = "%." + decimalPlaces + "f";

        return String.format(
                "<b>Lead and Tap Drill Size:</b><br>"
                + "Lead: <b>" + format + "</b> mm<br>"
                + "Tap Drill Size: <b>" + format + "</b> mm",
                lead, tapDrillSize
        );
    }
}
