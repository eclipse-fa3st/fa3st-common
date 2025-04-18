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
package org.eclipse.digitaltwin.fa3st.common.model.elementvaluemapper;

import java.util.List;
import org.eclipse.digitaltwin.aas4j.v3.model.DataElement;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXsd;
import org.eclipse.digitaltwin.aas4j.v3.model.KeyTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAnnotatedRelationshipElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.AnnotatedRelationshipElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.junit.Assert;
import org.junit.Test;


public class AnnotatedRelationshipElementValueTest {

    @Test
    public void testSetValueMapping() throws ValueFormatException, ValueMappingException {
        SubmodelElement actual = new DefaultAnnotatedRelationshipElement.Builder()
                .annotations(new DefaultProperty.Builder()
                        .idShort("property")
                        .build())
                .build();
        AnnotatedRelationshipElementValue value = createAnnotatedRelationshipElementValue();
        SubmodelElement expected = new DefaultAnnotatedRelationshipElement.Builder()
                .first(value.getFirst())
                .second(value.getSecond())
                .annotations(new DefaultProperty.Builder()
                        .idShort(value.getAnnotations().keySet().iterator().next())
                        .valueType(DataTypeDefXsd.STRING)
                        .value("foo")
                        .build())
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMappingWithNull() throws ValueMappingException {
        SubmodelElement actual = new DefaultAnnotatedRelationshipElement.Builder()
                .annotations((List<DataElement>) null)
                .first(null)
                .second(null)
                .build();
        AnnotatedRelationshipElementValue value = new AnnotatedRelationshipElementValue.Builder()
                .first(new DefaultReference.Builder()
                        .type(ReferenceTypes.MODEL_REFERENCE)
                        .keys(new DefaultKey.Builder()
                                .type(KeyTypes.SUBMODEL)
                                .value("http://example.org/submodel/1")
                                .build())
                        .build())
                .second(null)
                .build();
        SubmodelElement expected = new DefaultAnnotatedRelationshipElement.Builder()
                .first(value.getFirst())
                .second(null)
                .annotations((List<DataElement>) null)
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMapping() throws ValueFormatException, ValueMappingException {
        AnnotatedRelationshipElementValue expected = createAnnotatedRelationshipElementValue();
        SubmodelElement input = new DefaultAnnotatedRelationshipElement.Builder()
                .first(expected.getFirst())
                .second(expected.getSecond())
                .annotations(new DefaultProperty.Builder()
                        .idShort(expected.getAnnotations().keySet().iterator().next())
                        .valueType(DataTypeDefXsd.STRING)
                        .value("foo")
                        .build())
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMappingWithNull() throws ValueMappingException {
        SubmodelElement element = new DefaultAnnotatedRelationshipElement.Builder()
                .annotations((List<DataElement>) null)
                .first(null)
                .second(null)
                .build();
        AnnotatedRelationshipElementValue expected = new AnnotatedRelationshipElementValue.Builder()
                .first(null)
                .second(null)
                .build();
        AnnotatedRelationshipElementValue value = ElementValueMapper.toValue(element, AnnotatedRelationshipElementValue.class);
        Assert.assertEquals(expected, value);
    }


    private AnnotatedRelationshipElementValue createAnnotatedRelationshipElementValue() throws ValueFormatException {
        return new AnnotatedRelationshipElementValue.Builder()
                .first(new DefaultReference.Builder()
                        .type(ReferenceTypes.MODEL_REFERENCE)
                        .keys(new DefaultKey.Builder()
                                .type(KeyTypes.SUBMODEL)
                                .value("http://example.org/submodel/1")
                                .build())
                        .keys(new DefaultKey.Builder()
                                .type(KeyTypes.PROPERTY)
                                .value("property1")
                                .build())
                        .build())
                .second(new DefaultReference.Builder()
                        .type(ReferenceTypes.MODEL_REFERENCE)
                        .keys(new DefaultKey.Builder()
                                .type(KeyTypes.SUBMODEL)
                                .value("http://example.org/submodel/2")
                                .build())
                        .keys(new DefaultKey.Builder()
                                .type(KeyTypes.PROPERTY)
                                .value("property2")
                                .build())
                        .build())
                .annotation("property", PropertyValue.of(Datatype.STRING, "foo"))
                .build();
    }

}
