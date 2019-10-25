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

package com.example.et.util.model;


import com.example.et.util.lifeful.OnLoadLifefulListener;

import java.util.Map;

/**
 * @author Smile Wei
 * @since 2017/7/9.
 */

public interface TaskContractModel {

    interface Presenter {
        void lifeful(String url, Map<String, Object> params, OnLoadLifefulListener<String> listener);
    }

}
