<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../view/include/common_css_js.jsp"%>
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
<%@include file="../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>资金结算</li>
            <li>主页</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget" id="main_001"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>数据统计</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body no-padding">
                                    <div class="mt10 mb10">
                                        <table class="table">
                                            <col />
                                            <tbody>
                                            <tr class="lh32">
                                                <td class="tr">

                                                </td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                   <%-- <footer>
                                        <button class="btn btn-primary" onclick="javascript:void(0);">确认</button>
                                    </footer>--%>
                                </div>
                                <!-- end widget content -->
                        </div>


                    </div>
                    <!--
                         -->
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->

                    <!--/article>
                        </div>
                        <div class="row"-->
                    <!-- NEW WIDGET START -->
                    <!--article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"-->

                </article>
            </div>

        </section>
    </div>
<%@include file="../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();

        DT_page("borrow-rep-table12",true,'',$("#logListForm"));

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

        //添加按钮按下
        $("#btn_add").button().click(function() {
            window.open("/loan/loanAdd?","_self");
        });

        //删除按钮
        $('#btn_delete').click(function(){
            var no=$('#borrow-rep-table1 tbody :checkbox:checked');
            if(no.size()==0){
                alert("请选择要删除的合同！");
                return false;
            }
            if(!confirm("确认删除借款合同吗?")){
                return false;
            }
            var param=[];
            no.each(function(){
                param.push($(this).val());
            })
            $.post("/loan/loanDelete?",{'no':param.toString()},function(data){
                if(data>0){
                    alert("删除成功!");
                    //jAlert("删除成功!",'确认信息');
                    $("#loanListForm").submit();
                    return false;
                }
                return false;
            });

        });

        //日期型判断
        dateCheck() ;


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

    });

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

</script>

</body>

</html>