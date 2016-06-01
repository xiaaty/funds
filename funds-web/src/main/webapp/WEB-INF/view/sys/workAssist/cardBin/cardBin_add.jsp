<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加银行卡bin信息--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../include/common_css_js.jsp"%>
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
    
<%@include file="../../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>系统配置</li>
                <li>银行卡卡bin管理</li>
                <li>添加</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="cardBInForm" action="${contextPath}/fss/customer/saveCardBin" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="cardBinAdd" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>新增银行卡bin信息<font class="pl10 f12 color07"></font></h2>
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
                                                            <td align="left">发卡行名称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50"  name="bankName"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">机构代码：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="8" name="organCode"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="cardName" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">长&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="length" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">取&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;值：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="takeValue" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="cardType" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="addCardBin">保存</button>
                                                    <button class="btn btn-primary table-nobg-btn" type="button" onclick="location.href='${contextPath}/fss/customer/cardBinList'">返&nbsp;&nbsp;&nbsp;回</button>
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

<%@include file="../../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#addCardBin").click(function () {
    	        if (validateCheck()) {
    	            $("#cardBInForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("添加成功!", '信息提示');
    	                        //自动跳转
    	                        parent.location.href="${contextPath}/fss/customer/cardBinList";
    	                    } else {
    	                    	jAlert("添加失败", '消息提示');
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