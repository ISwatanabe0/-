package com.winc.sake.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.winc.sake.dto.ClientInfo;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginClient") == null) {
            // 未ログインの場合はログインページへ
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        ClientInfo client = (ClientInfo) session.getAttribute("loginClient");
        
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><meta charset='UTF-8'>");
        response.getWriter().println("<title>酒販卸売システム - ホーム</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { font-family: Arial, sans-serif; margin: 40px; }");
        response.getWriter().println(".header { background-color: #007bff; color: white; padding: 20px; border-radius: 8px; }");
        response.getWriter().println(".content { margin-top: 20px; padding: 20px; border: 1px solid #ddd; border-radius: 8px; }");
        response.getWriter().println(".logout-link { color: #007bff; text-decoration: none; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head><body>");
        
        response.getWriter().println("<div class='header'>");
        response.getWriter().println("<h1>酒販卸売システム</h1>");
        response.getWriter().println("<p>ようこそ、" + client.getClientName() + " 様</p>");
        response.getWriter().println("</div>");
        
        response.getWriter().println("<div class='content'>");
        response.getWriter().println("<h2>✅ ログイン成功！</h2>");
        response.getWriter().println("<p><strong>取引先ID:</strong> " + client.getClientId() + "</p>");
        response.getWriter().println("<p><strong>取引先名:</strong> " + client.getClientName() + "</p>");
        response.getWriter().println("<p><strong>電話番号:</strong> " + client.getPhoneNumber() + "</p>");
        
        response.getWriter().println("<h3>次の機能（今後実装予定）</h3>");
        response.getWriter().println("<ul>");
        response.getWriter().println("<li>商品一覧表示</li>");
        response.getWriter().println("<li>カート機能</li>");
        response.getWriter().println("<li>注文機能</li>");
        response.getWriter().println("<li>購入履歴</li>");
        response.getWriter().println("</ul>");
        
        response.getWriter().println("<p><a href='" + request.getContextPath() + "/logout' class='logout-link'>ログアウト</a></p>");
        response.getWriter().println("</div>");
        
        response.getWriter().println("</body></html>");
    }
}