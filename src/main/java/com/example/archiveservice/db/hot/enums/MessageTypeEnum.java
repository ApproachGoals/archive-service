package com.example.archiveservice.db.hot.enums;

import java.util.Arrays;
import java.util.Optional;

public enum MessageTypeEnum {
    REQUEST(2), RESPONSE(3), ERROR(4), WARNING(5), NOTIFICATION(6);

    private int val;
    MessageTypeEnum(int val) {
        this.val = val;
    }
    public Optional<MessageTypeEnum> valOf(int type) {
        return Arrays.stream(MessageTypeEnum.values()).filter(e -> e.val == type).findFirst();
    }
}
