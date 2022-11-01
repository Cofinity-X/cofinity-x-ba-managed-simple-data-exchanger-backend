/********************************************************************************
 * Copyright (c) 2022 T-Systems International Gmbh
 * Copyright (c) 2022 Contributors to the CatenaX (ng) GitHub Organisation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package com.catenax.sde.common.entities;

import java.io.Serializable;

import com.catenax.sde.common.edc.enums.DurationEnum;
import com.catenax.sde.common.edc.enums.PolicyAccessEnum;
import com.catenax.sde.common.edc.enums.UsagePolicyEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsagePolicy implements Serializable {

    @JsonProperty(value = "type")
    UsagePolicyEnum type;
    @JsonProperty(value = "typeOfAccess")
    PolicyAccessEnum typeOfAccess;
    @JsonProperty(value = "value")
    String value;
    @JsonProperty(value = "durationUnit")
    DurationEnum durationUnit;
}
