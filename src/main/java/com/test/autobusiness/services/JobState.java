package com.test.autobusiness.services;

import lombok.Getter;
import lombok.Setter;

public enum JobState {
    NOT_STARTED,
    RUNNING,
    ENDED;

    @Getter
    @Setter
    private long fileId;
}
