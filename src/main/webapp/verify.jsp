<%@page import="com.gqhmt.util.MakeCertPic"%>
<%@page language="java" import="java.util.*"
	contentType="image/jpeg" pageEncoding="utf-8" %>
<%
response.reset();
String str = MakeCertPic.getCertPic(84,28,response.getOutputStream());
//将验证码存入session中 
if(session.getAttribute("verifyCode")!=null){
	session.removeAttribute("verifyCode");
}
session.setAttribute("verifyCode",str);
%>
