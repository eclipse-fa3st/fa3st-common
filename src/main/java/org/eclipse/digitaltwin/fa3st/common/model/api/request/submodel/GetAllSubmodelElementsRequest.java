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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.submodel;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractSubmodelInterfaceRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractSubmodelInterfaceRequestWithPaging;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.OutputModifierConstraints;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.submodel.GetAllSubmodelElementsResponse;


/**
 * Request class for GetAllSubmodelElements requests.
 */
public class GetAllSubmodelElementsRequest extends AbstractSubmodelInterfaceRequestWithPaging<GetAllSubmodelElementsResponse> {

    public GetAllSubmodelElementsRequest() {
        super(OutputModifierConstraints.SUBMODEL_ELEMENT);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllSubmodelElementsRequest that = (GetAllSubmodelElementsRequest) o;
        return super.equals(that);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetAllSubmodelElementsRequest, B extends AbstractBuilder<T, B>>
            extends AbstractSubmodelInterfaceRequest.AbstractBuilder<T, B> {

    }

    public static class Builder extends AbstractBuilder<GetAllSubmodelElementsRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAllSubmodelElementsRequest newBuildingInstance() {
            return new GetAllSubmodelElementsRequest();
        }
    }
}
