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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFLanguages;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.aasx.InMemoryFile;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.fa3st.common.dataformat.EnvironmentSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SupportedDataformat;
import org.eclipse.digitaltwin.fa3st.common.model.serialization.DataFormat;


/**
 * RDF serializer for {@link org.eclipse.digitaltwin.aas4j.v3.model.Environment}s and related files.
 */
@SupportedDataformat(DataFormat.RDF)
public class RdfEnvironmentSerializer implements EnvironmentSerializer {

    // private static final Logger LOGGER = LoggerFactory.getLogger(RdfEnvironmentSerializer.class);
    public static final Lang DEFAULT_RDF_LANGUAGE = Lang.TTL;
    // private final Serializer serializer;

    public RdfEnvironmentSerializer() {
        // this.serializer = new Serializer();
    }


    /**
     * Serializes a {@link org.eclipse.digitaltwin.aas4j.v3.model.Environment} and related files as byte[].
     *
     * @param charset the charset to use
     * @param environment the environment to serialize
     * @param files related files to serialize
     * @param rdfLanguage the RDF language to use
     * @return input serialized as byte[]
     * @throws SerializationException if serialization fails
     */
    public byte[] write(Charset charset, Environment environment, Collection<InMemoryFile> files, Lang rdfLanguage) throws SerializationException {
        throw new UnsupportedOperationException("Current version of AAS4j library does not support RDF/JSON-LD de-/serialization");
        // if (Objects.nonNull(files) && !files.isEmpty()) {
        //     LOGGER.debug("embedded files are ignored when serializing to RDF");
        // }
        // try {
        //     return serializer.write(environment, rdfLanguage).getBytes(charset);
        // }
        // catch (org.eclipse.digitaltwin.aas4j.v3.dataformat.SerializationException e) {
        //     throw new SerializationException("RDF serialization failed", e);
        // }
    }


    @Override
    public byte[] write(Charset charset, Environment environment, Collection<InMemoryFile> files) throws SerializationException {
        return write(charset, environment, files, DEFAULT_RDF_LANGUAGE);
    }


    @Override
    public void write(File file, Charset charset, Environment environment, Collection<InMemoryFile> files) throws SerializationException, IOException {
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(write(charset, environment, files, RDFLanguages.filenameToLang(file.getName(), DEFAULT_RDF_LANGUAGE)));
        }
    }
}
