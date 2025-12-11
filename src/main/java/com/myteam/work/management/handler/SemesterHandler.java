package com.myteam.work.management.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.myteam.work.management.data.Semester;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemesterHandler {
	private Connection connection;

	public SemesterHandler() {
		this.connection = SQLHandler.getConnection();
	}

	public List<Semester> getAllSemester() {
		try {
			var semesterInformation = this.connection.prepareStatement("select * from semester").executeQuery();
			List<Semester> result = new LinkedList<>();

			while(semesterInformation.next()) result.add(new Semester(
							semesterInformation.getInt("id"),
							semesterInformation.getShort("semester"),
							semesterInformation.getShort("years")
						));

			if(!result.isEmpty()) return result;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}
}
