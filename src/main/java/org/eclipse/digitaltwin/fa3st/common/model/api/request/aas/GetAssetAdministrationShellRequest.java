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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.aas;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.OutputModifierConstraints;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.aas.GetAssetAdministrationShellResponse;


/**
 * Request class for GetAssetAdministrationShell requests.
 */
public class GetAssetAdministrationShellRequest extends AbstractRequestWithModifier<GetAssetAdministrationShellResponse> {

    private String id;

    public GetAssetAdministrationShellRequest() {
        super(OutputModifierConstraints.ASSET_ADMINISTRATION_SHELL);
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAssetAdministrationShellRequest that = (GetAssetAdministrationShellRequest) o;
        return super.equals(that) && Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetAssetAdministrationShellRequest, B extends AbstractBuilder<T, B>>
            extends AbstractRequestWithModifier.AbstractBuilder<T, B> {

        public B id(String value) {
            getBuildingInstance().setId(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<GetAssetAdministrationShellRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAssetAdministrationShellRequest newBuildingInstance() {
            return new GetAssetAdministrationShellRequest();
        }
    }
}
