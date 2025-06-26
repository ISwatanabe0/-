<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>取引先管理 - 酒販卸売システム</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }
        .header { background-color: #333; color: white; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        .form-section { margin-bottom: 30px; padding: 20px; border: 1px solid #ddd; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: inline-block; width: 120px; font-weight: bold; }
        .form-group input { width: 200px; padding: 5px; border: 1px solid #ddd; border-radius: 3px; }
        .button { padding: 8px 16px; margin: 5px; background-color: #007bff; color: white; border: none; border-radius: 3px; cursor: pointer; }
        .button:hover { background-color: #0056b3; }
        .success { color: green; font-weight: bold; padding: 10px; background-color: #d4edda; border-radius: 3px; }
        .error { color: red; font-weight: bold; padding: 10px; background-color: #f8d7da; border-radius: 3px; }
        .search-result { margin-top: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <%
        String adminUser = (String) request.getAttribute("adminUser");
        String userRole = (String) request.getAttribute("userRole");
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
        Object searchResult = request.getAttribute("searchResult");
    %>
    
    <div class="header">
        <h1>酒販卸売システム - 社内管理画面</h1>
        <p>ログインユーザー: <%=adminUser != null ? adminUser : ""%> (<%=userRole != null ? userRole : ""%>)</p>
    </div>
    
    <div class="container">
        <h1>取引先管理</h1>
        
        <!-- 取引先登録フォーム -->
        <div class="form-section">
            <h2>新規取引先登録</h2>
            <form action="<%=request.getContextPath()%>/admin/client" method="post">
                <input type="hidden" name="action" value="register">
                
                <div class="form-group">
                    <label for="clientName">取引先名:</label>
                    <input type="text" id="clientName" name="clientName" required maxlength="100">
                </div>
                
                <div class="form-group">
                    <label for="phoneNumber">電話番号:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" required maxlength="15" 
                           pattern="[0-9]+" placeholder="ハイフンなし数字のみ">
                </div>
                
                <div class="form-group">
                    <label for="postalCode">郵便番号:</label>
                    <input type="text" id="postalCode" name="postalCode" required maxlength="7" 
                           pattern="[0-9]{7}" placeholder="1234567">
                </div>
                
                <div class="form-group">
                    <label for="address">住所:</label>
                    <input type="text" id="address" name="address" required maxlength="200" style="width: 300px;">
                </div>
                
                <div class="form-group">
                    <label for="password">パスワード:</label>
                    <input type="password" id="password" name="password" required maxlength="50">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="button">登録</button>
                </div>
            </form>
        </div>
        
        <!-- メッセージ表示 -->
        <% if (successMessage != null && !successMessage.isEmpty()) { %>
            <div class="success"><%=successMessage%></div>
        <% } %>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error"><%=errorMessage%></div>
        <% } %>
        
        <!-- 取引先検索フォーム -->
        <div class="form-section">
            <h2>取引先検索</h2>
            <form action="<%=request.getContextPath()%>/admin/client" method="post">
                <input type="hidden" name="action" value="search">
                
                <div class="form-group">
                    <label for="searchPhone">電話番号:</label>
                    <input type="tel" id="searchPhone" name="phoneNumber" maxlength="15" 
                           value="<%=request.getParameter("phoneNumber") != null ? request.getParameter("phoneNumber") : ""%>" 
                           placeholder="完全一致検索">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="button">検索</button>
                </div>
            </form>
        </div>
        
        <!-- 検索結果 -->
        <% if (searchResult != null) { %>
            <div class="search-result">
                <h3>検索結果</h3>
                <table>
                    <tr>
                        <th>取引先ID</th>
                        <th>取引先名</th>
                        <th>電話番号</th>
                        <th>住所</th>
                        <th>最終ログイン</th>
                        <th>状態</th>
                    </tr>
                    <tr>
                        <td colspan="6">検索結果の表示機能は後で実装予定</td>
                    </tr>
                </table>
            </div>
        <% } %>
        
        <div style="margin-top: 30px; text-align: center;">
            <a href="<%=request.getContextPath()%>/admin/orders" class="button">注文履歴照会</a>
            <a href="<%=request.getContextPath()%>/" class="button" style="background-color: #6c757d;">取引先画面へ</a>
        </div>
    </div>
    
    <script>
        // 入力検証
        document.querySelector('form').addEventListener('submit', function(e) {
            const phoneNumber = document.getElementById('phoneNumber');
            const postalCode = document.getElementById('postalCode');
            
            if (phoneNumber && phoneNumber.value && !phoneNumber.value.match(/^[0-9]+$/)) {
                alert('電話番号は数字のみで入力してください');
                e.preventDefault();
                return;
            }
            
            if (postalCode && postalCode.value && !postalCode.value.match(/^[0-9]{7}$/)) {
                alert('郵便番号は7桁の数字で入力してください');
                e.preventDefault();
                return;
            }
        });
    </script>
</body>
</html>