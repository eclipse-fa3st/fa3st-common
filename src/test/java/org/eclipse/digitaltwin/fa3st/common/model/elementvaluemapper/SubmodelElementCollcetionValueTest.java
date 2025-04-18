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
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXsd;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodelElementCollection;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.StringValue;
import org.junit.Assert;
import org.junit.Test;


public class SubmodelElementCollcetionValueTest {

    @Test
    public void testToValueMapping() throws ValueMappingException {
        PropertyValue propertyValue = PropertyValue.builder().value(new StringValue("testValue")).build();
        PropertyValue propertyValue2 = PropertyValue.builder().value(new StringValue("testValue2")).build();
        SubmodelElementCollectionValue expected = SubmodelElementCollectionValue.builder()
                .value("prop1", propertyValue)
                .value("prop2", propertyValue2)
                .build();

        SubmodelElement input = new DefaultSubmodelElementCollection.Builder()
                .value(new DefaultProperty.Builder()
                        .idShort("prop1")
                        .value("testValue")
                        .build())
                .value(new DefaultProperty.Builder()
                        .idShort("prop2")
                        .value("testValue2")
                        .build())
                .build();

        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMappingWithNull() throws ValueMappingException {
        SubmodelElementCollectionValue expected = SubmodelElementCollectionValue.builder()
                .build();
        SubmodelElement input = new DefaultSubmodelElementCollection.Builder()
                .value((List<SubmodelElement>) null)
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMapping() throws ValueMappingException {
        SubmodelElement actual = new DefaultSubmodelElementCollection.Builder()
                .value(new DefaultProperty.Builder()
                        .idShort("prop1")
                        .build())
                .value(new DefaultProperty.Builder()
                        .idShort("prop2")
                        .build())
                .build();

        PropertyValue propertyValue = PropertyValue.builder().value(new StringValue("testValue")).build();
        PropertyValue propertyValue2 = PropertyValue.builder().value(new StringValue("testValue2")).build();
        SubmodelElementCollectionValue value = SubmodelElementCollectionValue.builder()
                .value("prop1", propertyValue)
                .value("prop2", propertyValue2)
                .build();

        SubmodelElement expected = new DefaultSubmodelElementCollection.Builder()
                .value(new DefaultProperty.Builder()
                        .idShort("prop1")
                        .value("testValue")
                        .valueType(DataTypeDefXsd.STRING)
                        .build())
                .value(new DefaultProperty.Builder()
                        .idShort("prop2")
                        .value("testValue2")
                        .valueType(DataTypeDefXsd.STRING)
                        .build())
                .build();

        actual = ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMappingWithNull() throws ValueMappingException {
        SubmodelElement actual = new DefaultSubmodelElementCollection.Builder()
                .value((List<SubmodelElement>) null)
                .build();
        SubmodelElementCollectionValue value = SubmodelElementCollectionValue.builder()
                .values(null)
                .build();

        SubmodelElement expected = new DefaultSubmodelElementCollection.Builder()
                .value((List<SubmodelElement>) null)
                .build();
        actual = ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }
}
