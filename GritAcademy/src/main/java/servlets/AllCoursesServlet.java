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

@WebServlet("/courses")
public class AllCoursesServlet extends HttpServlet {
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

            String query = "SELECT * FROM kurser";

            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Courses</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>All Courses</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>YHP</th><th>Description</th></tr>");

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
                int courseId = rs.getInt("id");
                String courseName = rs.getString("namn");
                int courseYHP = rs.getInt("YHP");
                String courseDescription = rs.getString("beskrivning");

                out.println("<tr><td>" + courseId + "</td><td>" + courseName + "</td><td>" + courseYHP
                        + "</td><td>" + courseDescription + "</td></tr>");
            }

            out.println("</table>");
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
