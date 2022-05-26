<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login Page</title>
	<link rel="stylesheet" href="/library/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/custom.css">
	<script src="/javascript/jquery-3.6.0.min.js"></script>
</head>

<body>
	
	<!-- Main section for other content -->
	<main>
		<div class="container">	
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 col-sm-12">
					<section class="form-section">
						<form id="login-form" action="ProcessLogin" method="post">
							<!-- Below div for form heading -->
							<div class="form-heading">
								<h2>Login Page</h2>
							</div>

							<!-- Below div for email of user -->
							<div class="form-group1">
								<label for="">Email :</label> <input type="email"
									class="form-control" id="email" name="email"
									placeholder="Please enter valid email">
								<p class="field-error" id="email-error"></p>

							</div>

							<!-- Below div for password of user -->
							<div class="form-group1">
								<label for="password">Password :</label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="Please enter Password">
								<p class="field-error" id="password-error"></p>
							</div>
							
							<div class="form-group1">
								<a href="forgot">Forgot password ? </a>
							</div>
							
							<div class="form-button">
								<input id="data" type="submit"  value="submit">
							</div>
							
							<div class="form-group">
								<label for="Register">Don't have an account ? </label>
								<a href="register">Register  </a>
							</div>
							
						</form>
						<!-- Below div for button  -->
						
					</section>
				</div>
			</div>
		</div>
	</main>
	<script>
		<#if loginError??>
			alert("${loginError}");
		</#if>
		
	</script>
	<script type="text/javascript" >
		$("form").submit(function(e){
			if(!passwordCheck($("#password").val(),"#password-error")
						| !emailCheck($("#email").val(),"#email-error")){
				alert("Please fill all fields");
				e.preventDefault(e);
			}
	    });
	
		$('#email').blur(function(){
			emailCheck($("#email").val(),"#email-error");
		});
		$('#password').blur(function(){
			passwordCheck($("#password").val(),"#password-error");
		});
		
		function emailCheck(fValue,errorField){
			var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
			if(nullCheck(fValue,errorField)){
				console.log("THiss not null");
				if(regex.test(fValue)){
					$.ajax({
						url : "DelUser",
						type : "post",
						data : {
							cuEmail : fValue,
							isCheck : "email",
						},
						success : function(data) {
							if (data.length != 0) {
								$("#email-error").text("");
								return true;
							}
							else{
								$("#email-error").text("Email not exist");
								return false;
							}
						}
					});	
					return true;	
				}
				else{
					$("#email-error").text("Please enter valid mail");
					return false;
				}
				return true;
			}	
			else{
				return false;		
			}
		}
		function nullCheck(fValue,errorField){
			if(fValue.length == 0){
					$(errorField).text("Please enter field value.");
					return false;
			}	
			else{
					$(errorField).text("");
					return true;
					
			}
		}
		function passwordCheck(fValue,errorField){
			var regex = /^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/;	
			if(nullCheck(fValue,errorField) && !regex.test(fValue)){
				$(errorField).text("Please enter valid password.");
				return false;
			}	
			else{
					return true;
			}
		}
	</script>
</body>

</html>
	
