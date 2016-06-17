/**
 * Created by xdw on 2016/6/15.
 */

//引用 ${contextPath}/js/jquery.blockUI.js
function bilocUtil(msg){
    $.blockUI({
        css: {
            border: 'none',
            padding: '15px',
            // backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            //   opacity: .7,
            bindEvents: true,
            constrainTabKey: false,
            color: '#000',
            //timeout :1000*60*30
            timeout : 5000

        },baseZ:999999,
         //message: '<img src="${contextPath}/img/loading.gif" />&nbsp;' + msg
        message: '<img src="/img/loading.gif" />&nbsp;' + msg
    });
    function closeBlock(){
        $.unblockUI();
        $(".blockUI").fadeOut("slow");
    }

}