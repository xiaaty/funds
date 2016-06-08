<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>代付审核--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>交易审核</li>
            <li>代付审核</li>
            <li>抵押权人代扣</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
 <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="withHoldForm" action="${contextPath}/loan/trade/${type}/withHold/${id}" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="lWithDraw" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>抵押权人代扣<font class="pl10 f12 color07"></font></h2>
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
                                            <tr class="lh32">
                                                <td align="left">合同编号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
<%--                                                         	<input type="hidden" name="token" value="${token}"/>  --%>
                                                            <input type="text" name="contractNo" readonly="readonly" value="${loan.contractNo }">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <c:if test="${loan.tradeType=='11090005'}">

                                                    <td align="left">抵押权人客户id：</td>
                                                </c:if>
                                                <c:if test="${loan.tradeType=='11090001'}">
                                                <td align="left">抵押权人资金平台账号：</td>
                                                </c:if>
                                                <td>
                                                 <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="mortgageeAccNo" readonly="readonly" value="${loan.mortgageeAccNo }">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">客户姓名：</td>
                                                <td>
												            <label class="input" style="width:210px">
												            <input type="hidden" name="userNo" readonly value="${userName}">
<%-- 												             <input type="text" name="" readonly="readonly" value="${userName }"> --%>
												            </label>
												</td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">金额(<span style="color:blue">可修改</span>)：</td>
                                                <td>
												            <label class="input" style="width:210px">
												             <input type="text" name="payAmt" value="${loan.contractAmt }">
												            </label>
												        </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">交易类型：</td>
                                                <td>
                                                <section style="width:210px">
                                                <label class="input" >
                                                     <input type="text" name="" readonly="readonly" value=" <fss:dictView key='${loan.tradeType}' />">
                                                     <input type="hidden" name="tradeType" readonly="readonly" value="${order.key}">
                                                   	 </label>
                                                   	  </section>
                                                </td>
                                            </tr>

                                            </tbody>
                                        </table>
                                         <div class="mb20" id="wid-id-713">
 													<button id="btn-success" class="btn btn-primary table-nobg-btn" type="button">保存</button>
                                                    <button class="btn btn-default table-nobg-btn" onclick="loaction.href='${contextPath}//loan/trade/${type}'" type="button" >取消</button>
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


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
	    $("#btn-success").click(function () {
	        if (validateCheck()) {
	            /*if (!confirm("确认 修改商户信息吗?")) {
	               return false;
	            }*/
	            $("#withHoldForm").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                        jAlert("代扣已提交!", '确认信息');
                            parent.location.href="${contextPath}/loan/trade/11090001";
	                    } else if(data.code == '0001'){
	                    	jAlert(data.message, '确认信息');
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

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html></html>