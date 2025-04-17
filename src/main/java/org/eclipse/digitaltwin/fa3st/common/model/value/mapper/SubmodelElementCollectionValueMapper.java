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
package org.eclipse.digitaltwin.fa3st.common.model.value.mapper;

import java.util.Objects;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementCollection;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue;
import org.eclipse.digitaltwin.fa3st.common.util.ElementValueHelper;
import org.eclipse.digitaltwin.fa3st.common.util.LambdaExceptionHelper;


/**
 * Converts between {@link org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementCollection} and
 * {@link org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue}.
 */
public class SubmodelElementCollectionValueMapper implements DataValueMapper<SubmodelElementCollection, SubmodelElementCollectionValue> {

    @Override
    public SubmodelElementCollectionValue toValue(SubmodelElementCollection submodelElement) throws ValueMappingException {
        if (submodelElement == null) {
            return null;
        }
        SubmodelElementCollectionValue value = SubmodelElementCollectionValue.builder().build();
        if (Objects.nonNull(submodelElement.getValue())) {
            value.setValues(submodelElement.getValue().stream()
                    .filter(Objects::nonNull)
                    .filter(ElementValueHelper::isValueOnlySupported)
                    .collect(Collectors.toMap(
                            Referable::getIdShort,
                            LambdaExceptionHelper.rethrowFunction(ElementValueMapper::toValue))));
        }
        return value;
    }


    @Override
    public SubmodelElementCollection setValue(SubmodelElementCollection submodelElement, SubmodelElementCollectionValue value) throws ValueMappingException {
        DataValueMapper.super.setValue(submodelElement, value);
        if (submodelElement.getValue() != null) {
            for (SubmodelElement element: submodelElement.getValue()) {
                if (element != null && value.getValues() != null && value.getValues().containsKey(element.getIdShort())) {
                    ElementValueMapper.setValue(element, value.getValues().get(element.getIdShort()));
                }
            }
        }
        return submodelElement;
    }
}
