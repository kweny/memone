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

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 MongoDB 自动配置的注解，添加到应用程序主类上——
 * <pre>
 *    {@code @EnableMongoCarefree}
 *    {@code @SpringBootApplication}
 *     public class Application {
 *         public static void main(String[] args) {
 *             SpringApplication.run(Application.class, args);
 *         }
 *     }
 * </pre>
 *
 * @author Kweny
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MongoCarefreeConfigurer.class})
public @interface EnableMongoCarefree {

    String ENABLED_OVERRIDE_PROPERTY = "carefree.mongodb.enable";

    String[] sources() default {};

    String prefix() default "carefree.mongodb";
}