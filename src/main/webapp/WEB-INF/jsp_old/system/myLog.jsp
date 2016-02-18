<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
<title>冠群驰骋查账系统-我的登录日志查看</title>
<%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
</head>
<body>
<c:set var="leftMenu" value="${menuId}" scope="request" />
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
    <div id="content">
       <!--saerch-->
        <!--saerch-->
         <div class="clearfix">
            <div class="fr mb15 mr20">
                <select class="select01 sel02" style="width:130px;" id="pageChange">
                <c:forEach var="num" items="${pageMap}">
                    <option value="${num.key}"  ${pageNum==num.key?"selected":""}>每页${num.value}条</option>
                </c:forEach>
                </select>
            </div>
        </div>
        <!--auto_box-->
        <div class="auto_box">
            <!--table01-->
            <table class="table01">
                <col width="200"/>
                <col width="300"/>
                <col width="200"/>
                <col />
                <thead>
                    <tr>
                        <th class="tl">登录用户编号</th>
                        <th>所属机构</th>
                        <th>登录时间</th>
                        <th>登录IP</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="log" items="${logs}">
                    <tr>
                        <td class="tl">${log.loginName}</td>
                        <td>${log.department}</td>
                        <td><fmt:formatDate value="${log.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${log.ip}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!--table01-->
        </div>
        <!--auto_box-->
        <!--page-->
        <div class="page mt20">
        	<page:page pager="${pb}" />
        </div>
        <!--page-->
	</div>
    </div>
    <%@include file="/WEB-INF/jsp/inc/foot.inc" %>
    </body>
</html>
