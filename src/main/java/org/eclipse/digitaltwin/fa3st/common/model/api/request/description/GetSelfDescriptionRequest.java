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
package org.eclipse.digitaltwin.fa3st.common.model.api.request.description;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.api.Request;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.description.GetSelfDescriptionResponse;


/**
 * Request class for GetSelfDescription requests.
 */
public class GetSelfDescriptionRequest extends Request<GetSelfDescriptionResponse> {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetSelfDescriptionRequest that = (GetSelfDescriptionRequest) o;
        return super.equals(that);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends GetSelfDescriptionRequest, B extends AbstractBuilder<T, B>> extends Request.AbstractBuilder<T, B> {

    }

    public static class Builder extends AbstractBuilder<GetSelfDescriptionRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GetSelfDescriptionRequest newBuildingInstance() {
            return new GetSelfDescriptionRequest();
        }
    }
}
