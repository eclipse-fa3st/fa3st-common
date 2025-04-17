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

import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.StringValue;
import org.junit.Assert;
import org.junit.Test;


public class PropertyValueTest {

    @Test
    public void testSetValueMapping() throws ValueMappingException {
        PropertyValue value = new PropertyValue(new StringValue("foo"));
        SubmodelElement expected = new DefaultProperty.Builder()
                .valueType(value.getValue().getDataType().getAas4jDatatype())
                .value(value.getValue().asString())
                .build();
        SubmodelElement actual = new DefaultProperty.Builder()
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMappingWithNull() throws ValueMappingException {
        PropertyValue value = new PropertyValue();
        SubmodelElement expected = new DefaultProperty.Builder()
                .valueType(null)
                .value(null)
                .build();
        SubmodelElement actual = new DefaultProperty.Builder()
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMapping() throws ValueFormatException, ValueMappingException {
        PropertyValue expected = PropertyValue.of(Datatype.STRING, "foo");
        SubmodelElement input = new DefaultProperty.Builder()
                .category("Test")
                .idShort("TestProperty")
                .valueType(expected.getValue().getDataType().getAas4jDatatype())
                .value(expected.getValue().asString())
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMappingWithNull() throws ValueFormatException, ValueMappingException {
        PropertyValue expected = PropertyValue.builder()
                .value(new StringValue(null))
                .build();
        SubmodelElement input = new DefaultProperty.Builder()
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }

}
