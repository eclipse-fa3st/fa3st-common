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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.typing.TypeInfo;


/**
 * Generic deserializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue}. It only determines
 * target type from current typeInfo context and forwards deserialization.
 */
public class ElementValueDeserializer extends StdDeserializer<ElementValue> {

    public ElementValueDeserializer() {
        this(null);
    }


    public ElementValueDeserializer(Class<ElementValue> type) {
        super(type);
    }


    @Override
    public ElementValue deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        TypeInfo typeInfo = ContextAwareElementValueDeserializer.getTypeInfo(context);
        return (ElementValue) context.readValue(parser, typeInfo.getType());
    }

}
