<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
<%@include file="../../../view/include/menu.jsp" %>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>商户新增</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="wid-id-641"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-add"></i> </span>
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
                                            <col width="500" />
                                            <col width="100" />
                                            <col />
                                            <tbody>
                                            <tr class="lh32">
                                                <td class="tr">商户名称：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" id="mchnName" name="mchnName" onchange="createNos();">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td class="tr">商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" id="mchnNo" name="mchnNo">
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
												                	<c:forEach items="${businessList}" var="busi">
												                    <option value="${busi.id}"> ${busi.mchnName} </option>
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
												                	<c:forEach items="${businessList}" var="busi">
												                    <option value="${busi.id}"> ${busi.mchnNo} </option>
												                	</c:forEach>
												                </select>
												                     <input type="hidden" id="parentNo" name="parentNo"  />
												            </label>
                                                        
                                                    </section>
                                                </td>
                                            </tr>
                                            
                                            <tr class="lh32">
                                                <td class="tr">状态：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
                                                            <input type="radio" name="state" value="0" checked/>未启用
                                                            <input type="radio" name="state" value="1"/>已启用
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button id="btn-success" class="btn btn-primary"  type="button">确认</button>
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
    //选中商户号
    function choice(obj){
    	var id=obj.options[obj.selectedIndex].value;
    	$("#parentN").val(id);
		var oSelect=document.getElementById("parentN");
   	     var txtOption=oSelect.options[oSelect.selectedIndex].innerHTML;//获取option中间的文本
   	 	 $("#parentNo").val(txtOption);
    	
    }
	//校验函数
	function validateCheck() {
    	var a=$("#parentId").val();
    	if(a==null||a==''){
    		jAlert("您的父商户为空，请选择父商户!", '提示信息');
    		return;
    	}
		var flag = false;
		var mchnNo = $("#mchnNo").val();
		$.ajax("${contextPath}/sys/busi/checkCode",{
            async:false,
            data:{mchnNo:mchnNo},
            dataType:"json",
            success:function(data){
                if(data.code=="0000"){
                	jAlert("商户名称已存在!", '提示信息');
                } else {
                	flag = true;
                }
            }
        });
        return flag;
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