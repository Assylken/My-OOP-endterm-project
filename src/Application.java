// Day 07.03.2021
// Created by Assylken Taukenov & Adilkhan Zhauyr
// Time:[16:37]
// Time spend - 5 hours without break
// Good luck
// Please comment our code

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    // Pair class to contain start and end time for subject
    static class Pair {
        private String start = "", end = "";
        public Pair(String start, String end) {
            this.start = start;
            this.end = end;
        }
        public String getStart(){ return start; }
        public String getEnd(){ return end; }
        public void setStart(String start){ this.start = start; }
        public void setEnd(String end){ this.end = end; }
    }
    // Main function
    public static void main(String[] args) throws SQLException{ start(); }

    // Static class start
    public static void start() throws SQLException {
        ControlSys control = new ControlSys();
        Scanner sc = new Scanner(System.in);
        boolean cicle = true;
        // Creating time arrays for each day
        Pair time1[] = new Pair[9];
        time1[0] = new Pair("09:00", "09:50"); time1[1] = new Pair("10:00", "10:50");
        time1[2] = new Pair("11:00", "11:50"); time1[3] = new Pair("12:00", "12:50");
        time1[4] = new Pair("13:00", "13:50"); time1[5] = new Pair("14:00", "14:50");
        time1[6] = new Pair("15:00", "15:50"); time1[7] = new Pair("16:00", "16:50"); time1[8] = new Pair("17:00", "17:50");
        Pair time2[] = new Pair[9]; Pair time3[] = new Pair[9];
        Pair time4[] = new Pair[9]; Pair time5[] = new Pair[9];
        for (int i = 0; i < 9; i++) {
            time2[i] = new Pair(time1[i].getStart(), time1[i].getEnd()); time3[i] = new Pair(time1[i].getStart(), time1[i].getEnd());
            time4[i] = new Pair(time1[i].getStart(), time1[i].getEnd()); time5[i] = new Pair(time1[i].getStart(), time1[i].getEnd());
        }
        // loop for infinite work
        while (cicle) {
            // Menu
            System.out.println("1 - Admin\n2 - Student\n3 - Teacher\n4 - Exit");
            int type = sc.nextInt();
            // Admin control
            if (type == 1) {
                System.out.println("1 - Insert new student to DB\n2 - Delete student form DB\n3 - Set schedule");
                int choose = sc.nextInt();
                if (choose == 1) { // Inserting new student into DB system
                    System.out.println("Please enter your information:");
                    System.out.println("Enter name:"); String name = sc.next();
                    System.out.println("Enter surname:"); String surname = sc.next();
                    System.out.println("Enter age:"); int age = sc.nextInt();
                    System.out.println("Enter gender:"); String gender = sc.next();
                    System.out.println("Enter phone number:"); String phone = sc.next();
                    System.out.println("Enter group:"); String group = sc.next();
                    System.out.println();
                    control.insertStudent(name, surname, age, gender, phone, group);
                } else if (choose == 2) { // Deleting student from DB system
                    System.out.println("Please enter Student name and surname: ");
                    System.out.println("Enter name:"); String name = sc.next();
                    System.out.println("Enter surname:"); String surname = sc.next();
                    System.out.println("Enter group"); String group = sc.next();
                    control.deleteStudent(name, surname, group);
                } else if (choose == 3) { // Setting schedule for both student and teacher
                    System.out.println("Enter the group: "); String group = sc.next();
                    System.out.println("Choose the subject: ");
                    System.out.println("1 - Physics\n2 - Music\n3 - Mathematics\n4 - Informatics"); int sub = sc.nextInt();
                    System.out.println("Enter the Day:"); String day = sc.next();
                    System.out.println("Choose time:");
                    // For each day own time
                    if (day.equals("Monday")) {
                        for (int i = 0; i < 9; i++)
                            if (time1[i].start != "22:00")  System.out.println(i + " " + time1[i].start + " " + time1[i].end);
                        int tm = sc.nextInt();
                        control.insertSchedule(sub, group, day, time1[tm].start, time1[tm].end); time1[tm].start = "22:00";
                    }
                    if (day.equals("Tuesday")) {
                        for (int i = 0; i < 9; i++)
                            if (time2[i].start != "22:00") System.out.println(i + " " + time2[i].start + " " + time2[i].end);
                        int tm = sc.nextInt();
                        control.insertSchedule(sub, group, day, time2[tm].start, time2[tm].end); time2[tm].start = "22:00";
                    }
                    if (day.equals("Wednesday")) {
                        for (int i = 0; i < 9; i++)
                            if (time3[i].start != "22:00")  System.out.println(i + " " + time3[i].start + " " + time3[i].end);
                        int tm = sc.nextInt();
                        control.insertSchedule(sub, group, day, time3[tm].start, time3[tm].end); time3[tm].start = "22:00";
                    }
                    if (day.equals("Thursday")) {
                        for (int i = 0; i < 9; i++)
                            if (time4[i].start != "22:00")  System.out.println(i + " " + time4[i].start + " " + time4[i].end);
                        int tm = sc.nextInt();
                        control.insertSchedule(sub, group, day, time4[tm].start, time4[tm].end); time4[tm].start = "22:00";
                    }
                    if (day.equals("Friday")) {
                        for (int i = 0; i < 9; i++)
                            if (time5[i].start != "22:00")  System.out.println(i + " " + time5[i].start + " " + time5[i].end);
                        int tm = sc.nextInt();
                        control.insertSchedule(sub, group, day, time5[tm].start, time5[tm].end); time5[tm].start = "22:00";
                    }
                }
            } else if (type == 2) { // Student control
                System.out.println("1 - Show my transcript of grades by quarter\n2 - Show my schedule");
                int sec = sc.nextInt();
                if (sec == 1) { // Show transcript option
                    System.out.println("Enter your name, surname and group: ");
                    System.out.println("Enter your name: "); String name = sc.next();
                    System.out.println("Enter your surname: "); String surname = sc.next();
                    System.out.println("Enter your group: "); String group = sc.next();
                    control.showTranscript(name, surname, group);
                } else if (sec == 2) { // Show schedule of student option
                    System.out.println("Enter your group: "); String group = sc.next();
                    control.showScheduleStudent(group);
                }
            } else if (type == 3) { // Teacher control
                System.out.println("1 - Grade the student\n2 - Change grade of student\n3 - See my schedule");
                int tp = sc.nextInt();
                if (tp == 1) { // Grade the student
                    System.out.println("Who are you?");
                    System.out.println("1 - Patrick Rolson: Physics\n2 - Bob Marley: Music\n3 - Sergei Svetlakov: Mathematics\n4 - Michael Jordon: Informatics"); int teacher = sc.nextInt();
                    System.out.println("Enter name and surname and group of student that you want to grade: ");
                    String nmS = sc.next(); String srS = sc.next(); String group = sc.next();
                    System.out.println("Choose which quarter that you want to grade:");
                    System.out.println("1st quarter\n2nd quarter\n3rd quarter\n4th quarter");
                    int quar = sc.nextInt();
                    System.out.println("Please grade student (0 - 100)");
                    int gr = sc.nextInt();
                    // Quarter grading
                    if (quar == 1) {  control.insertGrade(quar, teacher, nmS, srS, group, gr);
                    } else if (quar == 2) {  control.insertGrade(quar, teacher, nmS, srS, group, gr);
                    } else if (quar == 3) {  control.insertGrade(quar, teacher, nmS, srS, group, gr);
                    } else if (quar == 4) {  control.insertGrade(quar, teacher, nmS, srS, group, gr);  }
                } else if (tp == 2) { // Change the grade of student
                    System.out.println("Who are you?");
                    System.out.println("1 - Patrick Rolson: Physics\n2 - Bob Marley: Music\n3 - Sergei Svetlakov: Mathematics\n4 - Michael Jordon: Informatics"); int teacher = sc.nextInt();
                    System.out.println("Enter name and surname and group of student that you want to change the grade: ");
                    String nmS = sc.next(); String srS = sc.next(); String group = sc.next();
                    System.out.println("Choose which quarter that you want to change the grade:");
                    System.out.println("1st quarter\n2nd quarter\n3rd quarter\n4th quarter"); int quar = sc.nextInt();
                    System.out.println("Please new grade for student (0 - 100)"); int gr = sc.nextInt();
                    // Quarter grading
                    if (quar == 1) {  control.changeGrade(quar, teacher, nmS, srS, group, gr);
                    } else if (quar == 2) { control.changeGrade(quar, teacher, nmS, srS, group, gr);
                    } else if (quar == 3) {  control.changeGrade(quar, teacher, nmS, srS, group, gr);
                    } else if (quar == 4) {  control.changeGrade(quar, teacher, nmS, srS, group, gr);  }
                } else if (tp == 3) { // See schedule of teacher
                    System.out.println("Who are you: ");
                    System.out.println("1 - Patrick Rolson: Physics\n2 - Bob Marley: Music\n3 - Sergei Svetlakov: Mathematics\n4 - Michael Jordon: Informatics");
                    int teacher = sc.nextInt();
                    control.showScheduleTeacher(teacher);
                }
            } else if (type == 4) { // Exit control, to quit from loop
                System.out.println("Goodbye!");
                cicle = false;
            }
        }
    }
}