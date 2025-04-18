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
package org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.OperationVariable;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;


/**
 * Serializer for a collection of {@link org.eclipse.digitaltwin.aas4j.v3.model.OperationVariable}s.
 */
public class OperationVariableCollectionValueSerializer extends StdSerializer<Collection<OperationVariable>> {

    public OperationVariableCollectionValueSerializer() {
        this(null);
    }


    public OperationVariableCollectionValueSerializer(Class<Collection<OperationVariable>> type) {
        super(type);
    }


    @Override
    public void serialize(Collection<OperationVariable> values, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (Objects.nonNull(values)) {
            generator.writeStartObject();
            for (var value: values) {
                if (Objects.nonNull(value.getValue())) {
                    try {
                        provider.defaultSerializeField(value.getValue().getIdShort(), ElementValueMapper.toValue(value.getValue()), generator);
                    }
                    catch (ValueMappingException e) {
                        throw new JsonGenerationException("error serializing OperationVariable as valueOnly", generator);
                    }
                }
            }
            generator.writeEndObject();
        }
    }
}
