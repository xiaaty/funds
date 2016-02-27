package com.gqhmt.controller.api.trade;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.trade.FreezeDto;
import com.gqhmt.extServInter.dto.trade.UnFreezeDto;
import com.gqhmt.extServInter.service.trade.IFreeze;
import com.gqhmt.extServInter.service.trade.IUnFreeze;

/**
* <h1>资金解冻，冻结控制类/h1>
* <p>
* </p>
* 
* @author jhz
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
@RequestMapping(value = "/api")
public class FssFundApi {
	@Resource
	private IFreeze freezeImpl;
	
	@Resource
	private IUnFreeze unFreezeImpl;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年2月27日
	 * function：资金冻结
	 */
    @RequestMapping(value = "/freeze",method = RequestMethod.POST)
    public Object freeze(FreezeDto freezeDto){
    	Response response=new Response();
    	try {
    		response = freezeImpl.excute(freezeDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：资金解冻
     */
    @RequestMapping(value = "/withdrawOrder",method = RequestMethod.POST)
    public Object unFreeze(UnFreezeDto unFreezeDto){
    	Response response=new Response();
    	try {
    		response = unFreezeImpl.excute(unFreezeDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
	
}
