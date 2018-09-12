/*
 * Copyright (C) 2018 Apenk.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apenk.memone.application.autoconfig;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * TODO Kweny MongoCarefreeImportSelector
 *
 * @author Kweny
 * @since TODO version
 */
class MongoCarefreeImportSelector implements ImportSelector, EnvironmentAware, Ordered {

    private static final String[] NO_IMPORTS = {};

    private Environment environment;

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        if (!isEnabled(annotationMetadata)) {
            return NO_IMPORTS;
        }
        AnnotationAttributes attributes = getAnnotationAttributes(annotationMetadata);
//        List<String> configurations = getCandidateConfigurations(annotationMetadata,
//                attributes);
        System.out.println(attributes.getString("prefix") + "   =====");
        return NO_IMPORTS;
    }

    protected boolean isEnabled(AnnotationMetadata metadata) {
        if (getClass() == MongoCarefreeImportSelector.class) {
            return getEnvironment().getProperty(EnableMongoCarefree.ENABLED_OVERRIDE_PROPERTY, Boolean.class, true);
        }
        return true;
    }

    protected AnnotationAttributes getAnnotationAttributes(AnnotationMetadata metadata) {
        String name = getAnnotationClass().getName();
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(metadata.getAnnotationAttributes(name, true));
        Assert.notNull(attributes,
                () -> "No auto-configuration attributes found. Is "
                        + metadata.getClassName() + " annotated with "
                        + ClassUtils.getShortName(name) + "?");
        return attributes;
    }

    protected Class<?> getAnnotationClass() {
        return EnableMongoCarefree.class;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        System.out.println("set env : " + environment);
    }

    protected final Environment getEnvironment() {
        return this.environment;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

}