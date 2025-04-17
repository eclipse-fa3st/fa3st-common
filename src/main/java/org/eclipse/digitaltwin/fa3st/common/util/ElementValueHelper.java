/**
 * Copyright (c) 2025 the Eclipse FA³ST Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.digitaltwin.fa3st.common.util;

import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.digitaltwin.aas4j.v3.model.AnnotatedRelationshipElement;
import org.eclipse.digitaltwin.aas4j.v3.model.BasicEventElement;
import org.eclipse.digitaltwin.aas4j.v3.model.DataElement;
import org.eclipse.digitaltwin.aas4j.v3.model.Entity;
import org.eclipse.digitaltwin.aas4j.v3.model.OperationVariable;
import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceElement;
import org.eclipse.digitaltwin.aas4j.v3.model.RelationshipElement;
import org.eclipse.digitaltwin.aas4j.v3.model.Submodel;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementCollection;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementList;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.Page;
import org.eclipse.digitaltwin.fa3st.common.model.value.DataElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;


/**
 * Helper class for {@link org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue}.
 */
public class ElementValueHelper {

    private static final Type COLLECTION_GENERIC_TOKEN;
    private static final Type MAP_GENERIC_TOKEN;

    static {
        try {
            COLLECTION_GENERIC_TOKEN = TypeToken.of(Collection.class.getMethod("iterator").getGenericReturnType()).resolveType(Iterator.class.getTypeParameters()[0]).getType();
            MAP_GENERIC_TOKEN = Map.class.getMethod("get", Object.class).getGenericReturnType();
        }
        catch (NoSuchMethodException e) {
            throw new IllegalStateException("static initialization of ElementValueHelper failed", e);
        }
    }

    private ElementValueHelper() {}


    /**
     * Checks if an object can be converted to an element value.
     *
     * @param obj which should be checked
     * @return true if the object can be converted to an element value, false otherwise
     */
    public static boolean isValueOnlySupported(Object obj) {
        if (obj == null) {
            return true;
        }
        Class<?> type = obj.getClass();
        if (type.isArray()) {
            return Stream.of((Object[]) obj).allMatch(ElementValueHelper::isValueOnlySupported);
        }
        if (Collection.class.isAssignableFrom(type)) {
            return ((Collection) obj).stream().allMatch(ElementValueHelper::isValueOnlySupported);
        }
        if (Map.class.isAssignableFrom(type)) {
            return ((Map) obj).values().stream().allMatch(ElementValueHelper::isValueOnlySupported);
        }
        if (Page.class.isAssignableFrom(type)) {
            return ((Page) obj).getContent().stream().allMatch(ElementValueHelper::isValueOnlySupported);
        }
        return isValueOnlySupported(type);
    }


    /**
     * Checks if an object of a specific class can be converted to an element value.
     *
     * @param type which should be checked
     * @return true if an object of the type can be converted to an element value, false otherwise
     */
    public static boolean isValueOnlySupported(Class<?> type) {
        if (isSerializableAsValue(type)
                || Submodel.class.isAssignableFrom(type)
                || ElementValue.class.isAssignableFrom(type)) {
            return true;
        }
        if (type.isArray()) {
            return isValueOnlySupported(TypeToken.of(type).getComponentType());
        }
        if (Collection.class.isAssignableFrom(type)) {
            return isValueOnlySupported(TypeToken.of(type).resolveType(COLLECTION_GENERIC_TOKEN).getRawType());
        }
        if (Map.class.isAssignableFrom(type)) {
            return isValueOnlySupported(TypeToken.of(type).resolveType(MAP_GENERIC_TOKEN).getRawType());
        }
        return false;
    }


    /**
     * Checks if an object of a specific class can be converted to an element value.
     *
     * @param type which should be checked
     * @return true if an object of the type can be converted to an element value, false otherwise
     */
    public static boolean isSerializableAsValue(Class<?> type) {
        return DataElement.class.isAssignableFrom(type)
                || AnnotatedRelationshipElement.class.isAssignableFrom(type)
                || BasicEventElement.class.isAssignableFrom(type)
                || Entity.class.isAssignableFrom(type)
                || ReferenceElement.class.isAssignableFrom(type)
                || RelationshipElement.class.isAssignableFrom(type)
                || SubmodelElementCollection.class.isAssignableFrom(type)
                || SubmodelElementList.class.isAssignableFrom(type);
    }


    /**
     * Checks if given value is a valid {@link DataElementValue}.
     *
     * @param value the value to check
     * @return true if value is valid value of type {@link DataElementValue}, false otherwise
     */
    public static boolean isValidDataElementValue(Object value) {
        return value == null || DataElementValue.class.isAssignableFrom(value.getClass());
    }


    /**
     * Converts a list of {@link org.eclipse.digitaltwin.aas4j.v3.model.OperationVariable} to a map of
     * {@link org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue} with their idShort as keys.
     *
     * @param variables list of operation variables
     * @return the corresponding map of element values and their idShorts
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException if mapping of element values
     *             fails
     */
    public static Map<String, ElementValue> toValueMap(List<OperationVariable> variables) throws ValueMappingException {
        return variables.stream()
                .filter(Objects::nonNull)
                .map(OperationVariable::getValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        x -> x.getIdShort(),
                        LambdaExceptionHelper.rethrowFunction(
                                x -> ElementValueMapper.toValue(x))));
    }
}
