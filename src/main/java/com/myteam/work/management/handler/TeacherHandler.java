package com.myteam.work.management.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.myteam.work.management.data.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TeacherHandler {

    private Connection connection;

    public TeacherHandler() {
        this.connection = SQLHandler.getConnection();
    }
/*
    public List<User> loadTeacher(String s) {
        try {
            List<User> result = new LinkedList<>();

            var prepareStatement = SQLHandler.getConnection().prepareStatement("""
					SELECT
                        u.id AS teacher_id,
                        u.urName AS teacher_name,
                        (u.auth).authName AS username,
                        (u.auth).authPass AS password,
                        u.birth,
                        u.placeOfBirth,
                        u.sex,
                        sb.subjectName AS subject,
                        tc.className AS teach_class
                    FROM Users u
                    JOIN TeacherTeachClass ttc ON ttc.teacher = u.id
                    JOIN TeachClass tc ON tc.id = ttc.classes
                    JOIN SubjectClass sc ON sc.classes = tc.id
                    JOIN Subject sb ON sb.id = sc.subject
                    JOIN TeachSubject ts ON ts.teacher = u.id AND ts.subject = sb.id
                    WHERE u.ur = true AND u.id = ?;
					""");

            prepareStatement.setString(1, s);
			var teacherInformation = prepareStatement.executeQuery();

            while(teacherInformation.next())
                result.add(new User(
                    teacherInformation.getInt("teacher_id"),
                    teacherInformation.getString("username"),
                    teacherInformation.getString("password"),
                    teacherInformation.getBoolean("role"),
                    teacherInformation.getString("teacher_name"),
                    teacherInformation.getString("birth"),
                    teacherInformation.getString("placeOfBirth"),
                    teacherInformation.getBoolean("sex")));
                
            if(!result.isEmpty()) return result;
        } catch (SQLException e) {
            log.error(e.toString());
        }

        return null;
    }
*/

    public List<User> loadTeacher(String s) {
		try {
			List<User> results = new LinkedList<>();

			PreparedStatement statement;

			try {
				statement = this.connection.prepareStatement("""
							select * from users
							where id = ?
						""");
				statement.setInt(1, Integer.parseInt(s));
			} catch(NumberFormatException _) {
				statement = this.connection.prepareStatement("""
							SELECT
                        u.id AS teacher_id,
                        u.urName AS teacher_name,
                        (u.auth).authName AS username,
                        (u.auth).authPass AS password,
                        u.birth,
                        u.placeOfBirth,
                        u.sex,
                        sb.subjectName AS subject,
                        tc.className AS teach_class
                    FROM Users u
                    JOIN TeacherTeachClass ttc ON ttc.teacher = u.id
                    JOIN TeachClass tc ON tc.id = ttc.classes
                    JOIN SubjectClass sc ON sc.classes = tc.id
                    JOIN Subject sb ON sb.id = sc.subject
                    JOIN TeachSubject ts ON ts.teacher = u.id AND ts.subject = sb.id
                    WHERE u.ur = true AND u.id = ?;
						""");
				statement.setString(1, "%" + s + "%");
			}

			var teacherInformation = statement.executeQuery();

			while(teacherInformation.next()) {
				results.add(new User(
                    teacherInformation.getInt("id"),
                    teacherInformation.getString("authName"),
                    teacherInformation.getString("authPass"),
                    teacherInformation.getBoolean("role"),
                    teacherInformation.getString("teacher_name"),
                    teacherInformation.getString("birth"),
                    teacherInformation.getString("placeOfBirth"),
                    teacherInformation.getBoolean("sex")));
			}

			if(!results.isEmpty()) return results;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

    public String getTeacherName(int id) {
        try {
            List<User> result = new LinkedList<>();
            var prepareStatement = SQLHandler.getConnection().prepareStatement("""
                SELECT
                    u.id,
                    u.urName AS teacher_name
                FROM Users u
                WHERE u.ur = true;""");
            prepareStatement.setInt(1, id);
            var teacherNameInfo = prepareStatement.executeQuery();

            while(teacherNameInfo.next())
                result.add(new User(
                    teacherNameInfo.getInt("id"),
                    teacherNameInfo.getString("authName"),
                    teacherNameInfo.getString("authPass"),
                    teacherNameInfo.getBoolean("role"),
                    teacherNameInfo.getString("urName"),
                    teacherNameInfo.getString("birth"),
                    teacherNameInfo.getString("placeOfBirth"),
                    teacherNameInfo.getBoolean("sex")));
        } catch (SQLException e) {
            log.error(e.toString());
        }

        return null;
    }

}