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
package org.eclipse.digitaltwin.fa3st.common.model.messagebus.event.error;

import java.util.Objects;
import org.eclipse.digitaltwin.fa3st.common.model.messagebus.EventMessage;


/**
 * Event message indicating that an error has occured.
 */
public class ErrorEventMessage extends EventMessage {

    private String message;

    private ErrorLevel level;

    /**
     * Default Constructor creating ErrorEventMessage with level = Info.
     */
    public ErrorEventMessage() {
        this.level = ErrorLevel.INFO;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public ErrorLevel getLevel() {
        return level;
    }


    public void setLevel(ErrorLevel level) {
        this.level = level;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorEventMessage that = (ErrorEventMessage) o;
        return super.equals(o)
                && Objects.equals(message, that.message)
                && Objects.equals(level, that.level);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message, level);
    }


    public static Builder builder() {
        return new Builder();
    }

    public abstract static class AbstractBuilder<T extends ErrorEventMessage, B extends AbstractBuilder<T, B>> extends EventMessage.AbstractBuilder<T, B> {

        public B message(String value) {
            getBuildingInstance().setMessage(value);
            return getSelf();
        }


        public B level(ErrorLevel value) {
            getBuildingInstance().setLevel(value);
            return getSelf();
        }

    }

    public static class Builder extends AbstractBuilder<ErrorEventMessage, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected ErrorEventMessage newBuildingInstance() {
            return new ErrorEventMessage();
        }
    }
}
