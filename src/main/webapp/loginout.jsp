<%@ page import="com.gqhmt.core.util.ResourceUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String url = ResourceUtil.getValue("config.appContext","casLogoutUrl");
%>
<script language="javascript"> 
window.location.href="<%=url%>/";
</script>
