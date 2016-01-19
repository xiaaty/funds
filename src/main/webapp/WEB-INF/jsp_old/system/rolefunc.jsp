<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.gqhmt.sys.beans.SysAuthFunc"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<title>冠群驰骋查账系统-授权管理</title>
<%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
<link href="${contextPath}/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="${contextPath}/js/ztree.js"></script>
<%
	List<SysAuthFunc> funcList=(List<SysAuthFunc>)request.getAttribute("funcList");
	Map<Integer,Integer> roleFuncMap=(Map<Integer,Integer>)request.getAttribute("sysRoleFuncMap");
%>
<script type="text/javascript">
	var zTreeObj;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
		check : {
			enable : true
		},
		callback : {
			onCheck : onCheck
		}
	};
	$(document).ready(function(){
    	createTree();
    });
	var zNodes = [
<%
for(SysAuthFunc func:funcList){
    	 /* if(func.getFuncId()==10){
        	out.println("{ id:"+func.getFuncId()+",pId:"+func.getParentId()+",name:'"+func.getFuncName()+"',open:true},");	
    	 }else{ */
    		 if(roleFuncMap.containsKey(func.getFuncId())){
    			 out.println("{ id:"+func.getFuncId()+",pId:"+func.getParentId()+",name:'"+func.getFuncName()+"', checked:true},");
    			 continue;
    		 }
    		 out.println("{ id:"+func.getFuncId()+",pId:"+func.getParentId()+",name:'"+func.getFuncName()+"'},");
    	 //}
      }
%>
	];
	function onCheck(e, treeId, treeNode) {
		count();
	}
	function count() {
		var zTree = $.fn.zTree.getZTreeObj("treeFunc"), checkCount = zTree
				.getCheckedNodes(true);
		var v = "";
		for (var i = 0, l = checkCount.length; i < l; i++) {
			/* if (!checkCount[i].isParent || checkCount[i].level==2 || checkCount[i].level==1 ) { */
				v += checkCount[i].id + ",";
			/* } */
		}
		if (v.length > 0) {
			v = v.substring(0, v.length - 1);
		}
		;
		$("#funcIds").val(v);
	}
	function createTree() {
		$.fn.zTree.init($("#treeFunc"), setting, zNodes);
		count();
	}
	
</script>
</head>
<body>
<c:set var="leftMenu" value="${menuId}" scope="request" />
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
	<div id="content">
            <!--fun-->
            <div class="fun">
                <ol class="fun_li clearfix">
                    <li><a id="roleFuncSub" class="btn" href="javascript:void(0);" title="保存"><span class="icon save"></span>保存</a></li>
                </ol>
            </div>
            
            <div class="title01 clearfix ml20 mr20 mt10 mb20">
                    <h1 class="b">授权管理</h1>
            </div>


            <!--auto_box-->
            <div class="auto_box">
                <div class="user_info mb20">
                    <p class="color03 pl20">角色创建时间：<fmt:formatDate value="${roles.createTime}" pattern="yyyy-MM-dd"/><span class="pl30">角色名称：${roles.roleName}</span></p>
                </div>
                <!--user_info-->
                <div class="permis_control fl"> 
					<ul id="treeFunc" class="ztree"></ul>
                </div>

            </div>
            <form id="roleFuncForm" action="${contextPath}/sys/rolefunc" method="post">
            	<input type="hidden" name="funcIds" id="funcIds" />
           		<input type="hidden" value="${roleId}" name="id" />
            </form>
            <!--auto_box-->
        </div>

    <%@include file="/WEB-INF/jsp/inc/foot.inc" %>
    <script type="text/javascript" charset="utf-8">
    	$('#roleFuncSub').live('click',function(){
    		$('#roleFuncForm').submit();
    	});
    </script>
    </body>
</html>
