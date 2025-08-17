package com.example.archiveservice.db.cold.enums;

import java.util.Arrays;
import java.util.Optional;

public enum HistoryMessageTypeEnum {
    REQUEST(2), RESPONSE(3), ERROR(4), WARNING(5), NOTIFICATION(6);

    private int val;
    HistoryMessageTypeEnum(int val) {
        this.val = val;
    }
    public Optional<HistoryMessageTypeEnum> valOf(int type) {
        return Arrays.stream(HistoryMessageTypeEnum.values()).filter(e -> e.val == type).findFirst();
    }
}
