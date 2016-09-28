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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2850px;">
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
                                        <col width="200" />
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
                                                <td><fss:fmtData  value="${t.mobilePhone}"/></td>
                                                <td>${t.certType==1?"身份证":"护照"}</td>
                                                <td><fss:fmtData value="${t.certNo}"/></td>
                                                <td>${t.certIssueDate}</td>
                                                <td>${t.certFailDate}</td>
                                                <td>${t.sex==1?"男":"女"}</td>
                                                <td>${t.address}</td>
                                                <td>${t.birthdate}</td>
                                                <td>${t.isvalid==0?"有":"没有"}</td>
                                                <td>${t.bankLongName}</td>
                                                <td><fss:fmtData value="${t.bankNo}"/></td>
                                                <td><fss:fmtDate value="${t.createTime}" /></td>
                                                <td><fss:fmtDate value="${t.modifyTime}" /></td>
                                                <td>
                                                    <a  onclick="showInfo(${t.id})">核对</a>&nbsp;&nbsp;
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
<div class="box_pop"  style="display:none;">
<div class="pop"  style="display:block;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-500px;margin-top:-275px;width: 1000px;height:550px; padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;">
    <div style="height:300px;border:1px gray solid;">
        <br/>
        <h1 class="f18" align="center">信息核对</h1>
    <div class="mt10 mb10 ml30" style="float: left" >
        <h1 class="f18" align="center">富友返回信息</h1>
        <br/>
        <table class="table">
            <col width="112" />
            <col width="367" />
            <col />
            <tbody>
            <tr>
                <td align="right">客户姓名：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" name="cust_nm" id="cust_nm" style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">手机号码：</td>
                <td id="">
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" name="mobile_no" id="mobile_no" style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">身份证号：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" name="certif_id" id="certif_id" style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">开户行名称：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" id="bank_nm" name="bank_nm"  style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">银行卡号：</td>
                <td>
                    <label class="input">
                        <input type="hidden" maxlength="50" readonly="readonly" style="border:none" id="capAcntNos"  style="width:256px;" />
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" id="capAcntNo" name="capAcntNo"  style="width:256px;" />
                    </label>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="mt10 mb10 ml30" style="float: right">
        <h1 class="f18" align="center">客户信息</h1>
        <br/>
        <table class="table">
            <col width="112" />
            <col width="200" />
            <col />
            <tbody>
            <tr>
                <td align="right">客户姓名：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" name="customerName" id="customerName" style="width:256px;" />
                        <input type="text" maxlength="50" hidden  id="id" style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">手机号码：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" name="mobilePhone" id="mobilePhone" style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">身份证号：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" name="certNo" id="certNo" style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">开户行名称：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" id="bankLongName" name="bankLongName"  style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">银行卡号：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" readonly="readonly" style="border:none" id="bankNo" name="bankNo"  style="width:256px;" />
                    </label>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
        <br/>
    </div>
    <div style="height: 120px;border:1px gray solid;border-top: none;">
        <br/>
        <h1 class="f18" align="center">富有返回状态</h1>
        <br/>
        <div>
            <table class="table">
            <col width="150" />
            <col width="220" />
            <col width="150" />
            <col width="220" />
            <col />
            <tbody>
            <tr>
                <td align="right">卡密认证状态：</td>
                <td>
                    <section class="fl">
                        <input type="text" maxlength="10" id="card_pwd_verify_st" hidden name="card_pwd_verify_st" style="width:256px;"/>
                        <label class="text"  >
                            <input type="text" maxlength="10" style="border:none" id="card_pwd_verify_sts" readonly name="card_pwd_verify_sts" style="width:256px;"/>
                        </label>
                    </section>
                </td>
                <td align="right">账户验证状态：</td>
                <td>
                    <label class="input">
                        <input type="text" maxlength="50" id="id_nm_verify_st" hidden name="id_nm_verify_st"  style="width:256px;" />
                        <input type="text" maxlength="50" id="id_nm_verify_sts" style="border:none"  name="id_nm_verify_sts"  style="width:256px;" />
                    </label>
                </td>
            </tr>
            <tr>
                <td align="right">账户生效状态：</td>
                <td>
                    <section class="fl">
                        <label class="input" >
                            <input type="text" maxlength="10" id="contract_st" hidden name="contract_st" style="width:256px;"/>
                            <input type="text" maxlength="10" id="contract_sts" style="border:none"  name="contract_sts" style="width:256px;"/>
                        </label>
                    </section>
                </td>
                <td align="right">用户状态：</td>
                <td>
                    <section class="fl">
                        <label class="input" >
                            <input type="text" maxlength="10" id="user_st" hidden name="user_st" style="width:256px;"/>
                            <input type="text" maxlength="10" id="user_sts" style="border:none" readonly name="user_sts" style="width:256px;"/>
                        </label>
                    </section>
                </td>

            </tr>
            </tbody>
        </table>
    </div>

    </div>
    <div style="height: 50px" id="wid-id-713" align="center">
        <br/>
        <button class="btn btn-default table-nobg-btn" type="button" id="checkbankNo" style="display: none" onclick="checkbankNo()" >信息校对</button>
        <button class="btn btn-default table-nobg-btn" type="button" id="dropAccount" style="display: none" onclick="dropAccount()" >销户确认</button>
        <button class="btn btn-default table-nobg-btn" type="button" id="checkState" style="display: none" onclick="checkState()">状态核对</button>
        <button class="btn btn-default table-nobg-btn" type="button"   onclick="hideDiv()">返回</button>
    </div>
</div>
    <div style=" z-index:6666; position:absolute;left:0;top:0;width: 100%;height: 100%;background:#000;filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5;opacity: 0.5;  "></div>
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
    function checkbankNo() {
        var id=$("#id").val();
        $.ajax({
            url:"${contextPath}/checkCustomerInfo/checkbankNo/"+id,
            method:"post",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data:{bankCode:$("#bankCode").val(),bankNo:$("#capAcntNos").val(),bankName: $("#bank_nm").val()},
            dateType:"json",
            success : function (data){
                if (data.code == '0000') {
                    alert(data.msg);
                    location.reload();
                }else{
                    alert(data.msg);
                }
            }
        })
    }
    function checkState(id) {
        alert("请去冠e通后台进行账户状态核对");
    }
    function hideDiv() {
        $('.box_pop').hide();
    }
    function dropAccount() {
        var id=$("#id").val();
        $.ajax({
            url:"${contextPath}/checkCustomerInfo/dropAccount/"+id,
            method:"post",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dateType:"json",
            success : function (data){
                if (data.code == '0000') {
                    alert(data.msg);
                    $("#custForm").submit();
                }else{
                    alert(data.msg);

                }
            }
        })
    }
    function showInfo(id) {
//        wait("正在审核中，请耐心等待...");
        $.ajax({
            url:"${contextPath}/customerInfo/checkCustomerInfo/"+id,
            method:"post",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dateType:"json",
            success : function (data){
                if(data.code=='0000'){
                    $('.box_pop').show();

                    //富有返回信息
                    $("#cust_nm").val(data.custmerMap.cust_nm);
                    $("#mobile_no").val(transferTo(data.custmerMap.mobile_no));
                    $("#certif_id").val(transferTo(data.custmerMap.certif_id));
                    $("#bank_nm").val(data.custmerMap.bank_nm);

                    $("#capAcntNos").val(data.custmerMap.capAcntNo);
                    $("#capAcntNo").val(transferTo(data.custmerMap.capAcntNo));
                    $("#card_pwd_verify_st").val(data.custmerMap.card_pwd_verify_st);
                    if($("#card_pwd_verify_st").val() == 0){
                        $("#card_pwd_verify_sts").val("未验证");
                    }else{
                        $("#card_pwd_verify_sts").val("已验证");
                    }

                    $("#id_nm_verify_st").val(data.custmerMap.id_nm_verify_st);
                    if($("#id_nm_verify_st").val() == 0){
                        $("#checkState").show();
                        $("#id_nm_verify_sts").val("未验证");
                    }else{
                        $("#id_nm_verify_sts").val("已验证");
                    }
                    $("#contract_st").val(data.custmerMap.contract_st);
                    if($("#contract_st").val()=='0'){
                        $("#checkState").show();
                        $("#contract_sts").val("未通过");
                    }else if($("#contract_st").val() =='1'){
                        $("#contract_sts").val("通过");

                    }else{
                        $("#checkState").show();
                        $("#contract_sts").val("待验证");
                    }
                    $("#user_st").val(data.custmerMap.user_st);
                    if($("#user_st").val()=='1'){
                        $("#user_sts").val("正常");
                    }else if($("#user_st").val() =='2'){
                        $("#checkState").show();
                        $("#user_sts").val("已注销");
                    }else{
                        $("#dropAccount").show();
                        $("#user_sts").val("申请注销");
                    }
                    //本地信息
                    $("#id").val(data.bean.id);
                    $("#customerName").val(data.bean.customerName);
                    $("#mobilePhone").val(transferTo(data.bean.mobilePhone));
                    $("#certNo").val(transferTo(data.bean.certNo));
                    $("#bankLongName").val(data.bean.bankLongName);
                    $("#bankNo").val(transferTo(data.bean.bankNo));
                    var a=data.custmerMap.capAcntNo;
                    var b=data.bean.bankNo;
                    if(a===b){
                   }else{
                        $("#bankNo").css("color","red")
                       $("#checkbankNo").show();
                   }
                    if(data.bean.mobilePhone===data.custmerMap.mobile_no){
                    }else{
                        $("#mobilePhone").css("color","red")
                    }
                    if(data.bean.certNo===data.custmerMap.certif_id){
                    }else{
                        $("#certNo").css("color","red")
                    }
                }else{
                    alert(data.msg);
                }

            }
        })
    }
    function  transferTo(va) {
        var result=null;
        if(va != null){
            if(va.length>11){
                result = va.substring(0,4)+"*****"+va.substring(va.length-5);
            }else if(va.length == 11){
                result = va.substring(0,2)+"*****"+va.substring(va.length-4);
            }else if(va.length > 6){
                result = va.substring(0,2)+"*****"+value.substring(va.length()-3);
            }else{
                result = va;
            }
        }
       return result;
    }
</script>


<%@include file="../../../view/include/foot.jsp"%>
</body>



</html>