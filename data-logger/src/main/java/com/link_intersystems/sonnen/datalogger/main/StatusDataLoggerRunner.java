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

    public StatusDataLoggerRunner(StatusDataLoggerController controller) {
        this.controller = controller;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationArgs applicationArgs = new SpringApplicationArgs(args);
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
