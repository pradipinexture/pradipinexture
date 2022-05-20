<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

	<link rel="stylesheet" href="/library/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/custom.css">
	<script src="/javascript/jquery-3.6.0.min.js"></script>

</head>
<body>
	<!-- header section -->
	<header>
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">Pradip Company  </a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active"><a href="">Home <span class="sr-only">(current)</span></a></li>
		        <li><a href="profile?profileEmail=${sessionUser.email}">Profile</a></li>
		        <#if sessionUser.role.roleType != 0>
		         <li class=""><a href="viewusers">View All User</a></li>
		         <li class=""><a href="register?pageType=adduserpage"">Add New User</a></li>
		         </#if>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="Logout">Logout</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
	</header>
</body>
</html>