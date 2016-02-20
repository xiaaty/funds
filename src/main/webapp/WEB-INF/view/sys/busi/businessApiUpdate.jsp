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
            <li>修改商户API</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="wid-id-641"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>修改商户API的授权</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="busiApiUpdateForm" action="${contextPath}/fss/api/updateBusinessApi" method="post">
                                <input type="hidden" value="${id}" name="id"/>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body no-padding">
                                    <div class="mt10 mb10">
                                        <table class="table">
                                            <col width="100" />
                                            <col width="220" />
                                            <col width="100" />
                                            <col />
                                            <tbody>
                                            <tr class="lh32">
                                                <td class="tr">商户名称：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text"  readonly="readonly" value="${busiApi.mchnName}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="mchnNo" readonly="readonly" value="${busiApi.mchnNo}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">api名称：</td>
                                                <td>
                                                    <label class="select"  style="width:150px" >
                                                    	<input type="hidden" value="${busiApi.apiNo}" id="no">
                                                                <select class="select02"  name="apiNo" id="a">
                                                                《												<c:forEach items="${apiList}" var="api">
								                                    <option value="${api.apiNo }">${api.apiName}</option>
                                                                </c:forEach>
								                                </select>
                                                            </label>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">api地址：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="apiUrl" readonly="readonly" value="${busiApi.apiUrl}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                           <tr class="lh32">
                                                <td class="tr">回调方式：</td>
                                                <td>
                                                    <section style="width:250px">
                                                        <label class="text">
                                                            <input type="radio" name="isReturn" value="0" <c:if test="${busiApi.isReturn == '0'}"> checked </c:if> />回调
                                                            <input type="radio" name="isReturn" value="1" <c:if test="${busiApi.isReturn =='1'}"> checked </c:if> />不回调
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                           
                                            <tr class="lh32">
                                                <td class="tr">回调地址：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="returnUrl" value="${busiApi.returnUrl}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
											
                                            <tr class="lh32">
                                                <td class="tr">授权时间：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" name="creatTime"  value="<fmt:formatDate value='${busiApi.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">最后修改时间：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly"  value="<fmt:formatDate value='${busiApi.modifyTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button id="btn-success" class="btn btn-primary" type="button">确认</button>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>


                    </div>
            <!-- end widget content -->
<%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    	$("#a").val($("#no").val());
    });
	    $("#btn-success").click(function () {
	        if (validateCheck()) {
	            if (!confirm("确认 修改商户API信息吗?")) {
	               return false;
	            }
	            $("#busiApiUpdateForm").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                        jAlert("修改成功!", '确认信息');
	                        return;
	                    } else {
	                    	jAlert("修改失败!", '确认信息');
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