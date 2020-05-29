package newsManagement;

import dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//修改新闻接口 id 新闻id ；title 新闻标题 ；content 新闻内容
@WebServlet(name = "AuditNew" , urlPatterns = {"/AuditNew"})
public class AuditNew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setContentType("text/html;charset=UTF-8");
        NewsDao newsDao = new NewsDao();
        int id = Integer.parseInt(request.getParameter("id"));
        NewsInfo news = new NewsInfo(id,null,null,0);
        response.getWriter().print(newsDao.updateNews(news));
    }
}
