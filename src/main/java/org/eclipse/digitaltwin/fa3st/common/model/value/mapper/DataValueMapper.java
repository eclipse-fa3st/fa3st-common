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

import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.util.Ensure;


/**
 * Base class for mappers between submodel elements and their corresponding value class.
 *
 * @param <I> type of submodel element
 * @param <O> type if element value
 */
public interface DataValueMapper<I extends SubmodelElement, O extends ElementValue> {

    /**
     * Provides the value representation of a submodel element. Returns null when input is null.
     *
     * @param submodelElement the submodel element
     * @return the value representation
     * @throws ValueMappingException if mapping fails
     */
    public O toValue(I submodelElement) throws ValueMappingException;


    /**
     * Sets the value on a submodel element.
     *
     * @param submodelElement the submodel element to set the value on
     * @param value the value to set
     * @return the updated submodel element with the new value
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException is seeting the value fails
     * @throws IllegalArgumentException if submodelElement is null
     * @throws IllegalArgumentException if value is null
     */
    public default I setValue(I submodelElement, O value) throws ValueMappingException {
        Ensure.requireNonNull(submodelElement, "submodelElement must be non-null");
        Ensure.requireNonNull(value, "value must be non-null");
        return submodelElement;
    }

}
