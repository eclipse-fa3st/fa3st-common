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
package org.eclipse.digitaltwin.fa3st.common.util;

import java.util.Objects;


/**
 * Generic helper functions for working with strings.
 */
public class StringHelper {

    private StringHelper() {}


    /**
     * Checks if a given string is null or empty.
     *
     * @param value the string to check
     * @return true if value is null or empty, otherwise false
     */
    public static boolean isEmpty(String value) {
        return Objects.isNull(value) || value.isEmpty();
    }


    /**
     * Checks if a given string is null or blank.
     *
     * @param value the string to check
     * @return true if value is null or blank, otherwise false
     */
    public static boolean isBlank(String value) {
        return Objects.isNull(value) || value.isBlank();
    }


    /**
     * Changes to first character of the string to lower case.
     *
     * @param value the string to change
     * @return the decapitalized string, or null if input was null
     */
    public static String decapitalize(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }
}
