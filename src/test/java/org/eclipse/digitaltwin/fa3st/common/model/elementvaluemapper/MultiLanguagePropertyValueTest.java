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
import org.eclipse.digitaltwin.aas4j.v3.model.LangStringTextType;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultMultiLanguageProperty;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.MultiLanguagePropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.mapper.ElementValueMapper;
import org.junit.Assert;
import org.junit.Test;


public class MultiLanguagePropertyValueTest {

    @Test
    public void testToValueMapping() throws ValueMappingException {
        MultiLanguagePropertyValue expected = MultiLanguagePropertyValue.builder()
                .value("deutsch", "de")
                .value("english", "en")
                .build();
        SubmodelElement input = new DefaultMultiLanguageProperty.Builder()
                .value(List.copyOf(expected.getLangStringSet()))
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testToValueMappingWithNull() throws ValueMappingException {
        MultiLanguagePropertyValue expected = MultiLanguagePropertyValue.builder()
                .build();
        SubmodelElement input = new DefaultMultiLanguageProperty.Builder()
                .value((List<LangStringTextType>) null)
                .build();
        ElementValue actual = ElementValueMapper.toValue(input);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMapping() throws ValueMappingException {
        SubmodelElement actual = new DefaultMultiLanguageProperty.Builder()
                .build();
        MultiLanguagePropertyValue value = MultiLanguagePropertyValue.builder()
                .value("deutsch", "de")
                .value("english", "en")
                .build();
        SubmodelElement expected = new DefaultMultiLanguageProperty.Builder()
                .value(List.copyOf(value.getLangStringSet()))
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSetValueMappingWithNull() throws ValueMappingException {
        SubmodelElement actual = new DefaultMultiLanguageProperty.Builder()
                .build();
        MultiLanguagePropertyValue value = MultiLanguagePropertyValue.builder()
                .values(null)
                .build();
        SubmodelElement expected = new DefaultMultiLanguageProperty.Builder()
                .build();
        ElementValueMapper.setValue(actual, value);
        Assert.assertEquals(expected, actual);
    }

}
