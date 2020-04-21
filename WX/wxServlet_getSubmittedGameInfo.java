package WX;

import com.pedro.entity.Game;
import com.pedro.repository.GameRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * created on 2020/4/6
 */
@WebServlet(name = "wxServletGameInfo", urlPatterns = "/wxGameInfo")
public class wxServlet_getSubmittedGameInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到post请求");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到GET请求");
        request.setCharacterEncoding("utf-8");
        /*Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String element = parameterNames.nextElement();
            System.out.println(element +": "+request.getParameter(element));
        }*/
        //获取比赛信息和发起人的openid
        HttpSession session = request.getSession();
        String OpenId = (String) session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String site = request.getParameter("site");
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        System.out.println("提交的比赛信息： ");
        System.out.println(OpenId + " " + date + " " + time + " " + site + " " + name + " " + content);

        //addGame to DB
        Game newGame = new Game(OpenId, content, date, time, site, name);
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        int gameid = gameRepository.addGame(newGame);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(Integer.toString(gameid));
    }
}
