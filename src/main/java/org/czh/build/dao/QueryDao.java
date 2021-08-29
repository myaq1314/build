package org.czh.build.dao;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import org.czh.build.entity.config.ConfigEntity;
import org.czh.build.entity.eo.ColumnMetaEO;
import org.czh.build.entity.eo.TableMetaEO;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
public class QueryDao {

    public List<TableMetaEO> queryTableMetaList(@NotNullTag ConfigEntity configEntity) {
        EmptyAssert.isNotNull(configEntity);

        return executeQuery(
                configEntity,
                rs -> {
                    try {
                        List<TableMetaEO> resultEOList = new ArrayList<>(((ResultSetImpl) rs).getRows().size());
                        while (rs.next()) {
                            TableMetaEO tableMetaEO = new TableMetaEO();
                            tableMetaEO.setTableName(rs.getString("TABLE_NAME"));
                            tableMetaEO.setTableComment(rs.getString("TABLE_COMMENT"));
                            resultEOList.add(tableMetaEO);
                        }
                        return resultEOList;
                    } catch (SQLException e) {
                        throw new RuntimeException("处理数据库查询结果异常");
                    }
                },
                " SELECT `TABLE_NAME`, `TABLE_COMMENT` FROM information_schema.TABLES WHERE `TABLE_SCHEMA` = ? ",
                configEntity.getDbConfig().getSchema()
        );
    }

    public List<ColumnMetaEO> queryColumnMetaList(@NotNullTag ConfigEntity configEntity,
                                                  @NotBlankTag String tableName) {
        EmptyAssert.isNotNull(configEntity);
        EmptyAssert.isNotBlank(tableName);

        return executeQuery(
                configEntity,
                rs -> {
                    try {
                        List<ColumnMetaEO> resultEOList = new ArrayList<>(((ResultSetImpl) rs).getRows().size());
                        while (rs.next()) {
                            ColumnMetaEO columnMetaEO = new ColumnMetaEO();
                            columnMetaEO.setColumnName(rs.getString("COLUMN_NAME"));
                            columnMetaEO.setDataType(rs.getString("DATA_TYPE"));
                            columnMetaEO.setColumnType(rs.getString("COLUMN_TYPE"));
                            columnMetaEO.setIsNullable(rs.getString("IS_NULLABLE"));
                            columnMetaEO.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
                            columnMetaEO.setCharacterMaximumLength(rs.getString("CHARACTER_MAXIMUM_LENGTH"));
                            columnMetaEO.setNumericPrecision(rs.getString("NUMERIC_PRECISION"));
                            columnMetaEO.setNumericScale(rs.getString("NUMERIC_SCALE"));
                            columnMetaEO.setColumnKey(rs.getString("COLUMN_KEY"));
                            columnMetaEO.setExtra(rs.getString("EXTRA"));
                            columnMetaEO.setColumnComment(rs.getString("COLUMN_COMMENT"));
                            resultEOList.add(columnMetaEO);
                        }
                        return resultEOList;
                    } catch (SQLException e) {
                        throw new RuntimeException("处理数据库查询结果异常");
                    }
                },
                " SELECT "
                        + " c.`COLUMN_NAME`, "
                        + " c.`DATA_TYPE`, "
                        + " c.`COLUMN_TYPE`, "
                        + " c.`IS_NULLABLE`, "
                        + " c.`COLUMN_DEFAULT`, "
                        + " c.`CHARACTER_MAXIMUM_LENGTH`, "
                        + " c.`NUMERIC_PRECISION`, "
                        + " c.`NUMERIC_SCALE`, "
                        + " c.`COLUMN_KEY`, "
                        + " c.`EXTRA`, "
                        + " c.`COLUMN_COMMENT`  "
                        + " FROM `information_schema`.`COLUMNS` AS c "
                        + " WHERE c.`TABLE_NAME` = ? "
                        + " AND c.`TABLE_SCHEMA` = ? "
                        + " ORDER BY c.`ORDINAL_POSITION` ASC ",
                tableName,
                configEntity.getDbConfig().getSchema()
        );
    }

    private <T> List<T> executeQuery(@NotNullTag ConfigEntity configEntity,
                                     @NotNullTag Function<ResultSet, List<T>> function,
                                     @NotBlankTag String sql,
                                     String... params) {
        Connection connection = null; // 连接
        PreparedStatement pstmt = null; // 预编译
        ResultSet rs = null; // 结果集

        try {
            connection = getConnection(configEntity);
            pstmt = connection.prepareStatement(sql);
            if (EmptyValidate.isNotEmpty(params)) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setString(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            if (EmptyValidate.isNull(rs)) {
                return null;
            }

            return function.apply(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("查询数据库失败");
        } finally {
            close(rs, pstmt, connection);
        }
    }

    private Connection getConnection(ConfigEntity configEntity) throws ClassNotFoundException, SQLException {
        String driverClassName = "com.mysql.jdbc.Driver";    //启动驱动
        String url = configEntity.getDbConfig().getDriverUrl();
        String username = configEntity.getDbConfig().getUsername();
        String password = configEntity.getDbConfig().getPassword();
        Class.forName(driverClassName); //执行驱动
        return DriverManager.getConnection(url, username, password); //获取连接
    }

    private void close(AutoCloseable... autoCloseables) {
        if (EmptyValidate.isEmpty(autoCloseables)) {
            return;
        }

        for (AutoCloseable autoCloseable : autoCloseables) {
            try {
                if (autoCloseable != null) {
                    autoCloseable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (autoCloseable != null) {
                    try {
                        autoCloseable.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
