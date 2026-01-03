package com.myteam.work.management.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.myteam.work.management.data.TeachClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TeachClassHandler {
	private Connection connection;

	public TeachClassHandler() {
		this.connection = SQLHandler.getConnection();
	}

	public List<TeachClass> getClass(int semester, int teacher, int subject) {
		try {
			var prepareStatement = this.connection.prepareStatement("""
									SELECT DISTINCT
									tc.id AS class_id,
    								tc.className AS class_name
									FROM SubjectClass sc
									JOIN TeachClass tc ON tc.id = sc.classes
									JOIN Subject sb ON sb.id = sc.subject
									JOIN Semester se ON se.id = sc.semester
									JOIN TeacherTeachClass ttc ON ttc.classes = tc.id
									JOIN TeachSubject ts ON ts.subject = sb.id AND ts.teacher = ttc.teacher
									JOIN Users u ON u.id = ts.teacher
									WHERE sc.semester = ? AND ts.teacher  = ? AND sc.subject  = ?
								""");
			prepareStatement.setInt(1, semester);
			prepareStatement.setInt(2, teacher);
			prepareStatement.setInt(3, subject);
			var classInformation = prepareStatement.executeQuery();
			List<TeachClass> result = new LinkedList<>();

			while(classInformation.next())
				result.add(new TeachClass(
						classInformation.getInt("class_id"), 
						classInformation.getString("class_name")));

			if(!result.isEmpty())
				return result;
		} catch (SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public List<TeachClass> getClass(int semester, int subject) {
		try {
			var prepareStatement = this.connection.prepareStatement("""
									SELECT DISTINCT
									tc.id AS class_id,
    								tc.className AS class_name
									FROM SubjectClass sc
									JOIN TeachClass tc ON tc.id = sc.classes
									JOIN Subject sb ON sb.id = sc.subject
									JOIN Semester se ON se.id = sc.semester
									JOIN TeacherTeachClass ttc ON ttc.classes = tc.id
									JOIN TeachSubject ts ON ts.subject = sb.id AND ts.teacher = ttc.teacher
									JOIN Users u ON u.id = ts.teacher
									WHERE sc.semester = ? AND sc.subject  = ?
								""");
			prepareStatement.setInt(1, semester);
			prepareStatement.setInt(2, subject);
			var classInformation = prepareStatement.executeQuery();
			List<TeachClass> result = new LinkedList<>();

			while(classInformation.next())
				result.add(new TeachClass(
						classInformation.getInt("class_id"), 
						classInformation.getString("class_name")));
			if(!result.isEmpty())
				return result;
		} catch (SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public void updateClassGpa(int classes) {
		try(var prepareStatement = this.connection.prepareStatement("""
				UPDATE SubjectClass sc
				SET gpa = sub.class_gpa
				FROM (
					SELECT
						classes,
						AVG(normalizedScore) AS class_gpa
					FROM StudentListTeachClass
					WHERE classes = ?
					GROUP BY classes
				) sub
				WHERE sc.classes = sub.classes;
			""")) {

			prepareStatement.setInt(1, classes);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			log.error(e.toString());
		}
	}

     public void submit1(float test1, int student, int classes) {
        try {

            var submitInformation = this.connection.prepareStatement("""
                update studentlistteachclass
                set test1 = ?
                where student = ? and classes = ?
            """);

            submitInformation.setFloat(1, test1);
            submitInformation.setInt(2, student);
            submitInformation.setInt(3, classes);

            int result = submitInformation.executeUpdate();

            } catch (SQLException e) {
            log.error(e.toString());
        }
    }

	public void submit2(float test2, int student, int classes) {
        try {

            var submitInformation = this.connection.prepareStatement("""
                update studentlistteachclass
                set test2 = ?
                where student = ? and classes = ?
            """);

            submitInformation.setFloat(1, test2);
            submitInformation.setInt(2, student);
            submitInformation.setInt(3, classes);

            int result = submitInformation.executeUpdate();

            } catch (SQLException e) {
            log.error(e.toString());
        }
    }

	public void endtest(float endtest, int student, int classes) {
        try {

            var submitInformation = this.connection.prepareStatement("""
                update studentlistteachclass
                set endtest = ?
                where student = ? and classes = ?
            """);

            submitInformation.setFloat(1, endtest);
            submitInformation.setInt(2, student);
            submitInformation.setInt(3, classes);

            int result = submitInformation.executeUpdate();

            } catch (SQLException e) {
            log.error(e.toString());
        }
    }
}
