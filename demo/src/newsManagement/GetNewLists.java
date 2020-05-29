package newsManagement;

import dao.NewsDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//获取全部新闻列表接口

@WebServlet(name = "GetNewLists" ,urlPatterns = {"/GetNewLists"})
public class GetNewLists extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setContentType("text/html;charset=UTF-8");
        NewsDao newsDao = new NewsDao();
        // 实例化一个数据库操作对象
        String result = "";
        List<NewsInfo> list = newsDao.getListAll();
        for (int i = 0; i < list.size(); i++) {
            if( result != "") {
                result = result + ",";
            }
            result = result +
                "\""+ i +"\":"+
                "{"+
                "\"id\":"+ list.get(i).getId()+","+
                "\"title\":\""+list.get(i).getTitle()+"\","+
                "\"content\":\""+list.get(i).getContent()+"\","+
                "\"type\":"+list.get(i).getType()+
                "}";
        }
        result = "{"+result+"}";
        response.getWriter().print(result);
    }
}
