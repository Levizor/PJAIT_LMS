import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryUserInterface {
    // Dummy data (Replace this with actual data from your database)
    private static String[][] allBooks = {
            {"1", "The Great Gatsby", "Available"},
            {"2", "1984", "Borrowed"},
            {"3", "To Kill a Mockingbird", "Available"},
            {"4", "Moby Dick", "Withdrawn"},
            {"5", "Pride and Prejudice", "Available"}
    };

    private static String[][] userBorrowingHistory = {
            {"1", "The Great Gatsby", "2025-01-01", "2025-01-10"},
            {"2", "1984", "2024-12-15", "2024-12-22"}
    };

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Library User Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Library User Interface", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: All Books
        JPanel allBooksPanel = new JPanel(new BorderLayout());
        JTable allBooksTable = new JTable();
        updateTable(allBooksTable, new String[]{"ID", "Title", "Status"}, allBooks);
        JScrollPane allBooksScrollPane = new JScrollPane(allBooksTable);
        allBooksPanel.add(allBooksScrollPane, BorderLayout.CENTER);
        tabbedPane.addTab("All Books", allBooksPanel);

        // Tab 2: Available Books
        JPanel availableBooksPanel = new JPanel(new BorderLayout());
        JTable availableBooksTable = new JTable();
        updateTable(availableBooksTable, new String[]{"ID", "Title", "Status"},
                filterBooksByStatus("Available"));
        JScrollPane availableBooksScrollPane = new JScrollPane(availableBooksTable);
        availableBooksPanel.add(availableBooksScrollPane, BorderLayout.CENTER);
        tabbedPane.addTab("Available Books", availableBooksPanel);

        // Tab 3: Borrowing History
        JPanel borrowingHistoryPanel = new JPanel(new BorderLayout());
        JTable borrowingHistoryTable = new JTable();
        updateTable(borrowingHistoryTable, new String[]{"ID", "Title", "Borrow Date", "Return Date"},
                userBorrowingHistory);
        JScrollPane borrowingHistoryScrollPane = new JScrollPane(borrowingHistoryTable);
        borrowingHistoryPanel.add(borrowingHistoryScrollPane, BorderLayout.CENTER);
        tabbedPane.addTab("Borrowing History", borrowingHistoryPanel);

        // Add the tabbed pane to the main panel
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add the main panel to the frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }

    /**
     * Updates the table with new data.
     */
    private static void updateTable(JTable table, String[] columns, String[][] data) {
        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        table.setModel(tableModel);
    }

    /**
     * Filters books by their status.
     */
    private static String[][] filterBooksByStatus(String status) {
        return java.util.Arrays.stream(allBooks)
                .filter(book -> book[2].equals(status))
                .toArray(String[][]::new);
    }
}
