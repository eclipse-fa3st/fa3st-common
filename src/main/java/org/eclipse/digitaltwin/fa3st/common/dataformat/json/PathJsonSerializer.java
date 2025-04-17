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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.fa3st.common.dataformat.SerializationException;
import org.eclipse.digitaltwin.fa3st.common.dataformat.json.path.IdShortPathElementWalker;
import org.eclipse.digitaltwin.fa3st.common.exception.UnsupportedModifierException;
import org.eclipse.digitaltwin.fa3st.common.model.IdShortPath;
import org.eclipse.digitaltwin.fa3st.common.model.api.modifier.Level;
import org.eclipse.digitaltwin.fa3st.common.model.api.paging.Page;


/**
 * Serializer for content=path.
 */
public class PathJsonSerializer {

    private final SerializerWrapper wrapper;

    public PathJsonSerializer() {
        this.wrapper = new SerializerWrapper(this::modifyMapper);
    }


    public JsonMapper getMapper() {
        return wrapper.getMapper();
    }


    /**
     * Serializes an object as string.
     *
     * @param parent the path to the parent element
     * @param obj the object to serialize
     * @return the string serialization of the object
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public String write(IdShortPath parent, Object obj) throws SerializationException, UnsupportedModifierException {
        return write(parent, obj, Level.DEFAULT);
    }


    /**
     * Serializes a given object with given level. If obj if not a AAS element subject to serialization, result will be
     * empty JSON array.
     *
     * @param parent the path to the parent element
     * @param obj object to serialize
     * @param level level of serialization
     * @return JSON array of all idShort paths subject to serialization according to specification.
     * @throws SerializationException if serialization fails
     * @throws UnsupportedModifierException if the modifier is not supported for this element
     */
    public String write(IdShortPath parent, Object obj, Level level) throws SerializationException, UnsupportedModifierException {
        if (Objects.nonNull(obj) && Page.class.isAssignableFrom(obj.getClass())) {
            Page page = (Page) obj;
            return new JsonApiSerializer().write(Page.of(
                    page.getContent().stream()
                            .map(x -> findIdShortPaths(parent, x, level))
                            .toList(),
                    page.getMetadata()));
        }
        else {
            return new JsonApiSerializer().write(findIdShortPaths(parent, obj, level));
        }
    }


    private List<String> findIdShortPaths(IdShortPath parent, Object obj, Level level) {
        IdShortPathElementWalker walker = new IdShortPathElementWalker(level);
        walker.walk(obj);
        return walker.getIdShortPaths().stream()
                .map(x -> IdShortPath.combine(parent, x).toString())
                .collect(Collectors.toList());
    }


    /**
     * Extension point. Override this method in subclasses to modify mapper.
     *
     * @param mapper current mapper
     * @return new mapper to use
     */
    protected JsonMapper modifyMapper(JsonMapper mapper) {
        return mapper;
    }

}
