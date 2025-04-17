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
import java.util.Objects;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId;
import org.eclipse.digitaltwin.fa3st.common.model.asset.AssetIdentification;
import org.eclipse.digitaltwin.fa3st.common.model.asset.GlobalAssetIdentification;
import org.eclipse.digitaltwin.fa3st.common.model.asset.SpecificAssetIdentification;


/**
 *
 * @author jab
 */
public class AssetIdHelper {
    private AssetIdHelper() {}


    /**
     * Converts a list of {@code org.eclipse.digitaltwin.fa3st.common.model.asset.AssetIdentification} to
     * {@code org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId}.
     *
     * @param assetIds the input to convert
     * @return the converted output
     */
    public static List<SpecificAssetId> toSpecificAssetIds(List<AssetIdentification> assetIds) {
        if (Objects.isNull(assetIds)) {
            return List.of();
        }
        return assetIds.stream()
                .map(AssetIdHelper::toSpecificAssetId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Converts a {@code org.eclipse.digitaltwin.fa3st.common.model.asset.AssetIdentification} to
     * {@code org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId}.
     *
     * @param assetId the input to convert
     * @return the converted output
     */
    public static SpecificAssetId toSpecificAssetId(AssetIdentification assetId) {
        return Objects.isNull(assetId)
                ? null
                : assetId.asSpecificAssetId();
    }


    /**
     * Converts a list of {@code org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId} to
     * {@code org.eclipse.digitaltwin.fa3st.common.model.asset.AssetIdentification}.
     *
     * @param specificAssetIds the input to convert
     * @return the converted output
     */
    public static List<AssetIdentification> fromSpecificAssetIds(List<SpecificAssetId> specificAssetIds) {
        if (Objects.isNull(specificAssetIds)) {
            return List.of();
        }
        return specificAssetIds.stream()
                .map(AssetIdHelper::fromSpecificAssetId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Converts a {@code org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId} to
     * {@code org.eclipse.digitaltwin.fa3st.common.model.asset.AssetIdentification}.
     *
     * @param specificAssetId the input to convert
     * @return the converted output
     */
    public static AssetIdentification fromSpecificAssetId(SpecificAssetId specificAssetId) {
        if (Objects.isNull(specificAssetId)) {
            return null;
        }
        if (specificAssetId.getName().equalsIgnoreCase(Fa3stConstants.KEY_GLOBAL_ASSET_ID)) {
            return new GlobalAssetIdentification.Builder()
                    .value(specificAssetId.getValue())
                    .build();
        }
        return new SpecificAssetIdentification.Builder()
                .value(specificAssetId.getValue())
                .key(specificAssetId.getName())
                .build();
    }
}
