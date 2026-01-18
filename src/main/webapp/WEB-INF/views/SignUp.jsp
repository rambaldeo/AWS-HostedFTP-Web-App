<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HostedFTP - Sign Up</title>
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
        max-width: 450px;
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
    input[type="password"],
    input[type="email"] {
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
        background: #28a745;
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
        background: #1e7e34;
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
        background: #007BFF;
        color: #fff;
        text-decoration: none;
        border-radius: 6px;
        transition: background 0.3s ease;
    }

    a:hover {
        background: #0056b3;
    }
</style>
</head>
<body>
<div class="container">
    <h1>Sign Up</h1>

    <form action="${pageContext.request.contextPath}/SignUpServlet" method="post">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" value="${user.firstName}"/>

        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" value="${user.lastName}"/>

        <label for="userName">Email</label>
        <input type="email" id="userName" name="userName" value="${user.userName}"/>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" value="${user.password}"/>
        <small style="dispay:block; color: #555;">
        Password must contain at least 8 characters and include Uppercase, Lowercase and a number
        </small>
                
       <label for="verifyPassword">Enter your password again</label>
       <input type="password" id="verifyPassword" name="verifyPassword" value="${user.verifyPassword}"/>

        <input type="submit" value="Sign Up">
    </form>

    <p>Already have an account?</p>
    <a href="${pageContext.request.contextPath}/login">Login</a>
</div>
</body>
</html>
