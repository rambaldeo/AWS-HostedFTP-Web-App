<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About This WebApp</title>
<style>
    /* GENERAL STYLES */
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #f0f4f8;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        padding-top: 50px;
        min-width: 360px;
    }

    .container {
        background: #ffffff;
        padding: 2rem 3rem;
        border-radius: 12px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        width: 90%;
        max-width: 1000px;
    }

    h1 {
        font-size: 2rem;
        margin-bottom: 1rem;
        color: #222;
        text-align: center;
        border-bottom: 2px solid #007BFF;
        display: inline-block;
        padding-bottom: 0.5rem;
    }

    h3 {
        color: #555;
        line-height: 1.6;
        margin: 0;
    }

    p {
        color: #555;
        text-align: center;
        margin-bottom: 2rem;
    }

    /* FORM STYLES */
    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 1rem;
        margin-bottom: 2rem;
    }

    select, input[type="submit"] {
        padding: 0.6rem 1rem;
        border-radius: 6px;
        border: 1px solid #ccc;
        font-size: 1rem;
        cursor: pointer;
        min-width: 200px;
    }

    select:focus {
        outline: none;
        border-color: #007BFF;
    }

    input[type="submit"] {
        background: #007BFF;
        color: #fff;
        border: none;
        transition: background 0.3s ease;
    }

    input[type="submit"]:hover {
        background: #0056b3;
    }

    /* INFO BOX STYLES */
    .info-box {
        background: #f9f9f9;
        padding: 1rem 1.5rem;
        border-radius: 10px;
        box-shadow: 0 3px 8px rgba(0,0,0,0.05);
        color: #333;
        font-size: 1rem;
        height: 220px;
        overflow-y: auto;
        margin-bottom: 1.5rem;
        line-height: 1.6;
    }

    .info-box::-webkit-scrollbar {
        width: 8px;
    }

    .info-box::-webkit-scrollbar-thumb {
        background-color: #007BFF;
        border-radius: 4px;
    }

    /* RESPONSIVE LAYOUT */
    @media (max-width: 768px) {
        .container {
            padding: 1.5rem 2rem;
        }

        .info-box {
            height: 180px;
        }
    }
	
	/*Collapse button */
	.collapsible {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
	}
	
	/* Add a background color to the button if it is clicked on (add the .active class with JS), and when you move the mouse over it (hover) */
	.active, .collapsible:hover {
	  background-color: #ccc;
	}
	
	/* Style the collapsible content. Note: hidden by default */
	.content {
	  padding: 0 18px;
	  max-height: 0;              /* collapsed */
	  overflow: hidden;
	  background-color: #f1f1f1;
	  transition: max-height 0.3s ease-out;
	}

</style>
</head>
<body>
    <div class="container">
        <jsp:include page="_LoggedInHeader.jsp"></jsp:include>

        <h3>Welcome to the About Page</h3>
<%--         <p>Select the type of description you want to view: Technical or Marketing.</p>

        <form action="${pageContext.request.contextPath}/AboutWebApp" method="post">
            <select id="topicDropdown" name="topic">
                <option value="">Choose an option</option>
                <option value="Technical">Technical</option>
                <option value="Marketing">Marketing</option>
            </select>
            <input type="submit" value="Submit Option">
        </form> 
        
        If I want to re-add the flip box, check the following link: https://www.w3schools.com/howto/howto_css_flip_box.asp
        --%>
		<button type="button" class="collapsible">General Description</button>
		<div class="content">
		  <p>This was a quick full-stack application developed using Java with a Cloud-based MongoDB Cluster connection deployed on Apache Tomcat. 
                It features secure login, logout, and user data retrieval handled through a RESTful API designed for secure authentication and dynamic data management. 
                The MongoDB cluster uses SCRAM-SHA authentication and TLS encryption with credentials stored in environment variables. 
                The front-end is served via Nginx with SSL termination and reverse proxying for secure HTTPS access. 
                Logging, error handling, and environment-based configuration management ensure reliability and maintainability.</p>
		</div>

		<button type="button" class="collapsible">Security</button>
        <div class="content">
            <p>
                The application is secured with a multi-layered architecture protecting both data in transit and application-level interactions. 
                All traffic is routed through Nginx with HTTPS enforced using Let’s Encrypt TLS certificates. 
                The Java backend runs behind this reverse proxy on Tomcat, isolating it from direct external access. 
                MongoDB communication is secured with SRV DNS records, TLS encryption, and SCRAM-SHA authentication. 
                Sensitive credentials are stored outside the codebase, and Tomcat runs as a non-root user to minimize exposure. 
                The architecture follows the principle of least privilege, reducing attack surfaces and ensuring secure deployment.
            </p>
        </div>

		<button type="button" class="collapsible">Useability</button>
        <div class="content">
            <p>
                The UI emphasizes simplicity and intuitive interaction. 
                Users can navigate easily with organized menus and clearly labeled actions. 
                Core functions are accessible within one or two clicks, with consistent feedback like status messages and form validations. 
                Overall, the application supports a smooth and predictable workflow that enhances productivity and reduces user frustration.
            </p>
        </div>
    </div>
    
    
    
    <script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    }
  });
}
</script>

</body>
</html>










