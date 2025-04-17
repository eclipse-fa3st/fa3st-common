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
package org.eclipse.digitaltwin.fa3st.common.serialization.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.digitaltwin.fa3st.common.dataformat.DeserializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.JsonEventDeserializer;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueFormatException;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.messagebus.EventMessage;
import org.eclipse.digitaltwin.fa3st.common.serialization.json.fixture.EventExamples;
import org.junit.Assert;
import org.junit.Test;


public class JsonEventDeserializerTest {

    private final JsonEventDeserializer deserializer = new JsonEventDeserializer();

    @Test
    public void testElementReadEventMessage() throws SerializationException, DeserializationException, IOException {
        assertEquals(EventExamples.ELEMENT_READ_EVENT, EventExamples.ELEMENT_READ_EVENT_FILE);
    }


    @Test
    public void testOperationFinishEventMessage() throws SerializationException, DeserializationException, ValueFormatException, ValueMappingException, IOException {
        assertEquals(EventExamples.OPERATION_FINISH_EVENT, EventExamples.OPERATION_FINISH_EVENT_FILE);
    }


    @Test
    public void testOperationInvokeEventMessage() throws SerializationException, DeserializationException, ValueFormatException, ValueMappingException, IOException {
        assertEquals(EventExamples.OPERATION_INVOKE_EVENT, EventExamples.OPERATION_INVOKE_EVENT_FILE);
    }


    @Test
    public void testValueReadEventMessage() throws SerializationException, DeserializationException, IOException {
        assertEquals(EventExamples.VALUE_READ_EVENT, EventExamples.VALUE_READ_EVENT_FILE);
    }


    @Test
    public void testElementCreateEventMessage() throws SerializationException, DeserializationException, ValueFormatException, IOException {
        assertEquals(EventExamples.ELEMENT_CREATE_EVENT, EventExamples.ELEMENT_CREATE_EVENT_FILE);
    }


    @Test
    public void testElementDeleteEventMessage() throws SerializationException, DeserializationException, ValueFormatException, IOException {
        assertEquals(EventExamples.ELEMENT_DELETE_EVENT, EventExamples.ELEMENT_DELETE_EVENT_FILE);
    }


    @Test
    public void testElementUpdateEventMessage() throws SerializationException, DeserializationException, ValueFormatException, IOException {
        assertEquals(EventExamples.ELEMENT_UPDATE_EVENT, EventExamples.ELEMENT_UPDATE_EVENT_FILE);
    }


    @Test
    public void testValueChangeEventMessage() throws SerializationException, DeserializationException, ValueFormatException, IOException {
        assertEquals(EventExamples.VALUE_CHANGE_EVENT, EventExamples.VALUE_CHANGE_EVENT_FILE);
    }


    @Test
    public void testErrorEventMessage() throws SerializationException, DeserializationException, ValueFormatException, IOException {
        assertEquals(EventExamples.ERROR_EVENT, EventExamples.ERROR_EVENT_FILE);
    }


    private void assertEquals(EventMessage msg, String file) throws DeserializationException, SerializationException, IOException {
        Assert.assertEquals(msg, deserializer.read(Files.readString(Paths.get(file))));
    }

}
