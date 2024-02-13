package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/addstudents")
public class AddStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>Add Student</h2>");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Students</title>");
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
        out.println("<label for=\"fname\">First Name:</label>");
        out.println("<input type=\"text\" id=\"fname\" name=\"fname\"><br>");
        out.println("<label for=\"lname\">Last Name:</label>");
        out.println("<input type=\"text\" id=\"lname\" name=\"lname\"><br>");
        out.println("<label for=\"ort\">Ort:</label>");
        out.println("<input type=\"text\" id=\"ort\" name=\"ort\"><br>");
        out.println("<label for=\"intressen\">Intressen:</label>");
        out.println("<input type=\"text\" id=\"intressen\" name=\"intressen\"><br>");
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

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String ort = request.getParameter("ort");
            String intressen = request.getParameter("intressen");

            String query = "INSERT INTO studenter (fname, lname, ort, intressen) VALUES (?, ?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, ort);
            pstmt.setString(4, intressen);

            int result  = pstmt.executeUpdate();

            if(result > 0) {
                out.println("<html>");
                out.println("<head>");
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

                out.println("<br><a href=\"/students\">Back to Add Students</a>");

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
