<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加字典--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
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
                <li>提现审核</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
        <div class="clearfix ml20" id="addErrorMsg" style="color:red;"></div>
                    <!-- NEW WIDGET START -->
        			<form name="fm1" id="fm1" action="${contextPath}/account/withdraw/reviewWithdrawGoon">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>提现申请信息<font class="pl10 f12 color07"></font></h2>
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
                        <td align="right">客户编号:</td>
                        <td>
                        	<input class="input03" value="${withDraw.custId}" style="width:190px;" name="custId" id="custId" readonly="readonly">
					          <input type="hidden" name="id" value="${withDraw.id}" />
					          <input type="hidden" name="accountId" value="${withDraw.accountId}" />
					                	
                        </td>
                         <td align="right">客户名称:</td>
                        <td>
                        	<input class="input03" value="${withDraw.custName}" style="width:190px;" name="custName" id="custName" readonly="readonly">        </td>
                           <td align="right">手机号码:</td>
                        <td>
                        	<input class="input03" value="${withDraw.cellPhone}" style="width:190px;" name="cellPhone" id="cellPhone" readonly="readonly"> 
                        </td>
                     </tr>
                     <tr>
                         <td align="right">业务类型</td>
	                     <td>
	                        <input type="hidden" id="hiddenbussinessType" value="${withDraw.bussinessType}"/>
	                        <select id="bussinessType" style="width:140px" name="bussinessType" class="select02" disabled="disabled">
	                            <option value="">请选择</option>
	                            <option value="1">满标提现</option>
	                            <option value="2">月月通代付(线下)</option>
	                            <option value="3">还款归还保证金</option>
	                            <option value="4">债权赎回提现</option>
	                            <option value="5">抵押标借款人提现</option>
	                        </select>
	                    </td>
                            <td align="right">提现金额:</td>
                            <td><input class="input03" value="${withDraw.drawAmount}" style="width:190px;" name="drawAmount" id="drawAmount" readonly="readonly"></td>
                             <td align="right">已经提现金额:</td>
	                        <td>
	                        	<input class="input03" value="${withDraw.factDrawAmount}" style="width:190px;" name="factDrawAmount" id="factDrawAmount" readonly="readonly">
	                        </td>
                     </tr>
                     <tr>
                            <td align="right">上限额度:</td>
                            <td>
                          	  <input class="input03" value="500000" style="width:190px;" name="limitAmount" id="limitAmount" readonly="readonly">
                            </td>
                            <td align="right">ip地址</td>
                            <td><input class="input03" value="${withDraw.ipaddr}" style="width:190px;" name="ipaddr" id="ipaddr" readonly="readonly"></td>
                            <td align="right">申请时间:</td>
                            <td><input class="input03" value="value="<fmt:formatDate value='${withDraw.applyTime}' pattern='yyyy-MM--dd HH:mm:ss'/>"0" style="width:190px;" name="applyTime" id="applyTime" readonly="readonly"></td>
                     </tr>
                     <tr id="withhold">
                            <td align="right">审核意见</td>
                            <td colspan="5">
                           		<span class="pl10 pr50"><input checked="checked" name="applyStatus" type="radio" value="4"><label class="ml5" for="typeofPayYes">通过</label></span>
								<span class="pl10 pr50"><input name="applyStatus" type="radio" value="3"><label class="ml5" for="checkRefuse">取消</label></span> 
						
                            </td>
                     </tr>
                     <!-- 结算类型； t+0 / t+1  add by  tianwei 2015/10/20 -->
                     <tr>
                            <td align="right">结算类型:</td>
                            <td> 
		                            <input value="0"  name="settleType"  id="settleType"   type="radio"  
		                            	<c:if test="${withDraw.settleType == 0}">  checked </c:if>>T+0
		                            &nbsp;&nbsp;&nbsp;&nbsp;
		                            <input value="1"  name="settleType"  id="settleType"   type="radio" 
		                            	<c:if test="${withDraw.settleType == 1}">  checked </c:if>>T+1
	                        </td>
	                        <td  colspan="5">
	                           <span class="emphasis emphasis_txtx01 pr5">*T+0 提现，0:00-16:00可当天到账</span>
	                        </td>
                     </tr>
                    </tbody>
                </table>
                <div class="mb20" id="wid-id-713">
                                        <button id="sava" class="btn btn-primary table-nobg-btn"   type="button">保 存</button>
                                        <button  onclick="location.href='${contextPath}/withdrawApply/queryWithdrawList'" class="btn btn-default table-nobg-btn "  type="button">取消</button>
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
	
<form class="smart-form" action="${contextPath}/withdrawApply/queryWithdrawList" method="post" id="withdrawListForm"></form>
<%@include file="../../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
  <script src="${contextPath}/js/gqi.js"></script>
   <script src="${contextPath}/js/layer.min.js"></script>
    <script type="text/javascript">
       
       		$(function() {
       			
                   $('ul li a').each(function(){
                       var st = $(this).attr("href");
                       if(st.indexOf("queryWithdrawList") >= 0){
                           $(this).parent().parent().parent().trigger("click").addClass("t1 nav_line on");
                           $(this).parent().addClass("chd_on");
                       }
                   });
       
       			
       			selectedInit();
       			
				var limitAmount = $("#limitAmount").val();
				var drawAmount = $("#drawAmount").val();
				var factDrawAmount = $("#factDrawAmount").val();
				
				drawAmount = Number(drawAmount) - Number(factDrawAmount);

				var count = Math.ceil(drawAmount/limitAmount);
				var lastdrawAmount = drawAmount;
				
			
				if (count == 1) {
            		$("#withhold").before(' <tr class="table_input">'
            				+ '<td align="right">建议拆分金额为:</td>'
            				+ '<td><input  class="input02" type="text"  name="drawAmountSplit['+ i +']" id="" value="'+ drawAmount +'" /></td><td><a id="add_mens" class="add_mens">添加一笔拆分</a></td>'
            				+ '</tr>');
				}
				
				if (count != 1){
				
					for(var i=0;i<count;i++){
						if (i==0) {
		            		$("#withhold").before(' <tr class="table_input">'
		            				+ '<td align="right">建议拆分金额为:</td>'
		            				+ '<td><input  class="input02" type="text"  name="drawAmountSplit" id="" value="'+ limitAmount +'" /></td><td><a id="add_mens"  class="add_mens">添加一笔拆分</a></td>'
		            				+ '</tr>');
		            		
						} else if (i == count-1) {
		            		$("#withhold").before(' <tr class="table_input">'
		            				+ '<td align="right"></td>'
		            				+ '<td><input  class="input02" type="text"  name="drawAmountSplit" id="" value="'+ lastdrawAmount.toFixed(2) +'" /></td><td><a class="delete_mens">删除</a></td>'
		
		            				+ '</tr>');
	            		} else {
		            		$("#withhold").before(' <tr class="table_input">'
		            				+ '<td align="right"></td>'
		            				+ '<td><input  class="input02" type="text"  name="drawAmountSplit" id="" value="'+ limitAmount +'" /></td><td><a class="delete_mens">删除</a></td>'
		
		            				+ '</tr>');
	            		}
	            		
						lastdrawAmount  = lastdrawAmount - limitAmount;
					}
				
				}
				
				
	            $("#add_mens").click(function(){


            		$("#withhold").before(' <tr class="table_input">'
            				+ '<td align="right"></td>'
            				+ '<td><input  class="input02" type="text"  name="drawAmountSplit" id="" value="" /></td><td><a class="delete_mens">删除</a></td>'

            				+ '</tr>');
            		addEvents();
            		numCheck();
	                return false; 
	            
	           });

	            addEvents();
	            numCheck();
       		});
       		
            function addEvents(){
                $(".delete_mens").each(function(){
    				$(this).off();
              	 	$(this).click(function(){
              	 		$(this).parents("tr").remove();
              	 	});

                });
            }
     
       	    function selectedInit() {

       	        getSelectState("bussinessType", $("#hiddenbussinessType").val());
       	    }



       	    function getSelectState(selectId, optionValue) {
       	        var sel = document.getElementById(selectId);
       	        for (var i = 0; i < sel.length; i++) {
       	            if (sel.options[i].value == optionValue) {
       	                sel.selectedIndex = i;
       	                break;
       	            }
       	        }
       	    }
       	    
            function numCheck() {
            	var $input02 = $(".input02");
            	var rg = /^(-?\d+)(\.\d{0,2})?$/;
            	$input02.each(function() {
            		$(this).off();
    	        	$(this).focus(function() {
    	        		//
    	        		this.select();
    	        	})
            		.blur(function() {
    		        	if($(this).val() != "") {
        					if(!rg.test($(this).val())){
        						var msg = "请输入有效的数字，可以有两位小数!";
        						alert(msg);
        						return false;
        					} else {
        						$(this).val(Number($(this).val()).toFixed(2));
        					}
    		        	}
            		});
            	});
            }
            
            function validateCheck(){
            	var flg =true;
            	var $input02 = $(".input02");
            	var rg = /^(-?\d+)(\.\d{0,2})?$/;
            	var drawAmount = $("#drawAmount").val();
            	var limitAmount = $("#limitAmount").val();
            	
            	var factDrawAmount = $("#factDrawAmount").val();
            	drawAmount = Number(drawAmount) - Number(factDrawAmount);
            	
            	var sumdrawAmount = 0;
            	
            	
            	$input02.each(function() {

	        		if ($(this).val() == "") {
  						alert("拆分金额不能为空!");
   						flg = false;
	        		}
		        		
   		        	if($(this).val() != "") {
       					if(!rg.test($(this).val())){
       						alert("请输入有效的数字，可以有两位小数!");
       						flg = false;
       					} else if (Number($(this).val()) > Number(limitAmount)) {
     						alert("拆分金额不能大于单笔最大上限额度!");
       						flg = false;
       					} else {
       						sumdrawAmount = sumdrawAmount + Number($(this).val());
       						$(this).val(Number($(this).val()).toFixed(2));
       					}
   		        	}

            	});
            	
            	if (!flg) {
            		return flg;
            	}

            	if (Number(drawAmount).toFixed(2) != Number(sumdrawAmount).toFixed(2)) {
            		alert("拆分金额总和与代扣金额不一致，请重新拆分!");
            		flg = false;
            	}
            	
            	return flg;
            }
            
            
            $("#sava").click(function(){
            	
				if (!validateCheck()) {
					return false;
				}
				
				changAttrName();
        		
        		//loading层显示
				var pageLoad =$.layer({
        		    type: 1,
        		    title: '',
        		    area: ['100', '100'],
        		    border: [0,0,'#44a9d5'], //认边框
        		    shade: [0.5, '#999'], //遮罩
        		    bgcolor:'',
        		    closeBtn: false,
        		    move: false,
        		    page: {
        		    	html:'<img src="${contextPath}/images/loading.gif">'
        		    }
        		});
        		
	            $("#fm1").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                	layer.close(pageLoad); //执行关闭
	                    if (data.code == '0000') {
	                        jAlert("审核成功!", '确认信息');
	                        $("#withdrawListForm").submit();
	                        return;
	                    } else {
	                        $("#sava").hide();
	                        $("#addErrorMsg").html(data.message)
	                        $("html,body").animate({scrollTop: $("#addErrorMsg").offset().top - 100}, 0);
	                        return;
	                    }
	                }
	            });
        	});
            
            
            function changAttrName(){
            	var numTmp = new Array();
            	for(var i = 0;i<$(".input02").size();i++){
            		numTmp.push(i);
            	}
           
            	$(".input02").each(function(i){
           			$(this).removeAttr("name").attr("name","drawAmountSplit["+numTmp[i]+"]");
            	});
            }
       		
       </script>
    
    </body>
    
</html>
