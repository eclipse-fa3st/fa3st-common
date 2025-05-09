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
import java.time.format.DateTimeParseException;
import org.eclipse.digitaltwin.fa3st.common.model.value.Datatype;


/**
 * A datetime value.
 */
public class DateTimeValue extends AbstractDateTimeValue<OffsetDateTime> {

    public DateTimeValue() {
        super();
    }


    public DateTimeValue(OffsetDateTime value) {
        super(value);
    }


    @Override
    public Datatype getDataType() {
        return Datatype.DATE_TIME;
    }


    @Override
    protected DateTimeFormatter getFormatBase() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }


    @Override
    protected OffsetDateTime parseLocal(String value) throws DateTimeParseException {
        LocalDateTime local = LocalDateTime.parse(value);
        return OffsetDateTime.of(local, ZoneId.systemDefault().getRules().getOffset(local));
    }


    @Override
    protected OffsetDateTime parseOffset(String value) throws DateTimeParseException {
        return OffsetDateTime.parse(value);
    }

}
