package com.winc.sake.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.winc.sake.dto.ClientInfo;

/**
 * ログアウト処理
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String clientIp = request.getRemoteAddr();
        
        // セッション取得
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // ログイン情報取得（ログ出力用）
            ClientInfo client = (ClientInfo) session.getAttribute("loginClient");
            
            if (client != null) {
                logger.info("ログアウト実行: 取引先ID=" + client.getClientId() + 
                           ", 取引先名=" + client.getClientName() + ", IP=" + clientIp);
            }
            
            // セッション無効化（カート情報も削除される）
            session.invalidate();
        }
        
        logger.info("ログアウト処理完了: IP=" + clientIp);
        
        // ログアウト完了画面を表示
        request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // POSTでもGETと同じ処理
        doGet(request, response);
    }
}