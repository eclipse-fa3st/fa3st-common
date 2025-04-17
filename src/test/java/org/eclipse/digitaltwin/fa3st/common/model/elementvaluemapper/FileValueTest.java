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
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultFile;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.FileValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.junit.Assert;
import org.junit.Test;


public class FileValueTest {

    @Test
    public void testSetValueMapping() throws ValueMappingException {
        SubmodelElement actual = new DefaultFile.Builder()
                .build();
        FileValue value = FileValue.builder()
                .mimeType("application/json")
                .value("{}")
                .build();
        SubmodelElement expected = new DefaultFile.Builder()
                .contentType(value.getContentType())
                .value(value.getValue())
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMappingWithNull() throws ValueMappingException {
        SubmodelElement actual = new DefaultFile.Builder()
                .build();
        FileValue value = FileValue.builder()
                .mimeType(null)
                .value(null)
                .build();
        SubmodelElement expected = new DefaultFile.Builder()
                .contentType(null)
                .value(null)
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMapping() throws ValueMappingException {
        FileValue expected = FileValue.builder()
                .mimeType("application/json")
                .value("{}")
                .build();
        SubmodelElement input = new DefaultFile.Builder()
                .contentType(expected.getContentType())
                .value(expected.getValue())
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMappingWithNull() throws ValueMappingException {
        FileValue expected = FileValue.builder()
                .mimeType(null)
                .value(null)
                .build();
        SubmodelElement input = new DefaultFile.Builder()
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }

}
