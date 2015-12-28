package com.gqhmt.fss.pay.fuiou.factory;


import com.gqhmt.fss.pay.core.command.ThirdpartyCommand;
import com.gqhmt.fss.pay.core.factory.ThirdpartyAbstractFactory;
import com.gqhmt.fss.pay.fuiou.command.FuiouCommand;

/**
 * Created by yuyonf on 15/3/29.
 */
public class FuiouFactory extends ThirdpartyAbstractFactory {
    private ThirdpartyCommand command =  new FuiouCommand();;
    private static final ThirdpartyAbstractFactory factory = new FuiouFactory();


    @Override
    public ThirdpartyCommand getCommand() {
        return command;
    }


}
