<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--P2P法人平台开户文件--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../../view/include/common_css_js.jsp"%>
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
<%@include file="../../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
           <li>入账管理</li>
            <li>P2P法人平台开户文件</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-221"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fstp/p2pCorporate"  method="post" id="corporateForm">
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
                                                     <tr>
                                                      <td class="tr" nowrap="nowrap">商户号:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="mchn" value="${map.mchn}">
                                                            </label>
                                                        </td>
                                                        
                                                        <td class="tr" nowrap="nowrap">企业名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="companyName" value="${map.companyName}">
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
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-507"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>P2P法人平台开户文件</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2600px;">
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<thead>
                                    	<tr>
                                    		<td>商户号</td>
	                                    	<td>流水</td>
	                                    	<td>企业名称</td>
	                                    	<td>注册日期</td>
	                                    	<td>法人姓名</td>
	                                    	<td>身份证件号</td>
	                                    	<td>手机号</td>
	                                    	<td>邮箱地址</td>
	                                    	<td>第三方支付公司ID</td>
	                                    	<td>操作类型</td>
	                                    	<td>营业执照登记号</td>
	                                    	<td>税务登记号</td>
	                                    	<td>组织机构代码</td>
	                                    	<td>平台用户名</td>
	                                    	<td>金账户登陆用户名</td>
	                                    	<td>备注</td>
	                                    	<td>用户属性</td>
	                                    	<td>开户银行许可证号</td>
	                                    	<td>机构信用代码证</td>
	                                    	<td>统一社会信用代码</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
                                        	<td>${t.mchn}</td>
	                                    	<td>${t.seqNo}</td>
	                                    	<td>${t.companyName}</td>
	                                    	<td>${t.registeredDate}</td>
	                                    	<td>${t.legalpersonName}</td>
	                                    	<td>${t.certNo}</td>
	                                    	<td>${t.mobile}</td>
	                                    	<td>${t.email}</td>
	                                    	<td>${t.thirdPartyPaymentId}</td>
	                                    	<td>
	                                    		<c:if test="${t.actionType=='ADD'}">增加</c:if>
		                                    	<c:if test="${t.actionType=='MOD'}">修改</c:if>
		                                    	<c:if test="${t.actionType=='DEL'}">删除</c:if>
	                                    	</td>
	                                    	<td>${t.licenseNumber}</td>
	                                    	<td>${t.taxNo}</td>
	                                    	<td>${t.organizationCode}</td>
	                                    	<td>${t.platformUsername}</td>
	                                    	<td>${t.goldAccLoginName}</td>
	                                    	<td>${t.remark}</td>
	                                    	<td>${t.userAttributes}</td>
	                                    	<td>${t.bankLicense}</td>
	                                    	<td>${t.orgCreditCode}</td>
	                                    	<td>${t.uniteCreditCode}</td>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#corporateForm"));
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
	    	$("#corporateForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>