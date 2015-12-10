<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<title>冠群驰骋查账系统-新增角色</title>
<%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
</head>
<body>
<c:set var="leftMenu" value="${menuId}" scope="request" />
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="content">
            <!--fun-->
            <div class="fun">
                <ol class="fun_li clearfix">
                    <li><a id="roleSubmit" class="btn" href="javascript:void(0);" title="保存"><span class="icon save"></span>保存</a></li><li><span id="mess" style="color:red;"></span></li>
                </ol>
            </div>
            
            <!--div class="title01 clearfix ml20 mr20 mt10 mb20">
                    <h1 class="b">新增角色</h1>
                    <span class="color04">"*"为必填项</span>
            </div-->


            <!--auto_box-->
            <div class="auto_box">
                <div class="user_info mt15 mb15">
                    <p class="color03 pl20">创建：<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/><span class="pl30">创建者：${roleMap[user.roleId]}</span></p>
                </div>
                <!--user_info-->
                <!--table02-->
                <form id="addRole" action="${contextPath}/sys/addrole" method="post">
                <input type="hidden" name="id" value="<c:out value="${role.id}" default="0"/>" />
                <table class="table03 box998">
                <col width="112" />
                <col width="367" />
                <col width="112" />
                <col />
                    <tbody>
                        <tr>
                            <td align="left"><span class="color04 pr8">*</span>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
                            <td><input type="text" maxlength="50" name="roleName" class="input01" value="${role.roleName}" style="width:238px;" /></td>
                            <td align="left"><span class="color04 pr8">*</span>角色描述：</td>
                            <td>
                                <select class="select01" style="width:256px;" name="status" class="select01">
                                	<c:forEach var="status" items="${fMap}">
                            			<option value="${status.key}" ${role.status==status.key?"selected":""}>${status.value}</option>
                            		</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left" valign="top"><span class="color04 pr8">*</span>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
                            <td colspan="3">
                                <textarea class="mt10 textarea" maxlength="200" name="description">${role.description}</textarea>
                           </td>
                        </tr>
                    </tbody>
                </table>
                </form>
                <!--table02-->
            </div>
            <!--auto_box-->
        </div>
         <%@include file="/WEB-INF/jsp/inc/foot.inc" %>
         <script type="text/javascript" charset="utf-8">
         	$('#roleSubmit').live('click',function(){
         		checkForm()&&$('#addRole').submit();
         	});
         	function checkForm(){
            	var flag=true;
            	$('#addRole :input').each(function(){
            		if(!$(this).val()){
            			$('#mess').html($(this).closest('td').prev('td').html().replace(/[a-z0-9*<>/=&;："]+/gi,'')+"不能为空");
            			$(this).focus();
            			flag=false;
            			return false;
            		}
            	});
            	return flag;
            }
         </script>
</body>
</html>
