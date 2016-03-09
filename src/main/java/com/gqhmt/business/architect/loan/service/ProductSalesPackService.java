package com.gqhmt.business.architect.loan.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.gqhmt.business.architect.loan.entity.ProductSalesPackEntity;
import com.gqhmt.business.architect.loan.mapper.read.ProductPackBidReadMapper;

import javax.annotation.Resource;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 营销管理中的   产品营销包装功能的service层
 * @author user
 *
 */
@Service
public class ProductSalesPackService {

	
	@Resource
	private ProductPackBidReadMapper productPackBidReadMapper;
	
	/**
	 * 根据标的ID查询ProductSalesPack实体
	 * @param bid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProductSalesPackEntity queryProductSalesPackInfoByBid(Integer bid){
		
		ProductSalesPackEntity productSalesPackEntity = null;
		try {
			int pack_Id = this.queryPackIdByBidId(bid);
			
			//查询营销包装列表
			productSalesPackEntity = this.queryPackById(pack_Id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		return productSalesPackEntity;

	}
	
	/**
	 * 根据标的id 获取 产品的id
	 * @param bidId
	 * @return
	 */
	public Integer queryPackIdByBidId(Integer bidId){
		return productPackBidReadMapper.queryPackIdByBidId(bidId);
	}
	
	
	public ProductSalesPackEntity queryPackById(Integer packId){
		return productPackBidReadMapper.queryPackById(packId);
	}
	
	
	
}
