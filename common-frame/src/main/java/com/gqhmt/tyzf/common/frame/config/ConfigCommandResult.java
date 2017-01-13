package com.gqhmt.tyzf.common.frame.config;

import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by zhou on 2016/10/21.
 * Description:执行结果
 */
public class ConfigCommandResult {

    private ArrayList<String> results = null;

    /**
     * 构造函数
     */
    public ConfigCommandResult() {
        results = new ArrayList<String>();
    }

    public void add(String result) {
        if (result == null)
            return;
        if (result.trim().length() == 0)
            return;
        results.add(result);
    }

    /**
     * 将结果转成byte数组
     * @return
     */
    public byte[] toBytes() {
        StringBuilder sb = new StringBuilder();
        byte[] b = {};
        for (int i = 0, r = results.size(); i < r; i++) {
            sb.append(results.get(i) + System.getProperty("line.separator"));
        }
        try {
            b = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.getInstance().error(e);
        }
        return b;
    }
}
