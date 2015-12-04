package com.gqhmt.fss.pay.core.factory;


import com.gqhmt.fss.pay.core.command.ThirdpartyCommand;

/**
 *
 */
public abstract class ThirdpartyAbstractFactory {


    public abstract ThirdpartyCommand getCommand();
}
