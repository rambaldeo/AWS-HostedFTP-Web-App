<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="planning.webapp.UserAccounts" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{box-sizing: border-box}
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
        max-width: 3rem;
        width: 90%;
    }

	.tab{
		float: left;
		border: 1px solid #ccc;
		background-color: #f1f1f1;
		width: 30%;
		height: 2.5rem;
	}
	.tab button{
		display: block;
		background-color: inherit;
		color: black;
		padding: 22px 16px;
		width: 100%;
		border: none;
		outline: none;
		text-aling: left;
		cursor: pointer;
		transition: 0.3s;
	}
	
	.tab button:hover{
		background-color: #ddd;
	}
	.tab button.active{
	background-color: #ccc;
	}
	.tabcontent{
		float: left;
		padding: 0px 12px;
		border: 1px solid #ccc;
		border-left: none;
		width: 70%;
		height: 2.5rem;
	}
	
    h1 {
        font-size: 2rem;
        margin-bottom: 1.5rem;
        color: #333;
    }
</style>
</head>
<body>

<div class="container">
       <% 
            UserAccounts loginedUser = (UserAccounts) session.getAttribute("loginedUser");
        %>
	<!-- Need to show the user;s first name
		The company that they work at
		profile pic
		 
	 -->
	 <jsp:include page="_LoggedInHeader.jsp"></jsp:include>
	 
	 <h2>Account settings for: <%= loginedUser.getFirstName() %></h2>
	 
	 <div class="tab">
	 	<button class="tablinks" onclick="openTab(event, 'AccountDetails')">Account Details</button>
	 	<button class="tablinks" onclick="openTab(event, 'Change Pssword')">Change Password</button>
	 	<button class="tablinks" onclick="openTab(event, 'Logout')">Log out</button>
	 </div>
	 
	 <div id="AccountDetails" class="tabcontent">
	 	<h2>Account Details</h2>
	 </div>
	 <div id="ChangePassword" class="tabcontent">
	 	<h2>Change password</h2>
	 </div>
	 <div id="logout" class="tabcontent">Logout</div>
	 
</div>

<script>
	function openTab(evt, tabName){
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		//Get all elements with class =tabcontent and hide them
		for(i=0, i<tabcontent.length; i++){
			tabcontent[i].style.display = "none";	
		}
		//get all elements with class tablinks and remove the class=active
		tablinks = document.getElementsByClassName("tablinks");
			for( i=0;i<tablinks.length;i++){
				tablinks[i].className = tablinks[i].className.replace(" active", "");
			}
		//show the current tab and add an active class to the link that opened the tab
		document.getElementsById(tabName).style.display ="block";
		evt.currentTarget.className += " avtive";'
		
	}
</script>



</body>
</html>





























