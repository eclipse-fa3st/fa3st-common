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

import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.function.Consumer;
import org.eclipse.digitaltwin.aas4j.v3.model.Message;


/**
 * Wrapper class for {@link org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonDeserializer}.
 */
public class DeserializerWrapper extends org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonDeserializer {

    public DeserializerWrapper() {

    }


    public DeserializerWrapper(Consumer<JsonMapper> modifier) {
        if (modifier != null) {
            useImplementation(Message.class, org.eclipse.digitaltwin.fa3st.common.model.api.Message.class);
            modifier.accept(mapper);
        }
    }


    protected JsonMapper getMapper() {
        return mapper;
    }
}
