package gui;

import javax.swing.JFrame;

import startup.ResidenceSystem;

/**
 * The frame for the window to display the operations that involve students.
 */
public class ManagerOpsFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame for the operations that involve students.
     */
    public ManagerOpsFrame() {
        setTitle("Manager Operations");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ManagerOpsPanel panel = new ManagerOpsPanel();
        add(panel);
    }

    /**
     * A main to run the GUI version of the residence system that only involves student operations
     * and the ward.
     */
    public static void main(String[] args) {
        //ResidenceSystem system = new ResidenceSystem();
        // system.initialize();
        ManagerOpsFrame frame = new ManagerOpsFrame();
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static final long serialVersionUID = 1;
}
