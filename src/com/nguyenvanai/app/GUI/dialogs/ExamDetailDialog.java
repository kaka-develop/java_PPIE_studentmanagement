package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.Exam;
import com.nguyenvanai.app.models.Student;

public class ExamDetailDialog {

	ApplicationAssistant assistant = ApplicationAssistant.getInstance();
	private JComponent[] jinputs;

	public ExamDetailDialog(Exam exam) {

		setData(exam.getId(), exam.getName(),exam.getContent(),exam.getCourseId());

	}

	void setData(String id, String name,String content,String courseId) {
		JLabel jid = new JLabel("ID: " + id);
		JLabel jname = new JLabel("Name: " + name);
		JTextArea jcontent = new JTextArea(content);
		JLabel jcourse = new JLabel("Course: " + assistant.getCourseByID(courseId).toString());
		JLabel jstudents = new JLabel("");
		
		jcontent.setEditable(false);
		
		StringBuilder cBuidler = new StringBuilder("<html><body>");
		for(Student c : assistant.getStudentsByExamID(id)) {
			cBuidler.append("&#8594 ");
			cBuidler.append(c.getId() + " | " + c.getName()  + " | " + assistant.getMarkByExamStudentID(c.getId(), id).getName());
			cBuidler.append("<br>");
		}
		cBuidler.append("</body></html>");
		jstudents.setText(cBuidler.toString());
		
		jinputs = new JComponent[] { jid, jname,new JLabel("Content:"),jcontent,jcourse,new JLabel("Students: ID | Name | Mark"),jstudents};
	}

	public boolean show(String title, Component parent) {
		JOptionPane.showMessageDialog(parent, jinputs, title, JOptionPane.INFORMATION_MESSAGE);
		
		return true;
	}

}
