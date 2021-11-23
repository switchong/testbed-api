package com.nftgram.core.domain.nftgram.value;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ContractTypeConverter implements AttributeConverter<ContractType, String> {
    @Override
    public String convertToDatabaseColumn(ContractType attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getName();
    }

    @Override
    public ContractType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }
        return Stream.of(ContractType.values())
                .filter(c -> c.getValue().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
