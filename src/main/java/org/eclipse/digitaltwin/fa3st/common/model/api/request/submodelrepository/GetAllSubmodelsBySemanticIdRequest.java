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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.submodelrepository;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifierAndPaging;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.OutputModifierConstraints;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.submodelrepository.GetAllSubmodelsBySemanticIdResponse;


/**
 * Request class for GetAllSubmodelsBySemanticId requests.
 */
public class GetAllSubmodelsBySemanticIdRequest extends AbstractRequestWithModifierAndPaging<GetAllSubmodelsBySemanticIdResponse> {

    private Reference semanticId;

    public GetAllSubmodelsBySemanticIdRequest() {
        super(OutputModifierConstraints.SUBMODEL);
    }


    public Reference getSemanticId() {
        return semanticId;
    }


    public void setSemanticId(Reference semanticId) {
        this.semanticId = semanticId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllSubmodelsBySemanticIdRequest that = (GetAllSubmodelsBySemanticIdRequest) o;
        return super.equals(that)
                && Objects.equals(semanticId, that.semanticId);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), semanticId);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetAllSubmodelsBySemanticIdRequest, B extends AbstractBuilder<T, B>>
            extends AbstractRequestWithModifier.AbstractBuilder<T, B> {

        public B semanticId(Reference value) {
            getBuildingInstance().setSemanticId(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<GetAllSubmodelsBySemanticIdRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAllSubmodelsBySemanticIdRequest newBuildingInstance() {
            return new GetAllSubmodelsBySemanticIdRequest();
        }
    }
}
