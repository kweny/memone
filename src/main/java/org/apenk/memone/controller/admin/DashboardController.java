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

package org.apenk.memone.controller.admin;

import org.apenk.memone.common.BaseController;
import org.apenk.memone.domain.MemoneUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Kweny DashboardController
 *
 * @author Kweny
 * @since TODO version
 */
@Controller("dashboardController")
@RequestMapping(value = "admin")
public class DashboardController extends BaseController {

    @GetMapping(value = "template-name")
    public ModelAndView templateName(@RequestParam String name) {
        BaseController.templateName = name;
        return MV();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = {"/", "dashboard"})
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {
        MemoneUser user = new MemoneUser("testUser", "password");
        return adminMV("dashboard", "user", user);
    }

    @GetMapping(value = "article-list")
    public ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response) {
        return adminMV("article-list");
    }

    @GetMapping(value = "article-post")
    public ModelAndView postArticle(HttpServletRequest request, HttpServletResponse response) {
        return adminMV("article-post");
    }
}