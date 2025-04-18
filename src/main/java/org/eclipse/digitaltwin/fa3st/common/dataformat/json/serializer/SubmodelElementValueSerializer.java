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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.eclipse.digitaltwin.fa3st.common.util.ElementValueHelper;


/**
 * Serializer for {@link org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement}. Converts the submodel element zo value
 * before
 * serializing.
 */
public class SubmodelElementValueSerializer extends StdSerializer<SubmodelElement> {

    public SubmodelElementValueSerializer() {
        this(null);
    }


    public SubmodelElementValueSerializer(Class<SubmodelElement> type) {
        super(type);
    }


    @Override
    public void serialize(SubmodelElement value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (value != null && ElementValueHelper.isSerializableAsValue(value.getClass())) {
            try {
                generator.writeStartObject();
                provider.defaultSerializeField(value.getIdShort(), ElementValueMapper.toValue(value), generator);
                generator.writeEndObject();
            }
            catch (ValueMappingException e) {
                provider.reportMappingProblem(e, "error mapping submodel element to value");
            }
        }
        else {
            provider.defaultSerializeValue(value, generator);
        }
    }

}
