package com.nguyenvanai.app.assistants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nguyenvanai.app.managers.BatchManager;
import com.nguyenvanai.app.managers.CourseManager;
import com.nguyenvanai.app.managers.ExamManager;
import com.nguyenvanai.app.managers.LecturerManager;
import com.nguyenvanai.app.managers.MarkManager;
import com.nguyenvanai.app.managers.StudentManager;
import com.nguyenvanai.app.managers.UserManager;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.Batch;
import com.nguyenvanai.app.models.Course;
import com.nguyenvanai.app.models.Exam;
import com.nguyenvanai.app.models.Lecturer;
import com.nguyenvanai.app.models.Mark;
import com.nguyenvanai.app.models.Student;
import com.nguyenvanai.app.models.User;

public class ApplicationAssistant {
	private static final ApplicationAssistant instance = new ApplicationAssistant();

	public static final String NEW_STUDENT = "New Student";
	public static final String NEW_BATCH = "New Batch";
	public static final String UPDATE = "Update";
	public static final String NEW_COURSE = "New Course";
	public static final String NEW_EXAM = "New Exam";
	public static final String LOGIN_TITLE = "Login";
	public static final String ADMIN_ROLE = "admin";
	public static final String STAFF_ROLE = "staff";
	public static final String STUDENT_ROLE = "student";

	public static ApplicationAssistant getInstance() {
		return instance;
	}

	public ApplicationAssistant() {
		loadDataFromFile();

		//loadDefaultData();
	}

	LecturerManager lecturerManager = LecturerManager.getInstance();
	CourseManager courseManager = CourseManager.getInstance();
	BatchManager batchManager = BatchManager.getInstance();
	StudentManager studentManager = StudentManager.getInstance();
	ExamManager examManager = ExamManager.getInstance();
	MarkManager markManager = MarkManager.getInstance();
	UserManager userManager = UserManager.getInstance();

	public static void main(String[] args) {

		ApplicationAssistant.getInstance();
	}

	
	// load data from file
		public void loadDataFromFile() {
			try {
				userManager.loadData();
				lecturerManager.loadData();
				courseManager.loadData();
				batchManager.loadData();
				studentManager.loadData();
				examManager.loadData();
				markManager.loadData();
			} catch (Exception e) {
				loadDefaultData();
			}

		}

		// load default data
		public void loadDefaultData() {
			
			userManager.add(new User("U01", "admin", "admin@gmail.com", "0123456789", "admin"));
			userManager.add(new User("U02", "staff", "staff@gmail.com", "0123456789", "staff"));
			userManager.save();
			
			lecturerManager.add(new Lecturer("L01", "Jaya", "jaya@fpt.edu.vn", "0123456789"));
			lecturerManager.add(new Lecturer("L02", "Duong", "doungnt@fpt.edu.vn", "0123456789"));
			lecturerManager.save();
			
			courseManager.add(new Course("C01","Project Design", "L01"));
			courseManager.add(new Course("C02","Professional Skills", "L01"));
			courseManager.add(new Course("C03","Java Programming", "L02"));
			courseManager.add(new Course("C04","Procedural Programming", "L02"));
			courseManager.save();
			
			batchManager.add(new Batch("B01", "TH1031"));
			batchManager.add(new Batch("B02", "TH1033"));
			batchManager.add(new Batch("B03", "TH1038"));
			batchManager.save();

			studentManager.add(new Student("GC00702", "Tom", "tom@gmail.com", "0123456789", "B01", "C01"));
			studentManager.add(new Student("GC00102", "Kaka", "kaka@gmail.com", "0123456789", "B02", "C02"));
			studentManager.add(new Student("GC00502", "Terry", "terry@gmail.com", "0123456789", "B01", "C01"));
			studentManager.add(new Student("GC00802", "Messi", "messi@gmail.com", "0123456789", "B02", "C02"));
			studentManager.add(new Student("GC00101", "Ronaldo", "ronaldo@gmail.com", "0123456789", "B01", "C01"));
			studentManager.save();
			
			examManager.add(new Exam("E01","Student Management", "Make a student management system", "C03"));
			examManager.add(new Exam("E02","Employee Management", "Make a employee management system", "C04"));
			examManager.add(new Exam("E03","Essential Skills", "Discuss essential skils in business", "C01"));
			examManager.add(new Exam("E04","Personal Skills", "Discuss your personal skils", "C01"));
			examManager.add(new Exam("E05","Essential phases", " Discuss phases in software developement", "C02"));
			examManager.save();

			markManager.add(new Mark("M01", "distinction", "E01", "GC00702"));
			markManager.add(new Mark("M02", "merit", "E02", "GC00502"));
			markManager.add(new Mark("M03", "merit", "E03", "GC00102"));
			markManager.add(new Mark("M04", "distinction", "E03", "GC00702"));
			markManager.add(new Mark("M05", "merit", "E01", "GC00502"));
			markManager.add(new Mark("M06", "distinction", "E01", "GC00102"));
			markManager.add(new Mark("M07", "merit", "E02", "GC00102"));
			markManager.add(new Mark("M08", "distinction", "E02", "GC00102"));
			markManager.add(new Mark("M04", "distinction", "E05", "GC00702"));
			markManager.add(new Mark("M05", "merit", "E04", "GC00502"));
			markManager.add(new Mark("M06", "distinction", "E05", "GC00102"));
			markManager.save();
		}
		

		
	// add a student
	public boolean addStudent(Student student) {
		if (!batchManager.isExisted(student.getBatchId())) {
			return false;
		}
		// if (!courseManager.isExisted(student.getCourseId())) {
		// return false;
		// }
		return studentManager.add(student);
	}

	// update a student
	public boolean updateStudent(Student newStudent) {
		return studentManager.update(newStudent);
	}

	// delete a student by ID
	public boolean deleteStudent(String id) {
		if(getMarksByStudentID(id).size() > 0)
			return false;
		return studentManager.delete(id);
	}

	private List<Mark> getMarksByStudentID(String id) {
		List<Mark> list = new ArrayList<>();
		for(AbstractEntity ae : markManager.all()){
			Mark m = (Mark) ae;
			if(m.getStudentId().equals(id))
				list.add(m);
		}
		return list;
	}

	// Students to Array
	public Student[] studentsToArray() {
		int size = studentManager.count();
		return studentManager.all().toArray(new Student[size]);
	}

	// Courses to Array
	public Course[] coursesToArray(Collection<Course> courses) {
		int size = courseManager.count();
		return courses.toArray(new Course[size]);
	}

	// Exams to Array
	public Exam[] examsToArray(Collection<Exam> exams) {
		int size = examManager.count();
		return exams.toArray(new Exam[size]);
	}

	// get courses by student's ID
	public Collection<Exam> getExamsByStudentID(String id) {
		Collection<Exam> exams = new ArrayList<>();
		for (AbstractEntity a : examManager.all()) {
			Exam e = (Exam) a;

			for (Mark m : getMarksByExamID(e.getId())) {
				if (m.getStudentId().equals(id)) {
					exams.add(e);
				}
			}

		}
		return exams;
	}

	private List<Mark> getMarksByExamID(String id) {
		List<Mark> marks = new ArrayList<>();
		for (AbstractEntity a : markManager.all()) {
			Mark m = (Mark) a;
			if (m.getExamId().equals(id))
				marks.add(m);
		}
		return marks;
	}

	// Batches to Array
	public Batch[] batchesToArray() {
		int size = batchManager.count();
		return batchManager.all().toArray(new Batch[size]);
	}

	// get bath by Id
	public Batch getBatchByID(String id) {
		return (Batch) batchManager.get(id);
	}

	// get course by ID
	public Course getCourseByID(String id) {
		return (Course) courseManager.get(id);
	}

	// get Lecturer by ID
	public Lecturer getLecturerByID(String id) {
		return (Lecturer) lecturerManager.get(id);
	}

	// sort students by ID
	public Student[] sortStudentByID() {
		int size = studentManager.count();
		return studentManager.sortByID().toArray(new Student[size]);
	}

	// sort students by Name
	public Student[] sortStudentByName() {
		int size = studentManager.count();
		return studentManager.sortByName().toArray(new Student[size]);
	}

	// save all data
	public void saveData() {
		lecturerManager.save();
		courseManager.save();
		batchManager.save();
		studentManager.save();
		examManager.save();
	}

	

	public Batch[] batchsToArray() {
		int size = batchManager.count();
		return batchManager.all().toArray(new Batch[size]);
	}

	public boolean deleteBatch(String id) {
		if(getStudentsByBatchID(id).size() > 0)
			return false;
		return batchManager.delete(id);
	}

	public List<Student> getStudentsByBatchID(String id) {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<>();
		for (AbstractEntity s : studentManager.all()) {
			Student stu = (Student) s;
			if (stu.getBatchId().equals(id))
				students.add(stu);
		}
		return students;
	}

	public boolean addBatch(Batch batch) {
		// TODO Auto-generated method stub
		return batchManager.add(batch);
	}

	public boolean updateBatch(Batch batch) {
		// TODO Auto-generated method stub
		return batchManager.update(batch);
	}

	public Batch[] sortBatchesByID() {
		int size = batchManager.count();
		// TODO Auto-generated method stub
		return batchManager.sortByID().toArray(new Batch[size]);
	}

	public Batch[] sortBatchesByName() {
		int size = batchManager.count();
		// TODO Auto-generated method stub
		return batchManager.sortByName().toArray(new Batch[size]);
	}

	public Course[] coursesToArray() {
		int size = courseManager.count();
		return courseManager.all().toArray(new Course[size]);
	}

	public boolean deleteCourse(String id) {
		if(getStudentsByCourseID(id).size() > 0)
			return false;
		return courseManager.delete(id);
	}


	public List<Student> getStudentsByCourseID(String id) {
		List<Student> students = new ArrayList<>();
		for (AbstractEntity s : studentManager.all()) {
			Student stu = (Student) s;
			for (String cId : stu.getCourseIds()) {
				if (cId.equals(id)) {
					students.add(stu);
					break;
				}
			}
		}
		return students;
	}

	public List<Course> getCoursesByStudentID(String id) {
		List<Course> courses = new ArrayList<>();
		Student stu = (Student) studentManager.get(id);
		System.out.println(stu.getCourseIds());
		for (String cId : stu.getCourseIds()) {
			Course c = (Course) courseManager.get(cId);
			if (c != null)
				courses.add(c);
		}
		return courses;
	}

	public Lecturer[] lecturersToArray() {
		int size = lecturerManager.count();
		return lecturerManager.all().toArray(new Lecturer[size]);
	}

	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseManager.add(course);
	}

	public boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		return courseManager.update(course);
	}

	public Course[] sortCourseByID() {
		int size = courseManager.count();
		// TODO Auto-generated method stub
		return courseManager.sortByID().toArray(new Course[size]);
	}

	public Course[] sortCourseByName() {
		int size = courseManager.count();
		// TODO Auto-generated method stub
		return courseManager.sortByName().toArray(new Course[size]);
	}

	public Exam[] examsToArray() {
		int size = examManager.count();
		return examManager.all().toArray(new Exam[size]);
	}

	public boolean deleteExam(String id) {
		if(getMarksByExamID(id).size() > 0)
			return false;
		return examManager.delete(id);
	}

	public Student getStudentByID(String studentId) {
		// TODO Auto-generated method stub
		return (Student) studentManager.get(studentId);
	}

	public Mark getMarkByID(String markId) {
		// TODO Auto-generated method stub
		return (Mark) markManager.get(markId);
	}

	public Mark getMarkByExamStudentID(String studentID, String examID) {
		for (AbstractEntity item : markManager.all()) {
			Mark m = (Mark) item;
			if (m.getExamId().equals(examID) && m.getStudentId().equals(studentID)) {
				return m;
			}
		}
		return null;
	}

	public List<Student> getStudentsByExamID(String id) {
		List<Mark> marks = getMarskByExamID(id);

		List<Student> students = new ArrayList<>();
		for (Mark m : marks) {
			Student stu = getStudentByID(m.getStudentId());
			if (stu != null)
				students.add(stu);
		}

		return students;
	}

	private List<Mark> getMarskByExamID(String id) {
		List<Mark> marks = new ArrayList<>();
		for (AbstractEntity ae : markManager.all()) {
			Mark m = (Mark) ae;
			if (m.getExamId().equals(id))
				marks.add(m);
		}
		return marks;
	}

	public boolean addExam(Exam exam) {
		// TODO Auto-generated method stub
		return examManager.add(exam);
	}

	public boolean updateExam(Exam exam) {
		// TODO Auto-generated method stub
		return examManager.update(exam);
	}

	public Exam[] sortExamsByID() {
		int size = examManager.count();
		// TODO Auto-generated method stub
		return examManager.sortByID().toArray(new Exam[size]);
	}

	public Exam[] sortExamsByName() {
		// TODO Auto-generated method stub
		int size = examManager.count();
		// TODO Auto-generated method stub
		return examManager.sortByName().toArray(new Exam[size]);
	}

	public Student[] searchStudentsByName(String text) {
		Collection<AbstractEntity> list = studentManager.searchByName(text);
		int size = list.size();
		// TODO Auto-generated method stub
		return list.toArray(new Student[size]);
	}

	public Batch[] searchBatchesByName(String text) {
		Collection<AbstractEntity> list = batchManager.searchByName(text);
		int size = list.size();
		// TODO Auto-generated method stub
		return list.toArray(new Batch[size]);
	}

	public Course[] searchCoursesByName(String text) {
		Collection<AbstractEntity> list = courseManager.searchByName(text);
		int size = list.size();
		// TODO Auto-generated method stub
		return list.toArray(new Course[size]);
	}

	public Exam[] searchExamsByName(String text) {
		Collection<AbstractEntity> list = examManager.searchByName(text);
		int size = list.size();
		// TODO Auto-generated method stub
		return list.toArray(new Exam[size]);
	}

	public User checkLogin(String email, String password) {
		User user = userManager.getUserByEmailAndPassword(email, password);
		if( user == null)
			user =  studentManager.getStudentByEmailAndPassword(email, password);
		
		return user;
	}
}
