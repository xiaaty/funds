<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>对账管理--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>对账管理</li>
            <li>交易对账</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="contents">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="bloan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/checkAccounting/checkAccountList" method="post" >
                              
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
                                                <col width="220" />
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">交易流水号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="orderNo" value="${map.orderNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">用户名称：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="userName" value="${map.userName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">用户名：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="accName" value="${map.accName}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="tr" nowrap="nowrap">交易类型：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select">
							                                <select class="select02" style="width:202px;" name="type" id="status">
                                                                   <option value="">所有</option>
							                                   <fss:dictOrder var="order" dictOrder="checkTradeType">
                                                                   <option value="${order.key}"  <c:if test="${order.key==map.type}">selected</c:if> >${order.value}</option>
                                                               </fss:dictOrder>
							                                </select>
                                                                </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">异常状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select">
                                                                <select class="select02" style="width:202px;" name="abnormalState" id="abnormalState">
                                                                    <option value="">所有</option>
                                                                    <fss:dictOrder var="order" dictOrder="abnormalState">
                                                                        <option value="${order.key}"  <c:if test="${order.key==map.abnormalState}">selected</c:if> >${order.value}</option>
                                                                    </fss:dictOrder>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr">交易日期：</td>
                                                        <td colspan="3">
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" maxlength="10" readonly="readonly" name="startTime" class="selectdate" placeholder="请选择时间" value="${map.startTime}">
                                                                </label>
                                                            </section>
                                                            <span class="fl">&nbsp;至&nbsp;</span>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" maxlength="10" readonly="readonly"  name="endTime" class="selectdate" placeholder="请选择时间" value="${map.endTime}">
                                                                </label>
                                                            </section>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>


                        </div>
                        <%--<div class="jarviswidget-editbox">--%>


                        <%--</div>--%>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="borrowerLoan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>交易对账</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <input type="hidden" id="type" value="">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="withDraw"><i class="fa fa-plus"></i>&nbsp;提现交易导入</button>&nbsp;
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="withDrawBack"><i class="fa fa-plus"></i>&nbsp;提现退票导入</button>&nbsp;
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="withHold"><i class="fa fa-plus"></i>&nbsp;充值交易导入</button>&nbsp;
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="transfer"><i class="fa fa-plus"></i>&nbsp;转账交易导入</button>&nbsp;
                                        <%-- <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_detail"><i class="fa fa-list-ul"></i>&nbsp;详情</button>--%>
                                    </div>
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:4550px;">
                                        <col width="50" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="150"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="150"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td></td> 
                                            <td>交易流水号</td>
                                            <td>交易时间</td>
                                            <td>记账流水  </td>
                                            <td>记账日期   </td>
                                            <td>充值方式</td>
                                            <td>交易金额(元) </td>
                                            <td>客户ID</td>
                                            <td>账户</td>
                                            <td>用户名 </td>
                                            <td>用户名称 </td>
                                            <td>入账客户ID </td>
                                            <td>入账账户 </td>
                                            <td>入账用户名 </td>
                                            <td>入账用户名称 </td>
                                            <td>业务合同号 </td>
                                            <td>项目号 </td>
                                            <td>备注 </td>
                                            <td>状态 </td>
                                            <td>交易类型 </td>
                                            <td>是否对账 </td>
                                            <td>对账结果 </td>
                                            <td>异常状态 </td>
                                            <td>导入日期 </td>
                                            <td>修改日期 </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <%--<td>${t.mortgageeAccNo}</td>
                                                    <td>${t.accNo}</td>--%>
                                                    <td>${t.orderNo}</td>
                                                    <td>${t.tradeTime}</td>
                                                    <td>${t.accountingNo}</td>
                                                    <td>${t.accountingTime}</td>
                                                    <td>${t.rechargeWay}</td>
                                                    <td>
                                                        <fss:money money="${t.amount}"/>
                                                    </td>
                                                    <td>${t.custId}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.accName}</td>
                                                    <td>${t.userName}</td>
                                                    <td>${t.toCustId}</td>
                                                    <td>${t.toAccNo}</td>
                                                    <td>${t.toAccName}</td>
                                                    <td>${t.toUserName}</td>
                                                    <td>${t.contractNo}</td>
                                                    <td>${t.itemNo}</td>
                                                    <td>${t.remark}</td>
                                                    <td>${t.status}</td>
                                                    <td>
                                                        <fss:dictView key="${t.tradeType}" />
                                                    </td>
                                                    <td>
	                                                    <fss:dictView key="${t.accountingStatus}" />
                                                    </td>
                                                    <td>
	                                                    <fss:dictView key="${t.accountingResult}" />
                                                    </td>
                                                    <td>
	                                                    <fss:dictView key="${t.abnormalState}" />
                                                    </td>

                                                    <td><fss:fmtDate value="${t.createTime}" /></td>
                                                    <td><fss:fmtDate value="${t.modifyTime}" /></td>
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


<div class="pop" style="display:none;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 400px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;" >
    <form id="uploadForm" method="post"  enctype="multipart/form-data">
        <h1 class="f18">请从下方导入数据</h1>
        <p class="mt30 mb10">数据格式请参考导入模版</p>

        <div class="mb25 pr">
            <input type="text" id="file_pa" value="" class="input01 file_pa" style="width:280px; height:30px;"/>
            <a class="icon btn09 file_icon" href="#" title="文件夹"><span>文件夹</span></a>
            <input type="file" name="file" class="input01 file_fi" style="width:400px;height: 38px;"
                   onchange="document.getElementById('file_pa').value=this.value"/>
        </div>
        <div class="mb20" id="wid-id-713">
            <button class="btn btn-primary " id="import" type="button" title="导入">导&nbsp;入</button>&nbsp;&nbsp;
            <button class="btn btn-default fl mr30" id=""  type="button"  title="取消">取&nbsp;消</button>
            <%--<div class="mt20"><a class="btn_import fl" href="#" id="import" title="导入">导&nbsp;入</a>--%>
            <%--<a id="aaaaa" class="fl btn_cancel ml30" href="#" title="取消">取&nbsp;消</a>--%>
        </div>
    </form>
</div>
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortgageePayment"));
    });
    $(function () {
        $('#withDraw').click(function () {
            $('.mask').show();
            $('.pop').show();
            $("#type").val("10980001");
        })
        $('#withDrawBack').click(function () {
            $('.mask').show();
            $('.pop').show();
            $("#type").val("10980002");
        })
        $('#withHold').click(function () {
            $('.mask').show();
            $('.pop').show();
            $("#type").val("10980003");
        })
        $('#transfer').click(function () {
            $('.mask').show();
            $('.pop').show();
            $("#type").val("10980004");
        })

        $(".mr30").click(function () {
            $('.mask').show();
            $('.pop').hide();
        })

        $("#import").click(function () {
            var file_pa = $("#file_pa").val();
            if (file_pa == "") {
                alert("请选择导入的文件！");
                return;
            }
            var url="${contextPath}/upload/"+$("#type").val();
            upload(url);
        });

        selectedInit();
    });
    function selectedInit() {

    }
    $('#search').click(function () {
        $("#firstFlg").val("1");
        $("#customerForm").submit();
    })

    function upload(formUlr) {
        wait("正在导入中，请耐心等待...");
            $("#uploadForm").ajaxSubmit({
                url:formUlr,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    $.unblockUI();
                    if (data.code == '0000') {
                        alert(data.msg);
                        $('.pop').hide();
                        location.reload();
                    } else {

                        alert(data.msg);
                        $('.pop').hide();
                    }

                }
            });
    };
    /**
     * 遮罩栏
     * @param msg
     */
    function wait(msg){
        $.blockUI({
            css: {
                border: 'none',
                padding: '15px',
                // backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                //   opacity: .7,
                bindEvents: true,
                constrainTabKey: false,
                color: '#000'

            },baseZ:999999,
            message: '<img src="${contextPath}/img/loading.gif" />&nbsp;' + msg
        });
    }
    //校验函数
    function validateCheck() {
        return true;
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


</script>


<%@include file= "../../../../view/include/foot.jsp"%>

</body>

</html>