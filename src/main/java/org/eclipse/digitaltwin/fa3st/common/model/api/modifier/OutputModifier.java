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
package org.eclipse.digitaltwin.fa3st.common.model.api.modifier;

import java.util.Objects;


/**
 * Model class for output modifier.
 */
public class OutputModifier extends QueryModifier {

    public static final OutputModifier DEFAULT = new OutputModifier();
    protected Content content;

    public OutputModifier() {
        this.content = Content.NORMAL;
    }


    public Content getContent() {
        return content;
    }


    public static OutputModifier with(Content content) {
        return new Builder()
                .content(content)
                .build();
    }


    public static OutputModifier with(Level level) {
        return new Builder()
                .level(level)
                .build();
    }


    public static OutputModifier with(Extent extent) {
        return new Builder()
                .extent(extent)
                .build();
    }


    public static OutputModifier with(Content content, Level level, Extent extent) {
        return new Builder()
                .content(content)
                .level(level)
                .extent(extent)
                .build();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OutputModifier that = (OutputModifier) o;
        return super.equals(that)
                && content == that.content;
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content);
    }

    public abstract static class AbstractBuilder<T extends OutputModifier, B extends AbstractBuilder<T, B>> extends QueryModifier.AbstractBuilder<T, B> {

        public B content(Content value) {
            getBuildingInstance().content = value;
            switch (value) {
                case METADATA:
                case REFERENCE: {
                    getBuildingInstance().level = Level.CORE;
                    getBuildingInstance().extent = Extent.WITHOUT_BLOB_VALUE;
                    break;
                }
                default:
            }
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<OutputModifier, Builder> {

        @Override
        protected Builder getSelf() {
            return this;
        }


        @Override
        protected OutputModifier newBuildingInstance() {
            return new OutputModifier();
        }
    }
}
