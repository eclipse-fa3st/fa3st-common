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
 * Indicates underlying class of configuration could not be initialized.
 */
public class ConfigurationInitializationException extends ConfigurationException {

    public ConfigurationInitializationException() {
        super();
    }


    public ConfigurationInitializationException(String message) {
        super(message);
    }


    public ConfigurationInitializationException(String message, Throwable cause) {
        super(message, cause);
    }


    public ConfigurationInitializationException(Throwable cause) {
        super(cause);
    }
}
