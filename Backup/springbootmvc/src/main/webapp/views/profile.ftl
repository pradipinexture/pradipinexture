<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login Page</title>
<!-- Css File links -->

	<link rel="stylesheet" href="/library/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/custom.css">
	<script src="/javascript/jquery-3.6.0.min.js"></script>
<style type="text/css">
	.fields *, .a-field * {
		display: inline-block;
	}
	
	.fields, .p-address, .personal h4 {
		margin: 15px;
	}
	
	.p-address .a-field {
		margin-left: 25px;
	}
	
	.personal .fields {
		margin-left: 40px;
	}
	
	label {
		font-size: 16px;
		color: blue;
	}
	#profile-img{
		height:10%;
		width:20%;
		border: 1px solid black;
		border-radius:50%;
		margin-left:37%; 
	}
</style>
	
</head>
<body>
	<header>
		<#include "header.ftl">	
	</header>
	<main>
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 col-sm-12">
					<section class="form-section">
						<div class="personal">
					
							<h4>Personal Information :</h4>
							<div class="fields">
									<!-- assets/images/Image.png src="data:image/jpg;base64,<c:out value="{user.imageString}" />"-->
										<img id="profile-img" alt="Profile Image" src="data:image/jpg;base64,${profileUser.imageBase64!""}">
								
									
							</div>
							<div class="fields">
								<label>Name : </label>
								<div class="fields-value">${profileUser.name!""}</div>
							</div>
							<div class="fields">
								<label>Mobile : </label>
								<div class="fields-value">${profileUser.mobile!""}</div>
							</div>
							<div class="fields">
								<label>Email : </label>
								<div class="fields-value">${profileUser.email!""}</div>
							</div>
							<div class="fields">
								<label>Gender : </label>
								<div class="fields-value">${profileUser.gender!""}</div>
							</div>
							<div class="fields">
								<label>Birthdate : </label>
								<div class="fields-value">${profileUser.birthdate!""}</div>
							</div>
							<div class="fields">
								<label>Hobby : </label>
								<div class="fields-value">${profileUser.hobby!""}</div>
							</div>
						</div>
						<#list profileUser.address![] as addr>
							<div class="p-addresses">
								<div class="p-address">
									<h4>Address :</h4>
									<div class="a-field">
										<label>Address : </label>
										<div class="fields-value">${addr.general!""}</div>
									</div>
									<div class="a-field">
										<label>City : </label>
										<div class="fields-value">${addr.city!""}</div>
									</div>
									<div class="a-field">
										<label>State : </label>
										<div class="fields-value">${addr.state!""}</div>
									</div>
									<div class="a-field">
										<label>Pincode : </label>
										<div class="fields-value">${addr.pincode!""}</div>
									</div>
								</div>
							</div>					
			
						</#list>
						<div class="form-button">
							<a id="data" href="EditProfile?email=${profileUser.email!""}" class="btn btn-success">Edit</a>
						</div>					
					</section>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<#include "footer.ftl">	
	</footer>

</body>

</html>
