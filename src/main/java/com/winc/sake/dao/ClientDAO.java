// com.winc.sake.dao.ClientDAO.java
package com.winc.sake.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.winc.sake.dto.ClientInfo;

public class ClientDAO {
    private static final Logger logger = Logger.getLogger(ClientDAO.class.getName());
    
    // データベース接続情報
    private static final String DB_URL = "jdbc:db2://localhost:50000/SAKEDB";
    private static final String DB_USER = "JSU007";
    private static final String DB_PASSWORD = "JSU007pass";
    
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (ClassNotFoundException e) {
            logger.severe("DB2ドライバの読み込みに失敗: " + e.getMessage());
        }
    }
    
    /**
     * ログイン認証
     */
    public ClientInfo authenticate(String phoneNumber, String hashedPassword) {
        String sql = """
            SELECT CLIENT_ID, CLIENT_NAME, PHONE_NUMBER
            FROM SAKE_SALES.CLIENT_MASTER 
            WHERE PHONE_NUMBER = ? AND PASSWORD = ? AND ACTIVE_FLG = '1'
        """;
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, hashedPassword);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ClientInfo client = new ClientInfo();
                    client.setClientId(rs.getInt("CLIENT_ID"));
                    client.setClientName(rs.getString("CLIENT_NAME"));
                    client.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                    
                    logger.info("ログイン成功: " + phoneNumber);
                    return client;
                }
            }
            
            logger.warning("ログイン失敗: " + phoneNumber);
            return null;
            
        } catch (SQLException e) {
            logger.severe("認証処理エラー: " + e.getMessage());
            return null;
        }
    }
}