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
package org.eclipse.digitaltwin.fa3st.common.model;

import static org.eclipse.digitaltwin.fa3st.common.model.api.StatusCode.CLIENT_ERROR_BAD_REQUEST;

import org.eclipse.digitaltwin.fa3st.common.model.api.response.aasbasicdiscovery.DeleteAllAssetLinksByIdResponse;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.aasxfileserver.DeleteAASXPackageByIdResponse;
import org.eclipse.digitaltwin.fa3st.common.model.api.response.submodelrepository.DeleteSubmodelByIdResponse;
import org.junit.Assert;
import org.junit.Test;


/**
 * Test equals and hashCode methods on sample v3.api.response class
 */
public class ApiTest {

    @Test
    public void testEqualsDifferentSubclassesOfBaseResponse() {
        DeleteAASXPackageByIdResponse deleteAASXPackageByIdResponse = new DeleteAASXPackageByIdResponse();
        DeleteAllAssetLinksByIdResponse deleteAllAssetLinksByIdResponse = new DeleteAllAssetLinksByIdResponse();
        Assert.assertNotEquals(deleteAASXPackageByIdResponse, deleteAllAssetLinksByIdResponse);
    }


    @Test
    public void testEqualsNotSame() {
        DeleteSubmodelByIdResponse deleteSubmodelByIdResponseA = new DeleteSubmodelByIdResponse();
        DeleteSubmodelByIdResponse deleteSubmodelByIdResponseB = new DeleteSubmodelByIdResponse();
        deleteSubmodelByIdResponseB.setStatusCode(CLIENT_ERROR_BAD_REQUEST);
        Assert.assertNotEquals(deleteSubmodelByIdResponseA, deleteSubmodelByIdResponseB);
    }


    @Test
    public void testEqualsSame() {
        DeleteSubmodelByIdResponse deleteSubmodelByIdResponse = new DeleteSubmodelByIdResponse();
        Assert.assertEquals(deleteSubmodelByIdResponse, deleteSubmodelByIdResponse);
    }


    @Test
    public void testHashCode() {
        DeleteSubmodelByIdResponse deleteSubmodelByIdResponseA = new DeleteSubmodelByIdResponse();
        DeleteSubmodelByIdResponse deleteSubmodelByIdResponseB = new DeleteSubmodelByIdResponse();
        deleteSubmodelByIdResponseB.setStatusCode(CLIENT_ERROR_BAD_REQUEST);
        Assert.assertNotEquals(deleteSubmodelByIdResponseA.hashCode(), deleteSubmodelByIdResponseB.hashCode());
    }

}
