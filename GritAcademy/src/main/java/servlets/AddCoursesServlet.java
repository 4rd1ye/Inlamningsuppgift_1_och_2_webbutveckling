package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/addcourses")
public class AddCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Courses</title>");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Add Course</h2>");
        out.println("<nav>");
        out.println("<a href=\"students\">All Students</a> | ");
        out.println("<a href=\"courses\">All Courses</a> | ");
        out.println("<a href=\"all\">All Students with Courses</a> | ");
        out.println("<a href=\"addstudents\">Add Students</a> | ");
        out.println("<a href=\"addcourses\">Add Courses</a>");
        out.println("<a href=\"addstudentstocourse\">Add Students to course</a> | ");

        out.println("</nav>");
        out.println("<br><br>");

        out.println("<form method=\"post\">");
        out.println("<label for=\"namn\">Course Name:</label>");
        out.println("<input type=\"text\" id=\"namn\" name=\"namn\"><br>");
        out.println("<label for=\"yhp\">YHP:</label>");
        out.println("<input type=\"number\" id=\"yhp\" name=\"yhp\"><br>");
        out.println("<label for=\"beskrivning\">Description:</label>");
        out.println("<input type=\"text\" id=\"beskrivning\" name=\"beskrivning\"><br>");
        out.println("<input type=\"submit\" value=\"ADD\">");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");

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

            String namn = request.getParameter("namn");
            int yhp = Integer.parseInt(request.getParameter("yhp"));
            String beskrivning = request.getParameter("beskrivning");

            String query = "INSERT INTO kurser (namn, YHP, beskrivning) VALUES (?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, namn);
            pstmt.setInt(2, yhp);
            pstmt.setString(3, beskrivning);

            int result = pstmt.executeUpdate();

            if(result > 0) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Course Added</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Course Added</h2>");

                out.println("<nav>");
                out.println("<a href=\"students\">All Students</a> | ");
                out.println("<a href=\"courses\">All Courses</a> | ");
                out.println("<a href=\"all\">All Students with Courses</a> | ");
                out.println("<a href=\"addstudents\">Add Students</a> | ");
                out.println("<a href=\"addcourses\">Add Courses</a>");
                out.println("<a href=\"addstudentstocourse\">Add Students to course</a> | ");

                out.println("</nav>");
                out.println("<br><br>");

                out.println("<br><a href=\"/courses\">Back to Add Courses</a>");

                out.println("</body>");
                out.println("</html>");
            }

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}
