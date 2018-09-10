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

package org.apenk.memone.controller;

import org.apenk.memone.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Kweny IndexController
 *
 * @author Kweny
 * @since TODO version
 */
@Controller("indexController")
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @GetMapping(value = "admin")
    public String admin(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/admin/";
    }

    @GetMapping(value = "login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        return MV("login.html");
    }

    @GetMapping(value = {"/", "index"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return MV("index.html");
    }


}