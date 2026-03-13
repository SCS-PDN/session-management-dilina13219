import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // Hardcoded validation
        if ("student1".equals(user) && "pass1".equals(pass)) {
            // Create Session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Create Cookie
            Cookie userCookie = new Cookie("user", user);
            userCookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(userCookie);

            // Redirect to DashboardServlet
            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }
}