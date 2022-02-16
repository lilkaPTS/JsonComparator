package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dependencies<K,V> {
    private List<K> compared;
    private List<K> additional1;
    private List<K> additional2;
    private Map<K,V> map1;
    private Map<K,V> map2;
}
