package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.Course;
import com.nguyenvanai.app.models.Lecturer;

public class CourseEditableDialog implements InterfaceEditableDialog {

	private JTextField jid;
	private JTextField jname;
	private JComboBox<Lecturer> jlecturer;
	private JComponent[] jinputs;

	public CourseEditableDialog() {
		String id = "C01";
		String name = "Course1";
	
		setData(id, name);

	}

	public CourseEditableDialog(Course course) {

		setData(course.getId(), course.getName());
		jid.setEditable(false);

	}

	void setData(String id, String name) {
		jid = new JTextField(id);
		jname = new JTextField(name);
		
		Lecturer[] lects = ApplicationAssistant.getInstance().lecturersToArray();
		jlecturer = new JComboBox<>(lects);
		jinputs = new JComponent[] { new JLabel("ID"), jid, new JLabel("Name"), jname,new JLabel("Lecture"), jlecturer};
	}

	public boolean show(String title, Component parent) {
		int result = JOptionPane.showConfirmDialog(parent, jinputs, title, JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			try {
				String id = jid.getText().trim();
				String name = jname.getText().trim();
			
				if (id.equals("") || name.equals("")) {
					String message = " All Fields: not empty";
					return validation(title, parent, message);
				} 
				
				Lecturer lect = (Lecturer) jlecturer.getSelectedItem();
				
				switch (title) {
				case ApplicationAssistant.NEW_COURSE:
					if (!ApplicationAssistant.getInstance()
							.addCourse(new Course(id, name,lect.getId()))) {
						String message = "This ID "+id + " existed or invalid! \n Format: Cx (x is number)";
						return validation(title, parent, message);

					}else 
						System.out.println(title);
					break;
				case ApplicationAssistant.UPDATE:
					if (!ApplicationAssistant.getInstance()
							.updateCourse(new Course(id, name, lect.getId()))) {
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
