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
        text-align: left;
    }

    .navbar .user-info b {
        color: #007BFF;
    }

    /* Flex container for About + Logout */
    .nav-actions {
        display: flex;
        align-items: center;
        gap: 1.5rem;
    }

    .nav-actions form {
        margin: 0;
    }

    /* Link style for both About and Logout */
    .nav-link {
        background: none;
        border: none;
        color: #007BFF;
        font-weight: bold;
        font-size: 1rem;
        text-decoration: none;
        cursor: pointer;
        transition: color 0.3s ease;
    }

    .nav-link:hover {
        color: #0056b3;
    }
</style>

<div class="navbar">
    <div class="user-info">
        <% 
            UserAccounts loginedUser = (UserAccounts) session.getAttribute("loginedUser");
            if (loginedUser != null) { 
        %>
            Hello, <b><%= loginedUser.getFirstName() %></b>
        <% } %>
    </div>

    <div class="nav-actions">
    	<a href="${pageContext.request.contextPath}/UserInfoServlet" class="nav-link">Home</a>
        <a href="${pageContext.request.contextPath}/AboutWebApp" class="nav-link">About</a>
        <!-- Add in link for contact page
         -->
         <a href="${pageContext.request.contextPath}/ContactServlet" class="nav-link">Contact</a>
<%--          <a href="${pageContext.request.contextPath}/UserProfileServlet" class="nav-link">Profile</a> --%>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit" class="nav-link">Logout</button>
        </form>
    </div>
</div>


