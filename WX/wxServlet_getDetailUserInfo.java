package WX;

import com.pedro.entity.User;
import com.pedro.repository.UserRepositoryImpl;

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
@WebServlet(name = "wxServletUserInfo", urlPatterns = "/wxUserInfo")
public class wxServlet_getDetailUserInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到post请求");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        String OpenId = (String)session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        String rawData = request.getParameter("rawData");
        String nickName = Json.getValueFromJson(rawData, "nickName");
        //String gender = Json.getValueFromJson(rawData, "gender");
        //String language = Json.getValueFromJson(rawData, "language");
        //String country = Json.getValueFromJson(rawData, "country");
        //String city = Json.getValueFromJson(rawData, "city");
        //String province = Json.getValueFromJson(rawData, "province");
        String avatarUrl = Json.getValueFromJson(rawData, "avatarUrl");
        //System.out.println("用户信息" + nickName + " " + avatarUrl);
        //应将此数据存入数据库
        User user = new User(OpenId, nickName, avatarUrl);
        System.out.println(this.getClass().getName()+" : "+user.toString());
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.addUser(user);
        //System.out.println(rawData);
    }
}
