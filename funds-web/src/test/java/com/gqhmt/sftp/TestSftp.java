package com.gqhmt.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gqhmt.extServInter.callback.loan.PaymentCallback;
import com.gqhmt.extServInter.dto.loan.RepaymentChildDto;
import com.gqhmt.extServInter.dto.loan.RepaymentResponse;
import com.gqhmt.fss.architect.card.entiry.FssCardBinEntity;
import com.gqhmt.fss.architect.card.service.FssCardBinService;
import com.gqhmt.fss.architect.depos.service.FssDeposService;
import com.gqhmt.util.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.depos.txt.CreateTXT;
import com.gqhmt.fss.architect.depos.txt.ReadTXTFile;
import com.gqhmt.fss.architect.depos.utils.DeposDownLoadutils;
import com.gqhmt.fss.architect.depos.utils.DeposuploadUtils;

public class TestSftp extends TestService {
    @Resource
    private DeposuploadUtils sFTPuploadUtils;
    @Resource
    private CreateTXT createTXT;
    @Resource
    private DeposDownLoadutils deposDownLoadutils;
    @Resource
    private ReadTXTFile readTXTFile;
    @Resource
    private PaymentCallback paymentCallback;
    @Resource
    private FssDeposService fssDeposService;
    private static int num = 1000;

    /**
     * author:jhz
     * time:2016年5月16日
     * function：1.P2P个人平台开户文件
     */
    @Test
    public void createAccount() {
//			String createAccountFileTXT = createTXT.createAccountFileTXT();
        try {
//				sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check", createAccountFileTXT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage() + "-----");
        }

    }

    /**
     * author:jhz
     * time:2016年5月16日
     * function：P2P商户交易（包括冻结，动账，解冻）TXT文件
     */
    @Test
    public void createCreditInfoTxt() throws FssException {
//        String createCreditInfoCVS = createTXT.createCreditInfoTXT();
        try {
//            sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check", createCreditInfoCVS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * author:jhz
     * time:2016年5月16日
     * function：P2P标的财务汇总文件
     */
    @Test
    public void createFinanceSumTXT() throws FssException {
//        String createFinanceSumTXT = createTXT.createFinanceSumTXT();
//        try {
//            sFTPuploadUtils.upLoadFile("/check/" + CommonUtil.dateTostring(new Date()) + "/", createFinanceSumTXT);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

    /**
     * author:jhz
     * time:2016年5月16日
     * function：P2P项目信息
     */
    @Test
    public void createProjectInfoTXT() throws FssException {
        fssDeposService.createProjectInfoTXT();
    }

    /**
     * author:jhz
     * time:2016年5月16日
     * function：P2P项目信息回盘
     */
    @Test
    public void downBidback() throws Exception {
        String	filePath="F:\\P2P_PWXM_BACK_"+ DateUtil.dateTostring(new Date())+".txt";
        deposDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/backcheck/"+"P2P_PWXM_BACK_"+DateUtil.dateTostring(new Date())+".txt",filePath );
//        filePathsftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/backcheck/P2P_PWXM_BACK_20160601.txt",filePath );
//        sftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/overcheck/20160602_P2P_PWXM_20160602_1041.txt",filePath );
//        readTXTFile.insertProjectCallBacks(filePath);

    }
    /**
     * author:jhz
     * time:2016年5月16日
     * function：P2P项目信息回盘
     */
    @Test
    public void downBidbacks() throws Exception {
        String	filePath="F:\\20160606_P2P_PWXM_20160606_1132.txt";
        deposDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/overcheck/20160606_P2P_PWXM_20160606_1132.txt",filePath );
//        filePathsftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/backcheck/P2P_PWXM_BACK_20160601.txt",filePath );
//        sftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/overcheck/20160602_P2P_PWXM_20160602_1041.txt",filePath );
//        readTXTFile.insertProjectCallBacks(filePath);

    }

    /**
     * author:jhz
     * time:2016年5月16日
     * function：7.P2P标的财务汇总审核回盘文件（银行返回）
     *
     * @throws Exception
     */
    @Test
    public void downSum() throws Exception {
//        String filePath = "F:\\sumAudit" + CommonUtil.dateTostring(new Date()) + ".txt";
//        sftpDownLoadutils.downLoadFile("/overcheck/" + CommonUtil.dateTostring(new Date()) + "/" + "sum.txt", filePath);
//        readTXTFile.creatSumAudits(filePath);

    }

    public String process(MultipartFile file, HttpServletRequest req, HttpServletResponse res) {
        try {
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String path = req.getRealPath("/") + File.separator + "upload" + File.separator + UUID.randomUUID() + fileSuffix;
            file.transferTo(new File(path));
            MultipartHttpServletRequest multiPartReq = (MultipartHttpServletRequest) req;
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void dyWithdraw() throws Exception {
        try {
            RepaymentResponse re = paymentCallback.getCallBack("15651537LYPV", "2016052618081399201");
            List<RepaymentChildDto> repay_list = re.getRepay_list();
            for (RepaymentChildDto epaymentChildDto : repay_list) {
                System.out.println(epaymentChildDto.getReal_repay_amt() + "*******" + re.getMchn() + epaymentChildDto.getAmt());

            }
        } catch (FssException e) {
            e.printStackTrace();
        }
    }
    @Resource
    private FssCardBinService fssCardBinService;
    @Test
    public void dyWithdraaw() throws Exception {
        try {
            // 创建对Excel工作簿文件的引用
            HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream("F://bankcard.xls"));
            // 在Excel文档中，第一张工作表的缺省索引是0
            // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFSheet sheet = wookbook.getSheet("sheet");
            //获取到Excel文件中的所有行数
            List<FssCardBinEntity> list=new ArrayList<FssCardBinEntity>();
            int rows = sheet.getPhysicalNumberOfRows();
            //遍历行­
            for (int i = 0; i < rows; i++) {
                if (i>2){
                // 读取左上端单元格
                HSSFRow row = sheet.getRow(i);
                // 行不为空­
                if (row != null) {
                    //获取到Excel文件中的所有的列
                    int cells = row.getPhysicalNumberOfCells();
                    String value = "";
                    //遍历列­
                    for (int j = 0; j < cells; j++) {
                        if (j == 0 || j == 1 || j == 2 || j == 13 || j == 14 || j == 16) {
                            //获取到列的值
                            HSSFCell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_FORMULA:
                                        break;
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        int p=(int)cell.getNumericCellValue();
                                        String a=String.valueOf(p);
                                        value += a+ ",";

                                        break;
                                    case HSSFCell.CELL_TYPE_STRING:
                                        value += cell.getStringCellValue() + ",";
                                        break;
                                    default:
                                        value += "0";
                                        break;
                                }
                            }
                        }
                    }
                    // 将数据插入到mysql数据库中­
                    String[] val = value.split(",");
                    FssCardBinEntity entity = new FssCardBinEntity();
                    entity.setId(null);
                    entity.setBankName(val[0]);
                    entity.setOrganCode(val[1]);
                    entity.setCardName(val[2]);
                    double  b=Double.parseDouble(val[3]);
                    entity.setLength((int)b);
                    entity.setTakeValue(val[4]);
                    entity.setCardType(val[5]);
                    entity.setCreateTime(new Date());
                    entity.setModifyTime(new Date());
                    list.add(entity);
                }
            }}
            fssCardBinService.insetList(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//	/*
}
