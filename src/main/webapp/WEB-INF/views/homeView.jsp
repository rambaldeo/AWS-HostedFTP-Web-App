<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ram Baldeo's Java Web App</title>
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
        max-width: 1200px; 
        width: 90%;       
    }

    h1 {
        font-size: 2rem;
        margin-bottom: 1rem;
        color: #333;
    }

    p {
        font-size: 1rem;
        margin: 0.5rem 0;
        color: #555;
    }

    a {
        display: inline-block;
        margin-top: 1rem;
        padding: 0.6rem 1.2rem;
        background: #007BFF;
        color: #fff;
        text-decoration: none;
        border-radius: 8px;
        transition: background 0.3s ease;
    }

    a:hover {
        background: #0056b3;
    }

    .secondary {
        background: #28a745;
    }

    .secondary:hover {
        background: #1e7e34;
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
		<jsp:include page="_header.jsp"></jsp:include>

		<h1>Welcome to the home page</h1>
		<p>Please click the link below to login</p>
		<a href="${pageContext.request.contextPath}/login">Login</a>
		
		<p>If you do not have an account, please sign up</p>
		<a class="secondary" href="${pageContext.request.contextPath}/SignUpServlet">Sign Up</a>
	</div>
</body>
</html>
