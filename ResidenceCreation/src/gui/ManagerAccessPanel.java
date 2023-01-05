package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerAccessPanel extends JPanel {
    /**
     * The text field for the entry of the student's social insurance number.
     */
    JTextField textField;

    /**
     * Create the panel with the prompt label and text field. If data is entered into the text field
     * that is not a valid int value, a brief error message is entered at the front of the text
     * field. Otherwise, a new window is created with the student's data and operations on the
     * student.
     */
    public ManagerAccessPanel() {
        JLabel promptLabel = new JLabel("Access manager with Employee ID");
        add(promptLabel);
        textField = new JTextField(10);
        add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                String mName = textField.getText();
                ManagerFrame frame = null;
                try {
                    frame = new ManagerFrame(mName);
                } catch (RuntimeException e) {
                    textField.setText("Invalid Employee ID: " + textField.getText());
                    textField.revalidate();
                    return;
                }
                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
                textField.setText("");
                textField.revalidate();

            }
        });
    }

    public static final long serialVersionUID = 1;
}
