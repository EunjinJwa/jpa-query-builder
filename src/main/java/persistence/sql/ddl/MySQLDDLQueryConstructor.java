package persistence.sql.ddl;

import jakarta.persistence.GenerationType;

public class MySQLDDLQueryConstructor extends EntityTable {

    private MySQLDDLQueryConstructor() {

    }

    public String createTableQuery() {

        return DDLQueryFormatter.createTableQuery(getTableName(), getColumnsClause(), getPrimaryKeyClause(entityTable));
    }

    private Object getColumnsClause() {


    }

    private String createColumnClause(EntityColumn entityColumn) {
        String columnTypeFormat = "%s %s %s";

        return String.format(columnTypeFormat,
            entityColumn.getColumnName(),
            entityColumn.getColumnType(),
                entityColumn.getColumnProperty(),
                EntityInfoExtractor.getColumnName(field),
                EntityInfoExtractor.getColumnType(field).getMysqlType(),
                getColumnProperty(field)
        );
    }

    private String createColumnProperty(EntityColumn entityColumn) {
        StringBuilder columnProperty = new StringBuilder();
        if (entityColumn.isNullable()) {
            columnProperty.append("NOT NULL ");
        }
        if (entityColumn.getGenerationType() == GenerationType.IDENTITY) {
            columnProperty.append("AUTO_INCREMENT ");
        }
        return entityColumn.getColumnProperty();
    }


}
