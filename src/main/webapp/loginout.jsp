<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
session.invalidate();

%>
<script language="javascript"> 
window.location.href="<%=request.getContextPath()%>/";
</script>
