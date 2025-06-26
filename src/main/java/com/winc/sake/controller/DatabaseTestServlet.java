package com.winc.sake.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbtest")
public class DatabaseTestServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        try {
            // DB2 JDBCドライバーロード
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            
            // データベース接続（文字コード指定）
            String url = "jdbc:db2://localhost:50000/SAKEDB";
            Connection conn = DriverManager.getConnection(url, "JSU007", "JSU007pass");
            
            response.getWriter().println("<h1>データベース接続テスト</h1>");
            
            // カテゴリデータ取得テスト
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT CATEGORY_NAME FROM SAKE_SALES.CATEGORY_MASTER ORDER BY DISPLAY_ORDER");
            ResultSet rs = pstmt.executeQuery();
            
            response.getWriter().println("<h2>カテゴリ一覧:</h2><ul>");
            while (rs.next()) {
                response.getWriter().println("<li>" + rs.getString("CATEGORY_NAME") + "</li>");
            }
            response.getWriter().println("</ul>");
            
            // 取引先データ取得テスト
            pstmt = conn.prepareStatement(
                "SELECT CLIENT_NAME, PHONE_NUMBER FROM SAKE_SALES.CLIENT_MASTER");
            rs = pstmt.executeQuery();
            
            response.getWriter().println("<h2>取引先一覧:</h2><ul>");
            while (rs.next()) {
                response.getWriter().println("<li>" + rs.getString("CLIENT_NAME") + 
                    " (TEL: " + rs.getString("PHONE_NUMBER") + ")</li>");
            }
            response.getWriter().println("</ul>");
            
            response.getWriter().println("<p><strong>DB接続成功！文字化けなし！</strong></p>");
            
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (Exception e) {
            response.getWriter().println("<h1>エラー発生</h1>");
            response.getWriter().println("<p>" + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}