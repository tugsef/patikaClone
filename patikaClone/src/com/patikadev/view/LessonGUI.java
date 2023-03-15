package com.patikadev.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.helper.Item;
import com.patikadev.model.Comments;
import com.patikadev.model.Content;
import com.patikadev.model.Lesson;
import com.patikadev.model.Quiz;
import com.patikadev.model.Student;
import com.patikadev.model.User;

public class LessonGUI extends JFrame {
	private static Lesson lesson = new Lesson();
	private static User student = new Student();
	private JPanel contentPane;
	private JComboBox comboBox_content;
	private JPanel panel_quiz;
	private JTextArea textArea_quiz;
	private ListIterator<Quiz> iterator = null;
	private JRadioButton radio_answer1;
	private JRadioButton radio_answer2;
	private JRadioButton radio_answer3;
	private JRadioButton radio_answer4;
	private boolean next;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LessonGUI frame = new LessonGUI(lesson, student);
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

	public LessonGUI(Lesson lesson, User student) {
		setResizable(false);
		next = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 498);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 191, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 41, 792, 98);
		contentPane.add(scrollPane);

		JTextArea textArea_lesson = new JTextArea();

		textArea_lesson.setText(lesson.getCourse_name() + "\n" + lesson.getExplanation() + "\n" + lesson.getFeature());
		textArea_lesson.setToolTipText(lesson.getCourse_name());
		textArea_lesson.setToolTipText("");
		textArea_lesson.setEnabled(false);
		scrollPane.setViewportView(textArea_lesson);

		JButton btn_Lesson_link = new JButton("Derse Git");
		btn_Lesson_link.setBackground(new Color(255, 255, 255));
		btn_Lesson_link.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lesson.getLink() == null) {
					Helper.showMsg("Geçersiz url...");
				} else {
					try {
						openWebpage(new URI(lesson.getLink()));
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		btn_Lesson_link.setBounds(420, 150, 88, 23);
		contentPane.add(btn_Lesson_link);

		JButton btnTest = new JButton("Teste Başla");
		btnTest.setBackground(new Color(255, 255, 255));
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_content.getSelectedIndex() == 0) {
					Helper.showMsg("Ders Başlığı Seçiniz...");
				} else {
					int selectId = Helper.getItem(comboBox_content).getKey();

					LinkedList<Quiz> quizs = Quiz.getFetchIteretor(selectId);

					if (quizs.equals(null)) {

					} else {
						iterator = quizIterator(quizs);
						if (iterator.hasNext()) {
							Quiz quiz = (Quiz) iterator.next();

							textArea_quiz.setText(quiz.getQuestion());
							radio_answer1.setText(quiz.getAnswer());
							radio_answer2.setText(quiz.getFirstwrongAnswer());
							radio_answer3.setText(quiz.getSecondwrongAnswer());
							radio_answer4.setText(quiz.getThirdwrongAnswer());
						} else {
							System.out.println("boş");
						}
						panel_quiz.setVisible(true);
					}
				}
			}
		});
		btnTest.setToolTipText("Teste Başla");
		btnTest.setBounds(408, 188, 116, 23);
		contentPane.add(btnTest);

		panel_quiz = new JPanel();
		panel_quiz.setBackground(new Color(255, 255, 255));
		panel_quiz.setBounds(69, 222, 792, 201);
		contentPane.add(panel_quiz);
		panel_quiz.setVisible(false);
		panel_quiz.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 792, 50);
		panel_quiz.add(scrollPane_1);

		textArea_quiz = new JTextArea();
		textArea_quiz.setToolTipText("yu7uu7uuuuuuuuuuuuuuuuu");
		textArea_quiz.setEnabled(false);
		scrollPane_1.setViewportView(textArea_quiz);

		radio_answer1 = new JRadioButton("");
		radio_answer1.setBackground(new Color(255, 255, 255));
		radio_answer1.setBounds(33, 57, 517, 23);
		panel_quiz.add(radio_answer1);

		radio_answer2 = new JRadioButton("");
		radio_answer2.setBackground(new Color(255, 255, 255));
		radio_answer2.setBounds(33, 83, 523, 23);
		panel_quiz.add(radio_answer2);

		radio_answer3 = new JRadioButton("");
		radio_answer3.setBackground(new Color(255, 255, 255));
		radio_answer3.setBounds(33, 109, 523, 23);
		panel_quiz.add(radio_answer3);

		radio_answer4 = new JRadioButton("");
		radio_answer4.setBackground(new Color(255, 255, 255));
		radio_answer4.setForeground(new Color(0, 0, 0));
		radio_answer4.setBounds(33, 135, 523, 23);
		panel_quiz.add(radio_answer4);

		JButton btnNewButton_1 = new JButton("İleri");
		btnNewButton_1.setBackground(new Color(254, 191, 94));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!next) {
					if (iterator.hasNext()) {
						Quiz quiz = (Quiz) iterator.next();

						textArea_quiz.setText(quiz.getQuestion());
						radio_answer1.setText(quiz.getAnswer());
						radio_answer2.setText(quiz.getFirstwrongAnswer());
						radio_answer3.setText(quiz.getSecondwrongAnswer());
						radio_answer4.setText(quiz.getThirdwrongAnswer());

					}
					next = true;

				}
				if (iterator.hasNext()) {

					if (iterator.hasNext()) {
						Quiz quiz = (Quiz) iterator.next();

						textArea_quiz.setText(quiz.getQuestion());
						radio_answer1.setText(quiz.getAnswer());
						radio_answer2.setText(quiz.getFirstwrongAnswer());
						radio_answer3.setText(quiz.getSecondwrongAnswer());
						radio_answer4.setText(quiz.getThirdwrongAnswer());

					}

				} else {

					next = true;

				}
			}
		});
		btnNewButton_1.setBounds(405, 167, 89, 23);
		panel_quiz.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Geri");
		btnNewButton_1_1.setBackground(new Color(254, 191, 94));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (next) {
					if (iterator.hasPrevious()) {
						Quiz quiz = (Quiz) iterator.previous();

						textArea_quiz.setText(quiz.getQuestion());
						radio_answer1.setText(quiz.getAnswer());
						radio_answer2.setText(quiz.getFirstwrongAnswer());
						radio_answer3.setText(quiz.getSecondwrongAnswer());
						radio_answer4.setText(quiz.getThirdwrongAnswer());

					}
					next = false;

				}
				if (iterator.hasPrevious()) {
					Quiz quiz = (Quiz) iterator.previous();

					textArea_quiz.setText(quiz.getQuestion());
					radio_answer1.setText(quiz.getAnswer());
					radio_answer2.setText(quiz.getFirstwrongAnswer());
					radio_answer3.setText(quiz.getSecondwrongAnswer());
					radio_answer4.setText(quiz.getThirdwrongAnswer());

				} else {

				}

			}
		});
		btnNewButton_1_1.setBounds(293, 167, 89, 23);
		panel_quiz.add(btnNewButton_1_1);

		comboBox_content = new JComboBox();
		comboBox_content.setBackground(new Color(255, 255, 255));
		comboBox_content.setBounds(238, 150, 172, 22);
		contentPane.add(comboBox_content);

		JLabel lblNewLabel = new JLabel("Konu Seç");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(179, 155, 63, 18);
		contentPane.add(lblNewLabel);

		JButton btnYorumlar = new JButton("Yorumlar");
		btnYorumlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comments comments = new Comments();
				comments.setLessonId(lesson.getId());
				comments.setLessonUserName(student.getUserName());
				CommentGUI commentGUI = new CommentGUI(comments, lesson);
				commentGUI.setVisible(true);
			}
		});
		btnYorumlar.setToolTipText("Teste Başla");
		btnYorumlar.setBackground(Color.WHITE);
		btnYorumlar.setBounds(408, 425, 116, 23);
		contentPane.add(btnYorumlar);

		JLabel lb_link = new JLabel("");
		lb_link.setForeground(new Color(68, 75, 255));
		lb_link.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lb_link.setBackground(new Color(0, 0, 255));
		lb_link.setBounds(518, 154, 420, 14);
		contentPane.add(lb_link);
		loadComboboxContentList(lesson.getId());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
		lb_link.setText(lesson.getLink());

		JLabel lb_lesson_name = new JLabel("");
		lb_lesson_name.setForeground(new Color(68, 75, 255));
		lb_lesson_name.setFont(new Font("Yu Gothic Medium", Font.BOLD, 23));
		lb_lesson_name.setBounds(69, 11, 306, 30);
		lb_lesson_name.setText(lesson.getCourse_name());
		contentPane.add(lb_lesson_name);

	}

	public void loadComboboxContentList(int lesson_id) {
		comboBox_content.setModel(new DefaultComboBoxModel(new String[] { "" }));
		Content.getContentList().forEach(content -> {
			if (content.getLessonId() == lesson_id)
				comboBox_content.addItem(new Item(content.getId(), content.getName()));
		});
	}

	public void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ListIterator<Quiz> quizIterator(LinkedList<Quiz> quizs) {
		ListIterator<Quiz> iterator = quizs.listIterator();
		return iterator;

	}
}
