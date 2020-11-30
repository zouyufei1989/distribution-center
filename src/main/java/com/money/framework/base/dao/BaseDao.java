package com.money.framework.base.dao;

import com.money.custom.entity.enums.IEnumKeyValue;
import com.money.custom.entity.request.ChangeStatusBaseRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    <T> void addBatch(List<T> items);

    default <T> List<T> selectSearchList(QueryGridRequestBase request) {
        throw new UnsupportedOperationException("selectSearchList");
    }

    int selectSearchListCount(QueryGridRequestBase request);

    default <T> T findById(String id) {
        throw new UnsupportedOperationException("findById");
    }

    default <T> int add(T item) {return 0;}

    default <T> int edit(T item) {return 0;}

    <T extends IEnumKeyValue> int changeStatus(ChangeStatusBaseRequest<T> request);

    /**
     * Retrieve a single row mapped from the statement key
     *
     * @param <T>       the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @return Mapped object
     */
    default <T> T selectOne(String statement) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     *
     * @param <T>       the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    default <T> T selectOne(String statement, Object parameter) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     *
     * @param <E>       the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @return List of mapped object
     */
    default <E> List<E> selectList(String statement) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     *
     * @param <E>       the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return List of mapped object
     */
    default <E> List<E> selectList(String statement, Object parameter) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter,
     * within the specified row bounds.
     *
     * @param <E>       the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds Bounds to limit object retrieval
     * @return List of mapped object
     */
    default <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * Eg. Return a of Map[Integer,Author] for selectMap("selectAuthors","id")
     *
     * @param <K>       the returned Map keys type
     * @param <V>       the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param mapKey    The property to use as key for each value in the list.
     * @return Map containing key pair datadao.
     */
    default <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     *
     * @param <K>       the returned Map keys type
     * @param <V>       the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey    The property to use as key for each value in the list.
     * @return Map containing key pair datadao.
     */
    default <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     *
     * @param <K>       the returned Map keys type
     * @param <V>       the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey    The property to use as key for each value in the list.
     * @param rowBounds Bounds to limit object retrieval
     * @return Map containing key pair datadao.
     */
    default <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter
     * using a {@code ResultHandler}.
     *
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param handler   ResultHandler that will handle each retrieved row
     */
    default void select(String statement, Object parameter, ResultHandler handler) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a single row mapped from the statement
     * using a {@code ResultHandler}.
     *
     * @param statement Unique identifier matching the statement to use.
     * @param handler   ResultHandler that will handle each retrieved row
     */
    default void select(String statement, ResultHandler handler) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter
     * using a {@code ResultHandler} and {@code RowBounds}
     *
     * @param statement Unique identifier matching the statement to use.
     * @param rowBounds RowBound instance to limit the query results
     * @param handler   ResultHandler that will handle each retrieved row
     */
    default void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        throw new UnsupportedOperationException("select");
    }

    /**
     * Execute an insert statement.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the insert.
     */
    default int insert(String statement) {
        throw new UnsupportedOperationException("insert");
    }

    /**
     * Execute an insert statement with the given parameter object. Any generated
     * autoincrement values or selectKey entries will modify the given parameter
     * object properties. Only the number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    default int insert(String statement, Object parameter) {
        throw new UnsupportedOperationException("insert");
    }

    /**
     * Execute an update statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the update.
     */
    default int update(String statement) {
        throw new UnsupportedOperationException("update");
    }

    /**
     * Execute an update statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    default int update(String statement, Object parameter) {
        throw new UnsupportedOperationException("update");
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the delete.
     */
    default int delete(String statement) {
        throw new UnsupportedOperationException("delete");
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete.
     */
    default int delete(String statement, Object parameter) {
        throw new UnsupportedOperationException("delete");
    }

    default int execute(String sql) {
        throw new UnsupportedOperationException("excute");
    }

    default boolean existByTableName(String tableName) {
        throw new UnsupportedOperationException("existByTableName");
    }

    default int createTableFromSql(String createTableSql) {
        throw new UnsupportedOperationException("createTableFromSql");
    }

    default Map<String, String> getCreateTableSql(String tableName) {
        throw new UnsupportedOperationException("getCreateTableSql");
    }

    default int dropByTableName(String tableName) {
        throw new UnsupportedOperationException("dropByTableName");
    }

}
