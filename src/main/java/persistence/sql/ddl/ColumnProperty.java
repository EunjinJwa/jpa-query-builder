package persistence.sql.ddl;

public interface ColumnProperty {

    String getColumnName();
    String getDataType();
    String getDefaultValue();
    String getNotNull();
    String getGeneratedValue();
    String getComment();

}
