package com.gqhmt.pay.core.factory;


import com.gqhmt.pay.core.command.ThirdpartyCommand;

/**
 *
 */
public abstract class ThirdpartyAbstractFactory {


    public abstract ThirdpartyCommand getCommand();
}
