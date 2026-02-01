package capestoneproject;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;

import capestoneproject.util.DBConnection;

public class Deleteemployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;

    /* =========================
       MAIN METHOD
       ========================= */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Deleteemployee().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /* =========================
       CONSTRUCTOR
       ========================= */
    public Deleteemployee() {

        setTitle("Delete.java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 350);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTop = new JLabel("DELETE.JAVA", SwingConstants.CENTER);
        lblTop.setFont(new Font("Arial", Font.BOLD, 14));
        lblTop.setBounds(0, 5, 500, 20);
        contentPane.add(lblTop);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 480, 5);
        contentPane.add(separator);

        JLabel lblHeading = new JLabel("DELETE EMPLOYEE", SwingConstants.CENTER);
        lblHeading.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeading.setBounds(0, 55, 500, 25);
        contentPane.add(lblHeading);

        JLabel lblId = new JLabel("Enter ID");
        lblId.setFont(new Font("Arial", Font.BOLD, 13));
        lblId.setBounds(120, 120, 80, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(220, 120, 140, 22);
        contentPane.add(txtId);

        /* ---------- DELETE BUTTON ---------- */
        JButton btnDelete = new JButton("DELETE");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        btnDelete.setBounds(210, 170, 100, 28);
        contentPane.add(btnDelete);

        btnDelete.addActionListener(e -> deleteEmployee());

        /* ---------- BACK BUTTON ---------- */
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(40, 260, 90, 25);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new Add().setVisible(true);
        });

        /* ---------- NEXT BUTTON ---------- */
        JButton btnNext = new JButton("NEXT");
        btnNext.setFont(new Font("Arial", Font.BOLD, 12));
        btnNext.setBounds(390, 260, 90, 25);
        contentPane.add(btnNext);

        btnNext.addActionListener(e -> {
            dispose();
            new Updateemployee().setVisible(true);
        });
    }

    /* =========================
       DELETE LOGIC
       ========================= */
    private void deleteEmployee() {

        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter Employee ID");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());

            String sql = "DELETE FROM employee WHERE id = ?";

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Employee deleted successfully");
                txtId.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Employee ID not found");
            }

            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    }
}
