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
import org.eclipse.digitaltwin.fa3st.common.model.TypedInMemoryFile;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractSubmodelInterfaceRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.OutputModifierConstraints;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.submodel.PutFileByPathResponse;


/**
 * Request class for PutFileByPath requests.
 */
public class PutFileByPathRequest extends AbstractSubmodelInterfaceRequest<PutFileByPathResponse> {

    private String path;
    private TypedInMemoryFile content;

    public PutFileByPathRequest() {
        super(OutputModifierConstraints.NONE);
        this.path = "";
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public TypedInMemoryFile getContent() {
        return content;
    }


    public void setContent(TypedInMemoryFile content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PutFileByPathRequest that = (PutFileByPathRequest) o;
        return super.equals(that)
                && Objects.equals(path, that.path)
                && Objects.equals(content, that.content);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), path, content);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends PutFileByPathRequest, B extends AbstractBuilder<T, B>>
            extends AbstractSubmodelInterfaceRequest.AbstractBuilder<T, B> {

        public B path(String value) {
            getBuildingInstance().setPath(value);
            return getSelf();
        }


        public B content(TypedInMemoryFile value) {
            getBuildingInstance().setContent(value);
            return getSelf();
        }

    }

    public static class Builder extends AbstractBuilder<PutFileByPathRequest, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected PutFileByPathRequest newBuildingInstance() {
            return new PutFileByPathRequest();
        }
    }
}
