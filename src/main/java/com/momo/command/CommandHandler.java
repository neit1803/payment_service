package com.momo.command;

import java.util.List;

import com.momo.utils.CommandResult;

public interface CommandHandler {
    CommandResult execute(List<String> args);
}
