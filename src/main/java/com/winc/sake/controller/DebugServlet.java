package com.winc.sake.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Basic認証デバッグ用Servlet
 */
@WebServlet("/admin/debug")
public class DebugServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String remoteUser = request.getRemoteUser();
        boolean isAdmin = request.isUserInRole("admin");
        String authType = request.getAuthType();
        String contextPath = request.getContextPath();
        
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><meta charset='UTF-8'>");
        response.getWriter().println("<title>Basic認証デバッグ</title>");
        response.getWriter().println("<style>body{font-family:Arial;margin:40px;} .ok{color:green;} .ng{color:red;}</style>");
        response.getWriter().println("</head><body>");
        
        response.getWriter().println("<h1>🔍 Basic認証デバッグ情報</h1>");
        
        if (remoteUser != null) {
            response.getWriter().println("<h2 class='ok'>✅ Basic認証成功！</h2>");
            response.getWriter().println("<p><strong>ログインユーザー:</strong> " + remoteUser + "</p>");
            response.getWriter().println("<p><strong>認証タイプ:</strong> " + authType + "</p>");
            response.getWriter().println("<p><strong>admin権限:</strong> " + isAdmin + "</p>");
        } else {
            response.getWriter().println("<h2 class='ng'>❌ Basic認証されていません</h2>");
            response.getWriter().println("<p>この画面が表示される場合、認証設定に問題があります。</p>");
        }
        
        response.getWriter().println("<hr>");
        response.getWriter().println("<p><strong>コンテキストパス:</strong> " + contextPath + "</p>");
        response.getWriter().println("<p><strong>アクセス時刻:</strong> " + new java.util.Date() + "</p>");
        
        response.getWriter().println("<hr>");
        response.getWriter().println("<h3>テスト用リンク</h3>");
        response.getWriter().println("<ul>");
        response.getWriter().println("<li><a href='" + contextPath + "/admin/client'>取引先管理画面</a></li>");
        response.getWriter().println("<li><a href='" + contextPath + "/login'>取引先ログイン</a></li>");
        response.getWriter().println("</ul>");
        
        response.getWriter().println("</body></html>");
    }
}