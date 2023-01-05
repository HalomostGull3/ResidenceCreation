package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import commands.AssignManagerStudent;
import commands.CommandArguments;
import commands.DropAssociation;
import commands.ReleaseStudent;
import containers.StudentMapAccess;
import entities.BasicManager;
import entities.Manager;
import entities.Student;


/**
 * The panel to display the information for a student, and accept operations on the student. The
 * panel gives the student's name and social insurance number. If the student has bed in the ward, it is given
 * and the user has the option to remove the student from the bed. If the student does not have a
 * bed, a create is created for the ward information, so that the student can be added to an empty
 * bed. The managers of the student are given, and the user has the option to add another manager or
 * remove a manager.
 */
public class ManagerPanel extends JPanel{
    /**
     * Create the panel to display the student's information and accept operations on the student.
     *
     * @param manager the student whose information is to be displayed and on whom operations can be
     *        done
     */
    public ManagerPanel(Manager manager) {
        /*
         * The creation of the panel is placed in another method as it needs to be invoked whenever
         * the manager information of the student is changed.
         */
        build(manager);
    }

    /**
     * Fill in the panel to display the student's information and accept operations on the student.
     *
     * @param manager the student whose information is to be displayed and on whom operations can be
     *        done
     */
    private void build(Manager manager) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("Name: " + manager.getName()));
        add(new JLabel("Manager's SIN: " + manager.getSIN()));
        add(new JLabel("Employee ID: " + manager.getEmployeeId()));

        JButton addStudentButton = addStudentButton(manager);
        add(addStudentButton);
        addStudentButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addStudentButton.setMaximumSize(addStudentButton.getPreferredSize());

//        JButton accessStudent = accessStudentButton(manager);
        JPanel accessStudent = new StudentAccessPanel();
        add(accessStudent);
        accessStudent.setAlignmentX(Component.LEFT_ALIGNMENT);
        accessStudent.setMaximumSize(accessStudent.getPreferredSize());


        JButton removeStudent = removeStudent(manager);
        add(removeStudent);
        removeStudent.setAlignmentX(Component.LEFT_ALIGNMENT);
        removeStudent.setMaximumSize(removeStudent.getPreferredSize());


        add(new JLabel("  ")); // blank line in the panel for spacing
        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    private JButton addStudentButton(final Manager manager) {
        add(new JLabel("  "));
        add(new JLabel("Student SIN"));
        final JTextField textField = new JTextField(30);
        textField.setMaximumSize(textField.getPreferredSize());
        add(textField);
        JButton addStudentButton = new JButton("Add Student");
        add(addStudentButton);

        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String sSIN = textField.getText();
                AssignManagerStudent addAssoc = new AssignManagerStudent();

                CommandArguments cmdArguments = new CommandArguments();
                cmdArguments.mEN = manager.getEmployeeId();
                cmdArguments.sSIN = sSIN;
                addAssoc.setCommnadArguments(cmdArguments);
                //addAssoc.assignManager(managerName, student.getSIN());
                addAssoc.execute();
                if (addAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(manager);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog( ManagerPanel.this, addAssoc.getErrorMessage());
                }
            }
        });
        return addStudentButton;
    }

    private JButton accessStudentButton(final Manager manager) {
        add(new JLabel("  "));
        add(new JLabel("Student SIN"));
        final JTextField textField = new JTextField(30);
        textField.setMaximumSize(textField.getPreferredSize());
        add(textField);
        JButton accessStudentButton = new JButton("Access Student");
        add(accessStudentButton);

        accessStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String sSIN = textField.getText();
                JPanel accessStudentPanel = new StudentAccessPanel();
                CommandArguments cmdArguments = new CommandArguments();
                cmdArguments.sSIN = sSIN;
//                accessStudentPanel.add(sSIN, cmdArguments)
                accessStudentPanel.
                add(accessStudentPanel);
            }
        });

        return accessStudentButton;
    }

    private JButton removeStudent(final Manager manager) {
        add(new JLabel("  "));
        add(new JLabel("Student SIN"));
        final JTextField textField = new JTextField(30);
        textField.setMaximumSize(textField.getPreferredSize());
        add(textField);
        JButton removeStudent = new JButton("Remove Student");
        add(removeStudent);

        removeStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String sSIN = textField.getText();
                DropAssociation drop = new DropAssociation();
                CommandArguments cmdArguments = new CommandArguments();
                cmdArguments.mEN = manager.getEmployeeId();
                cmdArguments.sSIN = sSIN;
                cmdArguments.sNSID = StudentMapAccess.getInstance().get(sSIN).getNSID();
                cmdArguments.mName = manager.getName();
                drop.setCommnadArguments(cmdArguments);
                drop.execute();
                if (drop.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(manager);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog( ManagerPanel.this, drop.getErrorMessage());
                }
            }
        });

        return removeStudent;
    }

    public static final long serialVersionUID = 1;
}
