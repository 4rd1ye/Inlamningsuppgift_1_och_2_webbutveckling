package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addstudentstocourse")
public class AddStudentToCourse extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>Add Student</h2>");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Studentets to course</title>");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println("<nav>");
        out.println("<a href=\"students\">All Students</a> | ");
        out.println("<a href=\"courses\">All Courses</a> | ");
        out.println("<a href=\"all\">All Students with Courses</a> | ");
        out.println("<a href=\"addstudents\">Add Students</a> | ");
        out.println("<a href=\"addcourses\">Add Courses</a>");
        out.println("<a href=\"addstudentstocourse\">Add Students to course</a> | ");
        out.println("</nav>");
        out.println("<br><br>");

        out.println("<body>");

        out.println("<form method=\"post\">");
        out.println("<label for=\"studentid\">Student ID:</label>");
        out.println("<input type=\"text\" id=\"studentid\" name=\"studentid\"><br>");
        out.println("<label for=\"courseid\">Course ID:</label>");
        out.println("<input type=\"text\" id=\"courseid\" name=\"courseid\"><br>");
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

            String studentid = request.getParameter("studentid");
            String courseid = request.getParameter("courseid");

            String query = "INSERT INTO närvaro (studentid, courseid) VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentid);
            pstmt.setString(2, courseid);


            int result  = pstmt.executeUpdate();

            if(result > 0) {
                out.println("<html>");
                out.println("<head>");
                out.println("<style>");
                out.println("h1 {");
                out.println("color:black;");
                out.println("background-color:linen;");
                out.println("border: 1px solid black;");
                out.println("}");
                out.println("</style>");
                out.println("<title>Student Added</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Student Added</h2>");

                out.println("<nav>");
                out.println("<a href=\"students\">All Students</a> | ");
                out.println("<a href=\"courses\">All Courses</a> | ");
                out.println("<a href=\"all\">All Students with Courses</a> | ");
                out.println("<a href=\"addstudents\">Add Students</a> | ");
                out.println("<a href=\"addcourses\">Add Courses</a>");
                out.println("<a href=\"addstudentstocourse\">Add Students to course</a> | ");

                out.println("</nav>");
                out.println("<br><br>");

                out.println("<br><a href=\"/addstudentstocourse\">Back to Add Student to course</a>");

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

