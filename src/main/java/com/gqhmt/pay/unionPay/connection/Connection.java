package com.gqhmt.pay.unionPay.connection;

import com.gnete.security.crypt.Crypt;
import com.gnete.security.crypt.CryptException;
import com.google.common.collect.Maps;
import com.gqhmt.pay.unionPay.utils.Constants;
import com.gqhmt.pay.unionPay.utils.PayConsts;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipInputStream;

public class Connection {

	/**
	 * @Title: batchPayment @Description: 批量支付交易 @param payType-交易类型
	 *         dataList-交易数据 @return void @author liuyang @date 2015-1-5
	 *         下午01:52:33 @throws
	 */
	public synchronized Map<String, Object> batchPayment(String payType, List<Map<String, Object>> dataList) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		boolean isSuccess = false;
		String retInfo = "";
		try {
			if (StringUtils.isNotBlank(payType) && dataList != null && dataList.size() > 0) {
				/***************** 判断请求进行支付的数据是否存在正在交易中的 **********************/
				// LOG.info("【代收付交易】请求数据包校验前，数据包的数目="+dataList.size());
				// dataList =
				// dealFlowDetailManager.judgeIfHasDealingDeatils(dataList);
				// LOG.info("【代收付交易】请求数据包校验校验后，数据包的数目="+dataList.size());
				/****************************************************************/
				if (dataList != null && dataList.size() > 0) {
					// 获取请求流水号
					String reqSn = this.getReqSn(payType);
					// LOG.info("【代收付交易】请求流水号reqSn=" + reqSn);

					// 判断交易包中是否有大于交易限额的数据，若有，则进行拆分
					// LOG.info("【代收付交易】请求数据包拆分前，数据包的数目=" + dataList.size());
					dataList = this.splitTransactionDetails(dataList);
					// LOG.info("【代收付交易】请求数据包拆分后，数据包的数目=" + dataList.size());
					// 获取请求交易xml
					String payRequestXml = this.getPayRequestXmlData(payType, reqSn, dataList);
					/*************************** 发起交易前记录流水开始 **********************/
					// this.saveFlowModelsByBatchPayData(payType, reqSn,
					// dataList);
					/*************************** 发起交易前记录流水结束 **********************/
					// 连接银联服务发起交易
					Map<String, Object> resultMap = this.payRequestToYLServer(payRequestXml);
					boolean resultStatus = (Boolean) resultMap.get("isSuccess");
					String resultInfo = (String) resultMap.get("retInfo");
					if (resultStatus) {
						if (StringUtils.isNotBlank(resultInfo) && resultInfo.startsWith("<?xml")) {
							// 解析返回的数据
							Map<String, Object> resMap = this.analysisRespXml(reqSn, resultInfo);
							isSuccess = (Boolean) resMap.get("isSuccess");
							retInfo = (String) resMap.get("retInfo");
							if (isSuccess) {
								// 预约交易成功
								/**************************
								 * 结算中心复核表根据复核表主键关联流水号--更新复核表交易状态
								 ***************************/
								// dealFlowDetailManager.updateFlowModelsByBatchPayData(reqSn,
								// PayConsts.YL_DEAL_STATUS_DEALING, "",
								// PayConsts.YL_DEAL_RET_CODE_SUCCESS, retInfo);

								// this.updateTransStatusOfCashModels(PayConsts.YL_DEAL_STATUS_DEALING,
								// "", retInfo, dataList);
								/**************************
								 * 结算中心复核表根据复核表主键关联流水号--更新复核表交易状态
								 ***************************/
							} else {
								// 预约交易失败
								if (PayConsts.YL_PAYMENT_TYPE_DS.equals(payType)) {
									// 代收预约失败、直接置为交易失败(修改流水表和资金交易复核表)
									// dealFlowDetailManager.updateFlowModelsByBatchPayData(reqSn,
									// PayConsts.YL_DEAL_STATUS_DEAL_APPOINT_FAIL,
									// "0.00",
									// PayConsts.YL_DEAL_RET_CODE_APPOINT_FAIL,
									// retInfo);
									List<String> transactionIdList = new ArrayList<String>();
									String transactionId = "";
									for (Map map : dataList) {
										transactionId = map.get("transactionId").toString();
										if (!transactionIdList.contains(transactionId)) {
											transactionIdList.add(transactionId);
										}
									}
									// 如果交易包中只有一条交易请求数据，则将其置为交易失败，投资申请置为划扣失败
									if (transactionIdList.size() == 1) {
										// this.updateTransStatusOfCashModels(PayConsts.YL_DEAL_STATUS_DEAL_FAIL,
										// "0.00", retInfo, dataList);
										// 修改出借申请
										// crm.updateCreditInfoToFail(dataList);
									}
								} else {
									// 代付预约失败，还可再次发起预约
									// dealFlowDetailManager.updateFlowModelsByBatchPayData(reqSn,
									// PayConsts.YL_DEAL_STATUS_DEAL_APPOINT_FAIL,
									// "0.00",
									// PayConsts.YL_DEAL_RET_CODE_APPOINT_FAIL,
									// retInfo);
								}
							}
						} else {
							// 与银联交互失败，更新流水状态(以查询结果为准)
							// dealFlowDetailManager.updateFlowModelsByBatchPayData(reqSn,
							// PayConsts.YL_DEAL_STATUS_DEALING, "",
							// PayConsts.YL_DEAL_RET_CODE_SUCCESS, resultInfo);
							// this.updateTransStatusOfCashModels(PayConsts.YL_DEAL_STATUS_DEALING,
							// "", retInfo, dataList);
							retInfo = resultInfo;
						}
					} else {
						// 与银联交互失败，更新流水状态
						// dealFlowDetailManager.updateFlowModelsByBatchPayData(reqSn,
						// PayConsts.YL_DEAL_STATUS_DEAL_APPOINT_FAIL, "0.00",
						// PayConsts.YL_DEAL_RET_CODE_APPOINT_FAIL, resultInfo);
						retInfo = resultInfo;
					}
				} else {
					// LOG.info("【代收付交易】请求数据包校验后，可进行交易的数据为空");
					retInfo = "【代收付交易】请求数据包校验后，可进行交易的数据为空";
				}
			} else {
				int size = (dataList == null ? 0 : dataList.size());
				// LOG.info("【代收付交易】请求数据包的数据有误，payType=" + payType +
				// ",dataList的数目为=" + size);
				retInfo = "【代收付交易】请求数据包的数据有误，payType=" + payType + ",dataList的数目为=" + size;
			}
		} catch (Exception e) {
			// LOG.error("【代收付交易】请求操作失败", e);
			retInfo = "【代收付交易】请求失败" + e.toString();
		}

		retMap.put("isSuccess", isSuccess);
		retMap.put("retInfo", retInfo);
		return retMap;
	}

	/**
	 * @Title: getReqSn @Description: 获取交易流水号 @param payType 交易类型 @return
	 *         交易流水号 @author liuyang @throws
	 */
	public String getReqSn(String payType) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateStr = dateFormat.format(new Date());
		String prefix = "";
		if (com.gqhmt.pay.unionPay.utils.PayConsts.YL_PAYMENT_TYPE_DS.equals(payType)) {
			prefix = PayConsts.PAYMENT_PREFIX_DS;
		} else if (PayConsts.YL_PAYMENT_TYPE_DF.equals(payType)) {
			prefix = PayConsts.PAYMENT_PREFIX_DF;
		} else {
			prefix = PayConsts.PAYMENT_PREFIX_QU;
		}
		return prefix + dateStr;
	}

	/**
	 * @Title: splitTransactionDetails @Description: 判断交易数据中是否
	 *         有大于交易限额的数据，若有则进行拆分 @param 请求交易的数据 @return 拆分后的交易数据 @author
	 *         liuyang @throws
	 */
	public List<Map<String, Object>> splitTransactionDetails(List<Map<String, Object>> dataList) {
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		if (dataList != null && dataList.size() > 0) {
			// 单笔交易添加交易流水
			int snNum = 0;
			// 单笔交易最大限额
			BigDecimal limitAmount = new BigDecimal(PayConsts.YL_DEAL_LIMIT_AMOUNT);
			for (Map<String, Object> map : dataList) {
				// 交易明细流水号
				String snNumStr = "";
				// 预约交易金额(如果交易金额大于交易最大限额则进行拆分)
				BigDecimal payAmount = new BigDecimal(map.get("appointTransAmount").toString());
				if (payAmount.compareTo(limitAmount) > 0) {
					// LOG.info("transId="+map.get("transactionId")+"的请求交易金额为"+map.get("appointTransAmount")+"大于交易限额，开始进行拆分");
					// 预约金额>交易最大限额
					BigDecimal remainsAmount = payAmount;
					BigDecimal transAmount = BigDecimal.ZERO;
					Map<String, Object> transMap = null;
					while (remainsAmount.compareTo(BigDecimal.ZERO) > 0) {
						if (remainsAmount.compareTo(limitAmount) > 0) {
							transAmount = limitAmount;
							remainsAmount = remainsAmount.subtract(transAmount);
						} else {
							transAmount = remainsAmount;
							remainsAmount = BigDecimal.ZERO;
						}
						transMap = Maps.newHashMap(map);
						snNumStr = String.format("%06d", ++snNum);
						transMap.put("appointTransAmount", transAmount);
						transMap.put("detailSn", snNumStr);
						retList.add(transMap);
					}
				} else {
					snNumStr = String.format("%06d", ++snNum);
					map.put("detailSn", snNumStr);
					retList.add(map);
				}
			}
		} else {
			// LOG.info("进行拆分的交易请求包为空");
			retList = dataList;
		}
		return retList;
	}

	/**
	 * @Title: getPayRequestXmlData @Description: 获取银联交易的请求报文(xml格式) @param
	 *         payType 交易类型 @param dataList 交易数据 @return String @throws
	 */
	public String getPayRequestXmlData(String payType, String reqSn, List<Map<String, Object>> dataList) {
		String retPayXmlData = "";
		if (StringUtils.isNotBlank(payType) && dataList != null && dataList.size() > 0) {
			Document doc = null;
			try {
				doc = DocumentHelper.createDocument();
				doc.setXMLEncoding("GBK");
				Element rootEle = doc.addElement("GZELINK");
				Element infoEle = rootEle.addElement("INFO");
				Element bodyEle = rootEle.addElement("BODY");

				Element trxCodeEle = infoEle.addElement("TRX_CODE");
				Element versionEle = infoEle.addElement("VERSION");
				Element dataTypeEle = infoEle.addElement("DATA_TYPE");
				Element levelEle = infoEle.addElement("LEVEL");
				Element userNameEle = infoEle.addElement("USER_NAME");
				Element userPassEle = infoEle.addElement("USER_PASS");
				Element reqSnEle = infoEle.addElement("REQ_SN");
				Element SignedMsgEle = infoEle.addElement("SIGNED_MSG");
				// 交易代码
				String trxCode = "";
				// 业务代码
				String businessCode = "";
				if (PayConsts.YL_PAYMENT_TYPE_DS.equals(payType)) {
					trxCode = PayConsts.YL_TRX_CODE_DS;
					businessCode = Constants.YL_BUSINESS_CODE_DS;
				} else {
					trxCode = PayConsts.YL_TRX_CODE_DF;
					businessCode = Constants.YL_BUSINESS_CODE_DF;
				}
				trxCodeEle.addText(trxCode);
				versionEle.addText(PayConsts.YL_VERSION);
				dataTypeEle.addText(PayConsts.YL_DATA_TYPE);
				levelEle.addText(PayConsts.YL_LEVEL);
				userNameEle.addText(Constants.YL_USER_NAME);
				userPassEle.addText(Constants.YL_USER_PASS);
				reqSnEle.addText(reqSn);
				SignedMsgEle.addText("");

				// 组装Body数据
				Element transSumEle = bodyEle.addElement("TRANS_SUM");
				Element transDetailsEle = bodyEle.addElement("TRANS_DETAILS");
				int totalItem = 0;
				long totalSum = 0;
				for (Map<String, Object> map : dataList) {
					try {
						Element transDetailEle = transDetailsEle.addElement("TRANS_DETAIL");
						transDetailEle.addElement("SN").addText(map.get("detailSn").toString());
						transDetailEle.addElement("E_USER_CODE");
						transDetailEle.addElement("ACCOUNT_TYPE").addText(PayConsts.YL_ACCOUNT_TYPE_BANKCARD);
						transDetailEle.addElement("BANK_CODE").addText(map.get("bankCode").toString());
						transDetailEle.addElement("ACCOUNT_NO").addText(map.get("bankAccountNo").toString());
						transDetailEle.addElement("ACCOUNT_NAME").addText(map.get("bankAccountName").toString());
						transDetailEle.addElement("PROVINCE").addText(map.get("bankProvince") == null ? "" : map.get("bankProvince").toString());
						transDetailEle.addElement("CITY").addText(map.get("bankCity") == null ? "" : map.get("bankCity").toString());
						transDetailEle.addElement("BANK_NAME").addText(map.get("bankName") == null ? "" : map.get("bankName").toString());
						transDetailEle.addElement("ACCOUNT_PROP").addText(PayConsts.YL_ACCOUNT_PROP_PERSONAL);
						String amount = (new BigDecimal(map.get("appointTransAmount").toString()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP)).toString();
						transDetailEle.addElement("AMOUNT").addText(amount); // 以分为单位
						transDetailEle.addElement("CURRENCY").addText(PayConsts.YL_CURRENCY_TYPE_CNY);
						transDetailEle.addElement("PROTOCOL");
						transDetailEle.addElement("PROTOCOL_USERID");
						transDetailEle.addElement("ID_TYPE");
						transDetailEle.addElement("ID");
						transDetailEle.addElement("TEL");
						transDetailEle.addElement("CUST_USERID");
						transDetailEle.addElement("RECKON_ACCOUNT");
						transDetailEle.addElement("REMARK").addText(map.get("businessId") == null ? "" : map.get("businessId").toString());
						transDetailEle.addElement("RESERVE1");
						transDetailEle.addElement("RESERVE2");
						totalSum += Integer.parseInt(amount);
						totalItem++;
					} catch (Exception e) {
						// LOG.error("交易编号=" + map.get("transactionId") +
						// "组装【代收付交易】xml报文出错", e);
						// continue;
						throw new RuntimeException("交易编号=" + map.get("transactionId") + "组装【代收付交易】xml报文出错", e);
					}
				}

				transSumEle.addElement("BUSINESS_CODE").addText(businessCode);
				// 银联网络商户ID
				transSumEle.addElement("MERCHANT_ID").addText(Constants.YL_MERCHANT_ID_COMPANY);
				String submitDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				transSumEle.addElement("SUBMIT_TIME").addText(submitDateStr);
				transSumEle.addElement("TOTAL_ITEM").addText(totalItem + "");
				transSumEle.addElement("TOTAL_SUM").addText(totalSum + "");
			} catch (Exception e) {
				// LOG.error("生成【代收付交易】请求xml报文出错", e);
				throw new RuntimeException("生成【代收付交易】请求xml报文出错", e);
			}
			retPayXmlData = doc.asXML();
		}
		return retPayXmlData;
	}

	/**
	 * @throws CryptException
	 * @Title: payRequestToYLServer @Description: 请求银联服务 @param @return
	 *         String @author liuyang @throws
	 */
	@SuppressWarnings("deprecation")
	public Map<String, Object> payRequestToYLServer(String requestXmlData) throws CryptException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		boolean isSuccess = false;
		String retInfo = "";
		// LOG.info("银联服务请求的xml报文为="+requestXmlData);
		if (StringUtils.isNotBlank(requestXmlData)) {
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = null;
			boolean bCompress = true;
			try {
				// 交易请求url
				if (bCompress) {
					postMethod = new PostMethod(Constants.YL_PAY_SERVER_COM_URL);
				} else {
					postMethod = new PostMethod(Constants.YL_PAY_SERVER_URL);
				}

				// 设置编码
				httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
				// 队请求数据签名
				requestXmlData = this.signMsg(requestXmlData);
				if (bCompress) {
					// 队请求数据进行压缩加密
					requestXmlData = compress(requestXmlData);
				}
				postMethod.setRequestEntity(new StringRequestEntity(requestXmlData));
				long start = System.currentTimeMillis();
				try {
					// 执行getMethod
					int statusCode = httpClient.executeMethod(postMethod);
					// 失败
					if (statusCode != HttpStatus.SC_OK) {
						// 服务请求失败
						// LOG.info("银联服务请求失败,PostMethod failed: " +
						// postMethod.getStatusLine());
						String charset = postMethod.getResponseCharSet();
						// 读取内容
						byte[] responseBody = postMethod.getResponseBody();
						// 处理内容
						String strResp = new String(responseBody, charset);
						// LOG.info("银联服务请求失败后，返回的相应信息=" + strResp);
						retInfo = "银联服务请求失败,statusCode=" + statusCode;
					} else {
						// 支付服务请求成功
						String charset = postMethod.getResponseCharSet();
						// 读取内容
						byte[] responseBody = postMethod.getResponseBody();
						String strResp = new String(responseBody, charset);

						// LOG.info("银联服务请求成功后，返回的相应信息="+strResp);
						if (bCompress) {
							// 对相应数据进行解压缩解密
							strResp = this.decompress(strResp);
						}
						// LOG.info("银联服务请求成功后，返回的相应信息还原后:" + strResp);
						isSuccess = true;
						retInfo = strResp;
						// 验签
						if (this.verifySign(strResp)) {
							// LOG.info("银联服务请求成功，验签正确，处理服务器返回的报文");
						} else {
							// LOG.info("银联服务请求成功，验签失败");
						}
					}
					// LOG.info("cost:"+(System.currentTimeMillis()-start));
				} catch (Exception e) {
					// LOG.error("银联服务请求失败", e);
					isSuccess = true; // 不确定银联是否接受交易请求，将交易状态按交易中处理
					retInfo = "银联服务请求失败" + e.toString();
				}
			} catch (Exception e) {
				// LOG.error("银联服务请求失败",e);
				retInfo = "银联服务请求失败" + e.toString();
			} finally {
				// 释放连接
				postMethod.releaseConnection();
			}
		}
		retMap.put("isSuccess", isSuccess);
		retMap.put("retInfo", retInfo);
		return retMap;
	}

	/**
	 * 验证签名信息
	 */
	public boolean verifySign(String strXML) {
		// 签名(代收付系统JDK环境使用GBK编码，商户使用签名包时需传送GBK编码集)
		Crypt crypt = new Crypt("gbk");
		String pathCer = Constants.YL_PAY_PDSCERT_CER_ADDR;
		int iStart = strXML.indexOf("<SIGNED_MSG>");
		if (iStart != -1) {
			int end = strXML.indexOf("</SIGNED_MSG>");
			String signedMsg = strXML.substring(iStart + 12, end);
			// LOG.info(signedMsg);
			String strMsg = strXML.substring(0, iStart) + strXML.substring(end + 13);
			// 调用签名包验签接口(原文,签名,公钥路径)
			try {
				if (crypt.verify(strMsg, signedMsg, pathCer)) {
					// LOG.info("verify ok");
					return true;
				} else {
					// LOG.info("verify error");
					return false;
				}
			} catch (CryptException e) {
				// LOG.error("验签失败",e);
			}
		}
		return true;
	}

	/**
	 * 解压数据 comment here
	 * 
	 * @param strData
	 * @return
	 * @since gnete-pds 0.0.0.1
	 */
	@SuppressWarnings("restriction")
	public String decompress(String strData) {
		String strRnt = strData;
		try {
			strRnt = this.decompresszip(new sun.misc.BASE64Decoder().decodeBuffer(strData));

		} catch (IOException e) {
			// LOG.error("解压数据出错",e);
		}
		return strRnt;
	}

	/**
	 * 将压缩后的 byte[] 数据解压缩
	 * 
	 * @param compressed
	 *            压缩后的 byte[] 数据
	 * @return 解压后的字符串
	 */
	@SuppressWarnings("unused")
	public final String decompresszip(byte[] compressed) {
		if (compressed == null)
			return null;

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed;
		try {
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			java.util.zip.ZipEntry entry = zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString("GBK");
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}

	/**
	 * 交易请求前数据签名
	 * 
	 * @param payRequestData
	 *            请求数据
	 * @return String 签名后的请求书数据
	 * @author liuyang
	 * @throws CryptException
	 */
	public String signMsg(String payRequestData) throws CryptException {
		String strRnt = "";
		// 签名文件路径
		String pathPfx = Constants.YL_PAY_PDSCERT_PFX_ADDR;
		// 签名密码
		String pfxPassword = Constants.YL_PAY_PDSCERT_PFX_PASSWORD;

		// 签名(代收付系统JDK环境使用GBK编码，商户使用签名包时需传送GBK编码集)
		Crypt crypt = new Crypt("gbk");
		String strMsg = payRequestData.replaceAll("<SIGNED_MSG></SIGNED_MSG>", "");
		// 调用签名包签名接口(原文,私钥路径,密码)
		String signedMsg = crypt.sign(strMsg, pathPfx, pfxPassword);
		strRnt = payRequestData.replaceAll("<SIGNED_MSG></SIGNED_MSG>", "<SIGNED_MSG>" + signedMsg + "</SIGNED_MSG>");
		return strRnt;
	}

	/**
	 * 压缩+base64加密数据
	 * 
	 * @param strData
	 *            需要加密的请求数据
	 *            是否压缩
	 * @return 压缩加密后的文件内容
	 */
	@SuppressWarnings("restriction")
	public String compress(String strData) {
		String strRnt = strData;
		strRnt = new sun.misc.BASE64Encoder().encode(this.compresszip(strData));
		return strRnt;
	}

	/**
	 * 压缩数据，压缩字符串为 byte[] 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[]
	 * b)方法
	 * 
	 * @param str
	 *            需要压缩的请求数据
	 * @return 压缩的文件内容 ，为字符串
	 */
	public final byte[] compresszip(String str) {
		if (str == null)
			return null;

		byte[] compressed;
		ByteArrayOutputStream out = null;
		java.util.zip.ZipOutputStream zout = null;

		try {
			out = new ByteArrayOutputStream();
			zout = new java.util.zip.ZipOutputStream(out);
			zout.putNextEntry(new java.util.zip.ZipEntry("0"));
			zout.write(str.getBytes("GBK"));
			zout.closeEntry();
			compressed = out.toByteArray();
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return compressed;
	}

	/**
	 * @Title: analysisRespXml @Description: 解析返回的xml数据 @param strResp
	 *         接口返回的xml数据 @author liuyang @return void @throws
	 */
	public Map<String, Object> analysisRespXml(String reqSn, String strResp) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		boolean isSuccess = false;
		String retInfo = "";

		if (StringUtils.isNotBlank(reqSn) && StringUtils.isNotBlank(strResp)) {
			Document doc = null;
			try {
				// 解析xml字符串生成文档模型
				doc = DocumentHelper.parseText(strResp);
				// 获取根节点
				Element root = doc.getRootElement();
				// 解析【info】节点,查看info节点的状态
				Element infoElement = root.element("INFO");
				String req_sn = "";
				String ret_code = "";
				String err_msg = "";

				req_sn = infoElement.elementTextTrim("REQ_SN");
				ret_code = infoElement.elementTextTrim("RET_CODE");
				err_msg = infoElement.elementTextTrim("ERR_MSG");

				if (reqSn.equals(req_sn)) {
					if (PayConsts.YL_DEAL_RET_CODE_SUCCESS.equals(ret_code)) {
						// 预约交易请求成功
						isSuccess = true;
						retInfo = err_msg;
					} else {
						// 预约交易请求成功
						isSuccess = false;
						retInfo = err_msg;
					}
				} else {
					// LOG.info("交易请求流水号与请求响应的流水号不符，交易请求流水号="+reqSn+",请求响应流水号="+req_sn);
					isSuccess = false;
					retInfo = "交易请求流水号与请求响应的流水号不符，交易请求流水号=" + reqSn + ",请求响应流水号=" + req_sn;
				}

				/*
				 * //接收返回的预约信息
				 * 
				 * if(ret_code.equals("0000")){ //预约成功 //开始解析《body》中xml数据
				 * Element bodyElement = root.element("BODY");
				 * System.out.println(bodyElement.getName()); //<RET_DETAIL> 节点
				 * Iterator<?> detailsIterator =
				 * bodyElement.element("RET_DETAILS").elementIterator(
				 * "RET_DETAIL"); Element detailElement = null; String sn = "";
				 * String retCode = ""; while(detailsIterator.hasNext()){
				 * detailElement = (Element) detailsIterator.next(); sn =
				 * detailElement.elementTextTrim("SN"); retCode =
				 * detailElement.elementTextTrim("RET_CODE");
				 * retMap.put("reqStatus","success"); retMap.put(sn, retCode); }
				 * }else{ //预约失败 retMap.put("reqStatus","faile"); }
				 */
			} catch (Exception e) {
				// LOG.error("解析预约请求返回的xml出错：",e);
				retInfo = "解析预约请求返回的xml出错：" + e.toString();
			}
		} else {
			// LOG.info("解析预约请求返回的xml失败，请求流水reqSn="+reqSn+",银联响应信息="+strResp);
			retInfo = "解析预约请求返回的xml失败，请求流水reqSn=" + reqSn + ",银联响应信息=" + strResp;
		}

		retMap.put("isSuccess", isSuccess);
		retMap.put("retInfo", retInfo);
		return retMap;
	}

}
