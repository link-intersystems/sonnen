/*
 * Copyright (C) 2022 Link Intersystems GmbH
 *
 * This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *   SPDX-License-Identifier: EPL-2.0
 *
 *   Contributors:
 *       Link Intersystems GmbH - initial API and implementation
 */

package com.link_intersystems.sonnen.datalogger.main;

import com.link_intersystems.sonnen.datalogger.controller.StatusDataLoggerController;
import com.link_intersystems.util.Loop;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
class StatusDataLoggerRunner implements ApplicationRunner {

    private final StatusDataLoggerController controller;
    private ApplicationArgs applicationArgs;

    public StatusDataLoggerRunner(StatusDataLoggerController controller, ApplicationArgs applicationArgs) {
        this.controller = controller;
        this.applicationArgs = applicationArgs;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SonnenDataLoggerArgs sonnenDataLoggerArgs = new SonnenDataLoggerArgs(applicationArgs);
        Loop mainLoop = getMainLoop(sonnenDataLoggerArgs);
        mainLoop.execute(controller::execute);
    }

    private Loop getMainLoop(SonnenDataLoggerArgs args) {
        Loop mainLoop = new Loop();

        mainLoop.setMaxIterations(args.getMaxIterations());
        mainLoop.setLagDurationMs(args.getLagDurationMs());
        mainLoop.setInfinite(args.isInfinite());

        return mainLoop;
    }
}
