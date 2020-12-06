package com.money.custom.entity.request;

import com.money.custom.entity.BonusPlan;
import com.money.custom.entity.GoodsItem;

import java.util.Map;

public class QueryBonusPlanRequest extends QueryGridRequestBase {

    private BonusPlan bonusPlan = new BonusPlan();

    public BonusPlan getBonusPlan() {
        return bonusPlan;
    }

    public void setBonusPlan(BonusPlan bonusPlan) {
        this.bonusPlan = bonusPlan;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("bonusPlan", bonusPlan);
        return params;
    }

}
