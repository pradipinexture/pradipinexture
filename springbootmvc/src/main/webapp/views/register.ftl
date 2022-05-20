<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login Page</title>
	
	<link rel="stylesheet" href="/library/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/custom.css">
	<style>
		#imgPreview{
			width:150px;
			height: 150px;
			border: 1px solid black;
		}
	</style>
	<script src="/javascript/jquery-3.6.0.min.js"></script>
</head>

<body>
		<#assign pageName="Register">
		<#if sessionUser??>
			<header>
				<#include "header.ftl">	
			</header>
			<#assign pageName="Update">
		</#if>
		<main>
		
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 col-sm-12">
					<section class="form-section">
					<!--InsertUpdateUser-->
						<form action="AddUser" id="register-form"
							enctype='multipart/form-data' method="post">
							<!-- Below div for form heading -->
							<div class="form-heading">
								<h2>${pageName} Page</h2>
							</div>
							<input type="hidden" name="id" value="${userObj.id!""}">
							<!-- Below div for name of user -->
							<div class="form-group">
								<label for="name">Name :</label> <input type="text"
									class="form-control" id="name" name="name"
									placeholder="Please enter name"
									value="${userObj.name!""}" />
									
								<p class="field-error" id="name-error"></p>
							</div>
							<!-- Below div for name of user -->
									<!-- Below div for email of user -->
							<div class="form-group">
								<label for="">Email :</label> <input type="text"
									class="form-control" name="email" id="email"
									placeholder="Please enter valid email" value="${userObj.email!""}">
								<p class="field-error" id="email-error"></p>

							</div>

							<!-- Below div for mobile of user -->
							<div class="form-group">
								<label for="mobile">Mobile :</label> <input type="number"
									class="form-control" id="mobile" name="mobile"
									placeholder="Please enter mobile number"
									value="${userObj.mobile!""}">
								<p class="field-error" id="mobile-error"></p>
							</div>

							<!-- Below div for url of user -->
							<div class="form-group">
								<label for="date">Birth Date :</label> <input type="date"
									class="form-control" id="birthdate" name="birthdate"
									placeholder="Please enter birthdate" value="${userObj.birthdate!""}">
								<p class="field-error" id="birthdate-error"></p>
							</div>


							<!-- Below div for gender of user -->
							<div class="form-group">
								<label for="">Gender :</label>
								<div class="radio">
									<label> <input type="radio" name="gender" id="gender-male"
										value="male"> Male
									</label> <label> <input type="radio" name="gender" id="gender-female"
										value="female"> Female
									</label> <label> <input type="radio" name="gender" id="gender-other"
										value="other"> other
									</label>
								</div>
								<p class="field-error" id="radio-error"></p>
							</div>
							<!-- Below div for Hobby of user -->
							<div class="form-group">
								<label for="hobby">Hobby :</label> <select class="form-control"
									id="hobby" name="hobby">
									<option value="not">Select Hobby</option>
									<option value="Cricket">Cricket</option>
									<option value="Travelling">Travelling</option>
									<option value="Reading Books">Reading Books</option>
									<option value="Learning New Things">Learning New
										Things</option>
								</select>
								<p class="field-error" id="drop-error"></p>
							</div>
							<div class="addresses">

								<#list userObj.address![] as address>
								  <div class="form-group address-section">
										<label for="address">Address :</label>
										<input type="hidden" name="address[${address?index!""}].id" value="${address.id!""}">
										<textarea class="form-control addresstest" rows="3" class="address"
											placeholder="Please enter Address" name="address[${address?index!""}].general" >${address.general!""}</textarea>
										<div class="add-style">
											<span> <select class="form-control city" id="city${address?index!""}" name="address[${address?index!""}].city">
													<option value="not">Select City</option>
													<option value="Botad">Botad</option>
													<option value="Ahmedabad">Ahmedabad</option>
													<option value="Baroda">Baroda</option>
													<option value="Rajkot">Rajkot</option>
											</select>
											</span> <span> <select class="form-control state" id="state${address?index!""}" name="address[${address?index!""}].state">
													<option value="not">Select State</option>
													<option value="Gujarat">Gujarat</option>
													<option value="Rajasthan">Rajasthan</option>
													<option value="Madhyapradesh">Madhyapradesh</option>
											</select>
											</span> <span> <input type="number"
												class="form-control pincode" name="address[${address?index!""}].pincode"
												placeholder="Please enter pincode" value="${address.pincode!""}">
											</span> <span><a href="javascript:void(0);"
												class="list_remove_button btn btn-danger">-</a></span>
										</div>
										<p class="field-error1"></p>
									</div>
									<script type="text/javascript">
												$("#state${address?index}").val ("${address.state}");
												$("#city${address?index}").val ("${address.city}");
									</script>
								<#else>
									<div class="form-group address-section">
										<label for="address">Address :</label>
										<textarea class="form-control addresstest" rows="3" 
											placeholder="Please enter Address" name="address[0].general"></textarea>
										<div class="add-style">
											<span> <select class="form-control city" name="address[0].city">
													<option value="not">Select City</option>
													<option value="Botad">Botad</option>
													<option value="Ahmedabad">Ahmedabad</option>
													<option value="Baroda">Baroda</option>
													<option value="Rajkot">Rajkot</option>
											</select>
											</span> <span> <select class="form-control state" name="address[0].state">
													<option value="not">Select State</option>
													<option value="Gujarat">Gujarat</option>
													<option value="Rajasthan">Rajasthan</option>
													<option value="Madhyapradesh">Madhyapradesh</option>
											</select>
											</span> <span> <input type="number"
												class="form-control pincode" name="address[0].pincode"
												placeholder="Please enter pincode">
											</span> <span><a href="javascript:void(0);"
												class="list_remove_button btn btn-danger">-</a></span>
										</div>
										
										<p class="field-error1"></p>
									</div>
								</#list>
							</div>
							<div class="form-group">
								<p class="field-error" id="address-max-error"></p>
							</div>
							<div class="form-button">
								<button class="btn btn-primary list_add_button" type="button">+</button>
							</div>
							<!-- Below div for password of user -->
							<div class="form-group">
								<label for="password">Password :</label> <input type="password"
									name="password" class="form-control" id="password"
									placeholder="Please enter Password" value="${userObj.password!""}">
								<p class="field-error" id="password-error"></p>
							</div>

							<!-- Below div for conform password of user -->
							<div class="form-group">
								<label for="c-password">Confirm Password :</label> <input
									type="password" class="form-control" id="c-password"
									name="cpassword" placeholder="Please enter confirm Password" value="${userObj.password!""}">
								<p class="field-error" id="cpassword-error"></p>
							</div>
							<!-- Below div for image input of user -->
							<div class="form-group demo-image">
								
								<input type="file" name="image" id="imageclick" >
								<p class="field-error" id="image-error"></p>
								<div id="iii">
									<#if userObj.email??><img id='imgPreview'  alt='Profile preview' src="data:image/jpg;base64,${userObj.imageBase64!""}" width='100%' height='100%'/></#if>
								</div>
							</div>

							<div class="form-button">
								<input id="data" type="submit" value="${pageName}">
							</div>

							<#if !sessionUser??>
								<div class="form-group">
									<label for="">Already have an account ? </label> <a href="index">Login</a>
								</div>
							</#if>
							
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
	<script type="text/javascript">
		$(document).ready(function(){
		
		 $("form").submit(function(e){

		if(!birthDateCheck() | !genderMust() | !alphabets($("#name").val(), "#name-error") | !numberCheck($("#mobile").val(),"#mobile-error")
		| !passwordCheck($("#password").val(),"#password-error") | !dropCheck($("#hobby").val(),"#drop-error") | !allAddressesCheck()
		| !cPasswordCheck("#password","#c-password","#cpassword-error") <#if !userObj.email??>| !imageCheck($("#imageclick")[0],"#image-error")</#if> | !emailCheck($("#email").val(),"#email-error") ){
			alert("Please fill all fields");
			e.preventDefault(e);
		}
    });
		
			<#if userObj.email??>
				$("#gender-${userObj.gender!""}").attr('checked', true);
				$("#hobby").val ("${userObj.hobby!""}"); 	
				
			</#if>
			
	 
	
	$('.list_add_button').on('click', function() {
		addAddress();
	});
	
	$('.addresses').on('click', '.list_remove_button', function() {
		removeAddress(this);
	});
	var numOfAddress =$('.addresstest').length-1;
	function addAddress(){
		numOfAddress++;
		
			var list_fieldHTML = '<div class="form-group address-section"><label>Address '
					+ ' :</label><textarea class="form-control  addresstest" rows="3"  placeholder="Please enter Address" name="address['+numOfAddress+'].general"></textarea>'
					+ '<div class="add-style"><span><select class="form-control city"  name="address['+numOfAddress+'].city"><option value="not">Select City</option>'
					+ '<option value="Botad">Botad</option><option value="Ahmedabad">Ahmedabad</option><option value="Baroda">Baroda</option>'
					+ '<option value="Rajkot">Rajkot</option></select></span><span><select class="form-control state"  name="address['+numOfAddress+'].state">'
					+ '<option value="not">Select State</option><option value="Gujarat">Gujarat</option><option value="Rajasthan">Rajasthan</option>'
					+ '<option value="Madhyapradesh">Madhyapradesh</option></select></span><span>'
					+ '<input type="number" class="form-control pincode"  name="address['+numOfAddress+'].pincode" placeholder="Please enter pincode" ></span><span><a href="javascript:void(0);" class="list_remove_button btn btn-danger">-</a></span></div><input type="hidden"  name="newOldStatus" value="new"><p class="field-error" id="address-error"></p></div>';
			$('.addresses').append(list_fieldHTML); //Add field html
	}
	function removeAddress(element){
		if ($('.addresstest').length > 1) {
			$(element).parent().parent().parent().remove(); //Remove field html
		}
	}


	
		/* Below function add preview image after user add some image */	
		$('#imageclick').change(function(){
	        const file = this.files[0];
	        var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
	  			$("#image-error").text("Please enter image only");   
	  			$("#imgPreview").hide();      
	        }
	        else{
	        	if (file){
		          let reader = new FileReader();
		          reader.onload = function(event){
		          $("#image-error").text(""); 
		        	$("#iii").html($("<img id='imgPreview'  alt='Profile preview' width='100%' height='100%'/>"));
		            $('#imgPreview').attr('src', event.target.result);
		          }
		          reader.readAsDataURL(file);
		        }
	        }
	          
	 });
	
	
   $(".addresses").on('blur',"textarea",function(){
	   nullCheck($(this).val(),$(this).siblings("p"));
	   // $(this).siblings("p").text("this text area");
   });
   $(".addresses").on('blur',".pincode",function(){
	   pincodeCheck($(this).val(),$(this).parent().parent().siblings("p"));
   });
   $(".addresses").on('blur',".city",function(){
	   cityStateFun($(this));
   });
   $(".addresses").on('blur',".state",function(){
	   cityStateFun($(this));
   });
	function addressFun(element){
		 return nullCheck(element.val(),element.siblings("p"));
	}
	function cityStateFun(element){
		return dropCheck(element.val(),element.parent().parent().siblings("p"));
	}	
	function pincodeFun(element){
		return pincodeCheck(element.val(),element.parent().parent().siblings("p"));
	}
	function allAddressesCheck(){
		var addressesCheck=0;
		for(var i=0;i<$(".address-section").length;i++)
		{
			if(!addressFun($("textarea").eq(i)) || !cityStateFun($(".city").eq(i)) || !cityStateFun($(".state").eq(i)) || !pincodeFun($(".pincode").eq(i))){
				$(".address").eq(i).siblings("p").text("Please fill all fields of address");
				addressesCheck++;
			}
		}
		if(addressesCheck != 0){
			return false;
		}
		else{
			return true;
		}
	}

	$('#email').blur(function() {
		emailCheck($("#email").val(),"#email-error")
	});
	$('#password').blur(function(){
		passwordCheck($("#password").val(),"#password-error");
	});
	$('#c-password').blur(function(){
		cPasswordCheck("#password","#c-password","#cpassword-error");
	});
	/* Below function for check name only contain alphabets only */	
	$('#name').blur(function(){
		alphabets($("#name").val(), "#name-error");
	});
	
	/* Below function for check name only contain digits only */
	$('#mobile').blur(function(){
		numberCheck($("#mobile").val(),"#mobile-error");
	});
	$('#hobby').blur(function(){
		dropCheck($(this[this.selectedIndex]).val(),"#drop-error");
	}); 
	$('#hobby').focus(function(){
		genderMust();
	});
	/* Below function check date like (1. date is below 31 (2. month is below 12  (3. year is less then current year  */	
	$('#birthdate').blur(function(){
		birthDateCheck();				
	});

	
	function imageCheck(imgval,errorField){
		if(imgval.files.length === 0){
			$(errorField).text("Please select image");
			return false;	
		}
		else{
			$(errorField).text("");
			return true;
		}
	}
	function dropCheck(dropval,errorField){
		if(dropval != "not"){
			$(errorField).text("");
			return true;					
		}
		else{
			$(errorField).text("Please select value");
			return false;	

		}
	}
     
	function birthDateCheck(){
		var dt = new Date( $("#birthdate").val());
		if(dt.getFullYear() > 0){
			if( new Date().getFullYear() < dt.getFullYear() && dt.getMonth() <= 12 && dt.getDate() <= 31){
				$("#birthdate-error").text("Please enter valid date");
				return false;	
			}
			else{
				$("#birthdate-error").text("");
				return true;
			}
			
		}
		else{
			$("#birthdate-error").text("Please select date");
		}
		
	}
	
	function genderMust(){
		if($('input[name="gender"]:checked').length == 0){
			$("#radio-error").text("Please select gender");
			return false;	
		}
		else{
			$("#radio-error").text("");
			return true;
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
	function pincodeCheck(fValue,errorField){
		if(nullCheck(fValue,errorField) && fValue.length != 6){
				$(errorField).text("Please enter 6 digit in pincode.");
				return false;
		}	
		else{
				return true;
		}
	}
	function alphabets(fValue,errorField){
		var regex = /^[A-Za-z ]+$/;		
		if(nullCheck(fValue,errorField) && !regex.test(fValue)){
				$(errorField).text("Please enter alphabets only");
				return false;
		}	
		else{
				return true;
		}
	}
	
	function numberCheck(fValue,errorField){
		var regex = /^[1-9]{1}[0-9]{9}$/;
		if(nullCheck(fValue,errorField) && !regex.test(fValue)){
			$(errorField).text("Please enter 10 digit mobile number.");
			return false;
		}	
		else{
				return true;
		}
	}
	
	function emailCheck(fValue,errorField){
		var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
		if(nullCheck(fValue,errorField) && !regex.test(fValue)){
			$(errorField).text("Please enter valid email.");
			return false;
		}		
		else{
			if(nullCheck(fValue,errorField) && fValue != "${userObj.email!"pradip"}"){
				$.ajax({
					url : "EmailCheck",
					type : "post",
					data : {
						cuEmail : fValue
					},
					success : function(data) {
						if (data.length != 0) {
							$("#email-error").text("Email already exist");
							return false;
						}
						else{
							$("#email-error").text("");
							return true;
						}
					}
				});
			}
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
	function cPasswordCheck(fValue,fValue2,errorField){
		if(nullCheck($(fValue2).val(),errorField) && $(fValue).val() != $(fValue2).val()){
			$(errorField).text("Password and confirm password not same");
			return false;
		}	
		else{
				return true;
		}
	
	}
});

	</script>
</body>

</html>
	