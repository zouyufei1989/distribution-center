package com.money.framework.base.dao.impl;

import com.google.common.collect.Lists;
import com.money.custom.entity.enums.IEnumKeyValue;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.request.ChangeStatusBaseRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.BaseDao;
import com.money.framework.base.exception.CustomSpecException;
import com.money.framework.util.EnumUtils;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Repository("baseDaoImpl")
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

    @Override
    @Autowired
    @Qualifier("sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Transactional
    @Override
    public <T> void addBatch(List<T> items) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }

        for (List<T> partition : Lists.partition(items, 1000)) {
            insert("addBatch", partition);
        }
    }

    @Override
    public <T> List<T> selectSearchList(QueryGridRequestBase request) {
        List<T> result = selectList("selectSearchList", request.buildParams());
        if (CollectionUtils.isEmpty(result)) {
            result = Collections.EMPTY_LIST;
        }
        return result;
    }

    @Override
    public int selectSearchListCount(QueryGridRequestBase request) {
        return selectOne("selectSearchListCount", request.buildParams());
    }

    @Override
    public <T> T findById(String id) {
        return selectOne("findById", id);
    }

    @Override
    public <T> int add(T item) {
        return insert("add", item);
    }

    @Override
    public <T> int edit(T item) {
        return update("edit", item);
    }

    @Override
    public <T extends IEnumKeyValue> int changeStatus(ChangeStatusBaseRequest<T> request) {
        checkStatusLegal(request);
        return update("changeStatus", request.buildParams());
    }

    protected <T extends IEnumKeyValue> void checkStatusLegal(ChangeStatusBaseRequest<T> request) {
        Class<T> statusEnumCls = request.getEnumClass();
        Optional<IEnumKeyValue> opt = EnumUtils.getByValue(statusEnumCls, request.getStatus());
        if (!opt.isPresent()) {
            throw  CustomSpecException.businessError(ResponseCodeEnum.ILLEGAL_STATUS);
        }
    }

    @Override
    public <T> T selectOne(String sqlId) {
        return getSqlSession().selectOne(namespaceSqlId(sqlId));
    }

    @Override
    public <T> T selectOne(String sqlId, Object parameter) {
        return getSqlSession().selectOne(namespaceSqlId(sqlId), parameter);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String sqlId, String mapKey) {
        return getSqlSession().selectMap(namespaceSqlId(sqlId), mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String sqlId, Object parameter, String mapKey) {
        return getSqlSession().selectMap(namespaceSqlId(sqlId), parameter, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String sqlId, Object parameter, String mapKey, RowBounds rowBounds) {
        return getSqlSession().selectMap(namespaceSqlId(sqlId), parameter, mapKey, rowBounds);
    }

    @Override
    public <E> List<E> selectList(String sqlId) {
        return getSqlSession().selectList(namespaceSqlId(sqlId));
    }

    @Override
    public <E> List<E> selectList(String sqlId, Object parameter) {
        return getSqlSession().selectList(namespaceSqlId(sqlId), parameter);
    }

    @Override
    public <E> List<E> selectList(String sqlId, Object parameter, RowBounds rowBounds) {
        return getSqlSession().selectList(namespaceSqlId(sqlId), parameter, rowBounds);
    }

    @Override
    public void select(String sqlId, ResultHandler handler) {
        getSqlSession().select(namespaceSqlId(sqlId), handler);
    }

    @Override
    public void select(String sqlId, Object parameter, ResultHandler handler) {
        getSqlSession().select(namespaceSqlId(sqlId), parameter, handler);
    }

    @Override
    public void select(String sqlId, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        getSqlSession().select(namespaceSqlId(sqlId), parameter, rowBounds, handler);
    }

    @Override
    public int insert(String sqlId) {
        return getSqlSession().insert(namespaceSqlId(sqlId));
    }

    @Override
    public int insert(String sqlId, Object parameter) {
        return getSqlSession().insert(namespaceSqlId(sqlId), parameter);
    }

    @Override
    public int update(String sqlId) {
        return getSqlSession().update(namespaceSqlId(sqlId));
    }

    @Override
    public int update(String sqlId, Object parameter) {
        return getSqlSession().update(namespaceSqlId(sqlId), parameter);
    }

    @Override
    public int delete(String sqlId) {
        return getSqlSession().delete(namespaceSqlId(sqlId));
    }

    @Override
    public int delete(String sqlId, Object parameter) {
        return getSqlSession().delete(namespaceSqlId(sqlId), parameter);
    }

    @Override
    public int execute(String sql) {
        Map<String, Object> params = new HashMap<>();
        params.put("sql", sql);
        SqlSession sqlSession = getSqlSession();
        int a = sqlSession.update("Base.executeSql", params);
        return a;
    }

    @Override
    public boolean existByTableName(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        return getSqlSession().selectOne("Base.existByTableName", params) != null;
    }

    @Override
    public int createTableFromSql(String createTableSql) {
        return execute(createTableSql);
    }

    @Override
    public Map<String, String> getCreateTableSql(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        return getSqlSession().selectOne("Base.getCreateTableSql", params);
    }

    @Override
    public int dropByTableName(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        return getSqlSession().update("Base.dropByTableName", params);
    }

    @Override
    public void checkDaoConfig() throws IllegalArgumentException {
        Assert.notNull(getSqlSession(), "Property 'sqlSessionFactory' is required");
    }

    private SQLContext sqlContext = getClass().getAnnotation(SQLContext.class);

    private String namespaceSqlId(String sqlId) {
        String namespace = sqlContext.nameSpace();
        if (namespace == null || "".equals(namespace.trim())) {
            return namespace;
        }
        return namespace + "." + sqlId;
    }

    protected Map<String, Object> buildParams(ParamKeyValue... kvs) {
        Map<String, Object> params = new HashMap<>();

        for (ParamKeyValue kv : kvs) {
            params.put(kv.getKey(), kv.getValue());
        }

        return params;
    }

    protected static class ParamKeyValue {
        private String key;
        private Object value;

        public ParamKeyValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }

}
