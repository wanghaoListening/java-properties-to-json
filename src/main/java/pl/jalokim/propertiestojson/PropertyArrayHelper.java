package pl.jalokim.propertiestojson;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static pl.jalokim.propertiestojson.Constants.ARRAY_END_SIGN;
import static pl.jalokim.propertiestojson.Constants.ARRAY_START_SIGN;
import static pl.jalokim.propertiestojson.Constants.EMPTY_STRING;
import static pl.jalokim.propertiestojson.Constants.SIMPLE_ARRAY_DELIMETER;
import static pl.jalokim.propertiestojson.JsonObjectsTraverseResolver.INDEXES_PATTERN;

@Getter
public class PropertyArrayHelper {

    private List<Integer> indexesInArrayField;
    private String arrayFieldName;

    public PropertyArrayHelper(String field) {
        arrayFieldName = getNameFromArray(field);
        indexesInArrayField = getIndexesFromArrayField(field);
    }

    public static String getNameFromArray(String fieldName) {
        return fieldName.replaceFirst(INDEXES_PATTERN + "$", EMPTY_STRING);
    }

    public static List<Integer> getIndexesFromArrayField(String fieldName) {
        String indexesAsText = fieldName.replace(getNameFromArray(fieldName), EMPTY_STRING);
        String[] indexesAsTextArray = indexesAsText
                .replace(ARRAY_START_SIGN, EMPTY_STRING)
                .replace(ARRAY_END_SIGN, SIMPLE_ARRAY_DELIMETER)
                .replaceAll("\\s", EMPTY_STRING)
                .split(SIMPLE_ARRAY_DELIMETER);
        List<Integer> indexes = new ArrayList<>();
        for (String indexAsText : indexesAsTextArray) {
            indexes.add(Integer.valueOf(indexAsText));
        }
        return indexes;
    }
}
