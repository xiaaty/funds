<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
 <%@include file="../../../view/include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>商户列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="wid-id-641"  data-widget-deletebutton="false" data-widget-editbutton="false">
        <header>
            <h2>商户IP修改</h2>
        </header>
        <!-- widget div-->
	        <form class="smart-form" id="ipUpdateForm" action="${contextPath}/sys/busi/ipUpdateConfirm" method="post">
            	<input type="hidden" name="busiCode" value="${busiCode}">
		        <div class="jarviswidget-editbox">
                    <!-- This area used as dropdown edit box -->
                </div>
		        <div class="widget-body no-padding">
		        	<div class="mt10 mb10">
                        <table class="table" id="ip_table">
                            <col width="100" /><col width="100" />
                        	<tbody>
			            	<c:forEach items="${apiIpList}" var="ip">
                        		<tr class="lh32">
                                	<td class="tr">IP地址：</td>
                                    <td>           	
						      			<input type="text" name="ipAddress" value="${ip.ipAddress}" placeholder="请输入ip">
						      			修改日期：      	
						      			<input type="text" value="<fmt:formatDate value='${ip.modifyTime}' pattern='yyyy-MM--dd HH:mm:ss'/>" readonly="readonly" />
						      			<input type="hidden" name="mchnNo" value="${mchnNo}" >
						      		</td>
						      	</tr>
			            	</c:forEach>
	             			</tbody>
	             		</table>
	             	</div>
		            <footer>
		                <button id="btn-success" class="btn btn-primary" type="button">保存</button>
		                <button id="btn-delete" class="btn btn-primary" type="button">删除</button>
		                <button id="btn-add" class="btn btn-primary" type="button">新增</button>
		            </footer>
			     </div>
	        </form>
     </div>
            <!-- end widget content -->
 <%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
	    $("#btn-success").click(function () {
            $("#ipUpdateForm").ajaxSubmit({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    if (data.code == '0000') {
                        jAlert("修改成功!", '确认信息');
                        return;
                    } else {
                        return;
                    }
                }
            });
	    });
	    $("#btn-add").click(function () {
	    	var table1 = $("#ip_table");
            var firstTr = table1.find("tbody>tr:last");
            var row = $("<tr class='lh32'><td class='tr'>IP地址：</td></tr>");
            var td_1 = $("<td></td>");
            td_1.append($("<input type='text' name='ipAddress' placeholder='请输入ip'/><input type='hidden' name='mchnNo' value='${mchnNo}'/>"));
            row.append(td_1);
            table1.append(row);
	    });
	    // 删除最后一行
	    $("#btn-delete").click(function () {
	    	var table1 = $("#ip_table");
            var firstTr = table1.find("tbody>tr:last");
            firstTr.remove();
	    });
    });
	//校验函数
	function validateCheck() {
		return true;
	}
</script>

 <%@include file="../../../view/include/foot.jsp"%>
</body>

</html>