package com.myteam.work.management.handler;

import java.util.List;
import java.util.LinkedList;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import com.myteam.work.management.data.Subject;

@Slf4j
public class SubjectHandler {
	private Connection connection;

	public SubjectHandler() {
		this.connection = SQLHandler.getConnection();
	}

	public List<Subject> getAllSubject() {
		try {
			List<Subject> results = new LinkedList<>();
			var subjectInformation = this.connection.prepareStatement("select * from subject").executeQuery();

			while(subjectInformation.next()) {
				results.add(new Subject(
								subjectInformation.getInt("id"),
								subjectInformation.getShort("credits"),
								subjectInformation.getBoolean("required"),
								subjectInformation.getString("subjectname")
							));
			}

			if(!results.isEmpty()) return results;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public List<Integer> getPrerequistes(int id) {
		try {
			List<Integer> result = new LinkedList<Integer>();
			var prepareStatement = this.connection.prepareStatement("""
					select require from prerequisite
					where subject = ?
					""");
			prepareStatement.setInt(1, id);
			var requiredInfo = prepareStatement.executeQuery();

			while(requiredInfo.next()) result.add(requiredInfo.getInt("require"));

			return result;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public String getName(int id) {
		var result = "";

		try {
			var prepareStatement = this.connection.prepareStatement("""
						select subjectname from subject
						where id = ?
					""");
			prepareStatement.setInt(1, id);
			var subjectName = prepareStatement.executeQuery();

			if(subjectName.next()) result += subjectName.getString("subjectname");
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return result;
	}
	
	public List<Subject> getSubject(String s) {
		try {
			List<Subject> results = new LinkedList<>();

			PreparedStatement statement;

			try {
				statement = this.connection.prepareStatement("""
							select * from subject
							where id = ?
						""");
				statement.setInt(1, Integer.parseInt(s));
			} catch(NumberFormatException _) {
				statement = this.connection.prepareStatement("""
							select * from subject
							where subjectname ilike ?
						""");
				statement.setString(1, "%" + s + "%");
			}

			var subjectInformation = statement.executeQuery();

			while(subjectInformation.next()) {
				results.add(new Subject(
								subjectInformation.getInt("id"),
								subjectInformation.getShort("credits"),
								subjectInformation.getBoolean("required"),
								subjectInformation.getString("subjectname")
							));
			}

			if(!results.isEmpty()) return results;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}
}
