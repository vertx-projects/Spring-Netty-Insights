/*
 *
 *
 *  * Copyright (c) 2017 Leo Lee(lichl.1980@163.com).
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License. You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations
 *  * under the License.
 *  *
 *
 */

package org.sunyata.quark.server.springcloud.publish;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.sunyata.quark.MessageQueueService;

/**
 * Created by leo on 17/3/16.
 */
@Component
public class BusinessComponentListener implements ApplicationListener<BusinessComponentEvent> {
    Logger logger = LoggerFactory.getLogger(BusinessComponentListener.class);
//    @Autowired
//    AbstractBusinessManager businessManager;

//    @Autowired
//    @Qualifier("asyncBusinessManager")
//    BusinessManager businessManager;

    @Autowired
    MessageQueueService messageQueueService;

    @Override
    public void onApplicationEvent(BusinessComponentEvent event) {
        try {
            messageQueueService.enQueue(event.getBusinName(), event.getBusinName(), 0, event.getSerialNo(), true);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
