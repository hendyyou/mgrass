/**
 * Copyright (C) 2012, Grass CRM Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gcrm.service;

import java.util.List;

import com.gcrm.domain.OptionBase;

/**
 * Lead service
 */
public class OptionService<T extends OptionBase> extends BaseService<T>
        implements IOptionService<T> {

    @Override
    public List<T> getOptions(String clazz, String local) throws Exception {
        List<T> options = this.getBaseDao().getAllSortedObjects(clazz,
                "sequence", "asc");
        for (T option : options) {
            if ("zh_CN".equals(local)) {
                option.setLabel(option.getLabel_zh_CN());
            } else {
                option.setLabel(option.getLabel_en_US());
            }
        }
        return options;
    }

    public T getOptionById(Class<T> entityClass, Integer id, String local) {
        T option = this.getBaseDao().getEntityById(entityClass, id);
        if ("zh_CN".equals(local)) {
            option.setLabel(option.getLabel_zh_CN());
        } else {
            option.setLabel(option.getLabel_en_US());
        }
        return option;
    }
}
