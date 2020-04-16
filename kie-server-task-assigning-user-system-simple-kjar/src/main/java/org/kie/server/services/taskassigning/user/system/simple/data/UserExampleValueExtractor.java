/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.server.services.taskassigning.user.system.simple.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kie.server.api.model.taskassigning.data.LabelValueExtractor;
import org.kie.server.services.taskassigning.user.system.api.User;

/**
 * Example value extractor for showing the ability of implementing your own users labeling strategy.
 * Note: the example is also overriding the by default "SKILLS" label meaning that this class will be applied instead
 * of the already configured in product. This is determined given the label name "SKILLS" and the higher priority 2 of
 * this implementation regarding the 0 priority of the product LabelValueExtractor.
 * <p>
 * Note: Value extractors must be declared in the resource
 * <p>
 * src/main/resources/META-INF/services/org.kie.server.api.model.taskassigning.data.LabelValueExtractor
 */
public class UserExampleValueExtractor implements LabelValueExtractor<User> {

    public UserExampleValueExtractor() {
        //SPI constructor.
    }

    public Class<User> getType() {
        return User.class;
    }

    public String getLabelName() {
        return "SKILLS";
    }

    public int getPriority() {
        return 2;
    }

    public Set<Object> extract(User source) {
        Map<String, Object> inputs = source.getAttributes();
        Object value = inputs != null ? inputs.get("skills") : null;
        return value != null ? new HashSet<Object>(Collections.singleton(value)) : null;
    }
}
