package WX;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * created on 2020/4/6
 */
@WebServlet(name = "wxServlet", urlPatterns = "/wx")
public class wx_firstLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到post请求");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        String OpenId = Token.getOpenId(code);
        //create a session
        HttpSession session = request.getSession();
        session.setAttribute("openId",OpenId);
        System.out.println("已设置openId属性");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(session.getId());
    }
}
