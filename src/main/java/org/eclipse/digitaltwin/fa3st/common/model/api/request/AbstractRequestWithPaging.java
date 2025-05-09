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
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.PagingInfo;


/**
 * Base class for requests that suppport paging.
 *
 * @param <T> actual type of the request
 */
public abstract class AbstractRequestWithPaging<T extends Response> extends Request<T> {

    protected PagingInfo pagingInfo;

    protected AbstractRequestWithPaging() {
        this(PagingInfo.ALL);
    }


    protected AbstractRequestWithPaging(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
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
        AbstractRequestWithPaging<T> that = (AbstractRequestWithPaging<T>) o;
        return super.equals(that)
                && Objects.equals(pagingInfo, that.pagingInfo);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pagingInfo);
    }

    public abstract static class AbstractBuilder<T extends AbstractRequestWithPaging, B extends AbstractBuilder<T, B>> extends Request.AbstractBuilder<T, B> {

        public B pagingInfo(PagingInfo value) {
            getBuildingInstance().setPagingInfo(value);
            return getSelf();
        }
    }

}
