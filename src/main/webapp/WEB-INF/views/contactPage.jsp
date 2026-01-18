<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HostedFTP</title>
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
        width: 80%;
        max-width: 900px;
    }

    h1 {
        font-size: 2rem;
        margin-bottom: 1rem;
        color: #333;
        text-align: center;
    }

    h3 {
        text-align: center;
        color: #555;
    }

    .content {
        display: flex;
        justify-content: flex-start;
        align-items: flex-start;
        gap: 2rem;
        margin-top: 1.5rem;
    }

    .left {
        flex: 1;
        max-width: 50%;
    }

    .left p {
        font-size: 1rem;
        color: #555;
        margin-bottom: 1rem;
    }
    .right{
    	flex:1;
 		max-width:400px;
    }
    .right a{
    	display: block;
    	margin-bottom: 5px;
    }
    .left p {
    	font-size: 1rem;
    	color: #555;
    	margin-bottom: 1rem;	
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 0.5rem;
    }

    label {
        font-size: 0.9rem;
        color: #333;
        font-weight: bold;
    }

    input[type="text"] {
        padding: 0.5rem;
        border-radius: 6px;
        border: 1px solid #ccc;
        width: 100%;
        max-width: 350px;
    }

    input[type="submit"] {
        margin-top: 0.5rem;
        padding: 0.6rem 1.2rem;
        background: #007BFF;
        color: #fff;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: background 0.3s ease;
    }

    input[type="submit"]:hover {
        background: #0056b3;
    }

    .logout {
        text-align: center;
        margin-top: 2rem;
    }

    .logout input[type="submit"] {
        background: #dc3545;
    }

    .logout input[type="submit"]:hover {
        background: #a71d2a;
    }

</style>
</head>
<body>
<div class="container">
	<jsp:include page="_LoggedInHeader.jsp"></jsp:include>
<div>
    <h3>Contact me</h3>
    <p>If you wish to get in touch with me, you can fill out the form below with any inquiries or contact me through social media</p>

    <div class="content">
        <div class="left">
            <form action="${pageContext.request.contextPath}/sendSupportEmail" method="post">
                <input type="hidden" name="firstName" value="${user.firstName}">
                <input type="hidden" name="lastName" value="${user.lastName}">
                <input type="hidden" name="email" value="${user.username} }">
                <label for="companyName">Please enter your company name:</label>
				<input type="text" id="companyName" name="companyName" value=""/>
                <label for="country">Country</label>
                <select id="country" name="country">
                    <option value="canada">Canada</option>
                    <option value="usa">USA</option>
                </select>

                <label for="subject">Subject</label>
                <textarea id="subject" name="subject" value="subject" placeholder="Enter Your Message Subject" style="height:2rem; width:50%"></textarea>
				<label for="body">Please enter your message below</label>
				<textarea id="body" name="body" value="body" placeholder="Enter Your Message.." style="height:4rem; width:100%"></textarea>
                <input type="submit" value="Submit">
            </form>
        </div>

        <div class="right">
            <a href="https://www.linkedin.com/in/ram-baldeo-21238119a/" target="_blank">Link to my Linkedin Page</a>
            <a href="https://github.com/rambaldeo" target="_blank">Link to my Github page</a>
            <a href="https://www.hackerrank.com/profile/rambaldeo2411" target="_blank">Link to my Hacker rank Profile</a>
        </div>
    </div>
</div>

    <div class="logout">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="hidden" value="Logout">
        </form>
    </div>
</div>
</body>
</html>
