<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RamBaldeo</title>
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

    .content {
        display: flex;
        justify-content: flex-start;
        align-items: flex-start;
        gap: 2rem;
        margin-top: 1.5rem;
    }

    .left {
        flex: 1;
        max-width: 400px;
    }

    .right {
        flex: 1;
        max-width: 400px;
    }

    .left p {
        font-size: 1rem;
        color: #555;
        margin-bottom: 1rem;
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
<!-- 	include a header with an about page 
		<jsp:include page="_header.jsp"></jsp:include>
		Need to incorporate AI into this to display a daily quote.
-->
	<jsp:include page="_LoggedInHeader.jsp"></jsp:include>

    <div class="content">
        <div class="left">
		<p><strong>This is the quote from OpenAI:</strong></p>
		
		    <blockquote id="aiQuoteId" style="font-style: italic; color: #007BFF;">
		        "${aiQuote}"
		    </blockquote>


        <!-- <a href="${pageContext.request.contextPath}/AiResponse">Generate new quote</a> -->
        <button  onClick="generateQuote()">Generate new quote</button>
        
        <!-- List of quotes that have been generated for this session 
        This code will looop through all the items in the array
        -->
        <!--  Need to add a next and previous button that will call the next/previous item in the array -->

        
            <%-- <p><strong>Current Description:</strong></p>
            <p>${user.description}</p>

            <form action="${pageContext.request.contextPath}/description" method="post">
                <label for="description">Update your description:</label>
                <input type="text" id="description" name="description" value="${user.description}"/>
                <input type="hidden" id="firstName" name="firstName" value="${user.firstName}">
                <input type="submit" value="Update Description">
            </form> --%>
        </div>
        <div class="right">
        	<p>Please note that I am currently will working on implementing new features but due to my work schedule, it is taking
        	me some time. Once I have tested new features on my local environment and is satisfied, I will update my website.
        	Thank you for your patience. </p>
        </div>
    </div>

    <div class="logout">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</div>

<script>
	window.onload = function () {
		generateQuote();
	};


	function generateQuote() {
		const quoteEL = document.getElementById("aiQuoteId");
		quoteEl.innerText = "Generating....";
		fetch("${pageContext.request.contextPath}/AiResponse")
			.then(response => response.text())
			.then(tezt => {
				quoteEl.innerText = `"${text}"`;
			})
		.catch( () => {
			quoteEl.innerText = "Failed to load quote";
		})
	}
	
	
	
</script>

</body>
</html>









