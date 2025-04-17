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
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifierAndPaging;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.conceptdescription.GetAllConceptDescriptionsByIsCaseOfResponse;


/**
 * Request class for GetAllConceptDescriptionsByIsCaseOf requests.
 */
public class GetAllConceptDescriptionsByIsCaseOfRequest extends AbstractRequestWithModifierAndPaging<GetAllConceptDescriptionsByIsCaseOfResponse> {

    private Reference isCaseOf;

    public Reference getIsCaseOf() {
        return isCaseOf;
    }


    public void setIsCaseOf(Reference isCaseOf) {
        this.isCaseOf = isCaseOf;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllConceptDescriptionsByIsCaseOfRequest that = (GetAllConceptDescriptionsByIsCaseOfRequest) o;
        return super.equals(that)
                && Objects.equals(isCaseOf, that.isCaseOf);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isCaseOf);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetAllConceptDescriptionsByIsCaseOfRequest, B extends AbstractBuilder<T, B>>
            extends AbstractRequestWithModifier.AbstractBuilder<T, B> {

        public B isCaseOf(Reference value) {
            getBuildingInstance().setIsCaseOf(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<GetAllConceptDescriptionsByIsCaseOfRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetAllConceptDescriptionsByIsCaseOfRequest newBuildingInstance() {
            return new GetAllConceptDescriptionsByIsCaseOfRequest();
        }
    }
}
