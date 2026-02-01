package capestoneproject;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import capestoneproject.util.DBConnection;

public class Fetchallemployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Fetchallemployee().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Fetchallemployee() {

        setTitle("FetchAll.java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTop = new JLabel("FETCH ALL.JAVA", SwingConstants.CENTER);
        lblTop.setFont(new Font("Arial", Font.BOLD, 14));
        lblTop.setBounds(0, 5, 600, 20);
        contentPane.add(lblTop);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 570, 5);
        contentPane.add(separator);

        JLabel lblHeading = new JLabel("FETCH ALL EMPLOYEE", SwingConstants.CENTER);
        lblHeading.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeading.setBounds(0, 50, 600, 25);
        contentPane.add(lblHeading);

        /* ---------- TABLE ---------- */
        String[] columns = {
            "ID", "NAME", "GENDER", "EMAIL",
            "DEPARTMENT", "SALARY", "JOIN DATE", "STATUS"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(26);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);
        table.getColumnModel().getColumn(3).setPreferredWidth(220);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(
            table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setBounds(10, 90, 570, 220);
        contentPane.add(scrollPane);

        /* ---------- LOAD DATA FROM DATABASE ---------- */
        loadEmployeeData(model);

        /* ---------- BACK BUTTON ---------- */
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(40, 320, 90, 25);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new Fetchemployee().setVisible(true);
        });

        /* ---------- NEXT BUTTON ---------- */
        JButton btnNext = new JButton("NEXT");
        btnNext.setFont(new Font("Arial", Font.BOLD, 12));
        btnNext.setBounds(450, 320, 90, 25);
        contentPane.add(btnNext);

        btnNext.addActionListener(e -> {
            dispose();
            new Add().setVisible(true);
        });
    }

    /* =========================
       DATABASE FETCH LOGIC
       ========================= */
    private void loadEmployeeData(DefaultTableModel model) {

        try {
            String sql = "SELECT * FROM employee";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getDouble("salary"),
                    rs.getDate("join_date"),
                    rs.getString("status")
                };
                model.addRow(row);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error fetching data: " + e.getMessage());
        }
    }
}
