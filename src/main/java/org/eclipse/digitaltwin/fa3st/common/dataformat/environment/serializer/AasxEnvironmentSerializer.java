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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.aasx.AASXSerializer;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.aasx.InMemoryFile;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.fa3st.common.dataformat.EnvironmentSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SupportedDataformat;
import org.eclipse.digitaltwin.fa3st.common.model.serialization.DataFormat;


/**
 * AASX serializer for {@link org.eclipse.digitaltwin.aas4j.v3.model.Environment}s and related files.
 */
@SupportedDataformat(DataFormat.AASX)
public class AasxEnvironmentSerializer implements EnvironmentSerializer {

    private final AASXSerializer serializer;

    public AasxEnvironmentSerializer() {
        this.serializer = new AASXSerializer();
    }


    @Override
    public byte[] write(Charset charset, Environment environment, Collection<InMemoryFile> files) throws SerializationException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            serializer.write(environment, files, out);
            return out.toByteArray();
        }
        catch (org.eclipse.digitaltwin.aas4j.v3.dataformat.core.SerializationException | IOException e) {
            throw new SerializationException("AASX serialization failed", e);
        }
    }
}
