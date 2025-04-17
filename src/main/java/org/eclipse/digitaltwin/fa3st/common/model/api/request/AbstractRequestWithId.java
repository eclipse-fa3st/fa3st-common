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
package org.eclipse.digitaltwin.fa3st.common.model.api.request;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.api.Request;
import org.eclipse.digitaltwin.fa3st.common.model.api.Response;


/**
 * Abstract baseclass for request with an Id.
 *
 * @param <T> type of the corresponding response
 */
public class AbstractRequestWithId<T extends Response> extends Request<T> {

    protected String id;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractRequestWithId<T> that = (AbstractRequestWithId<T>) o;
        return super.equals(that)
                && Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public abstract static class AbstractBuilder<T extends AbstractRequestWithId, B extends AbstractBuilder<T, B>> extends Request.AbstractBuilder<T, B> {

        public B id(String value) {
            getBuildingInstance().setId(value);
            return getSelf();
        }
    }
}
