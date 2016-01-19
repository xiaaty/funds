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
    <title>内审系统--查账--匹账--借款还款查账</title>

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
        <li>借款回款</li>
    </ol>
    <!-- end breadcrumb -->
</div>
<div class="h30 ml15">
    <button class="btn btn-default fl table-nobg-btn" id="btn_upload"><i class="fa fa-share-square"></i>&nbsp;导入</button>
    <button class="btn btn-default fl table-nobg-btn searchingAdd"><i class="fa fa-plus-circle "></i>&nbsp;添加</button>
  <%--  <button class="btn btn-default fl table-nobg-btn"><i class="fa fa-times-circle "></i>&nbsp;删除</button>--%>
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
            <form class="smart-form" action="${contextPath}/bill/queryAccountList"  id="accountForm">
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
                                <td class="lh32 tr">导入日期：</td>
                                <td>
                                    <section class="fl">
                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="map[importStart]" class="selectdate" placeholder="请选择时间">
                                        </label>
                                    </section>
                                    <span class="fl">&nbsp;至&nbsp;</span>
                                    <section class="fl">
                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="map[importEnd]" class="selectdate" placeholder="请选择时间">
                                        </label>
                                    </section>
                                </td>
                                <td class="lh32 tr">查询金额：</td>
                                <td>
                                    <section class="fl">
                                    <label class="input"  style="width:140px">
                                        <input type="text"  name="map[incomeStart]"/>
                                    </label>
                                     </section>
                                    <span class="fl">&nbsp;至&nbsp;</span>
                                    <section class="fl">
                                        <label class="input"  style="width:140px">
                                            <input type="text"  name="map[incomeEnd]"/>
                                        </label>
                                    </section>
                                </td>
                                <td class="lh32 tr">查询人：</td>
                                <td>
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[requester]"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td class="lh32 tr">交易行名：</td>
                                <td>
                                    <label class="input" style="width:300px">
                                        <input type="text" name="map[bankName]"/>
                                    </label>
                                </td>
                                <td class="lh32 tr">贷款类型：</td>
                                <td>
                                    <label class="input" style="width:300px">
                                        <input type="text" name="map[loanType]"/>
                                    </label>
                                </td>
                                <td></td>
                                <td></td>
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
                <td>导入日期</td>
                <td >到账日期</td>
                <td>具体时间</td>
                <td>查询金额</td>
                <td>银行</td>
                <td>大区</td>
                <td>大区经理</td>
                <td>地区</td>
                <td>成本中心代码</td>
                <td>门店</td>
                <td>客户姓名</td>
                <td>合同编号</td>
                <td>查询部门</td>
                <td>查询人</td>
                <td>还款方式</td>
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
                                <td class="lh32 tr">收入金额：</td>
                                <td>
                                    <section class="fl">
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[incomeStart]" id="incomeStart"/>
                                    </label>
                                    </section>
                                    <span class="fl">&nbsp;至&nbsp;</span>
                                    <section class="fl">
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[incomeEnd]"  id="incomeEnd"/>
                                    </label>
                                        </section>
                                </td>
                                <td class="lh32 tr">交易行名：</td>
                                <td>
                                    <label class="input" style="width:210px">
                                        <input type="text" name="map[bankName]"/>
                                        <input type="hidden" name="map[businessType]" value="1">
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

<!-- add role dialog  -->
<div id="upload" title="<div class='widget-header'><h4 class='b'>导入</h4></div>">
    <form id="uploadForm" method="post" action="${contextPath}/bill/uploadBillRecord" enctype="multipart/form-data">
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
</div>

<form action="${contextPath}/bill/addQueryAccount" method="post" id="addQuweyAccount">

</form>
<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script>
function changeState(obj){
    $(obj).find(":checkbox").each(function(){
        $(this).click(function(){
            if(this.checked == true){
                $('#searchBook').find(":checkbox").removeAttr("checked");
                this.checked = true;
                var paydate = $(this).parents("tr").find(".paydate").text();
                var amout = $(this).parents("tr").find(".queryamount").text();
                $("#dealStart").val(paydate)
                $("#dealEnd").val(paydate)
                $("#incomeStart").val(amout.replace(new RegExp(",","gm"),"")  );
                $("#incomeEnd").val(amout.replace(new RegExp(",","gm"),""));
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
            { "mData": "BANK" },
            { "mData": "REGION" },
            { "mData": "REGIONMANAGER" },
            { "mData": "DISTRICT" },
            { "mData": "COSTCENTERCODE" },
            { "mData": "WORKSHOP" },
            { "mData": "CUSTOMERNAME" },
            { "mData": "CONTRACTNO" },
            { "mData": "DEPARTMENT" },
            { "mData": "REQUESTER" },
            { "mData": "PAYWAY" },
            { "mData": "LOANTYPE" }
        ];

        var common_table = DT_ajax_page("searchBook",true,$('#accountForm'),columns1,false,5,[{
            aTargets:[0],
            mData:function ( source, type, val ){
                return '<input name="id" value="'+source["ID"]+'" type="checkbox" >';
            }
        }]);

        $("#accountQueryButton").click(function(){

//            $("#searchBook_wrapper .first a").click();
            common_table.fnPageChange("first",true);
        })
        //DT_page("searchBook", false, false, false);
        /*
         <td>导入时间</td>
         <td>交易日期</td>
         <td>交易时间</td>
         <td>收入金额</td>
         <td>已匹配金额</td>
         <td>未匹配余额</td>
         <td>交易行名</td>
         <td>交易地点</td>
         <td>对方账号</td>
         <td>对方户名</td>
         <td>交易摘要</td>
         <td>交易渠道</td>
        */
        var columns = [
            { "mData": "INPUTDATE" },
            { "mData": "TRANSDATE" },
            { "mData": "TRANSTIME" },
            { "mData": "INCOME" },
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
            aTargets:[13],
           mData:function ( source, type, val ){
                return '<a href="javascript:void(0)" onclick="pair(\''+source.ID+'\')">匹配</a>';
            }
        }]);
        $("#bankQueryForm").click(function(){

            common_table_bank.fnPageChange("first",true);
//            common_table_bank.ajax.reload();;
        })
        /*
         * CONVERT DIALOG TITLE TO HTML
         * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
         */

        var add_dialog = $("#upload").dialog({
            autoOpen: false,
            width: 480,
            resizable: false,
            modal: true,
            buttons: [
                {
                html: "确&nbsp;&nbsp;&nbsp;&nbsp;认",
                "class": "btn btn-default",
                click: function () {
                    upload();

                }
            }, {

                html: "取&nbsp;&nbsp;&nbsp;&nbsp;消",
                "class": "btn btn-default",
                click: function () {
                    $(this).dialog("close");
                }
            }]
        });

        $(".searchingAdd").click(function () {
            if ($("#searchBook").find("tr.searchAddTr,tr.searchContTr").size() > 0) {
                return;
            }
            var htmlAdd = "";
            htmlAdd += '<tr class="searchAddTr"><td class="tl"></td>';
            htmlAdd += '<td>系统自动记录</td>';
            htmlAdd += '<td><input type="text" name="payDate" class="input" style="width:90px;"  class="selectdate" readonly="readonly" id="addPayDate"/></td>';
            htmlAdd += '<td><input type="text" name="specificTime" class="input" style="width:90px;" /></td>';
            htmlAdd += '<td><input type="text" name="queryAmountOri" class="input" style="width:46px"/></td>';
            htmlAdd += '<td><input type="text" name="bank" class="input" style="width:46px"/></td>';
            htmlAdd += '<td><input type="text" name="region" class="input" style="width:46px"/></td>';
            htmlAdd += '<td><input type="text" name="regionManager" class="input" style="width:46px"/></td>';
            htmlAdd += '<td><input type="text" name="district" class="input" style="width:30px"/></td>';
            htmlAdd += '<td><input type="text" name="costCenterCode" class="input" style="width:70px"/></td>';
            htmlAdd += '<td><input type="text" name="workshop" class="input" style="width:70px"/></td>';
            htmlAdd += '<td><input type="text" name="customerName" class="input" style="width:46px"/></td>';
            htmlAdd += '<td><input type="text" name="contractNo" class="input" style="width:65px"/></td>';
            htmlAdd += '<td><input type="text" name="department" class="input" style="width:60px"/></td>';
            htmlAdd += '<td><input type="text" name="requester" class="input" style="width:40px"/></td>';
            htmlAdd += '<td><select class="select02" style="width:70px" name="payWay" ><option value="银行转账">银行转账</option><option value="银行还款">银行还款</option></select>';
            htmlAdd += '<td><input type="text" name="loanType" class="input" style="width:46px"/></td></tr>';
            htmlAdd += '<tr class="searchContTr"><td colspan="16" id="addErroMsg" style="color:red;"></td><td colspan="3" align="right"><a class="btn02 searchCancel" href="#" title="取消">取&nbsp;消</a>&nbsp;&nbsp;<a class="btn02 searchConfirm" href="#" title="确定" >确&nbsp;定</a></td></tr>';
            $("#searchBook tbody").prepend(htmlAdd);
            $('#addPayDate').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                autoclose: 1,
                format: 'yyyymmdd',
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });
            $(".searchCancel").on('click', function () {
                //$(this).parent().remove();
                $(this).parents("#searchBook").find("tr.searchAddTr,tr.searchContTr").remove();
            })
            $(".searchConfirm").on('click', function () {
                var errorMsg  = "";
                var msg = ['到账日期','具体时间','查询金额','银行','大区','大区经理','地区','成本中心代码','门店','客户姓名 ','合同编号','查询部门','查询人','还款方式','贷款类型'];
                $(this).parents("#searchBook").find("tr.searchAddTr td :input").each(function(i){
                    if(!$(this).val()){
                        errorMsg += msg[i]+"不能为空    ";
                    }
                    if($(this).attr("name") == "queryAmountOri"){
                        var rg = /^(-?\d+)(\.\d{0,2})?$/g;
                        //$(this).val().
                        if(!rg.test($(this).val())){
                            errorMsg += msg[i]+"不能为非数字  ，且只能保留两位小数";
                        };


                    }



                });
                if(errorMsg.length>0){
                    $('#addErroMsg').html(errorMsg);
                    return;
                }
                $('#addErroMsg').html("");
                $("#addQuweyAccount").empty();
                $(this).parents("#searchBook").find("tr.searchAddTr td :input").each(function(){
                    $("#addQuweyAccount").append($(this).clone());
                });
               // $("#addQuweyAccount").append($(this).parents("#searchBook").find("tr.searchAddTr td select").clone());
                $("#addQuweyAccount").ajaxSubmit({
                    //upload
                    beforeSubmit:function(){
                        var button = $(this).parents("#searchBook").find("tr.searchAddTr td :button");
                        button.attr("disabled","disabled");
                    },
                    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                    dataType:"json",
                    success:function(data){
                        alert(data.message);
                        if(data.code=="0000"){
                            $("#accountQueryButton").click();
                        }
                        var button = $(this).parents("#searchBook").find("tr.searchAddTr td :button");
                        button.attr("disabled","disabled");

                    }
                });
//                $("#requester11").attr("disabled","disabled");
//                $("#billSearch").removeAttr("action");
//                $("#billSearch").attr("action","/bill/AddAccount");
//                $("#billSearch").off("submit");
//                $("#billSearch").submit();
            })

            $('.money').live('keyup',function(){
                $(this).val($(this).val().replace(/[^\d.]/g,"").replace(/^\./g,"").replace(".","$#$").replace(/\./g,"").replace("$#$","."));
            });
        })



        $("#btn_upload").button().click(function() {

            add_dialog.dialog("open");
            var button = $("#upload").next().find(":button")
            if(button.size() == 3){
                button.eq(0).off().remove();
            }
            return false;
        });


        function upload(){
            $("#uploadForm").ajaxSubmit({
                //upload
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
                    alert(data.message);
                    if(data.code=="0000"){
                        $("#accountQueryButton").click();
                        $("#upload").dialog("close");
                    }
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
            });
        }

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
            data:{bID:ID,aId:aId,type:1},
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
</script>

</body>

</html>
