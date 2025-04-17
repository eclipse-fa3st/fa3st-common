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
package org.eclipse.digitaltwin.fa3st.common.model.api.paging;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;


/**
 * Class holding paging metadata.
 */
public class PagingMetadata {

    public static final PagingMetadata EMPTY = builder().build();

    private String cursor;

    public String getCursor() {
        return cursor;
    }


    public void setCursor(String cursor) {
        this.cursor = cursor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PagingMetadata other = (PagingMetadata) o;
        return Objects.equals(cursor, other.cursor);
    }


    @Override
    public int hashCode() {
        return Objects.hash(cursor);
    }


    public static Builder builder() {
        return new Builder();
    }

    private abstract static class AbstractBuilder<T extends PagingMetadata, B extends AbstractBuilder<T, B>> extends ExtendableBuilder<T, B> {

        public B cursor(String value) {
            getBuildingInstance().setCursor(value);
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<PagingMetadata, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected PagingMetadata newBuildingInstance() {
            return new PagingMetadata();
        }
    }
}
