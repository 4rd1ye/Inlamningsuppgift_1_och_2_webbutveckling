package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/all")
public class AllStudentsWithCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3306/GritAcademy";
        String user = "ardi";
        String password = "21032021";

        // Define your SQL query
        String query = "SELECT s.id, s.Fname, s.Lname, c.namn AS course_name " +
                "FROM studenter s " +
                "JOIN närvaro n ON s.id = n.studentid " +
                "JOIN kurser c ON n.courseid = c.id";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                out.println("<html>");
                out.println("<head>");
                out.println("<title>All Students with Courses</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>All Students with Courses</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Course Name</th></tr>");

                out.println("<nav>");
                out.println("<a href=\"students\">All Students</a> | ");
                out.println("<a href=\"courses\">All Courses</a> | ");
                out.println("<a href=\"all\">All Students with Courses</a> | ");
                out.println("<a href=\"addstudents\">Add Students</a> | ");
                out.println("<a href=\"addcourses\">Add Courses</a>");
                out.println("<a href=\"addstudentstocourse\">Add Students to course</a> | ");

                out.println("</nav>");
                out.println("<br><br>");

                while (rs.next()) {
                    int studentId = rs.getInt("id");
                    String fname = rs.getString("Fname");
                    String lname = rs.getString("Lname");
                    String courseName = rs.getString("course_name");

                    out.println("<tr><td>" + studentId + "</td><td>" + fname + "</td><td>" + lname
                            + "</td><td>" + courseName + "</td></tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");

            } catch (SQLException e) {
                // Log the exception for internal use
                e.printStackTrace();
                // Provide a user-friendly error message
                out.println("An error occurred while processing your request.");
            }

        } catch (ClassNotFoundException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}