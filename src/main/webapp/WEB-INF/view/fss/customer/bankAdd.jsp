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
                                    <span class="tip02 color03">”*“为必填项</span>
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
                                                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>银行名称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="bankName" value="${bank.bankName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>银行简称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="sortName" value="${bank.sortName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>银行编码：</td>
                                                            <td>
                                                               <label class="input">
                                                                    <input type="text" maxlength="50" name="bankCode" value="${bank.bankCode}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
									                        <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>图标:</td>
									                        <td colspan="5">
									                            <input type="hidden" name="bankIcon" id="bankIcon" value="">
									                            <div id="dict_div_uploadDivLogo" class="identitycard clearfix "></div>
									                        </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">限额页面:</td>
									                        <td colspan="5">
									                            <input type="hidden" name="limitPage" id="limitPage" value="">
									                            <div id="dict_div_uploadDivLimit" class="identitycard clearfix "></div>
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
<script src="${contextPath}/js/uploadify/jquery.uploadify.min.js" ></script>
<script src="${contextPath}/js/uploadify/uploadify.css" ></script>
<script src="${contextPath}/js/uploadify/uploadify.swf" ></script>
<script src="${contextPath}/js/uploadify/uploadify-cancel.png" ></script>




    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#btn_success").click(function () {
    	        if (validateCheck()) {
    	            $("#bankAddForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("添加成功!", '确认信息');
    	                        //自动跳转
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
    	
    	
    	 $(function(){
    	        fileUpload("dict_div_uploadDivLogo","bankIcon","*.jpg;*.png;*.gif;*.icon","bankUploadIcon");
    	        fileUpload("dict_div_uploadDivLimit","limitPage","*.html;*.htm","bankUploadHtml");
    	        fileUpload("dict_div_uploadDivTemplate","tmplatePage","*.html;*.htm","bankUploadHtml");
    	  }) 
    	
    	 function fileUpload(divID,inputId,fileTypeExts,uploadUrl){
		        $("#"+divID).uploadify({
		            'method'		: 'post',
		            'debug'         :false,
		            'preventCaching': true,
		            'buttonText' 	: '上传',
		            'fileSizeLimit' : '2048KB',
		            'cancelImg'		: '${contextPath}/js/uploadify/uploadify-cancel.png',
		            'fileTypeExts' 	: fileTypeExts,
		            'swf'		 	: '${contextPath}/js/uploadify/uploadify.swf',
		            'multi'         :false,
		            'onSelectError': function (file, errorCode, errorMsg) {
		                switch (errorCode) {
		                    case -110:
		                        alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#'+inputID).uploadify('settings', 'fileSizeLimit') + "大小！");
		                        break;
		                }
		            },
		            'uploader': '${contextPath}/fund/'+uploadUrl,
		            'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
		            'onUploadError':function(file, errorCode, errorMsg, errorString){
		                alert(errorMsg);
		                if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
		                        || errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
		                    return;
		                }
		                var msgText = "上传失败\n";
		                switch (errorCode) {
		                    case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
		                        msgText += "HTTP 错误\n" + errorMsg;
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
		                        msgText += "上传文件丢失，请重新上传";
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.IO_ERROR:
		                        msgText += "IO错误";
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
		                        msgText += "安全性错误\n" + errorMsg;
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
		                        msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
		                        msgText += errorMsg;
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
		                        msgText += "找不到指定文件，请重新操作";
		                        break;
		                    case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
		                        msgText += "参数错误";
		                        break;
		                    default:
		                        msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n"
		                                + errorMsg + "\n" + errorString;
		                }
		                alert(msgText);
		            },
		            'onUploadSuccess' : function(file, data, response) {
		                var ext = data.substr(data.length-3,3);
		                if(ext.toLowerCase() == "gif" || ext.toLowerCase() === "jpg" || ext.toLowerCase() === "png"){
		                    $("#bankIcon").val(data);
		                }else{
		                    $("#limitPage").val(data);
		                }
		            }
		        });
    }
    	
     </script>
        
</body>

</html>