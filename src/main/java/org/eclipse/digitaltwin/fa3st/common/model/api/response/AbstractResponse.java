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
package org.eclipse.digitaltwin.fa3st.common.model.api.response;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.MessageTypeEnum;
import org.eclipse.digitaltwin.aas4j.v3.model.Result;
import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultResult;
import org.eclipse.digitaltwin.fa3st.common.model.api.Message;
import org.eclipse.digitaltwin.fa3st.common.model.api.Response;
import org.eclipse.digitaltwin.fa3st.common.model.api.StatusCode;


/**
 * Abstract base class for protocol-agnostic responses.
 */
public abstract class AbstractResponse implements Response {

    protected StatusCode statusCode;
    protected Result result;

    @Override
    public Result getResult() {
        return result;
    }


    @Override
    public void setResult(Result result) {
        this.result = result;
    }


    protected AbstractResponse() {
        this.statusCode = StatusCode.SERVER_INTERNAL_ERROR;
        this.result = new DefaultResult.Builder().build();
    }


    @Override
    public StatusCode getStatusCode() {
        return statusCode;
    }


    @Override
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Sets statusCode and message with type error.
     *
     * @param statusCode the status code to set
     * @param message the message to set
     */
    public void setError(StatusCode statusCode, String message) {
        setStatusCode(statusCode);
        setResult(new DefaultResult.Builder()
                .messages(Message.builder()
                        .messageType(MessageTypeEnum.ERROR)
                        .text(message)
                        .build())
                .build());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractResponse that = (AbstractResponse) o;
        return Objects.equals(statusCode, that.statusCode)
                && Objects.equals(result, that.result);
    }


    @Override
    public int hashCode() {
        return Objects.hash(statusCode, result);
    }

    public abstract static class AbstractBuilder<T extends AbstractResponse, B extends AbstractBuilder<T, B>> extends ExtendableBuilder<T, B> {

        public B statusCode(StatusCode value) {
            getBuildingInstance().setStatusCode(value);
            return getSelf();
        }


        public B success() {
            getBuildingInstance().setStatusCode(StatusCode.SUCCESS);
            return getSelf();
        }


        public B created() {
            getBuildingInstance().setStatusCode(StatusCode.SUCCESS_CREATED);
            return getSelf();
        }


        public B result(Result value) {
            getBuildingInstance().setResult(value);
            return getSelf();
        }


        public B error(StatusCode statusCode, String message) {
            getBuildingInstance().setStatusCode(statusCode);
            getBuildingInstance().setResult(
                    new DefaultResult.Builder()
                            .messages(Message.builder()
                                    .messageType(MessageTypeEnum.ERROR)
                                    .text(message)
                                    .build())
                            .build());
            return getSelf();
        }
    }
}
