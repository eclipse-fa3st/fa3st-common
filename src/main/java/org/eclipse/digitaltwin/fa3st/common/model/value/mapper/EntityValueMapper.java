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

import java.util.Objects;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.Entity;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.fa3st.common.exception.ValueMappingException;
import org.eclipse.digitaltwin.fa3st.common.model.value.EntityValue;
import org.eclipse.digitaltwin.fa3st.common.util.ElementValueHelper;
import org.eclipse.digitaltwin.fa3st.common.util.LambdaExceptionHelper;


/**
 * Converts between {@link org.eclipse.digitaltwin.aas4j.v3.model.Entity} and
 * {@link org.eclipse.digitaltwin.fa3st.common.model.value.EntityValue}.
 */
public class EntityValueMapper implements DataValueMapper<Entity, EntityValue> {

    @Override
    public EntityValue toValue(Entity submodelElement) throws ValueMappingException {
        if (submodelElement == null) {
            return null;
        }
        EntityValue value = EntityValue.builder().build();
        value.setEntityType(submodelElement.getEntityType());

        if (Objects.nonNull(submodelElement.getStatements())) {
            value.setStatements(submodelElement.getStatements().stream()
                    .filter(Objects::nonNull)
                    .filter(ElementValueHelper::isValueOnlySupported)
                    .collect(Collectors.toMap(
                            Referable::getIdShort,
                            LambdaExceptionHelper.rethrowFunction(ElementValueMapper::toValue))));
        }
        value.setGlobalAssetId(submodelElement.getGlobalAssetId());
        value.setSpecificAssetIds(submodelElement.getSpecificAssetIds());
        return value;
    }


    @Override
    public Entity setValue(Entity submodelElement, EntityValue value) throws ValueMappingException {
        DataValueMapper.super.setValue(submodelElement, value);
        if (value != null) {
            for (SubmodelElement statement: submodelElement.getStatements()) {
                if (statement != null
                        && value.getStatements() != null
                        && value.getStatements().containsKey(statement.getIdShort())) {
                    ElementValueMapper.setValue(statement, value.getStatements().get(statement.getIdShort()));

                }
            }

            submodelElement.setEntityType(value.getEntityType());
            submodelElement.setGlobalAssetId(value.getGlobalAssetId());
            submodelElement.setSpecificAssetIds(value.getSpecificAssetIds());
        }
        return submodelElement;
    }
}
