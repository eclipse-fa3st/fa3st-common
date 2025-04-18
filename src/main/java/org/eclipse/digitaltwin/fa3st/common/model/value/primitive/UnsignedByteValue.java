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
package org.eclipse.digitaltwin.fa3st.common.model.value.primitive;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;


/**
 * An unsigned byte value.
 */
public class UnsignedByteValue extends TypedValue<Short> {

    public UnsignedByteValue() {
        super();
    }


    public UnsignedByteValue(Short value) {
        super(value);
    }


    @Override
    public String asString() {
        return Short.toString(value);
    }


    @Override
    public void fromString(String value) throws ValueFormatException {
        if (StringUtils.isAllBlank(value)) {
            this.setValue(null);
            return;
        }
        try {
            Short valueShort = Short.parseShort(value);
            if (valueShort < 0 || valueShort > 255) {
                throw new ValueFormatException(String.format("value must be between 0 and 255 (actual value: %s)", value));
            }
            this.setValue(valueShort);
        }
        catch (NumberFormatException e) {
            throw new ValueFormatException(e);
        }
    }


    @Override
    public Datatype getDataType() {
        return Datatype.UNSIGNED_BYTE;
    }

}
