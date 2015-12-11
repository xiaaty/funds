<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>内审系统--出借管理</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
<%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
<%@include file="/WEB-INF/jsp/inc/common_css_js.inc"%>
<link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
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
</style>
</head>
<body>
	<!-- HEADER -->
	<%@include file="/WEB-INF/jsp/inc/menu.inc"%>

	<div id="main" role="main">

		<!-- RIBBON -->
		<div id="ribbon">
			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li>出借管理</li>
			</ol>
			<!-- end breadcrumb -->
		</div>

		<div id="content">
			<section id="widget-grid" class="">
				<div class="row">
					<!-- NEW WIDGET START -->
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="jarviswidget" id="wid-id-21" data-widget-deletebutton="false" data-widget-editbutton="false">
							<header>
								<h2>快速搜索</h2>
							</header>
							<!-- widget div-->
							<div>
								<form class="smart-form" id="lendListForm" method="post" action="${contextPath}/lend/lendList">
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
												<col width="300" />
												<col width="100" />
												<col />
												<tbody>
											<tr >
                                                <td class="lh32 tr">出借编号：</td>
                                                <td>
                                                    <label class="input" style="width:210px">
                                                        <input type="text" name="agreeNo" value="${lendQuery.agreeNo }"/>
                                                    </label>
                                                </td>
                                                <td class="lh32 tr">客户姓名：</td>
                                                <td>
                                                    <label class="input" style="width:300px">
                                                        <input type="text" name="customerName" value="${lendQuery.customerName }" />
                                                    </label>
                                                </td>
                                                <td class="lh32 tr">身份证号：</td>
                                                <td>
                                                    <label class="input" style="width:210px">
                                                        <input type="text" name="certNo" value="${lendQuery.certNo }" />
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr >
                                                <td class="lh32 tr">大&nbsp;&nbsp;&nbsp;区：</td>
                                                <td>
                                                    <label class="input" style="width:210px">
                                                        <input type="text" name="largeArea" value="${lendQuery.largeArea }" />
                                                    </label>
                                                </td>
                                                <td colspan="4"></td>
                                            </tr>
												</tbody>
											</table>
										</div>
										<footer>
											<button class="btn btn-primary" type="submit">查&nbsp;&nbsp;&nbsp;询</button>
										</footer>
									</div>
									<!-- end widget content -->
								</form>
							</div>


						</div>
						<!-- 
							 -->
						<!-- NEW WIDGET START -->
						<!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
						 <div class="clearfix ml20 mb5" id="addErrorMsg" style="color:red;"></div>
						<div class="jarviswidget jarviswidget-color-darken" id="wid-id-22"
							data-widget-deletebutton="false" data-widget-editbutton="false">
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>出借管理列表</h2>
							</header>
							<!-- widget div-->
							<div>
								<form class="smart-form">
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
									</div>
									<!-- end widget edit box -->
									<!-- widget content -->
									<div class="widget-body">
										<div class="widget-body-nobg-toolbar"
											style="overflow: hidden;">
											<button class="btn btn-default fl table-nobg-btn" 
												id="btn_upload">
												<i class="fa fa-sign-in"></i>&nbsp;导入
											</button>
											<button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add">
												<i class="fa fa-plus"></i>&nbsp;添加
											</button>
											<button class="btn btn-default fl table-nobg-btn"  id="btn_modify" type="button"><i class="fa fa-copy"></i>&nbsp;修改</button>
											<button class="btn btn-default fl table-nobg-btn"  id="btn_delete" type="button"><i class="fa fa-cut"></i>&nbsp;删除</button>
										</div>
										<table id="borrow-rep-table1" class="table table-bordered tc"
											style="min-width: 1800px">
											<thead>
												<tr class="b">
													<td><input type="checkbox" id="checkAll"/></td>
													<td>出借编号</td>
													<td>客户姓名</td>
													<td>身份证号</td>
													<td>电话</td>
													<td>产品</td>
													<td>本金</td>
													<td>投资期限</td>
													<td>年化收益率</td>
													<td>划扣时间</td>
													<td>大区</td>
												</tr>
											</thead>
											<tbody class="f12">
												<c:forEach var="lend" items="${page.list}">
													<tr>
														<td><input type="checkbox" class="checkBoxAll" value="${lend.id}"/></td>
														<td><a href="${contextPath}/lend/lendDetail?id=${lend.id }">${lend.agreeNo }</a></td>
														<td>${lend.customerName }</td>
														<td>${lend.certNo }</td>
														<td>${lend.mobilePhone }</td>
														<td>${lend.lendType }</td>
														<td>${lend.lendAmount}</td>
														<td>${lend.lendTerm }</td>
														<td>${lend.annualYield }</td>
														<td>${lend.estimateTime }</td>
														<td>${lend.largeArea }</td>
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

	<!-- add role dialog  -->
			<div id="upload" title="<div class='widget-header'><h4 class='b'>导入</h4></div>">
				<form id="uploadForm" method="post" action="${contextPath}/lend/importLend" enctype="multipart/form-data">
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

	<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc"%>
	<script src="${contextPath}/js/jquery.form.js" ></script>
	<script src="${contextPath}/js/jquery.alerts.js" ></script>


	<script>
		$(document).ready(function() {
						
			pageSetUp();

			DT_page("borrow-rep-table1", true,'${page.JSON}', $("#lendListForm"));

			/*
			 * CONVERT DIALOG TITLE TO HTML
			 * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
			 */
			
			 var add_dialog = $("#upload").dialog({
		            autoOpen : false,
		            width : 480,
		            resizable : false,
		            modal : true,
		            buttons : [{
		                html : "确&nbsp;&nbsp;&nbsp;&nbsp;认",
		                "class" : "btn btn-default",
		                click : function() {
						var file_pa = $("#file_pa").val();
							if (file_pa == "") {
								alert("请选择导入的文件！");
								return;
							}
							importLend();
		                }
		            }, {

		                html : "取&nbsp;&nbsp;&nbsp;&nbsp;消",
		                "class" : "btn btn-default",
		                click : function() {
		                    $(this).dialog("close");
		                }
		            }]
		        });
		        
		        function importLend(){
	                $("#uploadForm").ajaxSubmit({
	                    beforeSubmit:function(){
	                        var button = $("#upload").next().find(":button")
	                        if(button.size() == 3){
	                            button.eq(0).off().remove();
	                        }
	                        button.attr("disabled","disabled");
	                    },
	                    //upload
	                    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	                    dataType:"json",
	                    success:function(data){	
	                        if (data.code == '0000') {
	                            jAlert(data.message + "!", '确认信息');
	                            $("#lendListForm").submit();
	                            add_dialog.close();
	                        }else if (data.code == '0009') {
	                        	$("#addErrorMsg").html("导入失败! " +data.agreeNo+"出借编号已经存在！请确认文件后重新导入。");
	                        	var buttons = $("#upload").next().find(":button");
	                        	buttons.removeAttr("disabled");
	                        	return;
	                        } else {
	                            alert(data.message + "!");
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
	                    }
	                });
		        }


		        $("#btn_upload").button().click(function() {
		            add_dialog.dialog("open");
		            var button = $("#upload").next().find(":button")
		            if(button.size() == 3){
		                button.eq(0).off().remove();
		            }
		            return false;
		        });
			

			$('.selectdate').datetimepicker({
				language : 'zh-CN',
				weekStart : 1,
				autoclose : 1,
				format : 'yyyy-mm-dd',
				todayHighlight : 1,
				startView : 2,
				minView : 2,
				forceParse : 0
			});
			
			$('#checkAll').bind('click',function(){
	            var that=this;
	            $('.checkBoxAll').each(function(){
	                this.checked=that.checked;
	            });
	        });
	        
	        $('.checkBoxAll').each(function(){
	            $(this).click(function() {
		            if (this.checked == false ) {
		            	$("#checkAll").removeAttr("checked");
		            	return;
		            }
	            });
	        });
	        
	      	//删除按钮
	        $('#btn_delete').click(function(){
	        	var aid=$('#borrow-rep-table1 tbody :checkbox:checked');
	        	if(aid.size()==0){
	        		alert("请选择要删除的合同！");
	        		return false;
	        	}
	            if(!confirm("确认删除出借合同吗?")){
	                return false;
	            }
	            var param=[];
	            aid.each(function(){
	                param.push($(this).val());
	            })
	        	$.post("${contextPath}/lend/delLendAgreement?",{'aid':param.toString()},function(data){
	        		if(data>0){
	        			alert("删除成功!");
	        			$("#lendListForm").submit();
	        			return false;
	        		}
	        		return false;
	        	});

	        });
	        $('#btn_add').click(function() {
				window.open("${contextPath}/lend/lendAdd","_self");
				//window.location.href = '${contextPath}/lend/lendAdd';
			});
	        
	        //修改按钮
	        $('#btn_modify').click(function(){
	        	var no=$('#borrow-rep-table1 tbody :checkbox:checked');
	        	if(no.size()!=1){
	        		alert("请选择要修改的合同！");
	        		return false;
	        	}

	            var param=[];
	            no.each(function(){
	                param.push($(this).val());
	            })
	            window.open("${contextPath}/lend/lendModify?id=" + param.toString()+"&"+new Date().getTime() ,"_self");
	        });
		});

		
	</script>

</body>
</html>
