/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.faces.test.servlet31.facelets;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Issue1807IT {

    private String webUrl;
    private WebClient webClient;

    @Before
    public void setUp() {
        webUrl = System.getProperty("integration.url");
        webClient = new WebClient();
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void testRepeatNested() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/repeatNested.xhtml");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form:refresh");
        page = (HtmlPage) submit.click();

        HtmlCheckBoxInput cell0_0 = (HtmlCheckBoxInput) page.getElementById("form:level1:0:level2:0:_");
        assertTrue(cell0_0.isChecked());
        
        HtmlCheckBoxInput cell0_1 = (HtmlCheckBoxInput) page.getElementById("form:level1:0:level2:1:_");
        assertFalse(cell0_1.isChecked());

        HtmlCheckBoxInput cell1_0 = (HtmlCheckBoxInput) page.getElementById("form:level1:1:level2:0:_");
        assertFalse(cell1_0.isChecked());

        HtmlCheckBoxInput cell1_1 = (HtmlCheckBoxInput) page.getElementById("form:level1:1:level2:1:_");
        assertFalse(cell1_1.isChecked());
    }
}