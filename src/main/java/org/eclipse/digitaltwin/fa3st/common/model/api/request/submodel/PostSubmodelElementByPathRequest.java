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
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractSubmodelInterfaceRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.submodel.PostSubmodelElementByPathResponse;


/**
 * Request class for PostSubmodelElementByPath requests.
 */
public class PostSubmodelElementByPathRequest extends AbstractSubmodelInterfaceRequest<PostSubmodelElementByPathResponse> {

    private String path;
    private SubmodelElement submodelElement;

    public String getPath() {
        return path;
    }


    public void setPath(String key) {
        this.path = key;
    }


    public SubmodelElement getSubmodelElement() {
        return submodelElement;
    }


    public void setSubmodelElement(SubmodelElement submodelElement) {
        this.submodelElement = submodelElement;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostSubmodelElementByPathRequest that = (PostSubmodelElementByPathRequest) o;
        return super.equals(that)
                && Objects.equals(path, that.path)
                && Objects.equals(submodelElement, that.submodelElement);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), path, submodelElement);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends PostSubmodelElementByPathRequest, B extends AbstractBuilder<T, B>>
            extends AbstractSubmodelInterfaceRequest.AbstractBuilder<T, B> {

        public B path(String value) {
            getBuildingInstance().setPath(value);
            return getSelf();
        }


        public B submodelElement(SubmodelElement value) {
            getBuildingInstance().setSubmodelElement(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<PostSubmodelElementByPathRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected PostSubmodelElementByPathRequest newBuildingInstance() {
            return new PostSubmodelElementByPathRequest();
        }
    }
}
