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

import org.eclipse.digitaltwin.aas4j.v3.model.RelationshipElement;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.RelationshipElementValue;


/**
 * Converts between {@link org.eclipse.digitaltwin.aas4j.v3.model.RelationshipElement} and
 * {@link org.eclipse.digitaltwin.fa3st.common.model.value.RelationshipElementValue}.
 */
public class RelationshipElementValueMapper implements DataValueMapper<RelationshipElement, RelationshipElementValue> {

    @Override
    public RelationshipElementValue toValue(RelationshipElement submodelElement) {
        if (submodelElement == null) {
            return null;
        }
        RelationshipElementValue relationshipElementValue = new RelationshipElementValue();
        relationshipElementValue.setFirst(submodelElement.getFirst());
        relationshipElementValue.setSecond(submodelElement.getSecond());
        return relationshipElementValue;
    }


    @Override
    public RelationshipElement setValue(RelationshipElement submodelElement, RelationshipElementValue value) throws ValueMappingException {
        DataValueMapper.super.setValue(submodelElement, value);
        submodelElement.setFirst(value.getFirst());
        submodelElement.setSecond(value.getSecond());
        return submodelElement;
    }
}
