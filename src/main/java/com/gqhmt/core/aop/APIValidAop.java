package com.gqhmt.core.aop;

import com.github.pagehelper.PageHelper;
import com.gqhmt.annotations.*;
import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.GqPageInfo;
import com.gqhmt.core.util.*;
import com.gqhmt.extServInter.dto.PageSuperDto;
import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.fss.architect.order.entity.FssSeqOrderEntity;
import com.gqhmt.fss.architect.order.service.FssSeqOrderService;
import org.apache.commons.beanutils.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

/**
 * Filename:    com.gqhmt.core.aop.APIValidAop
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 16:08
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
@Component
@Aspect
public class APIValidAop {


    @Resource
    private FssSeqOrderService fssSeqOrderService;

    public APIValidAop() {
    }

    @Pointcut("execution(* com.gqhmt.extServInter.service.*.impl.*.execute(..))")
    public void point(){

    }


    @Around("point()")
    public Object validAround(ProceedingJoinPoint joinPoint) {

        Response response = null;
        String code = "90099999";
        SuperDto dto = null;
        Object targetClass = null;
        String methodName = null;
        try {
            dto = getArgsDto(joinPoint);
            targetClass = joinPoint.getTarget();
            methodName = joinPoint.getSignature().getName();
            //校验商户
            this.validMch(dto);
            //签名校验
            validSignature(targetClass,methodName,dto);
            //交易类型校验
            this.validTradeType(targetClass,methodName,dto);
            //数据校验
            this.validData(dto);
            //生成交易订单
            this.generate(dto);
            //查询分页设置
            generateAutoPage(targetClass,methodName,dto);
            response = (Response)joinPoint.proceed();
        } catch (Throwable throwable) {
            LogUtil.debug(this.getClass(),throwable);
            String codeTmp = throwable.getMessage();
            if(codeTmp != null && codeTmp.matches("[0-9]*")){
                String codeValue = Application.getInstance().getDictName(codeTmp == null?"":codeTmp);
                if(codeValue != null && !"".equals(codeValue)){
                    code = codeTmp;
                }
            }
        }
        if(response == null){
            response = new Response();
            response.setResp_code(code);
        }

        try {
            GenerateBeanUtil.GenerateClassInstance(response,dto);
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
        }

        String resCode = response.getResp_code();
        if(resCode != null && Integer.parseInt(resCode) == 0){
            resCode = "00000000";
            response.setResp_code(resCode);
        }
        String resMsg = Application.getInstance().getDictName(response.getResp_code());
        //处理成功返回值
        response.setResp_msg(resMsg);


        generateAutoPage(targetClass,methodName,response);
        //更改订单结果
        this.callbackOrder(response,dto);

        return response;
    }

    /**
     * 数据校验
     * @param dto
     */
    private void validData(Object dto) throws FssException {
        Class dtoClass =  dto.getClass();
        Class superDtoClass = dtoClass.getSuperclass();
        Field[] fields  = dtoClass.getDeclaredFields();
        Field[] superFields= superDtoClass.getDeclaredFields();
        for(Field field:superFields){
            String name = field.getName();
            //空值校验
            validIsNull(field,dto,"get"+name.substring(0,1).toUpperCase()+name.substring(1));
            validMoney(field,dto,"get"+name.substring(0,1).toUpperCase()+name.substring(1));
        }
        for(Field field:fields){
            String name = field.getName();
            //空值校验
            validIsNull(field,dto,"get"+name.substring(0,1).toUpperCase()+name.substring(1));
            validMoney(field,dto,"get"+name.substring(0,1).toUpperCase()+name.substring(1));
        }
    }

    /**
     * 商户校验
     * @param dto
     * @return
     * @throws FssException
     */
    private String validMch(SuperDto dto) throws FssException {
        String  result = "90099999";
        try {
            Class superDtoClass = getEntityClass(dto,SuperDto.class);
            Field superField= superDtoClass.getDeclaredField("mchn");
            //校验是否为空
            validIsNull(superField,dto,"getMchn");
            if (!Application.getInstance().existsMchn(dto.getMchn())){
                throw new FssException("90008102");
            }
            //校验权限 使用dubbo,此功能暂时不做
            //校验ip白名单,黑名单 使用dubbo,此功能暂时不做
            //签名校验,使用dubbo ,此处暂时不做

        } catch (NoSuchFieldException e) {
            throw  new FssException("90099998",e);
        }
        return result;
    }

    private void validSignature(Object obj ,String methodName,SuperDto dto) throws FssException {
        Method method = FssBeanUtil.findMethod(obj.getClass(),methodName,SuperDto.class);
        APISignature signatureAno = method.getAnnotation(APISignature.class);
        if(signatureAno == null){
            return;
        }
        /* String mchn = dto.getMchn();
        String seqNo = dto.getSeq_no();
        String tradeType = dto.getTrade_type();
        String key = Application.getInstance().getMechKey(mchn);
        
        String signature = dto.getSignature();

        String validSignature = Encriptor.getMD5(mchn+"|"+seqNo+"|"+tradeType+"|"+key);

//        if (signature == null || !signature.equals(validSignature)){
//            throw new FssException("90008302");
//        }
        if (signature == null || !signature.equals(validSignature)){
            throw new FssException("90008302");
        }*/

    }

    /**
     * 空值校验
     * @param superField
     * @param obj
     * @param methodName
     * @throws FssException
     */
    private void validIsNull(Field superField,Object obj,String methodName) throws FssException {
        APIValidNull api = superField.getAnnotation(APIValidNull.class);
        if(api == null )return;

        try {
            Object value = MethodUtils.invokeMethod(obj,methodName,null);
            if( value == null){
                throw  new FssException(api.errorCode());
            }
        } catch (NoSuchMethodException e) {
            throw new FssException("90099998",e);
        } catch (IllegalAccessException e) {
            throw new FssException("90099998",e);
        } catch (InvocationTargetException e) {
            throw new FssException("90099998",e);
        }

    }

    /**
     * 交易金额校验
     * @param superField
     * @param obj
     * @param methodName
     * @throws FssException
     */
    private void validMoney(Field superField,Object obj,String methodName) throws FssException {
        APIValid apiValid = superField.getAnnotation(APIValid.class);
        if(apiValid == null){
            return;
        }
        APIValidType apiValidType = apiValid.type();
        if(APIValidType.MONEY != apiValidType && apiValidType!=APIValidType.MONEY_ZERO) return;
        Object value = null;
        try {
            value = MethodUtils.invokeMethod(obj,methodName,null);
            if( value == null) return;

            long money = 0;

            //金额判断,必须大于0
            if(value instanceof BigDecimal){
                BigDecimal bigDecimal = (BigDecimal) ((BigDecimal) value).multiply(new BigDecimal(100));
                money = bigDecimal.longValue();
            }else if(value instanceof  Long){
                money = value == null ? 0:(long) value;
            }else if(value instanceof  String){
                BigDecimal bigDecimal = new BigDecimal(value.toString()).multiply(new BigDecimal(100));
                money = bigDecimal.longValue();
            }else{
                throw  new FssException("90099006");
            }

            if(APIValidType.MONEY == apiValidType && money <=0){
                throw  new FssException(apiValid.errorCode());
            }
            if(APIValidType.MONEY_ZERO == apiValidType && money <0){
                throw  new FssException(apiValid.errorCode());
            }

            //单位判断,小数点只能2位
            if(value instanceof BigDecimal){
                String moneyStr = ((BigDecimal) value).toPlainString();
                String[] moneySplit = moneyStr.split("\\.");
                if(moneySplit.length<2 || moneySplit[1].length()>2){
                    throw  new FssException("90004016");
                }
            }else if(value instanceof  String){
                String moneyStr = value.toString();
                String[] moneySplit = moneyStr.split("\\.");
                if(moneySplit.length<2 || moneySplit[1].length()>2){
                    throw  new FssException("90004016");
                }
            }

        } catch (NoSuchMethodException e) {
            throw new FssException("90099998",e);
        } catch (IllegalAccessException e) {
            throw new FssException("90099998",e);
        } catch (InvocationTargetException e) {
            throw new FssException("90099998",e);
        }


    }




    /**
     * 获取类类型
     * @param obj
     * @param target
     * @return
     * @throws FssException
     */

    public Class getEntityClass(Object obj,Class target) throws FssException {
        Class orgClass = obj instanceof Class ? (Class) obj: obj.getClass();
        if(target == null){
            return orgClass;
        }else if( target.getName().equals(orgClass.getName())){
            return orgClass;
        }else if("java.lang.Object".equals(orgClass.getName())){
            throw  new FssException("90099998");
        }else{
            return this.getEntityClass(orgClass.getSuperclass(),target);
        }
    }

    /**
     * 获取参数dto,如果不存在,就返回系统异常
     * @param joinPoint
     * @return
     * @throws FssException
     */

    private SuperDto getArgsDto(ProceedingJoinPoint joinPoint) throws FssException {
        Object[] objects = joinPoint.getArgs();
        for(Object obj:objects){
            if(obj instanceof SuperDto){
                return (SuperDto) obj;
            }
        }
        throw new FssException("90099998");
    }


    /**
     * 生成交易订单
     * @param dto
     * @throws Exception
     */
    private void generate(final SuperDto dto) throws Exception {
        FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,dto);
        fssSeqOrderEntity.setTradeType(Application.getInstance().getDictParentKey(dto.getTrade_type()));
        fssSeqOrderEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
        fssSeqOrderEntity.setTradeParam(JsonUtil.getInstance().getJson(dto));
        dto.setFssSeqOrderEntity(fssSeqOrderEntity);
        fssSeqOrderService.save(fssSeqOrderEntity);
    }

    /**
     * 交易完成,更新订单状态
     * @param response
     * @param dto
     */
    private void callbackOrder(Response response, SuperDto dto){
        FssSeqOrderEntity fssSeqOrderEntity = dto.getFssSeqOrderEntity();
        if (fssSeqOrderEntity == null){
            return;
        }
        fssSeqOrderEntity.setRespCode(response.getResp_code());
        fssSeqOrderEntity.setRespMsg(response.getResp_msg());
        fssSeqOrderEntity.setState(Integer.parseInt(response.getResp_code())==0 ? 10030002:10030003);
        try {
            fssSeqOrderService.update(fssSeqOrderEntity);
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
        }
    }


    /**
     * API分页控制
     * @param obj
     * @param methodName
     * @param dto
     */
    private void generateAutoPage(Object obj,String  methodName,SuperDto dto){
        Class class1 = obj.getClass();
        try {
            Method method = class1.getMethod(methodName,SuperDto.class);
            AutoPage autoPage =  method.getAnnotation(AutoPage.class);
            if (null != autoPage){
                Integer pageNum = 0;
                Integer pageSize = 10;
                if (dto instanceof PageSuperDto){
                    PageSuperDto pageSuperDto = (PageSuperDto) dto;
                    pageNum = pageSuperDto.getPageNum();
                    pageSize = pageSuperDto.getPageSize();
                }
                if(pageNum == null){
                    pageNum = 0;
                }
                if(pageSize == null){
                    pageSize = 10;
                }
                PageHelper.startPage(pageNum, pageSize);

            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 分页返回结果处理
     * @param obj
     * @param methodName
     * @param response
     */
    private void generateAutoPage(Object obj,String  methodName,Response response){
        Class class1 = obj.getClass();
        try {
            Method method = class1.getMethod(methodName,SuperDto.class);
            AutoPage autoPage =  method.getAnnotation(AutoPage.class);
            if (null != autoPage){
                if(response instanceof QueryListResponse){
                    QueryListResponse q = (QueryListResponse) response;
                    Object plain = q.getPlain();
                    if(plain instanceof List){
                        q.setPlain(new GqPageInfo((List)plain));
                    }
                }

            }
        } catch (NoSuchMethodException e) {
            LogUtil.error(this.getClass(),e);
        }

    }


    private void validTradeType(Object obj,String methodName,SuperDto dto) throws FssException {
        Class class1 = obj.getClass();
        String tradeType = dto.getTrade_type();
        boolean isSuccess = false;
        try {
            Method method = class1.getMethod(methodName,SuperDto.class);
            APITradeTypeValid apiValidType = method.getAnnotation(APITradeTypeValid.class);
            if(apiValidType == null){
                return ;
            }
            String  value = apiValidType.value();
            String  type = apiValidType.filterType();
            String  filter = type == null || "".equals(type) ?"" : Application.getInstance().getDictOrderValue(type);
            String  tradeFilter = apiValidType.mchnFilter();
            String mchnFilter =tradeFilter == null || "".equals(tradeFilter)?"": Application.getInstance().getDictOrderValue(dto.getMchn()+"_"+tradeFilter);
            String validType = value+","+mchnFilter+","+filter;
            if(!validType.contains(tradeType)){
                throw new FssException("90004020");
            }

        } catch (NoSuchMethodException e) {
            LogUtil.error(this.getClass(),e);
        }
    }
}