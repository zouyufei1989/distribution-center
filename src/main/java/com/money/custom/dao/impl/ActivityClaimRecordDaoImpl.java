package com.money.custom.dao.impl;

import com.money.custom.dao.ActivityClaimRecordDao;
import com.money.custom.dao.AssignActivityDao;
import com.money.custom.entity.AssignActivityItem;
import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import com.money.h5.entity.H5GridRequestBase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "ActivityClaimRecord")
public class ActivityClaimRecordDaoImpl extends BaseDaoImpl implements ActivityClaimRecordDao {


}
