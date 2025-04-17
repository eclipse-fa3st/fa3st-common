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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.conceptdescription;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.ConceptDescription;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithId;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.conceptdescription.PutConceptDescriptionByIdResponse;


/**
 * Request class for PutConceptDescriptionById requests.
 */
public class PutConceptDescriptionByIdRequest extends AbstractRequestWithId<PutConceptDescriptionByIdResponse> {

    private ConceptDescription conceptDescription;

    public ConceptDescription getConceptDescription() {
        return conceptDescription;
    }


    public void setConceptDescription(ConceptDescription conceptDescription) {
        this.conceptDescription = conceptDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PutConceptDescriptionByIdRequest that = (PutConceptDescriptionByIdRequest) o;
        return super.equals(that)
                && Objects.equals(conceptDescription, that.conceptDescription);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), conceptDescription);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends PutConceptDescriptionByIdRequest, B extends AbstractBuilder<T, B>> extends AbstractRequestWithId.AbstractBuilder<T, B> {

        public B conceptDescription(ConceptDescription value) {
            getBuildingInstance().setConceptDescription(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<PutConceptDescriptionByIdRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected PutConceptDescriptionByIdRequest newBuildingInstance() {
            return new PutConceptDescriptionByIdRequest();
        }
    }
}
