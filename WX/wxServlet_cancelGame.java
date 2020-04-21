package WX;

import com.pedro.repository.BridgeRepositoryImpl;

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
@WebServlet(name = "wxServletCancel", urlPatterns = "/wxCancelGame")
public class wxServlet_cancelGame extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到post请求");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //who要取消 which比赛
        String gameId = request.getParameter("gameId");
        HttpSession session = request.getSession();
        String OpenId = (String)session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        //应数据库中验证
        BridgeRepositoryImpl bridgeRepository = new BridgeRepositoryImpl();
        bridgeRepository.cancelGame(Integer.parseInt(gameId));
    }
}
