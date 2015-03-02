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

/**
 * {@link ZipCode}のテストクラス
 */
public class ZipCodeTest {

    public static class TestTarget {
        public TestTarget(String zipCode) {
            this.zipCode = zipCode;
        }

        @ZipCode
        public String zipCode;
    }

    @Test
    public void testZipCode() {
        TestTarget testTarget = new TestTarget("001-1111");

        List<ValidateMessage<TestTarget>> results = BeanValidator.validateBean(testTarget);
        assertEquals(0, results.size());

        testTarget = new TestTarget("00z-1111");
        results = BeanValidator.validateBean(testTarget);
        assertEquals(1, results.size());

        ValidateMessage<TestTarget> message = results.get(0);
        assertEquals("The format of the zipcode is invalid.", message.getMessage());
    }
}
