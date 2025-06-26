package com.winc.sake.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 社内画面：取引先管理
 */
@WebServlet("/admin/client")
public class AdminClientServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AdminClientServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Basic認証済みユーザー情報取得
        String adminUser = request.getRemoteUser();
        String userRole = request.isUserInRole("admin") ? "管理者" : "一般オペレーター";

 
        
        logger.info("社内画面アクセス: ユーザー=" + adminUser + ", 権限=" + userRole + 
                   ", IP=" + request.getRemoteAddr());
        
        // 取引先管理画面表示
        request.setAttribute("adminUser", adminUser);
        request.setAttribute("userRole", userRole);
        
        request.getRequestDispatcher("/WEB-INF/jsp/admin/client.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String adminUser = request.getRemoteUser();
        
        if ("register".equals(action)) {
            // 取引先登録処理
            registerClient(request, response, adminUser);
        } else if ("search".equals(action)) {
            // 取引先検索処理
            searchClient(request, response, adminUser);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無効な操作です");
        }
    }
    
    /**
     * 取引先登録処理
     */
    private void registerClient(HttpServletRequest request, HttpServletResponse response, String adminUser) 
            throws ServletException, IOException {
        
        String clientName = request.getParameter("clientName");
        String phoneNumber = request.getParameter("phoneNumber");
        String postalCode = request.getParameter("postalCode");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        
        logger.info("取引先登録試行: 操作者=" + adminUser + ", 取引先名=" + clientName + 
                   ", 電話番号=" + phoneNumber);
        
        // TODO: 取引先登録サービス実装
        // ClientRegistrationService service = new ClientRegistrationService();
        // boolean success = service.registerClient(clientName, phoneNumber, postalCode, address, password);
        
        // 暫定実装：成功メッセージ
        request.setAttribute("successMessage", "取引先を登録しました（暫定実装）");
        request.setAttribute("adminUser", adminUser);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/client.jsp").forward(request, response);
    }
    
    /**
     * 取引先検索処理
     */
    private void searchClient(HttpServletRequest request, HttpServletResponse response, String adminUser) 
            throws ServletException, IOException {
        
        String searchPhone = request.getParameter("phoneNumber");
        
        logger.info("取引先検索: 操作者=" + adminUser + ", 検索電話番号=" + searchPhone);
        
        // TODO: 取引先検索サービス実装
        // ClientSearchService service = new ClientSearchService();
        // ClientInfo result = service.searchByPhone(searchPhone);
        
        // 暫定実装：検索結果なし
        request.setAttribute("searchResult", null);
        request.setAttribute("adminUser", adminUser);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/client.jsp").forward(request, response);
    }
}