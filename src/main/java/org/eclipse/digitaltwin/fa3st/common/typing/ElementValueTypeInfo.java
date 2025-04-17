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
package org.eclipse.digitaltwin.fa3st.common.typing;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;


/**
 * Holds type information for an element value.
 */
public class ElementValueTypeInfo extends TypeInfo<String> {

    private Datatype datatype;

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public Class<? extends ElementValue> getType() {
        return (Class<? extends ElementValue>) type;
    }


    @Override
    public void setType(Class<?> type) {
        if (type != null && !ElementValue.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException(String.format("invalid type '%s' - must be subtype of ElementValue", type));
        }
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ElementValueTypeInfo that = (ElementValueTypeInfo) o;
        return super.equals(that)
                && Objects.equals(datatype, that.datatype);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), datatype);
    }

    public abstract static class AbstractBuilder<T extends ElementValueTypeInfo, B extends AbstractBuilder<T, B>> extends TypeInfo.AbstractBuilder<String, T, B> {

        public B datatype(Datatype value) {
            getBuildingInstance().setDatatype(value);
            return getSelf();
        }

    }

    public static class Builder extends AbstractBuilder<ElementValueTypeInfo, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected ElementValueTypeInfo newBuildingInstance() {
            return new ElementValueTypeInfo();
        }
    }

    public Datatype getDatatype() {
        return datatype;
    }


    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }

}
