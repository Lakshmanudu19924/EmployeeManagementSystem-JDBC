package capestoneproject;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import capestoneproject.util.DBConnection;

public class Fetchemployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextArea resultArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Fetchemployee().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Fetchemployee() {

        setTitle("Fetch.java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTop = new JLabel("FETCH.JAVA", SwingConstants.CENTER);
        lblTop.setFont(new Font("Arial", Font.BOLD, 13));
        lblTop.setBounds(0, 5, 500, 20);
        contentPane.add(lblTop);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 480, 5);
        contentPane.add(separator);

        JLabel lblHeading = new JLabel("FETCH EMPLOYEE", SwingConstants.CENTER);
        lblHeading.setFont(new Font("Arial", Font.BOLD, 14));
        lblHeading.setBounds(0, 45, 500, 25);
        contentPane.add(lblHeading);

        JLabel lblId = new JLabel("Enter ID");
        lblId.setFont(new Font("Arial", Font.BOLD, 13));
        lblId.setBounds(130, 80, 80, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(220, 80, 120, 22);
        contentPane.add(txtId);

        JButton btnFetch = new JButton("FETCH");
        btnFetch.setFont(new Font("Arial", Font.BOLD, 12));
        btnFetch.setBounds(200, 115, 100, 25);
        contentPane.add(btnFetch);

        /* ---------- RESULT AREA ---------- */
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBounds(60, 150, 400, 194); // increased height
        contentPane.add(resultArea);
        
        /* ---------- FETCH BUTTON ACTION ---------- */
        btnFetch.addActionListener(e -> fetchEmployee());

        /* ---------- BACK BUTTON ---------- */
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(40, 365, 90, 25);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new Capestone().setVisible(true);
        });

        /* ---------- NEXT BUTTON ---------- */
        JButton btnNext = new JButton("NEXT");
        btnNext.setFont(new Font("Arial", Font.BOLD, 12));
        btnNext.setBounds(390, 365, 90, 25);
        contentPane.add(btnNext);

        btnNext.addActionListener(e -> {
            dispose();
            new Fetchallemployee().setVisible(true);
        });
    }

    /* =========================
       FETCH LOGIC
       ========================= */
    private void fetchEmployee() {

        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());

            String sql = "SELECT * FROM employee WHERE id = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	resultArea.setText(
            		      String.format("%-15s : %s%n", "ID", rs.getInt("id"))
            		    + String.format("%-15s : %s%n", "NAME", rs.getString("name"))
            		    + String.format("%-15s : %s%n", "GENDER", rs.getString("gender"))
            		    + String.format("%-15s : %s%n", "EMAIL", rs.getString("email"))
            		    + String.format("%-15s : %s%n", "DEPARTMENT", rs.getString("department"))
            		    + String.format("%-15s : %s%n", "SALARY", rs.getDouble("salary"))
            		    + String.format("%-15s : %s%n", "JOIN DATE", rs.getDate("join_date"))
            		    + String.format("%-15s : %s", "STATUS", rs.getString("status"))
            		);
            } else {
                resultArea.setText("");
                JOptionPane.showMessageDialog(this, "Employee not found");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
