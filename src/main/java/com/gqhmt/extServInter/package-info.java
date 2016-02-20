/**
 * Filename:    com.gqhmt.extServInter.package-info
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/18 13:52
 * Description:
 * <p>对外服务接口包,定义对外服务接口,所有接口通用参数如下
 * <table>
 *     <th>
 *         <td>参数名称</td>
 *         <td>参数代码</td>
 *         <td>长度</td>
 *         <td>约束</td>
 *         <td>说明</td>
 *         <td>示例</td>
 *     </th>
 *     <tr>
 *         <td>系统代码</td>
 *         <td>mchn</td>
 *         <td>15</td>
 *         <td>M</td>
 *         <td></td>
 *         <td></td>
 *     </tr>
 *     <tr>
 *         <td>交易流水号</td>
 *         <td>seq_no</td>
 *         <td>30</td>
 *         <td>M</td>
 *         <td>商户唯一标识</td>
 *         <td></td>
 *     </tr>
 *     <tr>
 *         <td>签名</td>
 *         <td>signature</td>
 *         <td>60</td>
 *         <td>M</td>
 *         <td>按参数名字母排序后的值用“|”线连接起来的明文+盐值(商户密钥)，然后用MD5(或者rsa,先使用md5,未来考虑是否更换rsa,或者通过配置,两种都可以)值</td>
 *         <td></td>
 *     </tr>
 * </table>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/18  于泳      1.0     1.0 Version
 */
package com.gqhmt.extServInter;