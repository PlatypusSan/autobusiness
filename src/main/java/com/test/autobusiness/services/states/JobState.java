package com.test.autobusiness.services.states;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JobState {

    private final State state;
    private long fileId;
}
