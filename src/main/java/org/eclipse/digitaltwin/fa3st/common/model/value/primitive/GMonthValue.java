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
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;


/**
 * A month value.
 */
public class GMonthValue extends AbstractDateTimeValue<OffsetDateTime> {

    public GMonthValue() {
        super();
    }


    public GMonthValue(OffsetDateTime value) {
        super(value);
    }


    @Override
    public Datatype getDataType() {
        return Datatype.GMONTH;
    }


    @Override
    protected DateTimeFormatter getFormatBase() {
        return new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("--MM"))
                .parseDefaulting(ChronoField.YEAR, 0)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
    }


    @Override
    protected OffsetDateTime parseLocal(String value) throws DateTimeParseException {
        return OffsetDateTime.parse(value, getFormatLocal());
    }


    @Override
    protected OffsetDateTime parseOffset(String value) throws DateTimeParseException {
        return OffsetDateTime.parse(value, getFormatOffset());
    }

}
