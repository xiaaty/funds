<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.gqhmt.util.Auth"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>冠群驰骋查账系统-用户管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
        <style>
        .table-nobg-btn{
            font:15/29px;
            height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }

        .table > tbody > tr.table_input > td {
            padding: 4px;
            vertical-align: middle;
        }

        .table > tbody > tr.table_input > td .input input {
            height: 26px;
            line-height: 20px;
            padding: 3px;
            vertical-align: middle;
        }

        .table > tbody > tr.table_input > td .select select{
            height: 26px;
            line-height: 20px;
            padding:3px;
            vertical-align: middle;
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
<c:set var="leftMenu" value="${menuId}" scope="request" />



<!--------LEFT SIDER---------->


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>用户管理</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <div class="row">
            <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                <h4 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-cog"></i> 用户管理 </h4>
            </div>
        </div>
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
                            <form class="smart-form" id="empForm">
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
                                            <col width="300" />
                                            <col width="100" />
                                            <col />
                                            <tbody>
                                            <tr >
                                                <td class="lh32 tr">编号:</td>
                                                <td>
                                                    <label class="input" style="width:210px">
                                                        <input type="text" name="employeeNo" />
                                                    </label>
                                                </td>
                                                <td class="lh32 tr">姓名:</td>
                                                <td>
                                                    <label class="input" style="width:300px">
                                                        <input type="text" name="userName"/>
                                                    </label>
                                                </td>
                                                <td class="lh32 tr">所属部门:</td>
                                                <td>
                                                    <label class="input" style="width:210px">
                                                        <input type="text" name="department"/>
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr >
                                                <td class="lh32 tr">直接上司:</td>
                                                <td>
                                                    <label class="input" style="width:210px">
                                                        <input type="text" name="leader"/>
                                                    </label>
                                                </td>
                                                <td class="lh32 tr">状态:</td>
                                                <td>
                                                    <label class="select">
                                                        <select  name="status">
                                                            <option value="0">--不限--</option>
                                                            <c:forEach var="status" items="${statusMap}">
                                                                <option value="${status.key}" ${search.status==status.key?"selected":""}>${status.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </label>
                                                </td>
                                                <td colspan="2"></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button class="btn btn-primary" type="button">查&nbsp;&nbsp;&nbsp;询</button>
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
                    <div class="jarviswidget jarviswidget-color-darken" id="wid-id-12"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
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
                                        <button class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                    </div>
                                    <table id="borrow-rep-table1" class="table table-bordered tc">
                                        <thead>
                                        <tr class="b">
                                            <th class="tl">编号</th>
                                            <th>姓名</th>
                                            <th>登录名</th>
                                            <th>角色</th>
                                            <th>性别</th>
                                            <th>所属机构</th>
                                            <th>部门</th>
                                            <th>直接上司</th>
                                            <th>岗位</th>
                                            <th>电话</th>
                                            <th>状态</th>
                                            <th>是否禁用</th>
                                            <th class="tc">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="f12">
                                        <c:forEach var="user" items="${page.list}">
                                            <tr>
                                                <td class="tl">${user.employeeNo}</td>
                                                <td>${user.userName}</td>
                                                <td>${user.loginName}</td>
                                                <td>${user.roleName}</td>
                                                <td>${user.sexName}</td>
                                                <td>${user.company}</td>
                                                <td>${user.department}</td>
                                                <td>${user.leader}</td>
                                                <td>${user.job}</td>
                                                <td>${user.userTel}</td>
                                                <td>${statusMap[user.status]}</td>
                                                <td>${user.isDel==1?"正常":"禁用"}</td>
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
                                                            <a  href="${contextPath}/sys/uppwd?id=${user.id}&login=${user.loginName}" class="btn04 icon mr15  reset" title="重置密码"><span>重置密码</span></a>
                                                            <% } %>
                                                            <% if(isAssignAuth){ %>
                                                            <a href="${contextPath}/sys/assignauth?id=${user.id}" class="btn08 icon" title="角色分配"><span>角色分配</span></a>
                                                            <% } %>
                                                        </div>
                                                    </c:if>
                                                </td>
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
<script src="${contextPath}/js/jquery.form.js" ></script>

<script>
    $(document).ready(function() {
        pageSetUp();

       // DT_page("borrow-rep-table1",true,false,false);
        DT_page("borrow-rep-table1",true,'${page.JSON}',$("#empForm"));
        /*
         * CONVERT DIALOG TITLE TO HTML
         * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
         */

        var add_dialog = $("#upload").dialog({
            autoOpen : false,
            width : 600,
            resizable : false,
            modal : true,
            buttons : [{
                html : "确&nbsp;&nbsp;&nbsp;&nbsp;认",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");

                }
            }, {

                html : "取&nbsp;&nbsp;&nbsp;&nbsp;消",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });


        $("#btn_upload").button().click(function() {
            add_dialog.dialog("open");
            return false;
        });
        //添加按钮按下
        $("#btn_add").button().click(function() {
        	window.open("${contextPath}/sys/addemp?","_blank");
        });
        $('.selectdate').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            autoclose: 1,
            format:'yyyy-mm-dd',
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });

    });
</script>


</body>
</html>
