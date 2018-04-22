package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.User;

public class LoginDialog {
	private JTextField jemail;
	private JPasswordField jpassword;
	private JComponent[] jinputs;

	public LoginDialog() {
		String email = "admin@gmail.com";
		String password = "12345678";

		setData(email, password);

	}

	void setData(String email, String password) {
		jemail = new JTextField(email);
		jpassword = new JPasswordField(password);

		jinputs = new JComponent[] { new JLabel("Email"), jemail, new JLabel("Password"), jpassword };
	}

	public User show(Component parent) {
		int result = JOptionPane.showConfirmDialog(parent, jinputs, ApplicationAssistant.LOGIN_TITLE,
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			User user = null;
			try {
				String email = jemail.getText().trim();
				String password = new String(jpassword.getPassword()).trim();

				if (email.equals("") || password.equals("")) {
					String message = " All Fields: not empty";
					return validation(parent, message);
				}
				user = ApplicationAssistant.getInstance().checkLogin(email, password);
				if (user == null) {
					String message = " Invalid email or password!";
					return validation(parent, message);
				}

			} catch (Exception e) {
				String message = " All Fields: not empty : text \n Email | Password : text \n";
				return validation(parent, message);
			}
			return user;
		} else {
			return null;
		}

	}

	private User validation(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, "Warning!", JOptionPane.WARNING_MESSAGE);
		return show(parent);
	}
}
