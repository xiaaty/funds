<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c0" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>转账申请--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>交易管理</li>
            <li>转账申请</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="wid-id-641"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>转账申请</h2>
                        </header>
	
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="transApplyForm" action="${contextPath}/fss/trade" method="post">
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
                                                <td class="tr">id：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="id" value="id">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">交易申请编号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="applyNo" value="apply_no">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">交易类型：</td>
                                                <td>
                                                    <label class="select">
												                <select id="applyType" name ="applyType">
												                    <option value="1">充值</option>
												                    <option value="2">提现</option>
												                </select>
												            </label>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">客户编号：</td>
                                                <td>
												            <label class="select">
												             <input type="text" name="custNo" value="custNo">
												            </label>
												        </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">用户编号：</td>
                                                <td>
												            <label class="select">
												             <input type="text" name="userNo" value="userNo">
												            </label>
												        </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">业务编号：</td>
                                                <td>
												            <label class="select">
												             <input type="text" name="businessNo" value="businessNo">
												            </label>
												        </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">业务类型：</td>
                                                <td>
                                                    <label class="select">
												                <select id="busiType" name ="busiType">
												                    <option value="1">借款</option>
												                    <option value="2">出借</option>
												                    <option value="3">冠e通</option>
												                </select>
												            </label>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">账户账号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="accNo" value="accNo">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">交易金额：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="tradeAmount" value="tradeAmount">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">实际交易金额：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="realTradeAmount" value="realTradeAmount">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            
                                            <tr class="lh32">
                                                <td class="tr">交易手续费：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="tradeChargeAmount" value="tradeChargeAmount">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>

                                           <tr class="lh32">
                                                <td class="tr">交易状态：</td>
                                                <td>
                                                    <label class="select">
												                <select id="tradeState" name ="tradeState">
												                    <option value="1">未交易</option>
												                    <option value="2">部分成功</option>
												                    <option value="3">成功</option>
												                    <option value="4">失败</option>
												                </select>
												            </label>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                 <td class="tr">申请状态：</td>
                                                <td>
												            <label class="select">
												                <select id="applyState" name ="applyState">
												                    <option value="1">新增</option>
												                    <option value="2">审核成功</option>
												                    <option value="2">已交易</option>
												                    <option value="2">已回调通知</option>
												                </select>
												            </label>
												        </td>
                                            </tr>
	
                                            <tr class="lh32">
                                                <td class="tr">录入时间：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="createTime" value="createTime">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">最后修改时间：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="modifyTime" value="modifyTime">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>

                                            <tr class="lh32">
                                                <td class="tr">大商户好：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="mchnParent" value="mchn_parent">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">子商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="mchnChild" value="mchnChild">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">api业务交易流水号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="seqNo" value="seqNo">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button id="btn-success" class="btn btn-primary" type="button">申请</button>
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
	    $("#btn-success").click(function () {
	        if (validateCheck()) {
	            /*if (!confirm("确认 修改商户信息吗?")) {
	               return false;
	            }*/
	            $("#busiUpdateForm").ajaxSubmit({
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
	        }
	    });
    });
	//校验函数
	function validateCheck() {
		return true;
	}
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html></html>