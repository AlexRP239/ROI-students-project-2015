/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playbox;

import edu.roi.playbox.domain.User;
import edu.roi.playbox.domain.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Андрей
 */
public class Servlet extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        
        pw.println("<html>");
        pw.println("<head> <meta charset=\"UTF-8\"></head>");
        pw.println("<body>");
        pw.println("<table>");
        pw.println("<tr>");
        pw.println("<th>Имя</th>");
        pw.println("<th>Фамилия</th>");
        pw.println("<th>Skype</th>");
        pw.println("<th>Github</th>");
        pw.println("<th>О себе</th>");
        pw.println("<tr>");
        
        List userList = userDao.findAll();
        for(int i = 0 ; i< userList.size();i++){
            User user = (User) userList.get(i);
            pw.println("<tr>");
            pw.println("<td>"+user.getFirstName()+"</td>");
            pw.println("<td>"+user.getLastName()+"</td>");
            pw.println("<td>"+user.getSkypeLogin()+"</td>");
            pw.println("<td>"+user.getGithubLogin()+"</td>");
            pw.println("<td>"+user.getDescription()+"</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
