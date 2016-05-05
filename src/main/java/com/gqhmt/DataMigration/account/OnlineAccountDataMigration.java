package com.gqhmt.DataMigration.account;

import com.gqhmt.DataMigration.dao.BidDao;
import com.gqhmt.DataMigration.dao.FundAccountDao;
import com.gqhmt.DataMigration.dao.LoanDao;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.HashMap;
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


    private final static String gqgetFrontMchn = "";

    private final static String gqgetBackendMchn= "";

    private final static String LOAN_MCHN = "68191646GFTP";





    /**
     * 线上用户及 其他客户账户迁移
     */
    public void accountDataMig(){

        FundAccountDao fundAccountDao = FundAccountDao.getFundAccountDao();

        try {
            CachedRowSet cs = fundAccountDao.findOnlineAccount();

            while (cs.next()){
                Long custId = cs.getLong("cust_id");

                CustomerInfoEntity customerInfoEntity = fundAccountDao.findCustom(custId);

                System.out.println(custId+"|"+(customerInfoEntity == null?"客户不存在":customerInfoEntity.getCustomerName()));

                if(customerInfoEntity == null){
                    continue;
                }

                Integer bankId = customerInfoEntity.getBankId();

                BankCardInfoEntity cardInfoEntity = fundAccountDao.findBankCardInfo(customerInfoEntity.getBankId());

                System.out.println((cardInfoEntity != null ?cardInfoEntity.getBankNo():""));


                //互联网客户


                //线下出借


                //借款


                //其他

            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void onlineAccountDataMig(FundAccountEntity entity,CustomerInfoEntity customerInfoEntity,BankCardInfoEntity bankCardInfoEntity){

    }



    /**
     * 借款账户迁移
     */
    public void loanOnlineAccountDataMig(){
        this.loanOnlineAccountDataMig(0);
    }

    /**
     * 借款账户迁移
     * @param pageNum
     */
    public void loanOnlineAccountDataMig(Integer pageNum){

    }


    /**
     * 出借账户迁移
     */
    public void lendOnLineAccountDataMig(){
        this.lendOnLineAccountDataMig(0);
    }
    /**
     * 出借账户迁移
     * @param pageNum
     */
    public void lendOnLineAccountDataMig(Integer pageNum){

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
