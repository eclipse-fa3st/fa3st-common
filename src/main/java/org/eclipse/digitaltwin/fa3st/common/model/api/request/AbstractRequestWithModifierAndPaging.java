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
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.PagingInfo;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.AbstractPagedResponse;


/**
 * Base class for requests that suppport {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier}
 * and paging.
 *
 * @param <T> actual type of the request
 */
public abstract class AbstractRequestWithModifierAndPaging<T extends AbstractPagedResponse> extends AbstractRequestWithModifier<T> {

    protected PagingInfo pagingInfo;

    protected AbstractRequestWithModifierAndPaging() {
        super(OutputModifierConstraints.DEFAULT);
        this.pagingInfo = PagingInfo.ALL;
    }


    protected AbstractRequestWithModifierAndPaging(OutputModifierConstraints outputModifierConstraints) {
        super(outputModifierConstraints);
        this.pagingInfo = PagingInfo.ALL;
    }


    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }


    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractRequestWithModifierAndPaging<T> that = (AbstractRequestWithModifierAndPaging<T>) o;
        return super.equals(that)
                && Objects.equals(pagingInfo, that.pagingInfo);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pagingInfo);
    }

    public abstract static class AbstractBuilder<T extends AbstractRequestWithModifierAndPaging, B extends AbstractBuilder<T, B>>
            extends AbstractRequestWithModifier.AbstractBuilder<T, B> {

        public B pagingInfo(PagingInfo value) {
            getBuildingInstance().setPagingInfo(value);
            return getSelf();
        }
    }

}
