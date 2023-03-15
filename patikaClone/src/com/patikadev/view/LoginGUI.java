package com.patikadev.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.model.User;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8123919802218351222L;
	private JPanel contentPane;
	private JTextField fld_userName;
	private JPasswordField fld_userPassword;
	private boolean next;
	private boolean isEmptyType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setLocation(Helper.screenCenterPoint("x", frame), Helper.screenCenterPoint("y", frame));
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setTitle(Config.PROJE_TITLE);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		next = true;
		isEmptyType = true;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 349);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 191, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 307, 72);
		panel.setBackground(new Color(254, 191, 94));
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(102, 0, 125, 69);
		lblNewLabel.setBackground(new Color(254, 191, 94));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaClone\\src\\com\\patikadev\\view\\patikapng125.png"));
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(254, 191, 94));
		panel_2.setBounds(5, 95, 302, 253);
		panel_2.setLayout(null);
		contentPane.add(panel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Parola");
		lblNewLabel_2_1.setBounds(67, 65, 42, 17);
		panel_2.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setForeground(new Color(68, 75, 255));
		lblNewLabel_2_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));

		fld_userPassword = new JPasswordField();
		fld_userPassword.setBackground(new Color(254, 191, 94));
		fld_userPassword.setBounds(67, 83, 183, 26);
		panel_2.add(fld_userPassword);
		fld_userPassword.setBorder(null);

		JButton btn_userEnter = new JButton("Giriş Yap");
		btn_userEnter.setBounds(103, 147, 98, 23);
		panel_2.add(btn_userEnter);
		btn_userEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.isFieldEmpty(fld_userName) || Helper.isFieldEmpty(fld_userPassword)) {
					Helper.showMsg("fill");
				} else {
					String userName = fld_userName.getText();
					String password = fld_userPassword.getText();
					User user = User.userLogin(userName, password);

					if (user == null) {
						Helper.showMsg("Kayıt Bulunamadı");
					} else {
						String type = user.getType();
						switch (type) {
						case "educator":
							EducatorGUI educatorGUI = new EducatorGUI(user);
							isEmptyType = false;
							educatorGUI.setVisible(true);
							dispose();
							break;
						case "operator":
							OperatorGUI operatorGUI = new OperatorGUI(user);
							isEmptyType = false;
							operatorGUI.setVisible(true);
							dispose();
							break;
						case "student":
							StudentGUI studentGUI = new StudentGUI(user);
							isEmptyType = false;
							studentGUI.setVisible(true);
							dispose();
							break;
						default:
							Helper.showMsg("Bilinmeyen Kullanıcı Türü");

						}

					}

				}

			}
		});
		btn_userEnter.setForeground(new Color(68, 75, 255));
		btn_userEnter.setRequestFocusEnabled(false);
		btn_userEnter.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_2 = new JLabel("Kullanıcı Adı");
		lblNewLabel_2.setBounds(67, 0, 81, 17);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(68, 75, 255));
		lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btn_userStudent = new JButton("Kayıt Ol");
		btn_userStudent.setBounds(224, 177, 47, 17);
		panel_2.add(btn_userStudent);
		btn_userStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI registerGUI = new RegisterGUI();
				registerGUI.setVisible(true);
				dispose();

			}
		});
		btn_userStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_userStudent.setMnemonic(KeyEvent.VK_CLEAR);
		btn_userStudent.setFont(new Font("Arial", Font.ITALIC, 13));
		btn_userStudent.setForeground(new Color(68, 75, 255));
		btn_userStudent.setBorder(null);
		btn_userStudent.setRequestFocusEnabled(false);
		btn_userStudent.setBackground(new Color(254, 191, 94));

		fld_userName = new JTextField();
		fld_userName.setBackground(new Color(254, 191, 94));
		fld_userName.setBounds(67, 20, 183, 26);
		panel_2.add(fld_userName);
		fld_userName.setBorder(null);
		fld_userName.setHorizontalAlignment(SwingConstants.LEFT);
		fld_userName.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(68, 75, 255));
		separator.setBounds(67, 111, 183, 2);
		panel_2.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(68, 75, 255));
		separator_1.setBounds(67, 48, 183, 2);
		panel_2.add(separator_1);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
	}
}
