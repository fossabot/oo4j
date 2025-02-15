package com.tyutyutyu.oo4j.core.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class OracleProcedureField {

    private final String name;
    private final String inOut;
    private final OracleType type;

}
