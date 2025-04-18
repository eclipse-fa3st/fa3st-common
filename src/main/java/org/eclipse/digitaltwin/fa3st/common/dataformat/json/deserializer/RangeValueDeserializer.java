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
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonFieldNames;
import org.eclipse.digitaltwin.fa3st.common.model.value.RangeValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.RangeValue}.
 */
public class RangeValueDeserializer extends ContextAwareElementValueDeserializer<RangeValue> {

    public RangeValueDeserializer() {
        this(null);
    }


    public RangeValueDeserializer(Class<RangeValue> type) {
        super(type);
    }


    @Override
    public RangeValue deserializeValue(JsonNode node, DeserializationContext context) throws IOException, JacksonException {
        RangeValue.Builder<?> builder = RangeValue.builder();
        if (node.has(JsonFieldNames.RANGE_VALUE_MIN)) {
            builder = builder.min(context.readTreeAsValue(node.get(JsonFieldNames.RANGE_VALUE_MIN), TypedValue.class));
        }
        if (node.has(JsonFieldNames.RANGE_VALUE_MAX)) {
            builder = builder.max(context.readTreeAsValue(node.get(JsonFieldNames.RANGE_VALUE_MAX), TypedValue.class));
        }
        return builder.build();
    }

}
