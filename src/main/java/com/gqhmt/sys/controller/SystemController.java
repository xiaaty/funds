package com.gqhmt.sys.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.sys.entity.DictMain;
import com.gqhmt.sys.service.SystemService;
import com.gqhmt.util.StringUtils;

@Controller
public class SystemController{

    @Resource
    private SystemService sysService;
    
    /**
     * 查询字典表
     * @param request
     * @param model
     * @param dictmain
     * @return
     */
    @RequestMapping(value = "/sys/workassist/dictionary/{parent_id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object DictList(HttpServletRequest request,ModelMap model,DictMain dictmain,@PathVariable Long parent_id){
    	if(null!=parent_id && !"".equals(parent_id)){
    		dictmain.setParentId(parent_id);
    	}
        List<DictMain> dictList =sysService.queryDictmain(dictmain);
        model.addAttribute("page",dictList);
        model.addAttribute("dictmain",dictmain);
        return "sys/menu/dictList";
    }
    
    /**
     * 跳转到字典编辑页面
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictAdd/{parent_id}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object DictmainAdd(HttpServletRequest request, ModelMap model,@PathVariable Long parent_id,DictMain dict) throws FssException {
    	dict.setParentId(parent_id);
    	model.addAttribute("dict", dict);
		return "sys/menu/dictAdd";
	}
    
    /**
     * 保存新增字典信息
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object DictmainSave(HttpServletRequest request,@ModelAttribute(value="dict")DictMain dict){
    	if(StringUtils.isNotEmptyString(dict.getDictId())){
    		dict.setDictId(dict.getDictId());
    	}
    	if(StringUtils.isNotEmptyString(dict.getDictName())){
    		dict.setDictName(dict.getDictName());
    	}
    	if(null!=dict.getParentId()){
    		dict.setParentId(dict.getParentId());
    	}
    	dict.setCareateUserId(1l);//以后从session中获取
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
    @RequestMapping(value = "/sys/workassist/dictToUpdate/{dictId}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object DictmaintoUpdate(HttpServletRequest request, ModelMap model,@PathVariable String dictId,@ModelAttribute(value="dict")DictMain dict) throws FssException {
    	List<DictMain> dictlist =sysService.getDictmainById(dictId);
    	model.addAttribute("dict", dictlist.get(0));
		return "sys/menu/dictUpdate";
	}
    
    /**
     * 修改保存
     * @param request
     * @param dict
     * @return
     */
    @RequestMapping(value = "/sys/workassist/updateAndSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object dictUpdate(HttpServletRequest request,@ModelAttribute(value="dict")DictMain dict){
    	dict.setModifyUserId(2l);
    	dict.setModifyTime(new Date());
    	sysService.updateDict(dict);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    
    /**
     * shanchu 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/workassist/deletedeict",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object dictDelete(HttpServletRequest request,ModelMap model){
    	String dictId=	request.getParameter("dictId");
    	sysService.delteDict(dictId);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
        return map;
//		return "redirect:/sys/workassist/dictionary/0";
    }
}
