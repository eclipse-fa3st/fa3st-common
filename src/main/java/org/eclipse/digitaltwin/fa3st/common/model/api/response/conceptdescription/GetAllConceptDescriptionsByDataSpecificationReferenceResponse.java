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
package org.eclipse.digitaltwin.fa3st.common.model.api.response.conceptdescription;

import org.eclipse.digitaltwin.aas4j.v3.model.ConceptDescription;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.AbstractPagedResponse;


/**
 * Response class for GetAllConceptDescriptionsByDataSpecificationReference requests.
 */
public class GetAllConceptDescriptionsByDataSpecificationReferenceResponse extends AbstractPagedResponse<ConceptDescription> {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends
            AbstractBuilder<ConceptDescription, GetAllConceptDescriptionsByDataSpecificationReferenceResponse, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAllConceptDescriptionsByDataSpecificationReferenceResponse newBuildingInstance() {
            return new GetAllConceptDescriptionsByDataSpecificationReferenceResponse();
        }
    }
}
