<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.gqhmt.util.Auth"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
<title>冠群驰骋查账系统-角色管理</title>
<%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
</head>
<body>
<%
			boolean isAdd=Auth.checkAuth(request,"/sys/addrole");
			boolean isDel=Auth.checkAuth(request,"/sys/delrole");
%>
<c:set var="leftMenu" value="${menuId}" scope="request" />
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
    <div id="content">
    <% if(isAdd || isDel){ %>
        <!--fun-->
        <div class="fun">
            <ol class="fun_li clearfix">
            <% if(isAdd){ %>
                <li><a class="btn" href="${contextPath}/sys/addrole" title="新增"><span class="icon add"></span>新增</a></li>
                <% } %>
             <% if(isDel){ %>
                <li><a id="delrole" class="btn" href="${contextPath}/sys/delrole" title="删除"><span class="icon delete"></span>删除</a></li>
                <% } %>
            </ol>
        </div>
      <% } %>
        <!--fun-->
        <!--saerch-->
        <div class="search">
        <form action="${contextPath}/sys/rolemanage" method="post">
            <table class="f14">
                <col width="50" />
                <col width="240" />
                <col width="74" />
                <col width="270" />
                <col />
                    <tr>
                        <td><span>名称:</span></td>
                        <td>
                            <input type="text" name="roleName" class="input01" value="${search.roleName}" />
                        </td>
                        <td><span>角色特征:</span></td>
                        <td>
                            <select style="width:250px" name="status" class="select01">
                            		<option value="0">--不限--</option>
                                	<c:forEach var="sta" items="${fMap}">
                                		<option value="${sta.key}">${sta.value}</option>
                                	</c:forEach>
                            </select>
                        </td>
                        <td>
                        	<button type="submit" class="btn02">查&nbsp;询</button>
                        </td>
                    </tr>
                </table>
                </form>
        </div>
        <!--saerch-->
        <!--sel02-->
         <div class="clearfix">
            <div class="fr mb15 mr20">
                <select class="select01 sel02" style="width:130px;" id="pageChange">
                    <c:forEach var="num" items="${pageMap}">
                    <option value="${num.key}"  ${pageNum==num.key?"selected":""}>每页${num.value}条</option>
                </c:forEach>
                </select>
            </div>
        </div>
        <!--sel02-->
        <!--auto_box-->
        <div class="auto_box">
            <!--table01-->
            <table class="table01">
                <col width="40"/>
                <col width="200"/>
                <col width="200"/>
                <col width="400"/>
                <col />
                <thead>
                    <tr>
                    <% if(isDel){ %>
                        <th class="tl"><input type="checkbox"  name="checkAll" id="checkAll" /></th>
                        <% } %>
                        <th>名称</th>
                        <th>角色特征</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="">
                	<c:forEach var="role" items="${roles}">
                    <tr>
                     <% if(isDel){ %>
                        <td class="tl"><input type="checkbox" name="roleSelect" value="${role.id}" /></td>
                     <% } %>
                        <td>${role.roleName}</td>
                        <td>${fMap[role.status]}</td>
                        <td>${role.description}</td>
                        <td>
                           <div class="n_b">
                               <a href="${contextPath}/sys/addrole?id=${role.id}" class="btn05 icon" title="修改"><span></span></a>
                               &nbsp;&nbsp;&nbsp;&nbsp;
                               <c:if test="${role.id!=1}">
                               	<a href="${contextPath}/sys/rolefunc?id=${role.id}" class="btn10 icon" title="授权"><span></span></a>
                               </c:if>
                           </div> 
                        </td>
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
     <% if(isDel){ %>
    <script type="text/javascript" charset="utf-8">
    	$('#delrole').live('click',function(e){
    		e= e || window.event;
    		e.preventDefault();
    		var rid=[];
    		$('input[name=roleSelect]:checked').each(function(){
    			rid.push(this.value);
    			$(this).closest('tr').remove();
    		});
    		$.post(this.href,{'rid':rid.join(',')},function(e){
    			alert('删除成功');
    			window.location.href="/sys/rolemanage";
    		},'json');
    	});
    	
    	$('#checkAll').live('click',function(){
    		var that=this;
    		$('input[name=roleSelect]').each(function(){
    			if($(this).val()!=1 && $(this).val()!=2 && $(this).val()!=3 ){
    				this.checked=that.checked;
    			}
    		});
    	});
    	
    	$('input[name=roleSelect]').click(function(){
    		if(this.checked && ($(this).val()==1 || $(this).val()==2 || $(this).val()==3)){
    			this.checked=false;
    		}
    	});
    </script>
    <% } %>
    </body>
</html>
