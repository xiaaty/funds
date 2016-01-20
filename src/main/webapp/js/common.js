
  /*通用对话框样式*/
  $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
       _title : function(title) {
          if (!this.options.title) {
            title.html("&#160;");
          } else {
            title.html(this.options.title);
          }
        }
  }));



/* 表格翻页通用方法 */
function DT_page(tag,bPaginate,json,form){
    var totleInfo = "共计 _TOTAL_ 条记录"
    var pageSize = "10" ;
    var obj={};
    if(bPaginate){
        var obj = $.parseJSON(json);
        totleInfo = "共计 "+obj.pages+" 页 "+obj.total+" 条记录"
        pageSize  = obj.pageSize;
    }
    $('#'+tag).on("init.dt",function(){
        if(bPaginate){
            //borrow-rep-table1_length  显示页数
            form.append("<input type='hidden' name='pageNum' value='"+$("#"+tag+"_length select").val()+"'>");
            form.append("<input type='hidden' name='cpage' value='1'>");
            setPage(form,obj,tag);
        }
    });
    $('#'+tag).on("draw.dt",function(){
        if(bPaginate){
            //borrow-rep-table1_length  显示页数
            form.append("<input type='hidden' name='pageNum' value='"+$("#"+tag+"_length select").val()+"'>");
            form.append("<input type='hidden' name='cpage' value='1'>");
            setPage(form,obj,tag);
        }
    });
   /* var common_table = $('#'+tag).dataTable({
        pagingType:"full_numbers",
        paging:bPaginate,
        sort:false,
        lengthChange:false,
        searching:false,
        scrollX:"100%",
        scrollXInner: "100%",
        scrollCollapse:true,
        "language": {
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": totleInfo,
            "sInfoEmpty": "没有数据",
            "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "尾页"
            }
        }
    });*/
      var common_table = $('#'+tag).dataTable({
               "bPaginate":bPaginate,
               "sPaginationType" : "bootstrap_full",
               "bAutoWidth":false,
               "bLengthChange": false,//改变每页显示数据数量
               "bFilter": false, //过滤功能
               "bSort": false,//排序功能
                'iDisplayLength':pageSize,
               "oLanguage": {
                     "sZeroRecords": "抱歉， 没有找到",
                     "sInfo": totleInfo,
                     "sInfoEmpty": "没有数据",
                     "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                     "oPaginate": {
                                "sFirst": "首页",
                                "sPrevious": "上一页",
                                "sNext": "下一页",
                                "sLast": "尾页"
                                 }
                     },
                    "sZeroRecords": "没有检索到数据"
                 });

      return common_table;
}


  function setPage(form,obj,tag){
      //去掉页面记录显示条数事件
      $("#"+tag+"_length select").val(obj.pageSize);
      $("#"+tag+"_length select").off();
      //新添加页面显示记录条数事件
      $("#"+tag+"_length select").change(function(){
          form.find("[name='pageNum']").val($(this).val());
          pageSetup(form,"1");
      });
      $("#"+tag+"_wrapper .pagination .active a").text(obj.pageNum);
      //去掉原控件所有事件
      $("#"+tag+"_wrapper .pagination .first a").off();
///      $("#"+tag+"_wrapper .pagination .previous a").off();
      $("#"+tag+"_wrapper .pagination .prev a").off();
      $("#"+tag+"_wrapper .pagination .next a").off();
      $("#"+tag+"_wrapper .pagination .last a").off();
      $("#"+tag+"_wrapper .pagination .active a").off();
      //去掉原始所有禁用css
      $("#"+tag+"_wrapper  .first").removeClass("disabled");
      //$("#"+tag+"_wrapper  .previous").removeClass("disabled");
      $("#"+tag+"_wrapper  .prev").removeClass("disabled");
      $("#"+tag+"_wrapper  .next").removeClass("disabled");
      $("#"+tag+"_wrapper  .last").removeClass("disabled");

      if(obj.isFirstPage == "true"){
          $("#"+tag+"_wrapper .pagination .first").addClass("disabled");
      }
      if(obj.hasPreviousPage == "false"){
          //$("#"+tag+"_wrapper .pagination .previous").addClass("disabled");
          $("#"+tag+"_wrapper .pagination .prev").addClass("disabled");
      }
      if(obj. hasNextPage== "false"){
          $("#"+tag+"_wrapper .pagination .next").addClass("disabled");
      }
      if(obj.isLastPage == "true"){
          $("#"+tag+"_wrapper .pagination .last").addClass("disabled");
      }

      $("#"+tag+"_wrapper .pagination .active a").click(function(){
          pageSetup(form,obj.pageNum,tag);
      });

      //添加分页事件
      $("#"+tag+"_wrapper .pagination .first a").click(function(){
          pageSetup(form,"1",tag);
      });
      $("#"+tag+"_wrapper .pagination .prev a").click(function(){
          pageSetup(form,obj.prePage,tag);
      });;
      $("#"+tag+"_wrapper .pagination .next a").click(function(){
          pageSetup(form,obj.nextPage,tag);
      });;
      $("#"+tag+"_wrapper .pagination .last a").click(function(){
          pageSetup(form,obj.pages,tag);
      });;
  }


  function pageSetup(form,page,tag){
        form.find("[name='pageNum']").val($("#"+tag+"1_length select").val());
        form.find("[name='cpage']").val(page);
        form.submit();
  }

  /*将form 表单元素的值序列化*/
  serializeObject = function (form) {
           var obj = {};
           $.each(form.serializeArray(), function (index) {
               obj[this['name']] = this['value'];
           });
      return obj;
   };


  /* 表格翻页通用方法 */
  function DT_ajax_page(tag,change,form,columns,change,changePage,columsDef){
      var url = form.attr("action");
      /*var common_table =  $('#'+tag).dataTable({
          //deferLoading:false,
          pagingType:"full_numbers",
          sort:false,
          lengthChange:false,
          pageLength: changePage,
          searching:false,
          processing: true,
          serverSide: true,
          destroy:true,
          columns: columns,
          "aoColumnDefs": columsDef,
          ajax: {
                    url:url,
                    type:"post",
                    data:function(d){
                        var obj = serializeObject(form);
                        for( var i in obj){
                            d[i]=obj[i];
                        }
                    }
                },
          "language": {

              "sProcessing" : "正在加载数据......",
              "sZeroRecords": "抱歉， 没有找到",
              "sInfo": "共计 _PAGES_ 页 _TOTAL_ 条记录",
              "sInfoEmpty": "没有数据",
              "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
              "oPaginate": {
                  "sFirst": "首页",
                  "sPrevious": "上一页",
                  "sNext": "下一页",
                  "sLast": "尾页"
              }
          }
      });*/
      var common_table = $('#'+tag).dataTable({
          "sPaginationType" : "bootstrap_full",
          "bAutoWidth":false,
          "bLengthChange": change,//改变每页显示数据数量
          "bFilter": false, //过滤功能
          "bSort": false,//排序功能
          "bProcessing": true,
          "bServerSide": true,
          'iDisplayLength':changePage,
          fnServerData:function ( sSource, aoData, fnCallback ){
              $.ajax({
                  "dataType": 'json',
                  "type": "POST",
                  data:aoData,
                  "url": sSource,
                  "success": function(json){
                      fnCallback(json);
                  }
              } );
          },
          "sAjaxSource": url,
          "bDestroy": true,
          "fnServerParams": function ( aoData ) {

              var obj = serializeObject(form);
              for( var i in obj){
                  var p = [];
                  p.name = i;
                  p.value = obj[i];
                  aoData.push(p);
              }

          },


          "aoColumnDefs": columsDef,
          "aoColumns": columns,
          "oLanguage": {
              "sProcessing" : "正在加载数据...",
              "sZeroRecords": "抱歉， 没有找到",
              "sInfo": "共计 _PAGES_ 页 _TOTAL_ 条记录",
              "sInfoEmpty": "没有数据",
              "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
              "oPaginate": {
                  "sFirst": "首页",
                  "sPrevious": "上一页",
                  "sNext": "下一页",
                  "sLast": "尾页"
              }
          },
          "sZeroRecords": "没有检索到数据"
      });
      return common_table;
  }
