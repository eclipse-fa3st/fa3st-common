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
package org.eclipse.digitaltwin.fa3st.common.model.value;

import java.util.stream.Stream;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXsd;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.AnyURIValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.Base64BinaryValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.BooleanValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.ByteValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.DateTimeValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.DateValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.DecimalValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.DoubleValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.DurationValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.FloatValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.GDayValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.GMonthDayValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.GMonthValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.GYearMonthValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.GYearValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.HexBinaryValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.IntValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.IntegerValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.LangStringValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.LongValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.NegativeIntegerValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.NonNegativeIntegerValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.NonPositiveIntegerValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.PositiveIntegerValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.ShortValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.StringValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.TimeValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.UnsignedByteValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.UnsignedIntValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.UnsignedLongValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.UnsignedShortValue;


/**
 * Datatypes defined in AAS specification.
 */
public enum Datatype {
    STRING("xs:string", StringValue.class, DataTypeDefXsd.STRING),
    BOOLEAN("xs:boolean", BooleanValue.class, DataTypeDefXsd.BOOLEAN),
    DECIMAL("xs:decimal", DecimalValue.class, DataTypeDefXsd.DECIMAL),
    INTEGER("xs:integer", IntegerValue.class, DataTypeDefXsd.INTEGER),
    DOUBLE("xs:double", DoubleValue.class, DataTypeDefXsd.DOUBLE),
    FLOAT("xs:float", FloatValue.class, DataTypeDefXsd.FLOAT),
    DATE("xs:date", DateValue.class, DataTypeDefXsd.DATE),
    TIME("xs:time", TimeValue.class, DataTypeDefXsd.TIME),
    DATE_TIME("xs:datetime", DateTimeValue.class, DataTypeDefXsd.DATE_TIME),
    GYEAR("xs:gYear", GYearValue.class, DataTypeDefXsd.GYEAR),
    GMONTH("xs:gMonth", GMonthValue.class, DataTypeDefXsd.GMONTH),
    GDAY("xs:gDay", GDayValue.class, DataTypeDefXsd.GDAY),
    GYEAR_MONTH("xs:gYearMonth", GYearMonthValue.class, DataTypeDefXsd.GYEAR_MONTH),
    GMONTH_DAY("xs:gMonthDay", GMonthDayValue.class, DataTypeDefXsd.GMONTH_DAY),
    DURATION("xs:Duration", DurationValue.class, DataTypeDefXsd.DURATION),
    BYTE("xs:byte", ByteValue.class, DataTypeDefXsd.BYTE),
    SHORT("xs:short", ShortValue.class, DataTypeDefXsd.SHORT),
    INT("xs:int", IntValue.class, DataTypeDefXsd.INT),
    LONG("xs:long", LongValue.class, DataTypeDefXsd.LONG),
    UNSIGNED_BYTE("xs:unsignedByte", UnsignedByteValue.class, DataTypeDefXsd.UNSIGNED_BYTE),
    UNSIGNED_SHORT("xs:unsignedShort", UnsignedShortValue.class, DataTypeDefXsd.UNSIGNED_SHORT),
    UNSIGNED_INT("xs:unsignedInt", UnsignedIntValue.class, DataTypeDefXsd.UNSIGNED_INT),
    UNSIGNED_LONG("xs:unsignedLong", UnsignedLongValue.class, DataTypeDefXsd.UNSIGNED_LONG),
    POSITIVE_INTEGER("xs:positiveInteger", PositiveIntegerValue.class, DataTypeDefXsd.POSITIVE_INTEGER),
    NON_NEGATIVE_INTEGER("xs:nonNegativeInteger", NonNegativeIntegerValue.class, DataTypeDefXsd.NON_NEGATIVE_INTEGER),
    NEGATIVE_INTEGER("xs:negativeInteger", NegativeIntegerValue.class, DataTypeDefXsd.NEGATIVE_INTEGER),
    NON_POSITIVE_INTEGER("xs:nonPositiveInteger", NonPositiveIntegerValue.class, DataTypeDefXsd.NON_POSITIVE_INTEGER),
    HEX_BINARY("xs:hexBinary", HexBinaryValue.class, DataTypeDefXsd.HEX_BINARY),
    BASE64_BINARY("xs:base64Binary", Base64BinaryValue.class, DataTypeDefXsd.BASE64BINARY),
    ANY_URI("xs:anyURI", AnyURIValue.class, DataTypeDefXsd.ANY_URI),
    LANG_STRING("rdf:langString", LangStringValue.class, DataTypeDefXsd.STRING);

    public static final Datatype DEFAULT = Datatype.STRING;
    private final Class<? extends TypedValue> implementation;
    private final String name;
    private final DataTypeDefXsd aas4jDatatype;

    /**
     * Finds datatype from string. Matching is case-sensitive. If no match is found, {@link Datatype#DEFAULT} is
     * returned.
     *
     * @param name name of datatype as defined in AAS specification
     * @return matching datatype if found, else {@link Datatype#DEFAULT}
     */
    public static Datatype fromName(String name) {
        return Stream.of(Datatype.values())
                .filter(x -> x.getName().equals(name))
                .findAny()
                .orElse(DEFAULT);
    }


    /**
     * Finds datatype from aas4jDatatype {@link DataTypeDefXsd}. If no match is found, {@link Datatype#DEFAULT} is
     * returned.
     *
     * @param value the {@link DataTypeDefXsd}
     * @return matching datatype if found, else {@link Datatype#DEFAULT}
     */
    public static Datatype fromAas4jDatatype(DataTypeDefXsd value) {
        return Stream.of(Datatype.values())
                .filter(x -> value == x.getAas4jDatatype())
                .findAny()
                .orElse(DEFAULT);
    }


    /**
     * Checks if a given string is a valid (i.e. supported) datatype.
     *
     * @param name name of the datatype
     * @return true is it is a valid datatype, otherwise false
     */
    public static boolean isValid(String name) {
        return Stream.of(Datatype.values())
                .anyMatch(x -> x.getName().equals(name));
    }


    private Datatype(String name, Class<? extends TypedValue> implementation, DataTypeDefXsd aas4jDatatype) {
        this.name = name;
        this.implementation = implementation;
        this.aas4jDatatype = aas4jDatatype;
    }


    public String getName() {
        return name;
    }


    public DataTypeDefXsd getAas4jDatatype() {
        return aas4jDatatype;
    }


    protected Class<? extends TypedValue> getImplementation() {
        return implementation;
    }

}
