package com.gqhmt.channelDeploy;

import com.gqhmt.fss.architect.channelDeploy.entity.ChannelOrgEntity;
import com.gqhmt.fss.architect.channelDeploy.service.ChannelService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class test {

    @Resource
    private ChannelService channelService;

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Test
    public void getChannelOrgEntityList(){
        List<ChannelOrgEntity> channelEntityList = new ArrayList<ChannelOrgEntity>();
        System.out.println(channelService);
        channelEntityList = channelService.getChannelOrgList();
        System.out.println(channelEntityList);
    }
}
