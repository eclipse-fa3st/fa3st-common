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

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetAdministrationShell;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithId;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.aasrepository.PutAssetAdministrationShellByIdResponse;


/**
 * Request class for PutAssetAdministrationShellById requests.
 */
public class PutAssetAdministrationShellByIdRequest extends AbstractRequestWithId<PutAssetAdministrationShellByIdResponse> {

    private AssetAdministrationShell aas;

    public AssetAdministrationShell getAas() {
        return aas;
    }


    public void setAas(AssetAdministrationShell aas) {
        this.aas = aas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PutAssetAdministrationShellByIdRequest that = (PutAssetAdministrationShellByIdRequest) o;
        return super.equals(that)
                && Objects.equals(aas, that.aas);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), aas);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends PutAssetAdministrationShellByIdRequest, B extends AbstractBuilder<T, B>>
            extends AbstractRequestWithId.AbstractBuilder<T, B> {

        public B aas(AssetAdministrationShell value) {
            getBuildingInstance().setAas(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<PutAssetAdministrationShellByIdRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected PutAssetAdministrationShellByIdRequest newBuildingInstance() {
            return new PutAssetAdministrationShellByIdRequest();
        }
    }
}
