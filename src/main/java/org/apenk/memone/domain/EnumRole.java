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

package org.apenk.memone.domain;

/**
 * TODO Kweny EnumRole
 *
 * @author Kweny
 * @since TODO version
 */
public enum EnumRole {

    ADMIN, EDITOR, AUTHOR, CONTRIBUTOR, SUBSCRIBER, VISITOR,

    ;

    public static final String[] ALL_ROLES = {ADMIN.name(), EDITOR.name(), AUTHOR.name(), CONTRIBUTOR.name(), SUBSCRIBER.name(), VISITOR.name()};

    public static final String [] ADMIN_ROLES = {ADMIN.name(), EDITOR.name(), AUTHOR.name(), CONTRIBUTOR.name()};

    public static final String[] USER_ROLES = {SUBSCRIBER.name()};
}