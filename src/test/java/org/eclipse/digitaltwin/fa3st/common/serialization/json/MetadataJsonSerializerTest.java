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
package org.eclipse.digitaltwin.fa3st.common.serialization.json;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import net.javacrumbs.jsonunit.core.Option;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.MetadataJsonSerializer;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.serialization.json.fixture.MetadataExamples;
import org.junit.Test;


public class MetadataJsonSerializerTest {

    private final MetadataJsonSerializer serializer = new MetadataJsonSerializer();

    @Test
    public void testAssetAdministrationShell() throws SerializationException, IOException {
        assertEquals(MetadataExamples.ASSET_ADMINISTRATION_SHELL_FILE, MetadataExamples.ASSET_ADMINISTRATION_SHELL);
    }


    @Test
    public void testSubmodel() throws SerializationException, IOException {
        assertEquals(MetadataExamples.SUBMODEL_FILE, MetadataExamples.SUBMODEL);
    }


    @Test
    public void testBlob() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.BLOB_FILE, MetadataExamples.BLOB);
    }


    @Test
    public void testElementCollection() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.ELEMENT_COLLECTION_FILE, MetadataExamples.ELEMENT_COLLECTION);
    }


    @Test
    public void testElementList() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.ELEMENT_LIST_FILE, MetadataExamples.ELEMENT_LIST);
    }


    @Test
    public void testEntity() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.ENTITY_FILE, MetadataExamples.ENTITY);
    }


    @Test
    public void testFile() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.FILE_FILE, MetadataExamples.FILE);
    }


    @Test
    public void testMultiLanguageProperty() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.MULTI_LANGUAGE_PROPERTY_FILE, MetadataExamples.MULTI_LANGUAGE_PROPERTY);
    }


    @Test
    public void testProperty() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.PROPERTY_DATETIME_FILE, MetadataExamples.PROPERTY_DATETIME);
        assertEquals(MetadataExamples.PROPERTY_DOUBLE_FILE, MetadataExamples.PROPERTY_DOUBLE);
        assertEquals(MetadataExamples.PROPERTY_INT_FILE, MetadataExamples.PROPERTY_INT);
        assertEquals(MetadataExamples.PROPERTY_STRING_FILE, MetadataExamples.PROPERTY_STRING);
    }


    @Test
    public void testRange() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.RANGE_DOUBLE_FILE, MetadataExamples.RANGE_DOUBLE);
        assertEquals(MetadataExamples.RANGE_INT_FILE, MetadataExamples.RANGE_INT);
    }


    @Test
    public void testReferenceElement() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.REFERENCE_ELEMENT_GLOBAL_FILE, MetadataExamples.REFERENCE_ELEMENT_GLOBAL);
        assertEquals(MetadataExamples.REFERENCE_ELEMENT_MODEL_FILE, MetadataExamples.REFERENCE_ELEMENT_MODEL);
    }


    @Test
    public void testRelationshipElement() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.RELATIONSHIP_ELEMENT_FILE, MetadataExamples.RELATIONSHIP_ELEMENT);
    }


    @Test
    public void testAnnotatedRelationshipElement() throws SerializationException, IOException, ValueMappingException {
        assertEquals(MetadataExamples.ANNOTATED_RELATIONSHIP_ELEMENT_FILE, MetadataExamples.ANNOTATED_RELATIONSHIP_ELEMENT);
    }


    private void assertEquals(File expectedFile, Object value) throws IOException, SerializationException {
        assertEquals(Files.readString(expectedFile.toPath()), value);
    }


    private void assertEquals(String expected, Object value) throws IOException, SerializationException {
        assertThatJson(serializer.write(value))
                .when(Option.IGNORING_ARRAY_ORDER)
                .isEqualTo(expected);
    }

}
