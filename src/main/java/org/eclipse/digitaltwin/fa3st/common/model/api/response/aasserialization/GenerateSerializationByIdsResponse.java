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
package org.eclipse.digitaltwin.fa3st.common.model.api.response.aasserialization;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.EnvironmentContext;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.AbstractResponseWithPayload;
import org.eclipse.digitaltwin.fa3st.common.model.serialization.DataFormat;


/**
 * Response class for GenerateSerializationByIds requests.
 */
public class GenerateSerializationByIdsResponse extends AbstractResponseWithPayload<EnvironmentContext> {

    private DataFormat dataformat;

    public static Builder builder() {
        return new Builder();
    }


    public DataFormat getDataformat() {
        return dataformat;
    }


    public void setDataformat(DataFormat dataformat) {
        this.dataformat = dataformat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenerateSerializationByIdsResponse that = (GenerateSerializationByIdsResponse) o;
        return super.equals(o)
                && Objects.equals(dataformat, that.dataformat);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataformat);
    }

    public abstract static class AbstractBuilder<T extends GenerateSerializationByIdsResponse, B extends AbstractBuilder<T, B>>
            extends AbstractResponseWithPayload.AbstractBuilder<EnvironmentContext, T, B> {

        public B dataformat(DataFormat value) {
            getBuildingInstance().setDataformat(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<GenerateSerializationByIdsResponse, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected GenerateSerializationByIdsResponse newBuildingInstance() {
            return new GenerateSerializationByIdsResponse();
        }
    }

}
