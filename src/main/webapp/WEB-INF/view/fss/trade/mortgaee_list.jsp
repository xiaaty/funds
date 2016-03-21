<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易记录--代扣审核--抵押权人代扣--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
   <%@include file="../../../view/include/common_css_js.jsp"%>
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
<%@include file="../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>代扣审核</li>
            <li>抵押权人代扣</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                      <div class="jarviswidget" id="dictList-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action=""  method="post" id="mortForm">
                                    <div class="jarviswidget-editbox">
                                    </div>
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
                                                       <td class="tr">客户账号：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:300px" name="accNo" value="${tradeapply.accNo}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">交易状态：</td>
                                                        <td>
                                                            <select id = "tradeState" name = "tradeState" style="width:150px;height: 30px;">
										                    	<option value="">请选择</option>
										                    	<option  <c:if test="${tradeapply.tradeState==10030001}"> selected="selected" </c:if> value="10030001">交易提交</option>
										                    	<option  <c:if test="${tradeapply.tradeState==10030002}"> selected="selected" </c:if> value="10030002" >交易成功</option>
										                    	<option  <c:if test="${tradeapply.tradeState==10030003}"> selected="selected" </c:if> value="10030003" >交易失败</option>
										                    	<option  <c:if test="${tradeapply.tradeState==10030004}"> selected="selected" </c:if> value="10030004" >交易关闭</option>
										                    </select>
                                                        </td> 
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                          <button type="submit" class="btn btn-primary">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-02"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>抵押权人代扣</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <thead>
                                        <tr>
                                             <td>申请编号</td>
                                             <td>申请类型</td>
                                             <td>业务编号</td>
                                             <td>业务类型</td>
                                             <td>账户编号</td>
                                             <td>交易金额</td>
                                             <td>实际交易金额</td>
                                             <td>交易手续费</td>
                                             <td>交易状态</td>
                                             <td>申请状态</td>
                                             <td>预约到账日期</td>
                                             <td>合同号ID</td>
                                             <td>流水号</td>
                                             <td>创建时间</td>
                                             <td>修改时间</td>
                                             <td>商户号</td>
                                             <td>交易渠道</td>
                                        </tr>
                                        </thead>
                                         <tbody>
                                             <c:forEach items="${page.list}" var="tradeapply">
                                                <tr>
                                                    <td>${tradeapply.applyNo}</td>
                                                    <td>${tradeapply.applyType}</td>
                                                    <td>${tradeapply.businessNo}</td>
                                                    <td><fss:dictView key="${tradeapply.busiType}" /></td>
                                                    <td>${tradeapply.accNo}</td>
                                                    <td>${tradeapply.tradeAmount}</td>
                                                    <td>${tradeapply.realTradeAmount}</td>
                                                    <td>${tradeapply.tradeChargeAmount}</td>
                                                    <td><fss:dictView key="${tradeapply.tradeState}" /></td>
                                                    <td><fss:dictView key="${tradeapply.applyState}" /></td>
                                                    <td><fss:fmtDate value="${tradeapply.bespokedate}"/></td>
                                                    <td>${tradeapply.contractId}</td>
                                                    <td>${tradeapply.seqNo}</td>
                                                    <td><fss:fmtDate value="${tradeapply.createTime}"/></td>
                                                    <td><fss:fmtDate value="${tradeapply.modifyTime}"/></td>
                                                    <td>${tradeapply.mchnChild}</td>
                                                    <td>${tradeapply.transactionChannel}</td>
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
<%@include file="../../../view/include/common_footer_css_js.jsp"%>
</div>
 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortForm"));
    });

</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>