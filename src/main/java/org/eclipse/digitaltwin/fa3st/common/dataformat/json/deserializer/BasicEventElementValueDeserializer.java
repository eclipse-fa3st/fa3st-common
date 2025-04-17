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
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonFieldNames;
import org.eclipse.digitaltwin.fa3st.common.model.value.BasicEventElementValue;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.BasicEventElementValue}.
 */
public class BasicEventElementValueDeserializer extends ContextAwareElementValueDeserializer<BasicEventElementValue> {

    public BasicEventElementValueDeserializer() {
        this(null);
    }


    public BasicEventElementValueDeserializer(Class<BasicEventElementValue> type) {
        super(type);
    }


    @Override
    public BasicEventElementValue deserializeValue(JsonNode node, DeserializationContext context) throws IOException, JacksonException {
        BasicEventElementValue.Builder builder = new BasicEventElementValue.Builder();
        if (node.has(JsonFieldNames.BASIC_EVENT_ELEMENT_OBSERVED)) {
            builder.observed(context.readTreeAsValue(node.get(JsonFieldNames.BASIC_EVENT_ELEMENT_OBSERVED), Reference.class));
        }
        return builder.build();
    }

}
