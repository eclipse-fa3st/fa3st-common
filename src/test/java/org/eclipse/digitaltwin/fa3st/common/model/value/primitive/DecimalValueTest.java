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

import java.math.BigDecimal;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;
import org.junit.Assert;
import org.junit.Test;


public class DecimalValueTest {

    @Test
    public void testNegative() throws ValueFormatException {
        String value = "-1.23";
        BigDecimal expected = new BigDecimal(value);
        TypedValue actual = TypedValueFactory.create(Datatype.DECIMAL, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testLargeNumber() throws ValueFormatException {
        String value = "126789672374892739424.543233";
        BigDecimal expected = new BigDecimal(value);
        TypedValue actual = TypedValueFactory.create(Datatype.DECIMAL, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithExplicitPlusPrefix() throws ValueFormatException {
        String value = "+100000.00";
        BigDecimal expected = new BigDecimal(value);
        TypedValue actual = TypedValueFactory.create(Datatype.DECIMAL, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals("100000.00", actual.asString());
    }

}
