package newsManagement;

import dao.NewsDao;
import db.Db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//创建新闻接口
@WebServlet(name = "CreateNew", urlPatterns = {"/CreateNew"})
public class CreateNew extends HttpServlet {
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
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        NewsInfo news = new NewsInfo( 0 ,title,content,0);
        response.getWriter().print(newsDao.createNews(news));
    }
}
