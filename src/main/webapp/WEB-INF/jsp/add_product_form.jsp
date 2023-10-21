<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.Date" %>
        <%@ page import="java.text.SimpleDateFormat " %>
    
<%
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
  Date date = new Date();
%>
    
<!DOCTYPE html>
<html>
<head>
<%@include file="./base.jsp" %>
<meta charset="ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
footer{
    padding: 10px 0;
    background-color: #101010;
    color:#9d9d9d;
    bottom: 0;
    width: 100%;
    position:fixed
    
}
@import url('https://fonts.googleapis.com/css?family=Josefin+Sans:400,400i,600,600i');
html,body{
  margin:0;
  height:100%;
  font-family: 'Josefin Sans', sans-serif;

}
a{
  text-decoration:none
}
.header{
  position:relative;
  overflow:hidden;
  display:flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: flex-start;
  align-content: flex-start;
  height:10vw;
  color:#eee;
}
.header:after{
  content:"";
  width:100%;
  height:100%;
  position:absolute;
  bottom:0;
  left:0;
  z-index:-1;
 background: linear-gradient(to bottom, rgba(0,0,0,0.12) 40%,rgba(27,32,48,1) 100%);
}
.header:before{
  content:"";
  width:100%;
  height:200%;
  position:absolute;
  top:0;
  left:0;
    -webkit-backface-visibility: hidden;
    -webkit-transform: translateZ(0); backface-visibility: hidden;
  scale(1.0, 1.0);
    transform: translateZ(0);
  background:#1B2030 url(https://us.123rf.com/450wm/neirfy/neirfy2210/neirfy221000634/193445773-big-library-interior-sheves-with-books-learning-and-back-to-school-concept.jpg?ver=6) 50% 0 no-repeat;
  background-size:100%;
  background-attachment:fixed;
  animation: grow 360s  linear 10ms infinite;
  transition:all 0.4s ease-in-out;
  z-index:-2
}
.header a{
  color:#eee
}
.menu{
  display:block;
  width:40px;
  height:30px;
  border:2px solid #fff;
  border-radius:3px;
  position:absolute;
  right:20px;
  top:20px;
  text-decoration:none
}
.menu:after{
  content:"";
  display:block;
  width:20px;
  height:3px;
  background:#fff;
  position:absolute;
  margin:0 auto;
  top:5px;
  left:0;
  right:0;
  box-shadow:0 8px, 0 16px
}
.logo{
  border:2px solid #fff;
  border-radius:3px;
  text-decoration:none;
  display:inline-flex;
  align-items:center;
  align-content:center;
  margin:20px;
  padding:0px 10px;
  font-weight:900;
  font-size:1.1em;
  line-height:1;
  box-sizing:border-box;
  height:40px
}
.sides, .info{
  flex: 0 0 auto;
  width:50%
}
.info{
  width:100%;
  text-align:center;
  text-shadow:0 2px 3px rgba(0,0,0,0.2)
}
.author{
  display:inline-block;
  width:50px;
  height:50px;
  border-radius:50%;
  background:url(https://i.imgur.com/6DLCsZcb.jpg) center no-repeat;
  background-size:cover;
  box-shadow:0 2px 3px rgba(0,0,0,0.3);
  margin-bottom:3px
}
.info h4, .meta{
  font-size:0.7em
}
.meta{
  font-style:italic;
}
@keyframes grow{
  0% { transform: scale(1) translateY(0px)}
  50% { transform: scale(1.2) translateY(-400px)}
}
.content{  
  padding:5% 10%;
  text-align:justify
}
.btn{
  color:#333;
  border:2px solid;
  border-radius:3px;
  text-decoration:none;
  display:inline-block;
  padding:5px 10px;
  font-weight:600
}

.twtr{
  margin-top:100px
}.btn.twtr:after{content:"\1F426";padding-left:5px}
</style>
</head>
<body>
<div class="header">
  <div class="sides">
    Hii  ${userId} 
  </div>
  <div class="sides"> 
  <a href="/logout"  style="float:right">
   <br> <button class="btn btn-danger">Sign Out</button>
</a></div>
  <div class="info">
    <h1>THE LIBRARY MANAGEMENT SYSTEM</h1>
  </div>
</div>
<div class="container mt-3">
	<div class="row">
		<div class="col-md-6 offset-md-3">
		<br>
			<h1 class="text-center mb-3" >
				Fill the Book detail
			</h1><br>
			<form action="/handle-product" method="post">
				<div class="form-group">
					<label for="name">Book Code</label>
					<input type="text" class="form-control" id="bookCode" aria-describedby="emailHelp"
					 name="bookCode" placeholder="Enter the book code here" required>
				</div>
				<div class="form-group">
					<label for="description" >Book Name</label>
					<input type="text"  id="bookName" class="form-control"
					 name="bookName" placeholder="Enter the book description" required>
				</div>
				<div class="form-group">
					<label for="author"> Author</label>
					<select name="author" class="form-control">
						<option value="">---SELECT---</option>
					    <c:forEach items="${authorNames}" var="authorName">
					        <option value="${authorName}">${authorName}</option>
					    </c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="description">Added on</label>
					<input class="form-control" id="date" name="date" type="text" value="<%= dateFormat.format(date)  %>" readonly>
				</div>
			    <input type="hidden" class="form-control" id="user" name="user" type="text" value="${userId}" >
				
				<div class="container text-center">
					<a href="/user?userId=${userId} " class="btn btn-outline-danger">
						Back
					</a>
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</form>
		</div>
	
	</div>

</div><br><br><br><br><br><br>
<footer> 
    <div class="container text-center"> 
            <p>Copyright © Library Management. All Rights Reserved|Contact Us: +91-8448444853</p> 
    </div> 
</footer>
</body>
</html>