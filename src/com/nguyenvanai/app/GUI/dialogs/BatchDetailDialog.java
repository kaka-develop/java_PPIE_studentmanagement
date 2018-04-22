package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.Batch;
import com.nguyenvanai.app.models.Student;

public class BatchDetailDialog  {

	ApplicationAssistant assistant = ApplicationAssistant.getInstance();
	private JComponent[] jinputs;

	public BatchDetailDialog(Batch batch) {

		setData(batch.getId(), batch.getName());

	}

	void setData(String id, String name) {
		JLabel jid = new JLabel("ID: " + id);
		JLabel jname = new JLabel("Name: " + name);
	
		JLabel jstudents = new JLabel("");
	
		
		StringBuilder cBuidler = new StringBuilder("<html><body>");
		for(Student c : assistant.getStudentsByBatchID(id)) {
			cBuidler.append("&#8594 ");
			cBuidler.append(c.getId() + " | " + c.getName()  + " | " + c.getEmail());
			cBuidler.append("<br>");
		}
		cBuidler.append("</body></html>");
		jstudents.setText(cBuidler.toString());
		
		

		jinputs = new JComponent[] { jid, jname,new JLabel("Students: ID | Name | Email"),jstudents };
	}

	public boolean show(String title, Component parent) {
		JOptionPane.showMessageDialog(parent, jinputs, title, JOptionPane.INFORMATION_MESSAGE);
		
		return true;
	}

}
