<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
<%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
<title>冠群驰骋查账系统-分配角色</title>
<%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
</head>
<body>
<c:set var="leftMenu" value="${menuId}" scope="request" />
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="content">
        <!--fun-->
        <div class="fun">
            <ol class="fun_li clearfix">
                <li><a id="saveRole" class="btn" href="${contextPath}/sys/assignauth.do" title="保存"><span class="icon save"></span>保存</a></li>
                <li><a class="btn" href="${contextPath}/sys/empmanage" title="取消"><span class="icon cancel"></span>取消</a></li>
            </ol>
        </div>
        <!--fun-->
        <!--auto_box-->
        <div class="auto_box" style="margin-top:25px;">
            <!--table01-->
            <table class="table01">
                <col width="40"/>
                <col width="200"/>
                <col width="200"/>
                <col />
                <col />
                <thead>
                    <tr>
                        <th class="tl"><input type="checkbox"  name="checkAll" id="checkAll" /></th>
                        <th>名称</th>
                        <th>角色特征</th>
                        <th>描述</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="role" items="${roles}">
                    <tr>
                        <td class="tl"><input type="checkbox" class='${func:isInString(role.id,",1,")?"super":""}' value="${role.id}" name="roleSelect" ${func:isInString(role.id,empRoleIds)?"checked":""}/></td>
                        <td>${role.roleName}</td>
                        <td>${fMap[role.status]}</td>
                        <td>${role.description}</td>
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
	</div>
    </div>
    <%@include file="/WEB-INF/jsp/inc/foot.inc" %>
    <script type="text/javascript" charset="utf-8">
    alert("aa");
    $('#saveRole').live('click',function(e){
		e= e || window.event;
		e.preventDefault();
		var rid=[];
		$('input[name=roleSelect]:checked').each(function(){
			rid.push(this.value);
		});
		$.post(this.href,{'id':'${id}','rid':rid.join(',')},function(e){
			window.location.href="${contextPath}/sys/empmanage";
		},'json');
	});
	$('#checkAll').live('click',function(){
		var that=this;
		$('input[name=roleSelect]').each(function(){
			if($(this).val()!=1){
			this.checked=that.checked;
		}
		});
	});
	$('input[name=roleSelect]').click(function(){
		if($(this).val()==1&&this.checked){
			$('input[name=roleSelect]:not(:eq('+$(this).index()+'))').each(function(){
				this.checked=false;
			});
		}else if(this.checked){
			$('.super').attr('checked',false);
		}
		
	});
    </script>
    </body>
</html>
