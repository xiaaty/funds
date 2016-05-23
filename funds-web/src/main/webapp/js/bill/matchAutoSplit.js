/**
 * Created by 于泳 on 2015/1/10.
 */
////matchDetailGetRepayment
var AutoSplit = function(setting){
    if(typeof(setting) === "undefined"){
        alert("初始化失败");
        return null;
    }
    this._settings = setting;
    this._isInitComplete = false;
    this._repaymentPlan = {};
    this._repaymentActual = {};
    this._orgData = {};
    this._loanType = {};
    this._transDate = "";
    this._matchMoney = "";
    this._isClar = {};
    this._billTypeNameMap = {};
    this._auto = false;
    this._upDown = 0;
    this._isAutoSplit = false;
    this.init();
    this.log("初始化完成");
}
AutoSplit.prototype.init = function(){
    var auto = this;
    $.ajax({
        url:"matchDetailGetRepayment",
        data:{billId:this._settings.id},
        dataType:"json",
        type:"post",
        success:function(data){
            /*if(window.console && window.console.dir){
                window.console.dir(auto);
            }*/
            auto.setData(data);


        }
    });
}
/**
 * ajax 设置值
 * @param data
 */
AutoSplit.prototype.setData = function(data){
    this._orgData = data;
    this._transDate = data['transDate'];
    this._matchMoney = data['matchMoney'];
    this._repaymentPlan=data['repayment'];
    this._loanType = data['type'];
    this._isInitComplete = true;
    this._isClar = data['isClar'];
    this._billTypeNameMap = data['billTypeNameMap'];
    this._checkSubmit = true;
    this._upDown = data['upDown'];
    if(data.repayment === undefined){
        this._auto = false;
        $("#"+this._settings.aotuButton).hide();
//        $("#"+this._settings.manualSplitAccount).show();

        alert("无还款计划，初始化失败");
    }else{
        this._auto = true;
        $("#"+this._settings.aotuButton).show();
        $("#"+this._settings.manualSplitAccount).hide();
    }

    $("#"+this._settings.manualSplitAccount).hide();
}

AutoSplit.prototype.drawTable = function(obj){
    $(".matching tbody").empty();
    var formObj = this.serializeObject($("#"+this._settings.form));
//    this.dir(this._repaymentActual);
    var isEdit = false;
    if(formObj.balance == 1 || formObj.balance == 2){
        isEdit  = true;
    }

//    if()

    var auto = this._repaymentActual[obj['ID']];
    var addHtml = "<tr>";
    addHtml += '<td>'+obj['REPAYMENT_TIMES']+'</td>';
    addHtml += '<td>'+obj['REPAYMENT_DATE']+'</td>';
    addHtml += '<td>'+this.returnDouble(obj['SUBTOTAL'])+'    </td>';
    addHtml += '<td>'+this.returnDouble(this.compareInt(obj['SUBTOTAL'],obj['TOTAL']))+'    </td>';
    addHtml += '<td>'+this.returnDouble(this._upDown)+'</td>';
    addHtml += '<td>'+this.returnDouble(auto['capital'])+'</td>';
    addHtml += '<td>'+this.returnDouble(auto['interest'])+'</td>';

    if(!isEdit ){
        this.log(auto['isClear'])
        if(auto['isClear'] === undefined) auto['isClear'] = 4
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002104'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002104']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002105'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002105']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002106'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002106']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002107'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002107']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002108'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002108']))+'</td>';
    }else{
        addHtml += '<td><input type="text" class="input04 input_overdueFine" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002104\')" name="overdueFine" value="'+(this._repaymentActual[obj['ID']]['detail']['10002104'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002104']))+'" style="width:46px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_defaultInterest" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002105\')" name="defaultInterest" value="'+(this._repaymentActual[obj['ID']]['detail']['10002105'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002105']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04  input_prepaymentPenalty" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002106\')" name="prepaymentPenalty" value="'+(this._repaymentActual[obj['ID']]['detail']['10002106'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002106']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_nonbusinessIncome" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002107\')" name="nonbusinessIncome" value="'+(this._repaymentActual[obj['ID']]['detail']['10002107'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002107']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_abate" name="abate" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002108\')" value="'+(this._repaymentActual[obj['ID']]['detail']['10002108'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002108']))+'" style="width:34px" /></td>';
        if(auto['isClear'] === undefined) auto['isClear'] = 3;
    }
    if(auto['billType'] === undefined) auto['billType'] = 1

    addHtml += '<td><select class="select02 input_isClear" style="width:78px" name="isClear" onchange="AutoSplitUtil.changeIsChear(this.value,\''+obj['ID']+'\')">  ';
    for(var i in this._isClar){
        addHtml +='<option  value="'+i+'" '+(auto['isClear'] == i ?'selected':"")+'>'+this._isClar[i]+'</option>'
    }
    addHtml +=  '</select></td>';
    addHtml += '<td><select class="select02" name="billType" style="width:78px" onchange="AutoSplitUtil.changeBillType(this.value,\''+obj['ID']+'\')">';
    for(var i in this._billTypeNameMap){
        addHtml +='<option  value="'+i+'" '+(auto['billType'] == i ?'selected':"")+'>'+this._billTypeNameMap[i]+'</option>'
    }
    addHtml +=    '</select></td>';
    this.log(this.addInt(auto['capital'],auto['interest']) + '   ' +obj['SUBTOTAL']);
    if(!isEdit){
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002109'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002109']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002103'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002103']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002001'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002001']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002110'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002110']))+'</td>';
        addHtml += '<td>'+(this._repaymentActual[obj['ID']]['detail']['10002201'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002201']))+'</td>';
    }else{
        addHtml += '<td><input type="text" class="input04 input_visitFee" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002109\')" name="visitFee" value="'+(this._repaymentActual[obj['ID']]['detail']['10002109'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002109']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_serviceFee" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002103\')"  name="serviceFee" value="'+(this._repaymentActual[obj['ID']]['detail']['10002103'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002103']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_bail" name="bail" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002001\')" value="'+(this._repaymentActual[obj['ID']]['detail']['10002001'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002001']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_extentionFee" onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002110\')" name="extentionFee" value="'+(this._repaymentActual[obj['ID']]['detail']['10002110'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002110']))+'" style="width:34px" /></td>';
        addHtml += '<td><input type="text" class="input04 input_returnFee"  onkeyup="AutoSplitUtil.keyUp(this,\''+obj['ID']+'\',\'10002201\')" name="returnFee" value="'+(this._repaymentActual[obj['ID']]['detail']['10002201'] === undefined ? '0.00':this.returnDouble(this._repaymentActual[obj['ID']]['detail']['10002201']))+'" style="width:46px" /></td>';
    }

    addHtml += '<td class="detail_10002111">'+(auto['detail']['10002111'] === undefined ?'0.00':this.returnDouble(auto['detail']['10002111']))+'</td>';
    addHtml += '</tr>';

    $(".matching tbody").append(addHtml);
}

AutoSplit.prototype.changeIsChear = function(value,id){
    this._repaymentActual[id]['isClear']=value;
    this.log(this._repaymentActual[id]['isClear']);
}

AutoSplit.prototype.changeBillType = function(value,id){
    this._repaymentActual[id]['billType']=value;
}


AutoSplit.prototype.keyUp = function(obj,id,type){
    var ll = 0.0;
    if(!(this._repaymentActual[id]['detail']['10002111'] === undefined)){
        ll = this._repaymentActual[id]['detail']['10002111'];
    }
    this.log(this.returnLong(obj.value));
    if(this.compareInt(ll,this.returnLong(obj.value)) <0){
        alert('金额剩余不足');
        this._checkSubmit = false;
    }else{
        this._repaymentActual[id]['detail'][type]=obj.value;
        this._repaymentActual[id]['detail']['10002111']=this.compareInt(ll,this.returnLong(obj.value));
        this._checkSubmit = true;
        this.log("ttt:"+$(this).parent("tr").find(".detail_10002111").text());
        this.log($(obj).html());
        $(obj).parent().nextAll(".detail_10002111").text(this._repaymentActual[id]['detail']['10002111']);
    }


}


/**
 * 自动生成拆账信息
 * @param balance
 * @param isNotAmount
 */
AutoSplit.prototype.autoSplit = function(balance,isNotAmount){
    if(this._isInitComplete == false){
        return;
    }
    var matchMoney = this.addInt(this._matchMoney,this._upDown);
    this.log("matchMoney:"+matchMoney);
    var plan  = this._repaymentPlan;
    var formObj = {}
    if(this._settings.form){
        var formObj = this.serializeObject($("#"+this._settings.form));
    }else{
        formObj.balance = 1;
        formObj.isNotMoney = 1;
    }
    this.dir(formObj);
    for(var i in plan){
        var obj = plan[i];
        if(parseInt(obj['IS_CLEAR'],10) > 3){
            continue;
        }

        var total = this.compareInt(obj['SUBTOTAL'],obj['TOTAL']);
//        this.log("total:"+total)
        var capital = this.compareInt(obj['PRINCIPAL'],obj['CAPITAL']);
//        this.log("capital:"+capital)
        var interest = this.compareInt(obj['ACCRUAL'],obj['INTEREST']);
//        this.log("interest:"+interest)
        var compareFlag = this.compareInt(matchMoney,total);
//        this.log("compareFlag:"+compareFlag);
        if(this._repaymentActual[obj.ID] === undefined){
            this._repaymentActual[obj.ID] = new Object();
        }
        var actualChild = this._repaymentActual[obj.ID];
        actualChild['capital'] = capital;
        actualChild['interest'] = interest;
        actualChild['total'] = total;
        actualChild['contractId'] = obj['CONTRACT_ID'];
        actualChild['memo'] = "展期";
        actualChild['accountID'] = this._settings.accountID;
        actualChild['upDown'] = this._upDown;
        if(compareFlag=='0'){
            var detail  = {};
            actualChild['detail'] = detail;
            detail['10002101'] = capital;
            detail['10002102'] = interest;
            this.drawTable(obj);
            break;
        }else if(compareFlag > 0){
            var detail  = {};
            actualChild['detail'] = detail;
            detail['10002101'] = capital;
            detail['10002102'] = interest;
            detail['10002111'] = compareFlag;
            this.drawTable(obj);

            break;
        }else{
            var detail  = {};
            actualChild['detail'] = detail;
            if(formObj.isNotMoney == 1){
                if(this.compareInt(matchMoney,capital)>=0){
                    detail['10002101'] = capital;
                    detail['10002102'] = this.compareInt(matchMoney,capital);
                }else{
                    detail['10002101'] = this.compareInt(capital,matchMoney);
                }

            }else{

                if(this.compareInt(matchMoney,interest)>=0){
                    detail['10002101'] = this.compareInt(matchMoney,interest);
                    detail['10002102'] = interest;
                }else{
                    detail['10002101'] = this.compareInt(interest,matchMoney);
                }
                detail['10002101'] = capital;
                detail['10002102'] = interest;
            }


            this.drawTable(obj);



            break;
        }

    }
//    this.dir(this._repaymentPlan);
//    this.dir(this._repaymentActual);
//    this.log($.toJSON(this._repaymentActual));
    this._isAutoSplit = true;
}
/**
 * 上传数据到服务器
 */
AutoSplit.prototype.submit  = function(){
    //matchDetail.do
    //校验
    if(this._isAutoSplit == false){
        alert("请拆账");
        return;
    }

    if(!this._checkSubmit){
       alert("无法提交");
        return;
    }

//    return;
    $.ajax({
        url:"matchDetail.do",
        data:{billID:this._settings.id,json:$.toJSON(this._repaymentActual)},
        dataType:"json",
        type:"post",

        success:function(data){
           alert(data.code);
            if(data.code === "0000"){
                window.open("splitRecord","_self");
            }

        }
    });

}


/**
 * 数值比较，返回小于0，arg1小于arg2，等于0，相等，大于0，arg1大于arg2
 * @param arg1
 * @param arg2
 * @returns {number}
 */
AutoSplit.prototype.compareInt = function(arg1,arg2){
    return parseInt(arg1,10)-parseInt(arg2,10);
}
/**
 * 数值计算
 * @param arg1
 * @param arg2
 * @returns {number}
 */
AutoSplit.prototype.addInt = function(arg1,arg2){
    return parseInt(arg1,10)+parseInt(arg2,10);
}

/**
 * jQuery表单序列化
 * @param form
 * @returns {{}}
 */
AutoSplit.prototype.serializeObject = function(form){
    var obj = {};
    $.each(form.serializeArray(), function (index) {
        obj[this['name']] = this['value'];
    });
    return obj;
}
/**
 * log打印
 * @param msg
 */
AutoSplit.prototype.log=function(msg){
    if(this._settings.debug == false){
        return;
    }
    if(window.console && window.console.log){
        window.console.log(msg);
    }
}
/**
 * 打印Object对象内容
 * @param msg
 */
AutoSplit.prototype.dir=function(msg){
    if(this._settings.debug == false){
        return;
    }
    if(window.console && window.console.dir){
        window.console.dir(msg);
    }
}

AutoSplit.prototype.returnDouble  = function(str){
    this.log("returnDouble"+str);
    return this.returnLong(str)/10000;
}

AutoSplit.prototype.returnLong = function(str){
    if(!str || str==""){
        return 0;
    }
    if(window.console && window.console.log){
        window.console.log(typeof(str));
    }
    if(typeof(str) ==   "number"){
        return str*100
    }
    var tmp  = str.split(".");
    if(tmp[0] === undefined || tmp[0] ===''){
        tmp[0] = 0;
    }
    var intValue = parseInt(tmp[0],10)*100;
    var floatValue = 0;
    if(tmp.length == 2){
        if(tmp[1] && tmp[1] != ""){
            var floatTmp = tmp[1].substr(0,2);
            if(floatTmp.length == 1){
                floatTmp += "0";
            }

            floatValue = parseInt(floatTmp,10);
        }

    }
    return intValue  + floatValue;
}

var AutoSplitUtil = {
    init:function(setting){
        this.auto = new AutoSplit(setting);
    },
    changeIsChear:function(value,obj){
        this.auto.changeIsChear(value,obj)
    },
    changeBillType:function(value,obj){
        this.auto.changeBillType(value,obj);
    },
    submit:function(){
        this.auto.submit();
    },
    autoSplit:function(){
        this.auto.autoSplit();
    },
    keyUp:function(thisObj,obj,type){
        this.auto.keyUp(thisObj,obj,type);
    }

}
