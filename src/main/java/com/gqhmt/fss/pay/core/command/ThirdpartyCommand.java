package com.gqhmt.fss.pay.core.command;

import com.gqhmt.funds.architect.order.entity.FundOrderEntity;

/**
 * Created by yuyonf on 15/3/29.
 */
public interface ThirdpartyCommand {

    public CommandResponse command(String commandEnum, FundOrderEntity fundOrderEntity, Object... object);
}
