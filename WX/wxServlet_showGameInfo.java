package WX;

import com.pedro.entity.CheckResult;
import com.pedro.entity.User;
import com.pedro.repository.GameRepositoryImpl;
import com.pedro.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * created on 2020/4/6
 */
@WebServlet(name = "wxServletShowGameInfo", urlPatterns = "/wxShowGameInfo")
public class wxServlet_showGameInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("接收到post请求");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String gameId = request.getParameter("gameId");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String OpenId = (String) session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        // 把gameId放到数据库中查询
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        // System.out.println(this.getClass().getName()+" : "+gameId + "
        // "+Integer.parseInt(gameId));
        // System.out.println(this.getClass().getName()+" : "+gameId+" "+OpenId);
        CheckResult checkResult = gameRepository.checkGame(OpenId, Integer.parseInt(gameId));
        List<User> users = checkResult.getUsers();
        boolean isSponsor = checkResult.isIshost();
        boolean isSignedUp = checkResult.isIsplayer();

        // 返回信息包括参与者们的昵称和头像，本人是否为发起者，不是的话是否已报名
        // 待处理：JSON转换
        String json = Json.toJson(Utils.listToStringForUser(users), "participants");
        // String returned =
        // "\"nickName\":\"podro7\",\"avatarUrl\":\"https://wx.qlogo.cn/mmopen/vi_32/yZAkFt4zAOGP1xyOkrlCM2ibmEXDOM7iccnabo6oVMDibkqKmv9icZLSwl24OCC8W9csZ60W3XJ9ewnictYrU8HFxQw/132\";\n"
        // +
        // "\"nickName\":\"nopodro\",\"avatarUrl\":\"https://iknow-pic.cdn.bcebos.com/71cf3bc79f3df8dc3aef7321c311728b461028b4?x-bce-process=image/resize,m_lfit,w_600,h_800,limit_1\";\n";
        // returned = Json.toJson(returned,"nickName","participants");
        // returned = Json.addToJson(returned,"isSponsor","false");
        // returned = Json.addToJson(returned,"isSignUp","false");
        // System.out.println(returned);

        json = Json.addToJson(json, "isSponsor", Boolean.toString(isSponsor));
        json = Json.addToJson(json, "isSignedUp", Boolean.toString(isSignedUp));
        System.out.println(this.getClass().getName() + " : " + json);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json);
    }
}
