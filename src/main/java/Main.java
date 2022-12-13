import comparator.StudentComparator;
import comparator.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import io.XlsReader;
import io.XlsWriter;
import model.Statistics;
import model.Student;
import model.University;
import util.ComparatorUtil;
import util.JsonUtil;
import util.StatisticsUtil;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<University> universities =
                XlsReader.readXlsUniversities("src/main/resources/universityInfo.xlsx");
        UniversityComparator universityComparator =
                ComparatorUtil.getUniversityComparator(UniversityComparatorType.YEAR);
        universities.sort(universityComparator);
        String universitiesJson = JsonUtil.universityListToJson(universities);
        System.out.println(universitiesJson);
        List<University> universitiesFromJson = JsonUtil.jsonToUniversityList(universitiesJson);
        System.out.println(universities.size() == universitiesFromJson.size());
        universities.forEach(university -> {
            String universityJson = JsonUtil.universityToJson(university);
            System.out.println(universityJson);
            University universityFromJson = JsonUtil.jsonToUniversity(universityJson);
            System.out.println(universityFromJson);
        });

            List<Student> students =
                    XlsReader.readXlsStudents("src/main/resources/universityInfo.xlsx");
            StudentComparator studentComparator =
                    ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
            students.sort(studentComparator);
            String studentsJson = JsonUtil.studentListToJson(students);
            System.out.println(studentsJson);
            List<Student> studentsFromJson = JsonUtil.jsonToStudentList(studentsJson);
            System.out.println(students.size() == studentsFromJson.size());
            students.forEach(student -> {
                String studentJson = JsonUtil.studentToJson(student);
                System.out.println(studentJson);
                Student studentFromJson = JsonUtil.jsonToStudent(studentJson);
                System.out.println(studentFromJson);
            });

        List<Statistics> statisticsList = StatisticsUtil.createStatistics(students, universities);
        XlsWriter.writeXlsStatistics(statisticsList, "statistics.xlsx");

        }
}