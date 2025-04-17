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
package org.eclipse.digitaltwin.fa3st.common.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.eclipse.digitaltwin.fa3st.common.dataformat.DeserializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.BasicEventElementValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.EntityValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.EnumDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.MultiLanguagePropertyValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.PropertyValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.RangeValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.event.AnnotatedRelationshipElementValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.event.ElementValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.event.SubmodelElementCollectionValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.event.TypedValueDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.event.EventMessageMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.ReferenceElementValueMixin;
import org.eclipse.digitaltwin.fa3st.common.model.messagebus.EventMessage;
import org.eclipse.digitaltwin.fa3st.common.model.value.AnnotatedRelationshipElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.BasicEventElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.EntityValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.MultiLanguagePropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.RangeValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ReferenceElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.util.ReflectionHelper;


/**
 * JSON Deserializer for {@link EventMessage}.
 */
public class JsonEventDeserializer {

    private final DeserializerWrapper wrapper;

    public JsonEventDeserializer() {
        this.wrapper = new DeserializerWrapper(this::modifyMapper);
    }


    /**
     * Modifies Jackson JsonMapper.
     *
     * @param mapper mapper to modify
     */
    protected void modifyMapper(JsonMapper mapper) {
        SimpleModule module = new SimpleModule();
        ReflectionHelper.ENUMS.forEach(x -> module.addDeserializer(x, new EnumDeserializer(x)));
        module.addDeserializer(TypedValue.class, new TypedValueDeserializer());
        module.addDeserializer(PropertyValue.class, new PropertyValueDeserializer());
        module.addDeserializer(AnnotatedRelationshipElementValue.class, new AnnotatedRelationshipElementValueDeserializer());
        module.addDeserializer(BasicEventElementValue.class, new BasicEventElementValueDeserializer());
        module.addDeserializer(SubmodelElementCollectionValue.class, new SubmodelElementCollectionValueDeserializer());
        module.addDeserializer(MultiLanguagePropertyValue.class, new MultiLanguagePropertyValueDeserializer());
        module.addDeserializer(EntityValue.class, new EntityValueDeserializer());
        module.addDeserializer(RangeValue.class, new RangeValueDeserializer());
        module.addDeserializer(ElementValue.class, new ElementValueDeserializer());
        mapper.registerModule(module);
        mapper.addMixIn(EventMessage.class, EventMessageMixin.class);
        mapper.addMixIn(ReferenceElementValue.class, ReferenceElementValueMixin.class);
    }


    /**
     * Read an event message from string.
     *
     * @param <T> type of event message
     * @param json the JSON to parse
     * @param type type of event message to deserialize to
     * @return the parsed event message
     * @throws DeserializationException if deserialization fails
     */
    public <T extends EventMessage> T read(String json, Class<T> type) throws DeserializationException {
        try {
            return wrapper.getMapper().readValue(json, type);
        }
        catch (JsonProcessingException e) {
            throw new DeserializationException(
                    String.format("Deserializing event message failed (reason: %s)",
                            e.getMessage()),
                    e);
        }
    }


    /**
     * Read an event message from string.
     *
     * @param json the JSON to parse
     * @return the parsed event message
     * @throws DeserializationException if deserialization fails
     */
    public EventMessage read(String json) throws DeserializationException {
        return read(json, EventMessage.class);
    }

}
