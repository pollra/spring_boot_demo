package com.pollra.web.factory;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public interface TodoFactory<OUT, IN> {
    OUT action(OUT out, IN in);
}
