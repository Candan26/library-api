package com.siemens.library.api.model;

import com.siemens.library.api.entity.ResourceBorrowing;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseToQueryBorrowings {
    ArrayList<ResourceBorrowing> borrowings;
}
