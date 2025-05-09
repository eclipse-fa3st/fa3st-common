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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;


/**
 * Value class for SubmodelElementCollection.
 */
public class SubmodelElementCollectionValue extends DataElementValue {

    private Map<String, ElementValue> values;

    public SubmodelElementCollectionValue() {
        this.values = new HashMap<>();
    }


    public Map<String, ElementValue> getValues() {
        return values;
    }


    public void setValues(Map<String, ElementValue> values) {
        this.values = values;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubmodelElementCollectionValue elementCollectionValue = (SubmodelElementCollectionValue) o;
        return Objects.equals(values, elementCollectionValue.values);
    }


    @Override
    public int hashCode() {
        return Objects.hash(values);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends SubmodelElementCollectionValue, B extends AbstractBuilder<T, B>> extends ExtendableBuilder<T, B> {

        public B values(Map<String, ElementValue> value) {
            getBuildingInstance().setValues(value);
            return getSelf();
        }


        public B value(String name, ElementValue value) {
            getBuildingInstance().getValues().put(name, value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<SubmodelElementCollectionValue, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected SubmodelElementCollectionValue newBuildingInstance() {
            return new SubmodelElementCollectionValue();
        }
    }
}
