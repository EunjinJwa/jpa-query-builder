package persistence.sql.ddl;

import persistence.sql.dml.DMLQueryFormatter;

public class DDLQueryFormatter {

    private DDLQueryFormatter() {

    }

    public String createTableQuery(String tableName, String columnClause, String primaryKeyClause) {

        return String.format("CREATE TABLE %s (%s%s)", tableName, columnClause, primaryKeyClause);
    }




}
