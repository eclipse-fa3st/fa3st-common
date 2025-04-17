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

/**
 * Indicates usage of unsupported content modifier.
 */
public class UnsupportedLevelModifierException extends UnsupportedModifierException {

    public UnsupportedLevelModifierException(String level) {
        super(String.format("level '%s' not supported for this request", level));
    }


    public UnsupportedLevelModifierException() {
        super("level not supported for this request");
    }
}
