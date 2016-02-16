<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <li>商户列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="wid-id-641"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>商户修改</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="busiUpdateForm" action="${contextPath}/sys/busi/updateConfirm" method="post">
                                <input type="hidden" value="${busi.id}" name="id"/>
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
                                                            <input type="text" id="mchnName" name="mchnName" value="${busi.mchnName}" onchange="createNos();">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" id="mchnNo" name="mchnNo" value="${busi.mchnNo}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            
                                            <tr class="lh32">
                                                <td class="tr">父商户：</td>
                                                <td>
                                                    <section style="width:210px">
                                                    <label class="select">
												                <select id="parentId"  name ="parentId" onChange="choice(this)">
												                    <option  value="">--请选择--</option>
												                	<c:forEach items="${businessList}" var="busin">
												                    <option value="${busin.id}" <c:if test="${busi.parentId ==busin.id}">selected='selected'</c:if> > ${busin.mchnName} 
												                    </option>
												                	</c:forEach>
												                </select>
												            </label>
                                                        
                                                    </section>
                                                </td>
                                            </tr>
                                            
											<tr class="lh32">
                                                <td class="tr">父商号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                    <label class="select">
												                <select id="parentN"  disabled="disabled">
												                <option value=""></option>
												                	<c:forEach items="${businessList}" var="busin">
												                    <option value="${busin.id}" <c:if test="${busi.parentId ==busin.id}">selected='selected'</c:if>> ${busin.mchnNo} </option>
												                	</c:forEach>
												                </select>
												                     <input type="hidden" id="parentNo" name="parentNo" value="${busi.parentNo}"/>
												            </label>
                                                        
                                                    </section>
                                                </td>
                                            </tr>
                                            
											<tr class="lh32">
                                                <td class="tr">IP校验方式：</td>
                                                <td>
                                                    <section style="width:250px">
                                                        <label class="text">
                                                            <input type="radio" name="authIp" value="0" <c:if test="${busi.authIp ==0}">checked</c:if> />IP不校验
                                                            <input type="radio" name="authIp" value="1" <c:if test="${busi.authIp ==1}">checked</c:if>  />IP校验
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">API校验方式：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
                                                            <input type="radio" name="authApi" value="0" <c:if test="${busi.authApi ==0}">checked</c:if>/>可以访问所有公共API
                                                            <input type="radio" name="authApi" value="1" <c:if test="${busi.authApi ==1}">checked</c:if>/>除公共API外，可以访问授权API
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">状态：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
                                                            <input type="radio" name="state" value="0" <c:if test="${busi.state ==0}">checked</c:if>/>未启用
                                                            <input type="radio" name="state" value="1" <c:if test="${busi.state ==1}">checked</c:if>/>已启用
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button id="btn-success" class="btn btn-primary"  type="button">修改</button>
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
//     	$("#parentN").hide();
	    $("#btn-success").click(function () {
	        if (validateCheck()) {
	            if (!confirm("确认 修改商户信息吗?")) {
	               return false;
	            }
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
    //选中商户号
    function choice(obj){
    	var id=obj.options[obj.selectedIndex].value;
    	$("#parentN").val(id);
		var oSelect=document.getElementById("parentN");
   	     var txtOption=oSelect.options[oSelect.selectedIndex].innerHTML;//获取option中间的文本
//    	  txtOption= txtOption.substring(0,(txtOption.length-2));
   	 	 $("#parentNo").val(txtOption);
//    	     alert( $("#parentNo").val());
    	
    }
	//校验函数
	function validateCheck() {
        return true;
	}
	//生成商户号
	function createNos(){
		var a=("0000000" + 100000000 * Math.random()).match(/(\d{8})(\.|$)/)[1];
		var result = '';
		for(var i=0;i<4;i++){
		var ranNum = Math.ceil(Math.random() * 25); //生成一个0到25的数字
		//大写字母'A'的ASCII是65,A~Z的ASCII码就是65 + 0~25;然后调用String.fromCharCode()传入ASCII值返回相应的字符并push进数组里
		result+=String.fromCharCode(65+ranNum);
		}
		var mchnNo=	a.toString()+result;
		$("#mchnNo").val(mchnNo);
	}
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>