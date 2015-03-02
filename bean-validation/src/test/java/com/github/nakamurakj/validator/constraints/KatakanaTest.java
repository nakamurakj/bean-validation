/*
 * Copyright 2015 https://github.com/nakamurakj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
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
package com.github.nakamurakj.validator.constraints;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.github.nakamurakj.validator.BeanValidator;
import com.github.nakamurakj.validator.BeanValidator.ValidateMessage;

public class KatakanaTest {

    public static final String KATAKANA = "アイウエオカキクケコサシスセソタチツテト"
            + "ナニヌネノハフフヘホヤユヨワヲン"
            + "ガギグゲゴザジズゼゾダヂヅテドバビブベボパピポペポ";


    public static class Bean {
        @Katakana
        public String str;
    }

    @Test
    public void test() {
        Bean bean = new Bean();
        bean.str = null;
        List<ValidateMessage<Bean>> validates = BeanValidator.validateBean(bean);
        assertEquals(0, validates.size());


        bean.str = KATAKANA;
        validates = BeanValidator.validateBean(bean);
        assertEquals(0, validates.size());

        bean.str = KATAKANA + "あ";
        validates = BeanValidator.validateBean(bean);
        assertEquals(1, validates.size());
        assertEquals("The format of \"Katakana\" is invalid.", validates.get(0).getMessage());
    }

}
