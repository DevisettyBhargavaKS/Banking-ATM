package com.CodeWithBhargav.Request;

import lombok.Data;

@Data

public class TransactionRequest {
    private int amount;
    private int transactionalType;
        private int userId;


}
