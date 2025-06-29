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
package org.eclipse.digitaltwin.fa3st.common.model.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.eclipse.digitaltwin.aas4j.v3.model.AnnotatedRelationshipElement;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetAdministrationShell;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetInformation;
import org.eclipse.digitaltwin.aas4j.v3.model.Entity;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.Identifiable;
import org.eclipse.digitaltwin.aas4j.v3.model.KeyTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.Operation;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.aas4j.v3.model.Submodel;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementCollection;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementList;
import org.eclipse.digitaltwin.fa3st.common.util.ReferenceBuilder;
import org.eclipse.digitaltwin.fa3st.common.util.ReferenceHelper;


/**
 * Collects a map of all referables and their corresponding references.
 */
public class ReferenceCollector extends AssetAdministrationShellElementWalker {

    private Reference parent;
    private Map<Reference, Referable> result;
    private int currentListIndex;

    private void init() {
        parent = null;
        result = new HashMap<>();
        currentListIndex = -1;
    }


    /**
     * Collects a map of all referables and their corresponding references.
     *
     * @param obj the object to search, e.g., an null {@link org.eclipse.digitaltwin.aas4j.v3.model.Environment},
     *            {@link org.eclipse.digitaltwin.aas4j.v3.model.Referable}, or
     *            {@link org.eclipse.digitaltwin.aas4j.v3.model.Identifiable}
     * @return a map of all referables contained in any depth with their corresponding reference
     */
    public static Map<Reference, Referable> collect(Object obj) {
        ReferenceCollector collector = new ReferenceCollector();
        collector.walk(obj);
        return collector.result;
    }


    public ReferenceCollector() {
        this.after = new DefaultAssetAdministrationShellElementVisitor() {
            @Override
            public void visit(Referable referable) {
                if (isContainerElement(referable)) {
                    parent = ReferenceHelper.getParent(parent);
                }
            }
        };
        this.visitor = new DefaultAssetAdministrationShellElementVisitor() {

            @Override
            public void visit(Referable referable) {
                if (referable == null) {
                    return;
                }
                String id = referable.getIdShort();
                if (Identifiable.class.isAssignableFrom(referable.getClass())) {
                    id = ((Identifiable) referable).getId();
                }
                if (Objects.nonNull(parent) && parent.getKeys().get(parent.getKeys().size() - 1).getType() == KeyTypes.SUBMODEL_ELEMENT_LIST) {
                    if (currentListIndex < 0) {
                        throw new IllegalStateException("trying to process element in SubmodelElementList but no list index information available");
                    }
                    id = Integer.toString(currentListIndex);
                }

                Reference reference = ReferenceHelper.combine(
                        parent,
                        new ReferenceBuilder()
                                .element(id, referable.getClass())
                                .build());
                result.put(reference, referable);
                if (isContainerElement(referable)) {
                    parent = reference;
                }
            }
        };
    }


    @Override
    public void visit(AssetInformation element) {
        visitBefore(element);
        visitAfter(element);
    }


    @Override
    public void visit(Operation element) {
        visitBefore(element);
        visitAfter(element);
    }


    @Override
    public void walk(Object obj) {
        init();
        super.walk(obj);
    }


    private static boolean isContainerElement(Referable referable) {
        if (referable == null) {
            return false;
        }
        return Environment.class.isAssignableFrom(referable.getClass())
                || AssetAdministrationShell.class.isAssignableFrom(referable.getClass())
                || Submodel.class.isAssignableFrom(referable.getClass())
                || SubmodelElementCollection.class.isAssignableFrom(referable.getClass())
                || SubmodelElementList.class.isAssignableFrom(referable.getClass())
                || Entity.class.isAssignableFrom(referable.getClass())
                || AnnotatedRelationshipElement.class.isAssignableFrom(referable.getClass());
    }


    @Override
    public void visit(SubmodelElementList submodelElementList) {
        visitBefore(submodelElementList);
        if (submodelElementList != null) {
            visit(submodelElementList.getSemanticId());
            submodelElementList.getSupplementalSemanticIds().forEach(this::visit);
            submodelElementList.getDescription().forEach(this::visit);
            submodelElementList.getDisplayName().forEach(this::visit);
            submodelElementList.getQualifiers().forEach(this::visit);
            submodelElementList.getEmbeddedDataSpecifications().forEach(this::visit);
            submodelElementList.getExtensions().forEach(this::visit);
            if (Objects.nonNull(submodelElementList.getValue())) {
                for (int i = 0; i < submodelElementList.getValue().size(); i++) {
                    currentListIndex = i;
                    visit(submodelElementList.getValue().get(i));
                }
                currentListIndex = -1;
            }
        }
        visitAfter(submodelElementList);
    }

}
