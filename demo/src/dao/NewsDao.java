package dao;

import db.Db;
import newsManagement.NewsInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class NewsDao {
//    创建一条新闻
    public int createNews(NewsInfo news) {
        int status = 0;
        PreparedStatement prestmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            String sql =
                    "INSERT INTO `newsmanagement`.`nm_news`(`title`, `content`, `type`) VALUES ('"
                    + news.getTitle()+ "','"+ news.getContent()+ "','1')";
            prestmt = conn.prepareStatement(sql);
            status = prestmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Db.release(prestmt, conn, rs);
        }
        return status;
    }
    //修改新闻信息
    public int updateNews(NewsInfo news) {
        int status = 0;
        PreparedStatement prestmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            String sql = "UPDATE  nm_news SET ";
            if( news.getTitle() != null )
                sql += "title= '" + news.getTitle() + "'";
            if( news.getContent() != null ){
                if( news.getTitle() != null )
                    sql += ",";
                sql += "content= '" + news.getContent()+ "'";
            }
            if( news.getType() != -1 ){
                if( news.getTitle() != null || news.getContent() != null )
                    sql += ",";
                sql += "type= '" + news.getType()+ "'";
            }
            sql += " WHERE id= " + news.getId();
            System.out.println(sql);
            prestmt = conn.prepareStatement(sql);
            status = prestmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Db.release(prestmt, conn, rs);
        }
        return status;
    }
    //删除新闻
    public int deteleNews(int id){
        int status = 0;
        PreparedStatement prestmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            String sql = "delete from nm_news where id = "+ id;
            prestmt = conn.prepareStatement(sql);
            status = prestmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Db.release(prestmt, conn, rs);
        }
        return status;
    }
    //显示新闻列表
    public List<NewsInfo> getListAll() {
        PreparedStatement prestmt = null;
        Connection conn = null;
        ResultSet rs = null;
        List<NewsInfo> list = new ArrayList<NewsInfo>();//创建list集合，用于保存news对象
        try {
            conn = Db.getConnection();
            String sql = "select * from nm_news";
            prestmt = conn.prepareStatement(sql);
            rs = prestmt.executeQuery();

            while (rs.next()) {
                NewsInfo news = new NewsInfo();//创建User对象用于保持从数据看查出来的数据
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setType(rs.getInt("Type"));
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Db.release(prestmt, conn, rs);
        }
        return null;
    }
    //显示待审核新闻列表
    public List<NewsInfo> getAuditListAll() {
        PreparedStatement prestmt = null;
        Connection conn = null;
        ResultSet rs = null;
        List<NewsInfo> list = new ArrayList<NewsInfo>();//创建list集合，用于保存news对象
        try {
            conn = Db.getConnection();
            String sql = "select * from nm_news where type = 1";
            prestmt = conn.prepareStatement(sql);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                NewsInfo news = new NewsInfo();//创建User对象用于保持从数据看查出来的数据
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setType(rs.getInt("Type"));
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Db.release(prestmt, conn, rs);
        }
        return null;
    }
}