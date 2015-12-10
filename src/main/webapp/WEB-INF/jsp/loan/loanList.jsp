<%--
  Created by IntelliJ IDEA.
  User: 于泳
  Date: 2014/12/4
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统--借款管理</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    <style>
        .table-nobg-btn{
            font:15/29px;
            height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
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
        <li>借款管理</li>
    </ol>
    <!-- end breadcrumb -->
</div>
<div id="content">
    <section id="widget-grid" class="">
        <div class="row">
            <!-- NEW WIDGET START -->
            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="jarviswidget" id="wid-id-11" data-widget-deletebutton="false" data-widget-editbutton="false">
                    <header>
                        <h2>快速搜索</h2>
                    </header>
                    <!-- widget div-->
                    <div>
                        <form class="smart-form" action="${contextPath}/loan/loanList" method="post" id="loanListForm">
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
                                        <col width="220" />
                                        <col width="100" />
                                        <col width="300" />
                                        <col width="100" />
                                        <col />
                                        <tbody>
                                        <tr >
                                            <td class="lh32 tr">合同编号：</td>
                                            <td>
                                                <label class="input" style="width:210px">
                                                    <input type="text" class="input01" name="contractNo" value="${record.contractNo}" />
                                                </label>
                                            </td>
                                            <td class="lh32 tr">客户姓名：</td>
                                            <td>
                                                <label class="input" style="width:300px" >
                                                    <input type="text"  class="input01" value="${record.customerName}" name="customerName"/>
                                                </label>
                                            </td>
                                            <td class="lh32 tr">身份证号：</td>
                                            <td>
                                                <label class="input" style="width:210px" >
                                                    <input type="text" class="input01" value="${record.certNo}" name="certNo"/>
                                                </label>
                                            </td>
                                        </tr>
                                        <tr >
                                            <td class="lh32 tr">大&nbsp;&nbsp;&nbsp;区：</td>
                                            <td>
                                                <label class="input" style="width:210px">
                                                    <input type="text"  class="input01" value="${record.largeArea}" name="largeArea"/>
                                                </label>
                                            </td>
                                            <td class="tr">导入时间：</td>
                                            <td colspan="3">
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" name="startDate" class="selectdate" placeholder="请选择时间" value="${record.startDate}">
                                                    </label>
                                                </section>
                                                <span class="fl">&nbsp;至&nbsp;</span>
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" name="endDate" class="selectdate" placeholder="请选择时间" value="${record.endDate}">
                                                    </label>
                                                </section>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <footer>
                                    <button class="btn btn-primary" type="submit">查&nbsp;&nbsp;&nbsp;询</button>
                                </footer>
                            </div>
                            <!-- end widget content -->
                        </form>
                    </div>


                </div>
                <!--
                 -->
                <!-- NEW WIDGET START -->
                <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                <div class="clearfix ml20 mb5" id="addErrorMsg" style="color:red;"></div>
                <div class="jarviswidget jarviswidget-color-darken" id="wid-id-12" data-widget-deletebutton="false" data-widget-editbutton="false">
                    <header>
                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                        <h2>借款管理列表</h2>
                    </header>
                    <!-- widget div-->
                    <div>
                        <form class="smart-form">
                            <!-- widget edit box -->
                            <div class="jarviswidget-editbox">
                                <!-- This area used as dropdown edit box -->
                            </div>
                            <!-- end widget edit box -->
                            <!-- widget content -->
                            <div class="widget-body">
								<div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                      <button class="btn btn-default fl table-nobg-btn" id="btn_upload" type="button"><i class="fa fa-sign-in"></i>&nbsp;导入</button>
                                      <button class="btn btn-default fl table-nobg-btn"  id="btn_add" type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                      <button class="btn btn-default fl table-nobg-btn"  id="btn_modify" type="button"><i class="fa fa-copy"></i>&nbsp;修改</button>
                                      <button class="btn btn-default fl table-nobg-btn"  id="btn_delete" type="button"><i class="fa fa-cut"></i>&nbsp;删除</button>
			                     </div>

                                <table id="borrow-rep-table1" class="table table-striped table-bordered" style="min-width:1800px">
                                    <!--  <table class="table01"  style="overflow-x:auto;"> -->
                                    <thead>
                                    <tr  align="center">
                                   		<th><input type="checkbox" id="checkAll" checked="checked"/></th>
                                        <th>合同编号</th>
                                        <th>客户姓名</th>
                                        <th>主借人身份证号</th>
                                        <th>共借人姓名</th>
                                        <th>共借人身份证号</th>
                                        <th>大区</th>
                                        <th>区域</th>
                                        <th>城市</th>
                                        <th>借款类型</th>
                                        <th>抵押率</th>
                                        <th>合同金额</th>
                                        <th>实际放款</th>
                                        <th>服务费</th>
                                        <th>还款方式</th>
                                        <th>期限</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="record" items="${page.list}">
                                        <tr align="center">
                                            <td><input type="checkbox" class="checkBoxAll" value="${record.id}"/></td>
                                            <td><a href="${contextPath}/loan/loanDetail?id=${record.id}" target="_self">${record.contractNo}</a></td>
                                            <td>${record.customerName}</td>
                                            <td>${record.certNo}</td>
                                            <td>${record.comBorrowNames}</td>
                                            <td>${record.comBorrowIdCards}</td>
                                            <td>${record.largeArea}</td>
                                            <td>${record.area}</td>
                                            <td>${record.city}</td>
                                            <td>${record.loanType}</td>
                                            <td>${record.mortgageRate}</td>
                                            <td align="right">${record.contractAmount}</td>
                                            <td align="right">${record.actualLoanAmount}</td>
                                            <td align="right">${record.totalServiceFee}</td>
                                            <td>${record.backMoneyType}</td>
                                            <td>${record.limitTimes}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- end widget content -->
                        </form>
                    </div>
                </div>

            </article>
        </div>
    </section>
</div>
</div>

<!-- add role dialog  -->
<div id="upload" title="<div class='widget-header'><h4 class='b'>导入</h4></div>">
    <form id="uploadForm" method="post" action="${contextPath}/loan/uploadLoan" enctype="multipart/form-data">
        <fieldset>
            <!-- <input name="authenticity_token" type="hidden"> -->
            <div class="mt20 mb20 ml50 ofh pr">
                <label class="lh30 color01">只能导入EXCEL：</label>
                <input class="form-control fl" style="width:80%" id="file_pa" value="" type="text">
                <i class="fa fa-folder-open fl fa-lg ml10 mt10"></i>
                <input type="file" name="file" class="form-control fl pr" style="opacity:0;filter:alpha(opacity=0);z-index:9999;width:90%; top:-34px; left:0;" onchange="document.getElementById('file_pa').value=this.value" />
            </div>
        </fieldset>

    </form>
</div>



<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>

<script>
    $(document).ready(function() {
    	
    	$("#checkAll").removeAttr("checked");
    	
        pageSetUp();

        DT_page("borrow-rep-table1",true,'${page.JSON}',$("#loanListForm"));
        /*
         * CONVERT DIALOG TITLE TO HTML
         * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
         */

        var add_dialog = $("#upload").dialog({
            autoOpen : false,
            width : 480,
            resizable : false,
            modal : true,
            buttons : [{
                html : "确&nbsp;&nbsp;&nbsp;&nbsp;认",
                "class" : "btn btn-default",
                click : function() {
				var file_pa = $("#file_pa").val();
					if (file_pa == "") {
						alert("请选择导入的文件！");
						return;
					}
                	upload();
                }
            }, {

                html : "取&nbsp;&nbsp;&nbsp;&nbsp;消",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });
        
        function upload(){
            $("#uploadForm").ajaxSubmit({
                beforeSubmit:function(){
                    var button = $("#upload").next().find(":button")
                    if(button.size() == 3){
                        button.eq(0).off().remove();
                    }
                    button.attr("disabled","disabled");
                },
                //upload
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                dataType:"json",
                success:function(data){	
                    if (data.code == '0000') {
                        jAlert(data.message + "!", '确认信息');
                        $("#loanListForm").submit();
                        add_dialog.close();
                        
                    }else if (data.code == '0009') {
                    	$("#addErrorMsg").html("导入失败! " +data.contractNo+"合同编号已经存在！请确认文件后重新导入。");
                    	var buttons = $("#upload").next().find(":button");
                    	buttons.removeAttr("disabled");
                    	return;
                    } else {
                        alert(data.message + "!");
                        if(data.path != ""){
                            var button = $('<button class="btn btn-default ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" type="button" role="button" aria-disabled="false"><span class="ui-button-text">查    看</span></button>')
                           button.click(function(){
                               window.open("${contextPath}/upload/"+data.path,"_blank");
                           });
                           $("#upload").next().prepend(button)
                        }
                        var buttons = $("#upload").next().find(":button");
                        buttons.removeAttr("disabled");
                    }
                }
            });
        }


        $("#btn_upload").button().click(function() {
            add_dialog.dialog("open");
            
            var button = $("#upload").next().find(":button")
            if(button.size() == 3){
                button.eq(0).off().remove();
            }
            
            return false;
        });

        $('.selectdate').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            autoclose: 1,
            format:'yyyy-mm-dd',
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
        
        //添加按钮按下
        $("#btn_add").button().click(function() {
        	window.open("${contextPath}/loan/loanAdd","_self");
        });
        
        //删除按钮
        $('#btn_delete').click(function(){
        	var no=$('#borrow-rep-table1 tbody :checkbox:checked');
        	if(no.size()==0){
        		alert("请选择要删除的合同！");
        		return false;
        	}
            if(!confirm("确认删除借款合同吗?")){
                return false;
            }
            var param=[];
            no.each(function(){
                param.push($(this).val());
            })
        	$.post("${contextPath}/loan/loanDelete?",{'no':param.toString()},function(data){
        		if(data>0){
        			alert("删除成功!");
        			//jAlert("删除成功!",'确认信息');
        			$("#loanListForm").submit();
        			return false;
        		}
        		return false;
        	});

        });
        
        //修改按钮
        $('#btn_modify').click(function(){
        	var no=$('#borrow-rep-table1 tbody :checkbox:checked');
        	if(no.size()!=1){
        		alert("请选择要修改的合同！");
        		return false;
        	}

            var param=[];
            no.each(function(){
                param.push($(this).val());
            })
            window.open("${contextPath}/loan/loanGoModify?id=" + param.toString()+"&"+new Date().getTime() ,"_self");
        });
        
        //日期型判断
        dateCheck() ;
        
        
        $('#checkAll').bind('click',function(){
            var that=this;
            $('.checkBoxAll').each(function(){
                this.checked=that.checked;
            });
        });
        
        $('.checkBoxAll').each(function(){
            $(this).click(function() {
	            if (this.checked == false ) {
	            	$("#checkAll").removeAttr("checked");
	            	return;
	            }
            });
        });
        
    });

    
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
        	ErrorMsg += "日期格式必须为: yyyy-MM-dd"; 
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

