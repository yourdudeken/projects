
import java.util.Scanner;

class Student {
    private String name;
    private int yearOfStudy;
    private double[][] scores; // 2D array for scores

    public Student(String name, int yearOfStudy, double[][] scores) {
        this.name = name;
        this.yearOfStudy = yearOfStudy;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }


    public double[][] getScores() {
        return scores;
    }
}
class Course {
    private String[] subjects = {"Math", "Science", "English", "History", "Geography"};

    public double getTotal(double[] scores) {
        double total = 0;
        for (double score : scores) {
            total += score;
        }
        return total;
    }

    public String getGrade(double score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";

        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
  public double getAverage(double total, int numberOfSubjects) {
        return total / numberOfSubjects;
    }

    public String[] getSubjects() {
        return subjects;
    }
}

public class DemoClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Input student details
        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        System.out.println("Enter year of study:");
        int yearOfStudy = scanner.nextInt();

        double[][] scores = new double[1][5];

        // Input scores for 5 subjects
        System.out.println("Enter scores for 5 subjects:");
        for (int i = 0; i < 5; i++) {
            scores[0][i] = scanner.nextDouble();
        }

        // Create Student and Course objects
        Student student = new Student(name, yearOfStudy, scores);
        Course course = new Course();

   // Display the results
        System.out.println("\nStudent: " + student.getName());
        System.out.println("Year of Study: " + student.getYearOfStudy());

        double totalScore = 0;
        double[] subjectScores = student.getScores()[0];
        for (int i = 0; i < 5; i++) {
            double score = subjectScores[i];
            String grade = course.getGrade(score);
            System.out.println(course.getSubjects()[i] + ": Score = " + score + ", Grade = " + grade);
            totalScore += score;
        }

        double averageScore = course.getAverage(totalScore, 5);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Average Score: " + 

averageScore);
        System.out.println("Average Grade: " + course.getGrade(averageScore));

        scanner.close();
    }
}