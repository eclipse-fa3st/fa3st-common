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
package org.eclipse.digitaltwin.fa3st.common.dataformat.json.mixins;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.deserializer.key.ReferenceKeyDeserializer;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.serializer.key.ReferenceKeySerializer;


/**
 * Mixin for {@link org.eclipse.digitaltwin.fa3st.common.model.api.response.proprietary.ImportResult}.
 */
public abstract class ImportResultMixin {
    @JsonSerialize(keyUsing = ReferenceKeySerializer.class)
    @JsonDeserialize(keyUsing = ReferenceKeyDeserializer.class)
    private Map<Reference, String> modelErrors;
}
