package com.tyutyutyu.oo4j.core.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
@ToString
public class OracleProcedure {

    private final String schema;
    private final String objectName;
    private final String procedureName;
    private final String objectType;
    private final int subprogramId;
    private final Integer overload;

}
