package com.gqhmt.fss.controller.customer;

import com.gqhmt.core.util.FileUtil;
import com.gqhmt.core.util.ImageUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.IConstants;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Filename:    com.gqhmt.fss.controller.BankCardInfoController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015年12月21日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/21  李俊龙      1.0     1.0 Version
 */
@Controller
public class BankCardInfoController {
	@Resource
    private BankCardInfoService bankCardInfoService;

    private BankCardInfoEntity entity;

	public BankCardInfoEntity getEntity() {
		return entity;
	}

	public void setBean(BankCardInfoEntity entity) {
		this.entity = entity;
	}
	
	 /**
     * 银行卡变更信息列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fss/customer/cardchangelist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
    public Object bankCardChangeList(HttpServletRequest request,ModelMap model,FssChangeCardEntity fssBankcard){
		List<FssChangeCardEntity> changecardList = bankCardInfoService.getBankCardChangeList(fssBankcard);
		model.addAttribute("page", changecardList);
		model.addAttribute("fssBankcard", fssBankcard);
		model.addAttribute("state",fssBankcard.getState());
		return "fss/customer/bankCardChangeList";
    }
    
    /**
     * 银行列表
     * @param request
     * @param model
     * @param fssBankcard
     * @return
     */
    @RequestMapping(value = "/fund/banklist",method = {RequestMethod.GET,RequestMethod.POST})
   	@AutoPage
       public Object bankList(HttpServletRequest request,ModelMap model,BankEntity bankinfo){
   		List<BankEntity> banklist = bankCardInfoService.getBankList(bankinfo);
   		model.addAttribute("page", banklist);
   		model.addAttribute("bankinfo", bankinfo);
   		return "fss/customer/bankList";
       }
    
	
    /**
     * 添加银行信息
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fund/banktoadd",method = {RequestMethod.GET,RequestMethod.POST})
    public Object bankAdd(HttpServletRequest request, ModelMap model) throws FssException {

    	return "fss/customer/bankAdd";
    }
    
    /**
     * 保存银行信息
     * @param request
     * @param bank
     * @return
     */
    @RequestMapping(value = "/fund/savebank",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object bankSave(HttpServletRequest request,@ModelAttribute(value="bank")BankEntity bank){
    	if(StringUtils.isNotEmptyString(bank.getBankName())){
    		bank.setBankName(bank.getBankName());
    	}else{
    		bank.setBankName(null);
    	}
    	if(StringUtils.isNotEmptyString(bank.getSortName())){
    		bank.setSortName(bank.getSortName());
    	}else{
    		bank.setSortName(null);
    	}
    	if(StringUtils.isNotEmptyString(bank.getBankIcon())){
    		bank.setBankIcon(bank.getBankIcon());
    	}else{
    		bank.setBankIcon(null);
    	}
    	if(StringUtils.isNotEmptyString(bank.getLimitPage())){
    		bank.setLimitPage(bank.getLimitPage());
    	}else{
    		bank.setLimitPage(null);
    	}
    	
    	Date date=new Date();
    	bank.setCreateTime(date);//创建日期
    	bank.setModifyTime(date);//修改日期
    	bank.setCreateUserId(1l);//创建人
    	bank.setModifyUserId(1l);//修改人
    	
    	if(StringUtils.isNotEmptyString(bank.getTmplatePage())){
    		bank.setTmplatePage(bank.getTmplatePage());
    	}else{
    		bank.setTmplatePage(null);
    	}
    	
    	if(bank.getIsSetLimitPage()!=null && !"".equals(bank.getIsSetLimitPage())){
    		bank.setIsSetLimitPage(bank.getIsSetLimitPage());
    	}else{
    		bank.setIsSetLimitPage(null);
    	}
    	
    	if(StringUtils.isNotEmptyString(bank.getBankCode())){
    		bank.setBankCode(bank.getBankCode());
    	}else{
    		bank.setBankCode(null);
    	}
    	Map<String, String> map = new HashMap<String, String>();
    	try {
    		bankCardInfoService.saveBank(bank);
			 map.put("code", "0000");
		     map.put("message", "success");
		} catch (Exception e) {//保存失败
			e.printStackTrace();
			map.put("code", "0001");
		    map.put("message", "error");
		}
			return map;
    }
    
    /**
     * 修改银行信息
     * @param request
     * @param model
     * @param id
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fund/banktoupdate/{id}",method = {RequestMethod.GET,RequestMethod.POST})
  	public Object banktoUpdate(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws FssException {
      	BankEntity bank =bankCardInfoService.getBankById(id);
      	model.addAttribute("bank", bank);
  		return "fss/customer/bankUpdate";
  	}
    
    /**
     * 修改保存
     * @param request
     * @param bank
     * @return
     */
    @RequestMapping(value = "/fund/bankupdateandsave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object bankUpdate(HttpServletRequest request,@ModelAttribute(value="bank")BankEntity bank){
    	Date date=new Date();
    	bank.setModifyTime(date);//修改日期
    	bank.setModifyUserId(1l);//修改人
    	Map<String, String> map = new HashMap<String, String>();
    	try {
    		 bankCardInfoService.updateBank(bank);
			 map.put("code", "0000");
		     map.put("message", "success");
		} catch (Exception e) {//保存失败
			e.printStackTrace();
			map.put("code", "0001");
		    map.put("message", "error");
		}
			return map;
    }
    
    @RequestMapping(value = "/fund/checkPageXe/{id}",method = {RequestMethod.GET,RequestMethod.POST})
  	public Object chakanyemian(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws FssException {
      	BankEntity bank =bankCardInfoService.getBankById(id);
      	model.addAttribute("bank", bank);
  		return "fss/customer/ckxe";
  	}
    
    /**
     * 银行卡管理
     */
	@RequestMapping(value = "/fund/bankCardsManage", method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public Object bankCardList(HttpServletRequest request, ModelMap model,
			@ModelAttribute(value = "bankcard") BankCardInfoEntity  bankcard) {
		List<BankCardInfoEntity> bankCards = bankCardInfoService.findAllbankCards(bankcard);
		model.addAttribute("page", bankCards);
		model.addAttribute("bankcard", bankcard);
		return "fss/customer/bankCardList";
	}
	
	/**
	 * 修改银行卡信息
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws FssException
	 */
	  @RequestMapping(value = "/fund/updateBankCard/{id}",method = {RequestMethod.GET,RequestMethod.POST})
	  	public Object bankCardtoUpdate(HttpServletRequest request, ModelMap model,@PathVariable Integer id) throws FssException {
		  	BankCardInfoEntity bankcard =bankCardInfoService.getBankCardById(id);
	      	model.addAttribute("bankcard", bankcard);
	  		return "fss/customer/bankCardsToUpdate";
	  	}
	
		/**
		 * 修改保存银行卡信息
		 * @param request
		 * @param bankcard
		 * @return
		 */
	  	@RequestMapping(value = "/fund/bankcardsave",method = {RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
	    public Object bankCardSave(HttpServletRequest request,@ModelAttribute(value="bankcard")BankCardInfoEntity bankcard){
	    	Date date=new Date();
	    	bankcard.setModifyTime(date);//修改日期
	    	Map<String, String> map = new HashMap<String, String>();
	    	try {
	    		 bankCardInfoService.updateAndSaveBankCard(bankcard);
				 map.put("code", "0000");
			     map.put("message", "success");
			} catch (Exception e) {//保存失败
				e.printStackTrace();
				map.put("code", "0001");
			    map.put("message", "error");
			}
				return map;
	    }
	
	  	/**
	  	 * 删除银行卡信息
	  	 * @param request
	  	 * @param bankcard
	  	 * @return
	  	 */
	  	@RequestMapping(value = "/fund/deleteBankCard",method = {RequestMethod.GET,RequestMethod.POST})
	  	@ResponseBody
	  	public Object deleteBankCard(HttpServletRequest request){
	  		Integer id=Integer.parseInt(request.getParameter("id"));
	  		Map<String, String> map = new HashMap<String, String>();
	  		try {
	  			bankCardInfoService.delBankCard(id);
	  			map.put("code", "0000");
	  			map.put("message", "success");
	  		} catch (Exception e) {//保存失败
	  			e.printStackTrace();
	  			map.put("code", "0001");
	  			map.put("message", "error");
	  		}
	  		return map;
	  	}
	  	
	  	
	  	@RequestMapping("/fund/bankUploadIcon")
	    @ResponseBody
	    public String uploadBankIcon(HttpServletRequest request) throws Exception {
	        return this.upload(request,1);
	    }

	    @RequestMapping("/fund/bankUploadHtml")
	    @ResponseBody
	    public String uploadBankHtml(HttpServletRequest request) throws Exception {
	        return this.upload(request,2);
	    }

	    private String  upload(HttpServletRequest request,int type)throws Exception{
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        String fileName = "";
	        File filePath = new File(IConstants.BankImageFilePath);
	        if(!filePath.exists()){
	            filePath.mkdirs();

	            boolean exists = filePath.exists();
	            LogUtil.debug1(this.getClass(),exists);
	        }
	        try{
	            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	            //保存上传文件的文件夹相对路径
	            String originalPath = "";
	            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
	                MultipartFile file = entry.getValue();

	                if (file.getSize() > 0) {
	                    byte[] bytes = file.getBytes();
	                    String name= FileUtil.getMillisFileName(file.getOriginalFilename());
	                    name = name.toLowerCase();
	                    //保存省略图
	                    if(type==1) {
	                        ImageUtil.saveToImgByBytesNotPath(bytes, name, IConstants.BankImageFilePath);
	                    }else{

	                        FileOutputStream fos = null;
	                        try{
	                            File fileOut = new File(filePath+"/"+name);
	                            if(!fileOut.exists()){
	                                fileOut.createNewFile();
	                            }
	                            fos = new FileOutputStream(fileOut);
	                            fos.write(bytes);
	                            fos.flush();
	                            fos.close();
	                        }catch (Exception e){
	                            e.printStackTrace();
	                        }finally {
	                            if(fos != null)
	                                fos.close();
	                        }
	                    }
	                    fileName =name;
	                }
	            }
	        }catch(Exception e){
	            LogUtil.error(this.getClass(),e);
	        }
	        return filePath+"/"+fileName;
	    }
	    
	    /**
	     * 查看页面限额
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/fund/bankShowView/{id}")
	    public String bankShowView(HttpServletRequest request,@PathVariable Long id){
	        String path = request.getSession().getServletContext().getRealPath("/bank");
	        BankEntity bankList = bankCardInfoService.getBankById(id);
	        request.setAttribute("bankList", bankList);
	        return "/account/bank_show";
	    }
	    
}
