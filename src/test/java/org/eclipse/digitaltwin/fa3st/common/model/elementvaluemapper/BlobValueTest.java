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
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultBlob;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.junit.Assert;
import org.junit.Test;


public class BlobValueTest {

    @Test
    public void testSetValueMapping() throws ValueMappingException {
        SubmodelElement actual = new DefaultBlob.Builder()
                .build();
        BlobValue value = BlobValue.builder()
                .mimeType("application/json")
                .value("foo")
                .build();
        SubmodelElement expected = new DefaultBlob.Builder()
                .contentType(value.getContentType())
                .value(value.getValue())
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMappingWithNull() throws ValueMappingException {
        SubmodelElement actual = new DefaultBlob.Builder()
                .contentType(null)
                .value(null)
                .build();
        BlobValue value = BlobValue.builder()
                .mimeType(null)
                .value("foo")
                .build();
        SubmodelElement expected = new DefaultBlob.Builder()
                .contentType(null)
                .value(value.getValue())
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMapping() throws ValueMappingException {
        BlobValue expected = BlobValue.builder()
                .mimeType("application/json")
                .value("foo")
                .build();
        SubmodelElement input = new DefaultBlob.Builder()
                .contentType(expected.getContentType())
                .value(expected.getValue())
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMappingWithNull() throws ValueMappingException {
        BlobValue expected = BlobValue.builder()
                .mimeType(null)
                .value((String) null)
                .build();
        SubmodelElement input = new DefaultBlob.Builder()
                .contentType(null)
                .value(null)
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }

}
