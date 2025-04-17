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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonFieldNames;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Extent;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level;
import org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue;
import org.eclipse.digitaltwin.fa3st.common.util.EncodingHelper;


/**
 * Serializer for {@link org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue}.
 */
public class BlobValueSerializer extends ModifierAwareSerializer<BlobValue> {

    public BlobValueSerializer() {
        this(null);
    }


    public BlobValueSerializer(Class<BlobValue> type) {
        super(type);
    }


    @Override
    public void serialize(BlobValue value, JsonGenerator generator, SerializerProvider provider, Level level, Extent extend) throws IOException, JsonProcessingException {
        if (value != null) {
            generator.writeStartObject();
            generator.writeStringField(JsonFieldNames.BLOB_VALUE_CONTENT_TYPE, value.getContentType());
            if (extend == Extent.WITH_BLOB_VALUE) {
                generator.writeStringField(JsonFieldNames.BLOB_VALUE_VALUE, new String(EncodingHelper.base64Encode(value.getValue())));
            }
            generator.writeEndObject();
        }
    }
}
