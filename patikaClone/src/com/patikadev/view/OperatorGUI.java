package com.patikadev.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.helper.Item;
import com.patikadev.model.Course;
import com.patikadev.model.Operator;
import com.patikadev.model.Patika;
import com.patikadev.model.User;

public class OperatorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Operator operator = new Operator();
	private static Patika patika = new Patika();

	private JPanel wreppar;
	private JTable tb_userList;
	private JTextField fld_name;
	private JTextField fld_userName;
	private JTextField fld_userPassword;
	private JTextField fld_id;
	private JTextField fld_sh_name;
	private JTextField fld_sh_userName;
	private JTable tb_patikaList;
	private JTextField fld_patikaAdd;

	private JPopupMenu popupMenu;
	private JTextField fld_deleteCourse;
	private JTextField fld_addCoursName;
	private JTextField fld_addCourselangName;

	private DefaultTableModel userListModel = null;
	private Object[] columnNameUser = null;
	private Object[] columnDataUser = null;

	private DefaultTableModel patikaListModel = null;
	private Object[] columnNamePatika = null;
	private Object[] columnDataPatika = null;

	private DefaultTableModel courseListModel = null;

	private Object[] columnDataCourse = null;
	private JTable tb_courseList;

	private JComboBox cmb_educator;
	private JComboBox cmb_patika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperatorGUI frame = new OperatorGUI(operator);
					Helper.setLayout();

					frame.setTitle(Config.PROJE_TITLE);

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
	public OperatorGUI(User operator) {
		setResizable(false);

		setBackground(new Color(254, 191, 94));

		// userList

		userListModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0)
					return false;
				return super.isCellEditable(row, column);
			}

		};

		columnNameUser = new Object[5];
		columnNameUser[0] = "id";
		columnNameUser[1] = "Ad Soyad";
		columnNameUser[2] = "Kullanıcı Adı";
		columnNameUser[3] = "Password";
		columnNameUser[4] = "Statü";
		userListModel.setColumnIdentifiers(columnNameUser);

		columnDataUser = new Object[columnNameUser.length];
		try {
			operator.getUserList().stream().forEach(user -> {
				columnDataUser[0] = user.getId();
				columnDataUser[1] = user.getName();
				columnDataUser[2] = user.getUserName();
				columnDataUser[3] = user.getPassword();
				columnDataUser[4] = user.getType();
				userListModel.addRow(columnDataUser);
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ## userList

		// patikaList

		patikaListModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		columnNamePatika = new Object[2];
		columnNamePatika[0] = "id";
		columnNamePatika[1] = "patika";
		patikaListModel.setColumnIdentifiers(columnNamePatika);

		columnDataPatika = new Object[columnNamePatika.length];
		patika.getPatikaList().stream().forEach(patika -> {
			int i = 0;
			columnDataPatika[i++] = patika.getId();
			columnDataPatika[i++] = patika.getName();
			patikaListModel.addRow(columnDataPatika);
		});

		popupMenu = new JPopupMenu();
		JMenuItem delItem = new JMenuItem("Sil");
		JMenuItem upItem = new JMenuItem("Düzenle");
		popupMenu.add(delItem);
		popupMenu.add(upItem);

		delItem.addActionListener(e -> {
			if (Helper.confirm()) {
				int selectPatikaId = (int) tb_patikaList.getValueAt(tb_patikaList.getSelectedRow(), 0);
				patika.deletePatika(selectPatikaId);
				loadPatikaList();
				loadCourseList();
				loadCourseList();
			}

		});

		upItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = Integer
						.parseInt(tb_patikaList.getValueAt(tb_patikaList.getSelectedRow(), 0).toString());
				Patika upPatika = patika.getFetch(selectRow);

				UpdatePatikaGUI updatePatikaGUI = new UpdatePatikaGUI(upPatika);
				updatePatikaGUI.setLocation(Helper.screenCenterPoint("x", updatePatikaGUI),
						Helper.screenCenterPoint("y", updatePatikaGUI));
				updatePatikaGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updatePatikaGUI.setVisible(rootPaneCheckingEnabled);
				updatePatikaGUI.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						loadPatikaList();
						loadCboxPatika();
						loadCourseList();

					}

				});

			}
		});

		// ## patikaList

		// course
		courseListModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0)
					return false;
				return super.isCellEditable(row, column);
			}
		};

		Object[] columnName = { "ID", "Eğitmen", "Patika Adı", "Kursun Adı", "Programlama Dili" };
		courseListModel.setColumnIdentifiers(columnName);

		columnDataCourse = new Object[columnName.length];

		Course.getCourseList().stream().forEach(course -> {
			int i = 0;
			columnDataCourse[i++] = course.getId();
			columnDataCourse[i++] = course.getUser().getName();
			columnDataCourse[i++] = course.getPatika().getName();
			columnDataCourse[i++] = course.getName();
			columnDataCourse[i++] = course.getLanguage();
			courseListModel.addRow(columnDataCourse);
		});

		// ##course

		setBounds(100, 100, 903, 515);
		wreppar = new JPanel();
		wreppar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		wreppar.setBackground(new Color(254, 191, 94));
		wreppar.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(wreppar);
		wreppar.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 191, 94));
		panel.setBounds(0, 0, 887, 48);
		wreppar.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setOpaque(false);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaClone\\src\\com\\patikadev\\view\\logout_26px.png"));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(255, 255, 255));

		btnNewButton.setBounds(814, 11, 63, 31);
		panel.add(btnNewButton);

		JLabel lbl_welcome = new JLabel("Hoşgeldiniz");
		lbl_welcome.setBounds(10, 15, 220, 22);
		panel.add(lbl_welcome);
		lbl_welcome.setText("Hoşgeldiniz Sayın " + operator.getName());
		lbl_welcome.setIcon(new ImageIcon(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaKlonu\\src\\com\\patikadev\\view\\handshake_heart_24px.png"));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setBounds(10, 47, 867, 418);
		wreppar.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(254, 191, 94));
		tabbedPane.addTab("Kullanıcılar", (Icon) null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(254, 191, 94));
		scrollPane.setBounds(0, 49, 693, 318);
		panel_1.add(scrollPane);
		scrollPane.setForeground(new Color(255, 255, 255));
		;

		tb_userList = new JTable(userListModel);
		tb_userList.setSelectionBackground(new Color(254, 191, 94));
		tb_userList.setGridColor(new Color(255, 255, 255));
		tb_userList.setBackground(new Color(255, 255, 255));
		tb_userList.getTableHeader().setReorderingAllowed(false);
		tb_userList.getColumnModel().getColumn(0).setMaxWidth(100);
		tb_userList.getTableHeader().setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(tb_userList);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setBounds(725, 51, 93, 14);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 16));

		fld_name = new JTextField();
		fld_name.setBounds(725, 70, 127, 25);
		panel_1.add(fld_name);
		fld_name.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Kullanıcı Adı");
		lblNewLabel_1_1.setBounds(725, 106, 93, 14);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 16));

		fld_userName = new JTextField();
		fld_userName.setBounds(725, 123, 127, 25);
		panel_1.add(fld_userName);
		fld_userName.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Parola");
		lblNewLabel_1_2.setBounds(725, 159, 93, 14);
		panel_1.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Cambria Math", Font.PLAIN, 16));

		fld_userPassword = new JTextField();
		fld_userPassword.setBounds(725, 176, 127, 25);
		panel_1.add(fld_userPassword);
		fld_userPassword.setColumns(10);

		JLabel lblNewLabel_1_2_1 = new JLabel("Statü");
		lblNewLabel_1_2_1.setBounds(725, 212, 93, 14);
		panel_1.add(lblNewLabel_1_2_1);
		lblNewLabel_1_2_1.setFont(new Font("Cambria Math", Font.PLAIN, 16));

		JComboBox cmb_userStatu = new JComboBox();
		cmb_userStatu.setBounds(725, 227, 127, 25);
		panel_1.add(cmb_userStatu);
		cmb_userStatu.setModel(new DefaultComboBoxModel(new String[] { "", "student", "operator", "educator" }));
		cmb_userStatu.setBackground(new Color(255, 255, 255));

		JButton btn_userAdd = new JButton("Ekle");

		btn_userAdd.setBounds(725, 263, 127, 25);
		panel_1.add(btn_userAdd);
		btn_userAdd.addActionListener(e -> {

			if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_userName)
					|| Helper.isFieldEmpty(fld_userPassword)) {
				Helper.showMsg("fill");
			} else {
				String name = fld_name.getText();
				String userName = fld_userName.getText();
				String password = fld_userPassword.getText();
				String statu = (String) cmb_userStatu.getSelectedItem();

				try {
					operator.addUser(name, userName, password, statu);
					loadUserList();
					loaadCboxEducator();

					fld_name.setText(null);
					fld_userName.setText(null);
					fld_userPassword.setText(null);
					fld_deleteCourse.setText(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btn_userAdd.setBackground(new Color(255, 255, 255));

		fld_id = new JTextField();
		fld_id.setBounds(725, 299, 127, 25);
		panel_1.add(fld_id);
		fld_id.setEnabled(false);
		fld_id.setColumns(10);

		JButton btn_delete = new JButton("Sil");

		btn_delete.setBounds(725, 342, 127, 25);
		panel_1.add(btn_delete);
		btn_delete.addActionListener(e -> {

			if (fld_id.getText().isEmpty()) {

				Helper.showMsg("fill");
			} else {
				int id = Integer.parseInt(fld_id.getText());

				if (operator.deleteUser(id)) {
					fld_id.setText(null);
					loadUserList();
					loadCboxPatika();
					loaadCboxEducator();
				}
				loadCourseList();
			}
		});

		btn_delete.setBackground(Color.WHITE);

		JPanel pnl_sh = new JPanel();
		pnl_sh.setBounds(0, 4, 575, 42);
		panel_1.add(pnl_sh);
		pnl_sh.setBackground(new Color(254, 191, 94));

		pnl_sh.setLayout(null);

		fld_sh_name = new JTextField();
		fld_sh_name.setBounds(97, 12, 96, 20);
		pnl_sh.add(fld_sh_name);
		fld_sh_name.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Ad Soyad");
		lblNewLabel_3.setBounds(10, 15, 77, 14);
		pnl_sh.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Kullanıcı Adı");
		lblNewLabel_3_1.setBounds(203, 15, 88, 14);
		pnl_sh.add(lblNewLabel_3_1);

		fld_sh_userName = new JTextField();
		fld_sh_userName.setColumns(10);
		fld_sh_userName.setBounds(301, 12, 96, 20);
		pnl_sh.add(fld_sh_userName);

		JLabel lblNewLabel_3_1_1 = new JLabel("Statü");
		lblNewLabel_3_1_1.setBounds(407, 15, 35, 14);
		pnl_sh.add(lblNewLabel_3_1_1);

		JComboBox cmb_sh_statu = new JComboBox();
		cmb_sh_statu.setModel(new DefaultComboBoxModel(new String[] { "", "student", "operator", "educator" }));
		cmb_sh_statu.setBackground(new Color(255, 255, 255));
		cmb_sh_statu.setBounds(452, 11, 115, 22);
		pnl_sh.add(cmb_sh_statu);

		JButton btn_search = new JButton("ARA");
		btn_search.setBounds(578, 7, 63, 31);
		panel_1.add(btn_search);

		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fldName = fld_sh_name.getText();
				String fldUserName = fld_sh_userName.getText();
				String cmbStatu = cmb_sh_statu.getSelectedItem().toString();

				if (fldName.isEmpty() && fldUserName.isEmpty() && fldName.isEmpty()
						&& cmb_sh_statu.getSelectedItem().equals("")) {
					Helper.showMsg("fill");

				} else {

					String quary = operator.searchQuery(fldName, fldUserName, cmbStatu);
					loadUserList(operator.searchUserList(quary));
					fld_sh_name.setText(null);
					fld_sh_userName.setText(null);
					cmb_sh_statu.setSelectedIndex(0);
					loaadCboxEducator();
				}

			}
		});
		btn_search.setIcon(new ImageIcon(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaKlonu\\src\\com\\patikadev\\view\\refresh_512px.png"));
		btn_search.setOpaque(false);
		btn_search.setFocusable(false);
		btn_search.setBorder(null);
		btn_search.setBackground(Color.WHITE);

		JLabel lblNewLabel_2 = new JLabel("Yenile");
		lblNewLabel_2.setBounds(653, 4, 75, 38);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setDisplayedMnemonic(KeyEvent.VK_JAPANESE_HIRAGANA);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadUserList();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaKlonu\\src\\com\\patikadev\\view\\refresh_26px.png"));

		JPanel pnl_patika = new JPanel();
		pnl_patika.setBackground(new Color(254, 191, 94));
		pnl_patika.setForeground(new Color(255, 128, 0));
		tabbedPane.addTab("Patikalar", null, pnl_patika, null);
		pnl_patika.setLayout(null);

		JScrollPane scroll_patika = new JScrollPane();
		scroll_patika.setBounds(0, 39, 862, 361);
		pnl_patika.add(scroll_patika);

		tb_patikaList = new JTable(patikaListModel);
		tb_patikaList.setSelectionBackground(new Color(254, 191, 94));
		tb_patikaList.setGridColor(new Color(255, 255, 255));
		tb_patikaList.setBackground(new Color(255, 255, 255));
		tb_patikaList.getTableHeader().setReorderingAllowed(false);
		tb_patikaList.getTableHeader().setBackground(new Color(255, 255, 255));
		tb_patikaList.setComponentPopupMenu(popupMenu);
		tb_patikaList.getColumnModel().getColumn(0).setMaxWidth(75);
		tb_patikaList.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int select_row = tb_patikaList.rowAtPoint(point);
				tb_patikaList.setRowSelectionInterval(select_row, select_row);
			}

		});

		scroll_patika.setViewportView(tb_patikaList);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(254, 191, 94));
		panel_2.setBounds(0, 0, 862, 43);
		pnl_patika.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Patika Adı");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(53, 11, 58, 14);
		panel_2.add(lblNewLabel_4);

		fld_patikaAdd = new JTextField();
		fld_patikaAdd.setBounds(121, 10, 168, 20);
		panel_2.add(fld_patikaAdd);
		fld_patikaAdd.setColumns(10);

		JButton btn_patikaAdd = new JButton("Ekle");

		btn_patikaAdd.addActionListener(e -> {

			if (!Helper.isFieldEmpty(fld_id)) {
				Helper.showMsg("fill");
			} else {
				String fldPatikaAdd = fld_patikaAdd.getText();
				patika.addPatika(fldPatikaAdd);
				fld_patikaAdd.setText(null);
				loadPatikaList();
				loadCboxPatika();
				loadCourseList();

			}

		});
		btn_patikaAdd.setBounds(293, 9, 89, 23);
		panel_2.add(btn_patikaAdd);

		JPanel pnl_course = new JPanel();
		pnl_course.setLayout(null);
		pnl_course.setBackground(new Color(254, 191, 94));
		tabbedPane.addTab("Kurslar", null, pnl_course, null);

		JScrollPane scrollpane_courseList = new JScrollPane();
		scrollpane_courseList.setForeground(Color.WHITE);
		scrollpane_courseList.setBackground(new Color(254, 191, 94));
		scrollpane_courseList.setBounds(0, 27, 693, 340);
		pnl_course.add(scrollpane_courseList);

		tb_courseList = new JTable(courseListModel);
		tb_courseList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectId = tb_courseList.getValueAt(tb_courseList.getSelectedRow(), 0).toString();
				String selectName = tb_courseList.getValueAt(tb_courseList.getSelectedRow(), 3).toString();
				String selectLang = tb_courseList.getValueAt(tb_courseList.getSelectedRow(), 4).toString();
				fld_addCourselangName.setText(selectLang);
				fld_addCoursName.setText(selectName);
				fld_deleteCourse.setText(selectId);
			}
		});

		tb_courseList.setSelectionBackground(new Color(254, 191, 94));
		tb_courseList.setGridColor(new Color(255, 255, 255));
		tb_courseList.setBackground(new Color(255, 255, 255));
		tb_courseList.getTableHeader().setReorderingAllowed(false);
		tb_courseList.getColumnModel().getColumn(0).setMaxWidth(100);
		tb_courseList.getTableHeader().setBackground(new Color(255, 255, 255));

		scrollpane_courseList.setViewportView(tb_courseList);

		JLabel lblNewLabel_1_1_1 = new JLabel("Eğitmen");
		lblNewLabel_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(725, 27, 93, 14);
		pnl_course.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2_2 = new JLabel("Patika");
		lblNewLabel_1_2_2.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		lblNewLabel_1_2_2.setBounds(725, 80, 93, 14);
		pnl_course.add(lblNewLabel_1_2_2);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Kurs Adı");
		lblNewLabel_1_2_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1.setBounds(725, 133, 93, 14);
		pnl_course.add(lblNewLabel_1_2_1_1);

		fld_deleteCourse = new JTextField();
		fld_deleteCourse.setEnabled(false);
		fld_deleteCourse.setColumns(10);
		fld_deleteCourse.setBounds(725, 314, 127, 25);
		pnl_course.add(fld_deleteCourse);

		JButton btn_cooursDelete = new JButton("Sil");
		btn_cooursDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb_courseList.getSelectedRow() != -1) {
					if (Helper.confirm()) {
						if (tb_courseList.getSelectedRow() != -1) {
							int selectId = Integer.parseInt(fld_deleteCourse.getText());
							Course.deleteCourse(selectId);
							fld_deleteCourse.setText("");
							fld_addCourselangName.setText("");
							fld_addCoursName.setText("");
							loadCourseList();
						}
					}
				} else {
					Helper.showMsg("Güncellemek İstediğiniz Kursu Seçiniz...");
				}

			}
		});
		btn_cooursDelete.setBackground(Color.WHITE);
		btn_cooursDelete.setBounds(725, 342, 127, 25);
		pnl_course.add(btn_cooursDelete);

		cmb_educator = new JComboBox();
		loaadCboxEducator();
		cmb_educator.setBackground(Color.WHITE);
		cmb_educator.setBounds(725, 44, 127, 25);
		pnl_course.add(cmb_educator);

		cmb_patika = new JComboBox();
		loadCboxPatika();

		cmb_patika.setBackground(Color.WHITE);
		cmb_patika.setBounds(725, 97, 127, 25);
		pnl_course.add(cmb_patika);

		fld_addCoursName = new JTextField();
		fld_addCoursName.setColumns(10);
		fld_addCoursName.setBounds(725, 148, 127, 25);
		pnl_course.add(fld_addCoursName);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Programlama Dili");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_1.setBounds(725, 184, 127, 14);
		pnl_course.add(lblNewLabel_1_2_1_1_1);

		fld_addCourselangName = new JTextField();
		fld_addCourselangName.setColumns(10);
		fld_addCourselangName.setBounds(725, 199, 127, 25);
		pnl_course.add(fld_addCourselangName);

		JButton btn_add = new JButton("Ekle");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmb_educator.getSelectedIndex() == 0 || cmb_patika.getSelectedIndex() == 0
						|| Helper.isFieldEmpty(fld_addCoursName) || Helper.isFieldEmpty(fld_addCourselangName)) {
					Helper.showMsg("fill");

				} else {
					Item educator = (Item) cmb_educator.getSelectedItem();
					Item patika = (Item) cmb_patika.getSelectedItem();
					String name = fld_addCoursName.getText();
					String language = fld_addCourselangName.getText();
					Course.addCourse(educator.getKey(), patika.getKey(), name, language);
					cmb_patika.setSelectedIndex(0);
					cmb_educator.setSelectedIndex(0);
					fld_addCourselangName.setText(null);
					fld_addCoursName.setText(null);
					loadCourseList();
				}

			}
		});
		btn_add.setBackground(Color.WHITE);
		btn_add.setBounds(725, 235, 127, 25);
		pnl_course.add(btn_add);

		JButton btn_update = new JButton("Güncelle");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb_courseList.getSelectedRow() != -1) {
					if (cmb_educator.getSelectedIndex() == 0 || cmb_patika.getSelectedIndex() == 0
							|| Helper.isFieldEmpty(fld_addCoursName) || Helper.isFieldEmpty(fld_addCourselangName)) {
						Helper.showMsg("fill");

					} else {
						Item selectUserItem = (Item) cmb_educator.getSelectedItem();
						Item selectPatikaItem = (Item) cmb_patika.getSelectedItem();

						int selectId = Integer.parseInt(fld_deleteCourse.getText());
						int selectUserId = selectUserItem.getKey();
						int selectPatikaId = selectPatikaItem.getKey();
						String name = fld_addCoursName.getText();
						String language = fld_addCourselangName.getText();
						Course.updateCourse(selectId, selectUserId, selectPatikaId, name, language);
						loadCourseList();
						cmb_patika.setSelectedIndex(0);
						cmb_educator.setSelectedIndex(0);
						fld_addCourselangName.setText(null);
						fld_addCoursName.setText(null);

					}
				} else {
					Helper.showMsg("Güncellenmesini İstediğiniz Kursu Seçiniz...");
				}
			}
		});
		btn_update.setBackground(Color.WHITE);
		btn_update.setBounds(725, 266, 127, 25);
		pnl_course.add(btn_update);
		tb_userList.getSelectionModel().addListSelectionListener(e -> {

			try {
				String selectId = tb_userList.getValueAt(tb_userList.getSelectedRow(), 0).toString();
				fld_id.setText(selectId);
			} catch (Exception e2) {
				// TODO: handle exception
			}

		});
		tb_userList.getModel().addTableModelListener(e -> {
			if (e.getType() == TableModelEvent.UPDATE) {
				int selectId = Integer.parseInt(tb_userList.getValueAt(tb_userList.getSelectedRow(), 0).toString());
				String selectName = tb_userList.getValueAt(tb_userList.getSelectedRow(), 1).toString();
				String selectUserName = tb_userList.getValueAt(tb_userList.getSelectedRow(), 2).toString();
				String selectPassword = tb_userList.getValueAt(tb_userList.getSelectedRow(), 3).toString();
				String selectStatu = tb_userList.getValueAt(tb_userList.getSelectedRow(), 4).toString();

				if (selectName.isEmpty() || selectUserName.isEmpty() || selectPassword.isEmpty()
						|| selectStatu.isEmpty()) {
					Helper.showMsg("fill");
					loadUserList();
				} else {
					if (Helper.confirm()) {
						if (selectStatu.equals("student") || selectStatu.equals("operator")
								|| selectStatu.equals("educator")) {
							operator.updateUser(selectId, selectName, selectUserName, selectPassword, selectStatu);
							loadUserList();
							loadCourseList();
						} else {
							Helper.showMsg("Kullanabilir Statu : operator , student , educator");
							loadUserList();
							loaadCboxEducator();
						}

					}
					loadUserList();
					loaadCboxEducator();
				}

			}

		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);

	}

	// courlist
	private void loadCourseList() {

		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_courseList.getModel();
		clearDefaultTableModel.setRowCount(0);

		Course.getCourseList().stream().forEach(course -> {
			int i = 0;
			columnDataCourse[i++] = course.getId();
			columnDataCourse[i++] = course.getUser().getName();
			columnDataCourse[i++] = course.getPatika().getName();
			columnDataCourse[i++] = course.getName();
			columnDataCourse[i++] = course.getLanguage();
			courseListModel.addRow(columnDataCourse);
		});

	}
// courlist

// userList
	public void loadUserList() {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_userList.getModel();
		clearDefaultTableModel.setRowCount(0);

		try {

			operator.getUserList().stream().forEach(user -> {
				int i = 0;
				columnDataUser[i++] = user.getId();
				columnDataUser[i++] = user.getName();
				columnDataUser[i++] = user.getUserName();
				columnDataUser[i++] = user.getPassword();
				columnDataUser[i++] = user.getType();
				userListModel.addRow(columnDataUser);
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadUserList(ArrayList<User> searcList) {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_userList.getModel();
		clearDefaultTableModel.setRowCount(0);

		searcList.stream().forEach(user -> {
			int j = 0;
			columnDataUser[j++] = user.getId();
			columnDataUser[j++] = user.getName();
			columnDataUser[j++] = user.getUserName();
			columnDataUser[j++] = user.getPassword();
			columnDataUser[j++] = user.getType();
			userListModel.addRow(columnDataUser);
		});
	}
	// ## userList

	// patikaList
	public void loadPatikaList() {

		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_patikaList.getModel();
		clearDefaultTableModel.setRowCount(0);

		patika.getPatikaList().stream().forEach(patika -> {
			int i = 0;
			columnDataPatika[i++] = patika.getId();
			columnDataPatika[i++] = patika.getName();
			patikaListModel.addRow(columnDataPatika);
		});

	}
	// ## PatikaList
	// cbox patika

	// ## cbox patika
	public void loadCboxPatika() {
		cmb_patika.setModel(new DefaultComboBoxModel(new String[] { "" }));

		patika.getPatikaList().stream().forEach(patika -> {
			cmb_patika.addItem(new Item(patika.getId(), patika.getName()));
		});
	}

	// cbox educator
	public void loaadCboxEducator() {

		cmb_educator.removeAllItems();
		cmb_educator.setModel(new DefaultComboBoxModel(new String[] { "" }));
		String query = User.searchQuery("", "", "educator");

		User.searchUserList(query).stream().forEach(educator -> {
			cmb_educator.addItem(new Item(educator.getId(), educator.getName()));

		});
	}
}
