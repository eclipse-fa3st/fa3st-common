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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValueFactory;
import org.junit.Assert;
import org.junit.Test;


public class DateValueTest {

    private static final DateTimeFormatter FORMAT = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_OFFSET_DATE)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
            .toFormatter();

    @Test
    public void testSimple() throws ValueFormatException {
        String value = "2000-01-01";
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        OffsetDateTime expected = OffsetDateTime.of(localDateTime, ZoneId.systemDefault().getRules().getOffset(localDateTime));
        TypedValue actual = TypedValueFactory.create(Datatype.DATE, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithUTC() throws ValueFormatException {
        String value = "2000-01-01Z";
        OffsetDateTime expected = OffsetDateTime.parse(value, FORMAT);
        TypedValue actual = TypedValueFactory.create(Datatype.DATE, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }


    @Test
    public void testWithOffset() throws ValueFormatException {
        String value = "2000-01-01+12:05";
        OffsetDateTime expected = OffsetDateTime.parse(value, FORMAT);
        TypedValue actual = TypedValueFactory.create(Datatype.DATE, value);
        Assert.assertEquals(expected, actual.getValue());
        Assert.assertEquals(value, actual.asString());
    }

}
