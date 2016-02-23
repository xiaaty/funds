<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c0" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../../view/include/common_css_js.jsp"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
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
        #footer{position: absolute;bottom: 10px;z-index: 100px;}
        .footer-bottom{font-size:13px}
        .footer-bottom ul>li{padding:0}
        .footer-bottom ul>li+li:before{padding:0 10px;color:#ccc;content:"|"}
    </style>

</head>

<body>
<%@include file= "../../../view/include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>商户API列表</li>
            <li>商户API授权</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
 <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                            <form  id="busiApiInsertForm" action="${contextPath}/fss/api/insertBusinessApi" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-save pr10"></i>商户API授权<font class="pl10 f12 color07"></font></h2>
                                </header>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                            <tbody>
                                            <tr class="lh32">
                                                <td align="left">商户名称：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text"  readonly="readonly" value="${mchnName}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="mchnNo" readonly="readonly" value="${mchnNo}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">api名称：</td>
                                                <td>
                                                    <label class="select"  style="width:150px" >
                                                                <select class="select02"  name="apiNo" id="a">
                                                               											<c:forEach items="${apiList}" var="api">
								                                    <option value="${api.apiNo }">${api.apiName}</option>
                                                                </c:forEach>
								                                </select>
                                                            </label>
                                                </td>
                                            </tr>
                                           <tr class="lh32">
                                                <td align="left">回调方式：</td>
                                                <td>
                                                    <section style="width:250px">
                                                        <label class="text">
                                                            <input type="radio" name="isReturn" value="98010001"  />回调
                                                            <input type="radio" name="isReturn" value="98010002"  checked />不回调
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                           
                                            <tr class="lh32">
                                                <td align="left">回调地址：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="returnUrl" value="${busiApi.returnUrl}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="mb20" id="wid-id-713">
                                        	<button id="btn-success" class="btn btn-default table-nobg-btn" type="button">保&nbsp;&nbsp;&nbsp;存</button>
                                     		<button class="btn btn-primary table-nobg-btn" type="button" onclick="location.href='${contextPath}/sys/busi/list/${parentId}'">返&nbsp;&nbsp;&nbsp;回</button>
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
            <!-- end widget content -->
<%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    });
	    $("#btn-success").click(function () {
	        if (validateCheck()) {
	            if (!confirm("确认 添加商户的API权限吗?")) {
	               return false;
	            }
	            $("#busiApiInsertForm").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                        jAlert("添加成功!", '确认信息');
	                        return;
	                    } else {
	                    	jAlert("添加失败!", '确认信息');
	                        return;
	                    }
	                }
	            });
	        }
	    });
	//校验函数
	function validateCheck() {
		return true;
	}
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>