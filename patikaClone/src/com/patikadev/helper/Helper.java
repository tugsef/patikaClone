package com.patikadev.helper;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.Document;

public class Helper {
	// Devam eden uygulamada tekrar tekrar kullanılan metodların yzıldığı kısım

	public static void setLayout() {
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
	}

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionalPane.cancelButtonText", "İptal");
		UIManager.put("OptionalPane.yesButtonText", "Evet");
		UIManager.put("OptionalPane.noButtonText", "Hayır");
		UIManager.put("OptionalPane.okButtonText", "Tamam");

	}

	public static void showMsg(String str) {
		optionPaneChangeButtonText();

		String message;

		switch (str) {
		case "fill":   
			message = "Lütfen Tüm Alanları Doldurunuz...";

			break;
		case "done":
			message = "İşlem Başarılı";
			break;
		case "eror":
			message = "İşlem eror";
			break;
		default:
			message = str;
			break;

		}
		
		JOptionPane.showMessageDialog(null, message, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	
	public static boolean confirm() {
		optionPaneChangeButtonText();
		String message = "İşlem Onayı";
		
		int resault = JOptionPane.showConfirmDialog(null, message , "Önemli" , JOptionPane.YES_NO_OPTION);
		
		if(resault ==0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean isFieldEmpty(JTextField jTextField) {
		return jTextField.getText().trim().isEmpty();
	}
	
	public static int screenCenterPoint(String axis, Container container) {
		int point;
		
		switch (axis) {
		case "x":
			point = (Toolkit.getDefaultToolkit().getScreenSize().width - container.getSize().width) / 2;
			return point;
		case "y":
			point = (Toolkit.getDefaultToolkit().getScreenSize().height - container.getSize().height) / 2;
			return point;
		default:
			return 0;
		}
	}
	public static Item getItem(JComboBox jcombobox) {
		Item item =(Item)jcombobox.getSelectedItem();
		return item;
	}
	public static int screenCenterPoint(String axis , Dimension size) {
		int point;
		
		switch (axis) {
		case "x":
			point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
			return point;
		case "y":
			point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
			return point;
		default:
			return 0;
		}
	}

}
