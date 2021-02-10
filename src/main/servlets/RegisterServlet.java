package servlets;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String mobile = req.getParameter("mobile");

        User user = new User(firstName, lastName, mobile);

        SessionFactory sessionFactory = new
                Configuration()
                .configure("webapp/WEB-INF/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        PrintWriter out = resp.getWriter();

        out.write("<html>");
        out.write("<body>");
        out.write("<p>" + "the user saved" + "</p>");
        out.write("</body>");
        out.write("</html>");

    }
}

