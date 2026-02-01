package capestoneproject;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import capestoneproject.util.DBConnection;

public class Updateemployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtId;
    private JTextField txtNewName;
    private JTextField txtNewGender;
    private JTextField txtNewEmail;
    private JTextField txtNewDepartment;
    private JTextField txtNewSalary;
    private JTextField txtNewJoinDate;
    private JTextField txtNewStatus;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Updateemployee().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Updateemployee() {

        setTitle("Update.java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTop = new JLabel("UPDATE.JAVA", SwingConstants.CENTER);
        lblTop.setFont(new Font("Arial", Font.BOLD, 14));
        lblTop.setBounds(0, 5, 740, 20);
        contentPane.add(lblTop);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 720, 5);
        contentPane.add(separator);

        JLabel lblHeading = new JLabel("UPDATE EMPLOYEE", SwingConstants.CENTER);
        lblHeading.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeading.setBounds(0, 50, 740, 25);
        contentPane.add(lblHeading);

        JLabel lblId = new JLabel("Enter ID");
        lblId.setFont(new Font("Arial", Font.BOLD, 13));
        lblId.setBounds(130, 85, 100, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(304, 85, 200, 22);
        contentPane.add(txtId);

        int y = 130;
        int gap = 32;

        txtNewName       = addRow("New Name", "UPDATE NAME", "name", y); y += gap;
        txtNewGender     = addRow("New Gender", "UPDATE GENDER", "gender", y); y += gap;
        txtNewEmail      = addRow("New Email", "UPDATE EMAIL", "email", y); y += gap;
        txtNewDepartment = addRow("New Department", "UPDATE DEPARTMENT", "department", y); y += gap;
        txtNewSalary     = addRow("New Salary", "UPDATE SALARY", "salary", y); y += gap;
        txtNewJoinDate   = addRow("New Join Date (YYYY-MM-DD)", "UPDATE JOIN DATE", "join_date", y); y += gap;
        txtNewStatus     = addRow("New Status", "UPDATE STATUS", "status", y);

        /* ---------- UPDATE EMPLOYEE (CENTER) ---------- */
        JButton btnUpdateAll = new JButton("UPDATE EMPLOYEE");
        btnUpdateAll.setFont(new Font("Arial", Font.BOLD, 12));
        btnUpdateAll.setBounds(280, 413, 160, 30);
        contentPane.add(btnUpdateAll);

        btnUpdateAll.addActionListener(e -> updateAllFields());

        /* ---------- BACK BUTTON (RIGHT) ---------- */
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(560, 413, 120, 30);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new Deleteemployee().setVisible(true);
        });
    }

    /* =========================
       ROW CREATOR
       ========================= */
    private JTextField addRow(String labelText,
                              String buttonText,
                              String columnName,
                              int y) {

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setBounds(70, y, 220, 20);
        contentPane.add(label);

        JTextField field = new JTextField();
        field.setBounds(260, y, 200, 22);
        contentPane.add(field);

        JButton btnUpdate = new JButton(buttonText);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 11));
        btnUpdate.setBounds(500, y, 180, 24);
        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(e -> {
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter value for " + labelText);
                return;
            }
            updateColumn(columnName, field.getText());
            JOptionPane.showMessageDialog(this,
                    buttonText + " UPDATED SUCCESSFULLY");
        });

        return field;
    }

    /* =========================
       UPDATE ALL FIELDS
       ========================= */
    private void updateAllFields() {

        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Employee ID");
            return;
        }

        if (!txtNewName.getText().isEmpty())
            updateColumn("name", txtNewName.getText());

        if (!txtNewGender.getText().isEmpty())
            updateColumn("gender", txtNewGender.getText());

        if (!txtNewEmail.getText().isEmpty())
            updateColumn("email", txtNewEmail.getText());

        if (!txtNewDepartment.getText().isEmpty())
            updateColumn("department", txtNewDepartment.getText());

        if (!txtNewSalary.getText().isEmpty())
            updateColumn("salary", txtNewSalary.getText());

        if (!txtNewJoinDate.getText().isEmpty())
            updateColumn("join_date", txtNewJoinDate.getText());

        if (!txtNewStatus.getText().isEmpty())
            updateColumn("status", txtNewStatus.getText());

        JOptionPane.showMessageDialog(this,
                "Employee details updated successfully");
    }

    /* =========================
       UPDATE SINGLE COLUMN
       ========================= */
    private void updateColumn(String column, String newValue) {

        try {
            int id = Integer.parseInt(txtId.getText());
            String sql = "UPDATE employee SET " + column + " = ? WHERE id = ?";

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            if (column.equals("join_date")) {
                ps.setDate(1, Date.valueOf(newValue));
            } else if (column.equals("salary")) {
                ps.setDouble(1, Double.parseDouble(newValue));
            } else {
                ps.setString(1, newValue);
            }

            ps.setInt(2, id);
            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    }
}
