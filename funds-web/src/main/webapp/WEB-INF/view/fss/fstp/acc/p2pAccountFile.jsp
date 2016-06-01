<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--P2P个人平台开户文件--冠群驰骋投资管理(北京)有限公司</title>
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
            <li>P2P个人平台开户文件</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-054"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fstp/projectInfo"  method="post" id="accountFileForm">
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
                                                        
                                                        <td class="tr" nowrap="nowrap">平台用户名:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="itemNo" value="${map.platformUsername}">
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
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-510"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>P2P个人平台开户文件</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1700px;">
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
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
                                    	<col width="100" />
                                    	<thead>
                                    	<tr>
                                    		<td>商户号</td>
	                                    	<td>平台注册流水</td>
	                                    	<td>平台用户名</td>
	                                    	<td>登陆用户名</td>
	                                    	<td>年龄</td>
	                                    	<td>户名</td>
	                                    	<td>证件类型</td>
	                                    	<td>证件号</td>
	                                    	<td>性别</td>
	                                    	<td>银行预留手机号</td>
	                                    	<td>地址</td>
	                                    	<td>用户属性</td>
	                                    	<td>注册日期</td>
	                                    	<td>第三方支付公司ID</td>
	                                    	<td>操作类型</td>
	                                    	<td>备注</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
                                        	<td>${t.mchn}</td>
	                                    	<td>${t.registeredSeqNo}</td>
	                                    	<td>${t.platformUsername}</td>
	                                    	<td>${t.loginUsername}</td>
	                                    	<td>${t.age}</td>
	                                    	<td>${t.accName}</td>
	                                    	<td>
	                                    		<c:if test="${t.certType==0}">居民身份证</c:if>
		                                    	<c:if test="${t.certType==1}">护照</c:if>
		                                    	<c:if test="${t.certType==2}">军官证</c:if>
		                                    	<c:if test="${t.certType==7}">其他</c:if>
	                                    	</td>
	                                    	<td>${t.certNo}</td>
	                                    	<td>
	                                    		<c:if test="${t.sex==0}">男</c:if>
		                                    	<c:if test="${t.sex==1}">女</c:if>
	                                    	</td>
	                                    	<td>${t.mobile}</td>
	                                    	<td>${t.address}</td>
	                                    	<td>
	                                    		<c:if test="${t.userProperties==1}">借款人</c:if>
		                                    	<c:if test="${t.userProperties==2}">贷款人</c:if>
	                                    	</td>
	                                    	<td>${t.registrationDate}</td>
	                                    	<td>${t.thirdPartyPaymentId}</td>
	                                    	<td>
	                                    		<c:if test="${t.actionType=='ADD'}">增加</c:if>
		                                    	<c:if test="${t.actionType=='MOD'}">修改</c:if>
		                                    	<c:if test="${t.actionType=='DEL'}">删除</c:if>
	                                    	</td>
	                                    	<td>${t.remark}</td>
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#accountFileForm"));
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
	    	$("#accountFileForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>