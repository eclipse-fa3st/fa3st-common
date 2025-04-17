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

import java.util.stream.Stream;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedExtentModifierException;


/**
 * Enum of different extent options.
 */
public enum Extent {
    WITHOUT_BLOB_VALUE("WithoutBlobValue"),
    WITH_BLOB_VALUE("WithBlobValue");

    private final String name;
    public static final Extent DEFAULT = Extent.WITHOUT_BLOB_VALUE;

    public String getName() {
        return name;
    }


    Extent(String name) {
        this.name = name;
    }


    /**
     * Returns matching enum value from given string value.The names are matched case-insensitive, i.e. ignoring case.
     *
     * @param value the string value
     * @return matching enum value
     * @throws org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedExtentModifierException if the value
     *             does not match any element
     */
    public static Extent fromString(String value) throws UnsupportedExtentModifierException {
        return Stream.of(Extent.values())
                .filter(x -> x.getName().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new UnsupportedExtentModifierException(value));
    }


    /**
     * Returns matching enum value from given string value. The names are matched case-insensitive, i.e. ignoring case.
     * If the provided value does not match any enum value then {@link Extent#DEFAULT} is returned.
     *
     * @param value the string value
     * @return matching enum value or default ({@link Extent#DEFAULT}) if there is no match
     */
    public static Extent fromStringOrDefault(String value) {
        return Stream.of(Extent.values())
                .filter(x -> x.getName().equalsIgnoreCase(value))
                .findAny()
                .orElse(Extent.DEFAULT);
    }
}
