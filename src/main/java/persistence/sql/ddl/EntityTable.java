package persistence.sql.ddl;

import java.util.List;

public class EntityTable {

    private String entityName;
    private String tableName;

    private List<EntityColumn> columns;

    public String getEntityName() {
        return entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<EntityColumn> getColumns() {
        return columns;
    }
}
