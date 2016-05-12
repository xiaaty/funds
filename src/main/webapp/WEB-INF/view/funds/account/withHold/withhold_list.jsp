<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金管理--代扣管理--冠群驰骋投资管理(北京)有限公司</title>

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
           <li>资金管理</li>
           <li>代扣管理</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="holdList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/withholdApply/queryWithholdList"  method="post" id="withholdApplyForm">
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
	                                                     	<td class="tr" nowrap="nowrap">客户姓名:</td>
	                                                        <td nowrap="nowrap">
	                                                            <label class="input"  style="width:210px" >
	                                                                <input type="text" name="custName" value="${withholdBean.custName}">
	                                                            </label>
	                                                        </td>
	                                                 		<td class="tr" nowrap="nowrap">手机号码:</td>
	                                                        <td nowrap="nowrap">
	                                                            <label class="input"  style="width:150px" >
	                                                                <input type="text" name="custPhone" value="${withholdBean.custPhone}">
	                                                            </label>
	                                                        </td>
	                                                        <td class="tr" nowrap="nowrap">合同编号:</td>
	                                                        <td nowrap="nowrap">
	                                                            <label class="input"  style="width:150px" >
	                                                                <input type="text" name="bussinessContractNo" value="${withholdBean.bussinessContractNo}">
	                                                            </label>
	                                                        </td>
	                                                        <td class="tr" nowrap="nowrap">大区:</td>
	                                                        <td nowrap="nowrap">
	                                                            <label class="input"  style="width:200px" >
	                                                                <input type="text" name="bussinessArea" value="${withholdBean.bussinessArea}">
	                                                            </label>
	                                                        </td>
	                                                </tr>
	                                                <tr>
	                                                        <td class="tr">业务类型：</td>
			                                                <td>
			                                                 <input type="hidden" id="hiddenbussinessType" value="${withholdBean.bussinessType}"/>
				                                                <section style="width:100px">
		                                                    		<label class="select">
													                <select id="bussinessType" name ="bussinessType">
													                 <option value="">--不限--</option>
										                            <option value="1">线下出借合同</option>
										                            <option value="2">借款人还款</option>
										                            <option value="3">抵押标借款人还款</option>
										                            <option value="6">抵押权人放款</option>
													                </select>
													            </label>
													             </section>
	                                                		</td>
	                                                
	                                                 <td class="tr">代扣状态：</td>
			                                                <td>
		                                                    <input type="hidden" id="hiddenapplyStatus" value="${withholdBean.applyStatus}"/>
				                                                <section style="width:100px">
		                                                    		<label class="select">
													                <select id="applyStatus" name ="applyStatus">
													                   <option value="">--不限--</option>
											                            <option value="1">审核中</option>
											                            <option value="2">已代扣</option>
											                            <option value="3">取消</option>
											                            <option value="4">代扣中</option>
											                            <option value="5">失败</option>
											                            <option value="6">部分成功</option>
											                            <option value="99">人工对账</option>
													                </select>
													            </label>
													             </section>
	                                                </td>
	                                                 <td class="tr">第三方渠道:</td>
			                                                <td>
		                                                    <input type="hidden" id="hiddenthirdPartyType" value="${withholdBean.thirdPartyType}"/>
				                                                <section style="width:100px">
		                                                    		<label class="select">
													                <select id="thirdPartyType" name ="thirdPartyType">
													                 <option value="">--不限--</option>
											                            <option value="1">大钱</option>
											                            <option value="2">富友</option>
													                </select>
													            </label>
													             </section>
	                                                </td>
                                                     <td class="tr" width="90px">开户日期：</td>
			                                            <td colspan="3">
			                                                <section class="fl">
			                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" readonly="readonly" name="startTime" class="selectdate" placeholder="请选择时间" value="${withholdBean.startTime}">
			                                                    </label>
			                                                </section>
			                                                <span class="fl">&nbsp;至&nbsp;</span>
			                                                <section class="fl">
			                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" readonly="readonly"  name="endTime" class="selectdate" placeholder="请选择时间" value="${withholdBean.endTime}">
			                                                    </label>
			                                                </section>
			                                            </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <button class="btn btn-primary" type="button" onclick="verify();">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-071"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                             <h2>代扣管理</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <!--  <DIV>
                                            <button class="btn btn-default table-nobg-btn" id="btn_rech" type="button" >批量代扣</button>
                                     </div> -->
                                <div class="widget-body">
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1520px;">
                                    	<col width="20" />
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
                                        <col width="100"/>
                                        <col width="100"/>
                                    	<thead>
                                        <tr>
                                             <td align="left"><input type="checkbox" id="checkAll"/></td>
						                        <td>客户名称</td>
						                        <td>手机号码</td>
						                        <td>业务类型</td>
						                        <td>合同编号</td>
						                        <td>大区</td>
						                        <td>分公司名称</td>
						                        <td>顾问姓名</td>
						                        <th class='tr'>代扣金额</td>
						                        <th class='tr' style="padding-right:40px;">实际代扣金额</td>
						                        <td>代扣状态</td>
						                        <td>申请人</td>
						                        <td>申请时间</td>
						                        <td>审核人</td>
						                        <td>审核时间</td>
						                        <td>第三方渠道</td>
						                       <!--  <td>操作</td> -->
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="wd" varStatus="status">
									<tr>
										<td align="left"><input type="checkbox" class="checkBoxAll" value="${wd.id}"/></td>
										<td>${wd.custName}</td>
										<td>${wd.custPhone}</td>
										<td>
										    <c:if test="${wd.bussinessType == 1}">线下出借合同</c:if>
										    <c:if test="${wd.bussinessType == 2}">借款人还款</c:if>
										    <c:if test="${wd.bussinessType == 3}">抵押标借款人还款</c:if>
										    <c:if test="${wd.bussinessType == 6}">抵押权人放款</c:if>
	                                    </td>
	                                    <td>${wd.bussinessContractNo}</td>
	                                    <td>${wd.bussinessArea}</td>
	                                    <td>${wd.bussinessCompany}</td>
	                                    <td>${wd.counselorName}</td>
										<td class='tr' ><fmt:formatNumber value="${wd.drawAmount}" pattern="#,##0.00"/></td>
										<td class='tr' style="padding-right:40px;"><fmt:formatNumber value="${wd.factDrawAmount}" pattern="#,##0.00"/></td>
										<td>
										    <c:if test="${wd.applyStatus == 1}"><span style="color:olive">审核中</span></c:if>
										    <c:if test="${wd.applyStatus == 2}"><span style="color: green">已代扣</span></c:if>
										    <c:if test="${wd.applyStatus == 3}"><span style="color:gray;">取消</span></c:if>
										    <c:if test="${wd.applyStatus == 4}"><span style="color: green">代扣中</span></c:if>
										    <c:if test="${wd.applyStatus == 5}"><span style="color: red">失败</span></c:if>
										    <c:if test="${wd.applyStatus == 6}"><span style="color: red">部分成功</span></c:if>
										    <c:if test="${wd.applyStatus == 99}"><span style="color: red">人工对账</span></c:if>
	                                    </td>
	                                    <td>${wd.applyUserId}</td>
										<td><fmt:formatDate value="${wd.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${wd.reviewUserId}</td>
										<td><fmt:formatDate value="${wd.reviewTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>
										    <c:if test="${wd.thirdPartyType == 1}">大钱</c:if>
										    <c:if test="${wd.thirdPartyType == 2}">富友</c:if>
	                                    </td>
									<%-- 	<td>
												<c:if test="${wd.applyStatus == 1}">
		                                                <a href="${contextPath}/account/withhold/withholdReview?id=${wd.id}">代扣金额拆分</a>
												</c:if>
												<c:if test="${wd.applyStatus == 6}">
		                                                <a href="${contextPath}/account/withhold/withholdReviewGoon?id=${wd.id}">继续代扣</a>
												</c:if>
										</td> --%>
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
	     DT_page("rechtable", true, '${page.JSON}', $("#withholdApplyForm"));
		 selectedInit();
		 $("#checkAll").removeAttr("checked");
	 }); 
	 $('#checkAll').bind('click', function () {
       var that = this;
       $('.checkBoxAll').each(function () {
           this.checked = that.checked;
       });
   });
	   function selectedInit() {
			
       $("#bussinessType").val($("#hiddenbussinessType").val());
       $("#applyStatus").val( $("#hiddenapplyStatus").val());
       $("#thirdPartyType").val($("#hiddenthirdPartyType").val());
     }
	   $('#checkAll').bind('click', function () {
         var that = this;
         $('.checkBoxAll').each(function () {
             this.checked = that.checked;
         });
     });

     $('.checkBoxAll').each(function () {
         $(this).click(function () {
             if (this.checked == false) {
                 $("#checkAll").removeAttr("checked");
                 return;
             }
         });
     });
     
     function overReview(id){
     	 if(confirm("确定结束代扣？")){
     		 $.post("${contextPath}/account/withholdReviewOver?", {'id': id}, function (data) {
          		if(data.code == '0000'){
          			$("#withholdForm").submit();
 					return false;
 				} else {
 					alert(data.message);
 					return false;
 				}
         	 }, "json");
 		 }else{
 			 
 		 }
     }
     
     //批量代扣按钮
     $('#btn_rech').click(function () {
         var no = $('#rechtable tbody :checkbox:checked');
         if (no.size() == 0) {
             alert("请选择要代扣的申请！");
             return false;
         }

         var param = [];
         no.each(function () {
             param.push($(this).val());
         })
         
//	        var pageLoad =$.layer({
//			    type: 1,
//			    title: '',
//			    area: ['100', '100'],
//			    border: [0,0,'#44a9d5'], //认边框
//			    shade: [0.5, '#999'], //遮罩
//			    bgcolor:'',
//			    closeBtn: false,
//			    move: false,
//			    page: {
//			    	html:'<img src="${contextPath}/images/loading.gif">'
//			    }
//			});

         $.post("${contextPath}/account/withhold/withholdRech?", {'no': param.toString()}, function (data) {
//          	layer.close(pageLoad);
             if (data.code == '0000') {
					 var message = data.successCount + "件代扣成功，" +  data.failCount + "件代扣失败。" 
					 var html="";
					 if (data.failCustname001 != "" || data.failCustname002 != "" || data.message8 != "") {
						 message = message + "具体错误信息查看error区域：";
						 if (data.failCustname001 != "") {
							 message = message + '\n' + data.failCustname001 + "的代扣审核失败，" + data.message1;
							 if (html != "") {
								 html = html + '<br>';
							 }
							 html = html + data.failCustname001 + "的代扣审核失败，" + data.message1;
						 }
						 if (data.failCustname002 != "") {
							 message = message + '\n' + data.failCustname002 + "的代扣审核失败，" + data.message2;
							 if (html != "") {
								 html = html + '<br>';
							 }
							 html = html + data.failCustname002 + "的代扣审核失败，" + data.message2;
						 } 
						 if (data.message8 != "") {
							 if (html != "") {
								 html = html + '<br>';
							 }
							 message = message + '\n' + data.message8;
							 html = html + data.message8;
						 } 
					 } else {
						 html="代扣成功！";
					 } 
					 message=message.replace(/<br>/g,"\n");
					
                 $("#addErrorMsg").html(html);
                 $("html,body").animate({scrollTop: $("#addErrorMsg").offset().top - 400}, 0);
	
                 alert(message);
                 
                 if (html == '代扣成功！') {
                 	$("#withholdForm").submit();
                 	$("#checkAll").removeAttr("checked");
                 }
                 return false;
             } else {
             	var message = data.successCount + "件代扣成功，" +  data.failCount + "件代扣失败。 处理中断。。。" 
                 $("#addErrorMsg").html(html)
                 $("html,body").animate({scrollTop: $("#addErrorMsg").offset().top - 100}, 0);
                 alert(data.message);
             }
             $("#withholdForm").submit();
             $("#checkAll").removeAttr("checked");
             return false;
         }, "json");

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
	    			alert("请检查您输入的日期");
	    		}else{
	    			$("#withholdApplyForm").submit();
	    		}
	    	}else{
	    		var d = new Date();
	    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
	    		if(a[0].value>str){
	    			alert("请检查您输入的日期");
	    		}else{
	    			$("#withholdApplyForm").submit();
	    		}
	    	}
	    }
   </script>
</html>
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>