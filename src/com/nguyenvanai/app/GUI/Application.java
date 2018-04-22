
package com.nguyenvanai.app.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableCellRenderer;

import com.nguyenvanai.app.GUI.dialogs.BatchEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.CourseEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.ExamEditableDialog;
import com.nguyenvanai.app.GUI.dialogs.LoginDialog;
import com.nguyenvanai.app.GUI.dialogs.StudentEditableDialog;
import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.assistants.BatchAssistant;
import com.nguyenvanai.app.assistants.CourseAssistant;
import com.nguyenvanai.app.assistants.ExamAssistant;
import com.nguyenvanai.app.assistants.StudentAssistant;
import com.nguyenvanai.app.models.Course;
import com.nguyenvanai.app.models.Exam;
import com.nguyenvanai.app.models.Student;
import com.nguyenvanai.app.models.User;
import javax.swing.SwingConstants;

public class Application extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private ApplicationAssistant assistant;
	private User user;

	// private StudentTableModel tableModel_student;

	private javax.swing.JButton btn_newStudent;
	private javax.swing.JScrollPane scrollPane_student;
	private javax.swing.JTabbedPane main_TabbedPane;
	private javax.swing.JPanel tab_students;
	private javax.swing.JTable table_student;
	private JButton btn_sortByID;
	private JButton btnSortByName;

	private TableModel tableModel_student;

	private JPanel tab_batches;
	private JButton btn_newBatch;
	private JButton tbn_sortBatches_ID;
	private JButton tbl_sortBatches_Name;
	private JScrollPane scrollPane_batch;
	private javax.swing.JTable table_batches;
	private TableModel tableModel_batch;
	private JPanel tab_courses;

	private JButton btn_newCourse;
	private JButton tbn_sortCourses_ID;
	private JButton btn_sortCourses_Name;
	private JScrollPane scrollPane_Course;
	private JTable table_course;
	private TableModel tableModel_course;

	private JPanel tab_exams;
	private JButton tbl_newExam;
	private JButton tbl_sortExams_ID;
	private JButton tbl_sortExams_Name;
	private JScrollPane scrollPane_Exam;
	private JTable table_exam;
	private TableModel tableModel_exam;
	private JTextField text_searchStudents;
	private JTextField text_searchBatches;
	private JButton tbl_searchBatches;
	private JTextField text_searchCourses;
	private JButton tbl_searchCourse;
	private JTextField text_searchExams;
	private JButton tbl_searchExams;
	private JPanel tab_user;
	private JButton tbl_logout;

	public Application(User user) {
		this.user = user;
		assistant = ApplicationAssistant.getInstance();
		initComponents();

	}

	private void setTables(JTable table) {

		TableCellRenderer buttonRenderer = new JTableButtonRenderer();

		table.getColumn("ID").setCellRenderer(buttonRenderer);
		table.getColumn("ID").setPreferredWidth(100);
		table.getColumn("update").setCellRenderer(buttonRenderer);
		table.getColumn("delete").setCellRenderer(buttonRenderer);

		table.addMouseListener(new JTableButtonMouseListener(table));

	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		main_TabbedPane = new javax.swing.JTabbedPane();

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				saveData();
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(main_TabbedPane, GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(main_TabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		if (this.user.getRole().equals(ApplicationAssistant.ADMIN_ROLE)) {
			loadTabStudent();
			loadTabBatch();
			loadTabCourse();
			loadTabExam();
		}else if(this.user.getRole().equals(ApplicationAssistant.STAFF_ROLE)){
			loadTabStudent();
		}
		loadTabUser();
		
		getContentPane().setLayout(layout);

		main_TabbedPane.getAccessibleContext().setAccessibleName("");
		main_TabbedPane.getAccessibleContext().setAccessibleDescription("");

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void saveData() {
		if (JOptionPane.showConfirmDialog(main_TabbedPane, "Do you want to save data?", "Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			assistant.saveData();
		}
	}
	private void loadTabUser() {
		String id = this.user.getId();
		String name = this.user.getName();
		String email = this.user.getEmail();
		String phone = this.user.getPhone();
		String role = this.user.getRole();
		
		String tabTitle = email;
		tab_user = new JPanel();
		main_TabbedPane.addTab(tabTitle, null, tab_user, null);
		
		tbl_logout = new JButton();
		tbl_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut();
			}
		});
		tbl_logout.setText("Log Out");
		
		JLabel text_userID = new JLabel("ID: " + id);
		text_userID.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel text_userName = new JLabel("Name: " + name);
		text_userName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel text_userEmail = new JLabel("Email: " + email);
		text_userEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel text_userRole = new JLabel("Role: " + role);
		text_userRole.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel text_userPhone = new JLabel("Phone: " + phone);
		text_userPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel text_studentInfo = new JLabel("");
		text_studentInfo.setVerticalAlignment(SwingConstants.TOP);
		text_studentInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		text_studentInfo.setVisible(false);
		
		if(this.user instanceof Student){
			
			String text = "";
			StringBuilder sBuidler = new StringBuilder("<html><body>");
			sBuidler.append("<b>Courses:</b>");
			sBuidler.append("<table border='1'>"
					+ "<tr>"
					+ "<th>ID</th>"
					+ "<th>Name</th>"
					+ "<th>Lecturer</th>"
					+ "</tr>");
			for(Course c : assistant.getCoursesByStudentID(id)) {
				sBuidler.append("<tr>");
				sBuidler.append("<td>" + c.getId() + "</td>");
				sBuidler.append("<td>" + c.getName() + "</td>");
				sBuidler.append("<td>" + assistant.getLecturerByID(c.getLecturerId()).getName() + "</td>");
				sBuidler.append("</tr>");
			}
			sBuidler.append("</table> <br/>");
			
			sBuidler.append("<b>Exams:</b>");
			sBuidler.append("<table border='1'>"
					+ "<tr>"
					+ "<th>ID</th>"
					+ "<th>Name</th>"
					+ "<th>Mark</th>"
					+ "<th>Course</th>"
					+ "</tr>");
			for(Exam e : assistant.getExamsByStudentID(id)) {
				sBuidler.append("<tr>");
				sBuidler.append("<td>" + e.getId() + "</td>");
				sBuidler.append("<td>" + e.getName() + "</td>");
				sBuidler.append("<td>" + assistant.getMarkByExamStudentID(id,e.getId()).getName() + "</td>");
				sBuidler.append("<td>" + assistant.getCourseByID(e.getCourseId()).getName() + "</td>");
				sBuidler.append("</tr>");
			}
			sBuidler.append("</table>");
			sBuidler.append("</body></html>");
			text = sBuidler.toString();
			
			text_studentInfo.setVisible(true);
			text_studentInfo.setText(text);
		}
		
		GroupLayout gl_tab_user = new GroupLayout(tab_user);
		gl_tab_user.setHorizontalGroup(
			gl_tab_user.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tab_user.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_tab_user.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tab_user.createSequentialGroup()
							.addGroup(gl_tab_user.createParallelGroup(Alignment.LEADING)
								.addComponent(text_userRole, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(text_userPhone, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(text_userEmail, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(text_userID, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(text_userName, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
							.addGap(76))
						.addGroup(gl_tab_user.createSequentialGroup()
							.addComponent(tbl_logout)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(text_studentInfo, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		gl_tab_user.setVerticalGroup(
			gl_tab_user.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tab_user.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_tab_user.createParallelGroup(Alignment.LEADING)
						.addComponent(text_studentInfo, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_tab_user.createSequentialGroup()
							.addComponent(text_userID)
							.addGap(18)
							.addComponent(text_userName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(text_userEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(text_userPhone, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(text_userRole, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(tbl_logout, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
		);
		tab_user.setLayout(gl_tab_user);
	}
	private void loadTabStudent() {
		tab_students = new javax.swing.JPanel();
		scrollPane_student = new javax.swing.JScrollPane();
		table_student = new javax.swing.JTable();

		btn_newStudent = new javax.swing.JButton();

		btn_newStudent.setText("New Student");
		btn_newStudent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_newStudentActionPerformed(evt);
			}
		});

		btn_sortByID = new JButton();
		btn_sortByID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_student.setEntities(assistant.sortStudentByID());
			}
		});
		btn_sortByID.setText("Sort By ID");

		btnSortByName = new JButton();
		btnSortByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_student.setEntities(assistant.sortStudentByName());
			}
		});
		btnSortByName.setText("Sort By Name");

		JButton tbl_searchStudents = new JButton();
		tbl_searchStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = text_searchStudents.getText();
				tableModel_student.setEntities(assistant.searchStudentsByName(text));
			}
		});
		
		
		tbl_searchStudents.setText("Search");

		text_searchStudents = new JTextField();
		text_searchStudents.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		text_searchStudents.setText("ka");
		text_searchStudents.setColumns(10);

		javax.swing.GroupLayout gl_tab_students = new javax.swing.GroupLayout(tab_students);
		gl_tab_students.setHorizontalGroup(gl_tab_students
				.createParallelGroup(
						Alignment.LEADING)
				.addGroup(
						gl_tab_students.createSequentialGroup().addContainerGap().addComponent(btn_newStudent)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btn_sortByID, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSortByName, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
								.addComponent(text_searchStudents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(tbl_searchStudents)
								.addGap(23))
				.addComponent(scrollPane_student, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE));
		gl_tab_students.setVerticalGroup(gl_tab_students.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tab_students.createSequentialGroup().addContainerGap().addGroup(gl_tab_students
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_tab_students.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_newStudent, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
								.addComponent(btn_sortByID, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tab_students.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSortByName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(tbl_searchStudents, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(text_searchStudents, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane_student,
								GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)));
		tab_students.setLayout(gl_tab_students);

		main_TabbedPane.addTab("Students", tab_students);

		tableModel_student = new TableModel(new StudentAssistant());
		table_student.setModel(tableModel_student);
		setTables(table_student);

		scrollPane_student.setViewportView(table_student);
	}

	private void loadTabBatch() {
		tab_batches = new JPanel();
		main_TabbedPane.addTab("Batches", null, tab_batches, null);
		scrollPane_batch = new JScrollPane();
		table_batches = new JTable();

		btn_newBatch = new JButton();
		btn_newBatch.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_newBatchActionPerformed(evt);
			}
		});
		btn_newBatch.setText("New Batch");

		tbn_sortBatches_ID = new JButton();
		tbn_sortBatches_ID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_batch.setEntities(assistant.sortBatchesByID());
			}
		});
		tbn_sortBatches_ID.setText("Sort By ID");

		tbl_sortBatches_Name = new JButton();
		tbl_sortBatches_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_batch.setEntities(assistant.sortBatchesByName());
			}
		});
		tbl_sortBatches_Name.setText("Sort By Name");

		text_searchBatches = new JTextField();
		text_searchBatches.setText("batch");
		text_searchBatches.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		text_searchBatches.setColumns(10);

		tbl_searchBatches = new JButton();
		tbl_searchBatches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = text_searchBatches.getText();
				tableModel_batch.setEntities(assistant.searchBatchesByName(text));
			}
		});
		tbl_searchBatches.setText("Search");

		GroupLayout gl_tab_batches = new GroupLayout(tab_batches);
		gl_tab_batches
				.setHorizontalGroup(gl_tab_batches.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tab_batches.createSequentialGroup().addContainerGap().addComponent(btn_newBatch)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tbn_sortBatches_ID, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tbl_sortBatches_Name, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addGap(49)
								.addComponent(text_searchBatches, GroupLayout.PREFERRED_SIZE,
										150, GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(tbl_searchBatches, GroupLayout.PREFERRED_SIZE, 85,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_batch, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE));
		gl_tab_batches.setVerticalGroup(gl_tab_batches.createParallelGroup(Alignment.TRAILING).addGroup(gl_tab_batches
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_tab_batches.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_tab_batches.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_newBatch, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE).addComponent(
										tbn_sortBatches_ID, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(tbl_sortBatches_Name, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_tab_batches.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tab_batches.createSequentialGroup().addGap(3).addComponent(
										text_searchBatches, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addComponent(tbl_searchBatches, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane_batch, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)));

		tab_batches.setLayout(gl_tab_batches);

		scrollPane_batch.setViewportView(table_batches);
		tableModel_batch = new TableModel(new BatchAssistant());
		table_batches.setModel(tableModel_batch);
		setTables(table_batches);

		scrollPane_batch.setViewportView(table_batches);
	}

	private void loadTabCourse() {
		tab_courses = new JPanel();
		main_TabbedPane.addTab("Courses", null, tab_courses, null);
		scrollPane_Course = new JScrollPane();
		table_course = new JTable();

		btn_newCourse = new JButton();
		btn_newCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new CourseEditableDialog().show(ApplicationAssistant.NEW_COURSE, tab_courses)) {
					tableModel_course.notifyChanged();
				}
			}
		});
		btn_newCourse.setText("New Course");

		tbn_sortCourses_ID = new JButton();
		tbn_sortCourses_ID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_course.setEntities(assistant.sortCourseByID());
			}
		});
		tbn_sortCourses_ID.setText("Sort By ID");

		btn_sortCourses_Name = new JButton();
		btn_sortCourses_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_course.setEntities(assistant.sortCourseByName());
			}
		});
		btn_sortCourses_Name.setText("Sort By Name");

		text_searchCourses = new JTextField();
		text_searchCourses.setText("Course");
		text_searchCourses.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		text_searchCourses.setColumns(10);

		tbl_searchCourse = new JButton();
		tbl_searchCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = text_searchCourses.getText();
				tableModel_course.setEntities(assistant.searchCoursesByName(text));
			}
		});
		tbl_searchCourse.setText("Search");

		GroupLayout gl_tab_courses = new GroupLayout(tab_courses);
		gl_tab_courses
				.setHorizontalGroup(gl_tab_courses.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tab_courses.createSequentialGroup().addContainerGap().addComponent(btn_newCourse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tbn_sortCourses_ID, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btn_sortCourses_Name, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
								.addComponent(text_searchCourses, GroupLayout.PREFERRED_SIZE, 150,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(tbl_searchCourse, GroupLayout.PREFERRED_SIZE, 85,
										GroupLayout.PREFERRED_SIZE)
								.addGap(21))
						.addComponent(scrollPane_Course, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE));
		gl_tab_courses
				.setVerticalGroup(gl_tab_courses.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_tab_courses.createSequentialGroup().addContainerGap()
								.addGroup(
										gl_tab_courses.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_tab_courses.createParallelGroup(Alignment.BASELINE)
														.addComponent(btn_newCourse, GroupLayout.DEFAULT_SIZE,
																40, Short.MAX_VALUE)
														.addComponent(tbn_sortCourses_ID, GroupLayout.PREFERRED_SIZE,
																40, GroupLayout.PREFERRED_SIZE))
												.addComponent(btn_sortCourses_Name, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_tab_courses.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_tab_courses.createSequentialGroup().addGap(3)
																.addComponent(text_searchCourses,
																		GroupLayout.PREFERRED_SIZE, 32,
																		GroupLayout.PREFERRED_SIZE))
														.addComponent(tbl_searchCourse, GroupLayout.PREFERRED_SIZE, 40,
																GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane_Course,
										GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)));
		tab_courses.setLayout(gl_tab_courses);

		tableModel_course = new TableModel(new CourseAssistant());
		table_course.setModel(tableModel_course);
		setTables(table_course);

		scrollPane_Course.setViewportView(table_course);
	}

	private void loadTabExam() {

		tab_exams = new JPanel();
		main_TabbedPane.addTab("Exams", null, tab_exams, null);

		scrollPane_Exam = new JScrollPane();
		table_exam = new JTable();

		tbl_newExam = new JButton();
		tbl_newExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new ExamEditableDialog().show(ApplicationAssistant.NEW_EXAM, tab_exams)) {
					tableModel_exam.notifyChanged();
				}

			}
		});
		tbl_newExam.setText("New Exam");

		tbl_sortExams_ID = new JButton();
		tbl_sortExams_ID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_exam.setEntities(assistant.sortExamsByID());
			}
		});
		tbl_sortExams_ID.setText("Sort By ID");

		tbl_sortExams_Name = new JButton();
		tbl_sortExams_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_exam.setEntities(assistant.sortExamsByName());
			}
		});
		tbl_sortExams_Name.setText("Sort By Name");

		text_searchExams = new JTextField();
		text_searchExams.setText("Exam");
		text_searchExams.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		text_searchExams.setColumns(10);

		tbl_searchExams = new JButton();
		tbl_searchExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = text_searchExams.getText();
				tableModel_exam.setEntities(assistant.searchExamsByName(text));
			}
		});
		tbl_searchExams.setText("Search");

		GroupLayout gl_tab_exams = new GroupLayout(tab_exams);
		gl_tab_exams
				.setHorizontalGroup(gl_tab_exams.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tab_exams.createSequentialGroup().addContainerGap().addComponent(tbl_newExam)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tbl_sortExams_ID, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tbl_sortExams_Name, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
								.addComponent(text_searchExams, GroupLayout.PREFERRED_SIZE, 150,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(tbl_searchExams, GroupLayout.PREFERRED_SIZE, 85,
										GroupLayout.PREFERRED_SIZE)
								.addGap(22))
						.addComponent(scrollPane_Exam, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE));
		gl_tab_exams.setVerticalGroup(gl_tab_exams.createParallelGroup(Alignment.TRAILING).addGroup(gl_tab_exams
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_tab_exams.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_tab_exams.createParallelGroup(Alignment.BASELINE)
								.addComponent(tbl_newExam, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE).addComponent(
										tbl_sortExams_ID, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(tbl_sortExams_Name, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_tab_exams.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tab_exams.createSequentialGroup().addGap(3).addComponent(text_searchExams,
										GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addComponent(tbl_searchExams, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane_Exam, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)));
		tab_exams.setLayout(gl_tab_exams);

		tableModel_exam = new TableModel(new ExamAssistant());
		table_exam.setModel(tableModel_exam);
		setTables(table_exam);

		scrollPane_Exam.setViewportView(table_exam);
	}

	protected void btn_newBatchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (new BatchEditableDialog().show(ApplicationAssistant.NEW_BATCH, tab_batches)) {
			tableModel_batch.notifyChanged();
		}
	}

	private void btn_newStudentActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_newStudentActionPerformed
		if (new StudentEditableDialog().show(ApplicationAssistant.NEW_STUDENT, tab_students)) {
			tableModel_student.notifyChanged();
		}

	}// GEN-LAST:event_btn_newStudentActionPerformed

	private void logOut() {
		saveData();
		this.dispose();
		start();
	}
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				start();
			}
		});
	}
	
	public static void start() {
		User user = new LoginDialog().show(null);
		if (user != null) {
			new Application(user).setVisible(true);
		} else
			System.exit(0);
	}
}
