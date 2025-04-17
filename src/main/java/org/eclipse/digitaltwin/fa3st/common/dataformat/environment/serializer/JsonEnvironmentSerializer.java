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
package org.eclipse.digitaltwin.fa3st.common.dataformat.environment.serializer;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.aasx.InMemoryFile;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.fa3st.common.dataformat.EnvironmentSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SupportedDataformat;
import org.eclipse.digitaltwin.fa3st.common.model.serialization.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JSON serializer for {@link org.eclipse.digitaltwin.aas4j.v3.model.Environment}s and related files.
 */
@SupportedDataformat(DataFormat.JSON)
public class JsonEnvironmentSerializer implements EnvironmentSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonEnvironmentSerializer.class);
    private final JsonSerializer serializer;

    public JsonEnvironmentSerializer() {
        this.serializer = new JsonSerializer();
    }


    @Override
    public byte[] write(Charset charset, Environment environment, Collection<InMemoryFile> files) throws SerializationException {
        if (Objects.nonNull(files) && !files.isEmpty()) {
            LOGGER.debug("embedded files are ignored when serializing to JSON");
        }
        try {
            return serializer.write(environment).getBytes(charset);
        }
        catch (org.eclipse.digitaltwin.aas4j.v3.dataformat.core.SerializationException e) {
            throw new SerializationException("JSON serialization failed", e);
        }
    }
}
