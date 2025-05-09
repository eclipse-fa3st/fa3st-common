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

import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultLangStringTextType;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;
import org.junit.Assert;
import org.junit.Test;


public class LangStringValueTest {

    @Test
    public void testNormal() throws ValueFormatException {
        DefaultLangStringTextType expected = new DefaultLangStringTextType.Builder()
                .text("Hello")
                .language("en")
                .build();
        String value = "Hello@en";
        TypedValue actual = TypedValueFactory.create(Datatype.LANG_STRING, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test(expected = ValueFormatException.class)
    public void testEmptyLanguage() throws ValueFormatException {
        String value = "Hello@";
        TypedValueFactory.create(Datatype.LANG_STRING, value);
    }


    @Test(expected = ValueFormatException.class)
    public void testNoLanguage() throws ValueFormatException {
        String value = "Hello";
        TypedValueFactory.create(Datatype.LANG_STRING, value);
    }


    @Test
    public void testEmptyString() throws ValueFormatException {
        DefaultLangStringTextType expected = null;
        String value = "";
        TypedValue actual = TypedValueFactory.create(Datatype.LANG_STRING, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testNull() throws ValueFormatException {
        DefaultLangStringTextType expected = null;
        String value = null;
        TypedValue actual = TypedValueFactory.create(Datatype.LANG_STRING, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals("", actual.asString());
    }


    @Test
    public void testOnlyLanguage() throws ValueFormatException {
        DefaultLangStringTextType expected = new DefaultLangStringTextType.Builder()
                .text("")
                .language("en")
                .build();
        String value = "@en";
        TypedValue actual = TypedValueFactory.create(Datatype.LANG_STRING, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }

}
