package login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samplecenter.entities.User;

/**
 *
 * @author arkeine
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @PersistenceUnit
    EntityManagerFactory  emf;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
            EntityManager em = emf.createEntityManager();
            TypedQuery<User> typedQuery = em.createNamedQuery("User.findUserAndPassword", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password);
            List<User> listUser = typedQuery.getResultList();
            
            if (listUser.size() > 0 ) {
                request.getSession().setAttribute("username", listUser.get(0).getUsername());
                response.sendRedirect(request.getContextPath() + "/");  //TODO
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }  
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

}