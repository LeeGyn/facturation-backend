package com.etslyam.facturationbackend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetAllGlobalDTO {
    private String appId;
    private String requestId;
    private String macAddress;
    private String ipAddress;
}
