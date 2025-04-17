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

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import net.javacrumbs.jsonunit.core.Option;


/**
 * Helper class to compare objects that do not properly implement equals.
 */
public class EqualsHelper {

    private EqualsHelper() {}


    /**
     * Compares two {@link com.github.fge.jsonpatch.mergepatch.JsonMergePatch} instances by serializing them to Java.
     *
     * @param a instance a
     * @param b instance b
     * @return if both inputs are equal, otherwise false
     */
    public static boolean equals(JsonMergePatch a, JsonMergePatch b) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            assertThatJson(mapper.writeValueAsString(a))
                    .when(Option.IGNORING_ARRAY_ORDER)
                    .isEqualTo(mapper.writeValueAsString(b));
            return true;
        }
        catch (AssertionError | JsonProcessingException e) {
            return false;
        }
    }
}
