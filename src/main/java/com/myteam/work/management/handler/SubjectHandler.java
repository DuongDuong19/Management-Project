package com.myteam.work.management.handler;

import java.util.List;
import java.util.LinkedList;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import com.myteam.work.management.data.Subject;
import com.myteam.work.management.data.Pair;

@Slf4j
public class SubjectHandler {
	private Connection connection;
	private int student;
	private int classes; 

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

	public List<Integer> getPrerequisites(int id) {
		try {
			List<Integer> result = new LinkedList<Integer>();
			var prepareStatement = this.connection.prepareStatement("""
					select require from prerequisite
					where subject = ?
					""");
			prepareStatement.setInt(1, id);
			var requiredInfo = prepareStatement.executeQuery();

			while(requiredInfo.next()) result.add(requiredInfo.getInt("require"));

			if(!result.isEmpty()) return result;
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
	
	public List<Integer> getSubject(int id) {
		try {
			List<Integer> result = new LinkedList<Integer>();
			var prepareStatement = this.connection.prepareStatement("""
					select subjectname from subject
					where id = ?
					""");
			prepareStatement.setInt(1, id);
			var requiredInfo = prepareStatement.executeQuery();

			while(requiredInfo.next()) result.add(requiredInfo.getInt("subjectname"));

			if(!result.isEmpty()) return result;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
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

	public List<Subject> loadTeacherSubject(int id) {
		try {
			List<Subject> result = new LinkedList<>();
			var prepareStatement = SQLHandler.getConnection().prepareStatement("""
					SELECT sb.*
					FROM TeachSubject ts
					JOIN Subject sb ON sb.id = ts.subject
					WHERE ts.teacher = ?; 
					""");
			prepareStatement.setInt(1, id);
			var subjectInformation = prepareStatement.executeQuery();

			while(subjectInformation.next()) result.add(new Subject(
								subjectInformation.getInt("id"),
								subjectInformation.getShort("credits"),
								subjectInformation.getBoolean("required"),
								subjectInformation.getString("subjectname")
						));

			if(!result.isEmpty()) return result;
		} catch(SQLException e) {
			log.error(e.toString());
		}

		return null;
	}

	public void createSubject(short credits, boolean required, String subjectName) {
		try {
			PreparedStatement statement = this.connection.prepareStatement("insert into subject(credits, required, subjectname) values(?, ?, ?)");
			
			statement.setShort(1, credits);
			statement.setBoolean(2, required);
			statement.setString(3, subjectName);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			log.error(e.toString());
		}
	}

	public void editSubject(int id, short credits, boolean required, String subjectName) {
		try {
			PreparedStatement statement = this.connection.prepareStatement("update subject set credits = ?, required = ?, subjectname = ? where id = ?");

			statement.setInt(1, credits);
			statement.setBoolean(2, required);
			statement.setString(3, subjectName);
			statement.setInt(4, id);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error(e.toString());
		}
	}

	public void deleteSubject(int id) {
		try {
			PreparedStatement statement = this.connection.prepareStatement("delete from subject where id = ?");

			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			log.error(e.toString());
		}
	}

    public void submit1(double test1) {
        try {

            var submitInformation = this.connection.prepareStatement("""
                update studentlistteachclass
                set test1 = ?
                where student = ? and classes = ?
            """);

            submitInformation.setDouble(1, test1);
            submitInformation.setInt(2, student);
            submitInformation.setInt(3, classes);

            int result = submitInformation.executeUpdate();

            } catch (SQLException e) {
            log.error(e.toString());
        }

    }

	public void submit2(double test2) {
        try {

            var submitInformation = this.connection.prepareStatement("""
                update studentlistteachclass
                set test2 = ?
                where student = ? and classes = ?
            """);

            submitInformation.setDouble(1, test2);
            submitInformation.setInt(2, student);
            submitInformation.setInt(3, classes);

            int result = submitInformation.executeUpdate();

            } catch (SQLException e) {
            log.error(e.toString());
        }

    }

	public void endtest(double endtest) {
        try {

            var submitInformation = this.connection.prepareStatement("""
                update studentlistteachclass
                set test1 = ?
                where student = ? and classes = ?
            """);

            submitInformation.setDouble(1, endtest);
            submitInformation.setInt(2, student);
            submitInformation.setInt(3, classes);

            int result = submitInformation.executeUpdate();

            } catch (SQLException e) {
            log.error(e.toString());
        }
    }

    public int countExisted(String subjectName, short credits, boolean required) {
        try {
			var prepareStatement = SQLHandler.getConnection().prepareStatement("""
					SELECT COUNT(*) 
					FROM Subject
					WHERE subjectName = ?
					AND credits = ?
					AND required = true; 
					""");
			prepareStatement.setString(1, subjectName);
			prepareStatement.setShort(2, credits);
			prepareStatement.setBoolean(3, required);
			var countSubject = prepareStatement.executeQuery();

			if(countSubject.next())  return countSubject.getInt(1);
		} catch (SQLException e) {
			log.error(e.toString());
		}

		return -1;
    }

	public Object searchLatestSubjectId(String subjectName, short credits, boolean required) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'searchLatestSubjectId'");
	}

    public void addPrerequisite(Object createdSubjectId, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPrerequisite'");
    }

    public void removePrerequisites(int id, Integer id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePrerequisites'");
    }	
}
