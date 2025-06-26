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
import com.winc.sake.service.LoginService;

//ログインコントローラー
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private LoginService loginService;
	
	@Override
	public void init() throws ServletException{
		loginService = new LoginService();
		logger.info("LoginServlet初期化完了");
	}
	
	
	//ログイン画面表示
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		logger.info("ログイン画面表示要求");
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //既にログイン済みの場合は商品ページへ
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loginClient") != null) {
            logger.info("既ログイン済み - ウェルカムページへリダイレクト");
            response.sendRedirect(request.getContextPath() + "/welcome");
            return;
        }
        
        //ログイン画面表示
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
	}
	
	//ログイン処理
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 文字エンコーディング設定
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String clientIp = request.getRemoteAddr();
        
        logger.info("ログイン試行：電話番号=" + phoneNumber + ", IP=" + clientIp);
        
        ClientInfo client = loginService.authenticate(phoneNumber, password);
        
        
        if(client != null) {
        	//ログイン成功
        	HttpSession session = request.getSession(true);
        	session.setAttribute("loginClient", client);
        	session.setMaxInactiveInterval(30 * 60);
        	
        	logger.info("ログイン成功：取引先ID=" + client.getClientId() + ",取引先名=" + client.getClientName() + ",IP=" + clientIp);
        	//成功時の遷移
        	response.sendRedirect(request.getContextPath() + "/welcome");
        }else {
        	//ログイン失敗
        	logger.warning("ログイン失敗：電話番号=" + phoneNumber + ",IP=" + clientIp);
        	request.setAttribute("errorMessage", "電話番号またはパスワードが正しくありません");
        	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
	}
}
