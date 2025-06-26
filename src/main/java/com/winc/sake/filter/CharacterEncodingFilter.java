package com.winc.sake.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 文字エンコーディングフィルター
 */
@WebFilter(urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        
        // UTF-8エンコーディング設定
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 次のフィルターまたはサーブレットに処理を渡す
        chain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初期化処理（必要に応じて）
    }
    
    @Override
    public void destroy() {
        // 終了処理（必要に応じて）
    }
}