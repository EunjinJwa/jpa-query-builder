package persistence.sql.ddl;

public interface DDLQueryBuilderIf {

    String createTableQuery(EntityTable entityTable);

    String dropTableQuery(EntityTable entityTable);

}
