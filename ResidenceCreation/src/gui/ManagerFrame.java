package gui;

import javax.swing.JFrame;


import containers.ManagerMapAccess;
import entities.Manager;

/**
 * The frame for the window to display the information for a student, and accept operations on the
 * student.
 */
public class ManagerFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Create the frame to display the information for a student.
     *
     * @param mName the social insurance number of the student
     * @precond sSIN the SIN of a student
     */
    public ManagerFrame(String mName) {
        Manager manager = ManagerMapAccess.getInstance().get(mName);
        if (manager != null) {
            setTitle(manager.getName() + " (" + mName + ")");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            ManagerPanel panel = new ManagerPanel(manager);
            add(panel);
        } else
            throw new RuntimeException("Invalid name " + mName);
    }

    public static final long serialVersionUID = 1;
}
