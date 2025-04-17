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
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.Message;
import org.eclipse.digitaltwin.aas4j.v3.model.Result;
import org.eclipse.digitaltwin.fa3st.common.dataformat.ApiSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.AbstractRequestWithModifierMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.AbstractSubmodelInterfaceRequestMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.ImportResultMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.InvokeOperationRequestMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.MessageMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.PageMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.ResultMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.ServiceSpecificationProfileMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins.value.ReferenceElementValueMixin;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.EnumSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.ModifierAwareSerializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.PagingMetadataSerializer;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedModifierException;
import org.eclipse.digitaltwin.fa3st.common.model.ServiceSpecificationProfile;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.OutputModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.Page;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.PagingMetadata;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractRequestWithModifier;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.AbstractSubmodelInterfaceRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.submodel.InvokeOperationRequest;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.proprietary.ImportResult;
import org.eclipse.digitaltwin.fa3st.common.model.value.ElementValue;
import org.eclipse.digitaltwin.fa3st.common.model.value.ReferenceElementValue;
import org.eclipse.digitaltwin.fa3st.common.util.CollectionHelper;
import org.eclipse.digitaltwin.fa3st.common.util.Ensure;
import org.eclipse.digitaltwin.fa3st.common.util.ReflectionHelper;


/**
 * JSON API serializer for FA³ST supporting different output modifier as defined by specification.
 */
public class JsonApiSerializer implements ApiSerializer {

    private final PathJsonSerializer pathSerializer;
    private final ValueOnlyJsonSerializer valueOnlySerializer;
    private final MetadataJsonSerializer metadataJsonSerializer;
    private final SerializerWrapper wrapper;

    public JsonApiSerializer() {
        this.wrapper = new SerializerWrapper(this::modifyMapper);
        this.pathSerializer = new PathJsonSerializer();
        this.valueOnlySerializer = new ValueOnlyJsonSerializer();
        this.metadataJsonSerializer = new MetadataJsonSerializer();
    }


    /**
     * Modifies Jackson JsonMapper.
     *
     * @param mapper mapper to modify
     */
    protected void modifyMapper(JsonMapper mapper) {
        SimpleModule module = new SimpleModule();
        ReflectionHelper.ENUMS.forEach(x -> module.addSerializer(x, new EnumSerializer()));
        module.addSerializer(PagingMetadata.class, new PagingMetadataSerializer());
        mapper.registerModule(module);
        mapper.addMixIn(AbstractRequestWithModifier.class, AbstractRequestWithModifierMixin.class);
        mapper.addMixIn(AbstractSubmodelInterfaceRequest.class, AbstractSubmodelInterfaceRequestMixin.class);
        mapper.addMixIn(ReferenceElementValue.class, ReferenceElementValueMixin.class);
        mapper.addMixIn(Page.class, PageMixin.class);
        mapper.addMixIn(Message.class, MessageMixin.class);
        mapper.addMixIn(ImportResult.class, ImportResultMixin.class);
        mapper.addMixIn(InvokeOperationRequest.class, InvokeOperationRequestMixin.class);
        mapper.addMixIn(Result.class, ResultMixin.class);
        mapper.addMixIn(ServiceSpecificationProfile.class, ServiceSpecificationProfileMixin.class);
    }


    @Override
    public String write(Object obj, OutputModifier modifier) throws SerializationException, UnsupportedModifierException {
        Ensure.requireNonNull(modifier, "modifier must be non-null");
        switch (modifier.getContent()) {
            case VALUE:
                return valueOnlySerializer.write(obj, modifier.getLevel(), modifier.getExtent());
            case PATH:
                return pathSerializer.write(null, obj, modifier.getLevel());
            case METADATA:
                return metadataJsonSerializer.write(obj);
            case NORMAL:
            default: {
                return serializeNormal(obj, modifier);
            }
        }
    }


    private String serializeNormal(Object obj, OutputModifier modifier) throws SerializationException, UnsupportedModifierException {
        if (obj != null && ElementValue.class.isAssignableFrom(obj.getClass())) {
            return valueOnlySerializer.write(obj, modifier.getLevel(), modifier.getExtent());
        }
        try {
            JsonMapper mapper = wrapper.getMapper();
            if (Objects.nonNull(obj)) {
                if (List.class.isAssignableFrom(obj.getClass()) && !((List) obj).isEmpty()) {
                    Class<?> contentType = CollectionHelper.findMostSpecificCommonType(((List) obj));
                    ObjectWriter objectWriter = mapper
                            .writerFor(mapper.getTypeFactory()
                                    .constructCollectionType(List.class, contentType))
                            .withAttribute(ModifierAwareSerializer.LEVEL, modifier);
                    return objectWriter.writeValueAsString(obj);
                }
                if (Page.class.isAssignableFrom(obj.getClass())) {
                    Class<?> contentType = CollectionHelper.findMostSpecificCommonType(((Page) obj).getContent());
                    ObjectWriter objectWriter = mapper
                            .writerFor(mapper.getTypeFactory()
                                    .constructParametricType(Page.class, contentType))
                            .withAttribute(ModifierAwareSerializer.LEVEL, modifier);
                    return objectWriter.writeValueAsString(obj);
                }
            }
            return mapper.writer()
                    .withAttribute(ModifierAwareSerializer.LEVEL, modifier)
                    .writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            throw new SerializationException("serialization failed", e);
        }
    }

}
