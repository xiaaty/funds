<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--修改定时器--冠群驰骋投资管理(北京)有限公司</title>
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
                <li>定时器设置</li>
                <li>修改定时器</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="quarztForm" action="${contextPath}/quartz/quartzUpdate" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>修改定时器<font class="pl10 f12 color07"></font></h2>
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
                                                            <td align="left">job名字：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50"  name="name" readonly="readonly" value="${quarzt.name }"  style="width:256px;" />
                                                                    <input type="hidden" maxlength="50"  name="id" value="${quarzt.id}"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">job类名：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="8" readonly="readonly" name="className" value="${quarzt.className }"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">定时cron：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="cron" value="${quarzt.cron }"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">是否有效：</td>
                                                            <td>
                                                                 <label class="select">
                                                                        <input type="radio" name="state" value="98040001" <c:if test="${quarzt.state=='98040001' }"> checked="checked"</c:if> />有效
                                                                        <input type="radio" name="state" value="98040002" <c:if test="${quarzt.state=='98040002' }"> checked="checked"</c:if>  />无效
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">执行ip：</td>
                                                            <td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="ip" readonly="readonly" value="${quarzt.ip}"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-primary table-nobg-btn" type="button" id="addQuarzt">保存</button>
                                                    <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/quartz/quartzList'">返回</button>
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
    	    $("#addQuarzt").click(function () {
    	        if (confirm("您确认修改该条信息吗？")) {
    	            $("#quarztForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                    	jAlert("修改成功!", '信息提示')
    	                        //自动跳转
//     	                        location.href="${contextPath}/quartz/quartzList";
    	                    } else {
    	                    	jAlert("修改失败!", '消息提示');
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