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

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;
import org.junit.Assert;
import org.junit.Test;


public class GYearMonthValueTest {

    private static final DateTimeFormatter FORMAT_BASE = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("yyyy-MM"))
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    private static final DateTimeFormatter FORMAT_LOCAL = new DateTimeFormatterBuilder()
            .append(FORMAT_BASE)
            .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
            .toFormatter();

    private static final DateTimeFormatter FORMAT_OFFSET = new DateTimeFormatterBuilder()
            .append(FORMAT_BASE)
            .appendZoneOrOffsetId()
            .toFormatter();

    @Test
    public void testSimple() throws ValueFormatException {
        String value = "2000-02";
        OffsetDateTime expected = OffsetDateTime.parse(value, FORMAT_LOCAL);
        TypedValue actual = TypedValueFactory.create(Datatype.GYEAR_MONTH, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithUTC() throws ValueFormatException {
        String value = "2000-02Z";
        OffsetDateTime expected = OffsetDateTime.parse(value, FORMAT_OFFSET);
        TypedValue actual = TypedValueFactory.create(Datatype.GYEAR_MONTH, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithOffset() throws ValueFormatException {
        String value = "2000-02+03:00";
        OffsetDateTime expected = OffsetDateTime.parse(value, FORMAT_OFFSET);
        TypedValue actual = TypedValueFactory.create(Datatype.GYEAR_MONTH, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }

}
