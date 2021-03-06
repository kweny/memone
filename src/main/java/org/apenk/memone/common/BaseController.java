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

package org.apenk.memone.common;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.Map;

/**
 * TODO Kweny BaseController
 *
 * @author Kweny
 * @since 0.0.1
 */
public abstract class BaseController {

    public static String templateName = "memone";

    protected ModelAndView MV() {
        return new ModelAndView();
    }

    protected ModelAndView MV(String viewName) {
        return new ModelAndView(viewName);
    }

    protected ModelAndView MV(View view) {
        return new ModelAndView(view);
    }

    protected ModelAndView MV(String viewName, Map<String, ?> model) {
        return new ModelAndView(viewName, model);
    }

    protected ModelAndView MV(View view, Map<String, ?> model) {
        return new ModelAndView(view, model);
    }

    protected ModelAndView MV(String viewName, String modelName, Object modelValue) {
        return new ModelAndView(viewName, modelName, modelValue);
    }

    protected ModelAndView MV(View view, String modelName, Object modelValue) {
        return new ModelAndView(view, modelName, modelValue);
    }

    protected ModelAndView adminMV(String viewName) {
        return new ModelAndView(getTemplateViewName(viewName));
    }

    protected ModelAndView adminMV(String viewName, Map<String, ?> model) {
        return new ModelAndView(getTemplateViewName(viewName), model);
    }

    protected ModelAndView adminMV(String viewName, String modelName, Object modelValue) {
        return new ModelAndView(getTemplateViewName(viewName), modelName, modelValue);
    }

    private String getTemplateViewName(String viewName) {
        return templateName + "/" + viewName;
    }
}