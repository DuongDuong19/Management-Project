package com.myteam.work.management.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.myteam.work.management.data.Student;
import com.myteam.work.management.data.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentHandler {
	private Connection connection;

	public StudentHandler() {
		this.connection = SQLHandler.getConnection();
	}

	public List<Student> getStudent() {
		try {
			List<Student> results = new LinkedList<>();
			var studentInfo = this.connection.prepareStatement("""
				SELECT
					s.id AS student_id,
					s.urName AS student_name,
					s.sex AS student_sex,
					s.generation,
					sl.test1,
					sl.test2,
					sl.endTest,
					sl.score AS total_score,
					sl.normalizedScore,
					sl.rate
				FROM Student s
				JOIN StudentListTeachClass sl ON sl.student = s.id;
			""").executeQuery();

			while(studentInfo.next())
				results.add(new Student(
							studentInfo.getInt("id"),
							studentInfo.getShort("generation"),
							studentInfo.getFloat("gpa"),
							studentInfo.getString("urName"),
							studentInfo.getString("birth"),
							studentInfo.getString("placeOfBirth"),
							studentInfo.getBoolean("sex")));
			
			if(!results.isEmpty()) return results;

		} catch (SQLException e) {
			log.error(e.toString());
		}


		return null;
	}

	public Student getStudent(int id) {
		try {
			var prepareStatement = SQLHandler.getConnection().prepareStatement("""
					select * from student
					where id = ?
					""");
			prepareStatement.setInt(1, id);
			var studentInfo = prepareStatement.executeQuery();

			if(studentInfo.next()) return new Student(
						studentInfo.getInt("id"),
						studentInfo.getShort("generation"),
						studentInfo.getFloat("gpa"),
						studentInfo.getString("urname"),
						studentInfo.getString("birth"),
						studentInfo.getString("placeOfBirth"),
						studentInfo.getBoolean("sex")
					);
		} catch (SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public List<List<Object>> loadStudentListInfo(int teachclass) {
		try {
			List<List<Object>> result = new LinkedList<>();
			var prepareStatement = SQLHandler.getConnection().prepareStatement("""
				select * from studentlistteachclass
				where classes = ?
			""");
			prepareStatement.setInt(1, teachclass);
			var studentInfo = prepareStatement.executeQuery();

			while(studentInfo.next())
				result.add(new LinkedList<Object>(List.<Object>of(
								(Integer) studentInfo.getInt("student"),
								(Float) studentInfo.getFloat("test1"),
								(Float) studentInfo.getFloat("test2"),
								(Float) studentInfo.getFloat("endtest"),
								(Float) studentInfo.getFloat("score"),
								(Float) studentInfo.getFloat("normalizedScore"),
								studentInfo.getString("rate")
							)));

			if(!result.isEmpty()) return result;
		} catch (SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public void updateStudentGpa(int id) {
		try {
			var prepareStatement = this.connection.prepareStatement("""
				UPDATE Student st
				SET gpa = sub.gpa_cumulative
				FROM (
					SELECT
						sl.student,
						SUM(sl.normalizedScore * sb.credits)/SUM(sb.credits) AS gpa_cumulative
					FROM StudentListTeachClass sl
					JOIN SubjectClass sc ON sc.classes = sl.classes
					JOIN Semester se ON se.id = sc.semester
					JOIN Subject sb ON sb.id = sc.subject
					WHERE sl.student = ?
					AND (sc.subject, se.years, se.semester) IN (
							SELECT
								sc2.subject,
								se2.years,
								se2.semester
							FROM StudentListTeachClass sl2
							JOIN SubjectClass sc2 ON sc2.classes = sl2.classes
							JOIN Semester se2 ON se2.id = sc2.semester
							WHERE sl2.student = ?
							ORDER BY se2.years DESC, se2.semester DESC
					)
					GROUP BY sl.student
				) sub
				WHERE st.id = sub.student;
			""");

			prepareStatement.setInt(1, id);
			prepareStatement.setInt(2, id);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			log.error(e.toString());
		}

	}

	public List<Integer> getStudentName(int id) {
		try {
			List<Student> result = new LinkedList<>();
			var prepareStatement = SQLHandler.getConnection().prepareStatement("""
                SELECT
					st.id,
					st.urName AS student_name
				FROM Student st;
			""");
            prepareStatement.setInt(1, id);
            var studentNameInfo = prepareStatement.executeQuery();

			while(studentNameInfo.next())
				result.add(new Student(
					studentNameInfo.getInt("id"),
					studentNameInfo.getShort("generation"),
					studentNameInfo.getFloat("gpa"),
					studentNameInfo.getString("urName"),
					studentNameInfo.getString("birth"),
					studentNameInfo.getString("placeOfBirth"),
					studentNameInfo.getBoolean("sex")));

		} catch (SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public String getStudentUrName(int id) {
		try {
			List<Student> result = new LinkedList<>();
			var prepareStatement = SQLHandler.getConnection().prepareStatement("""
                select urName from student
					where id = ?
			""");
            prepareStatement.setInt(1, id);
            var studentNameInfo = prepareStatement.executeQuery();

			while(studentNameInfo.next())
				result.add(new Student(
					studentNameInfo.getInt("id"),
					studentNameInfo.getShort("generation"),
					studentNameInfo.getFloat("gpa"),
					studentNameInfo.getString("urName"),
					studentNameInfo.getString("birth"),
					studentNameInfo.getString("placeOfBirth"),
					studentNameInfo.getBoolean("sex")));

		} catch (SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public List<Student> loadStudent(String s) {
		try {
			List<Student> results = new LinkedList<>();

			PreparedStatement statement;

			try {
				statement = this.connection.prepareStatement("""
							select * from student
							where id = ?
						""");
				statement.setInt(1, Integer.parseInt(s));
			} catch(NumberFormatException _) {
				statement = this.connection.prepareStatement("""
							SELECT
								st.id,
								st.urName AS student_name,
								st.birth,
								st.placeOfBirth,
								st.sex,
								st.generation,
								st.gpa
							FROM Student st
							WHERE st.urName ILIKE ?
						""");
				statement.setString(1, "%" + s + "%");
			}

			var studentInformation = statement.executeQuery();

			while(studentInformation.next()) {
				results.add(new Student(
					studentInformation.getInt("id"),
					studentInformation.getShort("generation"),
					studentInformation.getFloat("gpa"),
					studentInformation.getString("student_name"),
					studentInformation.getString("birth"),
					studentInformation.getString("placeOfBirth"),
					studentInformation.getBoolean("sex")
				));
			}

			if(!results.isEmpty()) return results;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}


}
