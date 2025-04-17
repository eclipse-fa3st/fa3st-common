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

import org.eclipse.digitaltwin.aas4j.v3.model.Property;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;


/**
 * Converts between {@link org.eclipse.digitaltwin.aas4j.v3.model.Property} and
 * {@link org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue}.
 */
public class PropertyValueMapper implements DataValueMapper<Property, PropertyValue> {

    @Override
    public PropertyValue toValue(Property submodelElement) throws ValueMappingException {
        if (submodelElement == null) {
            return null;
        }
        PropertyValue propertyValue = new PropertyValue();
        try {
            propertyValue.setValue(TypedValueFactory.create(submodelElement.getValueType(), submodelElement.getValue()));
        }
        catch (ValueFormatException e) {
            throw new ValueMappingException("invalid data value", e);
        }
        return propertyValue;
    }


    @Override
    public Property setValue(Property submodelElement, PropertyValue value) throws ValueMappingException {
        DataValueMapper.super.setValue(submodelElement, value);
        TypedValue<?> propertyValue = value.getValue();
        if (propertyValue != null) {
            submodelElement.setValueType(propertyValue.getDataType() != null ? propertyValue.getDataType().getAas4jDatatype() : null);
            submodelElement.setValue(propertyValue.asString());
        }
        return submodelElement;
    }

}
