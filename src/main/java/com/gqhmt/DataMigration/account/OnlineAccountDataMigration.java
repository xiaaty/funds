package com.gqhmt.DataMigration.account;

import com.gqhmt.DataMigration.dao.BidDao;
import com.gqhmt.DataMigration.dao.FundAccountDao;
import com.gqhmt.DataMigration.dao.InvementDao;
import com.gqhmt.DataMigration.dao.LoanDao;
import com.gqhmt.business.architect.invest.entity.InvestmentInfo;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Filename:    com.gqhmt.DataMigration.account.OnlineAccountDataMigration
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/7 18:13
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/7  于泳      1.0     1.0 Version
 */
@Service
public class OnlineAccountDataMigration {

    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FssAccountService fssAccountService;

    @Resource
    private CustomerInfoService customerInfoService;


    private final static String gqgetFrontMchn = "76127667KUFK";

    private final static String gqgetBackendMchn= "84736851ZOXW";

    private final static String LOAN_MCHN = "73438335RDMZ";





    /**
     * 线上用户及 其他客户账户迁移
     */
    public void accountDataMig(){

        FundAccountDao fundAccountDao = FundAccountDao.getFundAccountDao();

        try {
            CachedRowSet cs = fundAccountDao.findOnlineAccount();

            while (cs.next()){
                FundAccountEntity entity = parseFundAccount(cs);
                Long custId = cs.getLong("cust_id");

                CustomerInfoEntity customerInfoEntity = fundAccountDao.findCustom(custId);

                LogUtil.info(getClass(),custId+"|"+(customerInfoEntity == null?"客户不存在":customerInfoEntity.getCustomerName()));

                if(customerInfoEntity == null){
                    continue;
                }

                Integer bankId = customerInfoEntity.getBankId();

                BankCardInfoEntity cardInfoEntity = fundAccountDao.findBankCardInfo(customerInfoEntity.getBankId(),custId);
                if(cardInfoEntity == null){
                    cardInfoEntity = fundAccountDao.findBankCardInfo(0,custId);
                }

                LogUtil.info(getClass(),(cardInfoEntity != null ?cardInfoEntity.getBankNo():""));

//                String  contractNo  = cs.getString(1);          //合同号
//                String  bankNo= cs.getString(2);                //银行卡号
//                String  name= cs.getString(3);                  //主借人姓名
//                String  mobile= cs.getString(4);                //客户电话
//                String  certNo = cs.getString(5);               //身份证号
//                String  bankId = cs.getString(6);               //银行全称

                if(customerInfoEntity.getId()>100){
                    //互联网客户
                    onlineAccountDataMig(entity,customerInfoEntity,cardInfoEntity);

                    //线下出借
                    lendOnLineAccountDataMig(entity,customerInfoEntity,cardInfoEntity);

                    //借款
                    loanOnlineAccountDataMig(entity,customerInfoEntity,cardInfoEntity);

                    //其他
                    otherAccountDataMig(entity,customerInfoEntity,cardInfoEntity);
                }else{
                    //对公账户
                    companyAccountDataMig(entity,customerInfoEntity,cardInfoEntity);
                }


            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private FundAccountEntity parseFundAccount(CachedRowSet rs) throws SQLException {
        FundAccountEntity fundAccountEntity  = new FundAccountEntity();
        fundAccountEntity.setId(rs.getLong("id"));
        fundAccountEntity.setUserName(rs.getString("user_name"));
        fundAccountEntity.setCustName(rs.getString("cust_name"));
        fundAccountEntity.setCreateTime(rs.getTimestamp("create_time"));

        return fundAccountEntity;
    }


    public void companyAccountDataMig(FundAccountEntity entity,CustomerInfoEntity customerInfoEntity,BankCardInfoEntity bankCardInfoEntity){

        String  bankNo= bankCardInfoEntity != null ? bankCardInfoEntity.getBankNo():"0000";                //银行卡号
        String  name= entity.getCustName() == null ? customerInfoEntity.getCustomerName():entity.getCustName();                  //主借人姓名
        String  mobile= entity.getUserName();                //客户电话
        String  certNo = customerInfoEntity.getCertNo();               //身份证号
        String  bankId = bankCardInfoEntity != null ? bankCardInfoEntity.getParentBankId():"0000";               //银行全称
        String  area = bankCardInfoEntity != null ? bankCardInfoEntity.getCityId():"0000";


        //对公账户
        // 10018001 公司收费账户          11028001
        // 10018002 保证金账户           11028002
        // 10018003 逆服务费账户          11028003
        // 10018004 保理公司账户          11028004
        String  tradeType = "11028001";
        if(customerInfoEntity.getId() == 3){
            tradeType = "11028003";
        }else if(customerInfoEntity.getId() == 2 || customerInfoEntity.getId() == 7  || customerInfoEntity.getId() == 10 || customerInfoEntity.getId() == 12 ) {
            tradeType = "11028002";
        }else if(customerInfoEntity.getId() == 13 || customerInfoEntity.getId() == 14){
            tradeType = "11028004";
        }

        String i = CommonUtil.getRandomString(4);

        try{
            fssAccountService.createAccount(tradeType,gqgetFrontMchn,mobile ,certNo+"_"+i,name,bankId,bankNo,area,"",customerInfoEntity.getId(),entity.getCreateTime());

        }catch (Exception e){

            LogUtil.error(getClass(),e);

        }
    }

    /**
     * 其他账户创建
     * @param entity
     * @param customerInfoEntity
     * @param bankCardInfoEntity
     */
    public void otherAccountDataMig(FundAccountEntity entity,CustomerInfoEntity customerInfoEntity,BankCardInfoEntity bankCardInfoEntity){

        int  custType = customerInfoEntity.getCustomerType();

        if(custType != 7 && custType != 8){
            return;
        }

        String  bankNo= bankCardInfoEntity != null ? bankCardInfoEntity.getBankNo():"0000";                //银行卡号
        String  name= entity.getCustName() == null ? customerInfoEntity.getCustomerName():entity.getCustName();                  //主借人姓名
        String  mobile= entity.getUserName();                //客户电话
        String  certNo = customerInfoEntity.getCertNo();               //身份证号
        String  bankId = bankCardInfoEntity != null ? bankCardInfoEntity.getParentBankId():"0000";               //银行全称
        String  area = bankCardInfoEntity != null ? bankCardInfoEntity.getCityId():"0000";


        try{
            fssAccountService.createAccount(custType == 7 ?"11020009":"11020014",gqgetFrontMchn,mobile ,certNo,name,bankId,bankNo,area,"",customerInfoEntity.getId(),entity.getCreateTime());

        }catch (Exception e){

            LogUtil.error(getClass(),e);

        }



    }

    /**
     * 互联网账户开户迁移
     * @param entity
     * @param customerInfoEntity
     * @param bankCardInfoEntity
     */
    public void onlineAccountDataMig(FundAccountEntity entity,CustomerInfoEntity customerInfoEntity,BankCardInfoEntity bankCardInfoEntity){
        String  bankNo= bankCardInfoEntity != null ? bankCardInfoEntity.getBankNo():"0000";                //银行卡号
        String  name= entity.getCustName() == null ? customerInfoEntity.getCustomerName():entity.getCustName();                  //主借人姓名
        String  mobile= entity.getUserName();                //客户电话
        String  certNo = customerInfoEntity.getCertNo();               //身份证号
        String  bankId = bankCardInfoEntity != null ? bankCardInfoEntity.getParentBankId():"0000";               //银行全称
        String  area = bankCardInfoEntity != null ? bankCardInfoEntity.getCityId():"0000";

        try{
            fssAccountService.createAccount("11020002",gqgetFrontMchn,mobile ,certNo,name,bankId,bankNo,area,"",customerInfoEntity.getId(),entity.getCreateTime());

        }catch (Exception e){

            LogUtil.error(getClass(),e);

        }

    }


    /**
     * 冠e通借款客户迁移
     * @param entity
     * @param customerInfoEntity
     * @param bankCardInfoEntity
     */
    public void loanOnlineAccountDataMig(FundAccountEntity entity,CustomerInfoEntity customerInfoEntity,BankCardInfoEntity bankCardInfoEntity) throws Exception {

        BidDao bidDao = BidDao.getBidDao();

        List<Bid> bids = bidDao.findAllBid(customerInfoEntity.getId());

        if (customerInfoEntity.getId()<5227){
            return;
        }
        if(bids.size() ==0 ){
            return;
        }
        if(bids == null || bids.size() ==0){
            return;
        }
        LogUtil.info(getClass(),"执行借款人合同开户:"+customerInfoEntity.getId());
        String  bankNo= bankCardInfoEntity != null ? bankCardInfoEntity.getBankNo():"0000";                //银行卡号
        String  name= entity.getCustName() == null ? customerInfoEntity.getCustomerName():entity.getCustName();                  //主借人姓名
        String  mobile= entity.getUserName();                //客户电话
        String  certNo = customerInfoEntity.getCertNo();               //身份证号
        String  bankId = bankCardInfoEntity != null ? bankCardInfoEntity.getParentBankId():"0000";               //银行全称
        String  area = bankCardInfoEntity != null ? bankCardInfoEntity.getCityId():"0000";



        for(Bid bid:bids){
            String contractNo = bid.getContractNo();
            if(contractNo == null || "".equals(contractNo)){
                continue;
            }
            try{
                fssAccountService.createAccount("11020007",gqgetBackendMchn,mobile ,certNo,name,bankId,bankNo,area,contractNo,customerInfoEntity.getId(),entity.getCreateTime());

            }catch (Exception e){


                if("90002017".equals(e.getMessage())){
                    System.out.println("重复合同号:"+contractNo);
                }
                LogUtil.error(getClass(),e);
                continue;
            }
        }

    }







    /**
     * 出借客户迁移
     * @param entity
     * @param customerInfoEntity
     * @param bankCardInfoEntity
     */
    public void lendOnLineAccountDataMig(FundAccountEntity entity,CustomerInfoEntity customerInfoEntity,BankCardInfoEntity bankCardInfoEntity) throws Exception {
        InvementDao invementDao = InvementDao.getInvementDao();

        List<InvestmentInfo> investmentInfos = invementDao.findAll(customerInfoEntity.getId());

        String  bankNo= bankCardInfoEntity != null ? bankCardInfoEntity.getBankNo():"0000";                //银行卡号
        String  name= entity.getCustName() == null ? customerInfoEntity.getCustomerName():entity.getCustName();                  //主借人姓名
        String  mobile= entity.getUserName();                //客户电话
        String  certNo = customerInfoEntity.getCertNo();               //身份证号
        String  bankId = bankCardInfoEntity != null ? bankCardInfoEntity.getParentBankId():"0000";               //银行全称
        String  area = bankCardInfoEntity != null ? bankCardInfoEntity.getCityId():"0000";

        for(InvestmentInfo investmentInfo : investmentInfos){
            String contractNo = investmentInfo.getInvestId();
            if(contractNo == null || "".equals(contractNo)){
                continue;
            }
            try{
                fssAccountService.createAccount("11020006",gqgetBackendMchn,mobile,certNo,name,bankId,bankNo,area,contractNo,customerInfoEntity.getId(),investmentInfo.getCreateTime());

            }catch (Exception e){


                if("90002017".equals(e.getMessage())){
                    System.out.println("重复合同号:"+contractNo);
                }
                continue;
            }
        }
    }




    /**
     * 纯线下账户导入
     */
    public void LoanOffLineAccountDataMig(){
        LoanDao loanDao = LoanDao.getLoanDao();
        BidDao bidDao = BidDao.getBidDao();
        try {

            Set<String> contractNoMap  = bidDao.findAllBidByMap();
            CachedRowSet cs = loanDao.findAllData();

            int count = 0;
            while (cs.next()){
                String  contractNo  = cs.getString(1);          //合同号
                String  bankNo= cs.getString(2);                //银行卡号
                String  name= cs.getString(3);                  //主借人姓名
                String  mobile= cs.getString(4);                //客户电话
                String  certNo = cs.getString(5);               //身份证号
                String  bankId = cs.getString(6);               //银行全称
                System.out.println(contractNo+":"+ ++count +":"+contractNoMap.contains(contractNo));
                if(contractNoMap.contains(contractNo)){
                    continue;
                }

                if(contractNo.contains("展期")){
                    continue;
                }

                Map<String,String> bankIdMap = new HashMap<>();
                bankIdMap.put("中国银行","0104");
                bankIdMap.put("农业银行","0103");
                bankIdMap.put("工商银行","0102");
                bankIdMap.put("建行","0105");
                bankIdMap.put("建设银行","0105");
                bankIdMap.put("招商银行","0308");
                bankIdMap.put("无","0000");


                try{
                    fssAccountService.createAccount("11020011",LOAN_MCHN,mobile == null || "".equals(mobile) ? "10000":mobile ,certNo,name,bankIdMap.get(bankId),bankNo == null || "".equals( bankNo ) ?"0000":bankNo,"",contractNo,0l);

                }catch (Exception e){


                    if("90002017".equals(e.getMessage())){
                        System.out.println("重复合同号:"+contractNo);
                    }
                    continue;
                }

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
