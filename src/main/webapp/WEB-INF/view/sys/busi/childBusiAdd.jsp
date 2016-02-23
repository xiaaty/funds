<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加商户--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../include/common_css_js.jsp"%>
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
    
<%@include file="../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
               <li>商户管理</li>
            	<li>子商户新增</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="busiAddForm" action="${contextPath}/sys/busi/addConfirm" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-save pr10"></i>子商户新增<font class="pl10 f12 color07"></font></h2>
                                </header>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td align="left">商户名称：</td>
                                                            <td>
                                                             <section style="width:210px">
                                                                <label class="input">
                                                                    <input type="text" id="mchnName" name="mchnName" onchange="createNos();">
                                                                </label>
                                                                </section>
                                                            </td>
                                                        </tr>
                                                          <tr class="lh32">
                                                
                                                        <tr class="lh32">
                                                <td align="left">商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" id="mchnNo" name="mchnNo">
                                                            <input type="hidden" readonly="readonly" id="mchnKey" name="mchnKey">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tr>
                                            
                                            <tr class="lh32">
                                                <td align="left">父商户：</td>
                                                <td>
                                                    <section style="width:210px">
                                                    <label class="input">
												                	<input type="hidden" name="parentId" value="${busi.id} ">
												                	<input type="text"  value="${busi.mchnName} ">
												            </label>
                                                        
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">父商号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                    <label class="input">
												                	<input type="text" name="parentNo" value="${busi.mchnNo} ">
												            </label>
                                                        
                                                    </section>
                                                </td>
                                            </tr>
                                            
                                            <tr class="lh32">
                                                <td align="left">状态：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
                                                            <input type="radio" name="state" value="98040002" checked/>未启用
                                                            <input type="radio" name="state" value="98040001"/>已启用
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                <!-- end widget content -->
                                      <div class="mb20" id="wid-id-713">
                                            <button class="btn btn-default table-nobg-btn" type="button" id="addChildBusi">保&nbsp;&nbsp;&nbsp;存</button>
                                            <button class="btn btn-primary table-nobg-btn" type="button" onclick="location.href='${contextPath}/sys/busi/list/${parentId}'">返&nbsp;&nbsp;&nbsp;回</button>
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

<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
//     	$("#parentN").hide();
	    $("#addChildBusi").click(function () {
	        if (validateCheck()) {
	            if (!confirm("确认 添加商户信息吗?")) {
	               return false;
	            }
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
//     //选中商户号
//     function choice(obj){
//     	var id=obj.options[obj.selectedIndex].value;
//     	$("#parentN").val(id);
// 		var oSelect=document.getElementById("parentN");
//    	     var txtOption=oSelect.options[oSelect.selectedIndex].innerHTML;//获取option中间的文本
//    	 	 $("#parentNo").val(txtOption);
    	
//     }
	//校验函数
	function validateCheck() {
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
		
		var data=["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]; 
		var mchnKey="";
		for(var i=0;i<20;i++){ //产生20位就使i<20
		var r=Math.floor(Math.random()*36); //16为数组里面数据的数量，目的是以此当下标取数组data里的值！ 
		mchnKey+=data[r]; //输出20次随机数的同时，让rrr加20次，就是20位的随机字符串了。 
		} 
		$("#mchnKey").val(mchnKey);
	}
</script>
        
        
        
        
        
</body>

</html>