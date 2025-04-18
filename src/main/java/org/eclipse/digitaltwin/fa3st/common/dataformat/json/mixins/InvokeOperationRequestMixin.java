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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.xml.datatype.Duration;
import org.eclipse.digitaltwin.aas4j.v3.model.Key;
import org.eclipse.digitaltwin.aas4j.v3.model.OperationVariable;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Content;
import org.eclipse.digitaltwin.fa3st.common.model.api.request.submodel.InvokeOperationRequest;


/**
 * Mixing for {@link InvokeOperationRequest}.
 */
public abstract class InvokeOperationRequestMixin {

    @JsonIgnore
    protected boolean internal;
    @JsonIgnore
    protected String id;
    @JsonIgnore
    protected Content content;
    @JsonIgnore
    protected List<Key> path;
    protected List<OperationVariable> inputArguments;
    protected List<OperationVariable> inoutputArguments;
    @JsonProperty("clientTimeoutDuration")
    protected Duration timeout;
}
