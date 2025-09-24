package com.bytepype.oracle.redolog;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
public class RedoLogMapper {

    public void mapInto(RedoLog redoLog, ResultSet resultSet) throws SQLException {
        try{
            redoLog.setSqlRedo(resultSet.getString("SQL_REDO"));
            redoLog.setRsId(resultSet.getString("RS_ID"));
            redoLog.setSsn(getBigInteger("SSN", resultSet));
            redoLog.setCsf(getBigInteger("CSF", resultSet));
            redoLog.setRowNum(getBigInteger("RN", resultSet));
            redoLog.setScn(getBigInteger("SCN", resultSet));
            redoLog.setUsername(resultSet.getString("USERNAME"));
            redoLog.setClientId(resultSet.getString("CLIENT_ID"));
            redoLog.setTableName(resultSet.getString("TABLE_NAME"));
            redoLog.setSchemaName(resultSet.getString("SEG_OWNER"));
            redoLog.setTimestamp(resultSet.getDate("TIMESTAMP").getTime());
            redoLog.setXid(Optional.ofNullable(resultSet.getBytes("XID"))
                    .map(Base64.getEncoder()::encodeToString)
                    .orElse(null));
            redoLog.setOperation(Optional.ofNullable(resultSet.getString("OPERATION"))
                    .map(value -> value.replace(" ", "_"))
                    .map(Operation::valueOf)
                    .orElse(null));
        } catch(Exception e){
            log.error("Exception while mapping result-set to redo-log. Metadata is {}", extractMetadata(resultSet), e);
        }
    }

    private BigInteger getBigInteger(String columnName, ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(resultSet.getBigDecimal(columnName)).map(BigDecimal::toBigInteger).orElse(null);
    }

    private String extractMetadata(ResultSet resultSet) throws SQLException {
        final StringBuilder builder = new StringBuilder();
        final ResultSetMetaData metadata = resultSet.getMetaData();
        for(int index = 1; index <= metadata.getColumnCount(); index++){
            builder.append(metadata.getColumnLabel(index))
                    .append(" : ")
                    .append(resultSet.getObject(index))
                    .append(",");
        }
        return builder.toString();
    }

}
