<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加银行信息--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../include/common_css_js.jsp"%>
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
    </style>
</head>
<body>
    
<%@include file="../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>银行信息</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="bankAddForm" action="${contextPath}/fund/savebank" method="post">
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>添加银行信息<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <div>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td align="left">银行名称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="bankName" value="${bank.bankName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">银行简称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="sortName" value="${bank.sortName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">银行编码：</td>
                                                            <td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="bankCode" value="${bank.bankCode}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">图标：</td>
                                                            <td>
                                                            	<a href="#">上传小图片</a>
                                                            	<%-- <div style="background:#fff;width:300px;overflow:hidden;"><input type="file" id="file_upload_1" 
                                                            	name="bankIcon"
                                                            	value="${bank.bankIcon}"/></div> --%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">限额页面：</td>
                                                            <td>
                                                            <a href="#">上传小页面</a>
                                                              <%--  <div style="background:#fff;width:300px;overflow:hidden;"><input type="file" id="file_upload_2" 
                                                               name="limitPage"
                                                               value="${bank.limitPage}"/></div> --%>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="btn_success">保存</button>
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="btn_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </form>

                </div>
            </section>
        </div>
    </div>

<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#btn_success").click(function () {
    	        if (validateCheck()) {
    	            /*if (!confirm("确认 修改商户信息吗?")) {
    	               return false;
    	            }*/
    	            $("#bankAddForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("修改成功!", '确认信息');
    	                        var parent_id=$("#parentId").val();
    	                        //自动跳转
    	                     //   parent.location.href="${contextPath}/sys/workassist/dictionary/${parent_id}";
    	                        parent.location.href="${contextPath}/fund/banklist";
    	                    } else {
    	                        return;
    	                    }
    	                }
    	            });
    	        }
    	    });
        });
    	//校验函数
    	function validateCheck() {
    		return true;
    	}
        
    	$("#btn_cancel").button().click(function() {
        	window.history.back();
        });
    	
    	
    		$("#file_upload_1").uploadify({
                //开启调试
                'debug' : false,
                //是否自动上传
                'auto':true,
                //超时时间
                'successTimeout':99999,
                //flash
                'swf': "<%=request.getContextPath()%>/js/uploadify/uploadify.swf",
                //不执行默认的onSelect事件
                'overrideEvents' : ['onDialogClose'],
                //文件选择后的容器ID
                //'queueID':'uploadfileQueue',
                //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
                'fileObjName':'multipartFile',
                //上传处理程序
                'uploader':'<%=request.getContextPath()%>/lend/addLendAnnex',
                'buttonText':'上传附件',
                //浏览按钮的背景图片路径
                'buttonImage':'upbutton.gif',
                //浏览按钮的宽度
                'width':'80',
                //浏览按钮的高度
                'height':'28',
                //expressInstall.swf文件的路径。
                //'expressInstall':'expressInstall.swf',
                //在浏览窗口底部的文件类型下拉菜单中显示的文本
                'fileTypeDesc':'支持的格式：',
                //允许上传的文件后缀
                'fileTypeExts':'*.jpg;*.jpge;*.gif;*.png',
                //上传文件的大小限制
                'fileSizeLimit':'3MB',
                //上传数量
               'queueSizeLimit' : 25,
                //每次更新上载的文件的进展
                'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
                     //有时候上传进度什么想自己个性化控制，可以利用这个方法
                     //使用方法见官方说明
                },
                //选择上传文件后调用
                'onSelect' : function(file) {
                          
                },
                //返回一个错误，选择文件的时候触发
                'onSelectError':function(file, errorCode, errorMsg){
                    switch(errorCode) {
                        case -100:
                            alert("上传的文件数量已经超出系统限制的"+$('#file_upload_'+id).uploadify('settings','queueSizeLimit')+"个文件！");
                            break;
                        case -110:
                            alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload_'+id).uploadify('settings','fileSizeLimit')+"大小！");
                            break;
                        case -120:
                            alert("文件 ["+file.name+"] 大小异常！");
                            break;
                        case -130:
                            alert("文件 ["+file.name+"] 类型不正确！");
                            break;
                    }
                },
                //检测FLASH失败调用
                'onFallback':function(){
                    alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
                },
                //上传到服务器，服务器返回相应信息到data里
                'onUploadSuccess':function(file, data, response){
                	var str = data;
                	str = str.replace('"','').replace('"','');
                    $('#annexPath_'+id).val(str);
                    $('#ck_'+id).attr("disabled",'false');
                    $('#ck_'+id).attr("checked",'true');
                    $(this).attr('disabled','false');
                    //下载按钮表示判断
                    var flg = $("#download_"+ id).val();
                    if (flg == undefined) {
                        var strHtml='<a  onClick="fileDownload('+id+');" id ="download_'+id+'">下载附件</a> '+
            						'<a  onClick="fileDelete('+id+');" id ="fileDelete_'+id+'">删除</a> ';
                        $("#file_upload_"+id).parent().after(strHtml);
                    } 
                },
                'onUploadStart': function (file) {
                	//在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
                    $("#file_upload_"+id).uploadify("settings", "formData", {'annexType':id});
                	$(this).attr('disabled','true');
                }
            });
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
     </script>
        
</body>

</html>