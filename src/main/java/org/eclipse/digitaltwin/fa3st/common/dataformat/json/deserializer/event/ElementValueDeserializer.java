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
package org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.core.internal.util.ReflectionHelper;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonFieldNames;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue}.
 */
public class ElementValueDeserializer extends StdDeserializer<ElementValue> {

    public ElementValueDeserializer() {
        this(null);
    }


    public ElementValueDeserializer(Class<ElementValue> type) {
        super(type);
    }


    private Class<?> getInlineTypeInfo(JsonNode root) {
        if (Objects.isNull(root) || !root.hasNonNull(JsonFieldNames.EVENT_MODELTYPE)) {
            return null;
        }
        String modelType = root.get(JsonFieldNames.EVENT_MODELTYPE).asText();
        Class<? extends SubmodelElement> aasType = (Class<? extends SubmodelElement>) ReflectionHelper.TYPES_WITH_MODEL_TYPE.stream()
                .filter(x -> Objects.equals(modelType, x.getSimpleName()))
                .filter(SubmodelElement.class::isAssignableFrom)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Missing type information '%s'",
                        JsonFieldNames.EVENT_MODELTYPE)));
        ((ObjectNode) root).remove(JsonFieldNames.EVENT_MODELTYPE);
        return ElementValueMapper.getValueClass(aasType);
    }


    @Override
    public ElementValue deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode root = parser.readValueAsTree();
        return (ElementValue) context.readTreeAsValue(root, getInlineTypeInfo(root));
    }

}
