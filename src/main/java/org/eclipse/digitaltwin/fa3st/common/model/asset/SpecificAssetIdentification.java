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
package org.eclipse.digitaltwin.fa3st.common.model.asset;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSpecificAssetId;


/**
 * Represents specific asset identification information.
 */
public class SpecificAssetIdentification extends AssetIdentification {

    private String key;

    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public SpecificAssetId asSpecificAssetId() {
        return new DefaultSpecificAssetId.Builder()
                .name(key)
                .value(value)
                .build();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpecificAssetIdentification that = (SpecificAssetIdentification) o;
        return super.equals(that)
                && Objects.equals(key, that.key);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends SpecificAssetIdentification, B extends AbstractBuilder<T, B>> extends AssetIdentification.AbstractBuilder<T, B> {

        public B key(String value) {
            getBuildingInstance().setKey(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<SpecificAssetIdentification, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected SpecificAssetIdentification newBuildingInstance() {
            return new SpecificAssetIdentification();
        }
    }
}
