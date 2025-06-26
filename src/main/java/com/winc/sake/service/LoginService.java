// com.winc.sake.service.LoginService.java
package com.winc.sake.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import com.winc.sake.dao.ClientDAO;
import com.winc.sake.dto.ClientInfo;

public class LoginService {
    private static final Logger logger = Logger.getLogger(LoginService.class.getName());
    private ClientDAO clientDAO;
    
    public LoginService() {
        this.clientDAO = new ClientDAO();
    }
    
    /**
     * ログイン認証
     */
    public ClientInfo authenticate(String phoneNumber, String password) {
        if (phoneNumber == null || password == null || 
            phoneNumber.trim().isEmpty() || password.trim().isEmpty()) {
            logger.warning("ログイン入力値不正");
            return null;
        }
        
        // パスワードハッシュ化
        String hashedPassword = hashPassword(password);
        if (hashedPassword == null) {
            return null;
        }
        
        // DAO呼び出し
        return clientDAO.authenticate(phoneNumber, hashedPassword);
    }
    
    /**
     * パスワードハッシュ化（SHA-256）
     */
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            logger.severe("ハッシュ化エラー: " + e.getMessage());
            return null;
        }
    }
}