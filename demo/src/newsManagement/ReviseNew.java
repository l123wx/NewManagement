package newsManagement;

import dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//审核新闻接口 get id 新闻id ； type 0 已发布 1 审核中
@WebServlet(name = "ReviseNew" ,urlPatterns = {"/ReviseNew"})
public class ReviseNew extends HttpServlet {
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
        String Title = request.getParameter("title");
        String Content = request.getParameter("content");
//        response.getWriter().print(id+" "+Title+" "+Content+" ");
        NewsInfo news = new NewsInfo(id,Title,Content,-1);
        response.getWriter().print(newsDao.updateNews(news));
    }
}
