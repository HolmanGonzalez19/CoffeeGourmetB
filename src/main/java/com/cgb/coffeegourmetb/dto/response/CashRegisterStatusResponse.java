package com.cgb.coffeegourmetb.dto.response;

import com.cgb.coffeegourmetb.enums.CashRegisterStatus;

public record CashRegisterStatusResponse(
        CashRegisterStatus estado
) {
}