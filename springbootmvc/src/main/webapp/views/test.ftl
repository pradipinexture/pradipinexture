<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login Page</title>
	
	<link rel="stylesheet" href="./resources/library/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="./resources/css/custom.css">
	<style>
		#imgPreview{
			width:150px;
			height: 150px;
			border: 1px solid black;
		}
		.form-group *{
			display:inline;
		}
		
	</style>
	<script src="./resources/javascript/jquery-3.6.0.min.js"></script>
</head>

<body>
		<#if sessionUser??>
			<header>
				<#include "header.ftl">	
			</header>
		</#if>
		<main>
		
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 col-sm-12">
					<section class="form-section">
					<!--InsertUpdateUser-->
						<form action="#" id="register-form"
							enctype='multipart/form-data' method="post">
							<!-- Below div for form heading -->
							<div class="form-heading">
								<h2>Error Page</h2>
							</div>
							<!-- Below div for name of user -->
							<div class="form-group">
								<label for="name">=> Exception :</label> 
								<p class="field-error" id="exception-error">${exception!""}</p>
							</div>
							<div class="form-group">
								<label for="name">=> Path :</label> 
								<p class="field-error" id="path-error">${url!""}</p>
							</div>
						</form>
					</section>
				</div>
			</div>
		</div>

	</main>
	<footer>
		<#if sessionUser??>
			<header>
				<#include "footer.ftl">	
			</header>
		</#if>
	</footer>

</body>

</html>
	