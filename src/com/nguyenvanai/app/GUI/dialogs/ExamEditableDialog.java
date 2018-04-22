package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.Course;
import com.nguyenvanai.app.models.Exam;

public class ExamEditableDialog implements InterfaceEditableDialog {

	private JTextField jid;
	private JTextField jname;
	private JTextArea jcontent;
	private JComboBox<Course> jcourse;
	private JComponent[] jinputs;

	public ExamEditableDialog() {
		String id = "E01";
		String name = "Exam 01";
		String content = "Develop a student management system";
		setData(id, name,content);

	}

	public ExamEditableDialog(Exam exam) {

		setData(exam.getId(), exam.getName(),exam.getContent());
		jid.setEditable(false);

	}

	void setData(String id, String name,String content) {
		jid = new JTextField(id);
		jname = new JTextField(name);
		jcontent = new JTextArea(content);
		
		Course[] courses = ApplicationAssistant.getInstance().coursesToArray();
		jcourse = new JComboBox<>(courses);		
		jinputs = new JComponent[] { new JLabel("ID"), jid, new JLabel("Name"), jname, new JLabel("Content"),jcontent,new JLabel("Courses"),jcourse };
	}

	public boolean show(String title, Component parent) {
		int result = JOptionPane.showConfirmDialog(parent, jinputs, title, JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			try {
				String id = jid.getText().trim();
				String name = jname.getText().trim();
				String content = jcontent.getText().trim();

				if (id.equals("") || name.equals("")) {
					String message = " All Fields: not empty";
					return validation(title, parent, message);
				}
				Course course = (Course) jcourse.getSelectedItem();
				switch (title) {
				case ApplicationAssistant.NEW_EXAM:
					if (!ApplicationAssistant.getInstance().addExam(new Exam(id, name, content, course.getId()))) {
						String message = "This ID " + id + " existed or invalid! \n Format: Ex (x is number)";
						return validation(title, parent, message);

					} else
						System.out.println(title);
					break;
				case ApplicationAssistant.UPDATE:
					if (!ApplicationAssistant.getInstance().updateExam(new Exam(id, name, content, course.getId()))) {
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
