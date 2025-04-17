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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.javacrumbs.jsonunit.core.Option;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.ValueOnlyJsonSerializer;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedContentModifierException;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Extent;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.eclipse.digitaltwin.fa3st.common.serialization.json.fixture.ValueOnlyExamples;
import org.eclipse.digitaltwin.fa3st.common.serialization.json.util.ValueHelper;
import org.eclipse.digitaltwin.fa3st.common.util.LambdaExceptionHelper;
import org.junit.Assert;
import org.junit.Test;


public class ValueOnlyJsonSerializerTest {

    ValueOnlyJsonSerializer serializer = new ValueOnlyJsonSerializer();

    @Test
    public void testAnnotatedRelationshipElement() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.ANNOTATED_RELATIONSHIP_ELEMENT_FILE, ValueOnlyExamples.ANNOTATED_RELATIONSHIP_ELEMENT);
        assertValue(ValueOnlyExamples.ANNOTATED_RELATIONSHIP_ELEMENT_FILE, ValueOnlyExamples.ANNOTATED_RELATIONSHIP_ELEMENT);
    }


    @Test
    public void testBasicEventElement() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.BASIC_EVENT_ELEMENT_FILE, ValueOnlyExamples.BASIC_EVENT_ELEMENT);
    }


    @Test
    public void testBlob() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.BLOB_FILE_WITH_BLOB, ValueOnlyExamples.BLOB, Extent.WITH_BLOB_VALUE);
        assertEquals(ValueOnlyExamples.BLOB_FILE_WITHOUT_BLOB, ValueOnlyExamples.BLOB, Extent.WITHOUT_BLOB_VALUE);
        assertValue(ValueOnlyExamples.BLOB_FILE_WITH_BLOB, ValueOnlyExamples.BLOB, Extent.WITH_BLOB_VALUE);
        assertValue(ValueOnlyExamples.BLOB_FILE_WITHOUT_BLOB, ValueOnlyExamples.BLOB, Extent.WITHOUT_BLOB_VALUE);
    }


    @Test
    public void testSubmodelElementCollection() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.SUBMODEL_ELEMENT_COLLECTION_FILE, ValueOnlyExamples.SUBMODEL_ELEMENT_COLLECTION);
        assertValue(ValueOnlyExamples.SUBMODEL_ELEMENT_COLLECTION_FILE, ValueOnlyExamples.SUBMODEL_ELEMENT_COLLECTION);
    }


    @Test
    public void testSubmodelElementList() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.SUBMODEL_ELEMENT_LIST_SIMPLE_FILE, ValueOnlyExamples.SUBMODEL_ELEMENT_LIST_SIMPLE);
        assertValue(ValueOnlyExamples.SUBMODEL_ELEMENT_LIST_SIMPLE_FILE, ValueOnlyExamples.SUBMODEL_ELEMENT_LIST_SIMPLE);
        assertEquals(ValueOnlyExamples.SUBMODEL_ELEMENT_LIST_FILE, ValueOnlyExamples.SUBMODEL_ELEMENT_LIST);
        assertValue(ValueOnlyExamples.SUBMODEL_ELEMENT_LIST_FILE, ValueOnlyExamples.SUBMODEL_ELEMENT_LIST);
    }


    @Test
    public void testEntity() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.ENTITY_FILE, ValueOnlyExamples.ENTITY);
        assertValue(ValueOnlyExamples.ENTITY_FILE, ValueOnlyExamples.ENTITY);
    }


    @Test
    public void testFile() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.FILE_FILE, ValueOnlyExamples.FILE);
        assertValue(ValueOnlyExamples.FILE_FILE, ValueOnlyExamples.FILE);
    }


    @Test
    public void testInvokeOperationRequest() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.INVOKE_OPERATION_REQUEST_FILE, ValueOnlyExamples.INVOKE_OPERATION_SYNC_REQUEST);
    }


    @Test
    public void testGetOperationAsyncResultResponse() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.GET_OPERATION_ASYNC_RESULT_RESPONSE_FILE, ValueOnlyExamples.GET_OPERATION_ASYNC_RESULT_RESPONSE);
    }


    @Test
    public void testMultiLanguageProperty() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.MULTI_LANGUAGE_PROPERTY_FILE, ValueOnlyExamples.MULTI_LANGUAGE_PROPERTY);
        assertValue(ValueOnlyExamples.MULTI_LANGUAGE_PROPERTY_FILE, ValueOnlyExamples.MULTI_LANGUAGE_PROPERTY);
    }


    @Test(expected = SerializationException.class)
    public void testNonValue() throws SerializationException, IOException, UnsupportedContentModifierException {
        serializer.write(new DefaultProperty.Builder().build());
    }


    @Test
    public void testProperty() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.PROPERTY_STRING_FILE, ValueOnlyExamples.PROPERTY_STRING);
        assertValue(ValueOnlyExamples.PROPERTY_STRING_FILE, ValueOnlyExamples.PROPERTY_STRING);
    }


    @Test
    public void testPropertyGDay() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.PROPERTY_GDAY_FILE, ValueOnlyExamples.PROPERTY_GDAY);
        assertValue(ValueOnlyExamples.PROPERTY_GDAY_FILE, ValueOnlyExamples.PROPERTY_GDAY);
    }


    @Test
    public void testList() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        Map<SubmodelElement, File> data = Map.of(ValueOnlyExamples.PROPERTY_STRING, ValueOnlyExamples.PROPERTY_STRING_FILE,
                ValueOnlyExamples.RANGE_INT, ValueOnlyExamples.RANGE_INT_FILE);
        String expected = data.entrySet().stream()
                .map(x -> {
                    try {
                        return ValueHelper.extractValueJson(x.getValue(), x.getKey());
                    }
                    catch (IOException e) {
                        Assert.fail("error extracting value from file");
                    }
                    return "";
                })
                .collect(Collectors.joining(",", "[", "]"));
        List<Object> values = data.keySet().stream()
                .map(LambdaExceptionHelper.rethrowFunction(x -> ElementValueMapper.toValue(x)))
                .collect(Collectors.toList());
        String actual = serializer.write(values);
        assertThatJson(actual).isEqualTo(expected);
    }


    @Test
    public void testArray() throws SerializationException, IOException, UnsupportedContentModifierException {
        Object[] array = new Object[] {
                ValueOnlyExamples.PROPERTY_STRING,
                ValueOnlyExamples.RANGE_INT
        };
        String expected = String.format("[%s,%s]",
                Files.readString(ValueOnlyExamples.PROPERTY_STRING_FILE.toPath()),
                Files.readString(ValueOnlyExamples.RANGE_INT_FILE.toPath()));
        String actual = serializer.write(array);
        assertEquals(expected, actual);
    }


    @Test
    public void testMap() throws SerializationException, IOException, UnsupportedContentModifierException {
        Map<String, Object> map = Map.of("first", ValueOnlyExamples.PROPERTY_STRING,
                "second", ValueOnlyExamples.RANGE_INT);
        String expected = String.format("{ \"first\": %s,\"second\":%s}",
                Files.readString(ValueOnlyExamples.PROPERTY_STRING_FILE.toPath()),
                Files.readString(ValueOnlyExamples.RANGE_INT_FILE.toPath()));
        String actual = serializer.write(map);
        assertEquals(expected, actual);
    }


    @Test
    public void testRange() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.RANGE_DOUBLE_FILE, ValueOnlyExamples.RANGE_DOUBLE);
        assertValue(ValueOnlyExamples.RANGE_DOUBLE_FILE, ValueOnlyExamples.RANGE_DOUBLE);
    }


    @Test
    public void testReferenceElement() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.REFERENCE_ELEMENT_GLOBAL_FILE, ValueOnlyExamples.REFERENCE_ELEMENT_GLOBAL);
        assertEquals(ValueOnlyExamples.REFERENCE_ELEMENT_MODEL_FILE, ValueOnlyExamples.REFERENCE_ELEMENT_MODEL);
        assertValue(ValueOnlyExamples.REFERENCE_ELEMENT_GLOBAL_FILE, ValueOnlyExamples.REFERENCE_ELEMENT_GLOBAL);
        assertValue(ValueOnlyExamples.REFERENCE_ELEMENT_MODEL_FILE, ValueOnlyExamples.REFERENCE_ELEMENT_MODEL);
    }


    @Test
    public void testRelationshipElement() throws SerializationException, IOException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.RELATIONSHIP_ELEMENT_FILE, ValueOnlyExamples.RELATIONSHIP_ELEMENT);
        assertValue(ValueOnlyExamples.RELATIONSHIP_ELEMENT_FILE, ValueOnlyExamples.RELATIONSHIP_ELEMENT);
    }


    @Test
    public void testSubmodel() throws SerializationException, IOException, UnsupportedContentModifierException {
        assertEquals(ValueOnlyExamples.SUBMODEL_FILE, ValueOnlyExamples.SUBMODEL);
    }


    private void assertEquals(File expectedFile, Object value) throws IOException, SerializationException, UnsupportedContentModifierException {
        assertEquals(expectedFile, value, Level.DEFAULT, Extent.DEFAULT);
    }


    private void assertEquals(String expected, Object value) throws IOException, SerializationException, UnsupportedContentModifierException {
        assertEquals(expected, value, Level.DEFAULT, Extent.DEFAULT);
    }


    private void assertEquals(File expectedFile, Object value, Level level, Extent extend)
            throws IOException, SerializationException, UnsupportedContentModifierException {
        assertEquals(Files.readString(expectedFile.toPath()), value, level, extend);
    }


    private void assertEquals(File expectedFile, Object value, Extent extend) throws IOException, SerializationException, UnsupportedContentModifierException {
        assertEquals(Files.readString(expectedFile.toPath()), value, Level.DEFAULT, extend);
    }


    private void assertEquals(String expected, Object value, Level level, Extent extend) throws IOException, SerializationException, UnsupportedContentModifierException {
        assertEquals(expected, serializer.write(value, level, extend));
    }


    private void assertEquals(String expected, String actual) {
        assertThatJson(actual)
                .when(Option.IGNORING_ARRAY_ORDER)
                .withTolerance(0)
                .isEqualTo(expected);
    }


    private void assertValue(File expectedFile, SubmodelElement submodelElement)
            throws IOException, SerializationException, ValueMappingException, UnsupportedContentModifierException {
        assertValue(expectedFile, submodelElement, Level.DEFAULT, Extent.DEFAULT);
    }


    private void assertValue(File expectedFile, SubmodelElement submodelElement, Extent extend)
            throws IOException, SerializationException, ValueMappingException, UnsupportedContentModifierException {
        assertValue(expectedFile, submodelElement, Level.DEFAULT, extend);
    }


    private void assertValue(File expectedFile, SubmodelElement submodelElement, Level level, Extent extend)
            throws IOException, SerializationException, ValueMappingException, UnsupportedContentModifierException {
        assertEquals(ValueHelper.extractValueJson(expectedFile, submodelElement), ElementValueMapper.toValue(submodelElement), level, extend);
    }

}
