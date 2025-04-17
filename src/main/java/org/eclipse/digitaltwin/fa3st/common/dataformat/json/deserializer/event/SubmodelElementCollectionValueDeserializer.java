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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Map;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.ContextAwareElementValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue}.
 */
public class SubmodelElementCollectionValueDeserializer extends ContextAwareElementValueDeserializer<SubmodelElementCollectionValue> {

    public SubmodelElementCollectionValueDeserializer() {
        this(null);
    }


    public SubmodelElementCollectionValueDeserializer(Class<SubmodelElementCollectionValue> type) {
        super(type);
    }


    @Override
    public SubmodelElementCollectionValue deserializeValue(JsonNode node, DeserializationContext context) throws IOException, JacksonException {
        return new SubmodelElementCollectionValue.Builder()
                .values(deserializeChildren(node, context, ElementValue.class))
                .build();
    }


    @Override
    protected <T extends ElementValue> Map<String, T> deserializeChildren(JsonNode node, DeserializationContext context, Class<T> type) throws IOException {
        return EventDeserializationHelper.deserializeChildren(node, context, type);
    }

}
