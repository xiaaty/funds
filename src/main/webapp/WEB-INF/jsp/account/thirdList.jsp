<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2014/12/7
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统--账户管理--第三方账户管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
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

    </style>

</head>
<body>
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>账户管理</li><li>第三方账户管理</li>
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
                    <div class="jarviswidget" id="wid-id-320"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>第三方账户管理</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="accountListForm" method="post" action="${contextPath}/account/thirdAccountList">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button class="btn btn-default fl table-nobg-btn" type="button" id="btn_add"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                        <button class="btn btn-default fl table-nobg-btn" type="button" id="btn_save"><i class="fa fa-save"></i>&nbsp;保存</button>
                                        <%--<button class="btn btn-default fl table-nobg-btn" type="button" id="btn_upload"><i class="fa fa-list-ul"></i>&nbsp;详情</button>--%>
                                    </div>
                                    <table id="borrow-rep-table1" class="table table-bordered tc" style="min-width:1200px">
                                        <col width="60" />
                                        <col />
                                        <thead>
                                        <tr class="b">
                                           <%-- <td>选择</td>--%>
                                            <td>第三方编码</td>
                                            <td>第三方名称</td>
                                            <td>商户号</td>
                                            <td>商户名称</td>
                                            <td>账户总额 </td>
                                            <td>账户可用余额</td>
                                            <td>账户冻结金额</td>
                                            <td>未结算金额</td>
                                            <td>账户状态</td>
                                            <td>操作人</td>
                                            <td>操作时间</td>
                                        </tr>
                                        </thead>
                                        <tbody class="f12" id="listBank">
                                        <c:forEach var="record" items="${page.list}">
                                            <tr>
                                               <%-- <td><input type="checkbox" /></td>--%>
                                                <td><a href="${contextPath}/account/thirdListDetail?id=${record.id}">${record.accountNo}</a></td>
                                                <td>${thirdMap[record.thirdOrg]}</td>
                                                <td>${record.merchantID}</td>
                                                <td>${record.merchantName}</td>
                                                <td>0.00</td>
                                                <td>0.00</td>
                                                <td>0.00</td>
                                                <td>0.00 </td>
                                                <td>${accountMap[record.status]}</td>
                                                <td>${record.userName}</td>
                                                <td><fmt:formatDate value="${record.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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

    <form method="post" action="${contextPath}/account/addThirdAccount" id="addThirdAccountForm">

    </form>
</div>

<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>

<script>
    $(document).ready(function() {
        pageSetUp();

        DT_page("borrow-rep-table1",true,'${page.JSON}',$("#accountListForm"));
        /*
        * <tr>
         <td><input type="checkbox" /></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         <td><lable class="input"><input type="text" value="" /></lable></td>
         </tr>*/

        $("#btn_add").click(function(){
            var size = $("#listBank .table_input").size();
            if(size >0){
                return;
            }
            var html='<tr class="table_input">';
            html += '<td></td>';
            //html += ' <td></td>';

            html += '<td><lable class="select"><select name="thirdOrg"><c:forEach var="s" items="${thirdMap}"><option value="${s.key}" ${search.status==s.key?"selected":""}>${s.value}</option></c:forEach></select></lable></td>';
            html += '<td><lable class="input"><input type="text" required="true" validmess="商户号" name="merchantID" value="" /></lable></td>';
            html += '<td><lable class="input"><input type="text" required="true" validmess="商户名称" name="merchantName" value="" length="18"/></lable></td>';
            html += '<td></td>';
            html += '<td></td>';
            html += '<td></td>';
            html += '<td></td>';
            html += '<td></td>';
            html += '<td></td>';

            $("#listBank .dataTables_empty").remove();
            $("#listBank").append(html);

        });


        $("#btn_save").click(function(){
        	if(!checkForm()){
        		return;
        	}

            if(!confirm("确定添加第三方账户？")){
                return;
            }
            var inputs = $("#listBank .table_input :text");
            var select = $("#listBank .table_input :selected");

            $("#addThirdAccountForm").empty();
            $("#addThirdAccountForm").append("<input name='thirdOrg' value='"+select.attr("value")+"'>");
            for(var i = 0;i<inputs.size();i++){
                var input = inputs.eq(i);
                $("#addThirdAccountForm").append(input.clone());
            }
            $("#addThirdAccountForm").submit();


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
