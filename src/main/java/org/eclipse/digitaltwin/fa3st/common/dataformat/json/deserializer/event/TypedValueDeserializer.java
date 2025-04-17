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
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonFieldNames;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;


/**
 * Deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue}.
 */
public class TypedValueDeserializer extends StdDeserializer<TypedValue> {

    public TypedValueDeserializer() {
        this(null);
    }


    public TypedValueDeserializer(Class<TypedValue> type) {
        super(type);
    }


    private Datatype getDatatype(JsonNode root) {
        if (Objects.isNull(root) || !root.hasNonNull(JsonFieldNames.EVENT_DATATYPE)) {
            return null;
        }
        String dataType = root.get(JsonFieldNames.EVENT_DATATYPE).asText();
        ((ObjectNode) root).remove(JsonFieldNames.EVENT_DATATYPE);
        if (Datatype.isValid(dataType)) {
            return Datatype.fromName(dataType);
        }
        return null;
    }


    @Override
    public TypedValue deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode root = parser.readValueAsTree();
        Datatype datatype = getDatatype(root);
        if (Objects.isNull(datatype)) {
            throw new IOException("Unable to determine datatype");
        }
        if (root.isEmpty()) {
            return null;
        }
        try {
            return TypedValueFactory.create(datatype, root.get(JsonFieldNames.EVENT_VALUE).asText());
        }
        catch (ValueFormatException e) {
            throw new IOException(String.format("error deserializing typed value (datatype: %s, value %s", datatype.getName(), parser.getValueAsString()), e);
        }
    }

}
