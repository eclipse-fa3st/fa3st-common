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
package org.eclipse.digitaltwin.fa3st.common.dataformat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedModifierException;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier;


/**
 * Seriliazer for API calls. Serializes not only whole
 * {@link org.eclipse.digitaltwin.aas4j.v3.model.Environment} but also other elements like
 * {@link org.eclipse.digitaltwin.aas4j.v3.model.AssetAdministrationShell},
 * {@link org.eclipse.digitaltwin.aas4j.v3.model.Submodel} or
 * {@link org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement} supporting {@link OutputModifier}.
 */
public interface ApiSerializer {

    public Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * Serializes given obj as string.
     *
     * @param obj object to serialize
     * @param modifier output modifier defining how to serialize
     * @return string serialization of obj
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public String write(Object obj, OutputModifier modifier) throws SerializationException, UnsupportedModifierException;


    /**
     * Serializes given obj as string using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier#DEFAULT}.
     *
     * @param obj object to serialize
     * @return string serialization of obj
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default String write(Object obj) throws SerializationException, UnsupportedModifierException {
        return write(obj, OutputModifier.DEFAULT);
    }


    /**
     * Serializes given obj to stream.
     *
     * @param out stream to serialize to
     * @param obj object to serialize
     * @param modifier output modifier defining how to serialize
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(OutputStream out, Object obj, OutputModifier modifier) throws IOException, SerializationException, UnsupportedModifierException {
        write(out, DEFAULT_CHARSET, obj, modifier);
    }


    /**
     * Serializes given obj to stream using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier#DEFAULT}.
     *
     * @param out stream to serialize to
     * @param obj object to serialize
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(OutputStream out, Object obj) throws IOException, SerializationException, UnsupportedModifierException {
        write(out, obj, OutputModifier.DEFAULT);
    }


    /**
     * Serializes given obj to stream.
     *
     * @param out stream to serialize to
     * @param charset charset to use for serialization
     * @param obj object to serialize
     * @param modifier output modifier defining how to serialize
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(OutputStream out, Charset charset, Object obj, OutputModifier modifier) throws IOException, SerializationException, UnsupportedModifierException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out, charset)) {
            writer.write(write(obj, modifier));
        }
    }


    /**
     * Serializes given obj to stream using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier#DEFAULT}.
     *
     * @param out stream to serialize to
     * @param charset charset to use for serialization
     * @param obj object to serialize
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(OutputStream out, Charset charset, Object obj) throws IOException, SerializationException, UnsupportedModifierException {
        write(out, charset, obj, OutputModifier.DEFAULT);
    }


    /**
     * Serializes given obj to file.
     *
     * @param file fileto serialize to
     * @param charset charset to use for serialization
     * @param obj object to serialize
     * @param modifier output modifier defining how to serialize
     * @throws FileNotFoundException if file is not found
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(File file, Charset charset, Object obj, OutputModifier modifier) throws IOException, SerializationException, UnsupportedModifierException {
        try (OutputStream out = new FileOutputStream(file)) {
            write(out, charset, obj, modifier);
        }
    }


    /**
     * Serializes given obj to file using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier#DEFAULT}.
     *
     * @param file fileto serialize to
     * @param charset charset to use for serialization
     * @param obj object to serialize
     * @throws FileNotFoundException if file is not found
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(File file, Charset charset, Object obj) throws IOException, SerializationException, UnsupportedModifierException {
        write(file, charset, obj, OutputModifier.DEFAULT);
    }


    /**
     * Serializes given obj to file.
     *
     * @param file fileto serialize to
     * @param obj object to serialize
     * @param modifier output modifier defining how to serialize
     * @throws FileNotFoundException if file is not found
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(File file, Object obj, OutputModifier modifier) throws IOException, SerializationException, UnsupportedModifierException {
        write(file, DEFAULT_CHARSET, obj, modifier);
    }


    /**
     * Serializes given obj to file using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier#DEFAULT}.
     *
     * @param file fileto serialize to
     * @param obj object to serialize
     * @throws FileNotFoundException if file is not found
     * @throws IOException if writing to the stream fails
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public default void write(File file, Object obj) throws IOException, SerializationException, UnsupportedModifierException {
        write(file, obj, OutputModifier.DEFAULT);
    }

}
