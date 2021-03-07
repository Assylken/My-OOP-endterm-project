// Day 07.03.2021
// Created by Assylken Taukenov & Adilkhan Zhauyr
// Time:[16:37]
// Time spend - 5 hours without break
// Good luck
// Please comment our code

import java.sql.*;
import java.util.ArrayList;

public class ControlSys {
    // Connection
    public Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/ENDKA";
        String username = "postgres";
        String password = "postgres";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection == null) {
                System.out.println("No Connection!!!");
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Admin field
    public void insertStudent(String name, String surname, int age, String gender, String phone, String group) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            String ins1 = "INSERT INTO student(st_name, st_surname, st_age, st_gender, st_phone, st_group) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(ins1);
            stat.setString(1, name);
            stat.setString(2, surname);
            stat.setInt(3, age);
            stat.setString(4, gender);
            stat.setString(5, phone);
            stat.setString(6, group);
            int rows = stat.executeUpdate();
            if (rows > 0) {
                System.out.println("Student: " + name + " " + surname + " " + age + " " + gender + " " + phone + " " + group + " inserted!");
            } else {
                System.out.println("Sorry");
            }
        }
    }

    // Admin field
    public void deleteStudent(String name, String surname, String group) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            String del1 = "DELETE FROM student where st_name = ? and st_surname = ? and st_group = ?";
            PreparedStatement stat = conn.prepareStatement(del1);
            stat.setString(1, name);
            stat.setString(2, surname);
            stat.setString(3, group);
            int rows = stat.executeUpdate();
            if (rows > 0) {
                System.out.println("Student Deleted Successfully!");
            } else {
                System.out.println("Sorry, there is no such student in DB");
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showStudent(String name, String surname) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            String select1 = "SELECT st_id, st_name, st_surname, st_age, st_gender, st_phone FROM Student where st_name = ? and st_surname = ?";
            PreparedStatement statement = conn.prepareStatement(select1);
            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No such Student in db!");
            } else {
                while (resultSet.next()) {
                    int id = resultSet.getInt("st_id");
                    String st_name = resultSet.getString("st_name");
                    String surn = resultSet.getString("st_surname");
                    int age = resultSet.getInt("st_age");
                    String gender = resultSet.getString("st_gender");
                    String phone = resultSet.getString("st_phone");
                    System.out.println(id + ":" + st_name + " " + surn + " " + age + " " + gender + " " + phone);
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void insertGrade(int quar, int teacher, String name, String surname, String group, int grade) throws SQLException {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Physics");
        subjects.add("Music");
        subjects.add("Mathematics");
        subjects.add("Informatics");
        Connection conn = connect();
        if (conn != null) {
            String selToId = "SELECT st_id FROM Student where st_name = ? and st_surname = ? and st_group = ?";
            PreparedStatement statement = conn.prepareStatement(selToId);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, group);
            ResultSet resultSet = statement.executeQuery();
            int id_st = 0;
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No such Student in db!");
            }
            while (resultSet.next()) {
                id_st = resultSet.getInt("st_id");
            }
            String ins1 = "insert into quarter(t_id, st_id, num, subject, grade) values (?, ?, ?, ?, ?)";
            PreparedStatement stat2 = conn.prepareStatement(ins1);
            stat2.setInt(1, teacher);
            teacher--;
            stat2.setInt(2, id_st);
            stat2.setInt(3, quar);
            stat2.setString(4, subjects.get(teacher));
            stat2.setInt(5, grade);
            int rows = stat2.executeUpdate();
            if (rows > 0)
                System.out.println("You successfully graded this student!");
            else
                System.out.println("Some Problems");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void changeGrade(int quar, int teacher, String name, String surname, String group, int grade) throws SQLException {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Physics");
        subjects.add("Music");
        subjects.add("Mathematics");
        subjects.add("Informatics");
        Connection conn = connect();
        if (conn != null) {
            String selToId = "SELECT st_id FROM Student where st_name = ? and st_surname = ? and st_group = ?";
            PreparedStatement statement = conn.prepareStatement(selToId);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, group);
            ResultSet resultSet = statement.executeQuery();
            int id_st = 0;
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No such Student in db!");
            } else {
                while (resultSet.next()) {
                    id_st = resultSet.getInt("st_id");
                }
                String upd1 = "update quarter set grade = ? where st_id = ? and t_id = ? and num = ? and subject = ?";
                PreparedStatement stat2 = conn.prepareStatement(upd1);
                stat2.setInt(1, grade);
                stat2.setInt(2, id_st);
                stat2.setInt(3, teacher);
                teacher--;
                stat2.setInt(4, quar);
                stat2.setString(5, subjects.get(teacher));
                int rows = stat2.executeUpdate();
                if (rows > 0)
                    System.out.println("You successfully changed the graded of this student!");
                else
                    System.out.println("Some Problems");
            }
        }
    }

    public void insertSchedule(int t_id, String group, String day, String start, String end) throws SQLException {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Physics");
        subjects.add("Music");
        subjects.add("Mathematics");
        subjects.add("Informatics");
        Connection conn = connect();
        if (conn != null) {
            String ins3 = "INSERT INTO Schedule(t_id, sc_group, day, sc_start, sc_end, subject) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(ins3);
            stat.setInt(1, t_id);
            stat.setString(2, group);
            stat.setString(3, day);
            stat.setString(4, start);
            stat.setString(5, end);
            t_id--;
            stat.setString(6, subjects.get(t_id));
            int rows = stat.executeUpdate();
            if (rows > 0) {
                System.out.println("Group: " + group + " on " + day + " " + start + " " + end + " inserted!");
            } else {
                System.out.println("Sorry");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showScheduleTeacher(int teacher) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            String Mon = "SELECT sc_group, sc_start, sc_end FROM schedule where t_id = ? and day = 'Monday'";
            String Tue = "SELECT sc_group, sc_start, sc_end FROM schedule where t_id = ? and day = 'Tuesday'";
            String Wed = "SELECT sc_group, sc_start, sc_end FROM schedule where t_id = ? and day = 'Wednesday'";
            String Thu = "SELECT sc_group, sc_start, sc_end FROM schedule where t_id = ? and day = 'Thursday'";
            String Fri = "SELECT sc_group, sc_start, sc_end FROM schedule where t_id = ? and day = 'Friday'";
            PreparedStatement stat1 = conn.prepareStatement(Mon);
            PreparedStatement stat2 = conn.prepareStatement(Tue);
            PreparedStatement stat3 = conn.prepareStatement(Wed);
            PreparedStatement stat4 = conn.prepareStatement(Thu);
            PreparedStatement stat5 = conn.prepareStatement(Fri);
            stat1.setInt(1, teacher);
            stat2.setInt(1, teacher);
            stat3.setInt(1, teacher);
            stat4.setInt(1, teacher);
            stat5.setInt(1, teacher);
            ResultSet resultSet1 = stat1.executeQuery();
            ResultSet resultSet2 = stat2.executeQuery();
            ResultSet resultSet3 = stat3.executeQuery();
            ResultSet resultSet4 = stat4.executeQuery();
            ResultSet resultSet5 = stat5.executeQuery();
            System.out.println();
            System.out.println("-----Monday-----");
            if (!resultSet1.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet1.next()) {
                    String group = resultSet1.getString("sc_group");
                    String start = resultSet1.getString("sc_start");
                    String end = resultSet1.getString("sc_end");
                    System.out.println(group + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Tuesday-----");
            if (!resultSet2.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet2.next()) {
                    String group = resultSet2.getString("sc_group");
                    String start = resultSet2.getString("sc_start");
                    String end = resultSet2.getString("sc_end");
                    System.out.println(group + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Wednesday-----");
            if (!resultSet3.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet3.next()) {
                    String group = resultSet3.getString("sc_group");
                    String start = resultSet3.getString("sc_start");
                    String end = resultSet3.getString("sc_end");
                    System.out.println(group + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Thursday-----");
            if (!resultSet4.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet4.next()) {
                    String group = resultSet4.getString("sc_group");
                    String start = resultSet4.getString("sc_start");
                    String end = resultSet4.getString("sc_end");
                    System.out.println(group + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Friday-----");
            if (!resultSet5.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet5.next()) {
                    String group = resultSet5.getString("sc_group");
                    String start = resultSet5.getString("sc_start");
                    String end = resultSet5.getString("sc_end");
                    System.out.println(group + " : " + start + " " + end);
                }
            }
        }
    }

    public void showScheduleStudent(String group) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            String Mon = "SELECT subject, sc_start, sc_end FROM schedule where sc_group = ? and day = 'Monday'";
            String Tue = "SELECT subject, sc_start, sc_end FROM schedule where sc_group = ? and day = 'Tuesday'";
            String Wed = "SELECT subject, sc_start, sc_end FROM schedule where sc_group = ? and day = 'Wednesday'";
            String Thu = "SELECT subject, sc_start, sc_end FROM schedule where sc_group = ? and day = 'Thursday'";
            String Fri = "SELECT subject, sc_start, sc_end FROM schedule where sc_group = ? and day = 'Friday'";
            PreparedStatement stat1 = conn.prepareStatement(Mon);
            PreparedStatement stat2 = conn.prepareStatement(Tue);
            PreparedStatement stat3 = conn.prepareStatement(Wed);
            PreparedStatement stat4 = conn.prepareStatement(Thu);
            PreparedStatement stat5 = conn.prepareStatement(Fri);
            stat1.setString(1, group);
            stat2.setString(1, group);
            stat3.setString(1, group);
            stat4.setString(1, group);
            stat5.setString(1, group);
            ResultSet resultSet1 = stat1.executeQuery();
            ResultSet resultSet2 = stat2.executeQuery();
            ResultSet resultSet3 = stat3.executeQuery();
            ResultSet resultSet4 = stat4.executeQuery();
            ResultSet resultSet5 = stat5.executeQuery();
            System.out.println();
            System.out.println("-----Monday-----");
            if (!resultSet1.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet1.next()) {
                    String subject = resultSet1.getString("subject");
                    String start = resultSet1.getString("sc_start");
                    String end = resultSet1.getString("sc_end");
                    System.out.println(subject + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Tuesday-----");
            if (!resultSet2.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet2.next()) {
                    String subject = resultSet2.getString("subject");
                    String start = resultSet2.getString("sc_start");
                    String end = resultSet2.getString("sc_end");
                    System.out.println(subject + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Wednesday-----");
            if (!resultSet3.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet3.next()) {
                    String subject = resultSet3.getString("subject");
                    String start = resultSet3.getString("sc_start");
                    String end = resultSet3.getString("sc_end");
                    System.out.println(subject + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Thursday-----");
            if (!resultSet4.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet4.next()) {
                    String subject = resultSet4.getString("subject");
                    String start = resultSet4.getString("sc_start");
                    String end = resultSet4.getString("sc_end");
                    System.out.println(subject + " : " + start + " " + end);
                }
            }
            System.out.println();
            System.out.println("-----Friday-----");
            if (!resultSet5.isBeforeFirst()) {
                System.out.println("No such info in db!");
            } else {
                while (resultSet5.next()) {
                    String subject = resultSet5.getString("subject");
                    String start = resultSet5.getString("sc_start");
                    String end = resultSet5.getString("sc_end");
                    System.out.println(subject + " : " + start + " " + end);
                }
            }
            System.out.println();
        }
    }

    public void showTranscript(String name, String surname, String group) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            String quar1 = "SELECT quarter.subject, quarter.grade from quarter inner join student on quarter.st_id = student.st_id where st_name = ? and st_surname = ? and st_group = ? and quarter.num = 1";
            String quar2 = "SELECT quarter.subject, quarter.grade from quarter inner join student on quarter.st_id = student.st_id where st_name = ? and st_surname = ? and st_group = ? and quarter.num = 2";
            String quar3 = "SELECT quarter.subject, quarter.grade from quarter inner join student on quarter.st_id = student.st_id where st_name = ? and st_surname = ? and st_group = ? and quarter.num = 3";
            String quar4 = "SELECT quarter.subject, quarter.grade from quarter inner join student on quarter.st_id = student.st_id where st_name = ? and st_surname = ? and st_group = ? and quarter.num = 4";

            PreparedStatement stat1 = conn.prepareStatement(quar1);
            stat1.setString(1, name);
            stat1.setString(2, surname);
            stat1.setString(3, group);
            ResultSet resultSet1 = stat1.executeQuery();

            PreparedStatement stat2 = conn.prepareStatement(quar2);
            stat2.setString(1, name);
            stat2.setString(2, surname);
            stat2.setString(3, group);
            ResultSet resultSet2 = stat2.executeQuery();

            PreparedStatement stat3 = conn.prepareStatement(quar3);
            stat3.setString(1, name);
            stat3.setString(2, surname);
            stat3.setString(3, group);
            ResultSet resultSet3 = stat3.executeQuery();

            PreparedStatement stat4 = conn.prepareStatement(quar4);
            stat4.setString(1, name);
            stat4.setString(2, surname);
            stat4.setString(3, group);
            ResultSet resultSet4 = stat4.executeQuery();

            System.out.println();
            System.out.println("First QUARTER: ");
            if (!resultSet1.isBeforeFirst()) {
                System.out.println("No such grades for this quarter in DB!");
            } else {
                while (resultSet1.next()) {
                    String sub = resultSet1.getString("subject");
                    int grade = resultSet1.getInt("grade");
                    System.out.println("Subject: " + sub + " grade: " + grade);
                }
            }

            System.out.println();
            System.out.println("Second QUARTER: ");
            if (!resultSet2.isBeforeFirst()) {
                System.out.println("No such grades for this quarter in DB!");
            } else {
                while (resultSet2.next()) {
                    String sub = resultSet2.getString("subject");
                    int grade = resultSet2.getInt("grade");
                    System.out.println("Subject: " + sub + " grade: " + grade);
                }
            }

            System.out.println();
            System.out.println("Third QUARTER: ");
            if (!resultSet3.isBeforeFirst()) {
                System.out.println("No such grades for this quarter in DB!");
            } else {
                while (resultSet3.next()) {
                    String sub = resultSet3.getString("subject");
                    int grade = resultSet3.getInt("grade");
                    System.out.println("Subject: " + sub + " grade: " + grade);
                }
            }

            System.out.println();
            System.out.println("Forth QUARTER: ");
            if (!resultSet4.isBeforeFirst()) {
                System.out.println("No such grades for this quarter in DB!");
            } else {
                while (resultSet4.next()) {
                    String sub = resultSet4.getString("subject");
                    int grade = resultSet4.getInt("grade");
                    System.out.println("Subject: " + sub + " grade: " + grade);
                }
            }
        }
    }
}