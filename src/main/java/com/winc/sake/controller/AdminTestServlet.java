package com.winc.sake.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Basic認証テスト用Servlet
 */
@WebServlet("/admin/test")
public class AdminTestServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String remoteUser = request.getRemoteUser();
        boolean isManager = request.isUserInRole("manager");
        
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><meta charset='UTF-8'>");
        response.getWriter().println("<title>Basic認証テスト</title></head><body>");
        response.getWriter().println("<h1>🎉 Basic認証成功！</h1>");
        response.getWriter().println("<p><strong>ログインユーザー:</strong> " + remoteUser + "</p>");
        response.getWriter().println("<p><strong>manager権限:</strong> " + isManager + "</p>");
        response.getWriter().println("<p><strong>アクセス時刻:</strong> " + new java.util.Date() + "</p>");
        response.getWriter().println("<hr>");
        response.getWriter().println("<a href='../login'>取引先ログインページ</a>");
        response.getWriter().println("</body></html>");
    }
}