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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.aasrepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifierAndPaging;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.OutputModifierConstraints;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.aasrepository.GetAllAssetAdministrationShellsByAssetIdResponse;


/**
 * Request class for GetAllAssetAdministrationShellsByAssetId requests.
 */
public class GetAllAssetAdministrationShellsByAssetIdRequest extends AbstractRequestWithModifierAndPaging<GetAllAssetAdministrationShellsByAssetIdResponse> {

    private List<SpecificAssetId> assetIds;

    public GetAllAssetAdministrationShellsByAssetIdRequest() {
        super(OutputModifierConstraints.ASSET_ADMINISTRATION_SHELL);
        this.assetIds = new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllAssetAdministrationShellsByAssetIdRequest that = (GetAllAssetAdministrationShellsByAssetIdRequest) o;
        return super.equals(that)
                && Objects.equals(assetIds, that.assetIds);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assetIds);
    }


    public List<SpecificAssetId> getAssetIds() {
        return assetIds;
    }


    public void setAssetIds(List<SpecificAssetId> assetIds) {
        this.assetIds = assetIds;
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetAllAssetAdministrationShellsByAssetIdRequest, B extends AbstractBuilder<T, B>>
            extends AbstractRequestWithModifier.AbstractBuilder<T, B> {

        public B assetId(SpecificAssetId value) {
            getBuildingInstance().getAssetIds().add(value);
            return getSelf();
        }


        public B assetIds(List<SpecificAssetId> value) {
            getBuildingInstance().setAssetIds(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<GetAllAssetAdministrationShellsByAssetIdRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAllAssetAdministrationShellsByAssetIdRequest newBuildingInstance() {
            return new GetAllAssetAdministrationShellsByAssetIdRequest();
        }
    }
}
