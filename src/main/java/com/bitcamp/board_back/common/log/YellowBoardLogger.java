package com.bitcamp.board_back.common.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YellowBoardLogger {

    public static void logServerStart() {
        log.info("                                                                           ");
        log.info("      (\\_/)");
        log.info("      (o.o)");
        log.info("      (> <) YellowStone team is ready to code!");
        log.info("============================================================================");
        log.info("********************** YELLOW BOARD IS UP AND RUNNING! *********************");
        log.info("============================================================================");
    }
}
