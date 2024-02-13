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

@WebServlet("/students")
public class AllStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3306/GritAcademy";
        String user = "ardi";
        String password = "21032021";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM studenter";

            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Students</title>");
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>All Students</h2>");
            out.println("<nav>");
            out.println("<a href=\"students\">All Students</a> | ");
            out.println("<a href=\"courses\">All Courses</a> | ");
            out.println("<a href=\"all\">All Students with Courses</a> | ");
            out.println("<a href=\"addstudents\">Add Students</a> | ");
            out.println("<a href=\"addcourses\">Add Courses</a>");
            out.println("<a href=\"addstudentstocourse\">Add Students to course</a> | ");

            out.println("</nav>");
            out.println("<br><br>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>ort</th><th>intressen</th></tr>");

            while (rs.next()) {
                int studentId = rs.getInt("id");
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("Lname");
                String ort = rs.getString("ort");
                String intressen = rs.getString("intressen");

                out.println("<tr><td>" + studentId + "</td><td>" + firstName + "</td><td>" + lastName
                        + "</td><td>" + ort + "</td><td>" + intressen + "</td></tr>");
            }

            out.println("</table>");

            out.println("<h2>Search for Student</h2>");
            out.println("<form method=\"post\">");
            out.println("<label for=\"fname\">First Name:</label>");
            out.println("<input type=\"text\" id=\"fname\" name=\"fname\"><br>");
            out.println("<label for=\"lname\">Last Name:</label>");
            out.println("<input type=\"text\" id=\"lname\" name=\"lname\"><br>");
            out.println("<input type=\"submit\" value=\"Search\">");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3306/GritAcademy";
        String user = "ardi";
        String password = "21032021";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");

            String query = "SELECT s.id, s.Fname, s.Lname, c.namn AS course_name " +
                    "FROM studenter s " +
                    "JOIN närvaro n ON s.id = n.studentid " +
                    "JOIN kurser c ON n.courseid = c.id " +
                    "WHERE s.Fname = ? AND s.Lname = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);

            ResultSet rs = pstmt.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Courses</title>");
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Student Courses</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Course Name</th></tr>");

            while (rs.next()) {
                int studentId = rs.getInt("id");
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("Lname");
                String courseName = rs.getString("course_name");

                out.println("<tr><td>" + studentId + "</td><td>" + firstName + "</td><td>" + lastName
                        + "</td><td>" + courseName + "</td></tr>");
            }

            out.println("</table>");

            out.println("<br><a href=\"/students\">Back to All Students</a>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}
