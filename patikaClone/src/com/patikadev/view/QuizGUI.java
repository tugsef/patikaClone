package com.patikadev.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.patikadev.helper.Config;
import com.patikadev.helper.Helper;
import com.patikadev.model.Quiz;

public class QuizGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6993986381557625953L;
	private JPanel contentPane;
	private static Quiz quiz = new Quiz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizGUI frame = new QuizGUI(quiz);
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
	public QuizGUI(Quiz quiz) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 191, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		contentPane.add(splitPane, gbc_splitPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		JTextArea textArea_question = new JTextArea();
		scrollPane_1.setViewportView(textArea_question);

		JLabel lblNewLabel_1 = new JLabel("Soru       ");
		lblNewLabel_1.setMaximumSize(new Dimension(67, 14));
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setBackground(new Color(254, 191, 94));
		lblNewLabel_1.setPreferredSize(new Dimension(63, 14));
		lblNewLabel_1.setMinimumSize(new Dimension(72, 14));
		splitPane.setLeftComponent(lblNewLabel_1);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_splitPane_1 = new GridBagConstraints();
		gbc_splitPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1.gridx = 0;
		gbc_splitPane_1.gridy = 1;
		contentPane.add(splitPane_1, gbc_splitPane_1);

		JLabel lblNewLabel_1_1 = new JLabel("Doğru Cevap");
		lblNewLabel_1_1.setBackground(new Color(254, 191, 94));
		splitPane_1.setLeftComponent(lblNewLabel_1_1);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane_1_1);

		JTextArea textArea_answer = new JTextArea();
		scrollPane_1_1.setViewportView(textArea_answer);

		JSplitPane splitPane_1_1 = new JSplitPane();
		splitPane_1_1.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_splitPane_1_1 = new GridBagConstraints();
		gbc_splitPane_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_1_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1_1.gridx = 0;
		gbc_splitPane_1_1.gridy = 2;
		contentPane.add(splitPane_1_1, gbc_splitPane_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Yanlış Cevap");
		lblNewLabel_1_1_1.setBackground(new Color(254, 191, 94));
		splitPane_1_1.setLeftComponent(lblNewLabel_1_1_1);

		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		splitPane_1_1.setRightComponent(scrollPane_1_1_1);

		JTextArea textArea_firstwrong = new JTextArea();
		scrollPane_1_1_1.setViewportView(textArea_firstwrong);

		JSplitPane splitPane_1_1_1 = new JSplitPane();
		splitPane_1_1_1.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_splitPane_1_1_1 = new GridBagConstraints();
		gbc_splitPane_1_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1_1_1.gridx = 0;
		gbc_splitPane_1_1_1.gridy = 3;
		contentPane.add(splitPane_1_1_1, gbc_splitPane_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Yanlış Cevap");
		splitPane_1_1_1.setLeftComponent(lblNewLabel_1_1_1_1);

		JScrollPane scrollPane_1_1_1_1 = new JScrollPane();
		splitPane_1_1_1.setRightComponent(scrollPane_1_1_1_1);

		JTextArea textArea_secondwrong = new JTextArea();
		scrollPane_1_1_1_1.setViewportView(textArea_secondwrong);

		JSplitPane splitPane_1_1_1_1 = new JSplitPane();
		splitPane_1_1_1_1.setBackground(new Color(254, 191, 94));
		GridBagConstraints gbc_splitPane_1_1_1_1 = new GridBagConstraints();
		gbc_splitPane_1_1_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_1_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1_1_1_1.gridx = 0;
		gbc_splitPane_1_1_1_1.gridy = 4;
		contentPane.add(splitPane_1_1_1_1, gbc_splitPane_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Yanlış Cevap");
		splitPane_1_1_1_1.setLeftComponent(lblNewLabel_1_1_1_1_1);

		JScrollPane scrollPane_1_1_1_1_1 = new JScrollPane();
		splitPane_1_1_1_1.setRightComponent(scrollPane_1_1_1_1_1);

		JTextArea textArea_thirdwrong = new JTextArea();
		scrollPane_1_1_1_1_1.setViewportView(textArea_thirdwrong);

		JButton btnNewButton = new JButton("Güncelle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = quiz.getId();
				String question = textArea_question.getText();
				String answer = textArea_answer.getText();
				String firstwrong = textArea_firstwrong.getText();
				String secondwrong = textArea_secondwrong.getText();
				String thirdwrong = textArea_thirdwrong.getText();
				if (Helper.confirm()) {
					if (quiz.updateQuiz(id, question, answer, firstwrong, secondwrong, thirdwrong)) {
						Helper.showMsg("done");

					} else {
						Helper.showMsg("fill");
					}
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		textArea_answer.setText(quiz.getAnswer());
		textArea_question.setText(quiz.getAnswer());
		textArea_firstwrong.setText(quiz.getFirstwrongAnswer());
		textArea_secondwrong.setText(quiz.getSecondwrongAnswer());
		textArea_thirdwrong.setText(quiz.getThirdwrongAnswer());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle(Config.PROJE_TITLE);
	}
}
