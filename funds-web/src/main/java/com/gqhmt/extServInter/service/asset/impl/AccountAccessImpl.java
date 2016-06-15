package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.service.asset.IAccountAccess;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.pay.service.account.IFundsAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户资产接口--账户资产
 * @author 57627
 */
@Service
public class AccountAccessImpl implements IAccountAccess{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
	/**
	 * 账户资产查询
	 */
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
		QueryListResponse response = new QueryListResponse();
    	List list=new ArrayList();
    	try {
    		FssAssetEntity fssAssetEntity = fundsAccountImpl.getAccountAsset((AssetDto)dto);
    		list.add(fssAssetEntity);
    		response.setPlain(list);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
    
}
