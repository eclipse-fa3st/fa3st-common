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

import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.fa3st.common.util.ReferenceHelper;


/**
 * Indicates that an element is ambiguous, i.e. cannot be uniquely identified.
 */
public class AmbiguousElementException extends Exception {

    private static final String BASE_MSG = "Ambiguous element";

    public AmbiguousElementException(Referable referable, List<Reference> references) {
        super(String.format("%s (referable: %s, found matching references: %s)",
                BASE_MSG,
                referable,
                references.stream()
                        .map(ReferenceHelper::toString)
                        .collect(Collectors.joining(", "))));
    }

}
