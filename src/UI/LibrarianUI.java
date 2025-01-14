package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianUI extends JFrame {
    public JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable table1;

    public static void main(String[] args) {
    }

    public LibrarianUI() {

        JFrame frame = new JFrame("LibrarianUI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tabbedPane1.getTitleAt(tabbedPane1.getSelectedIndex()));
            }
        });

        frame.setVisible(true);

    }
}
