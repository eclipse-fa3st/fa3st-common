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

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.digitaltwin.aas4j.v3.model.AbstractLangString;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultLangStringTextType;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;


/**
 * A string with language value.
 */
public class LangStringValue extends TypedValue<AbstractLangString> {

    private static final String SEPARATOR = "@";

    public LangStringValue() {
        super();
    }


    public LangStringValue(AbstractLangString value) {
        super(value);
    }


    @Override
    public String asString() {
        if (Objects.isNull(value)) {
            return "";
        }
        return String.format("%s@%s", value.getText(), value.getLanguage());
    }


    @Override
    public void fromString(String value) throws ValueFormatException {
        if (StringUtils.isAllBlank(value)) {
            this.setValue(null);
            return;
        }
        if (!value.contains(SEPARATOR)) {
            throw new ValueFormatException("LangString must be of format [text]@[language-tag], e.g. foo@en");
        }
        if (value.lastIndexOf(SEPARATOR) == value.length() - 1) {
            throw new ValueFormatException("LangString must have a non-empty language tag");
        }
        this.setValue(new DefaultLangStringTextType.Builder()
                .text(value.substring(0, value.lastIndexOf(SEPARATOR)))
                .language(value.substring(value.lastIndexOf(SEPARATOR) + 1))
                .build());
    }


    @Override
    public Datatype getDataType() {
        return Datatype.LANG_STRING;
    }

}
