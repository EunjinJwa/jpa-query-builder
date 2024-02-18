package persistence.sql.ddl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.entity.Person;
import persistence.entity.Person1;
import persistence.entity.Person2;

import static org.junit.jupiter.api.Assertions.*;

class DDLQueryBuilderTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DDLQueryBuilder ddlQueryBuilder = DDLQueryBuilder.getInstance();

    @Test
    @DisplayName("요구사항1_Person Create Table Query")
    void createTableQueryReq1() {
        String query = ddlQueryBuilder.createTableQuery(Person1.class);

        assertEquals("CREATE TABLE person1 (id BIGINT , name VARCHAR(255) , age INT , PRIMARY KEY (id))", query);
    }

    @Test
    @DisplayName("요구사항2_Person Create Table Query")
    void createTableQueryReq2() {
        String query = ddlQueryBuilder.createTableQuery(Person2.class);

        assertEquals("CREATE TABLE person (id BIGINT AUTO_INCREMENT , nick_name VARCHAR(255) , old INT , email VARCHAR(255) NOT NULL , PRIMARY KEY (id))", query);
    }

    @Test
    @DisplayName("요구사항3_Person Create Table Query2")
    void createTableQueryReq3() {
        String query = ddlQueryBuilder.createTableQuery(Person.class);

        assertEquals("CREATE TABLE users (id BIGINT AUTO_INCREMENT , nick_name VARCHAR(255) , old INT , email VARCHAR(255) NOT NULL , PRIMARY KEY (id))", query);
    }

    @Test
    @DisplayName("요구사항4_Person Drop Table Query")
    void dropTableQuery() {
        String query = ddlQueryBuilder.dropTableQuery(Person.class);

        assertEquals("DROP TABLE users", query);
    }

}
