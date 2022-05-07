package com.southsystem.dataanalysis.core.processor.impl;

import com.southsystem.dataanalysis.core.processor.LineCodeType;
import com.southsystem.dataanalysis.core.processor.LineProcessor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class LineProcessFactory {

    private static Map<LineCodeType, LineProcessor> lineProcessMap;

    @Autowired
    private LineProcessFactory(List<LineProcessor> processorList) {
        lineProcessMap = processorList.stream().collect(Collectors.toUnmodifiableMap(LineProcessor::getLineCode, Function.identity()));
    }

    public LineProcessor getLineProcessor(String codeType) {
        return  Optional.ofNullable(lineProcessMap.get(LineCodeType.fromString(codeType)))
                .orElseThrow((IllegalArgumentException::new));
    }
}
