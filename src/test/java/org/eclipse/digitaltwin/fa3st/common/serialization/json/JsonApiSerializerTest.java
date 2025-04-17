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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.javacrumbs.jsonunit.core.Option;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetAdministrationShell;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetKind;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXsd;
import org.eclipse.digitaltwin.aas4j.v3.model.Property;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAssetAdministrationShell;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAssetInformation;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonApiSerializer;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedModifierException;
import org.eclipse.digitaltwin.fa3st.common.model.AASFull;
import org.eclipse.digitaltwin.fa3st.common.model.api.StatusCode;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Content;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.Page;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.PagingMetadata;
import org.eclipse.digitaltwin.fa3st.common.serialization.json.fixture.ValueOnlyExamples;
import org.junit.Assert;
import org.junit.Test;


public class JsonApiSerializerTest {

    private final JsonApiSerializer serializer = new JsonApiSerializer();

    @Test
    public void testIdentifiableSerialization() throws Exception {
        AssetAdministrationShell shell = new DefaultAssetAdministrationShell.Builder()
                .idShort("testShell")
                .assetInformation(new DefaultAssetInformation.Builder().assetKind(AssetKind.INSTANCE).build())
                .build();
        assertAdminShellIoSerialization(shell);
    }


    @Test
    public void testIdentifiableListSerialization() throws Exception {
        List<Referable> shells = List.of(new DefaultAssetAdministrationShell.Builder()
                .idShort("testShell")
                .assetInformation(new DefaultAssetInformation.Builder().assetKind(AssetKind.INSTANCE).build())
                .build(),
                new DefaultAssetAdministrationShell.Builder()
                        .idShort("testShell2")
                        .assetInformation(new DefaultAssetInformation.Builder().assetKind(AssetKind.INSTANCE).build())
                        .build());
        assertAdminShellIoSerialization(shells);
    }


    @Test
    public void testReferableSerialization() throws Exception {
        Property property = new DefaultProperty.Builder()
                .idShort("testShell")
                .value("Test")
                .build();
        assertAdminShellIoSerialization(property);
    }


    @Test
    public void testReferableListSerialization() throws Exception {
        List<Referable> submodelElements = List.of(new DefaultProperty.Builder()
                .idShort("testShell")
                .value("Test")
                .build(),
                new DefaultProperty.Builder()
                        .idShort("testShell2")
                        .value("Test")
                        .build());

        assertAdminShellIoSerialization(submodelElements);
    }


    @Test
    public void testSubmodelElementListValueOnly() throws SerializationException, UnsupportedModifierException {
        Map<SubmodelElement, File> data = Map.of(ValueOnlyExamples.PROPERTY_STRING, ValueOnlyExamples.PROPERTY_STRING_FILE,
                ValueOnlyExamples.RANGE_INT, ValueOnlyExamples.RANGE_INT_FILE);
        String expected = data.entrySet().stream()
                .map(x -> {
                    try {
                        return Files.readString(x.getValue().toPath());
                    }
                    catch (IOException e) {
                        Assert.fail(String.format("error reading file %s", x.getValue()));
                    }
                    return "";
                })
                .collect(Collectors.joining(",", "[", "]"));
        String actual = serializer.write(data.keySet(), new OutputModifier.Builder()
                .content(Content.VALUE)
                .build());
        assertEquals(expected, actual);
    }


    @Test
    public void testEnumsWithCustomNaming() throws SerializationException, UnsupportedModifierException {
        Assert.assertEquals("\"SuccessCreated\"", serializer.write(StatusCode.SUCCESS_CREATED));
    }


    @Test
    public void testEnumsWithoutCustomNaming() throws SerializationException, UnsupportedModifierException {
        Assert.assertEquals("\"UTF-8\"", serializer.write(StandardCharsets.UTF_8));
    }


    @Test
    public void testFullExampleSerialization() throws Exception {
        String expected = new org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer().write(AASFull.createEnvironment());
        String actual = serializer.write(AASFull.createEnvironment(), new OutputModifier.Builder().build());
        assertEquals(expected, actual);
    }


    @Test
    public void testPageWithoutCursor() throws SerializationException, UnsupportedModifierException {
        Page<SubmodelElement> page = Page.<SubmodelElement> builder()
                .metadata(PagingMetadata.builder()
                        .build())
                .result(new DefaultProperty.Builder()
                        .idShort("idShort")
                        .value("foo")
                        .valueType(DataTypeDefXsd.STRING)
                        .build())
                .build();
        String actual = serializer.write(page);
        String expected = "{\n"
                + "  \"result\" : [ {\n"
                + "    \"modelType\" : \"Property\",\n"
                + "    \"value\" : \"foo\",\n"
                + "    \"valueType\" : \"xs:string\",\n"
                + "    \"idShort\" : \"idShort\"\n"
                + "  } ],\n"
                + "  \"paging_metadata\" : {\n"
                + "  }\n"
                + "}";
        assertEquals(expected, actual);
    }


    @Test
    public void testPageWithCursor() throws SerializationException, UnsupportedModifierException {
        Page<SubmodelElement> page = Page.<SubmodelElement> builder()
                .metadata(PagingMetadata.builder()
                        .cursor("foo")
                        .build())
                .result(new DefaultProperty.Builder()
                        .idShort("idShort")
                        .value("foo")
                        .valueType(DataTypeDefXsd.STRING)
                        .build())
                .build();
        String actual = serializer.write(page);
        String expected = "{\n"
                + "  \"result\" : [ {\n"
                + "    \"modelType\" : \"Property\",\n"
                + "    \"value\" : \"foo\",\n"
                + "    \"valueType\" : \"xs:string\",\n"
                + "    \"idShort\" : \"idShort\"\n"
                + "  } ],\n"
                + "  \"paging_metadata\" : {\n"
                + "    \"cursor\" : \"Zm9v\"\n"
                + "  }\n"
                + "}";
        assertEquals(expected, actual);
    }


    private void assertAdminShellIoSerialization(Referable referable) throws Exception {
        String expected = new org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer().write(referable);
        String actual = serializer.write(referable, new OutputModifier.Builder().build());
        assertEquals(expected, actual);
    }


    private void assertAdminShellIoSerialization(List<Referable> referables) throws Exception {
        String expected = new org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer().writeList(referables);
        String actual = serializer.write(referables, new OutputModifier.Builder().build());
        assertEquals(expected, actual);
    }


    private void assertEquals(String expected, String actual) {
        assertThatJson(actual)
                .when(Option.IGNORING_ARRAY_ORDER)
                .isEqualTo(expected);
    }

}
