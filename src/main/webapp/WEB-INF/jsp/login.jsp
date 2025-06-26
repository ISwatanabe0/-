<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン - 酒販卸売システム</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background-color: #f4f4f4; 
            margin: 0; 
            padding: 0;
        }
        .login-container { 
            max-width: 400px; 
            margin: 100px auto; 
            background: white; 
            padding: 40px; 
            border-radius: 8px; 
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .login-header { 
            text-align: center; 
            margin-bottom: 30px; 
        }
        .login-header h1 { 
            color: #333; 
            margin-bottom: 10px; 
        }
        .form-group { 
            margin-bottom: 20px; 
        }
        .form-group label { 
            display: block; 
            margin-bottom: 5px; 
            font-weight: bold; 
        }
        .form-group input { 
            width: 100%; 
            padding: 12px; 
            border: 1px solid #ddd; 
            border-radius: 4px; 
            font-size: 16px;
            box-sizing: border-box;
        }
        .login-button { 
            width: 100%; 
            padding: 12px; 
            background-color: #007bff; 
            color: white; 
            border: none; 
            border-radius: 4px; 
            font-size: 16px; 
            cursor: pointer;
        }
        .login-button:hover { 
            background-color: #0056b3; 
        }
        .error-message { 
            color: red; 
            text-align: center; 
            margin-top: 15px; 
            padding: 10px; 
            background-color: #ffe6e6; 
            border-radius: 4px;
        }
        .help-text { 
            text-align: center; 
            margin-top: 20px; 
            font-size: 14px; 
            color: #666;
        }
        .demo-info {
            background-color: #e7f3ff;
            padding: 15px;
            border-radius: 4px;
            margin-top: 20px;
            font-size: 14px;
        }
        .demo-info h4 {
            margin-top: 0;
            color: #0066cc;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h1>酒販卸売システム</h1>
            <p>取引先ログイン</p>
        </div>
        
        <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group">
                <label for="phoneNumber">電話番号:</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" required 
                       placeholder="例: 0312345678" maxlength="15" 
                       value="<%=request.getParameter("phoneNumber") != null ? request.getParameter("phoneNumber") : ""%>">
            </div>
            
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" id="password" name="password" required 
                       maxlength="50" placeholder="パスワードを入力">
            </div>
            
            <button type="submit" class="login-button">ログイン</button>
        </form>
        
        <!-- エラーメッセージ表示 -->
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-message"><%=errorMessage%></div>
        <% } %>
        
        <div class="help-text">
            ※ログインIDは登録されている代表電話番号です<br>
            ※ご不明な点はお問い合わせください
        </div>
        
        <!-- デモ用ログイン情報（研修用） -->
        <div class="demo-info">
            <h4>デモ用ログイン情報</h4>
            <p><strong>電話番号:</strong> 0312345678</p>
            <p><strong>パスワード:</strong> password123</p>
            <p><small>※酒のやまや新宿店</small></p>
        </div>
    </div>
    
    <script>
        // フォーム送信時の基本検証
        document.querySelector('form').addEventListener('submit', function(e) {
            const phoneNumber = document.getElementById('phoneNumber').value;
            const password = document.getElementById('password').value;
            
            // 電話番号検証（数字のみ）
            if (!phoneNumber.match(/^[0-9]+$/)) {
                alert('電話番号は数字のみで入力してください');
                e.preventDefault();
                return;
            }
            
            // パスワード長検証
            if (password.length < 4) {
                alert('パスワードは4文字以上で入力してください');
                e.preventDefault();
                return;
            }
        });
        
        // デモ情報クリックで自動入力
        document.querySelector('.demo-info').addEventListener('click', function() {
            document.getElementById('phoneNumber').value = '0312345678';
            document.getElementById('password').value = 'password123';
        });
    </script>
</body>
</html>