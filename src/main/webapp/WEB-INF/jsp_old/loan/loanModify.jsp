<%--
  Created by IntelliJ IDEA.
  User: 于泳
  Date: 2014/12/4
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统--借款管理--详细借款</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
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
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>

    <div id="main" role="main">
        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>借款管理</li>
                <li>修改借款</li>
            </ol>
            <!-- end breadcrumb -->
        </div>
        <!-- <div class="row mr10">
            <div class="fr">
                <button class="btn btn-default table-nobg-btn" id="returnParent" type="button"><i class="fa  fa-mail-reply mr5"></i>返回上一页</button>
            </div>
        </div> -->
        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                   <form id ="formform" action="${contextPath}/loan/loanModify" method="post" >
                    <input  type="hidden"  name="customerInfoBean.id" value = "${loanDetailBean.customerInfoBean.id}"/>
                    <input  type="hidden"  name="contractInfoBean.id" value = "${loanDetailBean.contractInfoBean.id}"/>
                    <input  type="hidden"  name="loanBankInfoBean.id" value = "${loanDetailBean.loanBankInfoBean.id}"/>
                    <input  type="hidden"  name="backBankInfoBean.id" value = "${loanDetailBean.backBankInfoBean.id}"/>
                    <input  type="hidden"  name="organizationInfoBean.id" value = "${loanDetailBean.organizationInfoBean.id}"/>
                    
                    
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-181"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2><i class="fa fa-edit pr10"></i>客户信息<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
										<div class="widget-body no-padding">
											<div class="mt10 mb10">
												<table class="table">
													<col width="120" />
												    <col />
												    <col width="140" />
												    <col />
												    <col width="120" />
												    <col />
												    <col width="120" />
												    <col />
												    <tbody>
												    <tr></tr>
												    <tr>
												        <td class="tr">客户姓名：</td>
												        <td>
												            <label class="input">
												                <input type="text" class="input01" maxlength="20" name= "customerInfoBean.customerName"
												                 value="${loanDetailBean.customerInfoBean.customerName}">
												            </label>
												        </td>
												        <td class="tr">移动电话：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input01" maxlength="12" name= "customerInfoBean.mobilePhone"
												                 value="${loanDetailBean.customerInfoBean.mobilePhone}">
												            </label>
												        </td>
												        <td class="tr">国&nbsp;&nbsp;籍：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input01" maxlength="10" name= "customerInfoBean.nationality"
												                 value="${loanDetailBean.customerInfoBean.nationality}">
												            </label>
												        </td>
												        <td class="tr">证件类型：</td>
												        <td>
												            <label class="select">
												           		 <input type="hidden" id="hiddencertType" value="${loanDetailBean.customerInfoBean.certType}" />
												                <select id="certType" name ="customerInfoBean.certType">
												                	<option value="1" >身份证</option>
												                    <option value="2" >护照</option>
												                </select>
												            </label>
												        </td>
												    </tr>
												    <tr>
												        <td class="tr">证件号码：</td>
												        <td>
												            <label class="input">
												                  <input type="text" class="input01" maxlength="18" id="certNo" name= "customerInfoBean.certNo"
												                  value="${loanDetailBean.customerInfoBean.certNo}">
												            </label>
												        </td>
												        <td class="tr">发证机关所在地：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input01" maxlength="100" name= "customerInfoBean.certAddress"
												                  value="${loanDetailBean.customerInfoBean.certAddress}">
												            </label>
												        </td>
												        <td class="tr">签发日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text"  value="${loanDetailBean.customerInfoBean.certIssueDate}" 
												                maxlength="10" name=customerInfoBean.certIssueDate class="selectdate" placeholder="请选择时间">
												            </label>
												        </td>
												        <td class="tr">失效日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text" value="${loanDetailBean.customerInfoBean.certFailDate}"
												                 maxlength="10" name="customerInfoBean.certFailDate" class="selectdate" placeholder="请选择时间">
												            </label>
												        </td>
												    </tr>
												    <tr>
												        <td class="tr">性&nbsp;&nbsp;别：</td>
												        <td>
												            <label class="select">
												            <input type="hidden" id="hiddensex" value="${loanDetailBean.customerInfoBean.sex}" />
												                <select id="sex" name="customerInfoBean.sex">
												                    <option value="1">男</option>
												                    <option value="2">女</option>
												                </select>
												            </label>
												        </td>
												        <td class="tr">出生日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text" id="birthdate" value="${loanDetailBean.customerInfoBean.birthdate}"
												                name="customerInfoBean.birthdate" class="selectdate" placeholder="请选择时间">
												            </label>
												        </td>
												        <td class="tr">学&nbsp;&nbsp;历：</td>
												        <td>
												            <label class="select">
												                <input type="hidden" id="hiddeneducation" value="${loanDetailBean.customerInfoBean.education}" />
												                <select id="education"  name="customerInfoBean.education">
												                    <option value="0">请选择</option>
												                    <option value="1">高中及以下</option>
												                    <option value="2">大专</option>
												                    <option value="3">本科</option>
												                    <option value="4">研究生</option>
												                </select>
												            </label>
												        </td>
												        <td class="tr">婚姻状况：</td>
												        <td>
												            <label class="select">
												        	  <input type="hidden" id="hiddenmaritalStatus" value="${loanDetailBean.customerInfoBean.maritalStatus}" />
												                <select id="maritalStatus" name = "customerInfoBean.maritalStatus">
												                    <option value="0">未婚</option>
												                    <option value="1">已婚</option>
												                    <option value="2">离异</option>
												                </select>
												            </label>
												        </td>
												    </tr>
												
												    <tr>
												        <td class="tr">电子邮箱：</td>
												        <td>
												            <label class="input">
												               <input type="text" class="input01" maxlength="50" value="${loanDetailBean.customerInfoBean.email}" name= "customerInfoBean.email">
												            </label>
												        </td>
												        <td class="tr">邮&nbsp;&nbsp;编：</td>
												        <td>
												            <label class="input">
												                <input type="text" class="input01" maxlength="10" value="${loanDetailBean.customerInfoBean.zipCode}" name= "customerInfoBean.zipCode">
												            </label>
												        </td>
												        <td colspan="4"></td>
												    <tr>
												        <td class="tr">通讯地址：</td>
												        <td colspan="7" class="lh32">
												            <span class="fl tr" style="width: 50px">国家</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" value="${loanDetailBean.customerInfoBean.addrCountry}" name= "customerInfoBean.addrCountry">
													        </label>
												            <span class="fl tr" style="width: 80px">省(直辖市)</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" value="${loanDetailBean.customerInfoBean.addrProvince}" name= "customerInfoBean.addrProvince">
													        </label>
												            <span class="fl tr" style="width: 50px">市(州)</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" value="${loanDetailBean.customerInfoBean.addrCity}" name= "customerInfoBean.addrCity">
													        </label>
												            <span class="fl tr" style="width: 50px">区(县)</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" value="${loanDetailBean.customerInfoBean.addrTown}" name= "customerInfoBean.addrTown">
													        </label>
													    </td>
												           
												    </tr>
												    <!-- 
												    <tr>
												        <td class="tr">通讯地址：</td>
												        <td colspan="7" class="lh32">
												            <span class="fl">国家</span>
												            <select name="customerInfoBean.addrCountry" id="country" class="select01 fl" style="width:176px">
												                <option value="1">中国</option>
												                <option value="2">美国</option>
												            </select>
												            <span class="fl">省(直辖市)</span>
												
												            <select name="customerInfoBean.addrProvince" id="province" class="select01 fl" style="width:180px">
												                <option value="0">请选择</option>
												                <option value="1">北京</option>
												                <option value="2">上海</option>
												            </select>
												            <span class="fl">市(州)</span>
												            <select name="customerInfoBean.addrCity" id="area" class="select01 fl" style="width:180px">
												                <option value="0">请选择</option>
												                <option value="1">朝阳区</option>
												                <option value="2">东城区</option>
												            </select>
												            <span class="fl">区(县)</span>
												            <label class="input fl">
												                <input class="input01" style="width: 282px;" name="customerInfoBean.addrTown" id="addrTown" maxlength="50" >
												            </label>
												        </td>
												    </tr>
												     -->
												    </tbody>
												</table>
												<div class="loan_line">共借人</div>
												<table class="table bdr_2">
												    <col width="100" />
												    <col width="230" />
												    <col width="110" />
												    <col width="230" />
												    <col width="120" />
												    <col width="230" />
												    <col />
												    <tbody id="mens">
												    <c:forEach var="loanPersonListBean" items="${loanDetailBean.loanPersonListBeanList}">
													    <tr class="input_data">
													        <td class="tr">共借人：</td>
													        <td>
													            <label class="input">
													                <input class="input01" name="commLoanPersonName" maxlength="40" value="${loanPersonListBean.commLoanPersonName}">
													            </label>
													        </td>
													        <td class="tr">共借人电话：</td>
													        <td>
													            <label class="input">
													               <input class="input01" name="commLoanPersonPhone" maxlength="11" value="${loanPersonListBean.commLoanPersonPhone}">
													            </label>
													        </td>
													        <td class="tr">共借人身份证：</td>
													        <td>
													            <label class="input" style="width:220px">
													               <input class="input01" name="commLoanPersonCertNo" maxlength="20" value="${loanPersonListBean.commLoanPersonCertNo}">
													            </label>
													        </td>
													        <td><a class="delete_mens">删除</a></td>
													    </tr>
													 </c:forEach>
												    </tbody>
												</table>
									
												<div class="change">
												    <button class="btn btn-success ml30 mb10 lh200 pr15 pl15" type="button" id="add_mens">增加</button>
												</div>
											</div>
										</div>
                                    
                                    </div>
                                </div>
                            </div>
                            <div class="jarviswidget" id="wid-id-182" data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>合同信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10">
							                    <table class="table">
							                    	<col width="120" />
												    <col />
												    <col width="140" />
												    <col />
												    <col width="120" />
												    <col />
												    <col width="120" />
												    <col />
							                        <tbody>
							                        <tr>
							                            <td class="tr">合同编号：</td>
							                            <td>
							                                <label class="input">
							                                     <input class="input01"  name="contractInfoBean.contractNo" value="${loanDetailBean.contractInfoBean.contractNo}"  maxlength="30" readonly="readonly" >
							                                </label>
							                            </td>
							                            <td class="tr">借款类型：</td>
							                            <td>
							                                <label class="select">
							                                <input type="hidden" id="hiddenloanType" value="${loanDetailBean.contractInfoBean.loanType}" />
							                                    <select id="loanType" name="contractInfoBean.loanType">
							                                        <option value="0">请选择</option>
							                                        <option value="1">信用贷款</option>
							                                        <option value="2">抵押贷款</option>
							                                        <option value="3">质押贷款</option>
							                                        <option value="4">混合贷款</option>
							                                    </select>
							                                </label>
							                            </td>
							                            <td class="tr">借款方式：</td>
							                            <td>
							                                <label class="select">
							                                	<input type="hidden" id="hiddenloanMode" value="${loanDetailBean.contractInfoBean.loanMode}" />
							                                    <select id="loanMode" name="contractInfoBean.loanMode">
							                                        <option value="0">请选择</option>
							                                        <option value="1">新客户</option>
							                                        <option value="2">循环贷</option>
							                                    </select>
							                                </label>
							                            </td>
							                            <td class="tr">合同金额：</td>
							                            <td>
							                                <label class="input">
							                                     <input class="input02"  name="contractInfoBean.contractAmount" value="${loanDetailBean.contractInfoBean.contractAmount}" maxlength="15" >
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">借款年利率：</td>
							                            <td>
							                                <label class="input">
							                                     <input id="loanRate" class="input02"  name="contractInfoBean.loanRate" maxlength="5" value="${loanDetailBean.contractInfoBean.loanRate}" >
							                                </label>
							                            </td>
							                            <td class="tr">期&nbsp;&nbsp;&nbsp;&nbsp;限：</td>
							                            <td>
							                                <label class="input">
							                                   <input class="input01" id="limitTimes"  name="contractInfoBean.limitTimes" maxlength="3" value="${loanDetailBean.contractInfoBean.limitTimes}">
							                                </label>
							                            </td>
							                            <td class="tr">还款方式：</td>
							                            <td>
							                                <label class="select">
							                               	 <input type="hidden" id="hiddenbackMoneyType" value="${loanDetailBean.contractInfoBean.backMoneyType}" />
							                                    <select id="backMoneyType" name="contractInfoBean.backMoneyType">
							                                        <option value="0">请选择</option>
							                                        <option value="1">等额等息</option>
							                                        <option value="2">等额本息</option>
							                                        <option value="3">先息后本</option>
							                                        <option value="4">自定义还款</option>
							                                    </select>
							                                </label>
							                            </td>
							                            <td class="tr">是否代扣：</td>
							                            <td>
							                                <label class="select">
							                                <input type="hidden" id="hiddenisWithholding" value="${loanDetailBean.contractInfoBean.isWithholding}" />
							                                    <select id="isWithholding" name="contractInfoBean.isWithholding">
							                                        <option value="0">请选择</option>
							                                        <option value="1">是</option>
							                                        <option value="2">否</option>
							                                    </select>
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">合同签署日：</td>
							                            <td>
							                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
							                                    <input type="text" name="contractInfoBean.contractSignDate" value="${loanDetailBean.contractInfoBean.contractSignDate}" maxlength="10" class="selectdate" placeholder="请选择时间">
							                                </label>
							                            </td>
							                            <td class="tr">应收总服务费：</td>
							                            <td>
							                                <label class="input">
							                                    <input class="input02"  name="contractInfoBean.totalServiceFee"  value="${loanDetailBean.contractInfoBean.totalServiceFee}"maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">应收总利息：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="totalAccrual" class="input02"  name="contractInfoBean.totalAccrual" value="${loanDetailBean.contractInfoBean.totalAccrual}" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">信访费：</td>
							                            <td>
							                                <label class="input">
							                                     <input class="input02"  name="contractInfoBean.petitionFee" value="${loanDetailBean.contractInfoBean.petitionFee}" maxlength="15" >
							                                </label>
							                            </td>
							                        </tr>
							
							                        <tr>
							                            <td class="tr">保证金：</td>
							                            <td>
							                                <label class="input">
							                                   <input class="input02"  name="contractInfoBean.assureMoney" value="${loanDetailBean.contractInfoBean.assureMoney}" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">已扣服务费：</td>
							                            <td>
							                                <label class="input">
							                                    <input class="input02"  name="contractInfoBean.deductServiceFee" value="${loanDetailBean.contractInfoBean.deductServiceFee}" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">已扣利息：</td>
							                            <td>
							                                <label class="input">
							                                    <input class="input02"  name="contractInfoBean.deductAccrual" value="${loanDetailBean.contractInfoBean.deductAccrual}" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">实际放款金额：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="actualLoanAmount" class="input02"  name="contractInfoBean.actualLoanAmount" value="${loanDetailBean.contractInfoBean.actualLoanAmount}" maxlength="15" >
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">剩余服务费：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="surplusServiceFee" class="input02"  name="contractInfoBean.surplusServiceFee" value="${loanDetailBean.contractInfoBean.surplusServiceFee}" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">剩余利息：</td>
							                            <td>
							                                <label class="input">
							                                   <input class="input02"  name="contractInfoBean.surplusAccrual" value="${loanDetailBean.contractInfoBean.surplusAccrual}" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">实际发款时间：</td>

							                            <td>
							                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
							                                    <input id="actualLoanDate" type="text" name="contractInfoBean.actualLoanDate" maxlength="10" class="selectdate" 
							                                    value="${loanDetailBean.contractInfoBean.actualLoanDate}" placeholder="请选择时间">
							                                </label>
							                            </td>
							                            <td class="tr">抵押率：</td>
							                            <td>
							                                <label class="input">
							                                    <input class="input02" type="text" name="contractInfoBean.mortgageRate" value="${loanDetailBean.contractInfoBean.mortgageRate}" maxlength="5">
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">借款原因：</td>
							                            <td colspan="7">
							                                <label class="input">
							                                     <input type="text" name="contractInfoBean.loanReason" value="${loanDetailBean.contractInfoBean.loanReason}" maxlength="100">
							                                </label>
							                            </td>
							                        </tr>
							                        </tbody>
							                    </table>
                                            </div>
                                        </div>
                                        <!-- end widget content -->
                                    </div>
                                </div>
                            </div>
                          
                            <div class="jarviswidget" id="wid-id-183" data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>附件信息</h2>
                                </header>
                               
                                <div>
                                    <div class="smart-form">
                                        
                                        <div class="widget-body no-padding clearfix">
                                           <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_1" <c:if test="${!empty loanDetailBean.icAnnex.id }">checked="checked"</c:if>  disabled="disabled"/> 身份证</div>
                                                <input type="hidden" name="annexList[0].id" value="${loanDetailBean.icAnnex.id }"/>
                                                <input type="hidden" name="annexList[0].annexType" value="1"/>
                                                <input type="hidden" name="annexList[0].annexPath" id="annexPath_1" value="${loanDetailBean.icAnnex.annexPath }"/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_1" /></div>
                                                <c:if test= "${loanDetailBean.icAnnex.annexPath !='' && loanDetailBean.icAnnex.annexPath !=null}">
                                                     <a onClick="fileDelete(1);" id ="fileDelete_1">删除</a>
                                               		 <a onClick="fileDownload(1);" id ="download_1">下载附件</a> 
                                                </c:if>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_2" <c:if test="${!empty loanDetailBean.saAnnex.id }">checked="checked"</c:if> disabled="disabled"/> 服务协议</div>
                                                <input type="hidden" name="annexList[1].id" value="${loanDetailBean.saAnnex.id }"/>
                                                <input type="hidden" name="annexList[1].annexType" value="2"/>
                                                <input type="hidden" name="annexList[1].annexPath" id="annexPath_2" value="${loanDetailBean.saAnnex.annexPath }"/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_2" /></div>
                                                <c:if test= "${loanDetailBean.saAnnex.annexPath !='' && loanDetailBean.saAnnex.annexPath !=null}">
                                                	<a onClick="fileDelete(2);" id ="fileDelete_2">删除</a>
                                               		<a onClick="fileDownload(2);" id ="download_2">下载附件</a> 
                                                </c:if>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_3" <c:if test="${!empty loanDetailBean.waAnnex.id }">checked="checked"</c:if> disabled="disabled"/> 委托代扣协议</div>
                                                <input type="hidden" name="annexList[2].id" value="${loanDetailBean.waAnnex.id }"/>
                                                <input type="hidden" name="annexList[2].annexType" value="3"/>
                                                <input type="hidden" name="annexList[2].annexPath" id="annexPath_3" value="${loanDetailBean.waAnnex.annexPath }"/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_3" /></div>
                                                <c:if test= "${loanDetailBean.waAnnex.annexPath !='' && loanDetailBean.waAnnex.annexPath !=null}">
                                                	 <a onClick="fileDelete(3);" id ="fileDelete_3">删除</a>
                                               		 <a onClick="fileDownload(3);" id ="download_3">下载附件</a> 
                                                </c:if>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_4" <c:if test="${!empty loanDetailBean.mcAnnex.id }">checked="checked"</c:if> disabled="disabled"/> 抵押合同</div>
                                                <input type="hidden" name="annexList[3].id" value="${loanDetailBean.mcAnnex.id }"/>
                                                <input type="hidden" name="annexList[3].annexType" value="4"/>
                                                <input type="hidden" name="annexList[3].annexPath" id="annexPath_4" value="${loanDetailBean.mcAnnex.annexPath }"/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_4" /></div>
                                                <c:if test= "${loanDetailBean.mcAnnex.annexPath !='' && loanDetailBean.mcAnnex.annexPath !=null}">
                                               		 <a onClick="fileDelete(4);" id ="fileDelete_4">删除</a>
                                               		 <a onClick="fileDownload(4);" id ="download_4">下载附件</a> 
                                                </c:if>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_5" <c:if test="${!empty loanDetailBean.gcAnnex.id }">checked="checked"</c:if> disabled="disabled"/> 保证合同</div>
                                                <input type="hidden" name="annexList[4].id" value="${loanDetailBean.gcAnnex.id }"/>
                                                <input type="hidden" name="annexList[4].annexType" value="5"/>
                                                <input type="hidden" name="annexList[4].annexPath" id="annexPath_5" value="${loanDetailBean.gcAnnex.annexPath }"/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_5" /></div>
                                                <c:if test= "${loanDetailBean.gcAnnex.annexPath !='' && loanDetailBean.gcAnnex.annexPath !=null}">
                                               		 <a onClick="fileDelete(5);" id ="fileDelete_5">删除</a>
                                               		 <a onClick="fileDownload(5);" id ="download_5">下载附件</a> 
                                                </c:if>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_6" <c:if test="${!empty loanDetailBean.oaAnnex.id }">checked="checked"</c:if> disabled="disabled"/> 其&nbsp;他</div>
                                                <input type="hidden" name="annexList[5].id" value="${loanDetailBean.oaAnnex.id }"/>
                                                <input type="hidden" name="annexList[5].annexType" value="6"/>
                                                <input type="hidden" name="annexList[5].annexPath" id="annexPath_6" value="${loanDetailBean.oaAnnex.annexPath }"/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_6" /></div>
                                                <c:if test= "${loanDetailBean.oaAnnex.annexPath !='' && loanDetailBean.oaAnnex.annexPath !=null}">
                                               		 <a onClick="fileDelete(6);" id ="fileDelete_6">删除</a>
                                               		 <a onClick="fileDownload(6);" id ="download_6">下载附件</a> 
                                                </c:if>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="jarviswidget" id="wid-id-184" data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>还款计划</h2>
                                    <div class="" style="float:left;">
					   				 	<button class="btn-success ml10  lh200 pr15 pl15" type="button" id="replaymentButton">生成还款计划</button>
									</div>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
            
                                        <div class="widget-body no-padding clearfix">
                                             <div class="widget-body-nobg-toolbar" style="overflow:hidden;height:47px;"></div>
												<table class="table table-bordered tc" id="borrow-rep-table1" >
                                                   <col width="40" />
                                                   <col width="160"/>
                                                   <col width="150"/>
                                                   <col width="150"/>
                                                   <col width="150"/>
                                                   <col width="150"/>
                                                   <col />
                                                    <thead>
                                                        <tr class="b">
                                                            <td>期数</td>
                                                            <td>还款日期</td>
                                                            <td>本金</td>
                                                            <td>利息</td>
                                                            <td>服务费</td>
                                                            <td>小计</td>
                                                            <td></td>
                                                        </tr>
                                                    </thead>
											        <tbody id= "replaymentPlanList">
											         <c:set var="lineNumber" value="0"/>
						 							 <c:forEach var="repaymentPlanBean" items="${loanDetailBean.repaymentPlanBeanList}">
						 				
                                                        <tr class="table_input">
                                                            <td>${repaymentPlanBean.repaymentTimes}</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text"  class="selectdate" name='repaymentPlanBeanList[${lineNumber}].repaymendate'  placeholder="" value="${repaymentPlanBean.repaymendate}" />
                                                                </label>
                                                            </td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text"  class="input02" name='repaymentPlanBeanList[${lineNumber}].principal'  placeholder="" value="${repaymentPlanBean.principal}" />
                                                                </label>
                                                            </td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text"  class="input02" name='repaymentPlanBeanList[${lineNumber}].accrual'  placeholder="" value="${repaymentPlanBean.accrual}" />
                                                                </label>
                                                            </td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text"  class="input02" name='repaymentPlanBeanList[${lineNumber}].serviceFee'  placeholder="" value="${repaymentPlanBean.serviceFee}" />
                                                                </label>
                                                            </td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text"  class="input02" name='repaymentPlanBeanList[${lineNumber}].subtotal'  placeholder="" value="${repaymentPlanBean.subtotal}" />
                                                                </label>
                                                            </td>
                                                            <td></td>
                                                        </tr>
						 							<c:set var="lineNumber" value="${lineNumber+1}"/>
						 							 </c:forEach>
						
											        </tbody>
                                                </table>
											</div>

                                        </div>

                                    </div>
                                </div>

                            <div class="jarviswidget" id="wid-id-185" data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>银行信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding clearfix">
                                            <div class="fl ml50 mr100 mt20 mb10" >
                                                <h2 class="color01 mb10 b ml10">放款银行信息：</h2> 
							                    <table class="table" style="width:400px">
							                        <col width="90" />
							                        <col />
							                        <tr>
							                            <td class="tr">开户人姓名:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="loanBankInfoBean.acctName"  value="${loanDetailBean.loanBankInfoBean.acctName }" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">银行账号:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="loanBankInfoBean.bankNo"  value="${loanDetailBean.loanBankInfoBean.bankNo }" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td align="right" class="vt">开&nbsp;户&nbsp;行:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="loanBankInfoBean.bankName" value="${loanDetailBean.loanBankInfoBean.bankName }"/>
							                                </label>
							                            </td>
							                        </tr>
							                    </table>
                                            </div>
                                            <div class="fl ml50 mr100 mt20 mb10" >
                                                <h2 class="color01 mb10 b ml10">回款银行信息:</h2> 
							                    <table class="table" style="width:400px">
							                        <tr>
							                            <td align="right">付款方式:</td>
							                            <td class="lh30">
							                            <input type="hidden" id="hiddenpaymentType" value="${loanDetailBean.backBankInfoBean.paymentType}" />
							                                <ul class="clearfix">
							                                    <li class="fl mr20">
							                                        <input type="radio" value="1" id="paymentType1" name="backBankInfoBean.paymentType" />代扣
							                                    </li>
							                                    <li class="fl mr20">
							                                        <input type="radio" value="2" id="paymentType2" name="backBankInfoBean.paymentType" />汇款
							                                    </li>
							                                    <li class="fl">
							                                        <input type="radio" value="3" id="paymentType3" name="backBankInfoBean.paymentType" />转账
							                                    </li>
							                                </ul>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">开户人姓名:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="backBankInfoBean.acctName" value="${loanDetailBean.backBankInfoBean.acctName}" id="bkName" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">银行账号:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="backBankInfoBean.bankNo"  id="bkAccount" value="${loanDetailBean.backBankInfoBean.bankNo }" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td align="right" class="vt">开&nbsp;户&nbsp;行:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="backBankInfoBean.bankName"  id="bkAddr" value="${loanDetailBean.backBankInfoBean.bankName }" />
							                                </label>
							                            </td>
							                        </tr>
							                    </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <div class="jarviswidget" id="wid-id-186"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>操作人员信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10">
							                    <table class="table">
							                    	<col width="120" />
												    <col />
												    <col width="120" />
												    <col />
												    <col width="120" />
												    <col />
												    <col width="120" />
												    <col />
							                        <tbody>
							                        <tr></tr>
							                        <tr>
							                            <td class="tr">大&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="organizationInfoBean.largeArea" value="${loanDetailBean.organizationInfoBean.largeArea }" />
							                                </label>
							                            </td>
							                            <td class="tr">区&nbsp;&nbsp;&nbsp;&nbsp;域：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="organizationInfoBean.area" value="${loanDetailBean.organizationInfoBean.area }" />
							                                </label>
							                            </td>
							                            <td class="tr">城&nbsp;&nbsp;&nbsp;&nbsp;市：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="50" name="organizationInfoBean.city" value="${loanDetailBean.organizationInfoBean.city }" />
							                                </label>
							                            </td>
							                            <td class="tr">门&nbsp;&nbsp;&nbsp;&nbsp;店：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="50" name="organizationInfoBean.store" value="${loanDetailBean.organizationInfoBean.store }" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">大区总监：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.largeAreaDirector" value="${loanDetailBean.organizationInfoBean.largeAreaDirector }" />
							                                </label>
							                            </td>
							                            <td class="tr">区域经理：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.areaManager" value="${loanDetailBean.organizationInfoBean.areaManager }" />
							                                </label>
							                            </td>
							                            <td class="tr">门店经理：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.storeManager" value="${loanDetailBean.organizationInfoBean.storeManager }" />
							                                </label>
							                            </td>
							                            <td class="tr">主&nbsp;&nbsp;&nbsp;&nbsp;管：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.director" value="${loanDetailBean.organizationInfoBean.director }" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">个贷人员：</td>
							                            <td>
							           						<label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.loanPerson" value="${loanDetailBean.organizationInfoBean.loanPerson }" />
							                                </label>
							                            </td>
							                            <td class="tr">面&nbsp;&nbsp;&nbsp;&nbsp;审：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.checkPerson" value="${loanDetailBean.organizationInfoBean.checkPerson }" />
							                                </label>
							                            </td>
							                            <td class="tr">外&nbsp;&nbsp;&nbsp;&nbsp;访：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.visitorPerson" value="${loanDetailBean.organizationInfoBean.visitorPerson }" />
							                                </label>
							                            </td>
							                            <td class="tr">电&nbsp;&nbsp;&nbsp;&nbsp;核：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.telCheckPerson" value="${loanDetailBean.organizationInfoBean.telCheckPerson }" />
							                                </label>
							                            </td>
							                        </tr>
							
							                        <tr>
							                            <td class="tr">区域电核：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.areaPointCheckPerson" value="${loanDetailBean.organizationInfoBean.areaPointCheckPerson }" />
							                                </label>
							                            </td>
							                            <td class="tr">终审：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.finalJudgment" value="${loanDetailBean.organizationInfoBean.finalJudgment }" />
							                                </label>
							                            </td>
							                            <td colspan=4></td>
							                        </tr>
							                        </tbody>
							                    </table>
                                            </div>
                                        </div>
                                        <!-- end widget content -->
                                    </div>
                                </div>
                            </div>
                            <div class="jarviswidget tc" id="wid-id-187">
                            	<button class="btn btn-default table-nobg-btn" type="button"  id= "btn-success" ><i class="fa  fa-clipboard mr5"></i>保存</button>
                            </div>
                        </article>
                    </form>
                </div>
            </section>
        </div>
    </div>

<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
	<script src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js" type="text/javascript" charset="utf-8"></script>
    <link href="<%=request.getContextPath()%>/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
 <script>
 
		//身份证正则表达式(15位) 
		isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/; 
		//身份证正则表达式(18位) 
		isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}(x|X))$/; 
		
        $(document).ready(function () {
            pageSetUp();

            //初期化添加共借人删除事件
            addEvents();
            $("#add_mens").click(function(){


                $("#mens").append('<tr class="input_data"><td class="tr">共借人：</td><td><label class="input"> <input class="input01"  name="commLoanPersonName" maxlength="40" ></label></td><td class="tr">共借人电话：</td><td><label class="input"><input class="input01"  name="commLoanPersonPhone" maxlength="11" ></label></td><td class="tr">共借人身份证：</td><td><label class="input" style="width:220px"><input class="input01" name="commLoanPersonCertNo" maxlength="20" ></label></td> <td><a class="delete_mens">删除</a></td></tr>');
                addEvents();
                return false; 
            
           });
            $('.selectdate').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                autoclose: 1,
                format: 'yyyy-mm-dd',
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });
            
            
            //保存按钮按下
            $("#btn-success").click(function(){
            	changAttrName();
            	$("#formform").submit();
            });
            
            //返回按钮
            $("#returnParent").click(function(){
                window.open("${contextPath}/loan/loanList","_self");
            });
            
            //自动生成还款计划按钮
            $("#replaymentButton").click(function(){
            	var num = $("#limitTimes").val();

            	if (num == "" ) {
            		alert("请输入期限次数！");
            		return;
            	}

        		var backMoneyType = $("#backMoneyType").val();
        		if (backMoneyType == 0) {
        			alert("请选择还款方式！");
        			$("#backMoneyType").focus();
        			return false;
        		}
            	
				
        		
        		//先息后本
        		if (backMoneyType == 3) {
        			var actualLoanAmount = $("#actualLoanAmount").val();
        			var loanRate =$("#loanRate").val();
        			var actualLoanDate = $("#actualLoanDate").val();
        			if (actualLoanAmount == "") {
        				alert("实际放款金额不能为空！")
        				$("#actualLoanAmount").focus();
        				return false;
        			}
        			
        			if (loanRate == "") {
        				alert("年化率不能为空！")
        				$("#loanRate").focus();
        				return false;
        			}
        			
        			if (actualLoanDate == "") {
        				alert("实际放款日不能为空！");
        				$("#actualLoanDate").focus();
        				return false;
        			}
        			
        			var benjin=Number(actualLoanAmount),//出借金额
        			lilvy=Number(loanRate/100), //年化收益
        			qixian=Number(num);//借款期限
        			
        			startYear=Number(actualLoanDate.replace(/-/g,"").substr(0,4));  //开始还款年
        			startMonth=Number(actualLoanDate.replace(/-/g,"").substr(4,2)); //开始还款月
        			startDay=Number(actualLoanDate.replace(/-/g,"").substr(6,2));   //开始还款日
        			
        			var surplusServiceFee = Number(0);  //剩余服务费
        			if ($("#surplusServiceFee").val() == "") {
        				
        			} else {
        				surplusServiceFee = Number($("#surplusServiceFee").val());
        			} 
        			
        			var fwfee = (surplusServiceFee/qixian).toFixed(2); //每期服务费
        			
        			var lastTotalFee = (surplusServiceFee/qixian +benjin + (benjin * lilvy /12)).toFixed(2); //最后一行小计
        			var totalFee = (surplusServiceFee/qixian + (benjin * lilvy /12)).toFixed(2); //每行小计
        			
        			//应收总利息
        			var totalAccrual = Number(0);
        			
        			
                    $("#borrow-rep-table1").dataTable().fnClearTable();
            		var srtHtml = "";
            		
            		

                	for(var i=0;i<num;i++){
                		//应收总利息
                		totalAccrual = totalAccrual + (benjin * lilvy /12);
                		
        				if ( i<9) {
        					srtHtml =' <td>00'+[i+1] +'</td>' 
        				} else if (i<99) {
        					srtHtml = ' <td>0'+[i+1] +'</td>' 
        				} else {
        					srtHtml = ' <td>'+[i+1] +'</td>' 
        				}
        				var date1 = new Date();
        			
        				date1.setFullYear(startYear, startMonth, 15);
        				date1.setMonth(date1.getMonth() + i);
        				var date = date1.getFullYear() + '-' + (date1.getMonth() + 1) + '-' + 15;
        				
        				if(i == num-1){
                       		$("#replaymentPlanList").append(' <tr class="table_input">'

                    				+ srtHtml

                    				+ '<td><label class="input"> <input   type="text" name="repaymentPlanBeanList['+ i +'].repaymendate"  class="selectdate" id="" placeholder="" value="'+date+'" /></label></td>'
                    				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].principal" id="" placeholder="" value="'+benjin.toFixed(2)+'" /></label></td>'
                    				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].accrual" id="" placeholder="" value="'+(benjin * lilvy /12).toFixed(2) +'" /></label></td>'
                    				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].serviceFee" id="" placeholder="" value="'+fwfee+'" /></label></td>'
                    				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].subtotal" id="" placeholder="" value="'+lastTotalFee+'" /></label></td>'
                    				+ '<td></td>'
                    				+ '</tr>');
                       		break;
        				}
                		$("#replaymentPlanList").append(' <tr class="table_input">'

                				+ srtHtml

                				+ '<td><label class="input"> <input   type="text" name="repaymentPlanBeanList['+ i +'].repaymendate"  class="selectdate" id="" placeholder="" value="'+date+'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].principal" id="" placeholder="" value="0.00" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].accrual" id="" placeholder="" value="'+(benjin * lilvy /12).toFixed(2) +'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].serviceFee" id="" placeholder="" value="'+fwfee+'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].subtotal" id="" placeholder="" value="'+totalFee+'" /></label></td>'
                				+ '<td></td>'
                				+ '</tr>');
                	}
                	
                	//设置应收总利息的值
                	$("#totalAccrual").val(totalAccrual.toFixed(2));
        		
        		//等额等息
        		} else if (backMoneyType == '2'){
        			
        			var actualLoanAmount = $("#actualLoanAmount").val();
        			var loanRate =$("#loanRate").val();
        			var actualLoanDate = $("#actualLoanDate").val();
        			if (actualLoanAmount == "") {
        				alert("实际放款金额不能为空！")
        				$("#actualLoanAmount").focus();
        				return false;
        			}
        			
        			if (loanRate == "") {
        				alert("年化率不能为空！")
        				$("#loanRate").focus();
        				return false;
        			}
        			
        			if (actualLoanDate == "") {
        				alert("实际放款日不能为空！");
        				$("#actualLoanDate").focus();
        				return false;
        			}
        			
        			var benjin=Number(actualLoanAmount),//出借金额
        			lilvy=Number(loanRate/100), //年化收益
        			qixian=Number(num);//借款期限
        			
        			startYear=Number(actualLoanDate.replace(/-/g,"").substr(0,4));  //开始还款年
        			startMonth=Number(actualLoanDate.replace(/-/g,"").substr(4,2)); //开始还款月
        			startDay=Number(actualLoanDate.replace(/-/g,"").substr(6,2));   //开始还款日
        			
        			var surplusServiceFee = Number(0);  //剩余服务费
        			if ($("#surplusServiceFee").val() == "") {
        				
        			} else {
        				surplusServiceFee = Number($("#surplusServiceFee").val());
        			} 
        			
        			var fwfee = (surplusServiceFee/qixian).toFixed(2); //每期服务费
        			
        			//应收总利息
        			var totalAccrual = Number(0);
        			
                    $("#borrow-rep-table1").dataTable().fnClearTable();
            		var srtHtml = "";

            		var surplusBaseAmount = benjin;
            		
                	for(var i=0;i<num;i++){
        				if ( i<9) {
        					srtHtml =' <td>00'+[i+1] +'</td>' 
        				} else if (i<99) {
        					srtHtml = ' <td>0'+[i+1] +'</td>' 
        				} else {
        					srtHtml = ' <td>'+[i+1] +'</td>' 
        				}
        				var date1 = new Date();
        			
        				date1.setFullYear(startYear, startMonth, 15);
        				date1.setMonth(date1.getMonth() + i);
        				var date = date1.getFullYear() + '-' + (date1.getMonth() + 1) + '-' + 15;
        				
        				
        				//月还款额 =  [借款金额 * 月利率 * (1+月利率)^总期数] /[(1+月利率)^总期数 -1]
        				var monthSumAmount = (benjin * lilvy /12 * Math.pow(1 + lilvy /12, qixian)) / (Math.pow(1 + lilvy /12, qixian) - 1);
        				//月还本金 =  [借款金额 * 月利率 * (1+月利率)^(i)] /[(1+月利率)^总期数 -1]
        				var monthBaseAmount = (benjin * lilvy /12 * Math.pow(1 + lilvy /12, i)) / (Math.pow(1 + lilvy /12, qixian) - 1);
        				//月还利息 =  月还款额 – 月还本金
        				var monthIrrAmount = monthSumAmount - monthBaseAmount;
        				//剩余本金
        				surplusBaseAmount = surplusBaseAmount - monthBaseAmount;
        				
        				//应收总利息
        				totalAccrual = totalAccrual + monthIrrAmount ;
        				
               			var totalFee = (monthSumAmount + surplusServiceFee/qixian).toFixed(2); //每行小计
        				

        				
        				
                		$("#replaymentPlanList").append(' <tr class="table_input">'

                				+ srtHtml

                				+ '<td><label class="input"> <input   type="text" name="repaymentPlanBeanList['+ i +'].repaymendate"  class="selectdate" id="" placeholder="" value="'+date+'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].principal" id="" placeholder="" value="'+monthBaseAmount.toFixed(2)+'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].accrual" id="" placeholder="" value="'+ monthIrrAmount.toFixed(2) +'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].serviceFee" id="" placeholder="" value="'+fwfee+'" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].subtotal" id="" placeholder="" value="'+totalFee+'" /></label></td>'
                				+ '<td></td>'
                				+ '</tr>');
                	}
                	
                	//设置应收总利息的值
                	$("#totalAccrual").val(totalAccrual.toFixed(2));
        		} else {
        			
                	//$("#replaymentPlanList").empty();
                    $("#borrow-rep-table1").dataTable().fnClearTable();
            		var srtHtml = "";
            		
            		
                	for(var i=0;i<num;i++){

        				if ( i<9) {
        					srtHtml =' <td>00'+[i+1] +'</td>' 
        				} else if (i<99) {
        					srtHtml = ' <td>0'+[i+1] +'</td>' 
        				} else {
        					srtHtml = ' <td>'+[i+1] +'</td>' 
        				}
        				
                		$("#replaymentPlanList").append(' <tr class="table_input">'

                				+ srtHtml

                				+ '<td><label class="input"> <input   type="text" name="repaymentPlanBeanList['+ i +'].repaymendate"  class="selectdate" id="" placeholder="" value="" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].principal" id="" placeholder="" value="" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].accrual" id="" placeholder="" value="" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].serviceFee" id="" placeholder="" value="" /></label></td>'
                				+ '<td><label class="input"> <input  class="input02" type="text" name="repaymentPlanBeanList['+ i +'].subtotal" id="" placeholder="" value="" /></label></td>'
                				+ '<td></td>'
                				+ '</tr>');
                	}
        		}
            	
            	
                $("#borrow-rep-table1").dataTable().fnDestroy();
                DT_page("borrow-rep-table1",false);
                
                //日期型判断
                dateCheck() ;
                //数字型判断
                numCheck();
            });
            
            DT_page("borrow-rep-table1",false);

            
            //下拉框默认选择
            selectedInit();
            //radiobox默认选中值
            radioInit()
            
            
            //日期型判断
            dateCheck() ;
            //数字型判断
            numCheck();
            
            //附件方法加载
            //身份证
            fileUpload('1');
            //服务协议
            fileUpload('2');
            //委托代扣协议
            fileUpload('3');
            //抵押合同
            fileUpload('4');
            //保证合同
            fileUpload('5');
            //其他
            fileUpload('6');
            
            //根据证件号码，出生日期自动写入
        	$("#certNo").focus(function() {
        		//选中
        		$(this).select();
        	})
    		.blur(function() {
    			var certNo = $(this).val();
    			
    			var certType = $("#certType").val();
    			//如果是身份证，出生日期自动写入
    			if (certType == '1') {
    				if(certNo != "" &&  !isIDCard1.test(certNo) && !isIDCard2.test(certNo) ) {
    					alert("请输入有效的身份证号码!");
    					$(this).focus();
    					return;
    	        	}
    	        	
    	        	if(certNo != "" && certNo.length == 18 ) {
    	        		$("#birthdate").val(certNo.substr(6,4) + "-" + certNo.substr(10,2) + "-"  + certNo.substr(12,2) );
    	        	}
    			}
    		});
            
            //还款方式变更事件
        	$("#backMoneyType").change(function () {
        		$("#borrow-rep-table1").dataTable().fnClearTable();
                $("#borrow-rep-table1").dataTable().fnDestroy();
                DT_page("borrow-rep-table1",false);
        	});
        });
        
        function changAttrName(){
        	var numTmp = new Array();
        	for(var i = 0;i<$(".input_data").size();i++){
        		numTmp.push(i);
        	}
        	$(".input_data").each(function(i){
        		$(this).find(".input01").each(function(j){
  
        			var name = $(this).attr("name");
        			$(this).removeAttr("name").attr("name","loanPersonListBeanList["+numTmp[i]+"]."+name);
        		});
        	});
        }
        
		
        //共借人删除
        function addEvents(){
            $(".delete_mens").each(function(){
				$(this).off();
          	 	$(this).click(function(){
          	 		$(this).parents("tr").remove();
          	 	});

            });
        }
        
        function selectedInit() {
        	
        	getSelectState("certType", $("#hiddencertType").val());
        	getSelectState("sex", $("#hiddensex").val());
        	
            getSelectState("education", $("#hiddeneducation").val());
            getSelectState("maritalStatus", $("#hiddenmaritalStatus").val());
            getSelectState("loanType", $("#hiddenloanType").val());
            getSelectState("loanMode", $("#hiddenloanMode").val());
            getSelectState("backMoneyType", $("#hiddenbackMoneyType").val());
            getSelectState("isWithholding", $("#hiddenisWithholding").val());
        }
        
        function radioInit(){
            var hiddenpaymentType = $("#hiddenpaymentType").val();

            if (hiddenpaymentType == '1') {
         	   $("input[type=radio][value=1]").attr("checked",'checked');
            }
            if (hiddenpaymentType == '2') {
         	   $("input[type=radio][value=2]").attr("checked",'checked');
            }
            if (hiddenpaymentType == '3') {
         	   $("input[type=radio][value=3]").attr("checked",'checked');
            }
        }
        
        function getSelectState(selectId, optionValue){ 
            var sel = document.getElementById(selectId); 
            for(var i=0;i<sel.length;i++) { 
             if(sel.options[i].value == optionValue) { 
                sel.selectedIndex = i; 
                break; 
             } 
            } 
        } 


        function numCheck() {
        	var $input02 = $(".input02");
        	var rg = /^(-?\d+)(\.\d{0,2})?$/;
        	$input02.each(function() {
        		$(this).off();
	        	$(this).focus(function() {
	        		//
	        		this.select();
	        	})
        		.blur(function() {
		        	if($(this).val() != "") {
    					if(!rg.test($(this).val())){
    						var msg = "请输入有效的数字 ，可以有两位小数!  ";
    						alert(msg);
    						this.focus();
    						return false;
    					} else {
    						$(this).val(Number($(this).val()).toFixed(2));
    					}
		        	}
        		});
        	});
        	
        	var rg2 = /^(-?\d+)?$/;
        	
        	$("#limitTimes").blur(function() {
            	var num = $("#limitTimes").val();
        		if(num != "") {
					if(!rg2.test(num)) {
						var msg = "请输入有效的数字 !";
						alert(msg);
						this.focus();
						return false;
					};
	        	}
        	});
        }
        
        
        function dateCheck() {
        	var $selectdate = $(".selectdate");
        	$selectdate.each(function() {
        		//$(this).off();
	        	$(this).focus(function() {
	        		//
	        		this.select();
	        	})
	        	.blur(function() {
		        	if($(this).val() != "") {
			        	var val = $(this).val();
			        	if (val.indexOf("\-") > 0 ) {
			        	} else {
			        		if (val.length == 8) {
			        			val = val.substr(0,4) + "-" + val.substr(4,2) + "-" + val.substr(6,2);
			        			$(this).val(val);
			        		}
			        	}
			        	var msg= isDate($(this).val());
			        	if (msg != "") {
				        	alert(msg);
				        	this.focus();
			        	}

	        		}
	        	});
        	});
        }
        
        
        function isDate(strDate){
        	var strSeparator = "-"; //日期分隔符 
        	var strDateArray; 
        	var intYear; 
        	var intMonth; 
        	var intDay; 
        	var boolLeapYear; 
        	var ErrorMsg = ""; //出错信息 
        	strDateArray = strDate.split(strSeparator); 
        	//没有判断长度,其实2008-8-8也是合理的//strDate.length != 10 || 
        	if(strDateArray.length != 3) { 
	        	ErrorMsg += "日期格式必须为: 年-月-日"; 
	        	return ErrorMsg; 
        	} 
        	intYear = parseInt(strDateArray[0],10); 
        	intMonth = parseInt(strDateArray[1],10); 
        	intDay = parseInt(strDateArray[2],10); 
        	if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) { 
        		ErrorMsg += "请输入有效的日期！"; 
	        	return ErrorMsg; 
        	} 
        	if(intMonth>12 || intMonth<1) { 
        		ErrorMsg += "请输入有效的日期！"; 
	        	return ErrorMsg; 
        	} 
        	if((intMonth==1||intMonth==3||intMonth==5||intMonth==7 
        		||intMonth==8||intMonth==10||intMonth==12) &&(intDay>31||intDay<1)) { 
        		ErrorMsg += "请输入有效的日期！"; 
	        	return ErrorMsg; 
        	} 
        	if((intMonth==4||intMonth==6||intMonth==9||intMonth==11) 
        		&&(intDay>30||intDay<1)) { 
        		ErrorMsg += "请输入有效的日期！";  
	        	return ErrorMsg; 
        	} 
        	if(intMonth==2){ 
	        	if(intDay < 1) { 
	        		ErrorMsg += "请输入有效的日期！";  
		        	return ErrorMsg; 
        		} 
	        	boolLeapYear = false; 
	        	if((intYear%100) == 0){ 
		        	if((intYear%400) == 0) 
		        	boolLeapYear = true; 
        		} else { 
		        	if((intYear % 4) == 0) 
		        		boolLeapYear = true; 
	        		} 
	        		if(boolLeapYear){ 
		        		if(intDay > 29) { 
		        			ErrorMsg += "请输入有效的日期！"; 
			        		return ErrorMsg; 
	        			} 
        			} else { 
			        	if(intDay > 28) { 
				        	ErrorMsg += "请输入有效的日期！"; 
				        	return ErrorMsg; 
		        		} 
        			} 
        		} 
        	return ErrorMsg; 
        } 
        
    	function fileUpload(id){
    		$("#file_upload_"+id).uploadify({
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
                'uploader':'<%=request.getContextPath()%>/loan/addLoanAnnex',
                'buttonText':'上传附件',
                //浏览按钮的背景图片路径
                //'buttonImage':'upbutton.gif',
                //浏览按钮的宽度
                'width':'80',
                //浏览按钮的高度
                'height':'28',
                //expressInstall.swf文件的路径。
                //'expressInstall':'expressInstall.swf',
                //在浏览窗口底部的文件类型下拉菜单中显示的文本
                'fileTypeDesc':'支持的格式：',
                //允许上传的文件后缀
                //'fileTypeExts':'*.jpg;*.jpge;*.gif;*.png',
                //上传文件的大小限制
                //'fileSizeLimit':'3MB',
                //上传数量
                //'queueSizeLimit' : 25,
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
                    
                    var flg = $("#download_"+ id).val();
                    if (flg == undefined) {
                        var strHtml='<a  onClick="fileDownload('+id+');" id ="download_'+id+'">下载附件</a> ' +
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
    	}
    	
    	function fileDownload(id){
    		var annexPath = $("#annexPath_"+id).val();
    		$("#download_"+id).attr("href","${contextPath}/loan/downLoad?urlPath="+ annexPath);
    		//window.open("${contextPath}"+annexPath,"_blank");
    		//document.getElementById("download").href="${contextPath}/loan/downLoad?urlPath="+ annexPath;
    	}

    	function fileDelete(id){
    		$('#ck_'+id).attr("checked",false);
    		$('#annexPath_'+id).val("");
    		$('#download_'+id).remove();
    		$('#fileDelete_'+id).remove();
    	}

    </script>



</body>

</html>


