<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="planning.webapp.UserAccounts" %>
<style>
    .navbar {
        background: #ffffff;
        border-bottom: 1px solid #ddd;
        box-shadow: 0 2px 6px rgba(0,0,0,0.05);
        height: 60px;
        padding: 0 2rem;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-family: Arial, sans-serif;
    }

    .navbar h3 {
        margin: 0;
        color: #007BFF;
        font-size: 1.3rem;
        letter-spacing: 0.5px;
    }

    .navbar .user-info {
        font-size: 0.95rem;
        color: #333;
        text-align: right;
    }

    .navbar .user-info b {
        color: #007BFF;
    }
</style>

<div class="navbar">
    <h3>Java Web App created by Ram Baldeo</h3>
    <div class="user-info">
 		<% 
            UserAccounts loginedUser = (UserAccounts) session.getAttribute("loginedUser");
            if (loginedUser != null) { 
        %>
            Hello, <b><%= loginedUser.getUserName() %></b>
        <% } %>
    </div>
</div>













