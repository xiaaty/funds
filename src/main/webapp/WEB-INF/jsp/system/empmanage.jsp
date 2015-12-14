<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
   <%@page import="com.gqhmt.util.Auth"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </style>

</head>

<body>
 
    <%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<%
	boolean isAdd=Auth.checkAuth(request,"/sys/addemp");
	boolean isUp=Auth.checkAuth(request,"/sys/upempstatus");
	boolean isAssignAuth=Auth.checkAuth(request,"/sys/assignauth");
	boolean isReset=Auth.checkAuth(request,"/sys/resetpwd");
%>
    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>用户管理</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="empForm" action="${contextPath}/sys/empmanage" method="post" >
                              
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr">员工编号：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="employeeNo" value="${sysUsers.employeeNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr">姓名：</td>
                                                        <td>
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="userName" value="${sysUsers.userName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr">所属部门:</td>
                                                        <td>
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="department" value="${sysUsers.department}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tr">直接上司：</td>
                                                        <td>
                                                            <label class="input" style="width:210px">
                                                                <input type="text" name="leader" value="${sysUsers.leader}">
                                                            </label>
                                                        </td>
                                                        <td class="tr">账户状态：</td>
                                                        <td>
                                                            <label class="select" style="width:210px">
                                                                <select name="isDel" >
                                                                   <option value="0">不限</option>
                                                                    <option value="1"  ${sysUsers.isDel == 1?"selected":""}>启用</option>
                                                                    <option value="-1"  ${sysUsers.isDel == -1?"selected":""}>禁用</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr">所属机构:</td>
                                                        <td>
                                                            <label class="input" style="width:210px">
                                                                <input type="text" value="${sysUsers.company}" name="company">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>


                        </div>
                        <!-- 
							 -->
                        <!-- NEW WIDGET START -->
                        <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                        <div class="jarviswidget jarviswidget-color-darken" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa-fw fa fa-table"></i> </span>
                                <h2>用户管理列表</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form">
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body">
                                   
                                        <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        
                                           
                                             <button class="btn btn-default fl table-nobg-btn" type="button" id="btn_add"><i class="fa fa-plus"></i>&nbsp;新增用户</button>
                                    
                                        </div>
                          
                                        <table id="borrow-rep-table1" class="table table-bordered" style="text-align:center;">
                                            <col width="270" />
                                            <col />
                                            <thead>
                                                <tr class="fb">
                                                
                                                    <td>操作</td>
                                                    <td>员工编号</td>
                                                    <td>姓名</td>
                                                    <td>登录名</td>
                                                    <td>性别</td>
                                                    <td>所属机构</td>
                                                    <td>部门</td>
                                                    <td>直接上司</td>
                                                    <td>岗位</td>
                                                    <td>电话</td>
                                                    <td>账户状态</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                      <c:forEach var="user" items="${page.list}">
                                            <tr>
                                             <td class="tc">
                                                    <c:if test="${!func:isInString(user.roleId,superRoleId)}">
                                                        <div class="n_b">
                                                            <% if(isUp){ %>
                                                            <a href="${contextPath}/sys/upempstatus?id=${user.id}" class="icon ${user.isDel==1?"btn06":"btn07"} mr15" title="${user.isDel==1?"禁用账户":"启用账户" }"><span>${user.isDel==1?"禁用账户":"启用账户" }</span></a>
                                                            <% } %>
                                                            <% if(isAdd){ %>
                                                            <a href="${contextPath}/sys/addemp?id=${user.id}" class="btn05 icon mr15" title="编辑"><span>编辑</span></a>
                                                            <% } %>
                                                            <% if(isReset){ %>
                                                            <a  href="#" class="btn04 icon mr15  reset" title="重置密码"  onclick="resetPSw(${user.id},'${user.loginName}')"><span>重置密码</span></a>
                                                            <% } %>
                                                    
                                                        </div>
                                                    </c:if>
                                                </td>
                                            
                                                <td class="tl">${user.employeeNo}</td>
                                                <td>${user.userName}</td>
                                                <td>${user.loginName}</td>                                          
                                                <td>${user.sexName}</td>
                                                <td>${user.company}</td>
                                                <td>${user.department}</td>
                                                <td>${user.leader}</td>
                                                <td>${user.job}</td>
                                                <td>${user.userTel}</td>
                                                
                                                <td>${user.isDel==1?"正常":"禁用"}</td>
                                               
                                            </tr>
                                        </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>
                        </div>
                    </article>
                </div>
            </section>
        </div>

    </div>

    
<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>

    <script>
        $(document).ready(function () {
        	pageSetUp();

            DT_page("borrow-rep-table1",true,'${page.JSON}',$("#empForm"));
            //添加按钮按下
            $("#btn_add").click(function() {
        	window.open("${contextPath}/sys/addemp","_self");
             // window.location.href = '${contextPath}/sys/addemp';
            });
   
       
        });
        function resetPSw(t,name){
            $.post("${cxt}/sys/resetpwd?",{'id':t,"login":name},function(data){

                alert("重置成功!");

                return false;

            });
        }
       
    </script>

</body>

</html>
