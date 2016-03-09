<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--修改银行卡信息--冠群驰骋投资管理(北京)有限公司</title>
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
    	<script type="text/javascript">  
		/* 	 function queryUser(){
				 if(window.ActiveXObject){ //IE  
				    	var st = window.showModalDialog("${contextPath}/fund/bankcustomerManagerChildWin?randomVal=" + Math.random(), window, "dialogWidth:1230px;status:no;dialogHeight:880px");  
				    	 
				 		if(st != null ){  
				 			  setValue(st.uid,st.realName,st.phone,st.certNo);
				        }  
				}else{  //非IE   
				    window.open("${contextPath}/fund/bankcustomerManagerChildWin?num=1", 'newwindow','height=880,width=1230,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');  
				}  
			} 
			 
			function setValue(id,name,phone,identity){  
				 $("#certName").val(name);
				 $("#custId").val(id);
				 $("#mobile").val(phone);
				 $("#certNo").val(identity); 
			}
 */
		</script>
    
    
<%@include file="../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>银行卡信息</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="custForm" action="${contextPath}/fund/bankcardsave" method="post">
                        <input type="hidden"  name="id" value="${bankcard.id}"/>  
                        <input type="hidden"  name="custId" id="custId" value="${bankcard.custId}" > 
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>修改银行卡信息<font class="pl10 f12 color07"></font></h2>
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
                                                            <td align="left">客户名称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="certName" value="${bankcard.certName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                            <td> 
	                                                            <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
				                      								<button type="button" class="btn btn-default fl table-nobg-btn" id="btn_search"><i class="fa fa-plus"></i>&nbsp;查找用户</button>
				                								</div>
									                    	</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">手机号码：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="mobile" value="${bankcard.mobile}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">身份证号码：</td>
                                                            <td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="certNo" value="${bankcard.certNo}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">银行账号：</td>
                                                            <td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="bankNo" value="${bankcard.bankNo}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">银行简称：</td>
                                                           	<td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="bankSortName" value="${bankcard.bankSortName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                         <tr>
                                                            <td align="left">银行具体地址：</td>
                                                           	<td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="bankAddress" value="" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                         <tr>
                                                            <td align="left">开户行地区（市县）：</td>
                                                           	<td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="cityId" value="${bankcard.cityId}" style="width:256px;" />
                                                                	请参照富友提供地区码表
                                                                </label>
                                                            </td>
                                                        </tr>
                                                         <tr>
                                                            <td align="left">开户行行别：</td>
                                                           	<td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="parentBankId" value="${bankcard.parentBankId}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                         <tr>
                                                            <td align="left">银行卡类型：</td>
                                                           <td>
							                                <label class="select" style="width:210px" >
							                                    <select name="isPersonalCard">
							                                        <option value="1">个人</option>
							                                        <option value="2">公司</option>
							                                    </select>
							                                </label>
						                            	</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="btn_success">保存</button>
                                                	<button class="btn btn-default table-nobg-btn" type="button" id="btn_cancel">取消</button>
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

<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#btn_success").click(function () {
    	        if (validateCheck()) {
    	            $("#updateBankCardForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("修改成功!", '确认信息');
    	                        //自动跳转
    	                        parent.location.href="${contextPath}/fund/bankCardsManage";
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
        
    	 //用户列表
        $("#btn_search").button().click(function() {
        	window.open("${contextPath}/fund/bankcustomerManagerChildWin","_self");
        });
    	
    	
    	
    	$("#btn_cancel").button().click(function() {
        	window.history.back();
        });
     </script>
        
</body>

</html>