package com.patikadev.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.helper.Item;
import com.patikadev.model.Content;
import com.patikadev.model.Course;
import com.patikadev.model.Educator;
import com.patikadev.model.Quiz;
import com.patikadev.model.User;

public class EducatorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1328654345891399522L;

	private JPanel wreppar;

	private static User educator = new Educator();
	private JTextField fld_add_Content;
	private JTextField fld_add_question;
	private JTextField fld_quiz_answer;
	private JTextField fld_quiz_f;
	private JTextField fld_quiz_2f;
	private JTextField fld_quiz_3f;
	private JTextField fld_link;
	private JTable tb_lesson;
	private JTextField fld_deleteLesson;
	private JComboBox cmb_operatorLessons;
	private JTextArea textPane_explanation;
	private JTextArea textPane_feature;
	private JComboBox cmn_lesson;

	private DefaultTableModel lessonModel;
	private Object[] column_lessonData = null;

	private DefaultTableModel contentModel;
	private Object[] column_connetData = null;
	private JTable tb_content;

	private DefaultTableModel quizModel;
	private Object[] column_quizData = null;

	private JPopupMenu popupQuiz;
	private JPopupMenu popupMenu;
	private JTable tb_quiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EducatorGUI frame = new EducatorGUI(educator);
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
	public EducatorGUI(User educator) {
		setResizable(false);

		// lessonLİst
		lessonModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4)
					return false;
				return super.isCellEditable(row, column);
			}
		};

		Object[] column_lessonName = { "id", "Ders Adı", "Özellik", "Açıklama", "link" };
		column_lessonData = new Object[column_lessonName.length];
		lessonModel.setColumnIdentifiers(column_lessonName);
		Educator.getLessonList().stream().forEach(lesson -> {
			column_lessonData[0] = lesson.getId();
			column_lessonData[1] = lesson.getCourse_name();
			column_lessonData[2] = lesson.getFeature();
			column_lessonData[3] = lesson.getExplanation();
			column_lessonData[4] = lesson.getLink();
			lessonModel.addRow(column_lessonData);

		});

		// ## lessonList

		// content List
		contentModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0)
					return false;
				return super.isCellEditable(row, column);
			}
		};

		Object[] column_contnetName = { "id", "Dersin İçeriği" };
		column_connetData = new Object[column_contnetName.length];
		contentModel.setColumnIdentifiers(column_contnetName);

		// ## content List

		// quizList

		quizModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3)
					return false;
				return super.isCellEditable(row, column);
			}
		};

		Object[] column_quizName = { "id", "Soru", "Doğru Cevap" };
		quizModel.setColumnIdentifiers(column_quizName);
		column_quizData = new Object[column_quizName.length];

		// ## quizList
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 512);
		wreppar = new JPanel();
		wreppar.setBackground(new Color(254, 191, 94));
		wreppar.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
		setContentPane(wreppar);
		GridBagLayout gbl_wreppar = new GridBagLayout();
		gbl_wreppar.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_wreppar.rowHeights = new int[] { 0, 0, 0 };
		gbl_wreppar.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_wreppar.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		wreppar.setLayout(gbl_wreppar);

		JPanel w_north = new JPanel();
		w_north.setIgnoreRepaint(true);
		w_north.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_w_north = new GridBagConstraints();
		gbc_w_north.gridwidth = 3;
		gbc_w_north.fill = GridBagConstraints.HORIZONTAL;
		gbc_w_north.anchor = GridBagConstraints.NORTH;
		gbc_w_north.insets = new Insets(0, 0, 5, 0);
		gbc_w_north.gridx = 0;
		gbc_w_north.gridy = 0;
		wreppar.add(w_north, gbc_w_north);
		GridBagLayout gbl_w_north = new GridBagLayout();
		gbl_w_north.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gbl_w_north.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_w_north.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_w_north.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		w_north.setLayout(gbl_w_north);

		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.setBackground(new Color(254, 191, 94));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lbl_welcome = new JLabel("Değerli Eğitmenimiz Hoşgeldiniz");
		lbl_welcome.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lbl_welcome = new GridBagConstraints();
		gbc_lbl_welcome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_welcome.gridwidth = 2;
		gbc_lbl_welcome.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_welcome.gridx = 1;
		gbc_lbl_welcome.gridy = 0;
		w_north.add(lbl_welcome, gbc_lbl_welcome);
		btnNewButton.setIcon(new ImageIcon(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\Java 102\\patikaKlonu\\patikaKlonu\\src\\com\\patikadev\\view\\logout_26px.png"));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.gridx = 22;
		gbc_btnNewButton.gridy = 0;
		w_north.add(btnNewButton, gbc_btnNewButton);

		JLabel lbl_educator = new JLabel("Sayın  ");
		lbl_educator.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lbl_educator = new GridBagConstraints();
		gbc_lbl_educator.gridwidth = 3;
		gbc_lbl_educator.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_educator.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_educator.gridx = 1;
		gbc_lbl_educator.gridy = 1;
		w_north.add(lbl_educator, gbc_lbl_educator);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 3;
		gbc_tabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		wreppar.add(tabbedPane, gbc_tabbedPane);
		lbl_educator.setText(lbl_educator.getText() + educator.getName());

		JPanel pnl_lessons = new JPanel();
		pnl_lessons.setBackground(new Color(254, 191, 94));
		tabbedPane.addTab("Dersler", null, pnl_lessons, null);
		GridBagLayout gbl_pnl_lessons = new GridBagLayout();
		gbl_pnl_lessons.columnWidths = new int[] { 0, 0, 0 };
		gbl_pnl_lessons.rowHeights = new int[] { 0, 0 };
		gbl_pnl_lessons.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnl_lessons.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		pnl_lessons.setLayout(gbl_pnl_lessons);

		JPanel pnl_lesson_list = new JPanel();
		GridBagConstraints gbc_pnl_lesson_list = new GridBagConstraints();
		gbc_pnl_lesson_list.insets = new Insets(0, 0, 0, 5);
		gbc_pnl_lesson_list.fill = GridBagConstraints.BOTH;
		gbc_pnl_lesson_list.gridx = 0;
		gbc_pnl_lesson_list.gridy = 0;
		pnl_lessons.add(pnl_lesson_list, gbc_pnl_lesson_list);
		GridBagLayout gbl_pnl_lesson_list = new GridBagLayout();
		gbl_pnl_lesson_list.columnWidths = new int[] { 0, 0 };
		gbl_pnl_lesson_list.rowHeights = new int[] { 0, 0 };
		gbl_pnl_lesson_list.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnl_lesson_list.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		pnl_lesson_list.setLayout(gbl_pnl_lesson_list);

		JScrollPane scrollPane_lesson = new JScrollPane();
		GridBagConstraints gbc_scrollPane_lesson = new GridBagConstraints();
		gbc_scrollPane_lesson.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_lesson.gridx = 0;
		gbc_scrollPane_lesson.gridy = 0;
		pnl_lesson_list.add(scrollPane_lesson, gbc_scrollPane_lesson);

		tb_lesson = new JTable(lessonModel);
		tb_lesson.setSelectionForeground(new Color(255, 255, 255));
		tb_lesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fld_deleteLesson.setText(tb_lesson.getValueAt(tb_lesson.getSelectedRow(), 0).toString());
//				int selectId =Integer.parseInt(tb_lesson.getValueAt(tb_lesson.getSelectedRow(), 0).toString()) ;
				String selectFeature = tb_lesson.getValueAt(tb_lesson.getSelectedRow(), 2).toString();
				textPane_feature.setText(selectFeature);
				String selectExplanation = tb_lesson.getValueAt(tb_lesson.getSelectedRow(), 3).toString();
				textPane_explanation.setText(selectExplanation);
				String selectLink = tb_lesson.getValueAt(tb_lesson.getSelectedRow(), 4).toString();
				fld_link.setText(selectLink);
			}
		});

		tb_lesson.setSelectionBackground(new Color(254, 191, 94));
		tb_lesson.setGridColor(new Color(255, 128, 0));
		tb_lesson.setBackground(new Color(255, 255, 255));
		tb_lesson.getTableHeader().setReorderingAllowed(false);
		tb_lesson.getTableHeader().setBackground(new Color(255, 255, 255));
		tb_lesson.getColumnModel().getColumn(0).setMaxWidth(75);
		scrollPane_lesson.setViewportView(tb_lesson);

		JPanel pnl_lesson_add = new JPanel();
		pnl_lesson_add.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_pnl_lesson_add = new GridBagConstraints();
		gbc_pnl_lesson_add.fill = GridBagConstraints.VERTICAL;
		gbc_pnl_lesson_add.gridx = 1;
		gbc_pnl_lesson_add.gridy = 0;
		pnl_lessons.add(pnl_lesson_add, gbc_pnl_lesson_add);
		GridBagLayout gbl_pnl_lesson_add = new GridBagLayout();
		gbl_pnl_lesson_add.columnWidths = new int[] { 0, 0 };
		gbl_pnl_lesson_add.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnl_lesson_add.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnl_lesson_add.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnl_lesson_add.setLayout(gbl_pnl_lesson_add);

		JLabel lblNewLabel = new JLabel("Atanan Dersler");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pnl_lesson_add.add(lblNewLabel, gbc_lblNewLabel);

		cmb_operatorLessons = new JComboBox();
		cmb_operatorLessons.setBackground(new Color(255, 255, 255));
		cmb_operatorLessons.setMinimumSize(new Dimension(46, 22));
		cmb_operatorLessons.setModel(new DefaultComboBoxModel(new String[] { "" }));

		GridBagConstraints gbc_cmb_operatorLessons = new GridBagConstraints();
		gbc_cmb_operatorLessons.anchor = GridBagConstraints.NORTH;
		gbc_cmb_operatorLessons.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_operatorLessons.insets = new Insets(0, 0, 5, 0);
		gbc_cmb_operatorLessons.gridx = 0;
		gbc_cmb_operatorLessons.gridy = 1;

		pnl_lesson_add.add(cmb_operatorLessons, gbc_cmb_operatorLessons);

		JLabel lblzelli = new JLabel("Açıklama");
		lblzelli.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblzelli = new GridBagConstraints();
		gbc_lblzelli.insets = new Insets(0, 0, 5, 0);
		gbc_lblzelli.gridx = 0;
		gbc_lblzelli.gridy = 2;
		pnl_lesson_add.add(lblzelli, gbc_lblzelli);

		textPane_explanation = new JTextArea();
		GridBagConstraints gbc_textPane_explanation = new GridBagConstraints();
		gbc_textPane_explanation.gridheight = 2;
		gbc_textPane_explanation.insets = new Insets(0, 0, 5, 0);
		gbc_textPane_explanation.fill = GridBagConstraints.BOTH;
		gbc_textPane_explanation.gridx = 0;
		gbc_textPane_explanation.gridy = 3;
		pnl_lesson_add.add(textPane_explanation, gbc_textPane_explanation);

		JLabel lblNewLabel_1 = new JLabel("Özellik");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 5;
		pnl_lesson_add.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textPane_feature = new JTextArea();
		textPane_feature.setFocusCycleRoot(true);
		textPane_feature.setFocusTraversalPolicyProvider(true);
		textPane_feature.setMaximumSize(new Dimension(25, 25));
		GridBagConstraints gbc_textPane_feature = new GridBagConstraints();
		gbc_textPane_feature.gridheight = 4;
		gbc_textPane_feature.insets = new Insets(0, 0, 5, 0);
		gbc_textPane_feature.fill = GridBagConstraints.BOTH;
		gbc_textPane_feature.gridx = 0;
		gbc_textPane_feature.gridy = 6;
		pnl_lesson_add.add(textPane_feature, gbc_textPane_feature);

		JButton btn_addLesson = new JButton("Ekle");
		btn_addLesson.setBackground(new Color(255, 255, 255));
		btn_addLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Helper.isFieldEmpty(fld_link) || cmb_operatorLessons.getSelectedItem().equals("")
						|| textPane_explanation.getText().equals("") || textPane_feature.getText().equals("")) {
					Helper.showMsg("fill");
				} else {
					Item comboItem = (Item) cmb_operatorLessons.getSelectedItem();
					int course_id = comboItem.getKey();
					String course_name = comboItem.getValue();
					String feature = textPane_feature.getText();
					String explanation = textPane_explanation.getText();
					String link = fld_link.getText();
					if (Educator.addCourse(course_id, course_name, feature, explanation, link)) {
						textPane_feature.setText(null);
						textPane_explanation.setText(null);
						fld_link.setText(null);
						cmb_operatorLessons.setSelectedIndex(0);
						loadComboboxLessonName();
						loadLessonList();
						Helper.showMsg("done");
					} else {
						Helper.showMsg("error");
					}
				}

			}
		});

		JLabel lblNewLabel_2 = new JLabel("Ders Linki");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 10;
		pnl_lesson_add.add(lblNewLabel_2, gbc_lblNewLabel_2);

		fld_link = new JTextField();
		fld_link.setMargin(new Insets(2, 100, 2, 100));
		GridBagConstraints gbc_fld_link = new GridBagConstraints();
		gbc_fld_link.insets = new Insets(0, 0, 5, 0);
		gbc_fld_link.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_link.gridx = 0;
		gbc_fld_link.gridy = 11;
		pnl_lesson_add.add(fld_link, gbc_fld_link);
		fld_link.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Alanları Temizle");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadisEmpty();
			}
		});
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 12;
		pnl_lesson_add.add(lblNewLabel_4, gbc_lblNewLabel_4);
		GridBagConstraints gbc_btn_addLesson = new GridBagConstraints();
		gbc_btn_addLesson.insets = new Insets(0, 0, 5, 0);
		gbc_btn_addLesson.gridx = 0;
		gbc_btn_addLesson.gridy = 13;
		pnl_lesson_add.add(btn_addLesson, gbc_btn_addLesson);

		JButton btn_updateLeson = new JButton("Güncelle");
		btn_updateLeson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb_lesson.getSelectedRow() != -1) {

					if (Helper.isFieldEmpty(fld_link) || cmb_operatorLessons.getSelectedItem().equals("")
							|| textPane_explanation.getText().equals("") || textPane_feature.getText().equals("")) {
						Helper.showMsg("fill");
					} else {
						int id = Integer.parseInt(fld_deleteLesson.getText());
						Item comboItem = (Item) cmb_operatorLessons.getSelectedItem();
						int course_id = comboItem.getKey();
						String course_name = comboItem.getValue();
						String feature = textPane_feature.getText();
						String explanation = textPane_explanation.getText();
						String link = fld_link.getText();
						if (Educator.updateLesson(id, course_id, course_name, feature, explanation, link)) {
							textPane_feature.setText(null);
							textPane_explanation.setText(null);
							fld_link.setText(null);
							cmb_operatorLessons.setSelectedIndex(0);
							loadLessonList();
							Helper.showMsg("done");
						} else {
							Helper.showMsg("error");
						}
					}
				} else {
					Helper.showMsg("Güncellenecek Ders Seçiniz...");
				}

			}
		});
		btn_updateLeson.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_updateLeson = new GridBagConstraints();
		gbc_btn_updateLeson.insets = new Insets(0, 0, 5, 0);
		gbc_btn_updateLeson.gridx = 0;
		gbc_btn_updateLeson.gridy = 14;
		pnl_lesson_add.add(btn_updateLeson, gbc_btn_updateLeson);

		fld_deleteLesson = new JTextField();
		fld_deleteLesson.setEnabled(false);

		GridBagConstraints gbc_fld_deleteLesson = new GridBagConstraints();
		gbc_fld_deleteLesson.insets = new Insets(0, 0, 5, 0);
		gbc_fld_deleteLesson.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_deleteLesson.gridx = 0;
		gbc_fld_deleteLesson.gridy = 15;
		pnl_lesson_add.add(fld_deleteLesson, gbc_fld_deleteLesson);
		fld_deleteLesson.setColumns(10);

		JButton bttn_deleteLesson = new JButton("Sil");
		bttn_deleteLesson.setBackground(new Color(255, 255, 255));
		bttn_deleteLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb_lesson.getSelectedRow() != -1) {
					int id = (int) tb_lesson.getValueAt(tb_lesson.getSelectedRow(), 0);
					if (Helper.confirm()) {
						if (Educator.deleteLesson(id)) {
							Helper.showMsg("done");

							loadisEmpty();
							loadLessonList();
							loadComboboxLessonName();
						} else {
							Helper.showMsg("error");
						}
					}

				} else {
					Helper.showMsg("Tablodan Ders Seçiniz...");
				}
			}

		});
		GridBagConstraints gbc_bttn_deleteLesson = new GridBagConstraints();
		gbc_bttn_deleteLesson.insets = new Insets(0, 0, 5, 0);
		gbc_bttn_deleteLesson.gridx = 0;
		gbc_bttn_deleteLesson.gridy = 16;
		pnl_lesson_add.add(bttn_deleteLesson, gbc_bttn_deleteLesson);

		JPanel pnl_content = new JPanel();
		pnl_content.setBackground(new Color(254, 191, 94));
		tabbedPane.addTab("İçerik Sorular", null, pnl_content, null);
		GridBagLayout gbl_pnl_content = new GridBagLayout();
		gbl_pnl_content.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnl_content.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnl_content.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnl_content.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		pnl_content.setLayout(gbl_pnl_content);

		JScrollPane scrollPane_content = new JScrollPane();
		GridBagConstraints gbc_scrollPane_content = new GridBagConstraints();
		gbc_scrollPane_content.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_content.gridheight = 21;
		gbc_scrollPane_content.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_content.gridx = 0;
		gbc_scrollPane_content.gridy = 0;
		pnl_content.add(scrollPane_content, gbc_scrollPane_content);

		tb_content = new JTable(contentModel);
		tb_content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectId = (int) tb_content.getValueAt(tb_content.getSelectedRow(), 0);
				loadQuizList(selectId);
			}
		});
		tb_content.getModel().addTableModelListener(e -> {
			if (e.getType() == TableModelEvent.UPDATE) {
				int selectId = Integer.parseInt(tb_content.getValueAt(tb_content.getSelectedRow(), 0).toString());
				String selectName = tb_content.getValueAt(tb_content.getSelectedRow(), 1).toString();

				if (selectName.isEmpty()) {
					Helper.showMsg("fill");
				} else {
					if (Helper.confirm()) {
						if (Content.updateContent(selectId, selectName)) {
							Helper.showMsg("done");
							loadContentList(cmn_lesson);
						} else
							Helper.showMsg("error");

					}
				}

			}

		});

		popupMenu = new JPopupMenu();
		JMenuItem delItem = new JMenuItem("Sil");
		popupMenu.add(delItem);

		delItem.addActionListener(e -> {
			if (Helper.confirm()) {
				int selectContentId = (int) tb_content.getValueAt(tb_content.getSelectedRow(), 0);
				if (Content.deleteContent(selectContentId)) {
					Helper.showMsg("done");
					loadContentList(cmn_lesson);

				} else
					Helper.showMsg("error");

			}

		});
		tb_content.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int select_row = tb_content.rowAtPoint(point);
				tb_content.setRowSelectionInterval(select_row, select_row);
			}

		});

		tb_content.setSelectionForeground(new Color(255, 255, 255));
		tb_content.setSelectionBackground(new Color(254, 191, 94));
		tb_content.setGridColor(new Color(255, 128, 0));
		tb_content.setBackground(new Color(255, 255, 255));
		tb_content.getTableHeader().setReorderingAllowed(false);
		tb_content.getTableHeader().setBackground(new Color(255, 255, 255));
		tb_content.getColumnModel().getColumn(0).setMaxWidth(50);
		scrollPane_content.setViewportView(tb_content);
		tb_content.setComponentPopupMenu(popupMenu);
		JScrollPane scrollPane_query = new JScrollPane();
		GridBagConstraints gbc_scrollPane_query = new GridBagConstraints();
		gbc_scrollPane_query.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_query.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_query.gridheight = 21;
		gbc_scrollPane_query.gridx = 4;
		gbc_scrollPane_query.gridy = 0;
		pnl_content.add(scrollPane_query, gbc_scrollPane_query);

		tb_quiz = new JTable(quizModel);

		popupQuiz = new JPopupMenu();
		JMenuItem delQuizItem = new JMenuItem("Sil");
		JMenuItem updateItem = new JMenuItem("Düzenle");
		popupQuiz.add(updateItem);
		popupQuiz.add(delQuizItem);

		delQuizItem.addActionListener(e -> {
			if (Helper.confirm()) {
				int selectContentId = (int) tb_quiz.getValueAt(tb_quiz.getSelectedRow(), 0);
				if (Quiz.deleteQuiz(selectContentId)) {
					Helper.showMsg("done");
					int selectId = (int) tb_content.getValueAt(tb_content.getSelectedRow(), 0);
					loadQuizList(selectId);

				} else
					Helper.showMsg("error");

			}

		});
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = Integer.parseInt(tb_quiz.getValueAt(tb_quiz.getSelectedRow(), 0).toString());
				Quiz quiz = Quiz.getFetch(selectRow);

				QuizGUI quizGUI = new QuizGUI(quiz);
				updateItem.setLocation(Helper.screenCenterPoint("x", quizGUI), Helper.screenCenterPoint("y", quizGUI));
				quizGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				quizGUI.setVisible(rootPaneCheckingEnabled);
				quizGUI.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						int selectId = (int) tb_content.getValueAt(tb_content.getSelectedRow(), 0);
						loadQuizList(selectId);
						loadQuizList(selectId);

					}

				});

			}
		});
		tb_quiz.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int select_row = tb_quiz.rowAtPoint(point);
				tb_quiz.setRowSelectionInterval(select_row, select_row);
			}

		});

		tb_quiz.setSelectionForeground(new Color(255, 255, 255));
		tb_quiz.setSelectionBackground(new Color(254, 191, 94));
		tb_quiz.setGridColor(new Color(255, 128, 0));
		tb_quiz.setBackground(new Color(255, 255, 255));
		tb_quiz.getTableHeader().setReorderingAllowed(false);
		tb_quiz.getTableHeader().setBackground(new Color(255, 255, 255));
		tb_quiz.getColumnModel().getColumn(0).setMaxWidth(50);
		tb_quiz.setComponentPopupMenu(popupQuiz);
		scrollPane_query.setViewportView(tb_quiz);

		JLabel lblNewLabel_2_1 = new JLabel("Ders Seçiniz");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 2;
		gbc_lblNewLabel_2_1.gridy = 1;
		pnl_content.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

		cmn_lesson = new JComboBox();
		cmn_lesson.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmn_lesson.getSelectedIndex() != 0) {
					loadContentList(cmn_lesson);
				}

			}
		});

		cmn_lesson.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_cmn_lesson = new GridBagConstraints();
		gbc_cmn_lesson.insets = new Insets(0, 0, 5, 5);
		gbc_cmn_lesson.gridx = 2;
		gbc_cmn_lesson.gridy = 2;
		pnl_content.add(cmn_lesson, gbc_cmn_lesson);

		JLabel lblNewLabel_3 = new JLabel("Konu Ekle");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 3;
		pnl_content.add(lblNewLabel_3, gbc_lblNewLabel_3);

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 1;
		gbc_horizontalGlue.gridy = 4;
		pnl_content.add(horizontalGlue, gbc_horizontalGlue);

		fld_add_Content = new JTextField();
		fld_add_Content.setColumns(10);
		GridBagConstraints gbc_fld_add_Content = new GridBagConstraints();
		gbc_fld_add_Content.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_add_Content.insets = new Insets(0, 0, 5, 5);
		gbc_fld_add_Content.gridx = 2;
		gbc_fld_add_Content.gridy = 4;
		pnl_content.add(fld_add_Content, gbc_fld_add_Content);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_1.gridx = 3;
		gbc_horizontalGlue_1.gridy = 4;
		pnl_content.add(horizontalGlue_1, gbc_horizontalGlue_1);

		JButton btn_add_content = new JButton("Ekle");
		btn_add_content.setBorder(null);
		btn_add_content.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lesson_id = Helper.getItem(cmn_lesson).getKey();
				String name = fld_add_Content.getText();
				if (name.isEmpty())
					Helper.showMsg("fill");
				else {
					if (Content.addContent(lesson_id, name)) {
						Helper.showMsg("done");
						loadContentList(cmn_lesson);
						fld_add_Content.setText("");
					} else {
						Helper.showMsg("error");
					}

				}

			}
		});
		btn_add_content.setMargin(new Insets(2, 45, 2, 45));
		GridBagConstraints gbc_btn_add_content = new GridBagConstraints();
		gbc_btn_add_content.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add_content.gridx = 2;
		gbc_btn_add_content.gridy = 5;
		pnl_content.add(btn_add_content, gbc_btn_add_content);

		JLabel lblNewLabel_1_2 = new JLabel("Soru Ekle");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 2;
		gbc_lblNewLabel_1_2.gridy = 7;
		pnl_content.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

		fld_add_question = new JTextField();
		fld_add_question.setColumns(10);
		GridBagConstraints gbc_fld_add_question = new GridBagConstraints();
		gbc_fld_add_question.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_add_question.insets = new Insets(0, 0, 5, 5);
		gbc_fld_add_question.gridx = 2;
		gbc_fld_add_question.gridy = 8;
		pnl_content.add(fld_add_question, gbc_fld_add_question);

		JLabel lblNewLabel_1_1_2 = new JLabel("Doğru Cevap");
		GridBagConstraints gbc_lblNewLabel_1_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_2.gridx = 2;
		gbc_lblNewLabel_1_1_2.gridy = 9;
		pnl_content.add(lblNewLabel_1_1_2, gbc_lblNewLabel_1_1_2);

		fld_quiz_answer = new JTextField();
		fld_quiz_answer.setColumns(10);
		GridBagConstraints gbc_fld_quiz_answer = new GridBagConstraints();
		gbc_fld_quiz_answer.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_quiz_answer.insets = new Insets(0, 0, 5, 5);
		gbc_fld_quiz_answer.gridx = 2;
		gbc_fld_quiz_answer.gridy = 10;
		pnl_content.add(fld_quiz_answer, gbc_fld_quiz_answer);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Yanlış Cevap");
		GridBagConstraints gbc_lblNewLabel_1_1_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_2.gridx = 2;
		gbc_lblNewLabel_1_1_1_2.gridy = 11;
		pnl_content.add(lblNewLabel_1_1_1_2, gbc_lblNewLabel_1_1_1_2);

		fld_quiz_f = new JTextField();
		fld_quiz_f.setColumns(10);
		GridBagConstraints gbc_fld_quiz_f = new GridBagConstraints();
		gbc_fld_quiz_f.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_quiz_f.insets = new Insets(0, 0, 5, 5);
		gbc_fld_quiz_f.gridx = 2;
		gbc_fld_quiz_f.gridy = 12;
		pnl_content.add(fld_quiz_f, gbc_fld_quiz_f);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Yanlış Cevap");
		GridBagConstraints gbc_lblNewLabel_1_1_1_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_1_2.gridx = 2;
		gbc_lblNewLabel_1_1_1_1_2.gridy = 13;
		pnl_content.add(lblNewLabel_1_1_1_1_2, gbc_lblNewLabel_1_1_1_1_2);

		fld_quiz_2f = new JTextField();
		fld_quiz_2f.setColumns(10);
		GridBagConstraints gbc_fld_quiz_2f = new GridBagConstraints();
		gbc_fld_quiz_2f.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_quiz_2f.insets = new Insets(0, 0, 5, 5);
		gbc_fld_quiz_2f.gridx = 2;
		gbc_fld_quiz_2f.gridy = 14;
		pnl_content.add(fld_quiz_2f, gbc_fld_quiz_2f);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Yanlış Cevap");
		GridBagConstraints gbc_lblNewLabel_1_1_1_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1_1_1_1.gridy = 15;
		pnl_content.add(lblNewLabel_1_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1_1);

		fld_quiz_3f = new JTextField();
		fld_quiz_3f.setColumns(10);
		GridBagConstraints gbc_fld_quiz_3f = new GridBagConstraints();
		gbc_fld_quiz_3f.fill = GridBagConstraints.HORIZONTAL;
		gbc_fld_quiz_3f.insets = new Insets(0, 0, 5, 5);
		gbc_fld_quiz_3f.gridx = 2;
		gbc_fld_quiz_3f.gridy = 16;
		pnl_content.add(fld_quiz_3f, gbc_fld_quiz_3f);

		JButton btn_quiz_add = new JButton("Ekle");
		btn_quiz_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int content_id = (int) tb_content.getValueAt(tb_content.getSelectedRow(), 0);

				System.out.println(content_id);
				if (content_id != -1) {

					String question = fld_add_question.getText();
					String answer = fld_quiz_answer.getText();
					String firstwrong = fld_quiz_f.getText();
					String secondwrong = fld_quiz_2f.getText();
					String thirdwrong = fld_quiz_3f.getText();

					if (Quiz.addQuiz(content_id, question, answer, firstwrong, secondwrong, thirdwrong)) {
						loadQuizList(content_id);
						quizFldisEmpty();
						Helper.showMsg("done");
					} else {
						Helper.showMsg("error");
					}

				} else {
					Helper.showMsg("Lütfen Tablodan İçerik Seçiniz...");
				}
			}
		});
		btn_quiz_add.setMargin(new Insets(2, 45, 2, 45));
		GridBagConstraints gbc_btn_quiz_add = new GridBagConstraints();
		gbc_btn_quiz_add.insets = new Insets(0, 0, 5, 5);
		gbc_btn_quiz_add.gridx = 2;
		gbc_btn_quiz_add.gridy = 17;
		pnl_content.add(btn_quiz_add, gbc_btn_quiz_add);
		loadComboboxLessonName();
		loadOperatorLesson(educator);
		loadLessonList();

	}

	public void loadContentList(JComboBox jComboBox) {

		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_content.getModel();
		clearDefaultTableModel.setRowCount(0);

		Content.getContentList().stream().forEach(cont -> {

			Item lessonItem = (Item) jComboBox.getSelectedItem();
			int lessonId = lessonItem.getKey();

			if (cont.getLessonId() == lessonId) {
				column_connetData[0] = cont.getId();
				column_connetData[1] = cont.getName();

				contentModel.addRow(column_connetData);

			}

		});
	}

	public void loadQuizList(int id) {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_quiz.getModel();
		clearDefaultTableModel.setRowCount(0);
		Quiz.getQuizList().stream().forEach(quiz -> {
			System.out.println("çalıştı");
			System.out.println(id);
			System.out.println(quiz.getContentId());
			if (id == quiz.getContentId()) {
				column_quizData[0] = quiz.getId();
				column_quizData[1] = quiz.getQuestion();
				column_quizData[2] = quiz.getAnswer();
				quizModel.addRow(column_quizData);
			}
		});
	}

	public void loadComboboxLessonName() {

		cmn_lesson.setModel(new DefaultComboBoxModel(new String[] { "" }));
		Educator.getLessonList().forEach(lesson -> {
			cmn_lesson.addItem(new Item(lesson.getId(), lesson.getCourse_name()));
		});
	}

	public void loadOperatorLesson(User educator) {
		Course.getCourseList().stream().forEach(course -> {
			if (course.getUser_id() == educator.getId()) {
				cmb_operatorLessons.addItem(new Item(course.getId(), course.getName()));
			}
		});

	}

	public void loadLessonList() {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_lesson.getModel();
		clearDefaultTableModel.setRowCount(0);
		Educator.getLessonList().stream().forEach(lesson -> {
			column_lessonData[0] = lesson.getId();
			column_lessonData[1] = lesson.getCourse_name();
			column_lessonData[2] = lesson.getFeature();
			column_lessonData[3] = lesson.getExplanation();
			column_lessonData[4] = lesson.getLink();
			lessonModel.addRow(column_lessonData);

		});
	}

	public void loadisEmpty() {
		textPane_feature.setText(null);
		textPane_explanation.setText(null);
		fld_link.setText(null);
		cmb_operatorLessons.setSelectedIndex(0);
		fld_deleteLesson.setText("");
	}

	public void quizFldisEmpty() {
		fld_add_question.setText("");
		fld_quiz_answer.setText("");
		fld_quiz_f.setText("");
		fld_quiz_2f.setText("");
		fld_quiz_3f.setText("");
		fld_add_question.setText("");

	}

}
