<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../../view/include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>API列表</li>
            <li>修改API</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
     <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                            <form id="apiUpdateForm" action="${contextPath}/fss/api/updateApi" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="apiaddrUpdate" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>修改API<font class="pl10 f12 color07"></font></h2>
                                </header>
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
                                            <tr class="lh32">
                                                <td align="left">api名称：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="hidden" name="id"  value="${addr.id}">
                                                            <input type="text" name="apiName"  value="${addr.apiName}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">api编号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="apiNo" value="${addr.apiNo}" />
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">api地址：</td>
                                                <td>
                                                               <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="apiUrl"  value="${addr.apiUrl}"/>
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">是否公共API</td>
                                                 <td>
                                                    <section style="width:250px">
                                                        <label class="text">
                                                            <input type="radio" name="pulic" value="98010001" <c:if test="${addr.pulic=='98010001' }">checked </c:if> />是
                                                            <input type="radio" name="pulic" value="98010002" <c:if test="${addr.pulic=='98010002'}">checked</c:if> />否
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">创建人：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="createUserId" readonly="readonly" value="${addr.createUserId}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">创建时间：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" name="creatTime"  value="<fmt:formatDate value='${addr.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                          <tr class="lh32">
                                                <td align="left">修改人：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="modifyId" value="2">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    <div class="mb20" id="wid-id-713">
                                            <button class="btn btn-default table-nobg-btn" type="button" id="btn-success" >保存</button>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
<script src="${contextPath}/js/util/lock.js"></script>

 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    });
	    $("#btn-success").click(function () {
            bilocUtil("保存...");
	        if (validateCheck()) {
	            if (!confirm("确认 修改API吗?")) {
                    $.unblockUI();
	               return false;
	            }
	            $("#apiUpdateForm").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
                            $.unblockUI();
	                        jAlert("修改成功!", '确认信息');
	                        return;
	                    } else {
                            $.unblockUI();
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

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>