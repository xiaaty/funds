<%--
  Created by IntelliJ IDEA.
  User: 于泳
  Date: 2014/12/7
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统--账户管理--银行账户管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

        .table > tbody > tr.table_input > td .select select{
            height: 26px;
            line-height: 20px;
            padding:3px;
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
            <li>账户管理</li><li>银行账户管理</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget" id="wid-id-310" data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>银行账户管理</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" action="${contextPath}/account/bankAccountList" method="post" id="accountListForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_save"><i class="fa fa-save"></i>&nbsp;保存</button>
                                       <%-- <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_detail"><i class="fa fa-list-ul"></i>&nbsp;详情</button>--%>
                                    </div>
                                    <table id="borrow-rep-table1" class="table table-bordered table-striped tc" style="min-width:1200px">
                                        <col width="60" />
                                        <col />
                                        <thead>
                                        <tr class="b">

                                            <td>账户编号</td>
                                            <td>开户行</td>
                                            <td>开户支行</td>
                                            <td>开户人姓名</td>
                                            <td>身份证</td>
                                            <td>卡号</td>
                                            <td>可用余额</td>
                                            <td>账户别名</td>
                                            <td>账户状态</td>
                                            <td>操作人</td>
                                            <td>操作时间</td>
                                        </tr>
                                        </thead>
                                        <tbody class="f12" id="listBank">
                                        <c:forEach var="bank" items="${page.list}">
                                            <tr>

                                                <td><a href="${contextPath}/account/bankListDetail?id=${bank.id}">${bank.accountNo}</a></td>
                                                <td>${bankMap[bank.openBank]}</td>
                                                <td>${bank.openBankBranch}</td>
                                                <td>${bank.name}</td>
                                                <td>${bank.certNo}</td>
                                                <td>${bank.cardNo}</td>
                                                <td>0.00</td>
                                                <td>${bank.alias}</td>
                                                <td>${accountMap[bank.status]}</td>
                                                <td>${bank.userName}</td>
                                                <td><fmt:formatDate value="${bank.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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

<div style="display: none">

   <form method="post" action="${contextPath}/account/addBankAccount" id="addBankAccountForm">

   </form>
</div>

<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>

<script>
    $(document).ready(function() {
        pageSetUp();

       DT_page("borrow-rep-table1",true,'${page.JSON}',$("#accountListForm"));

        $("#btn_add").click(function(){
            var size = $("#listBank .table_input").size();
            if(size >0){
                return;
            }
            var html='<tr class="table_input">';
            //html += '<td></td>';
            html += ' <td></td>';
            html += '<td><lable class="select"><select name="openBank"><c:forEach var="s" items="${bankMap}"><option value="${s.key}" ${search.status==s.key?"selected":""}>${s.value}</option></c:forEach></select></lable></td>';
            html += ' <td><lable class="input"><input type="text" required="true" validmess="开户支行" name="openBankBranch" value="" /></lable></td>';
            html += ' <td><lable class="input"><input type="text" required="true" validmess="开户人姓名" name="name" value="" /></lable></td>';
            html += '<td><lable class="input"><input type="text"  required="true" validmess="身份证" name="certNo" value="" length="18"/></lable></td>';
            html += '<td><lable class="input"><input type="text"  required="true" validmess="卡号" name="cardNo" value="" /></lable></td>';
            html += '<td></td>';
            html += '<td><lable class="input"><input type="text"  required="true" validmess="账户别名"  name="alias" value="" /></lable></td>';
            html += '<td></td>';
            html += '<td></td>';


            html += '';

            $("#listBank .dataTables_empty").remove();
            $("#listBank").append(html);

        });


        $("#btn_save").click(function(){
        	if(!checkForm()){
        		return;
        	}

            if(!confirm("确定添加银行账户？")){
                return;
            }
            var inputs = $("#listBank .table_input :text");
            var select = $("#listBank .table_input :selected");

            $("#addBankAccountForm").empty();

            $("#addBankAccountForm").append("<input name='openBank' value='"+select.attr("value")+"'>");
            for(var i = 0;i<inputs.size();i++){
                var input = inputs.eq(i);
                $("#addBankAccountForm").append(input.clone());
            }

            $("#addBankAccountForm").ajaxSubmit({
                dataType:"json",
                success:function(data){	
                    if (data.code == '0000') {
                        jAlert("添加成功!", '确认信息');
                        $("#accountListForm").submit();
                    } else if (data.code == '0007')  {
                        alert("该银行卡号已经存在!");
                    } else {
                    	alert("操作失败！");
                    }
                }
            });


        });
        function checkForm(){
        	var flag=true;
         	if($('.table_input :input').length<=0){
        		flag=false;
        	}
        	$('.table_input :input').each(function(){
        		if($(this).attr("required")&& !$(this).val() ){
        			alert($(this).attr("validmess")+"不能为空");
        		
        			flag= false;
        			return flag;
        		}
        	});
        	return flag;
        }

    });
</script>

</body>
</html>


