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
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.eclipse.digitaltwin.aas4j.v3.model.Key;
import org.eclipse.digitaltwin.aas4j.v3.model.Message;
import org.eclipse.digitaltwin.aas4j.v3.model.OperationResult;
import org.eclipse.digitaltwin.aas4j.v3.model.OperationVariable;
import org.eclipse.digitaltwin.aas4j.v3.model.Result;
import org.eclipse.digitaltwin.aas4j.v3.model.Submodel;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.AbstractRequestWithModifierMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.AbstractSubmodelInterfaceRequestMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.ImportResultMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.MessageMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.PageMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.ResponseMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.ResultMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.AbstractDateTimeValueMixIn;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.GetOperationAsyncResultResponseValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.InvokeOperationRequestValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.OperationResultValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.PropertyValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.ReferenceElementValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.SubmodelElementCollectionValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.SubmodelElementListValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.TypedValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.AnnotatedRelationshipElementValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.BasicEventElementValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.BlobValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.EntityValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.EnumSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.FileValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.ModifierAwareSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.MultiLanguagePropertyValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.PagingMetadataSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.SubmodelElementValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.SubmodelValueSerializer;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedContentModifierException;
import org.eclipse.digitaltwin.fa3st.common.model.api.Response;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Content;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Extent;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.Page;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.PagingMetadata;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractSubmodelInterfaceRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.submodel.InvokeOperationRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.proprietary.ImportResult;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.submodel.GetOperationAsyncResultResponse;
import org.eclipse.digitaltwin.fa3st.common.model.value.AnnotatedRelationshipElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.BasicEventElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.EntityValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.FileValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.MultiLanguagePropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.PropertyValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ReferenceElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementCollectionValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.SubmodelElementListValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.TypedValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.primitive.AbstractDateTimeValue;
import org.eclipse.digitaltwin.fa3st.common.util.ElementValueHelper;
import org.eclipse.digitaltwin.fa3st.common.util.ReflectionHelper;


/**
 * Serializer for content=value.
 */
public class ValueOnlyJsonSerializer {

    private final SerializerWrapper wrapper;

    private static boolean isJreType(Class<?> type) {
        if (type.getClassLoader() == null || type.getClassLoader().getParent() == null) {
            return true;
        }
        String pkg = type.getPackage().getName();
        return pkg.startsWith("java.") || pkg.startsWith("com.sun") || pkg.startsWith("sun.");
    }


    public ValueOnlyJsonSerializer() {
        this.wrapper = new SerializerWrapper(x -> modifyMapper(x));
    }


    public JsonMapper getMapper() {
        return wrapper.getMapper();
    }


    /**
     * Serializes a given object as string using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level#DEFAULT} and
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Extent#DEFAULT}.
     *
     * @param obj the object to serialize
     * @return the serialized object
     * @throws SerializationException if serialization fails
     * @throws UnsupportedContentModifierException if obj does not support valueOnly serialization
     */
    public String write(Object obj) throws SerializationException, UnsupportedContentModifierException {
        return write(obj, Level.DEFAULT, Extent.DEFAULT);
    }


    /**
     * Serializes a given object as string using provided level and
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Extent#DEFAULT}.
     *
     * @param obj the object to serialize
     * @param level the level to use for serialization
     * @return the serialized object
     * @throws SerializationException if serialization fails
     * @throws UnsupportedContentModifierException if obj does not support valueOnly serialization
     */
    public String write(Object obj, Level level) throws SerializationException, UnsupportedContentModifierException {
        return write(obj, level, Extent.DEFAULT);
    }


    /**
     * Serializes a given object as string using
     * {@link org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level#DEFAULT} and provided extent.
     *
     * @param obj the object to serialize
     * @param extent the extent to use for serialization
     * @return the serialized object
     * @throws SerializationException if serialization fails
     * @throws UnsupportedContentModifierException if obj does not support valueOnly serialization
     */
    public String write(Object obj, Extent extent) throws SerializationException, UnsupportedContentModifierException {
        return write(obj, Level.DEFAULT, extent);
    }


    /**
     * Serializes a given object as string using provided level and extent.
     *
     * @param obj the object to serialize
     * @param level the level to use for serialization
     * @param extend the extent to use for serialization
     * @return the serialized object
     * @throws SerializationException if serialization fails
     * @throws UnsupportedContentModifierException if obj does not support valueOnly serialization
     */
    public String write(Object obj, Level level, Extent extend) throws SerializationException, UnsupportedContentModifierException {
        if (Objects.nonNull(obj) &&
                !ElementValueHelper.isValueOnlySupported(obj) &&
                !isExplicitelyAcceptedType(obj.getClass())) {
            throw new UnsupportedContentModifierException(Content.VALUE, obj.getClass());
        }
        try {
            return wrapper.getMapper().writer()
                    .withAttribute(ModifierAwareSerializer.LEVEL, level)
                    .withAttribute(ModifierAwareSerializer.EXTEND, extend)
                    .writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            throw new SerializationException("serialization failed", e);
        }
    }


    private static boolean isExplicitelyAcceptedType(Class<?> type) {
        return Key.class.equals(type)
                || OperationVariable.class.equals(type)
                || InvokeOperationRequest.class.isAssignableFrom(type)
                || GetOperationAsyncResultResponse.class.equals(type)
                || Result.class.isAssignableFrom(type)
                || Message.class.equals(type);
    }


    /**
     * Modifies the mapper by adding required mixins and De-/serializers.
     *
     * @param mapper the mapper to modify
     * @return the updated mapper
     */
    protected JsonMapper modifyMapper(JsonMapper mapper) {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        mapper.addMixIn(PropertyValue.class, PropertyValueMixin.class);
        mapper.addMixIn(SubmodelElementCollectionValue.class, SubmodelElementCollectionValueMixin.class);
        mapper.addMixIn(SubmodelElementListValue.class, SubmodelElementListValueMixin.class);
        mapper.addMixIn(TypedValue.class, TypedValueMixin.class);
        mapper.addMixIn(AbstractDateTimeValue.class, AbstractDateTimeValueMixIn.class);
        mapper.addMixIn(ReferenceElementValue.class, ReferenceElementValueMixin.class);
        mapper.addMixIn(Page.class, PageMixin.class);
        mapper.addMixIn(Message.class, MessageMixin.class);
        mapper.addMixIn(ImportResult.class, ImportResultMixin.class);
        mapper.addMixIn(AbstractRequestWithModifier.class, AbstractRequestWithModifierMixin.class);
        mapper.addMixIn(AbstractSubmodelInterfaceRequest.class, AbstractSubmodelInterfaceRequestMixin.class);
        mapper.addMixIn(InvokeOperationRequest.class, InvokeOperationRequestValueMixin.class);
        mapper.addMixIn(Response.class, ResponseMixin.class);
        mapper.addMixIn(Result.class, ResultMixin.class);
        mapper.addMixIn(GetOperationAsyncResultResponse.class, GetOperationAsyncResultResponseValueMixin.class);
        mapper.addMixIn(OperationResult.class, OperationResultValueMixin.class);
        SimpleModule module = new SimpleModule();
        ReflectionHelper.ENUMS.forEach(x -> module.addSerializer(x, new EnumSerializer()));
        module.addSerializer(MultiLanguagePropertyValue.class, new MultiLanguagePropertyValueSerializer());
        module.addSerializer(FileValue.class, new FileValueSerializer());
        module.addSerializer(BlobValue.class, new BlobValueSerializer());
        module.addSerializer(BasicEventElementValue.class, new BasicEventElementValueSerializer());
        module.addSerializer(AnnotatedRelationshipElementValue.class, new AnnotatedRelationshipElementValueSerializer());
        module.addSerializer(EntityValue.class, new EntityValueSerializer());
        module.addSerializer(SubmodelElement.class, new SubmodelElementValueSerializer());
        module.addSerializer(Submodel.class, new SubmodelValueSerializer());
        module.addSerializer(PagingMetadata.class, new PagingMetadataSerializer());
        ObjectMapper result = mapper.registerModule(module);
        result = result.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                                     BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
                        beanProperties.removeIf(property -> !evalContainerType(property.getType()).stream()
                                .allMatch(
                                        x -> isJreType(x.getRawClass())
                                                || ElementValueHelper.isValueOnlySupported(x.getRawClass())
                                                || isExplicitelyAcceptedType(x.getRawClass())));
                        return beanProperties;
                    }
                });
            }
        });
        return (JsonMapper) result;
    }


    private Set<JavaType> evalContainerType(JavaType type) {
        Set<JavaType> result = new HashSet<>();
        if (type.isContainerType()) {
            if (type.getContentType() != null) {
                result.add(type.getContentType());
            }
            if (type.getKeyType() != null) {
                result.add(type.getKeyType());
            }
            if (type.getBindings() != null) {
                result.addAll(type.getBindings().getTypeParameters());
            }
        }
        return result;
    }

}
