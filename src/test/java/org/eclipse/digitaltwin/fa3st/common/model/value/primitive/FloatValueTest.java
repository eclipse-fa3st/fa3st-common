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


public class FloatValueTest {

    @Test
    public void testNegative() throws ValueFormatException {
        String value = "-1.0";
        float expected = -1.0f;
        TypedValue actual = TypedValueFactory.create(Datatype.FLOAT, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testPositiveZero() throws ValueFormatException {
        String value = "+0.0";
        float expected = 0.0f;
        TypedValue actual = TypedValueFactory.create(Datatype.FLOAT, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals("0.0", actual.asString());
    }


    @Test
    public void testNegativeZero() throws ValueFormatException {
        String value = "-0.0";
        float expected = -0.0f;
        TypedValue actual = TypedValueFactory.create(Datatype.FLOAT, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testE() throws ValueFormatException {
        String value = "234.567e8";
        float expected = (float) 234.567e8;
        TypedValue actual = TypedValueFactory.create(Datatype.FLOAT, value);
        Assert.assertEquals(expected, actual.getValue());
    }


    @Test
    public void testNegativeInf() throws ValueFormatException {
        String value = "-INF";
        float expected = Float.NEGATIVE_INFINITY;
        TypedValue actual = TypedValueFactory.create(Datatype.FLOAT, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testNaN() throws ValueFormatException {
        String value = "NaN";
        float expected = Float.NaN;
        TypedValue actual = TypedValueFactory.create(Datatype.FLOAT, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }

}
