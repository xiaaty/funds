<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2014/12/13
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>内审系统--查账--匹账--借款放款查账</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
    <style>
        .table-nobg-btn {
            font: 15/29px;
            height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }
        .table > tbody > tr.table_input > td {
            padding: 4px;
            vertical-align: middle;
        }
        .table > tbody > tr.table_input > td .input input {
            height: 26px;
            line-height: 20px;
            padding: 3px;
            vertical-align: middle;
        }
        .table > tbody > tr.table_input > td .select select {
            height: 26px;
            line-height: 20px;
            padding: 3px;
            vertical-align: middle;
        }
    </style>

</head>

<body>
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="main" role="main">

<!-- RIBBON -->
<div id="ribbon">
    <!-- breadcrumb -->
    <ol class="breadcrumb">
        <li>查账管理</li>
        <li>匹配</li>
        <li>借款放款</li>
    </ol>
    <!-- end breadcrumb -->
</div>

<div id="content">
<section id="widget-grid" class="">
<div class="row">

<!-- NEW WIDGET START -->
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

<div class="bdr1 mt10 mb10">
    <header class="bdr2 bg_color2">
        <h2 class="h30 f14 lh30 pl15 bdr_b1">查账基本信息</h2>
    </header>
    <div class="jarviswidget m15" id="wid-id-5113" data-widget-sortable="false" data-widget-editbutton="false" data-widget-deletebutton="false">
        <header>
            <h2>快速搜索</h2>
        </header>
        <!-- widget div-->
        <div class="">
            <form class="smart-form" action="${contextPath}/bill/queryLoanPaymentList"  id="accountForm">
                <!-- widget edit box -->
                <div class="jarviswidget-editbox">
                    <!-- This area used as dropdown edit box -->
                </div>
                <!-- end widget edit box -->
                <!-- widget content -->
                <div class="widget-body no-padding">
                    <div class="mt10 mb10">
                        <table class="table lh32">
                            <col width="100" />
                            <col width="320" />
                            <col width="100" />
                            <col width="320" />
                            <col width="100" />
                            <col />
                            <tbody>
                            <tr>
                                <td class="lh32 tr">放款日期：</td>
                                <td>
                                    <section class="fl">
                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="map[payDateStart]" class="selectdate" placeholder="请选择时间">
                                        </label>
                                    </section>
                                    <span class="fl">&nbsp;至&nbsp;</span>
                                    <section class="fl">
                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="map[payDateEnd]" class="selectdate" placeholder="请选择时间">
                                        </label>
                                    </section>
                                </td>
                                <td class="lh32 tr">放款金额：</td>
                                <td>
                                    <label class="input" style="width:210px">
                                        <input name="map[queryamount]" type="text" id="queryamount"/>
                                    </label>
                                </td>
                                <td class="lh32 tr">合同编号：</td>
                                <td>
                                    <label class="input" style="width:300px">
                                        <input name="map[contractNo]" type="text" />
                                    </label>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <footer>
                        <button class="btn btn-primary" type="button" id="accountQueryButton">查&nbsp;&nbsp;&nbsp;询</button>
                    </footer>
                </div>
                <!-- end widget content -->
            </form>
        </div>


    </div>

    <div class="m15">
        <table id="searchBook" class="table table-bordered table-striped tc matching" style="min-width:1500px;">
            <thead>
            <tr>
                <td class="tl"></td>
                <td>合同导入日期</td>
                <td>放款日期</td>
                <td>具体时间</td>
                <td>放款金额</td>
                <td>已匹配金额</td>
                <td>未匹配余额</td>
                <td>大区</td>
                <td>大区经理</td>
                <td>地区</td>
                <td>门店</td>
                <td>客户姓名</td>
                <td>合同编号</td>
                <td>贷款类型</td>
            </tr>
            </thead>
            <tbody class="f12">

            </tbody>
        </table>
    </div>


</div>

<div class="bdr1 mt20 mb10">
    <header class="bdr2 bg_color2">
        <h2 class="h30 f14 lh30 pl15 bdr_b1">银行流水匹配信息</h2>
    </header>
    <div class="jarviswidget m15" id="wid-id-5111" data-widget-sortable="false" data-widget-editbutton="false" data-widget-deletebutton="false">
        <header>
            <h2>快速搜索</h2>
        </header>
        <!-- widget div-->
        <div class="">
            <form class="smart-form" action="${contextPath}/water/allWater" method="post" id="bankRecordForm">
                <!-- widget edit box -->
                <div class="jarviswidget-editbox">
                    <!-- This area used as dropdown edit box -->
                </div>
                <!-- end widget edit box -->
                <!-- widget content -->
                <div class="widget-body no-padding">
                    <div class="mt10 mb10">
                        <table class="table lh32">
                            <col width="100" />
                            <col width="320" />
                            <col width="100" />
                            <col width="460" />
                            <col width="100" />
                            <col />
                            <tbody>
                            <tr>
                                <td class="lh32 tr">交易日期：</td>
                                <td>
                                    <section class="fl">
                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="map[dealDateStart]" class="selectdate" placeholder="请选择时间" id="dealStart">
                                        </label>
                                    </section>
                                    <span class="fl">&nbsp;至&nbsp;</span>
                                    <section class="fl">
                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="map[dealDatEnd]" class="selectdate" placeholder="请选择时间" id="dealEnd">
                                        </label>
                                    </section>
                                </td>
                                <td class="lh32 tr">支出金额：</td>
                                <td>
                                    <section class="fl">
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[paymentStart]" id="paymentStart"/>
                                    </label>
                                    </section>
                                    <span class="fl">&nbsp;至&nbsp;</span>
                                    <section class="fl">
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[paymentEnd]"  id="paymentEnd"/>
                                    </label>
                                        </section>
                                </td>
                                <td class="lh32 tr">交易行名：</td>
                                <td>
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[bankName]"/>
                                        <input type="hidden" name="map[businessType]" value="2">
                                    </label>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <footer>
                        <button class="btn btn-primary" type="button" id="bankQueryForm">查&nbsp;&nbsp;&nbsp;询</button>
                    </footer>
                </div>
                <!-- end widget content -->
            </form>
        </div>


    </div>
    <div id="success" class="alert alert-block alert-success ml15 mr15" style="display: none">
        <p><i class="fa fa-check-circle pr15"></i>匹配成功</p>
    </div>
    <div id="error" class="alert alert-danger alert-block ml15 mr15" style="display: none">
        <p><i class="fa fa-exclamation-circle pr15"></i>请选择数据进行匹配</p>
    </div>
    <div class="m15">
        <table id="borrow-rep-table1" class="table table-bordered table-striped tc matching" style="min-width:1500px;">
            <thead>
            <tr>
                <td>导入时间</td>
                <td>交易日期</td>
                <td>交易时间</td>
                <td>收入金额</td>
                <td>支出金额</td>
                <td>已匹配金额</td>
                <td>未匹配余额</td>
                <td>交易行名</td>
                <td>交易地点</td>
                <td>对方账号</td>
                <td>对方户名</td>
                <td>交易摘要</td>
                <td>交易渠道</td>
                <td>入账状态</td>
                <td>匹配</td>
            </tr>
            </thead>
            <tbody class="f12">

            </tbody>
        </table>
    </div>
</div>
</article>
</div>
</section>
</div>



<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script>

	var numrg = /^(-?\d+)(\.\d{0,2})?$/;
	
	function changeState(obj){
	    $(obj).find(":checkbox").each(function(){
	        $(this).click(function(){
	            if(this.checked == true){
	                $('#searchBook').find(":checkbox").removeAttr("checked");
	                this.checked = true;
	                var paydate = $(this).parents("tr").find(".paydate").text();
	                var amout = $(this).parents("tr").find(".queryamount").text();
	                $("#dealStart").val(paydate);
	                //$("#dealEnd").val(paydate)
	                $("#paymentStart").val('-' + amout.replace(new RegExp(",","gm"),"")  );
	                $("#paymentEnd").val('-' + amout.replace(new RegExp(",","gm"),""));
	                $("#bankQueryForm").click();
	            }
	        });
	    })
	}
    $(document).ready(function () {
        pageSetUp();
        $('#searchBook').on("init.dt",function(){

            changeState(this);
        });
        $('#searchBook').on("draw.dt",function(){
            changeState(this);
        });
        var columns1 = [
             null,
            { "mData": "INPUTDATE" },
            { "mData": "PAYDATE" ,"sClass":"paydate"},
            { "mData": "SPECIFICTIME" },
            { "mData": "QUERYAMOUNT" ,"sClass":"queryamount"},
            { "mData": "MATCHMONEY" },
            { "mData": "MATCHBALANCE" },
            { "mData": "REGION" },
            { "mData": "REGIONMANAGER" },
            { "mData": "DISTRICT" },
            { "mData": "WORKSHOP" },
            { "mData": "CUSTOMERNAME" },
            { "mData": "CONTRACTNO" },
            { "mData": "LOANTYPE" }
        ];


        var common_table = DT_ajax_page("searchBook",true,$('#accountForm'),columns1,false,5,[{
            aTargets:[0],
            mData:function ( source, type, val ){
                return '<input name="id" value="'+source["ID"]+'" type="checkbox" >';
            }
        }]);

        $("#accountQueryButton").click(function(){
        	
        	var queryamount = $("#queryamount").val();
        	
        	if(queryamount!= "") {
				if(!numrg.test(queryamount)){
					var msg = "放款金额输入有误 请输入有效的数字 ，可以有两位小数!  ";
					alert(msg);
					$("#queryamount").focus();
					return false;
				}
        	}
        	
            common_table.fnPageChange("first",true);
        })

        var columns = [
            { "mData": "INPUTDATE" },
            { "mData": "TRANSDATE" },
            { "mData": "TRANSTIME" },
            { "mData": "INCOME" },
            { "mData": "PAYMENT" },
            { "mData": "MATCHMONEY" },
            { "mData": "MATCHBALANCE" },
            { "mData": "ACCOUNTORG" },
            { "mData": "TRANSSITE" },
            { "mData": "FROMACCOUNT" },
            { "mData": "FROMNAME" },
            { "mData": "SUMMARY" },
            { "mData": "CHANNEL" },
            { "mData": "BILLSTATUS" },
                null
        ];

       var common_table_bank = DT_ajax_page("borrow-rep-table1",true,$('#bankRecordForm'),columns,false,5,[{
            aTargets:[14],
            mData:function ( source, type, val ){
                return '<a href="javascript:void(0)" onclick="pair(\''+source.ID+'\')">匹配</a>';
            }
        }]);
        $("#bankQueryForm").click(function(){
        	var paymentStart = $("#paymentStart").val();
        	if(paymentStart!= "") {
				if(!numrg.test(paymentStart)){
					var msg = "支出金额[from]输入有误 请输入有效的数字 ，可以有两位小数!  ";
					alert(msg);
					$("#paymentStart").focus();
					return false;
				}
        	}
        	var paymentEnd = $("#paymentEnd").val();
        	if(paymentEnd!= "") {
				if(!numrg.test(paymentEnd)){
					var msg = "支出金额[to]输入有误 请输入有效的数字，可以有两位小数!  ";
					alert(msg);
					$("#paymentEnd").focus();
					return false;
				}
        	}

            common_table_bank.fnPageChange("first",true);
        })
        /*
         * CONVERT DIALOG TITLE TO HTML
         * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
         */



        $('.selectdate').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            autoclose: 1,
            format: 'yyyy-mm-dd',
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });

        dateCheck();
        
        
        $("#queryamount").blur(function() {
        	if($(this).val() != "") {
				if(!numrg.test($(this).val())){
					var msg = "请输入有效的数字 ，可以有两位小数!  ";
					alert(msg);
					$(this).focus();
					$(this).select();
					return false;
				} else {
					$(this).val(Number($(this).val()).toFixed(2));
				}
        	}
		});
        
        $("#paymentStart").blur(function() {
        	if($(this).val() != "") {
				if(!numrg.test($(this).val())){
					var msg = "请输入有效的数字 ，可以有两位小数!  ";
					alert(msg);
					$(this).focus();
					$(this).select();
					return false;
				} else {
					$(this).val(Number($(this).val()).toFixed(2));
				}
        	}
		});
        
        $("#paymentEnd").blur(function() {
        	if($(this).val() != "") {
				if(!numrg.test($(this).val())){
					var msg = "请输入有效的数字 ，可以有两位小数!  ";
					alert(msg);
					$(this).focus();
					$(this).select();
					return false;
				} else {
					$(this).val(Number($(this).val()).toFixed(2));
				}
        	}
		});

    });


    function pair(ID){
        $("#success").hide();
        $("#error").hide();
        if($('#searchBook').find(":checked").size()==0){
            $("#error").html('<p><i class="fa fa-exclamation-circle pr15"></i>请选择数据进行匹配</p>');
            $("#error").show();
            return;
        }
        var aId = $('#searchBook').find(":checked").val();
        $.ajax("${contextPath}/bill/pairAccount",{
            async:false,
            data:{bID:ID,aId:aId,type:2},
            dataType:"json",
            success:function(data){
                if(data.code=="0000"){
                    $("#success").show();
                    $("#bankQueryForm").click();
                    $("#accountQueryButton").click();
                }else{
                    $("#error").html('<p><i class="fa fa-exclamation-circle pr15"></i>'+data.message+'</p>');
                    $("#error").show();
                }
            }

        });
    }
    
	//日期的合法性check
    function dateCheck() {
    	var $selectdate = $(".selectdate");
    	$selectdate.each(function() {
    		//$(this).off();
        	$(this).focus(function() {
        		//
        		this.select();
        	})
        	.blur(function() {
	        	if($(this).val() != "") {
		        	var val = $(this).val();
		        	if (val.indexOf("\-") > 0 ) {
		        	} else {
		        		if (val.length == 8) {
		        			val = val.substr(0,4) + "-" + val.substr(4,2) + "-" + val.substr(6,2);
		        			$(this).val(val);
		        		}
		        	}
		        	var msg= isDate($(this).val());
		        	if (msg != "") {
			        	alert(msg);
			        	this.focus();
		        	}

        		}
        	});
    	});
    }
	
    function isDate(strDate){
    	var strSeparator = "-"; //日期分隔符 
    	var strDateArray; 
    	var intYear; 
    	var intMonth; 
    	var intDay; 
    	var boolLeapYear; 
    	var ErrorMsg = ""; //出错信息 
    	strDateArray = strDate.split(strSeparator); 
    	//没有判断长度,其实2008-8-8也是合理的//strDate.length != 10 || 
    	if(strDateArray.length != 3) { 
        	ErrorMsg += "日期格式必须为: 年-月-日"; 
        	return ErrorMsg; 
    	} 
    	intYear = parseInt(strDateArray[0],10); 
    	intMonth = parseInt(strDateArray[1],10); 
    	intDay = parseInt(strDateArray[2],10); 
    	if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) { 
    		ErrorMsg += "请输入有效的日期！"; 
        	return ErrorMsg; 
    	} 
    	if(intMonth>12 || intMonth<1) { 
    		ErrorMsg += "请输入有效的日期！"; 
        	return ErrorMsg; 
    	} 
    	if((intMonth==1||intMonth==3||intMonth==5||intMonth==7 
    		||intMonth==8||intMonth==10||intMonth==12) &&(intDay>31||intDay<1)) { 
    		ErrorMsg += "请输入有效的日期！"; 
        	return ErrorMsg; 
    	} 
    	if((intMonth==4||intMonth==6||intMonth==9||intMonth==11) 
    		&&(intDay>30||intDay<1)) { 
    		ErrorMsg += "请输入有效的日期！";  
        	return ErrorMsg; 
    	} 
    	if(intMonth==2){ 
        	if(intDay < 1) { 
        		ErrorMsg += "请输入有效的日期！";  
	        	return ErrorMsg; 
    		} 
        	boolLeapYear = false; 
        	if((intYear%100) == 0){ 
	        	if((intYear%400) == 0) 
	        	boolLeapYear = true; 
    		} else { 
	        	if((intYear % 4) == 0) 
	        		boolLeapYear = true; 
        		} 
        		if(boolLeapYear){ 
	        		if(intDay > 29) { 
	        			ErrorMsg += "请输入有效的日期！"; 
		        		return ErrorMsg; 
        			} 
    			} else { 
		        	if(intDay > 28) { 
			        	ErrorMsg += "请输入有效的日期！"; 
			        	return ErrorMsg; 
	        		} 
    			} 
    		} 
    	return ErrorMsg; 
    } 
</script>

</body>

</html>