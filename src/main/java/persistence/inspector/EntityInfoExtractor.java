package persistence.inspector;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntityInfoExtractor {

    public static List<Field> getEntityFields(Class<?> clazz) {
        try {
            return ClsssMetadataInspector.getFields(clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Field> getEntityFieldsForInsert(Class<?> clazz) {
        try {

            return getEntityFields(clazz).stream()
                    .filter(EntityInfoExtractor::isInsertTargetField)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static EntityColumnType getColumnType(Field field) {
        return EntityColumnType.get(field.getType());
    }

    private static boolean isInsertTargetField(Field field) {
        return EntityFieldInspector.isPersistable(field) && !EntityFieldInspector.isAutoIncrement(field);
    }

    public static String getIdColumnName(Class<?> clazz) {
        return getColumnName(ClsssMetadataInspector.getIdField(clazz));
    }
    public static String getColumnName(Field field) {
        return EntityFieldInspector.getColumnName(field);
    }

    public static String getTableName(Class<?> clazz) {
        if (ClsssMetadataInspector.hasAnnotation(clazz, jakarta.persistence.Table.class) && (!clazz.getAnnotation(Table.class).name().isBlank())) {

                return clazz.getAnnotation(Table.class).name();
        }
        return clazz.getSimpleName().toLowerCase();
    }

    public static Field getIdField(Class<?> clazz) {

        return ClsssMetadataInspector.getFields(clazz).stream()
                .filter(EntityInfoExtractor::isPrimaryKey)
                .findFirst().orElse(null);
    }

    public static Object getFieldValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNullable(Field field) {
        return EntityFieldInspector.hasAnnotation(field, Column.class) && field.getAnnotation(Column.class).nullable();
    }

    public static boolean isPrimaryKey(Field field) {
        return field.isAnnotationPresent(Id.class);
    }

    public static boolean isAutoIncrement(Field field) {
        return EntityFieldInspector.hasAnnotation(field, GeneratedValue.class);
    }




}
