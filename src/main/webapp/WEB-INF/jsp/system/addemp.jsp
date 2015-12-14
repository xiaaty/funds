<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>内审系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
   
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
    <style>
        .table-nobg-btn {
            font: 15/29px;
            height: 31px;
            line-height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }
        .button-icon i{
        line-height:32px;
        }
    </style>

</head>

<body>
    
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>个人信息</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="empForm" action="${contextPath}/sys/addemp" method="post">
                       <input type="hidden" value="${emp.id}" name="id"  default="0"/>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                            <div class="alert alert-info fade in clearfix" id="wid-id-712">
                              <p class="color03 pl20">创建：<fmt:formatDate value="${emp.createTime}" pattern="yyyy-MM-dd"/><span class="pl30">创建者：<c:out value="${userMap[emp.createId].userName}" /><M></span><span class="pl30">修改：<fmt:formatDate value="${emp.modifyDate}" pattern="yyyy-MM-dd"/></span><span class="pl30">修改者：<c:out value="${userMap[emp.modifyId].userName}" default="无" /></span></p>
                             <li><span id="mess" style="color:red;">${message}</span></li>
                            </div>

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>个人信息<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td align="left">员工编号：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="employeeNo" value="${emp.employeeNo}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                            <td align="left">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="userName" value="${emp.userName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">登&nbsp;&nbsp;录&nbsp;&nbsp;名：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="20" name="loginName"  value="${emp.loginName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                            <td align="left">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <select style="width:256px;" name="sex" value="${emp.sex}">
                                                                        <option value="1">男</option>
                                                                        <option value="2">女</option>
                                                                    </select>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">部门/分公司：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input name="department" maxlength="50" value="${emp.department}" style="width:256px;">
                                                                </label>
                                                            </td>
                                                            <td align="left">所属机构：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="15" name="company" value="${emp.company}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">岗位/职务：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="job" maxlength="15" value= "${emp.job}"   style="width:256px;" />
                                                                </label>
                                                            </td>
                                                            <td align="left">直接上级：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="leader" maxlength="15"   value= "${emp.leader}"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="11" name="userTel"  style="width:256px;" value= "${emp.userTel}" />
                                                                </label>
                                                            </td>
                                                            <td align="left">账户状态：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <select style="width:256px;" name="status"  value= "${emp.status}" disabled="disabled">
                                                                        <option value="1">启用</option>
                                                                        <option value="2">禁用</option>

                                                                    </select>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
                                                            <td colspan="3">
                                                                <textarea style="width:256px" rows="4" cols="37" class="textarea" maxlength="150" name="description"  >${emp.description}</textarea>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="addemp"><i class="fa  fa-clipboard mr5"></i>保存</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </form>

                </div>
            </section>
        </div>
    </div>

    <%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
    <script type="text/javascript" charset="utf-8">
		$(document).ready(function () {
	    	pageSetUp();
	    });
        $('#addemp').click(function(){
                if(checkForm())
        		$('#empForm').submit();
        });
        function checkForm(){
        	var flag=true;
        	$('#empForm :input').each(function(){
        		if(!$(this).val()){
        			if($(this).closest('td').prev('td').html()!=null){
        				$('#mess').html($(this).closest('td').prev('td').html().replace(/[a-z0-9*<>/=&;："]+/gi,'')+"不能为空");
            			$(this).focus();
            			flag=false;
            			return false;
        			}
        		
        		
        		}
        	});
        	return flag;
        }
        
    	function btn_back(){
    		 
        	window.open("${contextPath}/sys/empmanage?","_parent");
        }
        </script>
</body>

</html>