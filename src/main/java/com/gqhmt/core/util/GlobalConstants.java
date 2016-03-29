package com.gqhmt.core.util;

import com.gqhmt.sys.beans.SysAuthFunc;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.util.Pager;
import com.gqhmt.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GlobalConstants {
	
    public  static final int RESERVED_CUSTOMERID_LIMIT = 100;
    public static final  int CHECK_WITHRAW_NUM = 3;
    
	public static final String USER_HOME = "/";
	public static final String ERROR_PAGE = "/error.jsp";
	public static final String SESSION_EMP = "emp_session";
	public static final String SESSION_MENU = "session_menu";
	public static final String EXCLUDE_URL_INIT="exclude";
	public static final String EXCLUDE_DIRECTORY_REGEX="^/css/|^/images/|^/js/|^/upload/|^/img/|^/api/|^/fonts/|^/loginout.jsp";
	public static final String ROLE_SUPPER_ID=",1,";
    public static final String ROLE_BUSINESS_SUPPER_ID=",2,";
	public static final String EXCLUDE_ONLY_LOGIN="^/json/";
	public static final int PAGE_SIZE=10;
	public static final int DEL=-1;
	public static final String SYSTEM_CODE="";
	
	//账户类型
    public static final int ACCOUNT_TYPE_PRIMARY=0;         //主账户
    public static final int ACCOUNT_TYPE_LOAN=1;            //借款账户
    public static final int ACCOUNT_TYPE_LEND_OFF=2;        //线下出借账户
    public static final int ACCOUNT_TYPE_LEND_ON=3;         //线上出借账户
    public static final int ACCOUNT_TYPE_PAYMENT=96;        //应付账户
    public static final int ACCOUNT_TYPE_FREEZE=99;         //冻结金账户
    
	//所有账目状态
	public static final int NO_ACCOUNT=1;
	public static final int ACCOUNTING=2;
	public static final int ACCOUNTED=3;

    //业务类型
    public static final int BUSINESS_LEND=1;
    public static final int BUSINESS_LOAN=2;
    public static final int BUSINESS_FUND_TRANSACTIONS=3;
    public static final int BUSINESS_OTHER=99;

    //账务类型
    public static final int ACCOUNT_LEND_CAPITAL=1;
    public static final int ACCOUNT_LEND_REDEEM=2;
    public static final int ACCOUNT_LEND_INTEREST=3;
    public static final int ACCOUNT_LOAN_PAYMENT=4;
    public static final int ACCOUNT_LOAN_REPAYMENT=5;
    public static final int ACCOUNT_LOAN_REFUND=6;

    public static final int ACCOUNT_FUND_TRANSACTIONS=7;
	
	//  1-充值 2-提现 3-代偿 4-投标 5-转账 6-还款 7-流标
	
    public static final int ORDER_CHARGE = 1;
	public static final int ORDER_WITHHOLDING = 2;
	public static final int ORDER_COMPENSATORY = 3;
	public static final int ORDER_BID = 4;
	public static final int ORDER_TRANSFER = 5;
	public static final int ORDER_REPAYMENT = 6;
	public static final int ORDER_ABORT_BID = 7;
	public static final int ORDER_SETTLE = 8;
	public static final int ORDER_WITHDRAW = 9;
	public static final int ORDER_DEBT = 10;
	public static final int ORDER_BUSINESS_WITHDRAW = 11;
	public static final int ORDER_AGENT_WITHDRAW = 12;
	public static final int ORDER_CREATE_ACCOUNT = 13;
	public static final int ORDER_CREATE_PWD = 14;
	public static final int ORDER_UPDATE_PWD = 15;
	public static final int ORDER_BIND_CARD = 16;
	public static final int ORDER_UNBIND_CARD = 17;
	public static final int ORDER_QUERY_CARD = 18;
	public static final int ORDER_FUNDS_FROZEN = 19;
	public static final int ORDER_FUNDS_UNFROZEN =20;
	public static final int ORDER_BID_NOTIFY =21;
	public static final int ORDER_REPAYMENT_FORZEN = 22;
	public static final int ORDER_FUNDS_THIRD_FROZEN = 23;
	public static final int ORDER_FUNDS_THIRD_UNFROZEN = 24;
	public static final int ORDER_WITHDRAW_UNFREEZE = 25;
	public static final int ORDER_BID_UNFREEZE = 26;
	public static final int ORDER_ABORT_BID_FREEZE = 27;
	public static final int ORDER_SETTLE_UNFORZEN = 28;
	public static final int ORDER_BALANCE = 29;
	public static final int ORDER_ABORT_BID_AYSN=30;
	public static final int ORDER_BID_TRANSFER = 31;
	public static final int ORDER_BID_FAILED_RETURN = 32;
	public static final int ORDER_WITHDRAW_CHARGE_AMOUNT = 33;
	public static final int ORDER_MORTGAGE_TRANSFER = 34;
	public static final int ORDER_UPDATE_CARD = 35;
	public static final int ORDER_SET_FUIOU_MMS = 36;
	public static final int ORDER_POINT_GQ_RETURN_FEE = 37;  //冠钱返现
	public static final int ORDER_MOBILE_CHECK_CARD = 38;  //手机签约

    public static final int ORDER_DROP_USER = 39;
    public static final int ORDER_UPDATE_CARD_QUERY = 40;

    public static final int ORDER_SETTLE_NEW = 11990048;

    public static final int ORDER_REPAYMENT_NEW = 11990049;

    public static final int ORDER_ABORT_NEW = 11990050;


    public static final int ORDER_MORTGAGEE_TRANS_ACC = 1001;  //抵押权人转给借款人


    public static final int ORDER_COST = 2198;  //收费
    public static final int ORDER_COST_RETURN = 2199;  //退费



	public static final int ORDER_STATUS_SUBMIT = 1;        //新增
	public static final int ORDER_STATUS_SUCCESS = 2;      //成功
	public static final int ORDER_STATUS_FAILED = 3;      //失败
	public static final int ORDER_STATUS_TIMEOUT = 4;      // 超时
	public static final int ORDER_STATUS_VALIDSMS = 5;      //短信校验
	public static final int ORDER_STATUS_AYSN = 6;          //异步处理
	public static final int ORDER_STATUS_THIRDNULL = 7;

	public static final int ORDER_STATUS_RETURN_WITHDRAW = 21; //提现退票

	public static final int ORDER_STATUS_PART = 998;       //异常
	public static final int ORDER_STATUS_THIRDERROR = 999; //异常手动处理
	public static final int ORDER_STATUS_CLOSE = 1000;     //交易关闭
	
    //业务映射流水类型
    public static final int BUSINESS_MAPPINF_CUSTOMER=1;
    public static final int BUSINESS_MAPPINF_BID=2;
    public static final int BUSINESS_MAPPINF_TENDER=3;
    public static final int BUSINESS_MAPPINF_DEBT=4;
    public static final int BUSINESS_MAPPINF_WITHDRAWAPPLY=5;
    public static final int BUSINESS_MAPPINF_REPAYMENT=6;
    public static final int BUSINESS_MAPPINF_CARD=9;
    
    /*================================冠钱积分====================================*/

    public static final int POINT_GQ = 1;               //冠钱账户
    public static final int POINT_GQ_FREE = 1001;       //冠钱冻结

    public static final int POINT_JF = 2;               //积分
    public static final int POINT_JF_FREE = 1002;       //积分冻结

    //入账类型
    public static final int POINT_SEQ_RECHAGE = 1001;       //普通入账
    public static final int POINT_SEQ_RECHAGE_TG = 1002;       //活动赠送
    public static final int POINT_SEQ_RECHAGE_BID = 1003;       //投标赠送
    public static final int POINT_SEQ_RECHAGE_JF = 1010;  //积分转换冠钱
    //冻结解冻
    public static final int POINT_SEQ_FEEE = 2001;
    public static final int POINT_SEQ_UNFEEE = 2002;

    //消费类型

    public static final int POINT_SEQ_PAYMENT_RETUEN_FEE = 3001;  //返现
    public static final int POINT_SEQ_PAYMENT_SHOPMALL = 3002;  //商城消费
    
    //是否创建第三方账户
    public static final int CREATR_THIRD_ACCOUNT = 2;
    public static final int NO_CREATR_THIRD_ACCOUNT = 1;
    
    public static final int BUSINESS_WITHHOLDING = 2;
    public static final int BUSINESS_AMOUNT_WITHHOLD = 1;
    public static final int BUSINESS_BID = 4;
    public static final int BUSINESS_REPAYMENT_WITHHOLDING = 3;
    public static final int BUSINESS_REPAYMENT = 6;
    public static final int BUSINESS_SETTLE = 8;
    public static final int BUSINESS_WITHDRAW = 9;
    public static final int BUSINESS_DEBT = 11;
    public static final int BUSINESS_TRANSFER = 10;
    public static final int BUSINESS_ABORT_BID = 12;
    public static final int BUSINESS_UPDATE_CARE = 13;

    public static final int NEW_BUSINESS_WITHHOLDING = 2001;

    public static final int NEW_BUSINESS_MT = 2002;//抵押权人转借款人


    public static final int NEW_BUSINESS_COST = 2098;//抵押权人转借款人

    public static final int NEW_BUSINESS_COST_RETURN = 2099;//抵押权人转借款人
    
	
	public static final String ROLE_URL_MAP="roleUrlMap";
	public static final String ROLE_MAP="roleMap";
	public static final String FUNC_MAP="funcMap";
	public static final String USER_MAP="userMap";
	
	public static Map<Long,String> bankAccountMap = new LinkedHashMap<>();
    public static Map<Long,String> thirdAccountMap = new LinkedHashMap<>();
	public static Map<Long,SysAuthFunc> funcMap=new LinkedHashMap<Long,SysAuthFunc>();
	public static Map<Long,SysUsers> usersMap=new ConcurrentHashMap<Long,SysUsers>();
	public static Map<Long,String> roleMap=new ConcurrentHashMap<Long,String>();
    public static List<SysAuthFunc> allMenu = new LinkedList<>();


    public static Map<Integer,Integer> iconMap=new ConcurrentHashMap<Integer,Integer>();
	public static Map<String,String> codeMap=new ConcurrentHashMap<String,String>();
	public static Map<Integer,String> stateMap=new LinkedHashMap<Integer,String>();
	public static Map<String,String> loginErrorMap=new ConcurrentHashMap<String,String>();
	public static Map<Integer,String> clearNameMap=new ConcurrentHashMap<Integer,String>();
	public static Map<Integer,String> billTypeNameMap=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> matchTypeNameMap=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> empStatusMap=new LinkedHashMap<Integer,String>();
    public static Map<Integer,String> empSexMap=new LinkedHashMap<Integer,String>();
    public static Map<Integer,String> roleFeatureMap=new LinkedHashMap<Integer,String>();
    public static Map<Integer,Integer> pageMap=new LinkedHashMap<Integer,Integer>();
    public static Map<Integer,String> accountMap=new LinkedHashMap<Integer,String>();
    public static Map<Integer,String> bankMap=new LinkedHashMap<Integer,String>();      //银行
    public static Map<Integer,String> thirdMap=new LinkedHashMap<Integer,String>();     //第三方账户类型
    public static Map<Integer,String> fundMap=new LinkedHashMap<Integer,String>();      //资金类型
    public static Map<Integer,String> financeMap=new LinkedHashMap<Integer,String>();      //财务类型
    public static Map<Integer,String> collectingMap=new LinkedHashMap<Integer,String>();      //归集类型
    public static Map<String,String> actionMap=new LinkedHashMap<String,String>();      //归集类型

    public static Map<String,String> AccountMap=new LinkedHashMap<String,String>();
    public static Map<String,String> AccountLoanRepaymentMap=new LinkedHashMap<String,String>();

    public static Map<Integer,String> accountType=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> accountRole=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> acctionType=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> fundsType=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> thirdpartyType=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> thirdpartyTypeEN=new ConcurrentHashMap<Integer,String>();
    public static Map<Integer,String> pointType=new ConcurrentHashMap<Integer,String>();



    //开户账户账号规则匹配(前四位,随意生成,无任何含义,未来改为通过配置表生成)
    public static Map<String,String> ACCOUNT_TYPE_MAPPING = new ConcurrentHashMap<>();

    //开户交易类型,与账户类型匹配规则
    public static Map<String,String> TRADE_ACCOUNT_TYPE_MAPPING = new ConcurrentHashMap<>();

    //开户交易类型,与交易渠道映射
    public static Map<String,String> TRADE_ACCOUNT_PAY_CHANNEL_MAPPING = new ConcurrentHashMap<>();

    public static Map<String,String> TRADE_APPLY_NO__MAPPING = new ConcurrentHashMap<>();
    
    //业务类型与交易类型匹配
    public static Map<Integer,Integer> TRADE_BUSINESS_TYPE__MAPPING = new ConcurrentHashMap<>();
    
    private static long getMenuId(Long fid) {
        if (GlobalConstants.funcMap.get(fid).getIsMenu() == 1)
            return fid;
        return getMenuId(GlobalConstants.funcMap.get(fid).getParentId());

    }

    public static long getMenuParentId(String url) {
        if (StringUtils.isEmpty(url))
            return 0;
        for (SysAuthFunc func1 : GlobalConstants.funcMap.values()) {
            if (url.equalsIgnoreCase(func1.getFuncUrl())) {
                if (func1.getIsMenu() == 1)
                    return func1.getFuncId();
                return getMenuId(func1.getParentId());
            }
        }
        return 0;
    }

	
	public static Object getSession(HttpServletRequest request,String name){
		return request.getSession(true).getAttribute(name);
	}
	
	public static void setSession(HttpServletRequest request,String name,Object value){
		request.getSession(true).setAttribute(name,value);
	}
	
	public static void addSearchCondition(Pager page, Object object, String[] fields){
		Class<? extends Object> targetClass=object.getClass();
		Method meth=null;
		for(String f:fields){
			try {
				meth= targetClass.getDeclaredMethod("get"+f.substring(0, 1).toUpperCase() + f.substring(1));
				page.addCondition(f,meth.invoke(object)+"");
			} catch (Exception e) {}
		}
	}
	static{
		empStatusMap.put(1,"正式员工");
		empStatusMap.put(2,"试用期间");

		empSexMap.put(1,"男");
		empSexMap.put(2,"女");
		
		roleFeatureMap.put(1,"超管");
		roleFeatureMap.put(2,"用户");
		roleFeatureMap.put(3,"测试");


		pageMap.put(10,	10);
		pageMap.put(15, 15);
		pageMap.put(20, 20);
		pageMap.put(25, 25);
		pageMap.put(30, 30);
		
		iconMap.put(2,2);
		iconMap.put(6,3);
		iconMap.put(10,4);

        codeMap.put("0000","文件上传成功");
		codeMap.put("0001","没有文件");
		codeMap.put("0002","上传文件失败");
		codeMap.put("0003","上传文件成功");
		codeMap.put("0004","文件格式不正确");
		codeMap.put("0005","文件夹不存在");
		codeMap.put("0006","写文件失败");
        codeMap.put("0007","数据库操作失败");
		
		stateMap.put(1,"未入账");
		stateMap.put(2,"待入账");
		stateMap.put(3,"已入账");
		
		loginErrorMap.put("0001","验证码不正确");
		loginErrorMap.put("0002","没有角色权限");
		loginErrorMap.put("0003","用户名或密码不正确");
		loginErrorMap.put("0004","该用户已经被禁用");
		loginErrorMap.put("0005","该用户角色权限不够");
		//状态，1未到期，2部分结清，3已结清4，逾期结清，5逾期6，提前结清，99其他
		clearNameMap.put(1,"未到期");
        clearNameMap.put(2,"逾期");
        clearNameMap.put(3,"部分结清");
        clearNameMap.put(4,"已结清");
        clearNameMap.put(5,"逾期结清");
        clearNameMap.put(6,"提前结清");
        clearNameMap.put(99,"其他");

		
//		billTypeNameMap.put(0,"不限");
		billTypeNameMap.put(1,"正常");
		billTypeNameMap.put(2,"更改");


        matchTypeNameMap.put(0,"未拆分");
        matchTypeNameMap.put(1,"已拆分");
        matchTypeNameMap.put(2,"已退回");
        matchTypeNameMap.put(3,"已更改");


        //账号状态1正常、2停用，3注销
        accountMap.put(1,"正常");
        accountMap.put(2,"停用");
        accountMap.put(3,"注销");


        //银行机构
        bankMap.put(1,"中国工商银行");
        bankMap.put(2,"中国建设银行");
        bankMap.put(3,"中国农业银行");


        thirdMap.put(1,"银联收付易");


        //资金类型
        fundMap.put(1,"出借");
        fundMap.put(2,"借款");
        fundMap.put(3,"资金往来");

        //财务类型
        financeMap.put(1,"代收");
        financeMap.put(2,"代扣");
        financeMap.put(3,"充值");
        financeMap.put(4,"提现");



        //归集类型
        collectingMap.put(1,"未归集");
        collectingMap.put(2,"已归集");

        //

        actionMap.put("0000","成功");
        actionMap.put("0001","失败");
        actionMap.put("0002","数据库操作失败");
        actionMap.put("0003","匹账错误，查账只能匹配一条流水记录");
        actionMap.put("0004","匹账错误，流水匹配金额不能小于合同未匹配余额");
        actionMap.put("0005","匹账错误，流水匹配金额与合同未匹配金额不等");
        actionMap.put("0008","合同不存在");
        actionMap.put("0009","添加失败，合同编号已经存在！");
        actionMap.put("9998","参数错误");
        actionMap.put("9999","未知错误");


        //账务明细类型

        AccountMap.put("10001001","本金");
        AccountMap.put("10001002","利息");
        AccountMap.put("10001003","服务费");
        AccountMap.put("10001004","提前赎回服务费");
        AccountMap.put("10002001","贷款总额");
        AccountMap.put("10002001","保证金");
        AccountMap.put("10002101","本金");
        AccountMap.put("10002102","利息");
        AccountMap.put("10002103","服务费");
        AccountMap.put("10002104","滞纳金");
        AccountMap.put("10002105","罚息");
        AccountMap.put("10002106","提前还款违约金");
        AccountMap.put("10002107","营业外收入");
        AccountMap.put("10002108","减免");
        AccountMap.put("10002109","上门费");
        AccountMap.put("10002110","展期费");
        AccountMap.put("10002111","挂账");
        AccountMap.put("10002201","退款");


        //借款回款
        AccountLoanRepaymentMap.put("10002101","本金");
        AccountLoanRepaymentMap.put("10002102","利息");
        AccountLoanRepaymentMap.put("10002103","服务费");
        AccountLoanRepaymentMap.put("10002104","滞纳金");
        AccountLoanRepaymentMap.put("10002105","罚息");
        AccountLoanRepaymentMap.put("10002106","提前还款违约金");
        AccountLoanRepaymentMap.put("10002107","营业外收入");
        AccountLoanRepaymentMap.put("10002108","减免");
        AccountLoanRepaymentMap.put("10002109","上门费");
        AccountLoanRepaymentMap.put("10002110","展期费");
        AccountLoanRepaymentMap.put("10002111","挂账");
        AccountLoanRepaymentMap.put("10002201","退款");
        
        accountType.put(0,"主账户");
        accountType.put(1,"借款账户");
        accountType.put(2,"线下出借账户");
        accountType.put(3,"线上出借账户");
        accountType.put(96,"应付账户");
        accountType.put(99,"冻结金账户");

        accountRole.put(1,"DEBTOR");
        accountRole.put(2,"INVESTOR");
        accountRole.put(3,"INVESTOR");

        acctionType.put(1,"充值");
        acctionType.put(2,"提现");
        acctionType.put(3,"转账");
        acctionType.put(4,"冻结");
        acctionType.put(5,"解冻");
        acctionType.put(6,"投标成功");
        acctionType.put(7,"还款");
        acctionType.put(8,"债权转让");


        /*
         *  #1开头，资金交易类型
         1001:充值
         1002:代扣
         1003:预提现（转到冻结金账户）
         1004:提现失败（冻结金账户转回）
         1005:一般转账转出
         1006:一般转账转入
         1007:资金冻结（转到冻结金账户）
         1008:资金解冻（冻结金账户转回）
         */
        fundsType.put(1001,"充值");
        fundsType.put(1002,"代扣");
        fundsType.put(1003,"提现");
        fundsType.put(1004,"提现退回");
        fundsType.put(1005,"一般转账:转出");
        fundsType.put(1006,"一般转账:转入");
        fundsType.put(1007,"资金冻结");
        fundsType.put(1008,"资金解冻");
        fundsType.put(1009,"资金收入");
        fundsType.put(1010, "债权转让手续费");
        fundsType.put(1011, "账务修正");
        fundsType.put(1012,"实时提现");

        /*

        #2开头，冻结资金类型
        2001:提现冻结
        2002:提现解冻
        2003:投标冻结
        2004:投标解冻
        2005：一般冻结
        2006：一般冻结解冻
*/
        fundsType.put(2001,"提现冻结");
        fundsType.put(2002,"提现退回解冻");
        fundsType.put(2003,"提现出账（提现转账到银行卡成功）");
        fundsType.put(2004,"投标冻结");
        fundsType.put(2005,"流标解冻");
        fundsType.put(2006,"投标成功转账");
        fundsType.put(2007,"一般冻结");
        fundsType.put(2008,"一般冻结解冻");
        fundsType.put(2009,"应付金转出");
        fundsType.put(2010,"应付金转入");
        /*
        #3开头投标交易类型
        3001:投标
        3002:投标清算
        3003:还款本金（借款出）
        3004:还款利息（借款出）
        3005:收款本金（出借入）
        3006:收款利息（出借入）
        3007:债权转让转出
        3008:债权转让转入
        3009:流标退款
         */
        fundsType.put(3001,"投标");
        fundsType.put(3002,"投标清算成功转入");
        fundsType.put(3003,"还款本金（借款出）");
        fundsType.put(3004,"还款利息（借款出）");
        fundsType.put(3005,"收款本金（出借入）");
        fundsType.put(3006,"收款利息（出借入）");
        fundsType.put(3007,"购买债权");
        fundsType.put(3008,"转让债权");
        fundsType.put(3009,"投标异常");
        fundsType.put(3010,"债权退回");
        fundsType.put(3011,"流标");

        fundsType.put(4001,"收取账户管理费");
        fundsType.put(4002,"账户管理费");
        fundsType.put(4003,"收取保证金");
        fundsType.put(4004,"收取保证金");
        fundsType.put(4005,"资金补偿");
        fundsType.put(4006,"服务费");
        fundsType.put(4007,"归还保证金");
        fundsType.put(4008,"抵押标借款人转账");
        fundsType.put(4010,"提现手续费");
        fundsType.put(4011,"抵押标借款人扣除账户管理费");
        fundsType.put(4012,"风险备用金");
        fundsType.put(4013,"归还风险备用金");

        thirdpartyType.put(1,"大钱");
        thirdpartyType.put(2,"富友");

        thirdpartyTypeEN.put(1,"daqian_");
        thirdpartyTypeEN.put(2,"");


        pointType.put(1001,"推荐奖励冠钱");
        pointType.put(1001,"活动赠送冠钱");
        pointType.put(1003,"投标赠送冠钱");
        pointType.put(1010,"积分转冠钱");
        pointType.put(2001,"投标返现冻结");
        pointType.put(2002,"解冻结");
        pointType.put(3001,"满标返现出账");



        ACCOUNT_TYPE_MAPPING.put("10010001","1306");            //互联网账户
        ACCOUNT_TYPE_MAPPING.put("10010002","1308");            //委托出借账户
        ACCOUNT_TYPE_MAPPING.put("10010003","1302");            //借款账户
        ACCOUNT_TYPE_MAPPING.put("10010004","1304");            //保理业务账户

        ACCOUNT_TYPE_MAPPING.put("10010005","2481");            //借款账户（冠e通）

        /*//借款系统开户
        ACCOUNT_TYPE_MAPPING.put("11020010","2346");//借款人开户
        ACCOUNT_TYPE_MAPPING.put("11020009","5231");//纯线下借款账户*/

        ACCOUNT_TYPE_MAPPING.put("10011000","9180");            //公司收费账户
        ACCOUNT_TYPE_MAPPING.put("10011001","8246");            //保证金账户
        ACCOUNT_TYPE_MAPPING.put("10011002","8248");            //逆服务费账户
        ACCOUNT_TYPE_MAPPING.put("10012001","6601");            //代偿人账户
        ACCOUNT_TYPE_MAPPING.put("10012002","6635");            //抵押权人账户
        ACCOUNT_TYPE_MAPPING.put("10012003","6663");            //借款代还账户


        //线上开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020001","10010001");//web开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020002","10010001");//wap开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020003","10010001");//app开户
        //TRADE_ACCOUNT_TYPE_MAPPING.put("11020003","10010001");//ios开户
        //TRADE_ACCOUNT_TYPE_MAPPING.put("11020003","10010001");//andriod开户
        //TRADE_ACCOUNT_TYPE_MAPPING.put("11020003","10010001");//微信开户

        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020001","97010001");
        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020002","97010001");
        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020003","97010001");
//        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020001","97010001");
//        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020002","97010001");
//        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020003","97010001");

        //冠e通
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020004","10010002");//委托出借开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020005","10010005");//借款账户（冠e通）

        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020004","97010001");
        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020005","97010001");


        //保理
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020008","10010004");//保理账户

        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020008","97010001");


        //借款系统开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020010","10010003");//借款人开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020009","10019002");//纯线下借款账户

        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020010","97010001");
        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020009","97010000");//纯线下


        //中间人账户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020006","10012001");//代偿人账户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020007","10012002");//抵押权人开户
        TRADE_ACCOUNT_TYPE_MAPPING.put("11020011","10012003");//借款代还账户


        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020006","97010001");
        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020007","97010001");
        TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.put("11020011","97010001");


        TRADE_APPLY_NO__MAPPING.put("11030004","CJKK");
        TRADE_APPLY_NO__MAPPING.put("11030005","GTHK");
        TRADE_APPLY_NO__MAPPING.put("11030006","GTDY");
        TRADE_APPLY_NO__MAPPING.put("11030007","DCKK");
        TRADE_APPLY_NO__MAPPING.put("11093001","JKHK");
        TRADE_APPLY_NO__MAPPING.put("11090001","DYKK");


        TRADE_APPLY_NO__MAPPING.put("11091001","JKTX");
        TRADE_APPLY_NO__MAPPING.put("11040005","GTFK");
        TRADE_APPLY_NO__MAPPING.put("11040006","DYTX");
        TRADE_APPLY_NO__MAPPING.put("11040007","DCTX");
        TRADE_APPLY_NO__MAPPING.put("11040004","CJSH");
        
        TRADE_BUSINESS_TYPE__MAPPING.put(11030004,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11030005,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11030006,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11030007,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11093001,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11090001,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11091001,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11040005,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11040006,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11040007,1);//借款 1
        TRADE_BUSINESS_TYPE__MAPPING.put(11040004,2);//出借 2


	}
}
