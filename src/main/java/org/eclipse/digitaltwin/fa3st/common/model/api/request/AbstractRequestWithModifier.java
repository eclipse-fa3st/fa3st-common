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
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedContentModifierException;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedExtentModifierException;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedLevelModifierException;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedModifierException;
import org.eclipse.digitaltwin.fa3st.common.model.api.Request;
import org.eclipse.digitaltwin.fa3st.common.model.api.Response;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Content;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Extent;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier;


/**
 * Base class for request that suppport {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier}.
 *
 * @param <T> actual type of the request
 */
public abstract class AbstractRequestWithModifier<T extends Response> extends Request<T> {

    private OutputModifierConstraints outputModifierConstraints;
    protected OutputModifier outputModifier;

    protected AbstractRequestWithModifier() {
        this(OutputModifierConstraints.DEFAULT);
    }


    protected AbstractRequestWithModifier(OutputModifierConstraints outputModifierConstraints) {
        this.outputModifier = OutputModifier.DEFAULT;
        this.outputModifierConstraints = outputModifierConstraints == null
                ? OutputModifierConstraints.DEFAULT
                : outputModifierConstraints;
    }


    public OutputModifier getOutputModifier() {
        return outputModifier;
    }


    public void setOutputModifier(OutputModifier outputModifier) {
        this.outputModifier = outputModifier;
    }


    public OutputModifierConstraints getOutputModifierConstraints() {
        return outputModifierConstraints;
    }


    public void setOutputModifierConstraints(OutputModifierConstraints outputModifierConstraints) {
        this.outputModifierConstraints = outputModifierConstraints;
    }


    /**
     * Checks if a given content modifier is supported for this type.
     *
     * @param content the content modifier to check
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedContentModifierException if the content
     *             modifier is not supported
     */
    public void checkContenModifierValid(Content content) throws UnsupportedContentModifierException {
        if (Objects.nonNull(content) && !outputModifierConstraints.getSupportedContentModifiers().contains(content)) {
            throw new UnsupportedContentModifierException(content, outputModifierConstraints.getSupportedContentModifiers());
        }
    }


    /**
     * Checks if a given level modifier is supported for this type.
     *
     * @param level the level modifier to check
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedLevelModifierException if provided level
     *             modifier is not supported
     */
    public void checkLevelModifierValid(Level level) throws UnsupportedLevelModifierException {
        if (Objects.nonNull(level) && !outputModifierConstraints.getSupportsLevel()) {
            throw new UnsupportedLevelModifierException();
        }

    }


    /**
     * Checks if a given extent modifier is supported for this type.
     *
     * @param extent the extent modifier to check
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedExtentModifierException if provided
     *             extent modifier is not supported
     */
    public void checkExtentModifierValid(Extent extent) throws UnsupportedExtentModifierException {
        if (!outputModifierConstraints.getSupportsExtent() && Objects.nonNull(extent)) {
            throw new UnsupportedExtentModifierException();
        }

    }


    /**
     * Validates if current output modifier violates the constraints.
     *
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedModifierException if any modifier is not
     *             supported
     */
    public void validate() throws UnsupportedModifierException {
        checkContenModifierValid(outputModifier.getContent());
        checkLevelModifierValid(outputModifier.getLevel());
        checkExtentModifierValid(outputModifier.getExtent());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractRequestWithModifier<T> that = (AbstractRequestWithModifier<T>) o;
        return super.equals(that)
                && Objects.equals(outputModifier, that.outputModifier)
                && Objects.equals(outputModifierConstraints, that.outputModifierConstraints);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), outputModifier, outputModifierConstraints);
    }

    public abstract static class AbstractBuilder<T extends AbstractRequestWithModifier, B extends AbstractBuilder<T, B>> extends Request.AbstractBuilder<T, B> {

        public B outputModifier(OutputModifier value) {
            getBuildingInstance().setOutputModifier(value);
            return getSelf();
        }


        public B outputModifierConstraints(OutputModifierConstraints value) {
            getBuildingInstance().setOutputModifierConstraints(value);
            return getSelf();
        }
    }

}
