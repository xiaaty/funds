<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>内审系统--出借管理-新增出借</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

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
                <li>出借管理</li>
                <li>新增出借</li>
            </ol>
            <!-- end breadcrumb -->
        </div>
        <div class="clearfix ml20" id="addErrorMsg" style="color:red;"></div>
        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    
                        <article class="col-sm-12 col-md-12 ui-sortable">
							<form id="fm" class="smartform" method="post" action="${contextPath}/lend/lendAddSbt">
                            <div class="jarviswidget" id="wid-id-210"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
												                <input type="text" class="input01" maxlength="20" name= "customerInfoBean.customerName">
												            </label>
												        </td>
												        <td class="tr">移动电话：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input01" maxlength="12" id="mobilePhone" name= "customerInfoBean.mobilePhone">
												            </label>
												        </td>
												        <td class="tr">国&nbsp;&nbsp;籍：</td>
												        <td>
												            <label class="input">
												            	<input type="text" class="input01" maxlength="12" name= "customerInfoBean.nationality">
												            </label>
												        </td>
												        <td class="tr">证件类型：</td>
												        <td>
												            <label class="select">
												                <select id="certType" name ="customerInfoBean.certType">
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
												                  <input type="text" class="input01" maxlength="20" id="certNo" name= "customerInfoBean.certNo">
												            </label>
												        </td>
												        <td class="tr">发证机关所在地：</td>
												        <td>
												            <label class="input">
												                 <input type="text" class="input01" maxlength="100" name= "customerInfoBean.certAddress">
												            </label>
												        </td>
												        <td class="tr">签发日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text" maxlength="10" name=customerInfoBean.certIssueDate class="selectdate" readonly="readonly" placeholder="请选择时间">
												            </label>
												        </td>
												        <td class="tr">失效日期：</td>
												        <td>
												            <label class="input"> <i class="icon-append fa fa-calendar"></i>
												                <input type="text" maxlength="10" name="customerInfoBean.certFailDate" class="selectdate" readonly="readonly" placeholder="请选择时间">
												            </label>
												        </td>
												    </tr>
												    <tr>
												        <td class="tr">性&nbsp;&nbsp;别：</td>
												        <td>
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
												                <input type="text" name="customerInfoBean.birthdate" class="selectdate" id="birthdate" readonly="readonly" placeholder="请选择时间">
												            </label>
												        </td>
												        <td class="tr">学&nbsp;&nbsp;历：</td>
												        <td>
												            <label class="select">
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
												               <input type="text" class="input01" maxlength="50" name= "customerInfoBean.email">
												            </label>
												        </td>
												        <td class="tr">邮&nbsp;&nbsp;编：</td>
												        <td>
												            <label class="input">
												                <input type="text" class="input01" maxlength="10" name= "customerInfoBean.zipCode">
												            </label>
												        </td>
												        <td colspan="4"></td>
												    <tr>
												        <td class="tr">通讯地址：</td>
												        <td colspan="7" class="lh32">
												            <span class="fl tr" style="width: 50px">国家</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" name= "customerInfoBean.addrCountry"/>
													        </label>
												            <span class="fl tr" style="width: 80px">省(直辖市)</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" name= "customerInfoBean.addrProvince"/>
													        </label>
												            <span class="fl tr" style="width: 50px">市(州)</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" name= "customerInfoBean.addrCity"/>
													        </label>
												            <span class="fl tr" style="width: 50px">区(县)</span>
												            <label class="input fl" style="width: 160px">
													                <input type="text" class="input01" maxlength="50" name= "customerInfoBean.addrTown"/>
													        </label>
													    </td>
												           
												    </tr>
												    </tbody>
												</table>
                                                <div class="loan_line">紧急联系人信息</div>
                                                <table class="table bdr_2">
                                                    <col width="100" />
                                                    <col width="230" />
                                                    <col width="110" />
                                                    <col width="230" />
                                                    <col width="120" />
                                                    <col />
                                                    <tbody id="mens">
                                                        <tr>
                                                            <td class="tr">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="customerInfoBean.emerContact">
                                                                </label>
                                                            </td>
                                                            <td class="tr">移动电话：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="emerContactMobile" name="customerInfoBean.emerContactMobile">
                                                                </label>
                                                            </td>
                                                            <td class="tr">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                                                            <td>
                                                                <label class="select" style="width:220px">
                                                                	<select name="customerInfoBean.emerContactSex">
												                    	<option value="1">男</option>
												                    	<option value="2">女</option>
												                	</select>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">与您的关系：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="customerInfoBean.emerContactRela">
                                                                </label>
                                                            </td>
                                                            <td class="tr">固定电话：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="emerContactPhone" name="customerInfoBean.emerContactPhone">
                                                                </label>
                                                            </td>
                                                            <td class="tr">教育程度：</td>
                                                            <td>
                                                                <label class="input" style="width:220px">
                                                                    <input type="text" name="customerInfoBean.emerContactEduca">
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="jarviswidget" id="wid-id-211"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2><i class="fa fa-edit pr10"></i>合同信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10">
                                                <table class="table">
                                                	<col width="160" />
												    <col />
												    <col width="140" />
												    <col />
												    <col width="150" />
												    <col />
												    <col width="120" />
												    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td class="tr">出借编号：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="agreeNo" name="lendAgreement.agreeNo">
                                                                </label>
                                                            </td>
                                                            <td class="tr">预计出借日期：</td>
                                                            <td>
                                                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="lendAgreement.estimateTime" class="selectdate" readonly="readonly" placeholder="请选择时间">
                                                                </label>
                                                            </td>
                                                            <td class="tr">实际划扣时间：</td>
                                                            <td>
                                                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="lendAgreement.actualTime" class="selectdate" readonly="readonly" placeholder="请选择时间">
                                                                </label>
                                                            </td>
                                                            <td class="tr">出借类型：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.lendType">
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">资金出借及回收方式：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.fundsType">
                                                                </label>
                                                            </td>
                                                            <td class="tr">出借期限：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <select name="lendAgreement.lendTerm">
												                    	<option value="1月">1月</option>
												                    	<option value="3月">3月</option>
												                    	<option value="6月">6月</option>
												                    	<option value="12月">12月</option>
												                    	<option value="24月">24月</option>
												                	</select>
                                                                </label>
                                                            </td>
                                                            <td class="tr">年化收益率：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.annualYield">
                                                                </label>
                                                            </td>
                                                            <td class="tr">起投金额：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="lowestInvestAmount" name="lendAgreement.lowestInvestAmount">
                                                                </label>
                                                            </td>

                                                        </tr>
                                                        <tr>
                                                            <td class="tr">封顶金额：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="highestInvestAmount" name="lendAgreement.highestInvestAmount">
                                                                </label>
                                                            </td>
                                                            <td class="tr">出借金额（小写）：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="lendAmount" name="lendAgreement.lendAmount">
                                                                </label>
                                                            </td>
                                                            <td class="tr">出借金额（大写）：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.lendAmountCapital">
                                                                </label>
                                                            </td>
                                                            <td class="tr">账户管理费：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" id="counterFee" name="lendAgreement.counterFee">
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
                            <div class="jarviswidget" id="wid-id-212"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>上传附件</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding clearfix">
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_1" disabled="disabled"/> 身份证</div>
                                                <input type="hidden" name="annexList[0].annexType" value="1"/>
                                                <input type="hidden" name="annexList[0].annexPath" id="annexPath_1" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_1" /></div>
                                            </div>

                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_2" disabled="disabled"/> 服务协议</div>
                                                <input type="hidden" name="annexList[1].annexType" value="2"/>
                                                <input type="hidden" name="annexList[1].annexPath" id="annexPath_2" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_2" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_3" disabled="disabled"/> 委托代扣协议</div>
                                                <input type="hidden" name="annexList[2].annexType" value="3"/>
                                                <input type="hidden" name="annexList[2].annexPath" id="annexPath_3" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_3" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_4" disabled="disabled"/> 抵押合同</div>
                                                <input type="hidden" name="annexList[3].annexType" value="4"/>
                                                <input type="hidden" name="annexList[3].annexPath" id="annexPath_4" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_4" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_5" disabled="disabled"/> 保证合同</div>
                                                <input type="hidden" name="annexList[4].annexType" value="5"/>
                                                <input type="hidden" name="annexList[4].annexPath" id="annexPath_5" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_5" /></div>
                                            </div>
                                            <div class="mt10 mb10 fl ml100">
                                                <div class="fl lh30 mr10"><input type="checkbox" id="ck_6" disabled="disabled"/> 其&nbsp;他</div>
                                                <input type="hidden" name="annexList[5].annexType" value="6"/>
                                                <input type="hidden" name="annexList[5].annexPath" id="annexPath_6" value=""/>
                                                <div style="background:#fff;width:80px;overflow:hidden;"><input type="file" id="file_upload_6" /></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="jarviswidget" id="wid-id-213"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>银行信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding clearfix">
                                            <div class="fl ml50 mr100 mt20 mb10" >
                                                <h2 class="color01 mb10 b ml10">出借银行信息：</h2> 
                                                <table class="table" style="width:400px">
							                        <tr>
							                            <td align="right">付款方式:</td>
							                            <td class="lh30">
							                                <ul class="clearfix">
							                                    <li class="fl mr20">
							                                        <input type="radio" value="1" name="lendBankInfoBean.paymentType" value="3" checked="checked" />代收
							                                    </li>
							                                    <li class="fl mr20">
							                                        <input type="radio" value="2" name="lendBankInfoBean.paymentType" value="3" />汇款
							                                    </li>
							                                    <li class="fl">
							                                        <input type="radio" value="3" name="lendBankInfoBean.paymentType" />转账
							                                    </li>
							                                </ul>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">开户人姓名:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="lendBankInfoBean.acctName" value="" id="lendName" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td class="tr">银行账号:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="lendBankInfoBean.bankNo"  id="lendAccount" value="" />
							                                </label>
							                            </td>
							                        </tr>
							                        <tr>
							                            <td align="right" class="vt">开&nbsp;户&nbsp;行:</td>
							                            <td>
							                                <label class="input">
							                                    <input type="text" maxlength="50" name="lendBankInfoBean.bankName"  id="lendAddr" value="" />
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
							                                        <input type="radio" value="1" name="backBankInfoBean.paymentType" value="3" checked="checked" />代付
							                                    </li>
							                                    <li class="fl mr20">
							                                        <input type="radio" value="2" name="backBankInfoBean.paymentType" value="3" />汇款
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
                             <div class="jarviswidget" id="wid-id-214"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
                                                                    <input type="text" name="lendAgreement.largeArea">
                                                                </label>
                                                            </td>
                                                            <td class="tr">区&nbsp;&nbsp;&nbsp;&nbsp;域：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.regional">
                                                                </label>
                                                            </td>
                                                            <td class="tr">城&nbsp;&nbsp;&nbsp;&nbsp;市：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.city">
                                                                </label>
                                                            </td>
                                                            <td class="tr">门&nbsp;&nbsp;&nbsp;&nbsp;店：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.store">
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">理财顾问：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.consultant">
                                                                </label>
                                                            </td>
                                                            <td class="tr">大区经理：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" name="lendAgreement.districtManager">
                                                                </label>
                                                            </td>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                        </tr>
                                                       
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <!-- end widget content -->
                                    </div>
                                </div>
                            </div>
                            </form>
                            <div class="jarviswidget tc" id="wid-id-215">
                            	<button class="btn btn-default table-nobg-btn btn_sub" type="button" ><i class="fa  fa-clipboard mr5"></i>保存</button>
                            </div>
                        </article>
                </div>
            </section>
        </div>
    </div>

   <%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
    <script src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js" type="text/javascript" charset="utf-8"></script>
    <link href="<%=request.getContextPath()%>/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <script src="${contextPath}/js/jquery.form.js" ></script>
    <script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script>
  		//校验整数数字正则
    	var isNum = /^([1-9]\d*|[0]{1,1})$/;
    	//居住电话正则表达式
    	var mdd =/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
    	//金额正则
    	var rg = /^(-?\d+)(\.\d{0,2})?$/;
    	//身份证正则表达式(15位) 
        isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/; 
        //身份证正则表达式(18位) 
        isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}(x|X))$/; 
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
                'uploader':'<%=request.getContextPath()%>/lend/addLendAnnex',
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
        $(document).ready(function () {
        	pageSetUp();
        	
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
        	
            $('.btn_sub').click(function(){
            	var mobilePhone = $('#mobilePhone').val();
            	var emerContactMobile = $('#emerContactMobile').val();
            	var emerContactPhone = $('#emerContactPhone').val();
            	var agreeNo = $.trim($('#agreeNo').val());
            	var lowestInvestAmount = $('#lowestInvestAmount').val();
            	var highestInvestAmount = $('#highestInvestAmount').val();
            	var lendAmount = $('#lendAmount').val();
            	if(mobilePhone && (!isNum.test(mobilePhone) || mobilePhone.length != 11)){
            		alert('请输入正确的移动电话！');
            		$('#mobilePhone').focus();
            		return false; 
            	}
            	if(emerContactMobile && (!isNum.test(emerContactMobile) || emerContactMobile.length != 11)){
            		alert('请输入正确的移动电话！');
            		$('#emerContactMobile').focus();
            		return; 
            	}
            	if(emerContactPhone && !mdd.test(emerContactPhone)){
            		alert('请输入正确的固定电话！');
            		$('#emerContactPhone').focus();
            		return; 
            	}
            	if(!agreeNo){
            		alert('出借编号不能为空！');
            		$('#agreeNo').focus();
            		return; 
            	}
            	if(lowestInvestAmount && !rg.test(lowestInvestAmount)){
            		alert('请输入正确的金额！');
            		$('#lowestInvestAmount').focus();
            		return; 
            	}
            	if(highestInvestAmount && !rg.test(highestInvestAmount)){
            		alert('请输入正确的金额！');
            		$('#highestInvestAmount').focus();
            		return; 
            	}
            	if(lendAmount && !rg.test(lendAmount)){
            		alert('请输入正确的金额！');
            		$('#lendAmount').focus();
            		return; 
            	}
            	var certType = $('#certType').val();
            	var certNo = $('#certNo').val();
            	if('1' == certType && certNo && !isIDCard1.test(certNo) && !isIDCard2.test(certNo)){
            		alert('请输入正确的身份证号码！');
            		$('#certNo').focus();
            		return;
            	}
            	$("#fm").ajaxSubmit({
                    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                    dataType:"json",
                    success:function(data){	
                        if (data.code == '0000') {
                        	jAlert("添加成功!", '确认信息');
                        	window.open("${contextPath}/lend/lendList","_self");
                        	return;
                        } else {
                        	$("#addErrorMsg").html("添加失败，出借编号已经存在！");
         
                        	$("html,body").animate({scrollTop: $("#addErrorMsg").offset().top - 100},0);
                        	return;
                        }
                    }
                });

            });
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
        });
        $('#certNo').blur(function(){
        	var certNo = $(this).val();
        	var certType = $('#certType').val();
        	if(!certNo || '1' != certType){
        		return;
        	}
        	if(isIDCard1.test(certNo) || isIDCard2.test(certNo)){
        		var birthday = getBirthdayFromIdCard(certNo);
        		$('#birthdate').val(birthday);
        	}else{
        		$('#birthdate').val('');
        		alert('请输入正确的身份证号码！')
        	}
        });
        function getBirthdayFromIdCard(idCard) {
            var birthday = "";
            if(idCard != null && idCard != ""){
                if(idCard.length == 15){
                    birthday = "19"+idCard.substr(6,6);
                } else if(idCard.length == 18){
                    birthday = idCard.substr(6,8);
                }
                birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
            }
            return birthday;
        }
        
    	function fileDownload(id){
    		var annexPath = $("#annexPath_"+id).val();
    		$("#download_"+id).attr("href","${contextPath}/lend/downLoad?urlPath="+ annexPath);
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