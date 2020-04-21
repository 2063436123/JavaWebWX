package WX;

import com.pedro.entity.Game;
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
import java.util.Map;

/**
 * created on 2020/4/6
 */
@WebServlet(name = "wxServletRelevantGame", urlPatterns = "/wxRelevantGame")
public class wxServlet_getRelevantGame extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到post请求");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        String OpenId = (String) session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        //数据库查询与OpenId相关的比赛信息
        System.out.println(this.getClass().getName() + " : " + "openid=" + OpenId);
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        List<Game> games = gameRepository.findGames(OpenId);
        System.err.println(this.getClass().getName() + " : " + games);
        //待处理：JSON格式转换
        List<Map<String, String>> gameMaps = Utils.listToStringForGame(games);
        String json = Json.toJson(gameMaps, "games");
        System.out.println(this.getClass().getName() + " : " + json);
        response.setContentType("text/html;charset=utf-8");
        //        String str_sql = "\"gameId\":\"sdf456456safwer3443\",\"date\":\"2019-01-01\",\"time\":\"12:00\",\"site\":\"体育馆\",\"sponsor\":\"xxx\";" +
        //                "\"gameId\":\"3443lkjollllll33\",\"date\":\"2039-01-01\",\"time\":\"16:00\",\"site\":\"内定场地哦\",\"sponsor\":\"wx\";";
        //        String json = Json.toJson(str_sql,"gameId","games");
        //System.out.println(json);
        response.getWriter().write(json);
    }


}
