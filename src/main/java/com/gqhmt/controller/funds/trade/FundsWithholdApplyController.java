package com.gqhmt.controller.funds.trade;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.business.architect.invest.service.InvestmentService;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.trade.bean.WithholdApplyBean;
import com.gqhmt.funds.architect.trade.bean.WithholdApplyFormBean;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.WithholdApplyService;
import com.gqhmt.sys.beans.SysUsers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* <h1>代扣管理控制类</h1>
* <p>
* 提现申请列表查询 提现申请审核
* </p>
* 
* @author jhz
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
public class FundsWithholdApplyController {
	@Resource
	private WithholdApplyService withholdApplyService;
	@Resource
	private WithholdApplyService withholdService;
	@Resource
	private InvestmentService investmentService;
	@Resource
	private BankCardInfoService bankCardinfoService;

//	private static final Log log = LogFactory
//			.getLog(WithholdApplyController.class);

	/**
	 * 
	 * author:jhz
	 * time:2016年2月18日
	 * function：根据条件查询并返回所有代扣申请列表信息
	 */
	@RequestMapping("/withholdApply/queryWithholdList")
	@AutoPage
	public String queryWithholdList(HttpServletRequest request,
			ModelMap model, WithholdApplyBean withholdBean) {
			List<WithholdApplyEntity> withHoldList= withholdApplyService.queryWithHoldList(withholdBean);
			model.addAttribute("withholdBean", withholdBean);
			model.addAttribute("page", withHoldList);
		return "funds/account/withHold/withhold_list";
	}

	
	
	
    /**
     * 
     * author:jhz
     * time:2016年2月25日
     * function：合同代扣
     * 批量代扣
     */
	@RequestMapping(value="/account/withhold/withholdRech")
	@ResponseBody
	public Object withholdRech(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="no",required=false,defaultValue="")String no){

		LogUtil.debug(this.getClass(),no);
		
		SysUsers user  = (SysUsers) request.getSession().getAttribute(GlobalConstants.SESSION_EMP);
		String code = "0000";
		String message="代扣成功。";
		String message1="";
		String message2="";
		String message8="";
		String failCustname001 = "";
		String failCustname002 = "";
		
		String[] ids = no.split(",");
		String returnCode ="";
		
		int successCount = 0;
		int failCount = 0;
		
		for (int i=0; i<ids.length; i++) {
		      try {
		    	  returnCode =  withholdService.updateWithholdRech(Long.parseLong(ids[i]), String.valueOf(1));
		    	  
		    	  successCount ++;
		        } catch (Exception e) {
		            LogUtil.error(this.getClass(),e.getMessage(),e);
			          //捕获充值失败异常
		              if (e.getMessage() !=null) {
		            	  if (e.getMessage().contains("0001")) {
				        	  if (failCustname001.equals("")) {
					        	  failCustname001  = failCustname001 + e.getMessage().replace("0001","");
				        	  } else {
					        	  failCustname001  = failCustname001 + ","+ e.getMessage().replace("0001","");
				        	  }
				        	  message1 ="已经代扣过，不能重复操作";
				          } else if (e.getMessage().contains("0002")) {
				        	  if (failCustname002.equals("")) {
					        	  failCustname002  = failCustname002 + e.getMessage().replace("0002","");
				        	  } else {
					        	  failCustname002  = failCustname002 + ","+ e.getMessage().replace("0002","");
				        	  }
				        	  message2 ="代扣金额超过单笔最大上限，请拆分金额实行代扣";
				          } else if (e.getMessage().contains("0008")) {
				        	  if (message8.equals("")) {
				        		  message8  = message8 + e.getMessage().replace("0008","").split("_")[0] + "的代扣失败！" + e.getMessage().replace("0008","").split("_")[1];
				        	  } else {
				        		  message8  = message8 + "<br>"+ e.getMessage().replace("0008","").split("_")[0] + "的代扣失败！" + e.getMessage().replace("0008","").split("_")[1];
				        	  }

				        	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0008====" + e.getMessage(), e);
				        	  //如果代扣失败,更新合同状态为代扣失败
								try {
									withholdService.updateWithholdRechStatus(ids[i], String.valueOf(1));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
						        	  code = "0007";
						        	  message = "数据库操作失败！";
						        	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0007(2)====" + e1.getMessage(), e1);
						        	  break;
								}
				          } else {
				        	  code = "0007";
				        	  message = "数据库操作失败！";
				        	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0007(1)====" + e.getMessage(), e);
				        	  break;
				          }
		              } else {
		            	  code = "0010";
		            	  message = "系统异常！请联系系统管理员";
		            	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0010====" + e.getMessage(), e);
		            	  break;
		              }
		              failCount ++;
		        }
		      
		}

        Map<String, String> map = new HashMap<String, String>();
        map.put("successCount", String.valueOf(successCount));
        map.put("failCount", String.valueOf(failCount));
        map.put("message", message);
        map.put("code", code);

        map.put("failCustname001", failCustname001);
        map.put("message1", message1);
        map.put("failCustname002", failCustname002);
        map.put("message2", message2);
        map.put("message8", message8);
        
        
        return map;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月25日
	 * function：跳转到代扣审核拆分页面
	 */
	@RequestMapping("/account/withhold/withholdReview")
    public String withholdReview( HttpServletRequest request, 
    		@RequestParam(value="id",required=false,defaultValue="")Long id, ModelMap model) {

    	//查询代扣申请信息
		WithholdApplyFormBean withholdApplyFormBean = new WithholdApplyFormBean();
		try {
			withholdApplyFormBean = this.withholdService.getWithholdApplyFormBean(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("detailBean",withholdApplyFormBean);
		
        return "/funds/account/withHold/withhold_review";
    }
	/**
	 * 
	 * author:jhz
	 * time:2016年2月25日
	 * function：审核保存
	 */
    @RequestMapping(value="/account/withhold/withholdReviewConfirm2")
    @ResponseBody
    public Object withholdReviewSave( HttpServletRequest request, 
    		@ModelAttribute(value="withholdApplyFormBean")WithholdApplyFormBean withholdApplyFormBean, ModelMap model) {
    	SysUsers user  = (SysUsers) request.getSession().getAttribute(GlobalConstants.SESSION_EMP);
    	String code = "0000";
		String agreeNo = "";
		String message="代扣成功。";
		String returnCode ="";
	      try {
	    	  returnCode = withholdService.updateWithholdRechSave(withholdApplyFormBean, String.valueOf(1));
	    	  if ("0001".equals(returnCode)) {
	    		  withholdService.updateCallBackBussness(withholdApplyFormBean.getId());
	    	  }
	        } catch (Exception e) {
	            LogUtil.error(this.getClass(),e.getMessage(),e);
		          //捕获充值失败异常
	              if (e.getMessage() !=null) {
	            	  if (e.getMessage().contains("0001")) {
			        	  code = "0001";
			        	  agreeNo = e.getMessage().replace("0001","");
			        	  message ="已经代扣过，不能重复操作";
			          } else if (e.getMessage().contains("0008")) {
			        	  code = "0008";
			        	  message = e.getMessage().replace("0008","");
			          } else {
			        	  code = "0007";
			        	  message = "数据库操作失败！";
			        	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0007====" + e.getMessage(), e);
			          }
	              } else {
	            	  code = "0010";
	            	  message = "系统异常！请联系系统管理员";
	              }
	        }
			//如果代扣失败,更新合同状态为代扣失败
			if ("0008".equals(code)) {
				try {
					withholdService.updateWithholdRechStatus(withholdApplyFormBean.getId(), String.valueOf(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月25日
     * function：审核保存
     */
    @RequestMapping(value="/account/withhold/withholdReviewConfirm")
    @ResponseBody
    public Object withholdReviewSave2( HttpServletRequest request, 
    		@ModelAttribute(value="withholdApplyFormBean")WithholdApplyFormBean withholdApplyFormBean, ModelMap model) {
    	SysUsers user  = (SysUsers) request.getSession().getAttribute(GlobalConstants.SESSION_EMP);
    	String code = "0000";
		String agreeNo = "";
		String message="代扣成功。";
		String returnCode ="";
	
		// 代扣申请明细
		WithholdApplyEntity withholdApplyEntity = null;
		BigDecimal factDrawAmount = BigDecimal.ZERO;
		
	      try {
	    		withholdApplyEntity = withholdService.getWithholdInfo(Long.valueOf(withholdApplyFormBean.getId()));
		  		// 审核不通过
		  		if (withholdApplyFormBean.getApplyStatus().intValue() == 3) {
		  			// 申请状态 3-取消
		  			withholdService.updateWithholdStatus(withholdApplyFormBean.getId(), String.valueOf(1), 3);

		  		} else {
		  		
		  			BankCardInfoEntity bankCardinfoEntity = null;
		  			
		  			if (withholdApplyEntity.getApplyStatus() != 1) {
			        	  code = "0001";
			        	  agreeNo = withholdApplyEntity.getCustName();
			        	  message ="已经代扣过，不能重复操作";
		  			} else {
		  				bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withholdApplyEntity.getBankId());
		  				
		  				for (int i = 0; i < withholdApplyFormBean.getDrawAmountSplit().size(); i++) {
		  					if (i != (withholdApplyFormBean.getDrawAmountSplit().size()-1)) {
			  					returnCode = withholdService.updateWithholdRechSave2(withholdApplyEntity,bankCardinfoEntity,withholdApplyFormBean.getDrawAmountSplit().get(i), false);
		  					} else {
		  						returnCode = withholdService.updateWithholdRechSave2(withholdApplyEntity,bankCardinfoEntity,withholdApplyFormBean.getDrawAmountSplit().get(i), false);
			  					
		  					}
		  					
		  					factDrawAmount = factDrawAmount.add(withholdApplyFormBean.getDrawAmountSplit().get(i));
		  				}
		  				
		  				//成功
	  					if (returnCode.equals("0000") && withholdApplyEntity.getDrawAmount().equals(factDrawAmount)) {
	  						// 申请状态 2-划扣成功
	  						withholdApplyEntity.setApplyStatus(2);
	  						withholdApplyEntity.setFactDrawAmount(factDrawAmount);
	  						// 审核时间
	  						withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
	  						// 审核user
	  						withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
		  					//更新合同状态
		  					withholdService.update(withholdApplyEntity);
		  					
		  					//回调
	  						withholdService.updateCallBackBussness(withholdApplyEntity);
	  						
	  					}  else if (returnCode.equals("0001") && withholdApplyEntity.getDrawAmount().equals(factDrawAmount)) {
	  						// 申请状态 4-代扣中
	  						withholdApplyEntity.setApplyStatus(4);
	  						withholdApplyEntity.setFactDrawAmount(factDrawAmount);
	  						// 审核时间
	  						withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
	  						// 审核user
	  						withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
		  					//更新合同状态
		  					withholdService.update(withholdApplyEntity);
		  					//回调
	  						withholdService.updateCallBackBussness(withholdApplyEntity);
	  					} else if ( returnCode.equals("0002") ) {
	  						// 申请状态 99-人工审核
	  						withholdApplyEntity.setApplyStatus(99);
	  						// 审核时间
	  						withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
	  						// 审核user
	  						withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
		  					//更新合同状态
		  					withholdService.update(withholdApplyEntity);
	  					}

		  			}
		  			
		  		}
		    	  
	        } catch (Exception e) {
	            LogUtil.error(this.getClass(),e.getMessage(),e);
		          //捕获充值失败异常
	              if (e.getMessage() !=null) {
	            	  if (e.getMessage().contains("0001")) {
			        	  code = "0001";
			        	  agreeNo = e.getMessage().replace("0001","");
			        	  message ="已经代扣过，不能重复操作";
			          } else if (e.getMessage().contains("0008")) {
			        	  code = "0008";
			        	  message = e.getMessage().replace("0008","");
			          } else {
			        	  code = "0007";
			        	  message = "数据库操作失败！";
			        	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0007====" + e.getMessage(), e);
			          }
	              } else {
	            	  code = "0010";
	            	  message = "系统异常！请联系系统管理员";
	              }
	        }
			//如果代扣失败,更新合同状态为代扣失败
			if ("0008".equals(code) && factDrawAmount.equals(BigDecimal.ZERO)) {
				try {
					withholdService.updateWithholdRechStatus(withholdApplyFormBean.getId(), String.valueOf(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//如果部分成功
			} else if ("0008".equals(code) && factDrawAmount.compareTo(BigDecimal.ZERO) > 0) {
				try {
					
					// 申请状态 6-部分成功
					withholdApplyEntity.setApplyStatus(6);
					withholdApplyEntity.setFactDrawAmount(factDrawAmount);
					// 审核时间
					withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
					withholdService.update(withholdApplyEntity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }

    /**
     * 
     * author:jhz
     * time:2016年2月25日
     * function：跳转到继续代扣审核拆分页面
     */
	@RequestMapping("/account/withhold/withholdReviewGoon")
    public String withholdReviewGoon( HttpServletRequest request, 
    		@RequestParam(value="id",required=false,defaultValue="")Long id, ModelMap model) {

    	//查询代扣申请信息
		WithholdApplyFormBean withholdApplyFormBean = new WithholdApplyFormBean();
		try {
			withholdApplyFormBean = this.withholdService.getWithholdApplyFormBean(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("detailBean",withholdApplyFormBean);
		
        return "funds/account/withHold/account_withholdreviewGoon";
    }
	/**
	 * 结束代扣
	 * @return
	 */
	@RequestMapping("/account/withhold/withholdReviewOver")
	@ResponseBody
	public Object withholdReviewOver(HttpServletRequest request, 
    		@RequestParam(value="id",required=false,defaultValue="")Long id, ModelMap model){
		SysUsers user  = (SysUsers) request.getSession().getAttribute(GlobalConstants.SESSION_EMP);
    	String code = "0000";
		String message="操作成功。";
		WithholdApplyEntity withholdApplyEntity = null;
		try {
			withholdApplyEntity = withholdService.getWithholdInfo(id);
			
			if (withholdApplyEntity.getApplyStatus() != 6) {
	        	  code = "0001";
	        	  message ="状态有误，不能操作";
			} else {
				//回调
				withholdService.updateReviewOver(withholdApplyEntity.getBussinessId(),withholdApplyEntity.getFactDrawAmount());
				
				withholdApplyEntity.setApplyStatus(2);//设置为已代扣
				withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
				// 审核user
				withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
				//更新合同状态
				withholdService.update(withholdApplyEntity);
			}
		} catch (Exception e) {
			
		}
		
		Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("message", message);
        return map;
	}
	
	/**
	 * 
	 * @Title: withholdReviewSave 
	 * @Description: 审核继续保存
	 * @author guofu   
	 * @date 2015-04-10 下午2:11:57  
	 * @return
	 * ModelAndView
	 */
    @RequestMapping(value="/account/withhold/withholdReviewGoonConfirm")
    @ResponseBody
    public Object withholdReviewGoonConfirm( HttpServletRequest request, 
    		@ModelAttribute(value="withholdApplyFormBean")WithholdApplyFormBean withholdApplyFormBean, ModelMap model) {
    	SysUsers user  = (SysUsers) request.getSession().getAttribute(GlobalConstants.SESSION_EMP);
    	String code = "0000";
		String agreeNo = "";
		String message="代扣成功。";
		String returnCode ="";
		
		// 代扣申请明细
		WithholdApplyEntity withholdApplyEntity = null;
		BigDecimal factDrawAmount = BigDecimal.ZERO;
		
	      try {
	    		withholdApplyEntity = withholdService.getWithholdInfo(Long.valueOf(withholdApplyFormBean.getId()));
		  		// 审核不通过
		  		if (withholdApplyFormBean.getApplyStatus().intValue() == 3) {
		  			// 申请状态 3-取消
		  			withholdService.updateWithholdStatus(withholdApplyFormBean.getId(), String.valueOf(1), 3);

		  		} else {
		  			BankCardInfoEntity bankCardinfoEntity = null;
		  			
		  			if (withholdApplyEntity.getApplyStatus() != 6) {
			        	  code = "0001";
			        	  agreeNo = withholdApplyEntity.getCustName();
			        	  message ="已经代扣过，不能重复操作";
		  			} else {
		  				bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withholdApplyEntity.getBankId());
		  				factDrawAmount = factDrawAmount.add(withholdApplyEntity.getFactDrawAmount());
		  				for (int i = 0; i < withholdApplyFormBean.getDrawAmountSplit().size(); i++) {
		  					if (i != (withholdApplyFormBean.getDrawAmountSplit().size()-1)) {
			  					returnCode = withholdService.updateWithholdRechSave2(withholdApplyEntity,bankCardinfoEntity,withholdApplyFormBean.getDrawAmountSplit().get(i), false);
		  					} else {
		  						returnCode = withholdService.updateWithholdRechSave2(withholdApplyEntity,bankCardinfoEntity,withholdApplyFormBean.getDrawAmountSplit().get(i), false);
			  					
		  					}
		  					
		  					factDrawAmount = factDrawAmount.add(withholdApplyFormBean.getDrawAmountSplit().get(i));
		  				}
		  				
		  				//成功
	  					if (returnCode.equals("0000") && withholdApplyEntity.getDrawAmount().equals(factDrawAmount)) {
	  						// 申请状态 2-划扣成功
	  						withholdApplyEntity.setApplyStatus(2);
	  						withholdApplyEntity.setFactDrawAmount(factDrawAmount);
	  						// 审核时间
	  						withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
	  						// 审核user
	  						withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
		  					//更新合同状态
		  					withholdService.update(withholdApplyEntity);
		  					
		  					//回调
	  						withholdService.updateCallBackBussness(withholdApplyEntity);
	  						
	  					}  else if (returnCode.equals("0001") && withholdApplyEntity.getDrawAmount().equals(factDrawAmount)) {
	  						// 申请状态 4-代扣中
	  						withholdApplyEntity.setApplyStatus(4);
	  						withholdApplyEntity.setFactDrawAmount(factDrawAmount);
	  						// 审核时间
	  						withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
	  						// 审核user
	  						withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
		  					//更新合同状态
		  					withholdService.update(withholdApplyEntity);
		  					//回调
	  						withholdService.updateCallBackBussness(withholdApplyEntity);
	  					} else if ( returnCode.equals("0002") ) {
	  						// 申请状态 99-人工审核
	  						withholdApplyEntity.setApplyStatus(99);
	  						// 审核时间
	  						withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
	  						// 审核user
	  						withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
		  					//更新合同状态
		  					withholdService.update(withholdApplyEntity);
	  					}

		  			}
		  			
		  		}
		    	  
	        } catch (Exception e) {
	            LogUtil.error(this.getClass(),e.getMessage(),e);
		          //捕获充值失败异常
	              if (e.getMessage() !=null) {
	            	  if (e.getMessage().contains("0001")) {
			        	  code = "0001";
			        	  agreeNo = e.getMessage().replace("0001","");
			        	  message ="已经代扣过，不能重复操作";
			          } else if (e.getMessage().contains("0008")) {
			        	  code = "0008";
			        	  message = e.getMessage().replace("0008","");
			          } else {
			        	  code = "0007";
			        	  message = "数据库操作失败！";
			        	  LogUtil.error(this.getClass(), "代扣审核的代扣审批动作报错日志0007====" + e.getMessage(), e);
			          }
	              } else {
	            	  code = "0010";
	            	  message = "系统异常！请联系系统管理员";
	              }
	        }
			//如果代扣失败,更新合同状态为代扣失败
			if ("0008".equals(code) && factDrawAmount.equals(BigDecimal.ZERO)) {
				try {
					withholdService.updateWithholdRechStatus(withholdApplyFormBean.getId(), String.valueOf(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//如果部分成功
			} else if ("0008".equals(code) && factDrawAmount.compareTo(BigDecimal.ZERO) > 0) {
				try {
					
					// 申请状态 6-部分成功
					withholdApplyEntity.setApplyStatus(6);
					withholdApplyEntity.setFactDrawAmount(factDrawAmount);
					// 审核时间
					withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					withholdApplyEntity.setReviewUserId(Integer.parseInt(String.valueOf(1)));
					withholdService.update(withholdApplyEntity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
    
}
