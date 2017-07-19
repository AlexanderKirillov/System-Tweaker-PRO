package com.nowenui.systemtweaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

class Stats implements Serializable, Comparator<Frequency> {
    private final static long serialVersionUID = 2L;
    private List<Frequency> frequencies;
    private Map<String, Long> times;
    private Long totalTime;
    private Stats previousStats;
    private Stats zeroPoint;
    private List<Frequency> sortedByFrequency;
    private transient Map<String, Double> percentages;
    private transient Map<String, Double> partialPercentages;
    private SortMethod sortMethod;

    Stats(List<Frequency> frequencies, Map<String, Long> times, Long totalTime) {
        this.frequencies = frequencies;
        this.sortedByFrequency = new ArrayList<>(frequencies);
        Collections.copy(sortedByFrequency, frequencies);
        sortMethod = SortMethod.Frequency;
        Collections.sort(sortedByFrequency, this);
        this.times = times;
        this.totalTime = totalTime;
    }

    List<Frequency> getFrequencies() {
        return frequencies;
    }

    private Double getPercentage(Frequency freq) {
        if (percentages == null) {
            throw new IllegalStateException("ERROR!");
        }
        return percentages.get(freq.getValue());
    }

    private Double getPartialPercentage(Frequency freq) {
        if (previousStats == null) {
            throw new IllegalStateException("ERROR!");
        }
        if (partialPercentages == null) {
            throw new IllegalStateException("ERROR!");
        }
        return partialPercentages.get(freq.getValue());
    }

    @Override
    public int compare(Frequency lhf, Frequency rhf) {
        if (sortMethod == SortMethod.Frequency) {
            return lhf.getMHz().compareTo(rhf.getMHz());
        } else if (sortMethod == SortMethod.FrequencyDesc) {
            return -(lhf.getMHz().compareTo(rhf.getMHz()));
        } else if (sortMethod == SortMethod.Percentage) {
            return getPercentage(lhf).compareTo(getPercentage(rhf));
        } else if (sortMethod == SortMethod.PercentageDesc) {
            return -(getPercentage(lhf).compareTo(getPercentage(rhf)));
        } else if (sortMethod == SortMethod.PartialPercentage) {
            return getPartialPercentage(lhf).compareTo(getPartialPercentage(rhf));
        } else if (sortMethod == SortMethod.PartialPercentageDesc) {
            return -(getPartialPercentage(lhf).compareTo(getPartialPercentage(rhf)));
        } else {
            return 0;
        }
    }


    private enum SortMethod {
        Frequency, FrequencyDesc, Percentage, PercentageDesc, PartialPercentage, PartialPercentageDesc
    }
}
