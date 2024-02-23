package persistence.sql.ddl;

public class MySQLColumnProperty implements ColumnProperty {

    @Override
    public String getColumnName() {
        return null;
    }

    @Override
    public String getDataType() {
        return null;
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    @Override
    public String getNotNull() {
        return "NOT NULL";
    }

    @Override
    public String getGeneratedValue() {
        return "AUTO_INCREMENT";
    }

    @Override
    public String getComment() {
        return null;
    }
}
