package com.gqhmt.sys.controller;


import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.sys.entity.DictOrderEntity;
import com.gqhmt.sys.service.SystemService;
import com.gqhmt.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SystemController{

    @Resource
    private SystemService sysService;
    @Resource
    private MerchantService merchantService;


    /**
     * 查询字典表
     * @param request
     * @param model
     * @param dictmain
     * @return
     */

	@RequestMapping(value = "/sys/dictionary",method = {RequestMethod.GET})
	@AutoPage
	public Object DictList(HttpServletRequest request, ModelMap model, DictEntity dictmain){
		return this.dictModel(model,dictmain,"0");
	}

    @RequestMapping(value = "/sys/dictionary/{parent_id}",method = {RequestMethod.GET})
    @AutoPage
    public Object DictList(HttpServletRequest request, ModelMap model, DictEntity dictmain, @PathVariable String parent_id) {
		return dictModel(model, dictmain, parent_id);
	}


	public String dictModel(ModelMap model, DictEntity dictmain,String parent_id){
		if(null == parent_id){
			parent_id = "0";
		}

		dictmain.setParentId(parent_id);
		String returnId = "0";
		List<DictEntity> dictList =sysService.queryDictmain(dictmain);
		if(Integer.parseInt(parent_id)>0){
			DictEntity dict = (DictEntity) sysService.findDictmain(parent_id);
			returnId = String.valueOf(dict.getParentId());
		}


		model.addAttribute("page",dictList);
		model.addAttribute("dictmain",dictmain);
		model.addAttribute("returnId",returnId);
		return "sys/workAssist/dictList";
	}
    
    /**
     * 跳转到字典编辑页面
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/dictionary/{parent_id}/add",method = {RequestMethod.GET})
	public Object DictmainAdd(HttpServletRequest request, ModelMap model,@PathVariable String parent_id,DictEntity dict) throws FssException {
    	dict.setParentId(parent_id);
    	model.addAttribute("dict", dict);
		return "sys/workAssist/dictAdd";
	}
    
    /**
     * 保存新增字典信息
     * @param request
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/dictionary/{parent_id}/add/save",method = {RequestMethod.POST})
    @ResponseBody
    public Object DictmainSave(HttpServletRequest request,@ModelAttribute(value="dict")DictEntity dict){
    	if(StringUtils.isNotEmptyString(dict.getDictId())){
    		dict.setDictId(dict.getParentId()+dict.getDictId());
    	}
    	if(StringUtils.isNotEmptyString(dict.getDictName())){
    		dict.setDictName(dict.getDictName());
    	}
    	if(null!=dict.getParentId()){
    		dict.setParentId(dict.getParentId());
    	}
    	dict.setCareateUserId(1l);
    	Date date=new Date();
    	dict.setCreateTime(date);
    	dict.setModifyUserId(1l);//以后从session中获取
    	dict.setModifyTime(date);
    	dict.setSort("0");
    	dict.setIsValid(dict.getIsValid());
    	Map<String, String> map = new HashMap<String, String>();
    	try {
			sysService.insertDictmain(dict);
			 map.put("code", "0000");
		     map.put("message", "success");
		     map.put("parentid",dict.getParentId().toString());
		} catch (Exception e) {//保存失败
			e.printStackTrace();
			map.put("code", "0001");
		    map.put("message", "error");
		}
			return map;
    }
    
    /**
     * 修改
     * @param request
     * @param model
     * @param dictId
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/dictionary/{dictId}/update",method = {RequestMethod.GET})
	public Object DictmaintoUpdate(HttpServletRequest request, ModelMap model,@PathVariable String dictId) throws FssException {
    	DictEntity dict =sysService.getDictmainById(dictId);
    	model.addAttribute("dict", dict);
		return "sys/workAssist/dictUpdate";
	}
    
    /**
     * 修改保存
     * @param request
     * @param dict
     * @return
     */
    @RequestMapping(value = "/sys/dictionary/{dictId}/update/save",method = {RequestMethod.PUT})
    @ResponseBody
    public Object dictUpdate(HttpServletRequest request,@ModelAttribute(value="dict")DictEntity dict){
    	dict.setModifyUserId(2l);
    	dict.setModifyTime(new Date());
    	sysService.updateDict(dict);

    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    
    /**
     * 删除
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/workassist/deletedeict",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void dictDelete(HttpServletRequest request,ModelMap model){
    	String dictId=	request.getParameter("dictId");
    	sysService.delteDict(dictId);
//		return "redirect:/sys/workassist/dictionary/0";
    }
   
    
    /**
     * 查询字典类型
     * @param request
     * @param model
     * @param dictorder
     * @return
     */
    @RequestMapping(value = "/sys/workassist/dictorder",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object DictList(HttpServletRequest request, ModelMap model, DictOrderEntity dictorder){
        List<DictOrderEntity> dictorderlist =sysService.queryDictOrder(dictorder);
        model.addAttribute("page",dictorderlist);
        model.addAttribute("dictorder",dictorder);
        return "sys/workAssist/dictOrderList";
    }
    
    /**
     * 添加字典类型
     * @param request
     * @param model
     * @param 
     * @param dictorder
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictOrderAdd",method = {RequestMethod.GET,RequestMethod.POST})
	public Object DictOrderAdd(HttpServletRequest request, ModelMap model,DictOrderEntity dictorder) throws FssException {
    	List<DictEntity> dictlist = sysService.findDictList();
    	model.addAttribute("dictlist", dictlist);
    	return "sys/workAssist/dictOrderAdd";
	}
    
    /**
     * 保存字典类型
     * @param request
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictOrderSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object DictOrederSave(HttpServletRequest request,@ModelAttribute(value="dictorder")DictOrderEntity dictorder){
    	Map<String, String> map = new HashMap<String, String>();
    	try {
			sysService.insertDictOrder(dictorder);
			 map.put("code", "0000");
			 map.put("message", "success");
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			map.put("code", "0001");
		    map.put("message", "error");
		}
		return map;
    }
    
    /**
     * 修改字典类型
     * @param request
     * @param model
     * @param id
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictorderToUpdate/{id}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object dictOrderUpdate(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws FssException {
    	//得到字典列表
    	DictOrderEntity dictorder =sysService.getDictOrderById(id);
    	List<DictEntity> dlist = sysService.findDictListByOrderList(dictorder.getOrderList());
    	List<DictEntity> ordlist=sysService.findDictOrder(dictorder.getOrderList());
    	
    	
    	//根据id得到要修改的对象
    	model.addAttribute("dlist", dlist);
    	model.addAttribute("ordlist", ordlist);
    	model.addAttribute("dictorder", dictorder);
		return "sys/workAssist/dictOrderUpdate";
	}
    
    /**
     * 修改保存
     * @param request
     * @return
     */
    @RequestMapping(value = "/sys/workassist/dictOrderUpdate",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object dictUpdate(HttpServletRequest request,@ModelAttribute(value="dictorder")DictOrderEntity dictorder){
    	dictorder.setOrderName(dictorder.getOrderName());
    	dictorder.setOrderDict(dictorder.getOrderDict());
    	dictorder.setOrderList(dictorder.getOrderList());
    	dictorder.setMemo(dictorder.getMemo());
    	sysService.updateDictOrder(dictorder);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    
    
}
