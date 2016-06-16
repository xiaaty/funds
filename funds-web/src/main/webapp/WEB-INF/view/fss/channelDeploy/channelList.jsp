<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--配置管理--交易渠道配置</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file= "../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>系统管理</li>
            <li>配置管理</li>
            <li>交易渠道配置</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
            <%--<div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget" id="bankcardList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>快速搜索</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="cardListForm" action="${contextPath}/fund/bankCardsManage" method="post" >
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
                                            <tr>
                                                <td class="tr" nowrap="nowrap">客户姓名:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input"  style="width:210px" >
                                                        <input type="text" name="certName" value="${map.certName}">
                                                    </label>
                                                </td>
                                                <td class="tr" nowrap="nowrap">银行卡号：</td>
                                                <td nowrap="nowrap">
                                                    <label class="input">
                                                        <input type="text" style="width:210px" name="bankNo" value="${map.bankNo}">
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="tr" nowrap="nowrap">银行简称：</td>
                                                <td nowrap="nowrap">
                                                    <label class="input">
                                                        <input type="text" style="width:210px" name="bankSortName" value="${map.bankSortName}">
                                                    </label>
                                                </td>
                                                <td class="tr">创建时间：</td>
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
                                        <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>


                    </div>

            </div>--%>
            <div id="content">
                <section id="widget-grid" class="">
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <!--    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                        <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                <h2>交易渠道配置</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form" id="">
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body">
                                        <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1100px;">
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="70" />
                                            <thead>
                                                <%--<tr>
                                                    <td>编号</td>
                                                    <td  align="left">客户名称</td>
                                                    <td  align="left">银行名称  </td>
                                                    <td  align="left">银行简称  </td>
                                                    <td>银行账号 </td>
                                                    <td>银行卡类型 </td>
                                                    <td>是否绑定第三方银行账户</td>
                                                    <td>创建日期 </td>
                                                    <td>修改日期 </td>
                                                    <!--  <td>操作</td> -->
                                                </tr>--%>
                                                <tr>
                                                    <td>渠道编码 </td>
                                                    <td>渠道名称 </td>
                                                    <td>渠道状态 </td>
                                                    <td>渠道方式 </td>
                                                    <td>渠道支付模式 </td>
                                                    <td>创建日期 </td>
                                                    <td>修改日期 </td>
                                                    <td>操作</td>
                                                </tr>
                                            </thead>

                                            <tbody>
                                            <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>
                                                        ${t.channelCode}
                                                    </td>
                                                    <td>
                                                        <%--${t.channelName}--%>
                                                        <fss:dictView key="${t.channelCode}" />
                                                    </td>
                                                    <td>
                                                        <fss:dictView key="${t.channelCondition}" />
                                                    </td>
                                                    <td>
                                                        <fss:dictView key="${t.channelType}" />
                                                    </td>
                                                    <td>
                                                        <fss:dictView key="${t.channelPaymentMode}" />
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                    </td>
                                                    <td>
                                                       <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                    </td>
                                                        <td>
                                                            <%--<a href="${contextPath}/fund/updateBankCard/${t.id}">配置</a>--%>
                                                                <a href="${contextPath}/channelDeploy/deploy/channelEdit/${t.id}">配置</a>
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
                        <!--  </article> -->
                    </div>

                </section>
            </div>
            <%@include file= "../../../view/include/common_footer_css_js.jsp"%>
            <script src="${contextPath}/js/jquery.form.js" ></script>
            <script src="${contextPath}/js/jquery.alerts.js" ></script>
    </div>


    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            pageSetUp();
            DT_page("borrow-rep-table12", true, '${page.JSON}', $("#cardListForm"));
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
        function verify(){
            var a=document.getElementsByName("startTime");
            var b=document.getElementsByName("endTime");
            if(b[0].value!=null&&b[0].value!=''){

                if(a[0].value>b[0].value){
                    JAlert("请检查您输入的日期","提示消息");
                }else{
                    $("#cardListForm").submit();
                }
            }else{
                var d = new Date();
                var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
                if(a[0].value>str){
                    JAlert("请检查您输入的日期","提示消息");
                }else{
                    $("#cardListForm").submit();
                }
            }
        }

        /**
         删除
         function deleteBankCard(id){
	            $.ajax({
	            	url : "${contextPath}/fund/deleteBankCard?id="+id,
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                      jAlert("删除成功!", '确认信息');
	                      //自动跳转
	                      parent.location.href="${contextPath}/fund/bankCardsManage";
	                    } else {
	                        return;
	                    }
	                }
	            });
    }
         **/



    </script>

    <%@include file= "../../../view/include/foot.jsp"%>
</body>
</html>
