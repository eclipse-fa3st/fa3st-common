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

import jakarta.xml.bind.DatatypeConverter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;


/**
 * A date value.
 */
public class DateValue extends AbstractDateTimeValue<OffsetDateTime> {

    public DateValue() {
        super();
    }


    public DateValue(OffsetDateTime value) {
        super(value);
    }


    @Override
    public Datatype getDataType() {
        return Datatype.DATE;
    }


    @Override
    protected DateTimeFormatter getFormatBase() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }


    @Override
    protected OffsetDateTime parseLocal(String value) throws DateTimeParseException {
        Instant instant = DatatypeConverter.parseDate(value).getTime().toInstant();
        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(instant);
        return instant.atOffset(offset);
    }


    @Override
    protected OffsetDateTime parseOffset(String value) throws DateTimeParseException {
        Calendar calendar = DatatypeConverter.parseDate(value);
        ZoneOffset offset = calendar.getTimeZone().toZoneId().getRules().getOffset(Instant.now());
        return calendar.getTime().toInstant().atOffset(offset);
    }

}
