<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="/library/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/custom.css">
	<script src="/javascript/jquery-3.6.0.min.js"></script>
<style>
		html,body{
		    margin: 0%;
		    padding: 0%;
		    background-color: rgb(234, 228, 239);
		    
		}
		.form-section2 {
			margin:5%;
			height: auto;
			min-height: 510px;
		}
		
		#csv-btn {
			margin-top: 3%;
			margin-left: 43%;
		}
		
</style>
</head>
<body>
	<header>
		<#include "header.ftl">	
	</header>
	<section class="form-section2">
		<table id="table_id" class="ui celled table" style="width: 100%">
			<thead>
				<tr>
					<th>Emp ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Gender</th>
					<th>View</th>
					<th>Edit</th>
					<th>Delete</th>

				</tr>
			</thead>
			<#list AllUserList![] as perUser>
				<tbody>
					<tr>
						<td>${perUser.id!""}</td>
						<td>${perUser.name!""}</td>
						<td>${perUser.email!""}</td>
						<td>${perUser.gender!""}</td>
						<td><a href="profile?profileEmail=${perUser.email!""}"  class="btn btn-warning">View</a></td>
						<td><a href="EditProfile?email=${perUser.email!""}"  class="btn btn-success">Edit</a></td>
						<td><a id='${perUser.id!""}'<#if perUser.role??> <#if perUser.role.roleType != 0> style="pointer-events: none;background-color:blue;" </#if></#if> class="btn btn-danger del-user">Delete</a></td>
					</tr>
				</tbody>
			</#list>
		</table>
		<div id="csv-btn">
			<a class="btn btn-success" href="GenerateCSV">Generate CSV File</a>
		</div>	

	</section>

	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
	<script>
		$(document).ready(function() {
			$(function() {
				$("#table_id").dataTable();
			});
			// crating new click event for save button
			
			$(".del-user").click(function() {
				var result = confirm("Are you sure for delete ?");
				if (result) {
					var id = $(this).attr('id');
					console.log(id);
					$.ajax({
						url : "DeleteUser",
						type : "post",
						data : {
							id : id,
						},
						success : function(data) {
						}
					});	
					$(this).parents("tr").animate("fast").animate({
						opacity : "hide"
					}, "fast");
				}
				
			});
		});
	</script>
	<footer>
		<#include "footer.ftl">	
	</footer>
</body>
</html>