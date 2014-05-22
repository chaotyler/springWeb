<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<title>Student List</title>
	<script type="text/javascript" src="/webapp/resources/js/jquery-2.1.1.min.js"></script>
</head>
<body>
<input id="ajaxGetList" value="get list using ajax" type="button"/>

<form id="addStuForm" action="/webapp/student" method="POST">
	<input id="newStudentName" type="text" name="name" placeholder="name"/>
	<input type="submit" value="add">
	<input id="ajaxAdd" type="button" value="add using AJAX">
</form>

<P>The number of students: ${stuList.size()}</P>
<c:if test="${stuList.size() >= 1}">
	<c:forEach var="i" begin="0" end="${stuList.size() - 1}">
	   <P>${i}: ${stuList.get(i).name}
	   <a target="_blank" href="/webapp/student/${stuList.get(i).id}">Detail</a>
	   <a href="/webapp/student/delete/${stuList.get(i).id}">Delete</a>
	   <input value="get detail using ajax" type="button" onclick="getDetailAjax(${stuList.get(i).id})">
	   <input value="delete using ajax" type="button" onclick="deleteAjax(${stuList.get(i).id})">
	   </P>
	</c:forEach>
</c:if>

<script>
//ajax simulate get list
$("#ajaxGetList").click(function(){
	$.ajax({
		url: "/webapp/student/json",
		type: "GET",
		success: function(data){
			console.log(data);
		},
		error: function(msg){
			console.log(msg.statusText);
		}
	});
});

//ajax simulate get one
function deleteAjax(id){
	$.ajax({
		url: "/webapp/student/delete/" + id + "/json",
		type: "GET",
		success: function(data){
			console.log(data);
		},
		error: function(msg){
			console.log(msg.statusText);
		}
	});
}

//ajax simulate get one
function getDetailAjax(id){
	$.ajax({
		url: "/webapp/student/" + id + "/json",
		type: "GET",
		success: function(data){
			console.log(data);
		},
		error: function(msg){
			console.log(msg.statusText);
		}
	});
}

//jquery ajax to simulate add
$("#ajaxAdd").click(function(){
	$.ajax({
	    url: "/webapp/student/json",
	    data: {"name": $("#newStudentName").val()},
	    type: "POST",
	    
	    success: function(data){
	    	console.log(data);
	    },
	    error: function(msg){
	    	console.log(msg.statusText);
	    }
	});	
});
</script>
</body>
</html>
