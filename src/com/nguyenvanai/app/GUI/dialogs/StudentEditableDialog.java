/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.managers.ValidationManager;
import com.nguyenvanai.app.models.Batch;
import com.nguyenvanai.app.models.Student;


public class StudentEditableDialog implements InterfaceEditableDialog{

	private JTextField jid;
	private JTextField jname;
	private JTextField jemail;
	private JTextField jphone;
	private JComboBox<Batch> jbatch;
	private JComponent[] jinputs;

	public StudentEditableDialog() {
		String id = "GC00709";
		String name = "Kaka";
		String email = "kaka@gmail.com";
		String phone = "0123456789";

		setData(id, name, email, phone);

	}

	public StudentEditableDialog(Student student) {

		setData(student.getId(), student.getName(), student.getEmail(), student.getPhone());
		jid.setEditable(false);

		jbatch.setSelectedItem(ApplicationAssistant.getInstance().getBatchByID(student.getBatchId()));
	}

	void setData(String id, String name, String email, String phone) {
		jid = new JTextField(id);
		jname = new JTextField(name);
		jemail = new JTextField(email);
		jphone = new JTextField(phone);

		Batch[] bacthes = ApplicationAssistant.getInstance().batchesToArray();
		jbatch = new JComboBox<>(bacthes);

		
		
		jinputs = new JComponent[] { new JLabel("ID"), jid, new JLabel("Name"), jname, new JLabel("Email"), jemail,
				new JLabel("Phone"), jphone, new JLabel("Batch"), jbatch};
	}

	public boolean show(String title, Component parent) {
		int result = JOptionPane.showConfirmDialog(parent, jinputs, title, JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			try {
				String id = jid.getText().trim();
				String name = jname.getText().trim();
				String email = jemail.getText();
				String phone = jphone.getText();
				if (id.equals("") || name.equals("") || email.equals("") || phone.equals("")) {
					String message = " All Fields: not empty";
					return validation(title, parent, message);

				} else if (!ValidationManager.validateEmail(email)) {
					String message = " Email "+email +" is invalid!";
					return validation(title, parent, message);

				} else if (!ValidationManager.validatePhone(phone)) {
					String message = " Phone " +phone + " is invalid! \n Phone number shouble be 10 or 11 numbers";
					return validation(title, parent, message);

				}

				String batchId = ((Batch) jbatch.getSelectedItem()).getId();
			
				switch (title) {
				case ApplicationAssistant.NEW_STUDENT:
					if (!ApplicationAssistant.getInstance()
							.addStudent(new Student(id, name, email, phone, batchId))) {
						String message = "This ID "+id + " existed or invalid! \n Format: GCxxxxx (x is digital)";
						return validation(title, parent, message);

					}
					break;
				case ApplicationAssistant.UPDATE:
					if (!ApplicationAssistant.getInstance()
							.updateStudent(new Student(id, name, email, phone, batchId))) {
						String message = "Update failed";
						return validation(title, parent, message);
					}
					break;
				}
			} catch (Exception e) {
				String message = " All Fields: not empty : text \n Name | ID : text \n";
				return validation(title, parent, message);
			}
			return true;
		} else {
			return false;
		}

	}

	private boolean validation(String title, Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, "Warning!", JOptionPane.WARNING_MESSAGE);
		return show(title, parent);
	}
}
