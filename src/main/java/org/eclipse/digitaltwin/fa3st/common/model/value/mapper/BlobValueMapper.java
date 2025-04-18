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
package org.eclipse.digitaltwin.fa3st.common.model.value.mapper;

import org.eclipse.digitaltwin.aas4j.v3.model.Blob;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue;


/**
 * Converts between {@link org.eclipse.digitaltwin.aas4j.v3.model.Blob} and
 * {@link org.eclipse.digitaltwin.fa3st.common.model.value.BlobValue}.
 */
public class BlobValueMapper implements DataValueMapper<Blob, BlobValue> {

    @Override
    public BlobValue toValue(Blob submodelElement) {
        if (submodelElement == null) {
            return null;
        }
        BlobValue blobValue = new BlobValue();
        blobValue.setValue(submodelElement.getValue());
        blobValue.setContentType(submodelElement.getContentType());
        return blobValue;
    }


    @Override
    public Blob setValue(Blob submodelElement, BlobValue value) throws ValueMappingException {
        DataValueMapper.super.setValue(submodelElement, value);
        submodelElement.setValue(value.getValue());
        submodelElement.setContentType(value.getContentType());
        return submodelElement;
    }
}
