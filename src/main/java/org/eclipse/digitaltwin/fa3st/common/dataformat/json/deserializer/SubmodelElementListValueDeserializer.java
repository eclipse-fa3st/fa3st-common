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
package org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementListValue;
import org.eclipse.digitaltwin.fa3st.common.typing.TypeInfo;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementListValue}.
 */
public class SubmodelElementListValueDeserializer extends ContextAwareElementValueDeserializer<SubmodelElementListValue> {

    public SubmodelElementListValueDeserializer() {
        this(null);
    }


    public SubmodelElementListValueDeserializer(Class<SubmodelElementListValue> type) {
        super(type);
    }


    @Override
    public SubmodelElementListValue deserializeValue(JsonNode node, DeserializationContext context) throws IOException {
        return new SubmodelElementListValue.Builder()
                .values(deserializeChildrenInternal(node, context))
                .build();
    }


    private <T extends ElementValue> List<T> deserializeChildrenInternal(JsonNode node, DeserializationContext context) throws IOException {
        List<T> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        TypeInfo typeInfo = getTypeInfo(context);
        if (typeInfo == null) {
            throw new IllegalArgumentException("no type information given in deserialization context");
        }
        if (!SubmodelElementListValue.class.equals(typeInfo.getType()) || !node.isArray()) {
            return result;
        }
        Iterator<JsonNode> iterator = node.elements();
        TypeInfo genericChildTypeInfo = (TypeInfo) typeInfo.getElements().get(null);
        if (Objects.isNull(genericChildTypeInfo)) {
            if (typeInfo.getElements().size() < node.size()) {
                throw new IllegalArgumentException(String.format(
                        "trying to deserialize SubmodelElementList with %d elements but found only type information for %d elements",
                        node.size(),
                        typeInfo.getElements().size()));
            }
            int i = 0;
            while (iterator.hasNext()) {
                JsonNode child = iterator.next();
                TypeInfo concreteChildTypeInfo = (TypeInfo) typeInfo.getElements().get(Integer.toString(i));
                result.add((T) context.setAttribute(VALUE_TYPE_CONTEXT, concreteChildTypeInfo)
                        .readTreeAsValue(child, concreteChildTypeInfo.getType()));
                i++;
            }
        }
        else {
            while (iterator.hasNext()) {
                JsonNode child = iterator.next();
                result.add((T) context.setAttribute(VALUE_TYPE_CONTEXT, genericChildTypeInfo)
                        .readTreeAsValue(child, genericChildTypeInfo.getType()));
            }
        }
        return result;
    }

}
