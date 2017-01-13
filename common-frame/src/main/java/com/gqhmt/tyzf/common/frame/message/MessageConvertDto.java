package com.gqhmt.tyzf.common.frame.message;

import com.gqhmt.tyzf.common.frame.annotation.DFormat;
import com.gqhmt.tyzf.common.frame.annotation.OXMapper;
import com.gqhmt.tyzf.common.frame.annotation.OXMapping;
import com.gqhmt.tyzf.common.frame.annotation.XOMapping;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhou on 2016/11/16.
 * Description: 报文消息与实体互转对象
 */
public class MessageConvertDto {

    /** 报文头组 **/
    @OXMapping(xpath = "requestHeader/req_sys_id")
    @XOMapping(xpath = "req_sys_id", preNode = MsgConstants.REQ_HEADER_TAG)
    private String reqSysId;

    @OXMapping(xpath = "requestHeader/serv_sys_id")
    @XOMapping(xpath = "serv_sys_id", preNode = MsgConstants.REQ_HEADER_TAG)
    private String servSysId;

    @OXMapping(xpath = "requestHeader/service_id")
    @XOMapping(xpath = "service_id", preNode = MsgConstants.REQ_HEADER_TAG)
    private String serviceId;

    @OXMapping(xpath = "requestHeader/receive_time")
    @XOMapping(xpath = "receive_time", preNode = MsgConstants.REQ_HEADER_TAG)
    private String receiveTime;//接收时间

    @OXMapping(xpath = "requestHeader/is_actual")
    @XOMapping(xpath = "is_actual", preNode = MsgConstants.REQ_HEADER_TAG)
    private String isActual;//是否同步交易 Y是 N不是

    @OXMapping(xpath = "requestHeader/is_batchd")
    @XOMapping(xpath = "is_batch", preNode = MsgConstants.REQ_HEADER_TAG)
    private String isBatch;//是否批量 Y是 N不是

    /** 报文体组 **/
    @OXMapping(xpath = "requestMsg/PmtID/OrderId")
    @XOMapping(xpath = "PmtID/OrderId", preNode = MsgConstants.REQ_BODY_TAG)
    private String orderId;//业务系统单号

    @OXMapping(xpath = "requestMsg/PmtID/MsgID")
    @XOMapping(xpath = "PmtID/MsgID", preNode = MsgConstants.REQ_BODY_TAG)
    private String msgId;//平台报文唯一标识

    @OXMapping(xpath = "requestMsg/PmtID/FrmtVrsn")
    @XOMapping(xpath = "PmtID/FrmtVrsn", preNode = MsgConstants.REQ_BODY_TAG)
    private String fmtVersion;//报文版本号

    @OXMapping(xpath = "requestMsg/PmtID/ExTxnId")
    @XOMapping(xpath = "PmtID/ExTxnId", preNode = MsgConstants.REQ_BODY_TAG)
    private String exTxnId;//第三方支付通道交易单号

    @OXMapping(xpath = "requestMsg/PmtID/SplitFlg1")
    @XOMapping(xpath = "PmtID/SplitFlg1", preNode = MsgConstants.REQ_BODY_TAG)
    private String splitFlg1;//交易拆分标识1（01=交易可拆分，02=交易不可拆分）

    @OXMapping(xpath = "requestMsg/PmtID/SplitFlg2")
    @XOMapping(xpath = "PmtID/SplitFlg2", preNode = MsgConstants.REQ_BODY_TAG)
    private String splitFlg2;//交易拆分标识2（01=已拆分主交易，02=已拆分子交易）

    @OXMapping(xpath = "requestMsg/PmtID/TxnTp")
    @XOMapping(xpath = "PmtID/TxnTp", preNode = MsgConstants.REQ_BODY_TAG)
    private String txnType;//交易类型（充值、提现、转账、鉴权、查询、获取短信验证码、快捷支付签约、快捷支付解约）

    @OXMapping(xpath = "requestMsg/PmtID/TxnSubTp")
    @XOMapping(xpath = "PmtID/TxnSubTp", preNode = MsgConstants.REQ_BODY_TAG)
    private String txnSubType;//交易子类型（垫资）

    @OXMapping(xpath = "requestMsg/PmtID/TxnSubTp")
    @XOMapping(xpath = "PmtID/PmtTp", preNode = MsgConstants.REQ_BODY_TAG)
    private String pmtTp;//支付类型（网关支付/快捷支付/单笔代扣/批量代扣/单笔代付/批量代付）

    @OXMapping(xpath = "requestMsg/PmtID/BizTp")
    @XOMapping(xpath = "PmtID/BizTp", preNode = MsgConstants.REQ_BODY_TAG)               //业务类型（投资、放款、还款、回款）
    private String bizTp;//业务类型（投资、放款、还款、回款）

    @OXMapping(xpath = "requestMsg/PmtID/ProcMd")
    @XOMapping(xpath = "PmtID/ProcMd", preNode = MsgConstants.REQ_BODY_TAG)             //同异步处理标识
    private String procMode;

    @OXMapping(xpath = "requestMsg/PmtID/MsgSts")
    @XOMapping(xpath = "PmtID/MsgSts", preNode = MsgConstants.REQ_BODY_TAG)               //报文状态
    private String msgSts;

    @OXMapping(xpath = "requestMsg/PmtID/ErrDesp")
    @XOMapping(xpath = "PmtID/ErrDesp", preNode = MsgConstants.REQ_BODY_TAG)            //错误描叙
    private String errorDesp;

    @OXMapping(xpath = "requestMsg/PmtID/RespCode")
    @XOMapping(xpath = "PmtID/RespCode", preNode = MsgConstants.REQ_BODY_TAG)             //响应吗
    private String respCode;

    @OXMapping(xpath = "requestMsg/PmtID/RespCdDesp")
    @XOMapping(xpath = "PmtID/RespCdDesp", preNode = MsgConstants.REQ_BODY_TAG)               //响应描叙
    private String resMsg;

    @OXMapping(xpath = "requestMsg/PmtID/ReasonFlg")
    @XOMapping(xpath = "PmtID/ReasonFlg", preNode = MsgConstants.REQ_BODY_TAG)            //是否为客户原因
    private String reasonFlg;

    @OXMapping(xpath = "requestMsg/PmtID/ChkSts")
    @XOMapping(xpath = "PmtID/ChkSts", preNode = MsgConstants.REQ_BODY_TAG)               //第三方复核状态
    private String chkSts;

    @OXMapping(xpath = "requestMsg/PmtID/SignInfo")
    @XOMapping(xpath = "PmtID/SignInfo", preNode = MsgConstants.REQ_BODY_TAG)             //数字签名
    private String signInfo;

    @OXMapping(xpath = "requestMsg/PmtID/RevFlg")
    @XOMapping(xpath = "PmtID/RevFlg", preNode = MsgConstants.REQ_BODY_TAG)           //预约标识
    private String reserveFlg;

    @OXMapping(xpath = "requestMsg/PmtID/RevId")
    @XOMapping(xpath = "PmtID/RevId", preNode = MsgConstants.REQ_BODY_TAG)            //预约人
    private String reserveId;

    @OXMapping(xpath = "requestMsg/PmtID/RevDt")
    @DFormat(format="yyyy-MM-dd HH:mm:ss")
    @XOMapping(xpath = "PmtID/RevDt", preNode = MsgConstants.REQ_BODY_TAG)            //预约时间
    private Date reserveTm;

    @OXMapping(xpath = "requestMsg/PmtID/CustID")
    @XOMapping(xpath = "PmtID/CustID", preNode = MsgConstants.REQ_BODY_TAG)               //客户号
    private String custId;

    @OXMapping(xpath = "requestMsg/PmtID/CardTp")
    @XOMapping(xpath = "PmtID/CardTp", preNode = MsgConstants.REQ_BODY_TAG)               //借贷分离标识
    private String cardTp;

    @OXMapping(xpath = "requestMsg/PmtID/BatchIdIn")
    @XOMapping(xpath = "PmtID/BatchIdIn", preNode = MsgConstants.REQ_BODY_TAG)           //业务系统批次号(来报批次号)
    private String batchIdIn;

    @OXMapping(xpath = "requestMsg/PmtID/BatchIdOut")
    @XOMapping(xpath = "PmtID/BatchIdOut", preNode = MsgConstants.REQ_BODY_TAG)           //通道批次号(往报批次号)
    private String batchIdOut;

    @OXMapping(xpath = "requestMsg/PmtID/ChnReUrl")
    @XOMapping(xpath = "PmtID/ChnReUrl", preNode = MsgConstants.REQ_BODY_TAG)             //业务端return url
    private String returnUrl;

    @OXMapping(xpath = "requestMsg/PmtID/ChnNotiUrl")
    @XOMapping(xpath = "PmtID/ChnNotiUrl", preNode = MsgConstants.REQ_BODY_TAG)            //业务nodify url
    private String notifyUrl;

    @OXMapping(xpath = "requestMsg/PmtID/ExReUrl")
    @XOMapping(xpath = "PmtID/ExReUrl", preNode = MsgConstants.REQ_BODY_TAG)         //通道端return url
    private String exReturnUrl;

    @OXMapping(xpath = "requestMsg/PmtID/ExNotiUrl")
    @XOMapping(xpath = "PmtID/ExNotiUrl", preNode = MsgConstants.REQ_BODY_TAG)         //通道端nodify url
    private String exNotifyUrl;

    @OXMapping(xpath = "requestMsg/PmtID/CapTm")
    @DFormat(format="yyyy-MM-dd HH:mm:ss")
    @XOMapping(xpath = "PmtID/CapTm", preNode = MsgConstants.REQ_BODY_TAG)          //接收时间
    private Date captureTime;

    @OXMapping(xpath = "requestMsg/PmtID/TraceTm")
    @DFormat(format="yyyy-MM-dd HH:mm:ss")
    @XOMapping(xpath = "PmtID/TraceTm", preNode = MsgConstants.REQ_BODY_TAG)            //回盘时间
    private Date traceTime;

    @OXMapping(xpath = "requestMsg/PmtID/ExeTm")
    @DFormat(format="yyyy-MM-dd HH:mm:ss")
    @XOMapping(xpath = "PmtID/ExeTm", preNode = MsgConstants.REQ_BODY_TAG)              //执行时间(发送时间)
    private Date exeTime;

    @OXMapping(xpath = "requestMsg/PmtID/ExSttlDt")
    @DFormat(format="yyyy-MM-dd")
    @XOMapping(xpath = "PmtID/ExSttlDt", preNode = MsgConstants.REQ_BODY_TAG)            //通道清算日期
    private Date exSttlDt;

    @OXMapping(xpath = "requestMsg/PmtID/NsSttlDt")
    @DFormat(format="yyyy-MM-dd")
    @XOMapping(xpath = "PmtID/NsSttlDt", preNode = MsgConstants.REQ_BODY_TAG)            //平台清算日期
    private Date nsSttlDt;

    @OXMapping(xpath = "requestMsg/PmtID/OperateType")
    @XOMapping(xpath = "PmtID/OperateType", preNode = MsgConstants.REQ_BODY_TAG)          //操作类型
    private String operateType;

    @OXMapping(xpath = "requestMsg/PmtID/OutTxnId")
    @XOMapping(xpath = "PmtID/OutTxnId", preNode = MsgConstants.REQ_BODY_TAG)            //发给第三方的点单号(通道发送第三方交易ID)
    private String outTxnId;

    @OXMapping(xpath = "requestMsg/PmtID/PstgStatus")
    @XOMapping(xpath = "PmtID/PstgStatus", preNode = MsgConstants.REQ_BODY_TAG)           //入账状态
    private String postingSts;

    @OXMapping(xpath = "requestMsg/PmtID/Reamrk1")
    @XOMapping(xpath = "PmtID/Reamrk1", preNode = MsgConstants.REQ_BODY_TAG)           	//冗余字段1
    private String remark1;

    @OXMapping(xpath = "requestMsg/PmtID/Reamrk2")
    @XOMapping(xpath = "PmtID/Reamrk2", preNode = MsgConstants.REQ_BODY_TAG)           	//冗余字段2
    private String remark2;

    @OXMapping(xpath = "requestMsg/PmtID/Extend1")
    @XOMapping(xpath = "PmtID/Extend1", preNode = MsgConstants.REQ_BODY_TAG)             //扩展字段1
    private String extend1;

    @OXMapping(xpath = "requestMsg/PmtID/Extend2")
    @XOMapping(xpath = "PmtID/Extend2", preNode = MsgConstants.REQ_BODY_TAG)             //扩展字段1
    private String extend2;

    @OXMapping(xpath = "requestMsg/OrgnlMsgInfo/OrgnlOrderId")
    @XOMapping(xpath = "OrgnlMsgInfo/OrgnlOrderId", preNode = MsgConstants.REQ_BODY_TAG)          //原报文业务单号
    private String orglOrderid;

    @OXMapping(xpath = "requestMsg/OrgnlMsgInfo/OrgnlMsgId")
    @XOMapping(xpath = "OrgnlMsgInfo/OrgnlMsgId", preNode = MsgConstants.REQ_BODY_TAG)              //原报文唯一标识
    private String orglMid;

    @OXMapping(xpath = "requestMsg/OrgnlMsgInfo/OrgnlTxnTp")
    @XOMapping(xpath = "OrgnlMsgInfo/OrgnlTxnTp", preNode = MsgConstants.REQ_BODY_TAG)            //原报文交易类型
    private String orglTxntp;

    @OXMapping(xpath = "requestMsg/OrgnlMsgInfo/OrgnlSttlmAmt")
    @XOMapping(xpath = "OrgnlMsgInfo/OrgnlSttlmAmt", preNode = MsgConstants.REQ_BODY_TAG)         //原交易金额
    private BigDecimal orglSttlAmt;

    @OXMapping(xpath = "requestMsg/OrgnlMsgInfo/OrgnlSttlmCcy")
    @XOMapping(xpath = "OrgnlMsgInfo/OrgnlSttlmCcy", preNode = MsgConstants.REQ_BODY_TAG)     //原报文交易币种
    private String orglSttlAmtCcy;

    @OXMapping(xpath = "requestMsg/Chnl/ChnlID")
    @XOMapping(xpath = "Chnl/ChnlID", preNode = MsgConstants.REQ_BODY_TAG)               //渠道编号(WEB交易，POS交易还是APP交易等)
    private String chanId;

    @OXMapping(xpath = "requestMsg/Chnl/OprtrID")
    @XOMapping(xpath = "Chnl/OprtrID", preNode = MsgConstants.REQ_BODY_TAG)               //报文处理操作员ID
    private String operId;

    @OXMapping(xpath = "requestMsg/Chnl/APPId")
    @XOMapping(xpath = "Chnl/APPId", preNode = MsgConstants.REQ_BODY_TAG)            //应用系统ID
    private String appSysId;

    @OXMapping(xpath = "requestMsg/Chnl/ProdInfo/MerchID")
    @XOMapping(xpath = "Chnl/ProdInfo/MerchID", preNode = MsgConstants.REQ_BODY_TAG)               //商户编号
    private String merchId;

    @OXMapping(xpath = "requestMsg/Chnl/ProdInfo/ProdID")
    @XOMapping(xpath = "Chnl/ProdInfo/ProdID", preNode = MsgConstants.REQ_BODY_TAG)           //业务产品编号
    private String bizProdId;

    @OXMapping(xpath = "requestMsg/Chnl/ProdInfo/PrdInfo")
    @XOMapping(xpath = "Chnl/ProdInfo/PrdInfo", preNode = MsgConstants.REQ_BODY_TAG)         //业务产品描述
    private String bizProdInfo;

    @OXMapping(xpath = "requestMsg/Chnl/ProdInfo/PrdQty")
    @XOMapping(xpath = "Chnl/ProdInfo/PrdQty", preNode = MsgConstants.REQ_BODY_TAG)          //业务产品数量
    private Integer bizProdQty;

    @OXMapping(xpath = "requestMsg/Chnl/FastPayInfo/Cvv")
    @XOMapping(xpath = "Chnl/FastPayInfo/Cvv", preNode = MsgConstants.REQ_BODY_TAG)              //银行卡cvv信息
    private String cvvInfo;

    @OXMapping(xpath = "requestMsg/Chnl/FastPayInfo/SmsCd")
    @XOMapping(xpath = "Chnl/FastPayInfo/SmsCd", preNode = MsgConstants.REQ_BODY_TAG)              //短信验证码
    private String smsCode;

    @OXMapping(xpath = "requestMsg/Chnl/FastPayInfo/ValidDt")
    @XOMapping(xpath = "Chnl/FastPayInfo/ValidDt", preNode = MsgConstants.REQ_BODY_TAG)              //卡有效期
    private String valDate;

    @OXMapping(xpath = "requestMsg/Chnl/FastPayInfo/TokenCd")
    @XOMapping(xpath = "Chnl/FastPayInfo/TokenCd", preNode = MsgConstants.REQ_BODY_TAG)            //token信息
    private String tokenInfo;

    @OXMapping(xpath = "requestMsg/Chnl/FastPayInfo/PayFlg")
    @XOMapping(xpath = "Chnl/FastPayInfo/PayFlg", preNode = MsgConstants.REQ_BODY_TAG)               //是否为首次支付标识符
    private String payFlg;

    @OXMapping(xpath = "requestMsg/Chnl/FastPayInfo/AuthId")
    @XOMapping(xpath = "Chnl/FastPayInfo/AuthId", preNode = MsgConstants.REQ_BODY_TAG)            //授权码
    private String authId;

    @OXMapping(xpath = "requestMsg/TxnAmtInfo/IntrBkSttlmAmt")
    @XOMapping(xpath = "TxnAmtInfo/IntrBkSttlmAmt", preNode = MsgConstants.REQ_BODY_TAG)              //交易金额
    private BigDecimal sttlAmt;

    @OXMapping(xpath = "requestMsg/TxnAmtInfo/IntrBkSttlmCcy")
    @XOMapping(xpath = "TxnAmtInfo/IntrBkSttlmCcy", preNode = MsgConstants.REQ_BODY_TAG)         //交易币种
    private String sttlAmtCcy;

    @OXMapping(xpath = "requestMsg/TxnAmtInfo/ActSttlmAmt")
    @XOMapping(xpath = "TxnAmtInfo/ActSttlmAmt", preNode = MsgConstants.REQ_BODY_TAG)       //成功交易金额
    private BigDecimal actualSttlAmt;

    @OXMapping(xpath = "requestMsg/TxnAmtInfo/ActSttlmCcyp")
    @XOMapping(xpath = "TxnAmtInfo/ActSttlmCcy", preNode = MsgConstants.REQ_BODY_TAG)   	//成功交易币种
    private String actualSttlCcy;

    @OXMapping(xpath = "requestMsg/TxnAmtInfo/RtrdIntrBkSttlmAmt")
    @XOMapping(xpath = "TxnAmtInfo/RtrdIntrBkSttlmAmt", preNode = MsgConstants.REQ_BODY_TAG)            //被返回交易金额
    private BigDecimal rSttlAmt;

    @OXMapping(xpath = "requestMsg/TxnAmtInfo/RtrdIntrBkSttlmCcy")
    @XOMapping(xpath = "TxnAmtInfo/RtrdIntrBkSttlmCcy", preNode = MsgConstants.REQ_BODY_TAG)        	//被返回加以币种
    private String rSttlCcy;

    @OXMapping(xpath = "requestMsg/RoutInfo/AssCdtrIdp")
    @XOMapping(xpath = "RoutInfo/AssCdtrId", preNode = MsgConstants.REQ_BODY_TAG)          //前端指定的贷方支付路由ID
    private String asgnCdtMop;

    @OXMapping(xpath = "requestMsg/RoutInfo/CdtrId")
    @XOMapping(xpath = "RoutInfo/CdtrId", preNode = MsgConstants.REQ_BODY_TAG)               //最终支付路由ID
    private String cdtMop;

    @OXMapping(xpath = "requestMsg/RoutInfo/CdtrNm")
    @XOMapping(xpath = "RoutInfo/CdtrNm", preNode = MsgConstants.REQ_BODY_TAG)          //前端指定的贷方支付路由名称
    private String cdtMopName;

    @OXMapping(xpath = "requestMsg/RoutInfo/KeyPath")
    @XOMapping(xpath = "RoutInfo/KeyPath", preNode = MsgConstants.REQ_BODY_TAG)              //密钥文件保存的地址路径
    private String keyPath;

    @OXMapping(xpath = "requestMsg/RoutInfo/IsChange")
    @XOMapping(xpath = "RoutInfo/IsChange", preNode = MsgConstants.REQ_BODY_TAG)             //指定路由是否可变
    private String isChange;

    @OXMapping(xpath = "requestMsg/RoutInfo/ExMerchId")
    @XOMapping(xpath = "RoutInfo/ExMerchId", preNode = MsgConstants.REQ_BODY_TAG)           //通道商户号
    private String exMerchId;

    @OXMapping(xpath = "requestMsg/Dbtr/Nm")
    @XOMapping(xpath = "Dbtr/Nm", preNode = MsgConstants.REQ_BODY_TAG)               //借方姓名
    private String dbtrNm;

    @OXMapping(xpath = "requestMsg/Dbtr/Por")
    @XOMapping(xpath = "Dbtr/PorO", preNode = MsgConstants.REQ_BODY_TAG)          //借方是公司客户还是个人客户
    private String dbtrPoFlag;

    @OXMapping(xpath = "requestMsg/Dbtr/BirthDt")
    @DFormat(format="yyyy-MM-dd")
    @XOMapping(xpath = "Dbtr/BirthDt", preNode = MsgConstants.REQ_BODY_TAG)     //借方生日
    private Date dbtrPBirthDate;

    @OXMapping(xpath = "requestMsg/Dbtr/CtryOfBirth")
    @XOMapping(xpath = "Dbtr/CtryOfBirth", preNode = MsgConstants.REQ_BODY_TAG)      //借方所在省     //借方所在国家
    private String dbtrPBirthCtry;

    @OXMapping(xpath = "requestMsg/Dbtr/PrvcOfBirth")
    @XOMapping(xpath = "Dbtr/PrvcOfBirthh", preNode = MsgConstants.REQ_BODY_TAG)      //借方所在省
    private String dbtrPBirthPrv;

    @OXMapping(xpath = "requestMsg/Dbtr/CityOfBirth")
    @XOMapping(xpath = "Dbtr/CityOfBirth", preNode = MsgConstants.REQ_BODY_TAG)     //借方所在市
    private String dbtrPBirthCity;

    @OXMapping(xpath = "requestMsg/Dbtr/IDType")
    @XOMapping(xpath = "Dbtr/IDType", preNode = MsgConstants.REQ_BODY_TAG)            //借方证件类型(01-身份证，02-护照，03-退伍证等)
    private String dbtrIdTp;

    @OXMapping(xpath = "requestMsg/Dbtr/IDNo")
    @XOMapping(xpath = "Dbtr/IDNo", preNode = MsgConstants.REQ_BODY_TAG)        //借方证件号码
    private String dbtrIdNumber;

    @OXMapping(xpath = "requestMsg/Dbtr/ContactNo")
    @XOMapping(xpath = "Dbtr/ContactNo", preNode = MsgConstants.REQ_BODY_TAG)        //借方电话号码
    private String dbtrContactno;

    @OXMapping(xpath = "requestMsg/Dbtr/ContractNo")
    @XOMapping(xpath = "Dbtr/ContractNo", preNode = MsgConstants.REQ_BODY_TAG)    //出借合同号
    private String lenderContractNo;

    @OXMapping(xpath = "requestMsg/DbtrAcct/IdTp")
    @XOMapping(xpath = "DbtrAcct/IdTp", preNode = MsgConstants.REQ_BODY_TAG)        //借方账户号类型
    private String dbtrAcctIdtp;

    @OXMapping(xpath = "requestMsg/DbtrAcct/IssrCd")
    @XOMapping(xpath = "DbtrAcct/IssrCd", preNode = MsgConstants.REQ_BODY_TAG)        //借方账户行代码
    private String dbtrIssuerCd;

    @OXMapping(xpath = "requestMsg/DbtrAcct/Issr")
    @XOMapping(xpath = "DbtrAcct/Issr", preNode = MsgConstants.REQ_BODY_TAG)        //借方账户号发行方
    private String dbtrIssuerNm;

    @OXMapping(xpath = "requestMsg/DbtrAcct/Branch")
    @XOMapping(xpath = "DbtrAcct/Branch", preNode = MsgConstants.REQ_BODY_TAG)           //借方开户网点
    private String dbtrBranch;

    @OXMapping(xpath = "requestMsg/DbtrAcct/Id")
    @XOMapping(xpath = "DbtrAcct/Id", preNode = MsgConstants.REQ_BODY_TAG)          //借方账户号
    private String dbtrAcctId;

    @OXMapping(xpath = "requestMsg/DbtrAcct/ShortCard")
    @XOMapping(xpath = "DbtrAcct/ShortCard", preNode = MsgConstants.REQ_BODY_TAG)       //借方短卡号
    private String dbtrShortCard;

    @OXMapping(xpath = "requestMsg/DbtrAcct/Ccy")
    @XOMapping(xpath = "DbtrAcct/Ccy", preNode = MsgConstants.REQ_BODY_TAG)         //借方账户币种
    private String dbtrAcctCcy;

    @OXMapping(xpath = "requestMsg/DbtrAcct/Nm")
    @XOMapping(xpath = "DbtrAcct/Nm", preNode = MsgConstants.REQ_BODY_TAG)          //借方账户名称
    private String dbtrAcctNm;

    @OXMapping(xpath = "requestMsg/DbtrAcct/Tp")
    @XOMapping(xpath = "DbtrAcct/Tp", preNode = MsgConstants.REQ_BODY_TAG)          //借方账户类型
    private String dbtrAcctTp;

    @OXMapping(xpath = "requestMsg/Cdtr/Nm")
    @XOMapping(xpath = "Cdtr/Nm", preNode = MsgConstants.REQ_BODY_TAG)               //贷方姓名
    private String cdtrNm;

    @OXMapping(xpath = "requestMsg/Cdtr/PorO")
    @XOMapping(xpath = "Cdtr/PorO", preNode = MsgConstants.REQ_BODY_TAG)          //贷方是公司或个人客户
    private String cdtrPoFlag;

    @OXMapping(xpath = "requestMsg/Cdtr/BirthDt")
    @DFormat(format="yyyy-MM-dd")
    @XOMapping(xpath = "Cdtr/BirthDt", preNode = MsgConstants.REQ_BODY_TAG)     //贷方个人生日
    private Date cdtrPBirthDate;

    @OXMapping(xpath = "requestMsg/Cdtr/CtryOfBirth")
    @XOMapping(xpath = "Cdtr/CtryOfBirth", preNode = MsgConstants.REQ_BODY_TAG)     //贷方所在国家
    private String cdtrPBirthCtry;

    @OXMapping(xpath = "requestMsg/Cdtr/PrvcOfBirth")
    @XOMapping(xpath = "Cdtr/PrvcOfBirth", preNode = MsgConstants.REQ_BODY_TAG)      //贷方所在省
    private String cdtrPBirthPrv;

    @OXMapping(xpath = "requestMsg/Cdtr/CityOfBirth")
    @XOMapping(xpath = "Cdtr/CityOfBirth", preNode = MsgConstants.REQ_BODY_TAG)     //贷方所在市
    private String cdtrPBirthCity;

    @OXMapping(xpath = "requestMsg/Cdtr/CrdtrTp")
    @XOMapping(xpath = "Cdtr/CrdtrTp", preNode = MsgConstants.REQ_BODY_TAG)               //贷方是银行还是非银行
    private String cdtrTp;

    @OXMapping(xpath = "requestMsg/Cdtr/IDType")
    @XOMapping(xpath = "Cdtr/IDType", preNode = MsgConstants.REQ_BODY_TAG)            //贷方证件类型(01-身份证，02-护照，03-退伍证等)
    private String cdtrIdTp;

    @OXMapping(xpath = "requestMsg/Cdtr/IDNo")
    @XOMapping(xpath = "Cdtr/IDNo", preNode = MsgConstants.REQ_BODY_TAG)        //贷方证件号码
    private String cdtrIdNumber;

    @OXMapping(xpath = "requestMsg/Cdtr/ContactNo")
    @XOMapping(xpath = "Cdtr/ContactNo", preNode = MsgConstants.REQ_BODY_TAG)        //贷方电话号码
    private String cdtrContactno;

    @OXMapping(xpath = "requestMsg/Cdtr/ContractNo")
    @XOMapping(xpath = "Cdtr/ContractNo", preNode = MsgConstants.REQ_BODY_TAG)      //借款合同号
    private String loanContractNo;

    @OXMapping(xpath = "requestMsg/CdtrAcct/IdTp")
    @XOMapping(xpath = "CdtrAcct/IdTp", preNode = MsgConstants.REQ_BODY_TAG)        //贷方账户号类型
    private String cdtrAcctIdtp;

    @OXMapping(xpath = "requestMsg/CdtrAcct/IssrCd")
    @XOMapping(xpath = "CdtrAcct/IssrCd", preNode = MsgConstants.REQ_BODY_TAG)          //贷方账户行代码
    private String cdtrIssrCd;

    @OXMapping(xpath = "requestMsg/CdtrAcct/Issr")
    @XOMapping(xpath = "CdtrAcct/Issr", preNode = MsgConstants.REQ_BODY_TAG)        //贷方账户号发行方
    private String cdtrIssuerNm;

    @OXMapping(xpath = "requestMsg/CdtrAcct/Branch")
    @XOMapping(xpath = "CdtrAcct/Branch", preNode = MsgConstants.REQ_BODY_TAG)           //贷方开户网点
    private String cdtrBranch;

    @OXMapping(xpath = "requestMsg/CdtrAcct/Id")
    @XOMapping(xpath = "CdtrAcct/Id", preNode = MsgConstants.REQ_BODY_TAG)          //贷方账户号
    private String cdtrAcctId;

    @OXMapping(xpath = "requestMsg/CdtrAcct/Ccy")
    @XOMapping(xpath = "CdtrAcct/Ccy", preNode = MsgConstants.REQ_BODY_TAG)         //贷方账户币种
    private String cdtrAcctCcy;

    @OXMapping(xpath = "requestMsg/CdtrAcct/Nm")
    @XOMapping(xpath = "CdtrAcct/Nm", preNode = MsgConstants.REQ_BODY_TAG)          //贷方账户名称
    private String cdtrAcctNm;

    @OXMapping(xpath = "requestMsg/CdtrAcct/Tp")
    @XOMapping(xpath = "CdtrAcct/Tp", preNode = MsgConstants.REQ_BODY_TAG)          //贷方账户类型
    private String cdtrAcctTp;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setFmtVersion(String fmtVersion) {
        this.fmtVersion = fmtVersion;
    }

    public void setExTxnId(String exTxnId) {
        this.exTxnId = exTxnId;
    }

    public void setSplitFlg1(String splitFlg1) {
        this.splitFlg1 = splitFlg1;
    }

    public void setSplitFlg2(String splitFlg2) {
        this.splitFlg2 = splitFlg2;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public void setPmtTp(String pmtTp) {
        this.pmtTp = pmtTp;
    }

    public void setBizTp(String bizTp) {
        this.bizTp = bizTp;
    }

    public void setProcMode(String procMode) {
        this.procMode = procMode;
    }

    public void setMsgSts(String msgSts) {
        this.msgSts = msgSts;
    }

    public void setErrorDesp(String errorDesp) {
        this.errorDesp = errorDesp;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public void setReasonFlg(String reasonFlg) {
        this.reasonFlg = reasonFlg;
    }

    public void setChkSts(String chkSts) {
        this.chkSts = chkSts;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    public void setReserveFlg(String reserveFlg) {
        this.reserveFlg = reserveFlg;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public void setReserveTm(Date reserveTm) {
        this.reserveTm = reserveTm;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setCardTp(String cardTp) {
        this.cardTp = cardTp;
    }

    public void setBatchIdIn(String batchIdIn) {
        this.batchIdIn = batchIdIn;
    }

    public void setBatchIdOut(String batchIdOut) {
        this.batchIdOut = batchIdOut;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public void setExReturnUrl(String exReturnUrl) {
        this.exReturnUrl = exReturnUrl;
    }

    public void setExNotifyUrl(String exNotifyUrl) {
        this.exNotifyUrl = exNotifyUrl;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public void setTraceTime(Date traceTime) {
        this.traceTime = traceTime;
    }

    public void setExeTime(Date exeTime) {
        this.exeTime = exeTime;
    }

    public void setExSttlDt(Date exSttlDt) {
        this.exSttlDt = exSttlDt;
    }

    public void setNsSttlDt(Date nsSttlDt) {
        this.nsSttlDt = nsSttlDt;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public void setOutTxnId(String outTxnId) {
        this.outTxnId = outTxnId;
    }

    public void setPostingSts(String postingSts) {
        this.postingSts = postingSts;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public void setOrglOrderid(String orglOrderid) {
        this.orglOrderid = orglOrderid;
    }

    public void setOrglMid(String orglMid) {
        this.orglMid = orglMid;
    }

    public void setOrglTxntp(String orglTxntp) {
        this.orglTxntp = orglTxntp;
    }

    public void setOrglSttlAmt(BigDecimal orglSttlAmt) {
        this.orglSttlAmt = orglSttlAmt;
    }

    public void setOrglSttlAmtCcy(String orglSttlAmtCcy) {
        this.orglSttlAmtCcy = orglSttlAmtCcy;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public void setAppSysId(String appSysId) {
        this.appSysId = appSysId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public void setBizProdId(String bizProdId) {
        this.bizProdId = bizProdId;
    }

    public void setBizProdInfo(String bizProdInfo) {
        this.bizProdInfo = bizProdInfo;
    }


    public void setCvvInfo(String cvvInfo) {
        this.cvvInfo = cvvInfo;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public void setPayFlg(String payFlg) {
        this.payFlg = payFlg;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public void setSttlAmt(BigDecimal sttlAmt) {
        this.sttlAmt = sttlAmt;
    }

    public void setSttlAmtCcy(String sttlAmtCcy) {
        this.sttlAmtCcy = sttlAmtCcy;
    }

    public void setActualSttlAmt(BigDecimal actualSttlAmt) {
        this.actualSttlAmt = actualSttlAmt;
    }

    public void setActualSttlCcy(String actualSttlCcy) {
        this.actualSttlCcy = actualSttlCcy;
    }

    public void setRSttlAmt(BigDecimal rSttlAmt) {
        this.rSttlAmt = rSttlAmt;
    }

    public void setRSttlCcy(String rSttlCcy) {
        this.rSttlCcy = rSttlCcy;
    }

    public void setAsgnCdtMop(String asgnCdtMop) {
        this.asgnCdtMop = asgnCdtMop;
    }

    public void setCdtMop(String cdtMop) {
        this.cdtMop = cdtMop;
    }

    public void setCdtMopName(String cdtMopName) {
        this.cdtMopName = cdtMopName;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public void setExMerchId(String exMerchId) {
        this.exMerchId = exMerchId;
    }

    public void setDbtrNm(String dbtrNm) {
        this.dbtrNm = dbtrNm;
    }

    public void setDbtrPoFlag(String dbtrPoFlag) {
        this.dbtrPoFlag = dbtrPoFlag;
    }

    public void setDbtrPBirthDate(Date dbtrPBirthDate) {
        this.dbtrPBirthDate = dbtrPBirthDate;
    }

    public void setDbtrPBirthCtry(String dbtrPBirthCtry) {
        this.dbtrPBirthCtry = dbtrPBirthCtry;
    }

    public void setDbtrPBirthPrv(String dbtrPBirthPrv) {
        this.dbtrPBirthPrv = dbtrPBirthPrv;
    }

    public void setDbtrPBirthCity(String dbtrPBirthCity) {
        this.dbtrPBirthCity = dbtrPBirthCity;
    }

    public void setDbtrIdTp(String dbtrIdTp) {
        this.dbtrIdTp = dbtrIdTp;
    }

    public void setDbtrIdNumber(String dbtrIdNumber) {
        this.dbtrIdNumber = dbtrIdNumber;
    }

    public void setDbtrContactno(String dbtrContactno) {
        this.dbtrContactno = dbtrContactno;
    }

    public void setLenderContractNo(String lenderContractNo) {
        this.lenderContractNo = lenderContractNo;
    }

    public void setDbtrAcctIdtp(String dbtrAcctIdtp) {
        this.dbtrAcctIdtp = dbtrAcctIdtp;
    }

    public void setDbtrIssuerCd(String dbtrIssuerCd) {
        this.dbtrIssuerCd = dbtrIssuerCd;
    }

    public void setDbtrIssuerNm(String dbtrIssuerNm) {
        this.dbtrIssuerNm = dbtrIssuerNm;
    }

    public void setDbtrBranch(String dbtrBranch) {
        this.dbtrBranch = dbtrBranch;
    }

    public void setDbtrAcctId(String dbtrAcctId) {
        this.dbtrAcctId = dbtrAcctId;
    }

    public void setDbtrShortCard(String dbtrShortCard) {
        this.dbtrShortCard = dbtrShortCard;
    }

    public void setDbtrAcctCcy(String dbtrAcctCcy) {
        this.dbtrAcctCcy = dbtrAcctCcy;
    }

    public void setDbtrAcctNm(String dbtrAcctNm) {
        this.dbtrAcctNm = dbtrAcctNm;
    }

    public void setDbtrAcctTp(String dbtrAcctTp) {
        this.dbtrAcctTp = dbtrAcctTp;
    }

    public void setCdtrNm(String cdtrNm) {
        this.cdtrNm = cdtrNm;
    }

    public void setCdtrPoFlag(String cdtrPoFlag) {
        this.cdtrPoFlag = cdtrPoFlag;
    }

    public void setCdtrPBirthDate(Date cdtrPBirthDate) {
        this.cdtrPBirthDate = cdtrPBirthDate;
    }

    public void setCdtrPBirthCtry(String cdtrPBirthCtry) {
        this.cdtrPBirthCtry = cdtrPBirthCtry;
    }

    public void setCdtrPBirthPrv(String cdtrPBirthPrv) {
        this.cdtrPBirthPrv = cdtrPBirthPrv;
    }

    public void setCdtrPBirthCity(String cdtrPBirthCity) {
        this.cdtrPBirthCity = cdtrPBirthCity;
    }

    public void setCdtrTp(String cdtrTp) {
        this.cdtrTp = cdtrTp;
    }

    public void setCdtrIdTp(String cdtrIdTp) {
        this.cdtrIdTp = cdtrIdTp;
    }

    public void setCdtrIdNumber(String cdtrIdNumber) {
        this.cdtrIdNumber = cdtrIdNumber;
    }

    public void setCdtrContactno(String cdtrContactno) {
        this.cdtrContactno = cdtrContactno;
    }

    public void setLoanContractNo(String loanContractNo) {
        this.loanContractNo = loanContractNo;
    }

    public void setCdtrAcctIdtp(String cdtrAcctIdtp) {
        this.cdtrAcctIdtp = cdtrAcctIdtp;
    }

    public void setCdtrIssrCd(String cdtrIssrCd) {
        this.cdtrIssrCd = cdtrIssrCd;
    }

    public void setCdtrIssuerNm(String cdtrIssuerNm) {
        this.cdtrIssuerNm = cdtrIssuerNm;
    }

    public void setCdtrBranch(String cdtrBranch) {
        this.cdtrBranch = cdtrBranch;
    }

    public void setCdtrAcctId(String cdtrAcctId) {
        this.cdtrAcctId = cdtrAcctId;
    }

    public void setCdtrAcctCcy(String cdtrAcctCcy) {
        this.cdtrAcctCcy = cdtrAcctCcy;
    }

    public void setCdtrAcctNm(String cdtrAcctNm) {
        this.cdtrAcctNm = cdtrAcctNm;
    }

    public void setCdtrAcctTp(String cdtrAcctTp) {
        this.cdtrAcctTp = cdtrAcctTp;
    }

    public String getExTxnId() {
        return exTxnId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getFmtVersion() {
        return fmtVersion;
    }

    public String getSplitFlg1() {
        return splitFlg1;
    }

    public String getSplitFlg2() {
        return splitFlg2;
    }

    public String getTxnType() {
        return txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public String getPmtTp() {
        return pmtTp;
    }

    public String getBizTp() {
        return bizTp;
    }

    public String getProcMode() {
        return procMode;
    }

    public String getMsgSts() {
        return msgSts;
    }

    public String getErrorDesp() {
        return errorDesp;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public String getReasonFlg() {
        return reasonFlg;
    }

    public String getChkSts() {
        return chkSts;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public String getReserveFlg() {
        return reserveFlg;
    }

    public String getReserveId() {
        return reserveId;
    }

    public Date getReserveTm() {
        return reserveTm;
    }

    public String getCustId() {
        return custId;
    }

    public String getCardTp() {
        return cardTp;
    }

    public String getBatchIdIn() {
        return batchIdIn;
    }

    public String getBatchIdOut() {
        return batchIdOut;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getExReturnUrl() {
        return exReturnUrl;
    }

    public String getExNotifyUrl() {
        return exNotifyUrl;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public Date getTraceTime() {
        return traceTime;
    }

    public Date getExeTime() {
        return exeTime;
    }

    public Date getExSttlDt() {
        return exSttlDt;
    }

    public Date getNsSttlDt() {
        return nsSttlDt;
    }

    public String getOperateType() {
        return operateType;
    }

    public String getOutTxnId() {
        return outTxnId;
    }

    public String getPostingSts() {
        return postingSts;
    }

    public String getRemark1() {
        return remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public String getExtend1() {
        return extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public String getOrglOrderid() {
        return orglOrderid;
    }

    public String getOrglMid() {
        return orglMid;
    }

    public String getOrglTxntp() {
        return orglTxntp;
    }

    public BigDecimal getOrglSttlAmt() {
        return orglSttlAmt;
    }

    public String getOrglSttlAmtCcy() {
        return orglSttlAmtCcy;
    }

    public String getChanId() {
        return chanId;
    }

    public String getOperId() {
        return operId;
    }

    public String getAppSysId() {
        return appSysId;
    }

    public String getMerchId() {
        return merchId;
    }

    public String getBizProdId() {
        return bizProdId;
    }

    public String getBizProdInfo() {
        return bizProdInfo;
    }

    public String getCvvInfo() {
        return cvvInfo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public String getValDate() {
        return valDate;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public String getPayFlg() {
        return payFlg;
    }

    public String getAuthId() {
        return authId;
    }

    public BigDecimal getSttlAmt() {
        return sttlAmt;
    }

    public String getSttlAmtCcy() {
        return sttlAmtCcy;
    }

    public BigDecimal getActualSttlAmt() {
        return actualSttlAmt;
    }

    public String getActualSttlCcy() {
        return actualSttlCcy;
    }

    public BigDecimal getRSttlAmt() {
        return rSttlAmt;
    }

    public String getRSttlCcy() {
        return rSttlCcy;
    }

    public String getAsgnCdtMop() {
        return asgnCdtMop;
    }

    public String getCdtMop() {
        return cdtMop;
    }

    public String getCdtMopName() {
        return cdtMopName;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public String getIsChange() {
        return isChange;
    }

    public String getExMerchId() {
        return exMerchId;
    }

    public String getDbtrNm() {
        return dbtrNm;
    }

    public String getDbtrPoFlag() {
        return dbtrPoFlag;
    }

    public Date getDbtrPBirthDate() {
        return dbtrPBirthDate;
    }

    public String getDbtrPBirthCtry() {
        return dbtrPBirthCtry;
    }

    public String getDbtrPBirthPrv() {
        return dbtrPBirthPrv;
    }

    public String getDbtrPBirthCity() {
        return dbtrPBirthCity;
    }

    public String getDbtrIdTp() {
        return dbtrIdTp;
    }

    public String getDbtrIdNumber() {
        return dbtrIdNumber;
    }

    public String getDbtrContactno() {
        return dbtrContactno;
    }

    public String getLenderContractNo() {
        return lenderContractNo;
    }

    public String getDbtrAcctIdtp() {
        return dbtrAcctIdtp;
    }

    public String getDbtrIssuerCd() {
        return dbtrIssuerCd;
    }

    public String getDbtrIssuerNm() {
        return dbtrIssuerNm;
    }

    public String getDbtrBranch() {
        return dbtrBranch;
    }

    public String getDbtrAcctId() {
        return dbtrAcctId;
    }

    public String getDbtrShortCard() {
        return dbtrShortCard;
    }

    public String getDbtrAcctCcy() {
        return dbtrAcctCcy;
    }

    public String getDbtrAcctNm() {
        return dbtrAcctNm;
    }

    public String getDbtrAcctTp() {
        return dbtrAcctTp;
    }

    public String getCdtrNm() {
        return cdtrNm;
    }

    public String getCdtrPoFlag() {
        return cdtrPoFlag;
    }

    public Date getCdtrPBirthDate() {
        return cdtrPBirthDate;
    }

    public String getCdtrPBirthCtry() {
        return cdtrPBirthCtry;
    }

    public String getCdtrPBirthPrv() {
        return cdtrPBirthPrv;
    }

    public String getCdtrPBirthCity() {
        return cdtrPBirthCity;
    }

    public String getCdtrTp() {
        return cdtrTp;
    }

    public String getCdtrIdTp() {
        return cdtrIdTp;
    }

    public String getCdtrIdNumber() {
        return cdtrIdNumber;
    }

    public String getCdtrContactno() {
        return cdtrContactno;
    }

    public String getLoanContractNo() {
        return loanContractNo;
    }

    public String getCdtrAcctIdtp() {
        return cdtrAcctIdtp;
    }

    public String getCdtrIssrCd() {
        return cdtrIssrCd;
    }

    public String getCdtrIssuerNm() {
        return cdtrIssuerNm;
    }

    public String getCdtrBranch() {
        return cdtrBranch;
    }

    public String getCdtrAcctId() {
        return cdtrAcctId;
    }

    public String getCdtrAcctCcy() {
        return cdtrAcctCcy;
    }

    public String getCdtrAcctNm() {
        return cdtrAcctNm;
    }

    public String getCdtrAcctTp() {
        return cdtrAcctTp;
    }

    public Integer getBizProdQty() {
        return bizProdQty;
    }

    public void setBizProdQty(Integer bizProdQty) {
        this.bizProdQty = bizProdQty;
    }

    public String getReqSysId() {
        return reqSysId;
    }

    public void setReqSysId(String reqSysId) {
        this.reqSysId = reqSysId;
    }

    public String getServSysId() {
        return servSysId;
    }

    public void setServSysId(String servSysId) {
        this.servSysId = servSysId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getIsActual() {
        return isActual;
    }

    public void setIsActual(String isActual) {
        this.isActual = isActual;
    }

    public String getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch;
    }
}
