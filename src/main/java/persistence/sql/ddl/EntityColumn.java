package persistence.sql.ddl;

import jakarta.persistence.GenerationType;

public class EntityColumn {
    private String columnName;
    private String columnType;
    private boolean isId;
    private GenerationType generationType;
    private boolean nullable;
    private boolean insertable;
    private boolean updatable;
    private String columnDefinition;
    private int length;

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean isNullable() {
        return nullable;
    }

    public boolean isInsertable() {
        return insertable;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public String getColumnDefinition() {
        return columnDefinition;
    }

    public int getLength() {
        return length;
    }

    public boolean isId() {
        return isId;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }
}
