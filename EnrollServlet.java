import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve courseId from URL
        String courseId = request.getParameter("courseId");
        
        HttpSession session = request.getSession(false);
        if (session != null && courseId != null) {
            List<String> enrolledCourses = (List<String>) session.getAttribute("enrolledCourses");
            
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }
            
            if (!enrolledCourses.contains(courseId)) {
                enrolledCourses.add(courseId);
            }
            
            // Add course to user's session
            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        
        // Redirect back to Dashboard
        response.sendRedirect("DashboardServlet?msg=success");
    }
}