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
package org.eclipse.digitaltwin.fa3st.common.exception;

import org.eclipse.digitaltwin.aas4j.v3.model.Identifiable;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.fa3st.common.util.ReferenceHelper;


/**
 * Indicates that a resource is not a container element, i.e. cannot have any child elements.
 */
public class ResourceNotAContainerElementException extends Exception {

    private static final String BASE_MSG = "Resource is not a container element";

    public ResourceNotAContainerElementException(String message) {
        super(message);
    }


    public ResourceNotAContainerElementException(Reference reference, Throwable cause) {
        this(String.format("%s (reference: %s)", BASE_MSG, ReferenceHelper.toString(reference)), cause);
    }


    public ResourceNotAContainerElementException(Reference reference) {
        this(String.format("%s (reference: %s)", BASE_MSG, ReferenceHelper.toString(reference)));
    }


    public ResourceNotAContainerElementException(String id, Class<? extends Identifiable> type, Throwable cause) {
        this(String.format("%s (id: %s, type: %s)", BASE_MSG, id, type), cause);
    }


    public ResourceNotAContainerElementException(String id, Class<? extends Identifiable> type) {
        this(String.format("%s (id: %s, type: %s)", BASE_MSG, id, type));
    }


    public ResourceNotAContainerElementException(String message, Throwable cause) {
        super(message, cause);
    }


    public ResourceNotAContainerElementException(Throwable cause) {
        super(cause);
    }

}
