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

public class YesNoTest {

    public static class TestClass {
        @YesNo
        public String value;
    }

    @Test
    public void test() throws IllegalArgumentException {
        TestClass testClass = new TestClass();
        List<ValidateMessage<TestClass>> message;
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());

        testClass.value = "yes";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());

        testClass.value = "no";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());

        testClass.value = "xxx";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        assertEquals("The format of \"yes\" or \"no\" is invalid.", message.get(0).getMessage());
    }

}
