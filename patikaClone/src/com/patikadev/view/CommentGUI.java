package com.patikadev.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.model.Comments;
import com.patikadev.model.Lesson;

public class CommentGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6704460407488533134L;

	private JPanel wreppar;
	private static Comments comments = new Comments();
	private static Lesson lesson = new Lesson();
	private JTextArea textArea_comment;
	private String commentString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommentGUI frame = new CommentGUI(comments, lesson);
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
	public CommentGUI(Comments comments, Lesson lesson) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 470);
		wreppar = new JPanel();
		wreppar.setBorder(new EmptyBorder(5, 5, 5, 5));
		wreppar.setBackground(new Color(254, 191, 94));
		wreppar.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(wreppar);
		wreppar.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 594, 170);
		wreppar.add(scrollPane);

		textArea_comment = new JTextArea();
		textArea_comment.setEnabled(false);
		scrollPane.setViewportView(textArea_comment);

		JLabel lblNewLabel = new JLabel("Yorumlar");
		lblNewLabel.setForeground(new Color(68, 75, 255));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setBackground(new Color(68, 75, 255));
		lblNewLabel.setBounds(10, 52, 77, 14);
		wreppar.add(lblNewLabel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 275, 594, 84);
		wreppar.add(scrollPane_1);

		JTextArea text_Area_commet = new JTextArea();
		text_Area_commet.setText("");
		scrollPane_1.setViewportView(text_Area_commet);

		JButton btnNewButton = new JButton("Yorum Ekle");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (text_Area_commet.getText().equals("")) {
					Helper.showMsg("fill");
				} else {
					String commentString = text_Area_commet.getText();
					if (Comments.addComment(comments.getLessonId(), comments.getLessonUserName(), commentString)) {
						Helper.showMsg("done");
						text_Area_commet.setText("");
						loadCommets(lesson);
					} else {
						Helper.showMsg("error");
					}
					loadCommets(lesson);
				}
			}
		});
		btnNewButton.setBounds(245, 370, 109, 23);
		wreppar.add(btnNewButton);

		JLabel lb_lessonName = new JLabel("");
		lb_lessonName.setText(lesson.getCourse_name());
		lb_lessonName.setFont(new Font("Yu Gothic Medium", Font.BOLD, 23));
		lb_lessonName.setForeground(new Color(68, 75, 255));
		lb_lessonName.setBounds(10, 11, 438, 38);
		lb_lessonName.setText(lesson.getCourse_name());
		wreppar.add(lb_lessonName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
		loadCommets(lesson);
	}

	public void loadCommets(Lesson lesson) {
		textArea_comment.setText("");
		commentString = "";
		Comments.getCommetList().stream().filter(com -> com.getLessonId() == lesson.getId()).forEach(commet -> {

			String değerString = commet.getLessonUserName() + "\n" + commet.getCommet() + "\n" + "\n";
			commentString = commentString + değerString;

		});
		textArea_comment.setText(commentString);
	}
}
