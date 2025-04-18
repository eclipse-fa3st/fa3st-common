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
package org.eclipse.digitaltwin.fa3st.common.model.messagebus.event.change;

import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;


/**
 * Event message indicating that an element has been changed.
 */
public abstract class ElementChangeEventMessage extends ChangeEventMessage {

    private Referable value;

    public Referable getValue() {
        return value;
    }


    public void setValue(Referable value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ElementChangeEventMessage that = (ElementChangeEventMessage) o;
        return super.equals(o)
                && Objects.equals(value, that.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    public abstract static class AbstractBuilder<T extends ElementChangeEventMessage, B extends AbstractBuilder<T, B>> extends ChangeEventMessage.AbstractBuilder<T, B> {

        public B value(Referable value) {
            getBuildingInstance().setValue(value);
            return getSelf();
        }
    }
}
