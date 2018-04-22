package com.nguyenvanai.app.GUI.dialogs;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.Batch;

public class BatchEditableDialog implements InterfaceEditableDialog {

	private JTextField jid;
	private JTextField jname;
	private JComponent[] jinputs;

	public BatchEditableDialog() {
		String id = "B01";
		String name = "TH0103";
	
		setData(id, name);

	}

	public BatchEditableDialog(Batch batch) {

		setData(batch.getId(), batch.getName());
		jid.setEditable(false);

	}

	void setData(String id, String name) {
		jid = new JTextField(id);
		jname = new JTextField(name);
		
		jinputs = new JComponent[] { new JLabel("ID"), jid, new JLabel("Name"), jname};
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
	

				switch (title) {
				case ApplicationAssistant.NEW_BATCH:
					if (!ApplicationAssistant.getInstance()
							.addBatch(new Batch(id, name))) {
						String message = "This ID "+id + " existed or invalid! \n Format: Bx (x is number)";
						return validation(title, parent, message);

					}else 
						System.out.println(title);
					break;
				case ApplicationAssistant.UPDATE:
					if (!ApplicationAssistant.getInstance()
							.updateBatch(new Batch(id, name))) {
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
