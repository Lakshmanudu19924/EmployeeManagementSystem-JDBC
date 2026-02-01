package capestoneproject;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class Capestone extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /* =========================
       MAIN METHOD
       ========================= */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Capestone frame = new Capestone();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /* =========================
       CONSTRUCTOR
       ========================= */
    public Capestone() {

        /* ---------- FRAME SETTINGS ---------- */
        setTitle("Index.java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        /* ---------- TOP TITLE ---------- */
        JLabel lblIndex = new JLabel("INDEX.JAVA", SwingConstants.CENTER);
        lblIndex.setFont(new Font("Arial", Font.BOLD, 13));
        lblIndex.setBounds(0, 5, 500, 20);
        contentPane.add(lblIndex);

        /* ---------- WINDOW CONTROLS ---------- */
        JButton btnMin = new JButton("_");
        btnMin.setBounds(410, 5, 25, 20);
        btnMin.addActionListener(e -> setState(JFrame.ICONIFIED));
        contentPane.add(btnMin);

        JButton btnMax = new JButton("□");
        btnMax.setBounds(440, 5, 25, 20);
        btnMax.addActionListener(e -> setExtendedState(JFrame.MAXIMIZED_BOTH));
        contentPane.add(btnMax);

        JButton btnClose = new JButton("X");
        btnClose.setBounds(470, 5, 25, 20);
        btnClose.addActionListener(e -> System.exit(0));
        contentPane.add(btnClose);

        /* ---------- SEPARATOR ---------- */
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 480, 5);
        contentPane.add(separator);

        /* ---------- MAIN HEADING ---------- */
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setBounds(10, 69, 500, 30);
        contentPane.add(heading);

        /* ---------- BUTTONS ---------- */
        int btnWidth = 200;
        int btnHeight = 35;

        JButton btnFetch = new JButton("FETCH EMPLOYEE");
        btnFetch.setBounds(31, 150, btnWidth, btnHeight);
        contentPane.add(btnFetch);

        JButton btnFetchAll = new JButton("FETCH ALL EMPLOYEE");
        btnFetchAll.setBounds(300, 150, btnWidth, btnHeight);
        contentPane.add(btnFetchAll);

        JButton btnAdd = new JButton("ADD EMPLOYEE");
        btnAdd.setBounds(31, 219, btnWidth, btnHeight);
        contentPane.add(btnAdd);

        JButton btnDelete = new JButton("DELETE EMPLOYEE");
        btnDelete.setBounds(300, 219, btnWidth, btnHeight);
        contentPane.add(btnDelete);

        JButton btnUpdate = new JButton("UPDATE EMPLOYEE");
        btnUpdate.setBounds(163, 303, btnWidth, btnHeight);
        contentPane.add(btnUpdate);

        /* =========================
           BUTTON ACTIONS (NAVIGATION)
           ========================= */

        btnFetch.addActionListener(e -> {
            dispose();
            new Fetchemployee().setVisible(true);
        });

        btnFetchAll.addActionListener(e -> {
            dispose();
            new Fetchallemployee().setVisible(true);
        });

        btnAdd.addActionListener(e -> {
            dispose();
            new Add().setVisible(true);
        });

        btnDelete.addActionListener(e -> {
            dispose();
            new Deleteemployee().setVisible(true);
        });

        btnUpdate.addActionListener(e -> {
            dispose();
            new Updateemployee().setVisible(true);
        });
    }
}
