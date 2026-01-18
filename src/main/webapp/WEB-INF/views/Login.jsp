<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal Website</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f7f9fc;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        background: #ffffff;
        padding: 2rem 3rem;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        text-align: center;
        max-width: 400px;
        width: 90%;
    }

    h1 {
        font-size: 2rem;
        margin-bottom: 1.5rem;
        color: #333;
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 0.8rem;
    }

    label {
        align-self: flex-start;
        font-size: 0.9rem;
        color: #333;
        font-weight: bold;
    }

    input[type="text"],
    input[type="password"] {
        padding: 0.5rem;
        border-radius: 6px;
        border: 1px solid #ccc;
        width: 100%;
        max-width: 320px;
        font-size: 1rem;
    }

    input[type="submit"] {
        margin-top: 1rem;
        padding: 0.6rem 1.2rem;
        background: #007BFF;
        color: #fff;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-size: 1rem;
        transition: background 0.3s ease;
        width: 100%;
        max-width: 150px;
    }

    input[type="submit"]:hover {
        background: #0056b3;
    }

    p {
        font-size: 0.95rem;
        margin-top: 1.5rem;
        color: #555;
    }

    a {
        display: inline-block;
        margin-top: 0.5rem;
        padding: 0.6rem 1.2rem;
        background: #28a745;
        color: #fff;
        text-decoration: none;
        border-radius: 6px;
        transition: background 0.3s ease;
    }

    a:hover {
        background: #1e7e34;
    }
</style>
</head>
<body>
<div class="container">
    <h1>Please Login</h1>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="userName">Email</label>
        <input type="text" id="userName" name="userName" value="${user.userName}">

        <label for="password">Password</label>
        <input type="password" id="password" name="password" value="${user.password}">

        <input type="submit" value="Login">
    </form>

    <p>If you do not have an account, please sign up:</p>
    <a href="${pageContext.request.contextPath}/SignUpServlet">Sign Up</a>
</div>
</body>
</html>
