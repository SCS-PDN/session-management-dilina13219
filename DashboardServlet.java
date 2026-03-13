import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // List of courses (Hardcoded)
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(101, "Java Programming", "Dr. Smith"));
        courses.add(new Course(102, "Web Development", "Prof. Jane"));
        courses.add(new Course(103, "Database Systems", "Dr. Wick"));

        // Store in request scope
        request.setAttribute("courseList", courses);

        // Forward to dashboard
        // Note: PDF Task 3 mentions dashboard.jsp to show dynamic data
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}