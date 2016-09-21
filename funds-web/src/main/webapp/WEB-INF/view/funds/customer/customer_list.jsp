<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户管理--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>客户信息管理</li>
            <li>客户信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="customerInfoMsg"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/funds/customer/customerList"  method="post" id="custForm">
                                    <div class="jarviswidget-editbox">
                                    </div>
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="100" />
                                                <col width="100" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr>
	                                                     <td class="tr">客户ID:</td>
	                                                        <td>
	                                                            <label class="input"  style="width:210px" >
	                                                                <input type="text" name="id" value="${map.id}"/>
	                                                            </label>
	                                                     </td>
	                                                     <td class="tr">客户姓名:</td>
	                                                        <td>
	                                                            <label class="input"  style="width:210px" >
	                                                                <input type="text" name="name" value="${map.name}"/>
	                                                            </label>
	                                                     </td>
	                                                     <td class="tr">证件号码：</td>
	                                                     <td>
	                                                         <label class="input" style="width:210px" >
	                                                             <input type="text" name="certNo" value="${map.certNo}" />
	                                                         </label>
	                                                     </td>
                                                    </tr>
                                                    <tr>
                                                       <td class="tr">手机号码：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="mobile" value="${map.mobile}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">开户日期：</td>
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
                
                
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-33"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>客户核心信息列表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2950px;">
                                        <col width="50" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="300" />
                                        <thead>
                                        <tr>
                                              <td></td>
                                              <td>客户编号</td>
                                              <td>客户姓名</td>
                                              <td>客户手机号</td>
                                              <td>证件类型</td>
                                              <td>证件号码</td>
                                              <td>证件签发日期</td>
                                              <td>证件失效日期</td>
                                              <td>性别</td>
                                              <td>地址</td>
                                              <td>生日</td>
                                              <td>是否有效</td>
                                              <td>银行名称</td>
                                              <td>银行卡号</td>
                                              <td>创建日期</td>
                                              <td>修改日期</td> 
                                              <td>操作</td> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="t" varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.id}</td>
                                                    <td>${t.customerName}</td>
                                                    <td>${t.mobilePhone}</td>
                                                    <td>${t.certType==1?"身份证":"护照"}</td>
                                                    <td>${t.certNo}</td>
                                                    <td>${t.certIssueDate}</td>
                                                    <td>${t.certFailDate}</td>
                                                    <td>${t.sex==1?"男":"女"}</td>
                                                    <td>${t.address}</td>
                                                    <td>${t.birthdate}</td>
                                                    <td>${t.isvalid==0?"有":"没有"}</td>
                                                    <td>${t.bankLongName}</td>
                                                    <td>${t.bankNo}</td>
                                                    <td><fss:fmtDate value="${t.createTime}" /></td>
                                                    <td><fss:fmtDate value="${t.modifyTime}" /></td>
                                                    <td>
                                                        <a  onclick="queryFor(${t.id})">核对银行卡信息</a>&nbsp;&nbsp;
                                                        <a  onclick="checkState(${t.id})">核对用户状态</a>&nbsp;&nbsp;
                                                        <a  id="ch" style="display: none" onclick="dropAccount(${t.id})">销户确认</a>&nbsp;&nbsp;
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
<%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
 <script type="application/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#custForm"));
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
	    			$("#custForm").submit();
	    		}
	    	}else{
	    		var d = new Date();
	    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
	    		if(a[0].value>str){
	    			JAlert("请检查您输入的日期","提示消息");
	    		}else{
	    			$("#custForm").submit();
	    		}
	    	}
	    }
    function queryFor(id) {
        $.ajax({
            url:"${contextPath}/checkCustomerInfo/queryForFuiou/"+id,
            method:"post",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dateType:"json",
            success : function (data){
                if (data.code == '0000') {
                    alert(data.msg);
                }else{
                    alert(data.msg);
                }
            }
        })
    }
    function checkState(id) {
        $.ajax({
            url:"${contextPath}/checkCustomerInfo/checkState/"+id,
            method:"post",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dateType:"json",
            success : function (data){
                if (data.code == '0000') {
                    alert(data.msg);
                }else if(data.code == '0002'){
                    $("#ch").show();
                    alert(data.msg);
                }else{
                    alert(data.msg);

                }
            }
        })
    }
    function dropAccount(id) {
        $.ajax({
            url:"${contextPath}/checkCustomerInfo/removeUser/"+id,
            method:"post",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dateType:"json",
            success : function (data){
                if (data.code == '0000') {
                    $("#ch").hide();
                    alert(data.msg);
                }else{
                    alert(data.msg);

                }
            }
        })
    }
</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>