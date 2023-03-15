package com.patikadev.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.model.Operator;

public class RegisterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2504693988417313897L;
	private JPanel contentPane;
	private JTextField fld_name;
	private JTextField fld_user_name;
	private JTextField fld_password;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 218, 294);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 191, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ad Soyad");
		lblNewLabel.setForeground(new Color(68, 75, 255));
		lblNewLabel.setBackground(new Color(68, 75, 255));
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		lblNewLabel.setBounds(41, 28, 80, 20);
		contentPane.add(lblNewLabel);

		fld_name = new JTextField();
		fld_name.setBorder(null);
		fld_name.setBackground(new Color(254, 191, 94));
		fld_name.setBounds(41, 40, 117, 20);
		contentPane.add(fld_name);
		fld_name.setColumns(10);

		JLabel lblKullancAd = new JLabel("Kullanıcı Adı");
		lblKullancAd.setForeground(new Color(68, 75, 255));
		lblKullancAd.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		lblKullancAd.setBounds(41, 79, 80, 20);
		contentPane.add(lblKullancAd);

		fld_user_name = new JTextField();
		fld_user_name.setBorder(null);
		fld_user_name.setBackground(new Color(254, 191, 94));
		fld_user_name.setColumns(10);
		fld_user_name.setBounds(41, 92, 117, 20);
		contentPane.add(fld_user_name);

		JLabel lblParola = new JLabel("Parola");
		lblParola.setForeground(new Color(68, 75, 255));
		lblParola.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		lblParola.setBounds(41, 125, 80, 26);
		contentPane.add(lblParola);

		fld_password = new JTextField();
		fld_password.setBorder(null);
		fld_password.setBackground(new Color(254, 191, 94));
		fld_password.setColumns(10);
		fld_password.setBounds(41, 141, 117, 20);
		contentPane.add(fld_password);

		JButton btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_user_name)
						|| Helper.isFieldEmpty(fld_password)) {
					Helper.showMsg("fill");
				} else {
					String name = fld_name.getText();
					String userName = fld_user_name.getText();
					String password = fld_password.getText();
					fld_name.setText("");
					fld_password.setText("");
					fld_user_name.setText("");
					try {
						Operator.addUser(name, userName, password, "student");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btnNewButton.setBounds(51, 175, 89, 23);
		contentPane.add(btnNewButton);

		lblNewLabel_1 = new JLabel("Ana Sayfa");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setForeground(new Color(68, 75, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBackground(new Color(68, 75, 255));
		lblNewLabel_1.setBounds(129, 225, 63, 14);
		contentPane.add(lblNewLabel_1);

		separator = new JSeparator();
		separator.setBackground(new Color(68, 75, 255));
		separator.setBounds(41, 62, 117, 2);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(68, 75, 255));
		separator_1.setBounds(41, 112, 117, 2);
		contentPane.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBackground(new Color(68, 75, 255));
		separator_2.setBounds(41, 162, 117, 2);
		contentPane.add(separator_2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
	}

	private static class __Tmp {
		private static void __tmp() {
			javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
