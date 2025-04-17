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

import java.time.OffsetTime;
import java.time.ZoneId;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;
import org.junit.Assert;
import org.junit.Test;


public class TimeValueTest {

    @Test
    public void testTimeOnly() throws ValueFormatException {
        String value = "14:23:00";
        OffsetTime expected = OffsetTime.of(14, 23, 0, 0, OffsetTime.now(ZoneId.systemDefault()).getOffset());
        TypedValue actual = TypedValueFactory.create(Datatype.TIME, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
        Assert.assertEquals(Datatype.TIME, actual.getDataType());
    }


    @Test
    public void testWithMillis() throws ValueFormatException {
        String value = "14:23:00.527634";
        OffsetTime expected = OffsetTime.of(14, 23, 0, 527634 * 1000, OffsetTime.now(ZoneId.systemDefault()).getOffset());
        TypedValue actual = TypedValueFactory.create(Datatype.TIME, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithMillisAndUTC() throws ValueFormatException {
        String value = "14:23:00.527634Z";
        OffsetTime expected = OffsetTime.parse(value);
        TypedValue actual = TypedValueFactory.create(Datatype.TIME, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithOffset() throws ValueFormatException {
        String value = "14:23:00+03:00";
        OffsetTime expected = OffsetTime.parse(value);
        TypedValue actual = TypedValueFactory.create(Datatype.TIME, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }

}
