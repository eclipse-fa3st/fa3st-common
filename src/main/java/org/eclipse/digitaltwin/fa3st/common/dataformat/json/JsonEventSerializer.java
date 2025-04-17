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
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.event.EventMessageMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.ReferenceElementValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.AnnotatedRelationshipElementValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.BasicEventElementValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.BlobValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.EnumSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.FileValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.MultiLanguagePropertyValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.event.ElementValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.event.TypedValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.model.messagebus.EventMessage;
import org.eclipse.digitaltwin.fa3st.common.model.value.AnnotatedRelationshipElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.BasicEventElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.FileValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.MultiLanguagePropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ReferenceElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.util.ReflectionHelper;


/**
 * JSON Serializer for {@link EventMessage}.
 */
public class JsonEventSerializer {

    private final SerializerWrapper wrapper;

    public JsonEventSerializer() {
        this.wrapper = new SerializerWrapper(this::modifyMapper);
    }


    /**
     * Modifies Jackson JsonMapper.
     *
     * @param mapper mapper to modify
     */
    protected void modifyMapper(JsonMapper mapper) {
        SimpleModule module = new SimpleModule();
        ReflectionHelper.ENUMS.forEach(x -> module.addSerializer(x, new EnumSerializer()));

        module.setSerializerModifier(new BeanSerializerModifier() {
            @Override
            public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
                if (ElementValue.class.isAssignableFrom(beanDesc.getBeanClass())) {
                    return new ElementValueSerializer((JsonSerializer<Object>) serializer);
                }
                return serializer;
            }
        });
        module.addSerializer(BlobValue.class, new BlobValueSerializer());
        module.addSerializer(FileValue.class, new FileValueSerializer());
        module.addSerializer(BasicEventElementValue.class, new BasicEventElementValueSerializer());
        module.addSerializer(MultiLanguagePropertyValue.class, new MultiLanguagePropertyValueSerializer());
        module.addSerializer(AnnotatedRelationshipElementValue.class, new AnnotatedRelationshipElementValueSerializer());
        module.addSerializer(TypedValue.class, new TypedValueSerializer());
        mapper.registerModule(module);
        mapper.addMixIn(EventMessage.class, EventMessageMixin.class);
        mapper.addMixIn(ReferenceElementValue.class, ReferenceElementValueMixin.class);
    }


    /**
     * Serializes a event message as JSON.
     *
     * @param msg the message to serialize
     * @return the JSON string representation of the message
     * @throws SerializationException if serialization fails
     */
    public String write(EventMessage msg) throws SerializationException {
        try {
            return wrapper.getMapper().writer().writeValueAsString(msg);
        }
        catch (JsonProcessingException e) {
            throw new SerializationException("serialization failed", e);
        }
    }
}
