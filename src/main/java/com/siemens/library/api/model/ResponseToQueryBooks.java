package com.siemens.library.api.model;

import com.siemens.library.api.entity.ResourceBook;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseToQueryBooks {
    ArrayList<ResourceBook> books;
}
