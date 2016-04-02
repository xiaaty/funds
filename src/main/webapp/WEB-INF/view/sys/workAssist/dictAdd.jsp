<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加字典--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../include/common_css_js.jsp"%>
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
    
<%@include file="../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>字典表</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="dictForm" action="${contextPath}/sys/dictionary/${parent_id}/add/save" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>新增字典信息<font class="pl10 f12 color07"></font></h2>
                                </header>
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
                                                            <td align="left">上级目录：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" readonly="readonly" name="parentId" value="${dict.parentId}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="8" name="dictId" value="${dict.dictId}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="dictName" value="${dict.dictName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">是否有效：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <select style="width:256px;" name="isValid" value="${dict.isValid}">
                                                                    <%--
                                                                        <option value="0">是</option>
                                                                        <option value="1">否</option>
                                                                     --%>
                                                                        <fss:dictOrder var="order" dictOrder="isValid">
                                                                            <option value="${order.key}">${order.value}</option>
                                                                        </fss:dictOrder>
                                                                    </select>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">是否最后一级：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <fss:dictOrder var="order" dictOrder="yesOrNo">
                                                                        <%--<option value="${order.key}">${order.value}</option>--%>
                                                                        <input type="radio" name="isEnd" value="${order.key}" id="t_${order.key}" <c:if test="${order.key == 98010002}">checked</c:if> ><label for="t_${order.key}">${order.value}</label>
                                                                        &nbsp;&nbsp;&nbsp;
                                                                    </fss:dictOrder>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="adddictmain">保存</button>
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

<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#adddictmain").click(function () {
    	        if (validateCheck()) {
    	            $("#dictForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("添加成功!", '信息提示');
    	                        //自动跳转
    	                        var parent_id=data.parentid;
    	                        parent.location.href="${contextPath}/sys/dictionary/${parent_id}";
    	                    } else {
    	                    	jAlert("添加失败,该编号已经存在,请勿重复添加!", '消息提示');
    	                        return;
    	                    }
    	                }
    	            });
    	        }
    	    });
        });
    	//校验函数
    	function validateCheck() {
    		return true;
    	}
        
        
        
        
        
        
        
        </script>
        
        
        
        
        
</body>

</html>