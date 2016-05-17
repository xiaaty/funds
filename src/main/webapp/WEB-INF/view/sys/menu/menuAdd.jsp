<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加菜单--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
<%@include file="../../../view/include/menu.jsp" %>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>菜单管理</li>
            <li>添加菜单</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="menuAdd"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>添加菜单</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="menuAddForm" action="${contextPath}/sys/menu/addMenu" method="post">
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
                                                <td class="tr">菜单名称：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" name="menuName">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">菜单链接：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" id="menuUrl" name="menuUrl">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
											<tr class="lh32">
                                                <td class="tr">上级菜单：</td>
                                                <td>
                                                <section style="width:210px">
                                                    <label class="select">
												                <select id="applyType" name ="parent_id">
												                <c:forEach items="${menus}" var="menus">
												                    <option value="${menus.id}">${menus.menuName}</option>
												                </c:forEach>
												                </select>
												            </label>
												             </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">排序(只能输数字)：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
<!--                                                             <input type="text" name="sort" id="sort" onblur="checked()"/> -->
                                                       <input onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" id="order" NAME="sort"/>
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
	    $("#btn-success").click(function () {
	    	var sort=$("#order").val();
	    	alert(sort);
	    	if(sort<4294967295){
	    		
	            $("#menuAddForm").ajaxSubmit({
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
	    	}else{
	    		alert("您输入的数字太大了呦亲");
// 	    		$("#sort").focus();
	    	}
	    });
    });


</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>