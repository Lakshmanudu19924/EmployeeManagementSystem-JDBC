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

public class Add extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtGender;
    private JTextField txtEmail;
    private JTextField txtDepartment;
    private JTextField txtSalary;
    private JTextField txtJoinDate;
    private JTextField txtStatus;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Add().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Add() {

        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTop = new JLabel("ADD EMPLOYEE", SwingConstants.CENTER);
        lblTop.setFont(new Font("Arial", Font.BOLD, 16));
        lblTop.setBounds(0, 5, 500, 25);
        contentPane.add(lblTop);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 480, 5);
        contentPane.add(separator);

        int labelX = 80;
        int fieldX = 230;
        int y = 60;
        int gap = 30;

        contentPane.add(createLabel("ID", labelX, y));
        txtId = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Name", labelX, y));
        txtName = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Gender", labelX, y));
        txtGender = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Email", labelX, y));
        txtEmail = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Department", labelX, y));
        txtDepartment = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Salary", labelX, y));
        txtSalary = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Join Date (yyyy-mm-dd)", labelX, y));
        txtJoinDate = createTextField(fieldX, y); y += gap;

        contentPane.add(createLabel("Status", labelX, y));
        txtStatus = createTextField(fieldX, y);

        /* ---------- BACK BUTTON (LEFT) ---------- */
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(40, 360, 90, 30);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new Fetchallemployee().setVisible(true);
        });

        /* ---------- ADD EMPLOYEE BUTTON (CENTER) ---------- */
        JButton btnSave = new JButton("ADD EMPLOYEE");
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        btnSave.setBounds(190, 360, 140, 30);
        contentPane.add(btnSave);

        btnSave.addActionListener(e -> addEmployee());

        /* ---------- NEXT BUTTON (RIGHT) ---------- */
        JButton btnNext = new JButton("NEXT");
        btnNext.setFont(new Font("Arial", Font.BOLD, 12));
        btnNext.setBounds(380, 360, 90, 30);
        contentPane.add(btnNext);

//        btnNext.addActionListener(e -> {
//            dispose();
//            new Updateemployee().setVisible(true);
//        });
        btnNext.addActionListener(e -> {
            dispose();
            new Deleteemployee().setVisible(true);
        });
    }

    /* =========================
       INSERT LOGIC
       ========================= */
    private void addEmployee() {

        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID is required");
            return;
        }

        try {
            String sql =
                "INSERT INTO employee " +
                "(id, name, gender, email, department, salary, join_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.setString(2, txtName.getText());
            ps.setString(3, txtGender.getText());
            ps.setString(4, txtEmail.getText());
            ps.setString(5, txtDepartment.getText());
            ps.setDouble(6, Double.parseDouble(txtSalary.getText()));
            ps.setDate(7, Date.valueOf(txtJoinDate.getText()));
            ps.setString(8, txtStatus.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Employee details added successfully");

            ps.close();
            con.close();

            txtId.setText("");
            txtName.setText("");
            txtGender.setText("");
            txtEmail.setText("");
            txtDepartment.setText("");
            txtSalary.setText("");
            txtJoinDate.setText("");
            txtStatus.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setBounds(x, y, 150, 20);
        contentPane.add(label);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 180, 22);
        contentPane.add(field);
        return field;
    }
}
