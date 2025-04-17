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

import org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSpecificAssetId;
import org.eclipse.digitaltwin.fa3st.common.util.Fa3stConstants;


/**
 * Represents a global asset identification.
 */
public class GlobalAssetIdentification extends AssetIdentification {

    @Override
    public SpecificAssetId asSpecificAssetId() {
        return new DefaultSpecificAssetId.Builder()
                .name(Fa3stConstants.KEY_GLOBAL_ASSET_ID)
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
        GlobalAssetIdentification that = (GlobalAssetIdentification) o;
        return super.equals(that);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GlobalAssetIdentification, B extends AbstractBuilder<T, B>> extends AssetIdentification.AbstractBuilder<T, B> {

    }

    public static class Builder extends AbstractBuilder<GlobalAssetIdentification, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GlobalAssetIdentification newBuildingInstance() {
            return new GlobalAssetIdentification();
        }
    }
}
