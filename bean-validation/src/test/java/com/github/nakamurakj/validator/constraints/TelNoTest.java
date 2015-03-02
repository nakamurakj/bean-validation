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

public class TelNoTest {

    public static class TestClass {
        @TelNo
        public String value;
    }

    @Test
    public void test() throws IllegalArgumentException {
        List<ValidateMessage<TestClass>> message;
        TestClass testClass = new TestClass();

        // 固定電話
        testClass.value = "03-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        testClass.value = "011-011-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        testClass.value = "0123-01-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        testClass.value = "01234-1-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());

        // 数値以外はエラー
        testClass.value = "xxx-asasa-fafsa";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        assertEquals("The format of the tel no is invalid.", message.get(0).getMessage());

        // 090,080,070,050は許容
        testClass.value = "090-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        testClass.value = "080-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        testClass.value = "070-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        testClass.value = "050-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(0, message.size());
        // それ以外の0n0はエラー
        testClass.value = "060-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        testClass.value = "040-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        testClass.value = "030-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        testClass.value = "020-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());
        testClass.value = "010-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());

        testClass.value = "103-1111-1111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());

        testClass.value = "01111111111";
        message = BeanValidator.validateBean(testClass);
        assertEquals(1, message.size());

    }

}
