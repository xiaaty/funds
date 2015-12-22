<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <#include "../../include/common_css_js.ftl">
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
<#include "../../include/menu.ftl">

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
                            <h2>商户新增</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="busiAddForm" action="${contextPath}/sys/busi/addConfirm" method="post">
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
                                                            <input type="text" name="busiName">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">商户标识：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" id="busiCode" name="busiCode">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
											<tr class="lh32">
                                                <td class="tr">IP校验方式：</td>
                                                <td>
                                                    <section style="width:250px">
                                                        <label class="text">
                                                            <input type="radio" name="authIpType" value="0" checked/>IP不校验
                                                            <input type="radio" name="authIpType" value="1" />IP校验
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">API校验方式：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
                                                            <input type="radio" name="authApiType" value="0" checked/>API不校验
                                                            <input type="radio" name="authApiType" value="1"/>API校验
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
<#include "../../include/common_footer_css_js.ftl">
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
	            $("#busiAddForm").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                        jAlert("新增成功!", '确认信息');
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
		var flag = false;
		var code = $("#busiCode").val();
		$.ajax("${contextPath}/sys/busi/checkCode",{
            async:false,
            data:{busiCode:code},
            dataType:"json",
            success:function(data){
                if(data.code=="0000"){
                	jAlert("商户标识已存在!", '提示信息');
                } else {
                	flag = true;
                }
            }
        });
        return flag;
	}
</script>

<#include "../../include/foot.ftl">
</body>

</html>