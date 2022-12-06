package com.example.businessapp.graphql.scalar;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsRuntimeWiring;
import com.netflix.graphql.dgs.DgsScalar;
import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring;

@DgsComponent
public class BigDecimalScalar {
    @DgsRuntimeWiring
    public RuntimeWiring.Builder addBigDecimalScalar(RuntimeWiring.Builder builder) {
        return builder.scalar(ExtendedScalars.GraphQLBigDecimal);
    }
}

