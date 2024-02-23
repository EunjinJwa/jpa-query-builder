package persistence.sql.ddl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import persistence.inspector.EntityInfoExtractor;
import persistence.sql.dml.DMLQueryBuilder;
import persistence.sql.dml.DMLQueryFormatter;

public class MySQLDDLQueryBuilder implements DDLQueryBuilderIf {

    private final MySQLDDLQueryConstructor queryConstructor = new MySQLDDLQueryConstructor();

    @Override
    public String createTableQuery(EntityTable entityTable) {

        DDLQueryFormatter.createTableQuery(entityTable.getTableName());

        return createTableQuery(entityTable.getTableName(), createColumnClause(clazz), createPrimaryKeyClause(clazz));
    }

    private String createTableQuery(String tableName, String columnClause, String primaryKeyClause) {

        return String.format("CREATE TABLE %s (%s%s)", tableName, columnClause, primaryKeyClause);
    }

    @Override
    public String dropTableQuery(EntityTable entityTable) {

        return createDropTableQuery(getTableName(entityTable));
    }

    private String createDropTableQuery(String tableName) {

        return String.format("DROP TABLE %s", tableName);
    }

    private String getTableName(Class<?> clazz) {

        return EntityInfoExtractor.getTableName(clazz);
    }

    private String createColumnClause(Class<?> clazz) {
        List<String> columnClauses = EntityInfoExtractor.getEntityFields(clazz).stream()
            .map(this::createColumnClause)
            .collect(Collectors.toList());

        return String.join(", ", columnClauses);
    }

    private String createColumnClause(Field field) {
        String columnTypeFormat = "%s %s %s";

        return String.format(columnTypeFormat,
                EntityInfoExtractor.getColumnName(field),
                EntityInfoExtractor.getColumnType(field).getMysqlType(),
                getColumnProperty(field)
        );
    }

    private String createPrimaryKeyClause(Class<?> clazz) {
        final String primaryKeyClauseFormat = ", PRIMARY KEY (%s)";

        return String.format(primaryKeyClauseFormat, getPrimaryKeyColumnName(clazz));
    }

    private String getPrimaryKeyColumnName(Class<?> clazz) {

        return EntityInfoExtractor.getColumnName(EntityInfoExtractor.getIdField(clazz));
    }

    private String getColumnProperty(Field field) {
        StringBuilder columnProperty = new StringBuilder();
        if (!EntityInfoExtractor.isNullable(field)) {
            columnProperty.append("NOT NULL ");
        }
        if (EntityInfoExtractor.isAutoIncrement(field)) {
            columnProperty.append("AUTO_INCREMENT ");
        }
        return columnProperty.toString();
    }

}

