package com.gqhmt.core.util;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.entity.FssAreaMappingEntity;
import com.gqhmt.fss.architect.customer.service.FssAreaMappingService;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.service.BankService;
import com.gqhmt.sys.entity.BankDealamountLimitEntity;
import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.sys.entity.DictOrderEntity;
import com.gqhmt.sys.entity.MenuEntity;
import com.gqhmt.sys.service.BankDealamountLimitService;
import com.gqhmt.sys.service.SystemService;
import com.gqhmt.util.ServiceLoader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Application {


	private static Application application = new Application();


	private Application(){
        init();

    }
	public static Application getInstance() {
		return application;
	}


    private final Map<String,String> dict = new ConcurrentHashMap<>();
    private final Map<String,DictEntity> dictEntityMap = new ConcurrentHashMap<>();
    private final Map<String,String> dictOrder = new ConcurrentHashMap<>();

    private final Map<String,MerchantEntity>   merchantEntityMap = new ConcurrentHashMap<>();
    
    private final Map<String,BankDealamountLimitEntity>   bankAmountLimitMap = new ConcurrentHashMap<>();
    
    private final Map<String,String>   fourCodemap = new ConcurrentHashMap<>();
    private final Map<String,String>   sixCodemap = new ConcurrentHashMap<>();
    private final Map<String,String>   eightCodemap = new ConcurrentHashMap<>();
    private final Map<String,BankEntity>   bankEntitymap = new ConcurrentHashMap<>(); //银行列表

    private void init(){
        synchronized (this){
            update();
        }
        LogUtil.debug(this.getClass(),dict.toString());
        LogUtil.debug(this.getClass(),dictEntityMap.toString());
        LogUtil.debug(this.getClass(),dictOrder.toString());
        LogUtil.debug(this.getClass(),merchantEntityMap.toString());
        LogUtil.debug(this.getClass(),bankAmountLimitMap.toString());
        LogUtil.debug(this.getClass(),eightCodemap.toString());
        LogUtil.debug(this.getClass(),sixCodemap.toString());
        LogUtil.debug(this.getClass(),fourCodemap.toString());
        LogUtil.debug(this.getClass(),bankEntitymap.toString());
    }

    public void reload(){
        synchronized (this){
            dict.clear();
            dictOrder.clear();
            merchantEntityMap.clear();
            bankAmountLimitMap.clear();
            fourCodemap.clear();
            sixCodemap.clear();
            eightCodemap.clear();
            bankEntitymap.clear();
            update();
        }
    }

    private void update(){
        initDict();
        initMerchant();
        initBankDealamountLimit();
        iniBankArea();
        initBankList();
    }

    /*======================================数据字典初始化及应用========================================================*/
    private void initDict(){
        SystemService systemService = ServiceLoader.get(SystemService.class);
        List<DictEntity> dicts = systemService.findALl();
        if(dicts == null) return;

        for(DictEntity dictEntity:dicts){
            this.dict.put(dictEntity.getDictId(),dictEntity.getDictName());
            dictEntityMap.put(dictEntity.getDictId(),dictEntity);
        }

        List<DictOrderEntity> dictOrders = systemService.findALlDictOrder();
        for(DictOrderEntity dictEntity:dictOrders){
            this.dictOrder.put(dictEntity.getOrderDict(),dictEntity.getOrderList());

           /* File file = new File("");
            file.exists();*/
        }

    }


    public boolean existsDice(String  key){
        return this.dict.containsKey(key);
    }

    public String getDictParentKey(String key) throws FssException {
        DictEntity dictEntity = dictEntityMap.get(key);
        if(dictEntity == null){
            throw new FssException("90008403");
        }
        return dictEntity.getParentId();
    }

    public String getDictName(String key){
        if(key == null || "".equals(key) || "null".equals(key)){
            return "无";
        }
        if("00000000".equals(key) || "0000".equals(key) ){
            return "成功";
        }
        String value = this.dict.get(key);
        if(value == null || "".equals(value)){
            value = "数据字典未配置";
        }
        return value;
    }


    public String getDictOrderValue(String key){
        String value = this.dictOrder.get(key);
        if(value == null || "".equals(value)){
            value = "数据字典类型未配置";
        }
        return value;
    }
    /*======================================银行交易限额初始化及应用========================================================*/

	private  void initBankDealamountLimit(){
    	BankDealamountLimitService bankDealamountLimitService = ServiceLoader.get(BankDealamountLimitService.class);
    	
    	List<BankDealamountLimitEntity> findAll = bankDealamountLimitService.findAll();  	
    	for(BankDealamountLimitEntity bankDealamountLimitEntity:findAll) {
            bankAmountLimitMap.put(bankDealamountLimitEntity.getBankCode()+bankDealamountLimitEntity.getTradeType(),bankDealamountLimitEntity);
    	}
    }
    
//    public boolean  existsBankDealamountLimit(String  bankCode){
//    	return bankAmountLimitMap.containsKey(bankCode);
//    }
    
    public BigDecimal getBankDealamountLimit(String bankCode) throws FssException {
    	BankDealamountLimitEntity bankDealamountLimitEntity = bankAmountLimitMap.get(bankCode);
    	if(bankDealamountLimitEntity == null){
    		throw new FssException("90004024");
    	}
    	return bankDealamountLimitEntity.getLimitAmount();
    }
    /*======================================银行交易限额初始化及应用结束========================================================*/
    /**
     * 商户内存加载
     */
    private  void initMerchant(){
        MerchantService merchantService = ServiceLoader.get(MerchantService.class);

        List<MerchantEntity> merchantEntities = merchantService.findBusinessALl();

        for(MerchantEntity merchantEntity:merchantEntities) {
            merchantEntityMap.put(merchantEntity.getMchnNo(),merchantEntity);
        }
    }

    public boolean  existsMchn(String  mchn){
        return merchantEntityMap.containsKey(mchn);
    }

    public String getParentMchn(String mchn) throws FssException {
        MerchantEntity merchantEntity = merchantEntityMap.get(mchn);
        if(merchantEntity == null){
            throw new FssException("90008102");
        }
        return merchantEntity.getParentNo();
    }

    public String getMechKey(String mchn) throws FssException {
        MerchantEntity merchantEntity = merchantEntityMap.get(mchn);
        if(merchantEntity == null){
            throw new FssException("90008102");
        }
        return merchantEntity.getMchnKey();
    }

    /*======================================银行交易限额初始化及应用结束========================================================*/
   
    /**
     * 地区码内存加载
     */
    private  void iniBankArea(){
    	FssAreaMappingService bankAreaMappingService = ServiceLoader.get(FssAreaMappingService.class);
    	
    	 List<FssAreaMappingEntity> bankAreas = bankAreaMappingService.findAllAreaMapping();
    	
    	for(FssAreaMappingEntity bankArea:bankAreas) {
    		sixCodemap.put(bankArea.getFourCode(),bankArea.getSixCode());
    		fourCodemap.put(bankArea.getSixCode(),bankArea.getFourCode());
    		eightCodemap.put(bankArea.getFourCode(),bankArea.getEightCode());
    	}
    }
    /**
     * 
     * author:jhz
     * time:2016年4月1日
     * function：根据四位码返回六位码
     */
    public String getSixCode(String fourCode) throws FssException {
    	String string = sixCodemap.get(fourCode);
    	return string;
    }
    /**
     * 
     * author:jhz
     * time:2016年4月1日
     * function：根据四位码返回八位码
     */
    public String getEightCode(String fourCode) throws FssException {
    	String string = eightCodemap.get(fourCode);
    	return string;
    }
    /**
     * 
     * author:jhz
     * time:2016年4月1日
     * function：根据六位码返回四位码
     */
    public String getFourCode(String sixCode) throws FssException {
    	String string = fourCodemap.get(sixCode);
    	try{
    		if(string==null||"".equals(string)){
    			String dictParentKey = this.getDictParentKey("95"+sixCode);
    			if(dictParentKey!=null&&!"".equals(dictParentKey))
    			string = getFourCode(dictParentKey.substring(2));
    		}
    	}catch(FssException e){
    		 throw new FssException("90004031");
    	}
    	return string;
    }
    
//    =============================银行列表初始化及应用=====================start===========================
    private void initBankList(){
    	BankService bankService = ServiceLoader.get(BankService.class);
        List<BankEntity> banks = bankService.findAll();
        if(banks == null) return;

        for(BankEntity bankEntity:banks){
            this.dict.put(bankEntity.getBankCode(),bankEntity.getBankName());
            bankEntitymap.put(bankEntity.getBankCode(),bankEntity);
        }
    }
    public boolean  existsBank(String  bankCode){
        return bankEntitymap.containsKey(bankCode);
    }

    public String getBankName(String bankCode) throws FssException {
    	BankEntity bankEntity = bankEntitymap.get(bankCode);
        if(bankEntity == null){
            throw new FssException("90002012");
        }
        return bankEntity.getBankName();
    }
    public String getBankShortName(String bankCode) throws FssException {
    	BankEntity bankEntity = bankEntitymap.get(bankCode);
    	if(bankEntity == null){
    		throw new FssException("90002012");
    	}
    	return bankEntity.getSortName();
    }
//    =============================银行列表初始化及应用=====================end===========================
    
    /*======================================菜单初始化及应用========================================================*/



    public StringBuffer getHtml(List<MenuEntity> func, String context, String url){

        StringBuffer sb = new StringBuffer();
        if(func.size()<=0){
            return sb;
        }
        sb.append("<ul>");
        for(MenuEntity menu : func){
            com.gqhmt.util.LogUtil.debug(this.getClass(),"tag:"+url+"___"+menu.getMenuUrl());
            if(checkMenu(url,menu)){
                sb.append(" <li class='active'>");
            }else{
                sb.append(" <li class=''>");
            }

            sb.append("<a href='");
            if(!menu.getMenuUrl().substring(0,1).equals("#")) {
                sb.append(context);
            }
            sb.append(parse(menu,url));//添加链接
            sb.append("' title='");
            sb.append(menu.getMenuName());
            sb.append("'>");
            /*if(menu.getIcoClass()!= null && !"".equals(menu.getIcoClass())){
                sb.append("<i class='fa fa-lg fa-fw ");
                sb.append(menu.getIcoClass());
                sb.append(" '></i>");
                sb.append("<span class='menu-item-parent'>");
                sb.append(menu.getMenuName());
                sb.append("</span>");
            }else{
                sb.append(menu.getMenuName());
            }*/

            sb.append(menu.getMenuName());

            sb.append("</a>");

            if(menu.getList().size()>0){
                sb.append(getHtml(menu.getList(),context,url));
            }

            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb;
    }

    private boolean checkMenu(String url, MenuEntity menu) {
        if(menu.getParma()==null){
            return url.equals(menu.getMenuUrl());
        }
        String[] param = menu.getParma().split(",");
        String[] menuUrlLength = menu.getMenuUrl().split("/");
        String[] urlLength = url.split("/");
        if (menuUrlLength.length != urlLength.length) return false;
        boolean flag = true;
        for(int i = 0;i<menuUrlLength.length;i++){
            String tmp = menuUrlLength[i];
            if(check(tmp,param))
                continue;

            String urlTmp = urlLength[i];

            if(!tmp.equals(urlTmp)){
                flag  =false;
                break;
            }

        }
        return flag;
    }

    private boolean check(String tmp, String[] param) {

        for(String t:param){
            if(t.equals(tmp)){
                return true;
            }
        }

        return false;
    }

    private String parse(MenuEntity menu, String url){
        if(menu.getParma() == null || "".equals(menu.getParma())){
            return menu.getMenuUrl();
        }

        String returnUrl = null;
        String[] urlLength = url.split("/");
        String[] menuUrlLength = menu.getMenuUrl().split("/");
        if(urlLength.length == menuUrlLength.length){
            returnUrl = replaceUrlValue(menu,url);
        }else{
            returnUrl = relpaceDefaultValue(menu);
        }


        return returnUrl;
    }

    private String replaceUrlValue(MenuEntity menu, String url) {
       /* if(checkMenu(url,menu)){
            return url;
        }
*/
        return relpaceDefaultValue(menu);
    }


    private String relpaceDefaultValue(MenuEntity menu){
        if(menu == null || menu.getParma() == null){
            return "";
        }
        String[] param = menu.getParma().split(",");
        String[] paramValue = menu.getParmaDefaule().split(",");
        String url  = menu.getMenuUrl();

        for(int i =0;i<param.length;i++){
            String paramTmp = param[i];
            url = url.replace(paramTmp,paramValue[i]);
        }
        return url;

    }

    /*======================================菜单初始化及应用结束========================================================*/
}
