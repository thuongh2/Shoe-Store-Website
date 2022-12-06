package vn.longvan.payment.paymentgateway.graphql.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DgsScalar(name = "DateCustom")
public class DateCustomScalar implements Coercing<Date, String> {

    //    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    @Override
    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof Date) {
            Date cDate = (Date) dataFetcherResult;
            return dateFormat.format(cDate);
        } else {
            throw new CoercingSerializeException("Value is not a valid ISO date time. Time format HH:mm:ss'T'dd-MM-yyyy");
        }
    }

    @NotNull
    @Override
    public Date parseValue(Object input) throws CoercingParseValueException {
        try {
            return dateFormat.parse(input.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NotNull
    @Override
    public Date parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            try {
                StringValue stringValue = (StringValue) input;
                return dateFormat.parse(stringValue.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new CoercingParseLiteralException("Value is not a valid ISO date time. Time format HH:mm:ss'T'dd-MM-yyyy");
    }
}
