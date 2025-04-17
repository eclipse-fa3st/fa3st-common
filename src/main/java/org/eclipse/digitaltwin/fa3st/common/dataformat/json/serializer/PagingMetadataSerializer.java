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
import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.PagingMetadata;
import org.eclipse.digitaltwin.fa3st.common.util.EncodingHelper;
import org.eclipse.digitaltwin.fa3st.common.util.Fa3stConstants;


/**
 * Serializer for {@code PagingMetadata}.
 */
public class PagingMetadataSerializer extends StdSerializer<PagingMetadata> {

    public PagingMetadataSerializer() {
        this(null);
    }


    public PagingMetadataSerializer(Class<PagingMetadata> t) {
        super(t);
    }


    @Override
    public void serialize(PagingMetadata value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        if (Objects.nonNull(value.getCursor())) {
            generator.writeStringField(Fa3stConstants.CURSOR, EncodingHelper.base64UrlEncode(value.getCursor()));
        }
        generator.writeEndObject();
    }
}
