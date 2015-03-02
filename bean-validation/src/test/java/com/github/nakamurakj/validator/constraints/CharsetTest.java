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

public class CharsetTest {

    public class TestValue {
        @Charset
        public String value;
    }

    @Test
    public void test() throws IllegalArgumentException {
        TestValue value = new TestValue();
        List<ValidateMessage<TestValue>> exp = BeanValidator.validateBean(value);
        assertEquals(0, exp.size());

        value.value = "UTF-8";
        exp = BeanValidator.validateBean(value);
        assertEquals(0, exp.size());

        value.value = "Shift_JIS";
        exp = BeanValidator.validateBean(value);
        assertEquals(0, exp.size());

        value.value = "Windows-31J";
        exp = BeanValidator.validateBean(value);
        assertEquals(0, exp.size());

        value.value = "EUC_JP";
        exp = BeanValidator.validateBean(value);
        assertEquals(0, exp.size());

        value.value = "xxx";
        exp = BeanValidator.validateBean(value);
        assertEquals(1, exp.size());
        ValidateMessage<TestValue> message = exp.get(0);
        assertEquals("charset is not supported.", message.getMessage());

    }

}