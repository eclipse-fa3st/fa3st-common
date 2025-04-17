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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.aasbasicdiscovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId;
import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithPaging;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.aasbasicdiscovery.GetAllAssetAdministrationShellIdsByAssetLinkResponse;


/**
 * Request class for GetAllAssetAdministrationShellIdsByAssetLink requests.
 */
public class GetAllAssetAdministrationShellIdsByAssetLinkRequest extends AbstractRequestWithPaging<GetAllAssetAdministrationShellIdsByAssetLinkResponse> {

    private List<SpecificAssetId> assetIdentifierPairs;

    public GetAllAssetAdministrationShellIdsByAssetLinkRequest() {
        this.assetIdentifierPairs = new ArrayList<>();
    }


    public List<SpecificAssetId> getAssetIdentifierPairs() {
        return assetIdentifierPairs;
    }


    public void setAssetIdentifierPairs(List<SpecificAssetId> assetIdentifierPairs) {
        this.assetIdentifierPairs = assetIdentifierPairs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllAssetAdministrationShellIdsByAssetLinkRequest that = (GetAllAssetAdministrationShellIdsByAssetLinkRequest) o;
        return super.equals(that)
                && Objects.equals(assetIdentifierPairs, that.assetIdentifierPairs);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assetIdentifierPairs);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetAllAssetAdministrationShellIdsByAssetLinkRequest, B extends AbstractBuilder<T, B>> extends ExtendableBuilder<T, B> {

        public B assetIdentifierPair(SpecificAssetId value) {
            getBuildingInstance().getAssetIdentifierPairs().add(value);
            return getSelf();
        }


        public B assetIdentifierPairs(List<SpecificAssetId> value) {
            getBuildingInstance().setAssetIdentifierPairs(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<GetAllAssetAdministrationShellIdsByAssetLinkRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAllAssetAdministrationShellIdsByAssetLinkRequest newBuildingInstance() {
            return new GetAllAssetAdministrationShellIdsByAssetLinkRequest();
        }
    }
}
