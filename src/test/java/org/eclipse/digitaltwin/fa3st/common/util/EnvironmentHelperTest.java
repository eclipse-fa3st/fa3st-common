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
package org.eclipse.digitaltwin.fa3st.common.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.Property;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.aas4j.v3.model.Submodel;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodel;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodelElementCollection;
import org.eclipse.digitaltwin.fa3st.common.exception.AmbiguousElementException;
import org.eclipse.digitaltwin.fa3st.common.exception.ResourceNotFoundException;
import org.eclipse.digitaltwin.fa3st.common.model.AASFull;
import org.eclipse.digitaltwin.fa3st.common.model.IdShortPath;
import org.eclipse.digitaltwin.fa3st.common.model.visitor.AssetAdministrationShellElementWalker;
import org.eclipse.digitaltwin.fa3st.common.model.visitor.AssetAdministrationShellElementWalker.ElementFilter;
import org.eclipse.digitaltwin.fa3st.common.model.visitor.DefaultAssetAdministrationShellElementVisitor;
import org.junit.Assert;
import org.junit.Test;


public class EnvironmentHelperTest {

    @Test
    public void testResolveWithAASFull() {
        AtomicBoolean hasFailed = new AtomicBoolean(false);
        Environment environment = AASFull.createEnvironment();
        AssetAdministrationShellElementWalker.builder()
                .filter(ElementFilter.REFERENCABLE)
                .visitor(new DefaultAssetAdministrationShellElementVisitor() {
                    @Override
                    public void visit(Referable referable) {
                        try {
                            assertResolve(referable, environment);
                        }
                        catch (Exception e) {
                            hasFailed.set(true);
                        }
                    }
                })
                .build()
                .walk(environment);
        Assert.assertFalse("at least one element could not be resolved", hasFailed.get());
    }


    @Test
    public void resolveIdShortPathInSubmodel() throws ResourceNotFoundException {
        Property expected = new DefaultProperty.Builder()
                .idShort("property1")
                .build();
        Submodel submodel = new DefaultSubmodel.Builder()
                .idShort("foo")
                .submodelElements(new DefaultSubmodelElementCollection.Builder()
                        .idShort("collection")
                        .value(expected)
                        .build())
                .submodelElements(new DefaultSubmodelElementCollection.Builder()
                        .idShort("collection")
                        .value(new DefaultProperty.Builder()
                                .idShort("property2")
                                .build())
                        .build())
                .build();
        Property actual = EnvironmentHelper.resolveUniquePath(
                IdShortPath.parse("collection.property1"),
                submodel,
                Property.class);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void resolveSematicIdPathInSubmodel() throws ResourceNotFoundException {
        Reference semanticIdCollection = ReferenceBuilder.global("collection");
        Reference semanticIdProperty1 = ReferenceBuilder.global("property1");
        Property expected = new DefaultProperty.Builder()
                .semanticId(semanticIdProperty1)
                .build();
        Submodel submodel = new DefaultSubmodel.Builder()
                .semanticId(ReferenceBuilder.global("foo"))
                .submodelElements(new DefaultSubmodelElementCollection.Builder()
                        .semanticId(semanticIdCollection)
                        .value(expected)
                        .build())
                .submodelElements(new DefaultSubmodelElementCollection.Builder()
                        .semanticId(semanticIdCollection)
                        .value(new DefaultProperty.Builder()
                                .semanticId(ReferenceBuilder.global("property"))
                                .build())
                        .build())
                .build();
        Property actual = EnvironmentHelper.resolveUniquePath(
                List.of(semanticIdCollection, semanticIdProperty1),
                submodel,
                Property.class);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void resolveSematicIdPathInSubmodel_NonUnique() throws ResourceNotFoundException {
        Reference semanticIdCollection = ReferenceBuilder.global("collection");
        Reference semanticIdProperty = ReferenceBuilder.global("property");
        Property property1 = new DefaultProperty.Builder()
                .semanticId(semanticIdProperty)
                .build();
        Property property2 = new DefaultProperty.Builder()
                .semanticId(semanticIdProperty)
                .build();
        List<Property> expected = List.of(property1, property2);
        Submodel submodel = new DefaultSubmodel.Builder()
                .semanticId(ReferenceBuilder.global("foo"))
                .submodelElements(new DefaultSubmodelElementCollection.Builder()
                        .semanticId(semanticIdCollection)
                        .value(property1)
                        .build())
                .submodelElements(new DefaultSubmodelElementCollection.Builder()
                        .semanticId(semanticIdCollection)
                        .value(property2)
                        .build())
                .build();
        List<Property> actual = EnvironmentHelper.resolvePath(
                List.of(semanticIdCollection, semanticIdProperty),
                submodel,
                Property.class);
        Assert.assertEquals(expected, actual);
    }


    private void assertResolve(Referable expected, Environment environment) throws ResourceNotFoundException, AmbiguousElementException {
        Reference reference = EnvironmentHelper.asReference(expected, environment);
        Referable actual = EnvironmentHelper.resolve(reference, environment);
        Assert.assertEquals(expected, actual);
    }
}
