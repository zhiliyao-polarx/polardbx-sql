/*
 * Copyright [2013-2021], Alibaba Group Holding Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.polardbx.executor.operator.spill;

import com.alibaba.polardbx.optimizer.core.datatype.DataType;
import com.alibaba.polardbx.optimizer.spill.SpillMonitor;
import com.google.inject.Inject;
import org.apache.calcite.sql.OutFileParams;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class GenericSpillerFactory implements SpillerFactory {

    private final SingleStreamSpillerFactory singleStreamSpillerFactory;

    @Inject
    public GenericSpillerFactory(SingleStreamSpillerFactory singleStreamSpillerFactory) {
        this.singleStreamSpillerFactory =
            requireNonNull(singleStreamSpillerFactory, "singleStreamSpillerFactory can not be null");
    }

    @Override
    public Spiller create(List<DataType> types, SpillMonitor spillMonitor, OutFileParams outFileParams) {
        return new GenericSpiller(types, singleStreamSpillerFactory, spillMonitor, outFileParams);
    }

    @Override
    public SingleStreamSpillerFactory getStreamSpillerFactory() {
        return singleStreamSpillerFactory;
    }
}
