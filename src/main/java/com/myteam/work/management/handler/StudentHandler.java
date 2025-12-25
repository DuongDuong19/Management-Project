package com.myteam.work.management.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.myteam.work.management.data.Student;

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
}
