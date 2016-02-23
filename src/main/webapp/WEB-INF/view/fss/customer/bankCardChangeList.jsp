<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--银行卡变更--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
            <li>客户信息管理</li>
            <li>银行卡变更列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="cardChangeListForm" action="" method="post" >
                              
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
                                                        <td class="tr" nowrap="nowrap">客户姓名:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="custName" value="${fssBankcard.custName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">客户手机号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="mobile" value="${fssBankcard.mobile}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">客户身份证号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="certNo" value="${fssBankcard.certNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">变更状态：</td>
                                                      	<td>
							                                <label class="select" style="width:210px" >
							                                    <select name="state">
							                                    	<option value="0">全部</option>
							                                        <option value="1">变更中</option>
							                                        <option value="2">变更成功</option>
							                                        <option value="3">变更失败</option>
								                                    <c:if test="${state==0}">
								                                    	<option value="0" selected="selected">全部</option>
								                                    </c:if>
								                                    <c:if test="${state==1}">
								                                    	<option value="1" selected="selected">变更中</option>
							                                        </c:if>
								                                    <c:if test="${state==2}">
								                                    	<option value="2" selected="selected">变更成功</option>
							                                        </c:if>
								                                    <c:if test="${state==3}">
								                                    	<option value="3" selected="selected">变更失败</option>
							                                        </c:if>
							                                    </select>
							                                </label>
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

                        </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>银行卡变更列表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>业务编号</th>
                                            <th>客户姓名</th>
                                            <th>申请来源</th>
                                            <th>证件号码 </th>
                                            <th>开户行</th>
                                            <th>开户地区</th>
                                            <th>银行卡号</th>
                                            <th>申请时间</th>
                                            <th>审批时间</th>
                                            <th>生效时间</th>
                                            <th>变更结果</th>
                                            <th>流程状态</th>
                                            <th>备注</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t" varStatus="index">
                                                <tr>
                                                    <td>${index.count}</td>
                                                    <td>${t.custId}</td>
                                                    <td>${t.custName}</td>
                                                    <td>${t.type}</td>
                                                    <td><fss:fmtData value="${t.certNo}"/></td>
                                                    <td>${t.bankName}</td>
                                                    <td>${t.bankCity}</td>
                                                    <td><fss:fmtData value="${t.cardNo}"/> </td>
                                                    <td><fss:fmtDate value="${t.createTime}"/></td>
                                                    <td><fss:fmtDate value="${t.passTime}" /></td>
                                                    <td><fss:fmtDate value="${t.effectTime}" /></td>
                                                    <td>
                                                    	<c:if test="${t.state==1}">
                                                    		变更中
                                                    	</c:if>
                                                    	<c:if test="${t.state==2}">
                                                    		变更成功
                                                    	</c:if>
                                                    	<c:if test="${t.state==3}">
                                                    		变更失败
                                                    	</c:if>
                                                    </td>
                                                    <td>
                                                    	<c:if test="${t.tradeState==1}">
                                                    		申请
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==2}">
                                                    		已审核
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==3}">
                                                    		图片已上传
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==4}">
                                                    		数据传到富友
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==5}">
                                                    		成功
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==6}">
                                                    		失败
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==99}">
                                                    		同步换卡信息表
                                                    	</c:if>
                                                    	<c:if test="${t.tradeState==100}">
                                                    		换卡流程完结
                                                    	</c:if>
                                                    </td>
                                                    <td>${t.respMsg}</td>
                                                    <td><a href="${contextPath}/img/wx.gif">查看图片</a></td>
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
<%@include file= "../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#cardChangeListForm"));
    });

</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>