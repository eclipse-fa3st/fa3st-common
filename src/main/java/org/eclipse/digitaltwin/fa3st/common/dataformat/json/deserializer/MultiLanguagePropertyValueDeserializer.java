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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.value.MultiLanguagePropertyValue;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.MultiLanguagePropertyValue}.
 */
public class MultiLanguagePropertyValueDeserializer extends ContextAwareElementValueDeserializer<MultiLanguagePropertyValue> {

    public MultiLanguagePropertyValueDeserializer() {
        this(null);
    }


    public MultiLanguagePropertyValueDeserializer(Class<MultiLanguagePropertyValue> type) {
        super(type);
    }


    @Override
    public MultiLanguagePropertyValue deserializeValue(JsonNode node, DeserializationContext context) throws IOException, JacksonException {
        MultiLanguagePropertyValue.Builder builder = MultiLanguagePropertyValue.builder();
        if (Objects.nonNull(node) && node.isArray()) {
            for (var entryNode: node) {
                if (!entryNode.isObject() || entryNode.size() != 1) {
                    context.reportBadDefinition(MultiLanguagePropertyValue.class, "each entry must be a JSON object with language as single key and text as value");
                }
                var entry = entryNode.fields().next();
                builder.value(entry.getKey(), entry.getValue().textValue());
            }
        }
        return builder.build();
    }

}
