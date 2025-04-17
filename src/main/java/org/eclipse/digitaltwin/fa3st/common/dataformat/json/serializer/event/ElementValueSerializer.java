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
package org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.event;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonFieldNames;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;


/**
 * Mixin for {@link org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue} when used in message bus events. As
 * in this case no context informatino is available it needs to be embedded when serialising.
 */
public class ElementValueSerializer extends StdSerializer<ElementValue> {

    private final transient JsonSerializer<Object> defaultSerializer;

    public ElementValueSerializer(JsonSerializer<Object> defaultSerializer) {
        this(ElementValue.class, defaultSerializer);
    }


    public ElementValueSerializer(Class<ElementValue> type, JsonSerializer<Object> defaultSerializer) {
        super(type);
        this.defaultSerializer = defaultSerializer;
    }


    @Override
    public void serialize(ElementValue value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (value != null) {
            generator.writeStartObject();
            generator.writeStringField(JsonFieldNames.EVENT_MODELTYPE, ElementValueMapper.getElementClass(value.getClass()).getSimpleName());
            StringWriter valueJson = new StringWriter();
            JsonGenerator valueGenerator = generator.getCodec().getFactory().createGenerator(valueJson);
            defaultSerializer.serialize(value, valueGenerator, provider);
            valueGenerator.flush();
            JsonNode contentNode = new ObjectMapper().readTree(valueJson.toString());
            if (Objects.nonNull(contentNode) && contentNode.isArray()) {
                provider.defaultSerializeField(JsonFieldNames.EVENT_VALUE, contentNode, generator);
            }
            if (Objects.nonNull(contentNode)
                    && contentNode.size() == 1
                    && contentNode.has(JsonFieldNames.EVENT_VALUE)
                    && contentNode.fields().next().getValue().isObject()) {
                for (Iterator<Map.Entry<String, JsonNode>> i = ((ObjectNode) contentNode.fields().next().getValue()).fields(); i.hasNext();) {
                    Map.Entry<String, JsonNode> entry = i.next();
                    provider.defaultSerializeField(entry.getKey(), entry.getValue(), generator);
                }
            }
            else if (Objects.nonNull(contentNode)) {
                for (Iterator<Map.Entry<String, JsonNode>> i = contentNode.fields(); i.hasNext();) {
                    Map.Entry<String, JsonNode> entry = i.next();
                    provider.defaultSerializeField(entry.getKey(), entry.getValue(), generator);
                }
            }
            generator.writeEndObject();
        }
    }

}
