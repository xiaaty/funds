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
    <title>内审系统--借款管理--新增借款</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
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
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>

    <div id="main" role="main">
        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>借款管理</li>
                <li>新增借款</li>
            </ol>
            <!-- end breadcrumb -->
        </div>
        <div class="clearfix ml20" id="addErrorMsg" style="color:red;"></div>
        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                   <form id ="formform" action="${contextPath}/loan/loanAddConfirom" method="post" >
                        <article class="col-sm-12 col-md-12 ui-sortable">
							
                            <div class="jarviswidget" id="wid-id-131"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
												                <input type="text" class="input" maxlength="20" name= "customerInfoBean.customerName" value="${loanDetailBean.customerInfoBean.customerName}">
												            </label>
												        </td>
												        <td class="tr">移动电话：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input" maxlength="12" name= "customerInfoBean.mobilePhone" value="${loanDetailBean.customerInfoBean.mobilePhone}">
												            </label>
												        </td>
												        <td class="tr">国&nbsp;&nbsp;籍：</td>
												        <td>
											            <label class="input">
											                 <input type="text" class="input" maxlength="10" name= "customerInfoBean.nationality" value="${loanDetailBean.customerInfoBean.nationality}">
											            </label>
												            <!-- <label class="select">
												                <select name ="customerInfoBean.nationality">
												                    <option value="1">中国</option>
												                    <option value="2">美国</option>
												                </select>
												            </label>-->
												        </td>
												        <td class="tr">证件类型：</td>
												        <td>
												         <input type="hidden" id="hiddencertType" value="${loanDetailBean.customerInfoBean.certType}" />
												            <label class="select">
												                <select id = "certType" name ="customerInfoBean.certType">
												                    <option value="1">身份证</option>
												                    <option value="2">护照</option>
												                </select>
												            </label>
												        </td>
												    </tr>
												    <tr>
												        <td class="tr">证件号码：</td>
												        <td>
												            <label class="input">
												                  <input type="text" class="input" maxlength="18" id ="certNo" name= "customerInfoBean.certNo" value="${loanDetailBean.customerInfoBean.certNo}">
												            </label>
												        </td>
												        <td class="tr">发证机关所在地：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input" maxlength="100" name= "customerInfoBean.certAddress" value="${loanDetailBean.customerInfoBean.certAddress}">
												            </label>
												        </td>
												        <td class="tr">签发日期：</td>
												        <td>
												            <label class="input" > <i class="icon-append fa fa-calendar"></i>
												                <input type="text" maxlength="10" name="customerInfoBean.certIssueDate" value="${loanDetailBean.customerInfoBean.certIssueDate}" class="selectdate"  placeholder="请选择时间">
												            </label>
												        </td>
												        <td class="tr">失效日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text" maxlength="10" name="customerInfoBean.certFailDate" value="${loanDetailBean.customerInfoBean.certFailDate}" class="selectdate" placeholder="请选择时间">
												            </label>
												        </td>
												    </tr>
												    <tr>
												        <td class="tr">性&nbsp;&nbsp;别：</td>
												        <td>
												         <input type="hidden" id="hiddensex" value="${loanDetailBean.customerInfoBean.sex}" />
												            <label class="select">
												                <select name="customerInfoBean.sex">
												                    <option value="1">男</option>
												                    <option value="2">女</option>
												                </select>
												            </label>
												        </td>
												        <td class="tr">出生日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text" id="birthdate" name="customerInfoBean.birthdate" value="${loanDetailBean.customerInfoBean.birthdate}" class="selectdate" placeholder="请选择时间">
												            </label>
												        </td>
												        <td class="tr">学&nbsp;&nbsp;历：</td>
												        <td>
												            <label class="select">
												            <input type="hidden" id="hiddeneducation" value="${loanDetailBean.customerInfoBean.education}" />
												                <select  name="customerInfoBean.education">
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
												                <select name = "customerInfoBean.maritalStatus">
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
												               <input type="text" class="input" maxlength="50" name= "customerInfoBean.email" value="${loanDetailBean.customerInfoBean.email}">
												            </label>
												        </td>
												        <td class="tr">邮&nbsp;&nbsp;编：</td>
												        <td>
												            <label class="input">
												                <input type="text" class="input" maxlength="10" name= "customerInfoBean.zipCode" value="${loanDetailBean.customerInfoBean.zipCode}">
												            </label>
												        </td>
												        <td colspan="4"></td>
												    <tr>
												        <td class="tr">通讯地址：</td>
												        <td colspan="7" class="lh32">

													        	<span class="fl tr" style="width: 50px">国家</span>
													            <label class="input fl" style="width: 160px">
														                <input type="text" class="input" maxlength="50" name= "customerInfoBean.addrCountry" value="${loanDetailBean.customerInfoBean.addrCountry}">
														        </label>

													        	<span class="fl tr" style="width: 80px">省(直辖市)</span>
													            <label class="input fl" style="width: 160px">
														                <input type="text" class="input" maxlength="50" name= "customerInfoBean.addrProvince" value="${loanDetailBean.customerInfoBean.addrProvince}">
														        </label>
													        	<span class="fl tr" style="width: 50px">市(州)</span>
													            <label class="input fl" style="width: 160px">
														                <input type="text" class="input" maxlength="50" name= "customerInfoBean.addrCity" value="${loanDetailBean.customerInfoBean.addrCity}">
														        </label>
	
												        		<span class="fl tr" style="width: 50px">区(县)</span>
													            <label class="input fl" style="width: 160px">
														                <input type="text" class="input" maxlength="50" name= "customerInfoBean.addrTown" value="${loanDetailBean.customerInfoBean.addrTown}">
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
												                <input class="input" style="width: 282px;" name="customerInfoBean.addrTown" id="addrTown" maxlength="50" >
												            </label>
												        </td>
												    </tr>
												     -->
												    </tbody>
												</table>
												<div class="loan_line">共借人</div>
												<table class="table bdr_2">
												    <col width="120" />
												    <col width="230" />
												    <col width="120" />
												    <col width="230" />
												    <col width="120" />
												    <col width="230" />
												    <col />
												    <tbody id="mens">
													    <tr class="input_data">
													        <td class="tr">共借人：</td>
													        <td>
													            <label class="input">
													                <input class="input" name="commLoanPersonName" maxlength="40" >
													            </label>
													        </td>
													        <td class="tr">共借人电话：</td>
													        <td>
													            <label class="input">
													               <input class="input" name="commLoanPersonPhone" maxlength="11" >
													            </label>
													        </td>
													        <td class="tr">共借人身份证：</td>
													        <td>
													            <label class="input" style="width:220px">
													               <input class="input" name="commLoanPersonCertNo" maxlength="20" >
													            </label>
													        </td>
													        <td><a class="delete_mens">删除</a></td>
													    </tr>
													    <tr class="input_data">
													        <td class="tr">共借人：</td>
													        <td>
													            <label class="input">
													               <input class="input" name="commLoanPersonName" maxlength="40" >
													            </label>
													        </td>
													        <td class="tr">共借人电话：</td>
													        <td>
													            <label class="input">
													               <input class="input"  name="commLoanPersonPhone" maxlength="11" >
													            </label>
													        </td>
													        <td class="tr">共借人身份证：</td>
													        <td>
													            <label class="input" style="width:220px">
													                <input class="input"  name="commLoanPersonCertNo" maxlength="20" >
													            </label>
													        </td>
													         <td><a class="delete_mens">删除</a></td>
													    </tr>
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
                            <div class="jarviswidget" id="wid-id-132"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
							                                     <input class="input" id="contractNo" name="contractInfoBean.contractNo" value ="${loanDetailBean.contractInfoBean.contractNo }"  maxlength="30" >
							                                </label>
							                            </td>
							                            <td class="tr">借款类型：</td>
							                            <td>
							                                <label class="select">
							                                    <select name="contractInfoBean.loanType">
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
							                                    <select name="contractInfoBean.loanMode">
							                                        <option selected value="0">请选择</option>
							                                        <option value="1">新客户</option>
							                                        <option value="2">循环贷</option>
							                                    </select>
							                                </label>
							                            </td>
							                            <td class="tr">合同金额：</td>
							                            <td>
							                                <label class="input">
							                                     <input class="input"  name="contractInfoBean.contractAmount" maxlength="15" >
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">借款年利率：</td>
							                            <td>
							                                <label class="input">
							                                     <input id="loanRate" class="input02"  name="contractInfoBean.loanRate" maxlength="5" >
							                                </label>
							                            </td>
							                            <td class="tr">期&nbsp;&nbsp;&nbsp;&nbsp;限：</td>
							                            <td>
							                                <label class="input">
							                                   <input class="input" id="limitTimes"  name="contractInfoBean.limitTimes" maxlength="3" >
							                                </label>
							                            </td>
							                            <td class="tr">还款方式：</td>
							                            <td>
							                                <label class="select">
							                                    <select id="backMoneyType" name="contractInfoBean.backMoneyType">
							                                        <option selected value="0">请选择</option>
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
							                                    <select name="contractInfoBean.isWithholding">
							                                        <option selected value="0">请选择</option>
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
							                                    <input type="text" name="contractInfoBean.contractSignDate" maxlength="10" class="selectdate" placeholder="请选择时间">
							                                </label>
							                            </td>
							                            <td class="tr">应收总服务费：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="totalServiceFee" class="input02"  name="contractInfoBean.totalServiceFee" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">应收总利息：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="totalAccrual" class="input02"  name="contractInfoBean.totalAccrual" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">信访费：</td>
							                            <td>
							                                <label class="input">
							                                     <input class="input02"  name="contractInfoBean.petitionFee" maxlength="15" >
							                                </label>
							                            </td>
							                        </tr>
							
							                        <tr>
							                            <td class="tr">保证金：</td>
							                            <td>
							                                <label class="input">
							                                   <input class="input02"  name="contractInfoBean.assureMoney" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">已扣服务费：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="deductServiceFee" class="input02"  name="contractInfoBean.deductServiceFee" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">已扣利息：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="deductAccrual"  class="input02"  name="contractInfoBean.deductAccrual" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">实际放款金额：</td>
							                            <td>
							                                <label class="input">
							                                    <input id="actualLoanAmount" class="input02"  name="contractInfoBean.actualLoanAmount" maxlength="15" >
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">剩余服务费：</td>
							                            <td>
							                                <label class="input">
							                                    <input id ="surplusServiceFee" class="input02"  name="contractInfoBean.surplusServiceFee" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">剩余利息：</td>
							                            <td>
							                                <label class="input">
							                                   <input class="input02"  name="contractInfoBean.surplusAccrual" maxlength="15" >
							                                </label>
							                            </td>
							                            <td class="tr">实际发款时间：</td>
							                            <td>
							                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
							                                    <input type="text" id="actualLoanDate" name="contractInfoBean.actualLoanDate" maxlength="10" class="selectdate" placeholder="请选择时间">
							                                </label>
							                            </td>
							                            <td class="tr">抵押率：</td>
							                            <td>
							                                <label class="input">
							                                    <input class="input02" type="text" name="contractInfoBean.mortgageRate" maxlength="5">
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">借款原因：</td>
							                            <td colspan="7">
							                                <label class="input">
							                                     <input type="text" name="contractInfoBean.loanReason" maxlength="100">
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
                            
                            <div class="jarviswidget" id="wid-id-133"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>附件信息</h2>
                                </header>
                                
                                <div>
                                    <div class="smart-form">
                                        <div class="widget-body no-padding clearfix" style="margin-left:40px;">
                                            <div class="mt10 mb10 fl mr100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_1" disabled="disabled"/> 身份证</div>
                                                <input type="hidden" name="annexList[0].annexType" value="1"/>
                                                <input type="hidden" name="annexList[0].annexPath" id="annexPath_1" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_1" /></div>
                                            </div>

                                            <div class="mt10 mb10 fl mr100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_2" disabled="disabled"/> 服务协议</div>
                                                <input type="hidden" name="annexList[1].annexType" value="2"/>
                                                <input type="hidden" name="annexList[1].annexPath" id="annexPath_2" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_2" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl mr100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_3" disabled="disabled"/> 委托代扣协议</div>
                                                <input type="hidden" name="annexList[2].annexType" value="3"/>
                                                <input type="hidden" name="annexList[2].annexPath" id="annexPath_3" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_3" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl mr100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_4" disabled="disabled"/> 抵押合同</div>
                                                <input type="hidden" name="annexList[3].annexType" value="4"/>
                                                <input type="hidden" name="annexList[3].annexPath" id="annexPath_4" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_4" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl mr100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_5" disabled="disabled"/> 保证合同</div>
                                                <input type="hidden" name="annexList[4].annexType" value="5"/>
                                                <input type="hidden" name="annexList[4].annexPath" id="annexPath_5" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_5" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl mr10">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_6" disabled="disabled"/> 其&nbsp;他</div>
                                                <input type="hidden" name="annexList[5].annexType" value="6"/>
                                                <input type="hidden" name="annexList[5].annexPath" id="annexPath_6" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_6" /></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 

                            <div class="jarviswidget" id="wid-id-134"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>还款计划</h2>
                                    <div class="" style="float:left;">
					   				 	<button class="btn-success ml10 mt5 lh200 pr15 pl15" type="button" id="replaymentButton">生成还款计划</button>
									</div>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form mt10">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding clearfix">
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
						
										        </tbody>
                                            </table>
										</div>
                                       </div>
                                   </div>
                                </div>

                            <div class="jarviswidget" id="wid-id-135" data-widget-deletebutton="false" data-widget-editbutton="false">
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
							                                    <input type="text" maxlength="50" name="loanBankInfoBean.acctName"  value="" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">银行账号:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="loanBankInfoBean.bankNo"  value="" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td align="right" class="vt">开&nbsp;户&nbsp;行:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="loanBankInfoBean.bankName" value="" />
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
							                                <ul class="clearfix">
							                                    <li class="fl mr20">
							                                        <input type="radio" value="1" name="backBankInfoBean.paymentType" checked="checked" />代扣
							                                    </li>
							                                    <li class="fl mr20">
							                                        <input type="radio" value="2" name="backBankInfoBean.paymentType"/>汇款
							                                    </li>
							                                    <li class="fl">
							                                        <input type="radio" value="3" name="backBankInfoBean.paymentType" />转账
							                                    </li>
							                                </ul>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">开户人姓名:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="backBankInfoBean.acctName" value="" id="bkName" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">银行账号:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="backBankInfoBean.bankNo"  id="bkAccount" value="" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td align="right" class="vt">开&nbsp;户&nbsp;行:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="backBankInfoBean.bankName"  id="bkAddr" value="" />
							                                </label>
							                            </td>
							                        </tr>
							                    </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <div class="jarviswidget" id="wid-id-136"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
							                                    <input type="text" maxlength="50" name="organizationInfoBean.largeArea" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">区&nbsp;&nbsp;&nbsp;&nbsp;域：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="organizationInfoBean.area" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">城&nbsp;&nbsp;&nbsp;&nbsp;市：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="50" name="organizationInfoBean.city" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">门&nbsp;&nbsp;&nbsp;&nbsp;店：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="50" name="organizationInfoBean.store" value="" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">大区总监：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.largeAreaDirector" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">区域经理：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.areaManager" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">门店经理：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.storeManager" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">主&nbsp;&nbsp;&nbsp;&nbsp;管：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.director" value="" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">个贷人员：</td>
							                            <td>
							           						<label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.loanPerson" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">面&nbsp;&nbsp;&nbsp;&nbsp;审：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.checkPerson" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">外&nbsp;&nbsp;&nbsp;&nbsp;访：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.visitorPerson" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">电&nbsp;&nbsp;&nbsp;&nbsp;核：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.telCheckPerson" value="" />
							                                </label>
							                            </td>
							                        </tr>
							
							                        <tr>
							                            <td class="tr">区域电核：</td>
							                            <td>
							                                <label class="input">
							                                   <input type="text" maxlength="30" name="organizationInfoBean.areaPointCheckPerson" value="" />
							                                </label>
							                            </td>
							                            <td class="tr">终审：</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="30" name="organizationInfoBean.finalJudgment" value="" />
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
                            <div class="jarviswidget tc" id="wid-id-137">
                            <button class="btn btn-default table-nobg-btn" type="button"  id= "btn-success" ><i class="fa  fa-clipboard mr5"></i>保存</button>
                            </div>
                        </article>
                    </form>
                </div>
            </section>
        </div>
    </div>
    <form class="smart-form" action="${contextPath}/loan/loanList" method="post" id="loanListForm"></form>


<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
    <script src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js" type="text/javascript" charset="utf-8"></script>
    <link href="<%=request.getContextPath()%>/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <script src="${contextPath}/js/jquery.form.js" ></script>
    <script src="${contextPath}/js/jquery.alerts.js" ></script>
    

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


                $("#mens").append('<tr class="input_data"><td class="tr">共借人：</td><td><label class="input"> <input class="input"  name="commLoanPersonName" maxlength="40" ></label></td><td class="tr">共借人电话：</td><td><label class="input"><input class="input"  name="commLoanPersonPhone" maxlength="11" ></label></td><td class="tr">共借人身份证：</td><td><label class="input" style="width:220px"><input class="input" name="commLoanPersonCertNo" maxlength="20" ></label></td> <td><a class="delete_mens">删除</a></td></tr>');
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
            	
				if (validateCheck()) {
	            	changAttrName();
	                $("#formform").ajaxSubmit({
	                    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	                    dataType:"json",
	                    success:function(data){	
	                        if (data.code == '0000') {
	                        	jAlert("添加成功!", '确认信息');
	                        	$("#loanListForm").submit();
	                        	return;
	                        } else {
	                        	$("#addErrorMsg").html(data.message)
	         
	                        	$("html,body").animate({scrollTop: $("#addErrorMsg").offset().top - 100},0);
	                        	return;
	                        }
	                    }
	                });
				}
            });
            
            //输入data校验
            function validateCheck() {
            	var flg =true;
	          	if ($.trim($("#contractNo").val()) == "") {
	        		alert ("合同编号不能为空！");
	        		$("#contractNo").focus();
	        		return false;
	        	 } 
	          	return flg;
            }
            
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
        		
        		//等额本息
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
        		
        		

            	

                //日期型判断
                dateCheck();
                //数字型判断
                numCheck();

                $("#borrow-rep-table1").dataTable().fnDestroy();
                DT_page("borrow-rep-table1",false);
            });
            
            

            DT_page("borrow-rep-table1",false);

            //日期型判断
            dateCheck() ;
            //数字型判断
            numCheck();
            
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
        
		//把一览的内容附上name属性
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
    						$(this).focus();
    						return false;
    					} else {
    						$(this).val(Number($(this).val()).toFixed(2));
    					}
		        	}
        		});
        	});
        	
        	var rg2 = /^(-?\d+)?$/;
        	$("#limitTimes").off();
        	$("#limitTimes").blur(function() {
        		if($("#limitTimes").val() != "") {
					if(!rg2.test($("#limitTimes").val())) {
						var msg = "请输入有效的数字 !";
						alert(msg);
						//$(this).val($(this).val() + msg);
						$(this).focus();
						return false;
					}
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
    	}
    	
    	function fileDownload(id){
    		var annexPath = $("#annexPath_"+id).val();
    		$("#download_"+id).attr("href","${contextPath}/loan/downLoad?urlPath="+ annexPath);
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