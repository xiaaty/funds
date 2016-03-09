<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--客户列表--冠群驰骋投资管理(北京)有限公司</title>

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
       
        <ol class="breadcrumb">
            <li>客户信息管理</li>
            <li>客户列表</li>
        </ol>
    </div>
    <div id="content">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form" id="Form" action="" method="post" >
                                    <div class="jarviswidget-editbox"></div>
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <tbody>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">姓名:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="customerName" value="${customer.customerName}">
                                                            </label>
                                                        </td>
                                                          
                                                        <td class="tr" nowrap="nowrap">客户类型:</td>
                                                        <td>
                                                            <select id = "customerType" name = "customerType">
										                    	<option value="">请选择</option>
										                    	<option  <c:if test="${customer.customerType==1}"> selected="selected" </c:if>  value="1">借款用户</option>
										                    	<option  <c:if test="${customer.customerType==2}"> selected="selected" </c:if> value="2" >借款共借人</option>
										                    	<option  <c:if test="${customer.customerType==3}"> selected="selected" </c:if> value="3" >线下出借用户</option>
										                    	<option  <c:if test="${customer.customerType==4}"> selected="selected" </c:if> value="4" >线上出借注册用户</option>
										                    	<option  <c:if test="${customer.customerType==8}"> selected="selected" </c:if> value="8" >线下赎回接标紧急用户（内部用）</option> 
										                    </select>
                                                        </td>
                                                         <td class="tr" nowrap="nowrap">姓别:</td>
                                                        <td nowrap="nowrap">
                                                            <select id = "sex" name = "sex">
										                    	<option value="">请选择</option>
										                    	<option <c:if test="${customer.sex==1}">selected="selected"</c:if> value="1">男</option>
			                       								<option <c:if test="${customer.sex==2}">selected="selected"</c:if> value="2">女</option>  
										                    </select>
                                                        </td> 
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                            <a title="添加选择用户" href="#" onclick="selectUserS()" target="_self" class="btn01 btn"><span class="btn">添加选择用户<span class="icon04 icon"></span></span></a>
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
                            <h2>客户信息管理列表(<span class="emphasis emphasis_txtx01 pr5">列表只显示已通过实名认证的客户</span>)</h2>
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
                               <!--  <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;新增银行</button>
                                </div> -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <thead>
                                        <tr>
                                          <th align="center">请选择</th>
                                          <th align="center">客户姓名</th>
		                                  <th align="center">客户类型</th>
		                                  <th align="center">客户电话</th>
		                                  <th align="center">证件类型</th>
		                                  <th align="center">证件号码</th>
		                                  <th align="center">性别</th>
		                                  <th align="center">出生日期</th>
		                                  <th align="center">学历</th>
		                                  <th align="center">婚姻状况</th>
		                                  <th align="center">紧急联系人</th>
		                                  <th align="center">紧急联系人电话</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="record">
                                          <tr align="center" class="gvItem">
		                                          <td>
		                                          	<input type="radio" name="selectUser" value="${record.customerName}" uid="${record.id}" uphone = "${record.mobilePhone}" uidNo = "${record.certNo}" />
		                                          </td> 
		                                          <td>
		                                          	${record.customerName}
		                                          </td>
			                                      <td>
			                                        <c:if test="${record.customerType==1}">借款用户 </c:if>
							                   		<c:if test="${record.customerType==2}">借款共借人</c:if>
							                   		<c:if test="${record.customerType==3}">线下出借用户</c:if>
							                   		<c:if test="${record.customerType==4}">线上出借注册用户</c:if>
							                   		<c:if test="${record.customerType==8}">线下赎回接标紧急用户（内部用）</c:if>
			                                       </td>
			                                       <td>${record.mobilePhone}</td>
			                                       <td>
			                                        <c:if test="${record.certType==1}">身份证 </c:if>
							                   		<c:if test="${record.certType==2}">护照</c:if>
							                   		<c:if test="${record.certType==3}">驾照</c:if>
							                   		<c:if test="${record.certType==4}">军人证</c:if>
			                                        </td>
			                                        <td>${record.certNo}</td>
			                                        <td>
			                                            <c:if test="${record.sex==1}">男 </c:if>
							                   		    <c:if test="${record.sex==2}">女</c:if>
			                                        </td>
		                                          	<td>${record.birthdate}</td>
		                                            <td>${record.education}</td>
			                                        <td>
			                                          	<c:if test="${record.maritalStatus==1}">未婚 </c:if>
								                   		<c:if test="${record.maritalStatus==2}">已婚</c:if>
								                   		<c:if test="${record.maritalStatus==3}">离异</c:if>
								                   		<c:if test="${record.maritalStatus==4}">丧偶</c:if>
								                   	</td>
								                   	<td>${record.emerContact}</td>
								                   	<td>${record.emerContactMobile}</td>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
    });

    //添加
    $("#btn_add").button().click(function() {
    	window.open("${contextPath}/fund/banktoadd","_self");
    });
    
  	//确认选择用户
    function selectUserS(){
		var rchecked = 0;
		$(".gvItem input:radio").each(function(){
			if($(this).attr("checked")){
				rchecked++; 
				if(window.ActiveXObject){ //IE
					window.returnValue = {"uid":$(this).attr("uid"),"realName":this.value,"phone":$(this).attr("uphone"),"certNo":$(this).attr("uidNo")};
				    window.close();  
				}else{ //非IE   
			        if(window.opener) {  
			            window.opener.setValue($(this).attr("uid"),this.value,$(this).attr("uphone"),$(this).attr("uidNo")) ;   
			        }  
			        window.close();  
			    }    
			}
		});
		if(rchecked == 0){
			alert('请选择借款用户');
			return;
		} 
		window.close();
	} 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>