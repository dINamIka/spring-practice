package com.yarmak.picture;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.net.URI;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Picture {

    private final String id;
    private final String name;
    private final URI uri;
    private final Resolution resolution;

}
