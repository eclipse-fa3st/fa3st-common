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
package org.eclipse.digitaltwin.fa3st.common.model.value;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;


/**
 * Value class for Property.
 */
public class PropertyValue extends DataElementValue {

    private TypedValue value;

    /**
     * Creates a new instance given datatype and string-representation of value. If datatype cannot be parsed it
     * defaults to {@link org.eclipse.digitaltwin.fa3st.common.model.value.Datatype#DEFAULT}
     *
     * @param datatype string-representation of datatype
     * @param value string-representation of value
     * @return new instance
     * @throws ValueFormatException if string-representation of value cannot be parsed into provided datatype
     */
    public static PropertyValue of(String datatype, String value) throws ValueFormatException {
        return new PropertyValue(TypedValueFactory.create(datatype, value));
    }


    /**
     * Creates a new instance given datatype and string-representation of value.
     *
     * @param datatype the datatype
     * @param value string-representation of value
     * @return new instance
     * @throws ValueFormatException if string-representation of value cannot be parsed into provided datatype
     */
    public static PropertyValue of(Datatype datatype, String value) throws ValueFormatException {
        return new PropertyValue(TypedValueFactory.create(datatype, value));
    }


    public PropertyValue() {}


    public PropertyValue(TypedValue value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PropertyValue that = (PropertyValue) o;
        return Objects.equals(value, that.value);
    }


    public TypedValue getValue() {
        return value;
    }


    public void setValue(TypedValue value) {
        this.value = value;
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends PropertyValue, B extends AbstractBuilder<T, B>> extends ExtendableBuilder<T, B> {

        public B value(TypedValue value) {
            getBuildingInstance().setValue(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<PropertyValue, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected PropertyValue newBuildingInstance() {
            return new PropertyValue();
        }
    }
}
