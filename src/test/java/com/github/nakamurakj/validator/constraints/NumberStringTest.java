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

public class NumberStringTest {

    public static class TestClass {
        @NumberString
        public String value;
    }

    public static class TestClass2 {
        @NumberString(min=1, max=18)
        public String value;
    }


    @Test
    public void test() throws IllegalArgumentException {
        TestClass testClass = new TestClass();
        List<ValidateMessage<TestClass>> message;
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());


        testClass.value = "1234567890";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());

        testClass.value = "１２３４５６７８９０";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());

        testClass.value = "xxx";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        assertEquals("NumberString length must be between 1 and 10", message.get(0).getMessage());
    }

    @Test
    public void test2() throws IllegalArgumentException {
        TestClass2 testClass2 = new TestClass2();

        testClass2.value = "123456789012345678";
        List<ValidateMessage<TestClass2>> message2 = BeanValidator.validateBean(testClass2);
        assertEquals(0, message2.size());

        testClass2.value = "1234567890123456789";
        message2 = BeanValidator.validateBean(testClass2);
        assertEquals(1, message2.size());
        assertEquals("NumberString length must be between 1 and 18", message2.get(0).getMessage());
    }

}
