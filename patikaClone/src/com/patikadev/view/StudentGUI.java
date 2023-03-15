package com.patikadev.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.helper.Item;
import com.patikadev.model.Course;
import com.patikadev.model.Educator;
import com.patikadev.model.Lesson;
import com.patikadev.model.Patika;
import com.patikadev.model.RegisteredCourse;
import com.patikadev.model.Student;
import com.patikadev.model.User;

public class StudentGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6166951498324730539L;
	private static User student = new Student();
	private JPanel wreppar;
	private JTable tb_studentLesson;
	private JComboBox comboBox_patika;
	private JComboBox comboBox_Lesson;
	private JPopupMenu popupStudent;

	private DefaultTableModel registerModel;
	private Object[] columnData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI frame = new StudentGUI(student);
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
	public StudentGUI(User student) {
		setResizable(false);

		registerModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		Object[] column = { "id", "Patika", "Ders", "DersId" };
		registerModel.setColumnIdentifiers(column);
		columnData = new Object[column.length];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 434);
		wreppar = new JPanel();
		wreppar.setBackground(new Color(254, 191, 94));
		wreppar.setBorder(new EmptyBorder(5, 5, 5, 5));
		wreppar.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(wreppar);
		wreppar.setLayout(null);

		comboBox_patika = new JComboBox();
		comboBox_patika.setBackground(new Color(255, 255, 255));
		comboBox_patika.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Item item = Helper.getItem(comboBox_patika);
				if (comboBox_patika.getSelectedIndex() != 0) {
					loadComboBoxLesson(item.getKey());
				}

			}
		});

		comboBox_patika.setBounds(265, 75, 208, 22);
		wreppar.add(comboBox_patika);

		comboBox_Lesson = new JComboBox();
		comboBox_Lesson.setBackground(new Color(255, 255, 255));
		comboBox_Lesson.setBounds(265, 138, 208, 22);
		wreppar.add(comboBox_Lesson);

		JLabel lblNewLabel = new JLabel("Patika Seç");
		lblNewLabel.setForeground(new Color(68, 75, 255));
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		lblNewLabel.setBounds(265, 50, 75, 14);
		wreppar.add(lblNewLabel);

		JLabel lblDersSe = new JLabel("Ders Seç");
		lblDersSe.setForeground(new Color(68, 75, 255));
		lblDersSe.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		lblDersSe.setBounds(265, 113, 75, 14);
		wreppar.add(lblDersSe);

		JButton btnNewButton = new JButton("Dersi Ekle");
		btnNewButton.setForeground(new Color(68, 75, 255));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBox_Lesson.getSelectedIndex() == 0 || comboBox_patika.getSelectedIndex() == 0) {
					Helper.showMsg("fill");
				} else {
					if (student.getId() != 0) {

						Item patikaitem = Helper.getItem(comboBox_patika);
						Item lessonItem = Helper.getItem(comboBox_Lesson);
						int user_id = student.getId();
						int lesson_id = lessonItem.getKey();
						int course_id = Course.getFetch(lesson_id);
						int patika_id = patikaitem.getKey();
						String course_name = lessonItem.getValue();
						String patika_name = patikaitem.getValue();

						RegisteredCourse.addRegisterLesson(user_id, course_id, patika_id, course_name, patika_name,
								lesson_id);
						loadRegister(student);
					}
				}
			}
		});
		btnNewButton.setBounds(315, 178, 111, 22);
		wreppar.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Devam eden derslerim");
		scrollPane.setBounds(40, 211, 621, 173);
		wreppar.add(scrollPane);

		tb_studentLesson = new JTable(registerModel);
		tb_studentLesson.setSelectionBackground(new Color(254, 191, 94));
		tb_studentLesson.setGridColor(new Color(255, 128, 0));
		tb_studentLesson.setBackground(new Color(255, 255, 255));
		tb_studentLesson.getTableHeader().setReorderingAllowed(false);
		tb_studentLesson.getTableHeader().setBackground(new Color(255, 255, 255));
		tb_studentLesson.getColumnModel().getColumn(0).setMaxWidth(75);

		popupStudent = new JPopupMenu();
		JMenuItem delStudentItem = new JMenuItem("Sil");
		JMenuItem openLessonItem = new JMenuItem("Derse Devam Et...");
		popupStudent.add(delStudentItem);
		popupStudent.add(openLessonItem);

		delStudentItem.addActionListener(e -> {
			if (Helper.confirm()) {
				int selectRegisterId = (int) tb_studentLesson.getValueAt(tb_studentLesson.getSelectedRow(), 0);
				if (RegisteredCourse.deleteRegisterCourse(selectRegisterId)) {
					Helper.showMsg("done");
					loadRegister(student);

				} else
					Helper.showMsg("error");

			}

		});
		openLessonItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectId = (int) tb_studentLesson.getValueAt(tb_studentLesson.getSelectedRow(), 3);
				Lesson lesson = Lesson.getFetch(selectId);
				System.out.println(selectId);
				LessonGUI lessonGUI = new LessonGUI(lesson, student);
				lessonGUI.setVisible(true);
				dispose();

			}
		});
		tb_studentLesson.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int select_row = tb_studentLesson.rowAtPoint(point);
				tb_studentLesson.setRowSelectionInterval(select_row, select_row);
			}

		});
		tb_studentLesson.setComponentPopupMenu(popupStudent);
		tb_studentLesson.setToolTipText("Takip Ettiğin Dersler");

		scrollPane.setViewportView(tb_studentLesson);
		loadRegister(student);
		JLabel lblNewLabel_1 = new JLabel("Kayıtlı Derslerim");
		lblNewLabel_1.setForeground(new Color(68, 75, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNewLabel_1.setBounds(40, 178, 123, 22);
		wreppar.add(lblNewLabel_1);

		JLabel lb_student = new JLabel("Hoşgeldiniz Öğrencimiz Sayın " + student.getName());
		lb_student.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lb_student.setForeground(new Color(68, 75, 255));
		lb_student.setBackground(new Color(68, 75, 255));
		lb_student.setBounds(10, 11, 475, 22);
		wreppar.add(lb_student);

		JLabel lblNewLabel_2 = new JLabel("Çıkış");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaClone\\src\\com\\patikadev\\view\\logout_26px.png"));
		lblNewLabel_2.setBounds(643, 12, 84, 41);
		wreppar.add(lblNewLabel_2);
		loadComboboxPatikaName();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
	}

	public void loadComboboxPatikaName() {

		comboBox_patika.setModel(new DefaultComboBoxModel(new String[] { "" }));
		Patika.getPatikaList()
				.forEach(patika -> {
				comboBox_patika.addItem(new Item(patika.getId(), patika.getName()));
		});
	}

	public void loadRegister(User student) {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_studentLesson.getModel();
		clearDefaultTableModel.setRowCount(0);

		RegisteredCourse.getRegisteredCoursesList()
				.stream()
				.filter(register -> student.getId() == register.getUserId())
				.forEach(register -> {

					columnData[0] = register.getId();
					columnData[1] = register.getPatikaName();
					columnData[2] = register.getCourseName();
					columnData[3] = register.getLessonId();
					registerModel.addRow(columnData);
				});
	}

	public void loadComboBoxLesson(int id) {

		int courseId = Course.getFetch(id);

		comboBox_Lesson.setModel(new DefaultComboBoxModel(new String[] { "" }));
		Educator.getLessonList()
				.forEach(lesson -> {
			if (courseId == lesson.getCourse_id()) {
				comboBox_Lesson.addItem(new Item(lesson.getId(), lesson.getCourse_name()));
			}
		});
	}
}