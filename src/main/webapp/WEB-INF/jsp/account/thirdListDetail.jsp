<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2014/12/9
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统</title>

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
<body><%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="main" role="main">

<!-- RIBBON -->
<div id="ribbon">
    <!-- breadcrumb -->
    <ol class="breadcrumb">
        <li>账户管理</li><li>第三方账户管理</li>
    </ol>
    <!-- end breadcrumb -->
</div>
<div class="row ml15">
    <button class="btn btn-default fl table-nobg-btn" id="returnParent"><i class="fa fa-reply"></i>&nbsp;返回</button>
    <button class="btn btn-default fl table-nobg-btn" id="btn_upload"><i class="fa fa-upload"></i>&nbsp;导入</button>
</div>
<div id="content">
<section id="widget-grid" class="">
<div class="row">
<!-- NEW WIDGET START -->
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
<!-- NEW WIDGET START -->

<div class="jarviswidget" id="wid-id-3410" data-widget-deletebutton="false" data-widget-editbutton="false">
    <header>
        <h2>我的账户</h2>
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
            <div class="widget-body no-padding">
                <div class="mt10 mb10">
                    <table class="table lh32 tc">
                        <col width="20%" />
                        <col width="20%" />
                        <col />
                        <tbody class="f12">
                        <tr>
                            <td>
                                <img src="${contextPath}/img/avatars/top.jpg" />
                                <h4>我的账户</h4>
                            </td>
                            <td>
                                <ul>
                                    <li>第三方名称：${thirdMap[thirdBean.thirdOrg]}</li>
                                    <li>商户号：${thirdBean.merchantID}</li>
                                    <li>商户名称：${thirdBean.merchantName}</li>
                                    <li>账户状态：${accountMap[thirdBean.status]}</li>
                                </ul>
                            </td>
                            <td>
                                <div style="margin:0 auto;display: none">
                                    <table class="tc" style="width:680px">
                                        <tr class="b">
                                            <td style="color:#c7254e; border-right:1px #ccc solid">账户总额</td>
                                            <td>账户可用余额</td>
                                            <td>账户冻结金额</td>
                                            <td>未结算金额</td>
                                        </tr>
                                        <tr class="b f20 pb10" style="border-bottom:1px #c7254e solid">
                                            <td style="color:#c7254e; border-right:1px #ccc solid">￥16544.22</td>
                                            <td>￥16544.22</td>
                                            <td>0.00</td>
                                            <td>0.00</td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- end widget content -->
        </form>
    </div>
</div>
<div class="jarviswidget" id="wid-id-3411" data-widget-deletebutton="false" data-widget-editbutton="false">
    <header>
        <h2>快速搜索</h2>
    </header>
    <!-- widget div-->
    <div>
        <form class="smart-form" id="accountForm" action="${contextPath}/account/thirdListDetail" method="post">
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
                        <tr>
                            <td class="lh32 tr">导入时间：</td>
                            <td>
                                <section class="fl">
                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="map[importStart]" value="${query.map['importStart']}" class="selectdate" placeholder="请选择时间">
                                    </label>
                                </section>
                                <span class="fl">&nbsp;至&nbsp;</span>


                                <section class="fl">
                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="map[importEnd]" value="${query.map['importEnd']}" class="selectdate" placeholder="请选择时间">
                                    </label>
                                </section>
                            </td>
                            <td class="lh32 tr">交易日期：</td>
                            <td>
                                <section class="fl">
                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="map[dealDateStart]" value="${query.map['dealDateStart']}" class="selectdate" placeholder="请选择时间">
                                    </label>
                                </section>
                                <span class="fl">&nbsp;至&nbsp;</span>
                                <section class="fl">
                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="map[dealDatEnd]" value="${query.map['dealDatEnd']}" class="selectdate" placeholder="请选择时间">
                                    </label>
                                </section>
                            </td>
                            <%--<td class="lh32 tr">交易时间：</td>
                            <td>
                                <section class="fl">
                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="map[deaTimeStart]" value="${query.map['deaTimeStart']}" class="selectdate" placeholder="请选择时间">
                                    </label>
                                </section>
                                <span class="fl">&nbsp;至&nbsp;</span>
                                <section class="fl">
                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="map[deaTiemEnd]" value="${query.map['deaTiemEnd']}" class="selectdate" placeholder="请选择时间">
                                    </label>
                                </section>
                            </td>--%>
                            <td class="lh32 tr">是否归集：</td>
                            <td>
                                <label class="select" style="width:302px">
                                    <select  name="map[businessType]" >
                                        <option value="0">所有</option>
                                        <option value="1" ${query.map['businessType']=="1"?"selected":""}>未归集</option>
                                        <option value="2" ${query.map['businessType']=="2"?"selected":""}>已归集</option>
                                    </select>
                                </label>
                            </td>
                        </tr>
                        <tr >
                            <td class="lh32 tr">流水编号：</td>
                            <td>
                                <label class="input" style="width:302px">
                                    <input name="map[serialNumber]" type="text" value="${query.map['serialNumber']}" />
                                </label>
                            </td>
                            <td class="lh32 tr">批次号：</td>
                            <td>
                                <label  class="input" style="width:302px">
                                    <input name="map[systemToken]" type="text" value="${query.map['systemToken']}"/>
                                </label>
                            </td>
                            <td class="lh32 tr">财务类型：</td>
                            <td>
                                <label class="select" style="width:302px">
                                    <select  name="map[financeType]" >
                                        <option value="0">所有</option>
                                        <c:forEach var="map" items="${financeMap}">
                                            <option value="${map.key}" ${map.key == query.map['financeType']?"selected":""}>${map.value}</option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>

                        </tr>
                        </tbody>
                    </table>
                </div>
                <footer>
                    <input type="hidden" name="id" value="${thirdBean.id}">
                    <button class="btn btn-primary" type="submit">查&nbsp;&nbsp;&nbsp;询</button>
                </footer>
            </div>
            <!-- end widget content -->
        </form>
    </div>
</div>

<div class="jarviswidget" id="wid-id-3412"  data-widget-deletebutton="false" data-widget-editbutton="false">
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
        <h2>第三方流水</h2>
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
                <div class="widget-body-nobg-toolbar" style="overflow:hidden;height: 15px;">
                    <%--<button class="btn btn-default fl table-nobg-btn"><i class="fa fa-group"></i>&nbsp;批量归集</button>--%>
                </div>
                <table id="borrow-rep-table" class="table table-bordered table-striped tc">
                    <thead>
                    <tr class="b">
                        <%--<td></td>--%>
                        <td>导入时间/创建时间</td>
                        <td>流水编号</td>
                        <td>批次号</td>
                        <td>交易时间</td>
                        <td>财务类型</td>
                        <td>收入金额</td>
                        <td>支出金额</td>
                        <td>账户余额</td>
                        <td>入账状态</td>
                        <td>归集类型</td>
                        <td>备注</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody class="f12">
                        <c:forEach var="record" items="${page.list}">
                    <tr class="table_input">
                        <%--<td><input type="checkbox" name="id" value="${record.id}"/></td>--%>
                        <td><fmt:formatDate value="${record.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${record.serialNumber}</td>
                        <td>${record.systemToken}</td>
                        <td><fmt:formatDate value="${record.trans}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <select  name="map[fType]">
                                <option value="0">请选择</option>
                                <c:forEach var="map" items="${financeMap}">
                                    <option value="${map.key}" ${map.key == record.financeType?"selected":""}>${map.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><fmt:formatNumber value="${record.income/100}" pattern="#,###,###,###,###,##0.00"/></td>
                        <td><fmt:formatNumber value="${record.payment/100}" pattern="#,###,###,###,###,##0.00"/></td>
                        <td><fmt:formatNumber value="${record.balance/100}" pattern="#,###,###,###,###,##0.00"/></td>
                        <td>${stateMap[record.billStatus]}</td>
                        <td>
                            <label class="select">
                                <select  name="map[bTYpe]">
                                    <option value="0">请选择</option>
                                    <c:forEach var="map" items="${fundMap}">
                                        <option value="${map.key}" ${map.key == record.businessType?"selected":""}>${map.value}</option>
                                    </c:forEach>
                                </select>

                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="input" name="map[memo]" value="${record.memo}"/>
                            </label>
                        </td>
                        <td>
                            <input type="hidden" name="id" value="${record.id}"/>
                            <a href="javascript:void(0);" onclick="changeBusiness(this);">归集</a>
                        </td>
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
<!-- add role dialog  -->
<!-- add role dialog  -->
<div id="upload" title="<div class='widget-header'><h4 class='b'>导入</h4></div>">
    <form id="uploadForm" method="post" action="${contextPath}/account/uploadAccount" enctype="multipart/form-data">
        <fieldset>
            <!-- <input name="authenticity_token" type="hidden"> -->
            <div class="mt20 mb20 ml50 ofh pr">
                <label class="lh30 color01">只能导入EXCEL：</label>
                <input class="form-control fl" style="width:80%" id="file_pa" value="" type="text">
                <i class="fa fa-folder-open fl fl-lg ml10 mt10"></i>
                <input type="file" name="file" class="form-control fl pr" style="opacity:0;filter:alpha(opacity=0);z-index:9999;width:90%; top:-34px; left:0;" onchange="document.getElementById('file_pa').value=this.value" />
            </div>
        </fieldset>
        <input type="hidden" name="accountID" value="${thirdBean.id}">
        <input type="hidden" name="accountType" value="2">
    </form>
</div>

<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script>
    $(document).ready(function() {
        pageSetUp();

        $("#third_tabs").tabs();

        DT_page("borrow-rep-table",true,'${page.JSON}',$("#accountForm"));


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


        $("#btn_upload").button().click(function() {
            add_dialog.dialog("open");
            var button = $("#upload").next().find(":button")
            if(button.size() == 3){
                button.eq(0).off().remove();
            }
            return false;
        });

        $("#returnParent").click(function(){
            window.open("${contextPath}/account/thirdAccountList","_self");
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
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                dataType:"json",
                success:function(data){
                    if (data.code == '0000') {
                        jAlert(data.message + "!", '确认信息');
                        $("#accountForm").submit();
                        add_dialog.close();
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

    });

    function changeBusiness(obj){

        var input = $(obj).parents("tr").find("input");
        var select = $(obj).parents("tr").find("select");
        var data = {};
        for(var i =0 ;i<input.size();i++){
            data[input.eq(i).attr("name")]=input.eq(i).val();
        }
        for(var i =0 ;i<select.size();i++){
            data[select.eq(i).attr("name")]=select.eq(i).val();
        }
        if(data['map[bTYpe]'] ==="0" && data['map[fType]']){
            data["map[isbType]"]="1";
        }else{
            data["map[isbType]"]="2";
        }
        if(window.console && window.console.dir){
            window.console.dir(data);
        }
        $.ajax("${contextPath}/water/updateFund",{
            type:"post",
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            data:data,
            dataType:"json",
            success:function(data){
                //var obj =  $.parseJSON(data);
                alert(data.message);
            }
        });
    }
</script>

</body>
</html>
