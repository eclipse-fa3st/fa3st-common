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

import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;
import org.junit.Assert;
import org.junit.Test;


public class StringValueTest {

    @Test
    public void testEnglish() throws ValueFormatException {
        String expected = "Hello world";
        TypedValue actual = TypedValueFactory.create(Datatype.STRING, expected);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(expected, actual.asString());
    }


    @Test
    public void testGreek() throws ValueFormatException {
        String expected = "Καλημέρα κόσμε";
        TypedValue actual = TypedValueFactory.create(Datatype.STRING, expected);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(expected, actual.asString());
    }


    @Test
    public void testJapanese() throws ValueFormatException {
        String expected = "こんにちは世界";
        TypedValue actual = TypedValueFactory.create(Datatype.STRING, expected);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(expected, actual.asString());
    }

}
