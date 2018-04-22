package com.nguyenvanai.app.GUI;

import com.nguyenvanai.app.GUI.dialogs.BatchEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.CourseEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.ExamEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.InterfaceEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.StudentEditableDialog;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.Batch;
import com.nguyenvanai.app.models.Course;
import com.nguyenvanai.app.models.Exam;
import com.nguyenvanai.app.models.Student;

public class DialogFactory {
	
	public static InterfaceEditableDialog createEditableDialog(AbstractEntity entity){
		InterfaceEditableDialog detailDialog = null;
		if(entity instanceof Student){
			detailDialog = new StudentEditableDialog((Student) entity);
		}else if (entity instanceof Batch){
			detailDialog = new BatchEditableDialog((Batch)entity);
		}else if (entity instanceof Course){
			detailDialog = new CourseEditableDialog((Course)entity);
		}else if (entity instanceof Exam){
			detailDialog = new ExamEditableDialog((Exam)entity);
		}
		return detailDialog;
	}
}
