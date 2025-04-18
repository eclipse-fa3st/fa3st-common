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
package org.eclipse.digitaltwin.fa3st.common.model.api.response.proprietary;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;


/**
 * The result of an import operation.
 */
public class ImportResult {
    private Map<String, String> fileErrors;
    private Map<Reference, String> modelErrors;

    public ImportResult() {
        this.fileErrors = new HashMap<>();
        this.modelErrors = new HashMap<>();
    }


    public Map<String, String> getFileErrors() {
        return fileErrors;
    }


    public void setFileErrors(Map<String, String> fileErrors) {
        this.fileErrors = fileErrors;
    }


    public Map<Reference, String> getModelErrors() {
        return modelErrors;
    }


    public void setModelErrors(Map<Reference, String> modelErrors) {
        this.modelErrors = modelErrors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImportResult that = (ImportResult) o;
        return Objects.equals(fileErrors, that.fileErrors)
                && Objects.equals(modelErrors, that.modelErrors);
    }


    @Override
    public int hashCode() {
        return Objects.hash(fileErrors, modelErrors);
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ExtendableBuilder<ImportResult, Builder> {

        public Builder fileErrors(Map<String, String> fileErrors) {
            getBuildingInstance().setFileErrors(fileErrors);
            return getSelf();
        }


        public Builder fileError(String path, String reason) {
            getBuildingInstance().getFileErrors().put(path, reason);
            return getSelf();
        }


        public Builder modelErrors(Map<Reference, String> modelErrors) {
            getBuildingInstance().setModelErrors(modelErrors);
            return getSelf();
        }


        public Builder modelError(Reference reference, String reason) {
            getBuildingInstance().getModelErrors().put(reference, reason);
            return getSelf();
        }


        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected ImportResult newBuildingInstance() {
            return new ImportResult();
        }
    }
}
