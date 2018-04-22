/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.Course;
import com.nguyenvanai.app.models.Exam;
import com.nguyenvanai.app.models.Student;


public class StudentDetailDialog  {

	ApplicationAssistant assistant = ApplicationAssistant.getInstance();
	private JComponent[] jinputs;

	public StudentDetailDialog(Student student) {

		setData(student.getId(), student.getName(), student.getEmail(), student.getPhone(),student.getBatchId());

	}

	void setData(String id, String name, String email, String phone,String batchId) {
		JLabel jid = new JLabel("ID: " + id);
		JLabel jname = new JLabel("Name: " + name);
		JLabel jemail = new JLabel("Email: " + email);
		JLabel jphone = new JLabel("Phone: " + phone);
		JLabel jbatch = new JLabel("Batch: " + assistant.getBatchByID(batchId).getName());
		JLabel jcourses = new JLabel("");
		JLabel jexams = new JLabel("");
		
		StringBuilder cBuidler = new StringBuilder("<html><body>");
		for(Course c : assistant.getCoursesByStudentID(id)) {
			cBuidler.append("&#8594 ");
			cBuidler.append(c.getId() + " | " + c.getName()  + " | " + assistant.getLecturerByID(c.getLecturerId()).getName());
			cBuidler.append("<br>");
		}
		cBuidler.append("</body></html>");
		jcourses.setText(cBuidler.toString());
		
		StringBuilder eBuilder = new StringBuilder("<html><body>");
		for(Exam e : assistant.getExamsByStudentID(id)) {
			eBuilder.append("&#8594 ");
			eBuilder.append(e.getId() + " | " + e.getName() + " | " +assistant.getMarkByExamStudentID(id,e.getId()).getName()+ " | " + assistant.getCourseByID(e.getCourseId()).getName());
			eBuilder.append("<br>");
		}
		eBuilder.append("</body></html>");
		jexams.setText(eBuilder.toString());

		jinputs = new JComponent[] { jid, jname, jemail, jphone,jbatch,new JLabel("Courses: ID | Name | Lecturer"),jcourses,new JLabel("Exams: ID | Name | Mark | Course "),jexams };
	}

	public boolean show(String title, Component parent) {
		JOptionPane.showMessageDialog(parent, jinputs, title, JOptionPane.INFORMATION_MESSAGE);
		
		return true;
	}

}
