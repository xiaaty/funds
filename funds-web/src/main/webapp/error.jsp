<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<%
   String error=(String)request.getAttribute("error_key");
	String mes="";
   if(error==null||"".equals(error)){
          mes="未知异常";
       
   }else{
   		mes="没有权限";
     
    }
 %>
<head>
</head>
<body>
	<center>
		<br> <br> <br> <br><%=mes%>
		<br>

	</center>
</body>

</html>