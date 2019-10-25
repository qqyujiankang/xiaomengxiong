/*
 * Copyright (c) 2017 [zhiye.wei@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.et.util.lifeful;


import java.lang.ref.WeakReference;

/**
*
 */

public interface LifefulGenerator<Callback> {

    /**
     * @return the callbacka.
     */
    Callback getCallback();

    /**
     * @return the weak reference which bind with life cycle.
     */
    WeakReference<Lifeful> getLifefulWeakReference();

    /**
     * Check to see whether the lifeful is null.
     *
     * @return true if lifeful is null
     */
    boolean isLifefulNull();
}
