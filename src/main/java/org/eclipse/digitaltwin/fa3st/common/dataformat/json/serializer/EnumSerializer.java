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
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.eclipse.digitaltwin.fa3st.common.util.ReflectionHelper;


/**
 * Enum Serializer applying the AAS naming strategy.
 */
public class EnumSerializer extends JsonSerializer<Enum> {

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            if (ReflectionHelper.ENUMS.contains(value.getClass())) {
                gen.writeString(org.eclipse.digitaltwin.aas4j.v3.dataformat.core.internal.serialization.EnumSerializer.serializeEnumName(value.name()));
            }
            else {
                provider.findValueSerializer(Enum.class).serialize(value, gen, provider);
            }
        }
    }
}
